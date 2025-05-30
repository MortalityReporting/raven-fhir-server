package edu.gatech.chai.VRDR.messaging;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.messaging.util.DocumentBundler;
import edu.gatech.chai.VRDR.model.DemographicCodedContentBundle;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.MessageHeader;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.ResourceType;

@ResourceDef(name = "DemographicsCodingUpdateMessage", profile = "http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-DemographicsCodingUpdateMessage")
public class DemographicsCodingUpdateMessage extends BaseMessage implements DocumentBundler<DemographicCodedContentBundle> {

    // this comes from the VRDR Messaging IG http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/StructureDefinition-VRM-DemographicsCodingUpdateMessage.html
    public static final String MESSAGE_TYPE = "http://nchs.cdc.gov/vrdr_demographics_coding_update";

    private DemographicCodedContentBundle demographicCodedContentBundle;

    public DemographicsCodingUpdateMessage() {
        super(MESSAGE_TYPE);
    }

    public DemographicsCodingUpdateMessage(Bundle messageBundle) {
        super(messageBundle);
        setDocumentBundleFromMessageBundle(DemographicCodedContentBundle.class, this, messageBundle);
    }

    public void setDocumentBundle(DemographicCodedContentBundle demographicCodedContentBundle) {
        setDemographicCodedContentBundle(demographicCodedContentBundle);
    }

    public DemographicCodedContentBundle getDemographicCodedContentBundle() {
        return demographicCodedContentBundle;
    }

    public void setDemographicCodedContentBundle(DemographicCodedContentBundle demographicCodedContentBundle) {
        this.demographicCodedContentBundle = demographicCodedContentBundle;
        addBundleEntryAndLinkToHeader(this, messageHeader, demographicCodedContentBundle);
    }

    public DemographicsCodingUpdateMessage(BaseMessage messageToCode) {
        this(messageToCode == null ? null : messageToCode.getMessageHeaderId(),
                messageToCode == null ? null : messageToCode.getMessageDestination(),
                messageToCode == null ? null : messageToCode.getMessageSource());
        setCertNo(messageToCode == null ? null : messageToCode.getCertNo());
        setStateAuxiliaryId(messageToCode == null ? null : messageToCode.getStateAuxiliaryId());
        setDeathYear(messageToCode == null ? null : messageToCode.getDeathYear());
        setJurisdictionId(messageToCode == null ? null : messageToCode.getJurisdictionId());
    }

    public DemographicsCodingUpdateMessage(String messageId, String source, String destination) {
        super(MESSAGE_TYPE);
        messageHeader.getSource().setEndpoint(source == null ? DeathRecordSubmissionMessage.MESSAGE_TYPE : source);
        setMessageDestination(destination);
        MessageHeader.MessageHeaderResponseComponent resp = new MessageHeader.MessageHeaderResponseComponent();
        resp.setIdentifier(messageId);
        resp.setCode(MessageHeader.ResponseType.OK);
        messageHeader.setResponse(resp);
    }

    public String getIGMessageType() {
        return MESSAGE_TYPE;
    }

    public DemographicCodedContentBundle getDocumentBundle() {
        return getDocumentBundle();
    }

    public String getCodedMessageId() {
        return messageHeader != null && messageHeader.getResponse() != null ? messageHeader.getResponse().getIdentifier() : null;
    }

    public void setCodedMessageId(String value) {
        if (messageHeader.getResponse() == null) {
            messageHeader.setResponse(new MessageHeader.MessageHeaderResponseComponent());
            messageHeader.getResponse().setCode(MessageHeader.ResponseType.OK);
        }
        messageHeader.getResponse().setIdentifier(value);
    }

}
