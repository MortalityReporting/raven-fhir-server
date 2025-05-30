package edu.gatech.chai.VRDR.messaging;

import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.parser.LenientErrorHandler;
import ca.uhn.fhir.parser.JsonParser4BundleOfBundles;
import edu.gatech.chai.VRDR.context.VRDRFhirContext;
import edu.gatech.chai.VRDR.messaging.util.DocumentBundler;
import edu.gatech.chai.VRDR.messaging.util.MessageParseException;
import edu.gatech.chai.VRDR.model.DeathCertificateDocument;
import edu.gatech.chai.VRDR.model.DeathDate;
import edu.gatech.chai.VRDR.model.DeathLocation;
import edu.gatech.chai.VRDR.model.util.AddressUtil;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.DeathCertificateDocumentUtil;
import org.hl7.fhir.r4.model.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BaseMessage extends Bundle {

    protected Parameters messageParameters;
    protected MessageHeader messageHeader;

    public static void addBundleEntryAndLinkToHeader(Bundle bundle, MessageHeader header, Bundle innerBundle) {
        // remove any existing entries of the same type
        bundle.getEntry().removeIf(entry ->
                innerBundle.getResourceType().equals(entry.getResource().getResourceType()));
        // remove the header link
        header.setFocus(null);
        // create a new bundle entry
        Bundle.BundleEntryComponent newEntry = new Bundle.BundleEntryComponent();
        String innerBundleRef = innerBundle.getId();
        if (innerBundleRef != null && !innerBundleRef.startsWith("urn:uuid:")) {
            innerBundleRef = "urn:uuid:" + innerBundleRef;
        }
        newEntry.setFullUrl(innerBundleRef);
        newEntry.setResource(innerBundle);
        newEntry.getResource().setId(ensureBareId(innerBundle.getId()));
        // add the new entry to the bundle
        bundle.addEntry(newEntry);
        // add the new entry reference to the header
        header.addFocus(new Reference(innerBundleRef));
    }

    public static String ensureRefPrefix(String id) {
        if (id != null && !id.startsWith("urn:uuid:")) {
            id = "urn:uuid:" + id;
        }
        return id;
    }

    public static String ensureBareId(String id) {
        if (id != null && id.startsWith("urn:uuid:")) {
            id = id.substring("urn:uuid:".length(), id.length());
        }
        return id;
    }

    protected BaseMessage() {
        super();
        setId(UUID.randomUUID().toString());
        setType(BundleType.MESSAGE);
        setTimestamp(new Date());
    }

    protected BaseMessage(Bundle messageBundle) {
        this(messageBundle, false);
    }

    protected BaseMessage(Bundle messageBundle, boolean ignoreExceptions) {
        this();

        // configure bundle
        setId(messageBundle.getId());
        setTimestamp(messageBundle.getTimestamp());
        BundleType bundleType = messageBundle == null ? BundleType.NULL : messageBundle.getType();
        if (!ignoreExceptions && bundleType != BundleType.MESSAGE) {
            throw new MessageParseException("The FHIR Bundle must be of type message, not " + bundleType.toString(), messageBundle);
        }
        setType(bundleType);

        messageHeader = CommonUtil.findEntry(messageBundle, MessageHeader.class, ignoreExceptions);
        messageParameters = CommonUtil.findEntry(messageBundle, Parameters.class, ignoreExceptions);
        if (!ignoreExceptions && !isMessageEventMatchingMessageType(getIGMessageType())) {
            throw new MessageParseException(getMessageEventTypeMismatchErrorMessage(getIGMessageType()), messageBundle);
        }

        addBundleEntryForHeaderAndParameters();
    }
    protected BaseMessage(String messageType) {
        this(messageType, true);
    }

    // to support jurisdictions who want to bypass adding meta profile by default
    protected BaseMessage(String messageType, boolean includeMetaProfiles) {
        this();

        // Start with a message header
        messageHeader = new MessageHeader();
        if (includeMetaProfiles) {
            messageHeader.getMeta().addProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-SubmissionHeader");
        }
        messageHeader.setId(UUID.randomUUID().toString());
        messageHeader.setEvent(new UriType(messageType));

        // add message header source
        MessageHeader.MessageSourceComponent source = new MessageHeader.MessageSourceComponent();
        messageHeader.setSource(source);

        // add message header destination
        MessageHeader.MessageDestinationComponent destination = new MessageHeader.MessageDestinationComponent();
        destination.setEndpoint(DeathRecordSubmissionMessage.MESSAGE_TYPE);
        List<MessageHeader.MessageDestinationComponent> destinationComponents = new ArrayList<>();
        destinationComponents.add(destination);
        messageHeader.setDestination(destinationComponents);

        // setup parameters and reference in header
        messageParameters = new Parameters();
        if (includeMetaProfiles) {
            messageParameters.getMeta().addProfile("http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-MessageParameters");
        }
        messageParameters.setId(UUID.randomUUID().toString());
        messageHeader.addFocus(new Reference(ensureRefPrefix(messageParameters.getId())));
        addBundleEntryForHeaderAndParameters();
    }

    protected void addBundleEntryForHeaderAndParameters() {
        // add message header to bundle
        Bundle.BundleEntryComponent headerBundleComponent = new Bundle.BundleEntryComponent();
        headerBundleComponent.setFullUrl(ensureRefPrefix(messageHeader.getId()));
        headerBundleComponent.setResource(messageHeader);
        headerBundleComponent.getResource().setId(ensureBareId(messageHeader.getId()));
        addEntry(headerBundleComponent);

        // add parameters resource to bundle
        Bundle.BundleEntryComponent parametersBundleComponent = new Bundle.BundleEntryComponent();
        parametersBundleComponent.setFullUrl(ensureRefPrefix(messageParameters.getId()));
        parametersBundleComponent.setResource(messageParameters);
        parametersBundleComponent.getResource().setId(ensureBareId(messageParameters.getId()));
        addEntry(parametersBundleComponent);
    }

    protected <T extends Bundle> void setDocumentBundleFromMessageBundle(Class<T> tClass, DocumentBundler<T> documentBundler, Bundle messageBundle) {
        try {
            Bundle bundle = CommonUtil.findEntry(messageBundle, tClass);
            if (bundle == null) {
                throw new IllegalArgumentException("No bundle found in the passed messageBundle");
            }
            if (!(tClass.isAssignableFrom(bundle.getClass()))) {
                throw new IllegalArgumentException("Bundle found in the passed messageBundle is of type "
                        + bundle.getClass().getCanonicalName() + " not of expected type " + tClass.getCanonicalName());
            }
            documentBundler.setDocumentBundle((T)bundle);
        } catch (IllegalArgumentException ex) {
            throw new MessageParseException("Error processing entry in the message: " + ex.getMessage(), messageBundle);
        }
    }

    public String getMessageHeaderId() {
        return messageHeader != null ? messageHeader.getId() : null;
    }

    protected String getIGMessageType() {
        return null; // override for specific IG message types in subclass
    }


    protected String getMessageEventType() {
        return messageHeader != null && messageHeader.hasEventUriType() ? messageHeader.getEventUriType().getValue() : null;
    }

    protected boolean isMessageEventMatchingMessageType(String messageType) {
        String messageEventType = getMessageEventType();
        return messageEventType != null && messageEventType.equals(messageType);
    }

    protected String getMessageEventTypeMismatchErrorMessage(String messageType) {
        return "Message event uri type " + getMessageEventType() + " does not match the expected message type " + messageType;
    }

    protected void extractBusinessIdentifiers(DeathCertificateDocument from) {
        extractCertNo(from);
        extractStateAuxiliaryId(from);
        extractDeathYear(from);
        extractJurisdictionId(from);
    }

    private void extractCertNo(DeathCertificateDocument from) {
        setCertNo(null);
        if (from != null && from.hasIdentifier() && from.getIdentifier().hasExtension(DeathCertificateDocumentUtil.certificateNumberUrl)) {
            Extension extension = from.getIdentifier().getExtensionByUrl(DeathCertificateDocumentUtil.certificateNumberUrl);
            if (extension.hasValue() && extension.getValue() instanceof IntegerType) {
                setCertNo(((IntegerType) extension.getValue()).getValue());
            }
            else if (extension.hasValue() && extension.getValue() instanceof StringType) {
                setCertNo(Integer.parseInt(((StringType) extension.getValue()).getValue()));
            }
            else {
                setCertNo(null);
            }
        }
        else {
            setCertNo(null);
        }
    }

    private void extractStateAuxiliaryId(DeathCertificateDocument from) {
        if (from != null && from.hasIdentifier() && from.getIdentifier().hasExtension(DeathCertificateDocumentUtil.auxillaryStateIndentifierUrl)) {
            Extension extension = from.getIdentifier().getExtensionByUrl(DeathCertificateDocumentUtil.auxillaryStateIndentifierUrl);
            if (extension.hasValue() && extension.getValue() instanceof StringType) {
                setStateAuxiliaryId(((StringType) extension.getValue()).getValue());
            }
            else {
                setStateAuxiliaryId(null);
            }
        } else {
            setStateAuxiliaryId(null);
        }
    }

    private void extractDeathYear(DeathCertificateDocument from) {
        if (from != null && from.getDeathDate() != null && from.getDeathDate().size() > 0) {
            DeathDate deathDate = from.getDeathDate().get(0);
            if (deathDate.getEffective() instanceof DateTimeType) {
                DateTimeType dateTimeType = (DateTimeType) deathDate.getEffective();
                this.setDeathYear(dateTimeType.getYear());
            } else if (deathDate.getEffective() instanceof DateType) {
                DateType dateType = (DateType) deathDate.getEffective();
                setDeathYear(dateType.getYear());
            } else if (deathDate.getEffective() instanceof StringType) {
                StringType stringType = (StringType) deathDate.getEffective();
                setDeathYear(Integer.parseInt(stringType.getValue()));
            } else {
                setDeathYear(null);
            }
        } else {
            setDeathYear(null);
        }
    }

    private void extractJurisdictionId(DeathCertificateDocument from) {
        setJurisdictionId(getDeathLocationJurisdiction(from));
    }

    public String getDeathLocationJurisdiction(DeathCertificateDocument from) {
        if (from == null) {
            return null;
        }
        for (DeathLocation deathLocation : from.getDeathLocation()) {
            if (deathLocation.hasAddress()) {
                Address address = deathLocation.getAddress();
                StringType stateElement = address.getStateElement();
                if (stateElement.hasExtension(AddressUtil.locationJurisdictionIdUrl)) {
                    Extension extension = stateElement.getExtensionByUrl(AddressUtil.locationJurisdictionIdUrl);
                    if (extension.hasValue() && extension.getValue() instanceof StringType) {
                        return ((StringType) extension.getValue()).getValue();
                    }
                }
                return address.getState();
            }
        }
        return null;
    }

    protected void setSingleStringValue(String key, String value) {
        messageParameters.setParameter(key, (String)null);
        if (value != null && !value.trim().isEmpty()) {
            messageParameters.setParameter(key, value);
        }
    }

    public Integer getCertNo() {
        if (messageParameters.hasParameter("cert_no") && messageParameters.getParameter("cert_no") instanceof IntegerType) {
            IntegerType certNoIntegerType = (IntegerType) messageParameters.getParameter("cert_no");
            if (certNoIntegerType.hasValue()) {
                return certNoIntegerType.getValue();
            } else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    public void setCertNo(Integer value) {
        messageParameters.setParameter("cert_no", (UnsignedIntType)null);
        if (value != null) {
            if (value > 999999) {
                throw new IllegalArgumentException("Certificate number must be a maximum of six digits");
            }
            messageParameters.addParameter("cert_no", new UnsignedIntType(value));
        }
    }

    public String getStateAuxiliaryId() {
        if (messageParameters.hasParameter("state_auxiliary_id") && messageParameters.getParameter("state_auxiliary_id") instanceof StringType) {
            StringType stateAuxiliaryIdStringType = (StringType) messageParameters.getParameter("state_auxiliary_id");
            if (stateAuxiliaryIdStringType.hasValue()) {
                return stateAuxiliaryIdStringType.getValue();
            } else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    public void setStateAuxiliaryId(String value) {
        setSingleStringValue("state_auxiliary_id", value);
    }

    public Integer getDeathYear() {
        if (messageParameters.hasParameter() && messageParameters.getParameter("death_year") instanceof IntegerType) {
            IntegerType deathYearIntegerType = (IntegerType) messageParameters.getParameter("death_year");
            if (deathYearIntegerType.hasValue()) {
                return deathYearIntegerType.getValue();
            } else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    public void setDeathYear(Integer value) {
        messageParameters.setParameter("death_year", (UnsignedIntType)null);
        if (value != null) {
            if (value < 1000 || value > 9999) {
                throw new IllegalArgumentException("Year of death must be specified using four digits");
            }
            messageParameters.addParameter("death_year", new UnsignedIntType(value));
        }
    }

    public String getJurisdictionId() {
        if (messageParameters.hasParameter("jurisdiction_id") && messageParameters.getParameter("jurisdiction_id") instanceof StringType) {
            StringType jurisdictionIdStringType = (StringType) messageParameters.getParameter("jurisdiction_id");
            if (jurisdictionIdStringType.hasValue()) {
                return jurisdictionIdStringType.getValue();
            } else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    public void setJurisdictionId(String value) {
        messageParameters.setParameter("jurisdiction_id", (StringType)null);
        if (value != null) {
            if (value.length() != 2) {
                throw new IllegalArgumentException("Jurisdiction ID must be a two character string");
            }
            setSingleStringValue("jurisdiction_id", value);
        }
    }

    public Bundle cloneAsBundle() {
        // All messages have resource type Bundle according to the VRDR Messaging IG
        // so we have to reconstruct the Bundle object before outputting it
        // otherwise the parser uses the class name as the resource type
        Bundle bundle = new Bundle();
        bundle.setId(getId());
        bundle.setType(BundleType.MESSAGE);
        bundle.setTimestamp(getTimestamp());
        for (Bundle.BundleEntryComponent entry : getEntry()) {
            bundle.addEntry(entry);
        }
        return bundle;
    }

    public String toJson(VRDRFhirContext ctx, boolean prettyPrint) {
        return ctx
                .getCtx()
                .newJsonParser()
                .setPrettyPrint(prettyPrint)
                .encodeResourceToString(
                        cloneAsBundle());
    }

    public String toJson(VRDRFhirContext ctx) {
        return toJson(ctx, false);
    }

    public String toXML(VRDRFhirContext ctx, boolean prettyPrint) {
        return ctx
                .getCtx()
                .newXmlParser()
                .setPrettyPrint(prettyPrint)
                .encodeResourceToString(
                        cloneAsBundle());
    }

    public String toXML(VRDRFhirContext ctx) {
        return toXML(ctx, false);
    }

    public String getMessageType() {
        if (messageHeader != null && messageHeader.getEvent() != null && messageHeader.getEvent() instanceof UriType) {
            return ((UriType) messageHeader.getEvent()).getValue();
        } else {
            return null;
        }
    }

    public void setMessageType(String value) {
        messageHeader.setEvent(new UriType(value));
    }

    public String getMessageSource() {
        if (messageHeader != null && messageHeader.getSource() != null) {
            return messageHeader.getSource().getEndpoint();
        } else {
            return null;
        }
    }

    public void setMessageSource(String value) {
        if (messageHeader.getSource() == null) {
            messageHeader.setSource(new MessageHeader.MessageSourceComponent());
        }
        messageHeader.getSource().setEndpoint(value);
    }

    public String getMessageDestination() {
        if (messageHeader != null && messageHeader.getDestination().size() > 0) {
            return messageHeader.getDestination().get(0).getEndpoint();
        } else {
            return null;
        }
    }

    public void setMessageDestination(String value) {
        messageHeader.getDestination().clear();
        MessageHeader.MessageDestinationComponent dest = new MessageHeader.MessageDestinationComponent();
        dest.setEndpoint(value);
        messageHeader.getDestination().add(dest);
    }

    public String getNCHSIdentifier() {
        Integer deathYear = getDeathYear();
        String jurisdictionId = getJurisdictionId();
        Integer certNo = getCertNo();
        if (deathYear == null || jurisdictionId == null || certNo == null) {
            return null;
        }
        return String.format("%04d", deathYear) + jurisdictionId + String.format("%06d", certNo);
    }

    public static String getAbsoluteFilePath(String filePath) {
        File file = new File(filePath);
        if (file.isAbsolute()) {
            return filePath;
        } else {
            return new File(System.getProperty("user.dir"), filePath).getPath();
        }
    }

    public static InputStream getInputStream(String filePath) {
        String path = getAbsoluteFilePath(filePath);
        try {
            return new FileInputStream(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T extends Bundle> T parseXML(Class<T> tClass, VRDRFhirContext ctx, String xmlString) {
        return parse(tClass, ctx.getCtx().newXmlParser(), null, xmlString);
    }

    public static <T extends Bundle> T parseJson(Class<T> tClass, VRDRFhirContext ctx, String jsonString) {
        return parse(tClass, ctx.getCtx().newJsonParser(), null, jsonString);
    }

    public static <T extends Bundle> T parseJsonBundleOfBundles(Class<T> tClass, VRDRFhirContext ctx, String jsonString) {
        return parse(tClass, new JsonParser4BundleOfBundles(ctx.getCtx(), new LenientErrorHandler()), null, jsonString);
    }

    public static <T extends Bundle> T parseXMLFile(Class<T> tClass, VRDRFhirContext ctx, String filePath) {
        return parse(tClass, ctx.getCtx().newXmlParser(), getInputStream(filePath), null);
    }

    public static <T extends Bundle> T parseJsonFile(Class<T> tClass, VRDRFhirContext ctx, String filePath) {
        return parse(tClass, ctx.getCtx().newJsonParser(), getInputStream(filePath), null);
    }

    public static <T extends Bundle> T parse(Class<T> tClass, IParser parser, InputStream stream, String bundleString) {
        if (stream != null && bundleString != null) {
            throw new IllegalArgumentException("Cannot parse from both a stream and a string, one must be null");
        }
        try (InputStreamReader reader = stream == null ? null : new InputStreamReader(stream)) {
            parser.setParserErrorHandler(new LenientErrorHandler());
            Bundle bundle = reader != null
                    ? parser.setParserErrorHandler(new LenientErrorHandler()).parseResource(Bundle.class, reader)
                    : parser.setParserErrorHandler(new LenientErrorHandler()).parseResource(Bundle.class, bundleString);
            if (BaseMessage.class.isAssignableFrom(tClass)) {
                return (T) tClass.getConstructor(Bundle.class).newInstance(bundle);
            } else if (Bundle.class.isAssignableFrom(tClass)) {
                return (T) bundle;
            } else {
                throw new IllegalArgumentException("Cannot parse to class " + tClass.getName());
            }
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof MessageParseException) {
                throw (MessageParseException)e.getTargetException();
            } else {
                throw new IllegalArgumentException("Unable to instantiate class with bundle parameter, exception: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to parse bundle, exception: " + e);
        }
    }

public static List<BaseMessage> parseBundleOfBundles(VRDRFhirContext ctx, String bundleStrings) {
        Bundle outerBundle = BaseMessage.parseJsonBundleOfBundles(Bundle.class, ctx, bundleStrings);
        List<BaseMessage> listMessages = new ArrayList();
        ListIterator iterator = outerBundle.getEntry().listIterator();
        BaseMessage message;
        String messageType;
        while(iterator.hasNext()) {
            BundleEntryComponent bundleEntryComponent = (BundleEntryComponent)iterator.next();
            Resource resource = bundleEntryComponent.getResource();
            if(resource.getResourceType().toString().equals("Bundle")) {
                Bundle bundle = (Bundle)resource;
                if(bundle != null) {
                    message = new BaseMessage(bundle, true);
                    messageType = message.getMessageType();
                    switch(messageType) {
                        case DeathRecordSubmissionMessage.MESSAGE_TYPE:
                            listMessages.add(new DeathRecordSubmissionMessage(bundle));
                            break;
                        case DeathRecordUpdateMessage.MESSAGE_TYPE:
                            listMessages.add(new DeathRecordUpdateMessage(bundle));
                            break;
                        case AcknowledgementMessage.MESSAGE_TYPE:
                            listMessages.add(new AcknowledgementMessage(bundle));
                            break;
                        case DeathRecordVoidMessage.MESSAGE_TYPE:
                            listMessages.add(new DeathRecordVoidMessage(bundle));
                            break;
                        case DeathRecordAliasMessage.MESSAGE_TYPE:
                            listMessages.add(new DeathRecordAliasMessage(bundle));
                            break;
                        case CauseOfDeathCodingMessage.MESSAGE_TYPE:
                            listMessages.add(new CauseOfDeathCodingMessage(bundle));
                            break;
                        case DemographicsCodingMessage.MESSAGE_TYPE:
                            listMessages.add(new DemographicsCodingMessage(bundle));
                            break;
                        case CauseOfDeathCodingUpdateMessage.MESSAGE_TYPE:
                            listMessages.add(new CauseOfDeathCodingUpdateMessage(bundle));
                            break;
                        case DemographicsCodingUpdateMessage.MESSAGE_TYPE:
                            listMessages.add(new DemographicsCodingUpdateMessage(bundle));
                            break;
                        case ExtractionErrorMessage.MESSAGE_TYPE:
                            listMessages.add(new ExtractionErrorMessage(bundle));
                            break;
                        case StatusMessage.MESSAGE_TYPE:
                            listMessages.add(new StatusMessage(bundle));
                            break;
                        default:
                            String errorText;
                            if(message.messageHeader == null || message.messageHeader.isEmpty()) {
                                errorText = "Failed to find a Bundle Entry containing a Resource of type MessageHeader";
                            } else if(messageType == null) {
                                errorText = "Message type was missing from MessageHeader";
                            } else {
                                errorText = "Unsupported message type: " + messageType;
                            }
                            throw new MessageParseException(errorText, message);
                    }
                }
            }
        }
        return listMessages;
    }
}
