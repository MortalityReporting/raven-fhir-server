package ca.uhn.fhir.parser;

/*
 * #%L
 * This is a modified version of JsonParser.java from ca.uhn.fhir.parser of ca.uhn.hapi.fhir
 * (version 4.1.0) to function as 1 of the 2 helper classes for parsing FHIR Bundle of Bundles
 * #L%
 */

import ca.uhn.fhir.context.*;
import ca.uhn.fhir.parser.json.*;
import ca.uhn.fhir.parser.json.JsonLikeValue.ScalarType;
import ca.uhn.fhir.parser.json.JsonLikeValue.ValueType;
import org.apache.commons.lang3.Validate;
import org.hl7.fhir.instance.model.api.*;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * This class is the FHIR JSON parser/encoder. Users should not interact with this class directly, but should use
 * {@link FhirContext#newJsonParser4BundleOfBundles()} to get an instance.
 */
public class JsonParser4BundleOfBundles extends JsonParser {
    private FhirContext myContext;
    private boolean myPrettyPrint;

    /**
     * Do not use this constructor, the recommended way to obtain a new instance of the JSON parser is to invoke
     * {@link FhirContext#newJsonParser4BundleOfBundles()}.
     *
     * @param theParserErrorHandler
     */
    public JsonParser4BundleOfBundles(FhirContext theContext, IParserErrorHandler theParserErrorHandler) {
        super(theContext, theParserErrorHandler);
        myContext = theContext;
    }

    private void beginArray(JsonLikeWriter theEventWriter, String arrayName) throws IOException {
        theEventWriter.beginArray(arrayName);
    }

    private void beginObject(JsonLikeWriter theEventWriter, String arrayName) throws IOException {
        theEventWriter.beginObject(arrayName);
    }

    private JsonLikeWriter createJsonWriter(Writer theWriter) {
        JsonLikeStructure jsonStructure = new GsonStructure();
        JsonLikeWriter retVal = jsonStructure.getJsonLikeWriter(theWriter);
        return retVal;
    }

    public <T extends IBaseResource> T doParseResource(Class<T> theResourceType, JsonLikeStructure theJsonStructure) {
        JsonLikeObject object = theJsonStructure.getRootObject();
        JsonLikeValue resourceTypeObj = object.get("resourceType");
        if (resourceTypeObj == null || !resourceTypeObj.isString() || isBlank(resourceTypeObj.getAsString())) {
            throw new DataFormatException("Invalid JSON content detected, missing required element: 'resourceType'");
        }
        String resourceType = resourceTypeObj.getAsString();
        ParserState4BundleOfBundles<? extends IBaseResource> state = ParserState4BundleOfBundles.getPreResourceInstance(this, theResourceType, myContext, true, getErrorHandler());
        ParserState4BundleOfBundles.getPreResourceInstance(this, theResourceType, myContext, true, getErrorHandler());
        state.enteringNewElement(null, resourceType);
        parseChildren(object, state);
        state.endingElement();
        state.endingElement();
        @SuppressWarnings("unchecked")
        T retVal = (T) state.getObject();
        
        return retVal;
    }

    @Override
    public void encodeResourceToJsonLikeWriter(IBaseResource theResource, JsonLikeWriter theJsonLikeWriter) throws IOException, DataFormatException {
        Validate.notNull(theResource, "theResource can not be null");
        Validate.notNull(theJsonLikeWriter, "theJsonLikeWriter can not be null");
        if (theResource.getStructureFhirVersionEnum() != myContext.getVersion().getVersion()) {
            throw new IllegalArgumentException(
                    "This parser is for FHIR version " + myContext.getVersion().getVersion() + " - Can not encode a structure for version " + theResource.getStructureFhirVersionEnum());
        }
        EncodeContext encodeContext = new EncodeContext();
        String resourceName = myContext.getResourceDefinition(theResource).getName();
        encodeContext.pushPath(resourceName, true);
        doEncodeResourceToJsonLikeWriter(theResource, theJsonLikeWriter, encodeContext);
    }

    private JsonLikeArray grabJsonArray(JsonLikeObject theObject, String nextName, String thePosition) {
        JsonLikeValue object = theObject.get(nextName);
        if (object == null || object.isNull()) {
            return null;
        }
        if (!object.isArray()) {
            throw new DataFormatException("Syntax error parsing JSON FHIR structure: Expected ARRAY at element '" + thePosition + "', found '" + object.getJsonType() + "'");
        }
        return object.getAsArray();
    }

    private void parseAlternates(JsonLikeValue theAlternateVal, ParserState4BundleOfBundles<?> theState, String theElementName, String theAlternateName) {
        if (theAlternateVal == null || theAlternateVal.isNull()) {
            return;
        }
        if (theAlternateVal.isArray()) {
            JsonLikeArray array = theAlternateVal.getAsArray();
            if (array.size() > 1) {
                throw new DataFormatException("Unexpected array of length " + array.size() + " (expected 0 or 1) for element: " + theElementName);
            }
            if (array.size() == 0) {
                return;
            }
            parseAlternates(array.get(0), theState, theElementName, theAlternateName);
            return;
        }

        JsonLikeValue alternateVal = theAlternateVal;
        if (alternateVal.isObject() == false) {
            getErrorHandler().incorrectJsonType(null, theAlternateName, ValueType.OBJECT, null, alternateVal.getJsonType(), null);
            return;
        }

        JsonLikeObject alternate = alternateVal.getAsObject();
        for (String nextKey : alternate.keySet()) {
            JsonLikeValue nextVal = alternate.get(nextKey);
            if ("extension".equals(nextKey)) {
                boolean isModifier = false;
                JsonLikeArray array = nextVal.getAsArray();
                parseExtension(theState, array, isModifier);
            } else if ("modifierExtension".equals(nextKey)) {
                boolean isModifier = true;
                JsonLikeArray array = nextVal.getAsArray();
                parseExtension(theState, array, isModifier);
            } else if ("id".equals(nextKey)) {
                if (nextVal.isString()) {
                    theState.attributeValue("id", nextVal.getAsString());
                } else {
                    getErrorHandler().incorrectJsonType(null, "id", ValueType.SCALAR, ScalarType.STRING, nextVal.getJsonType(), nextVal.getDataType());
                }
            } else if ("fhir_comments".equals(nextKey)) {
                parseFhirComments(nextVal, theState);
            }
        }
    }

    private void parseChildren(JsonLikeObject theObject, ParserState4BundleOfBundles<?> theState) {
        Set<String> keySet = theObject.keySet();
        int allUnderscoreNames = 0;
        int handledUnderscoreNames = 0;
        for (String nextName : keySet) {
            if ("resourceType".equals(nextName)) {
                continue;
            } else if ("extension".equals(nextName)) {
                JsonLikeArray array = grabJsonArray(theObject, nextName, "extension");
                parseExtension(theState, array, false);
                continue;
            } else if ("modifierExtension".equals(nextName)) {
                JsonLikeArray array = grabJsonArray(theObject, nextName, "modifierExtension");
                parseExtension(theState, array, true);
                continue;
            } else if (nextName.equals("fhir_comments")) {
                parseFhirComments(theObject.get(nextName), theState);
                continue;
            } else if (nextName.charAt(0) == '_') {
                allUnderscoreNames++;
                continue;
            }

            JsonLikeValue nextVal = theObject.get(nextName);
            String alternateName = '_' + nextName;
            JsonLikeValue alternateVal = theObject.get(alternateName);
            if (alternateVal != null) {
                handledUnderscoreNames++;
            }
            parseChildren(theState, nextName, nextVal, alternateVal, alternateName, false);
        }
    }

    private void parseChildren(ParserState4BundleOfBundles<?> theState, String theName, JsonLikeValue theJsonVal, JsonLikeValue theAlternateVal, String theAlternateName, boolean theInArray) {
        if (theName.equals("id")) {
            if (!theJsonVal.isString()) {
                getErrorHandler().incorrectJsonType(null, "id", ValueType.SCALAR, ScalarType.STRING, theJsonVal.getJsonType(), theJsonVal.getDataType());
            }
        }

        if (theJsonVal.isArray()) {
            JsonLikeArray nextArray = theJsonVal.getAsArray();

            JsonLikeValue alternateVal = theAlternateVal;
            if (alternateVal != null && alternateVal.isArray() == false) {
                getErrorHandler().incorrectJsonType(null, theAlternateName, ValueType.ARRAY, null, alternateVal.getJsonType(), null);
                alternateVal = null;
            }

            JsonLikeArray nextAlternateArray = JsonLikeValue.asArray(alternateVal); // could be null
            for (int i = 0; i < nextArray.size(); i++) {
                JsonLikeValue nextObject = nextArray.get(i);
                JsonLikeValue nextAlternate = null;
                if (nextAlternateArray != null && nextAlternateArray.size() >= (i + 1)) {
                    nextAlternate = nextAlternateArray.get(i);
                }
                parseChildren(theState, theName, nextObject, nextAlternate, theAlternateName, true);
            }
        } else if (theJsonVal.isObject()) {
            if (!theInArray && theState.elementIsRepeating(theName)) {
                getErrorHandler().incorrectJsonType(null, theName, ValueType.ARRAY, null, ValueType.OBJECT, null);
            }

            theState.enteringNewElement(null, theName);
            parseAlternates(theAlternateVal, theState, theAlternateName, theAlternateName);
            JsonLikeObject nextObject = theJsonVal.getAsObject();
            boolean preResource = false;
            if (theState.isPreResource()) {
                JsonLikeValue resType = nextObject.get("resourceType");
                if (resType == null || !resType.isString()) {
                    throw new DataFormatException("Missing required element 'resourceType' from JSON resource object, unable to parse");
                }
                theState.enteringNewElement(null, resType.getAsString());
                preResource = true;
            }
            parseChildren(nextObject, theState);
            if (preResource) {
                theState.endingElement();
            }
            theState.endingElement();
        } else if (theJsonVal.isNull()) {
            theState.enteringNewElement(null, theName);
            parseAlternates(theAlternateVal, theState, theAlternateName, theAlternateName);
            theState.endingElement();
        } else {
            // must be a SCALAR
            theState.enteringNewElement(null, theName);
            theState.attributeValue("value", theJsonVal.getAsString());
            parseAlternates(theAlternateVal, theState, theAlternateName, theAlternateName);
            theState.endingElement();
        }
    }

    private void parseExtension(ParserState4BundleOfBundles<?> theState, JsonLikeArray theValues, boolean theIsModifier) {
        int allUnderscoreNames = 0;
        int handledUnderscoreNames = 0;
        for (int i = 0; i < theValues.size(); i++) {
            JsonLikeObject nextExtObj = JsonLikeValue.asObject(theValues.get(i));
            JsonLikeValue jsonElement = nextExtObj.get("url");
            String url;
            if (null == jsonElement || !(jsonElement.isScalar())) {
                String parentElementName;
                if (theIsModifier) {
                    parentElementName = "modifierExtension";
                } else {
                    parentElementName = "extension";
                }
                getErrorHandler().missingRequiredElement(new ParseLocation().setParentElementName(parentElementName), "url");
                url = null;
            } else {
                url = getExtensionUrl(jsonElement.getAsString());
            }
            theState.enteringNewElementExtension(null, url, theIsModifier, getServerBaseUrl());
            for (String next : nextExtObj.keySet()) {
                if ("url".equals(next)) {
                    continue;
                } else if ("extension".equals(next)) {
                    JsonLikeArray jsonVal = JsonLikeValue.asArray(nextExtObj.get(next));
                    parseExtension(theState, jsonVal, false);
                } else if ("modifierExtension".equals(next)) {
                    JsonLikeArray jsonVal = JsonLikeValue.asArray(nextExtObj.get(next));
                    parseExtension(theState, jsonVal, true);
                } else if (next.charAt(0) == '_') {
                    allUnderscoreNames++;
                    continue;
                } else {
                    JsonLikeValue jsonVal = nextExtObj.get(next);
                    String alternateName = '_' + next;
                    JsonLikeValue alternateVal = nextExtObj.get(alternateName);
                    if (alternateVal != null) {
                        handledUnderscoreNames++;
                    }
                    parseChildren(theState, next, jsonVal, alternateVal, alternateName, false);
                }
            }

            /*
             * This happens if an element has an extension but no actual value. I.e.
             * if a resource has a "_status" element but no corresponding "status"
             * element. This could be used to handle a null value with an extension
             * for example.
             */
            if (allUnderscoreNames > handledUnderscoreNames) {
                for (String alternateName : nextExtObj.keySet()) {
                    if (alternateName.startsWith("_") && alternateName.length() > 1) {
                        JsonLikeValue nextValue = nextExtObj.get(alternateName);
                        if (nextValue != null) {
                            if (nextValue.isObject()) {
                                String nextName = alternateName.substring(1);
                                if (nextExtObj.get(nextName) == null) {
                                    theState.enteringNewElement(null, nextName);
                                    parseAlternates(nextValue, theState, alternateName, alternateName);
                                    theState.endingElement();
                                }
                            } else {
                                getErrorHandler().incorrectJsonType(null, alternateName, ValueType.OBJECT, null, nextValue.getJsonType(), null);
                            }
                        }
                    }
                }
            }
            theState.endingElement();
        }
    }

    private void parseFhirComments(JsonLikeValue theObject, ParserState4BundleOfBundles<?> theState) {
        if (theObject.isArray()) {
            JsonLikeArray comments = theObject.getAsArray();
            for (int i = 0; i < comments.size(); i++) {
                JsonLikeValue nextComment = comments.get(i);
                if (nextComment.isString()) {
                    String commentText = nextComment.getAsString();
                    if (commentText != null) {
                        theState.commentPre(commentText);
                    }
                }
            }
        }
    }

    @Override
    public <T extends IBaseResource> T parseResource(Class<T> theResourceType, JsonLikeStructure theJsonLikeStructure) throws DataFormatException {
        /*
         * We do this so that the context can verify that the structure is for
         * the correct FHIR version
         */
        if (theResourceType != null) {
            myContext.getResourceDefinition(theResourceType);
        }

        // Actually do the parse
        T retVal = doParseResource(theResourceType, theJsonLikeStructure);

        RuntimeResourceDefinition def = myContext.getResourceDefinition(retVal);
        if ("Bundle".equals(def.getName())) {
            BaseRuntimeChildDefinition entryChild = def.getChildByName("entry");
            BaseRuntimeElementCompositeDefinition<?> entryDef = (BaseRuntimeElementCompositeDefinition<?>) entryChild.getChildByName("entry");
            List<IBase> entries = entryChild.getAccessor().getValues(retVal);
            if (entries != null) {
                for (IBase nextEntry : entries) {

                    /**
                     * If Bundle.entry.fullUrl is populated, set the resource ID to that
                     */
                    // TODO: should emit a warning and maybe notify the error handler if the resource ID doesn't match the
                    // fullUrl idPart
                    BaseRuntimeChildDefinition fullUrlChild = entryDef.getChildByName("fullUrl");
                    if (fullUrlChild == null) {
                        continue; // TODO: remove this once the data model in tinder plugin catches up to 1.2
                    }
                    List<IBase> fullUrl = fullUrlChild.getAccessor().getValues(nextEntry);
                    if (fullUrl != null && !fullUrl.isEmpty()) {
                        IPrimitiveType<?> value = (IPrimitiveType<?>) fullUrl.get(0);
                        if (value.isEmpty() == false) {
                            List<IBase> entryResources = entryDef.getChildByName("resource").getAccessor().getValues(nextEntry);
                            if (entryResources != null && entryResources.size() > 0) {
                                IBaseResource res = (IBaseResource) entryResources.get(0);
                                String versionId = res.getIdElement().getVersionIdPart();
                                res.setId(value.getValueAsString());
                                if (isNotBlank(versionId) && res.getIdElement().hasVersionIdPart() == false) {
                                    res.setId(res.getIdElement().withVersion(versionId));
                                }
                            }
                        }
                    }
                }
            }
        }
        return retVal;
    }
}
