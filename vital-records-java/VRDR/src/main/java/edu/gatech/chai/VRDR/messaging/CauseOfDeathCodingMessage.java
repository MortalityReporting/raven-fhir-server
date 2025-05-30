package edu.gatech.chai.VRDR.messaging;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.messaging.util.DocumentBundler;
import edu.gatech.chai.VRDR.model.CauseOfDeathCodedContentBundle;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.MessageHeader;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.ResourceType;

@ResourceDef(name = "CauseOfDeathCodingMessage", profile = "http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-CauseOfDeathCodingMessage")
public class CauseOfDeathCodingMessage extends BaseMessage implements DocumentBundler<CauseOfDeathCodedContentBundle> {

    // from the VRDR Messaging IG http://build.fhir.org/ig/nightingaleproject/vital_records_fhir_messaging_ig/branches/main/StructureDefinition-VRM-CauseOfDeathCodingMessage.html
    public static final String MESSAGE_TYPE = "http://nchs.cdc.gov/vrdr_causeofdeath_coding";

    private CauseOfDeathCodedContentBundle causeOfDeathCodedContentBundle;

    public CauseOfDeathCodingMessage() {
        super(MESSAGE_TYPE);
    }

    public CauseOfDeathCodingMessage(Bundle messageBundle) {
        super(messageBundle);
        setDocumentBundleFromMessageBundle(CauseOfDeathCodedContentBundle.class, this, messageBundle);
    }

    public CauseOfDeathCodedContentBundle getDocumentBundle() {
        return getCauseOfDeathCodedContentBundle();
    }

    public void setDocumentBundle(CauseOfDeathCodedContentBundle causeOfDeathCodedContentBundle) {
        setCauseOfDeathCodedContentBundle(causeOfDeathCodedContentBundle);
    }

    public CauseOfDeathCodedContentBundle getCauseOfDeathCodedContentBundle() {
        return causeOfDeathCodedContentBundle;
    }

    public void setCauseOfDeathCodedContentBundle(CauseOfDeathCodedContentBundle causeOfDeathCodedContentBundle) {
        this.causeOfDeathCodedContentBundle = causeOfDeathCodedContentBundle;
        addBundleEntryAndLinkToHeader(this, messageHeader, causeOfDeathCodedContentBundle);
    }

    public CauseOfDeathCodingMessage(BaseMessage messageToCode) {
        this(messageToCode == null ? null : messageToCode.getMessageHeaderId(),
                messageToCode == null ? null : messageToCode.getMessageDestination(),
                messageToCode == null ? null : messageToCode.getMessageSource());
        setCertNo(messageToCode == null ? null : messageToCode.getCertNo());
        setStateAuxiliaryId(messageToCode == null ? null : messageToCode.getStateAuxiliaryId());
        setDeathYear(messageToCode == null ? null : messageToCode.getDeathYear());
        setJurisdictionId(messageToCode == null ? null : messageToCode.getJurisdictionId());
    }

    public CauseOfDeathCodingMessage(String messageId, String source, String destination) {
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
