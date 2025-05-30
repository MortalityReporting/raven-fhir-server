package edu.gatech.chai.VRDR.messaging;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import org.hl7.fhir.r4.model.*;

@ResourceDef(name = "StatusMessage", profile = "http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-StatusMessage")
public class StatusMessage extends BaseMessage {

    public static final String MESSAGE_TYPE = "http://nchs.cdc.gov/vrdr_status";

    public StatusMessage() {
        super(MESSAGE_TYPE);
    }

    public StatusMessage(Bundle messageBundle) {
        super(messageBundle);
    }

    public StatusMessage(BaseMessage messageToStatus) {
        this(messageToStatus == null ? null : messageToStatus.getMessageHeaderId(),
                messageToStatus == null ? null : messageToStatus.getMessageSource(),
                messageToStatus == null ? null : messageToStatus.getMessageDestination());
        setCertNo(messageToStatus == null ? null : messageToStatus.getCertNo());
        setStateAuxiliaryId(messageToStatus == null ? null : messageToStatus.getStateAuxiliaryId());
        setDeathYear(messageToStatus == null ? null : messageToStatus.getDeathYear());
        setJurisdictionId(messageToStatus == null ? null : messageToStatus.getJurisdictionId());
    }

    public StatusMessage(BaseMessage messageToStatus, String status) {
        this(messageToStatus);
        setStatus(status);
    }

    public StatusMessage(String messageId, String destination, String source) {
        super(MESSAGE_TYPE);
        messageHeader.getSource().setEndpoint(source);
        setMessageDestination(destination);
        MessageHeader.MessageHeaderResponseComponent resp = new MessageHeader.MessageHeaderResponseComponent();
        resp.setIdentifier(messageId);
        resp.setCode(MessageHeader.ResponseType.OK);
        messageHeader.setResponse(resp);
    }

    public StatusMessage(String messageId, String destination) {
        this(messageId, destination, DeathRecordSubmissionMessage.MESSAGE_TYPE);
    }

    public String getIGMessageType() {
        return MESSAGE_TYPE;
    }

    public String getStatusedMessageId() {
        return messageHeader != null && messageHeader.getResponse() != null ? messageHeader.getResponse().getIdentifier() : null;
    }

    public void setStatusedMessageId(String value) {
        if (messageHeader.getResponse() == null) {
            messageHeader.setResponse(new MessageHeader.MessageHeaderResponseComponent());
            messageHeader.getResponse().setCode(MessageHeader.ResponseType.OK);
        }
        messageHeader.getResponse().setIdentifier(value);
    }

    public String getStatus() {
        Type type = messageParameters.getParameter("status");
        if (type instanceof StringType) {
            return ((StringType) type).getValue();
        }
        return null;
    }

    public void setStatus(String status) {
        setSingleStringValue("status", status);
    }

}
