package edu.gatech.chai.VRDR.messaging;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.IntegerType;
import org.hl7.fhir.r4.model.UnsignedIntType;
import org.hl7.fhir.r4.model.MessageHeader;
import org.hl7.fhir.r4.model.ResourceType;

@ResourceDef(name = "AcknowledgementMessage", profile = "http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-AcknowledgementMessage")
public class AcknowledgementMessage extends BaseMessage {

    public static final String MESSAGE_TYPE = "http://nchs.cdc.gov/vrdr_acknowledgement";

    public AcknowledgementMessage() {
        super(MESSAGE_TYPE);
    }

    public AcknowledgementMessage(Bundle messageBundle) {
        super(messageBundle);
    }

    public AcknowledgementMessage(BaseMessage messageToAck) {
        this(messageToAck == null ? null : messageToAck.getMessageHeaderId(),
                messageToAck == null ? null : messageToAck.getMessageSource(),
                messageToAck == null ? null : messageToAck.getMessageDestination());
        setCertNo(messageToAck == null ? null : messageToAck.getCertNo());
        setStateAuxiliaryId(messageToAck == null ? null : messageToAck.getStateAuxiliaryId());
        setDeathYear(messageToAck == null ? null : messageToAck.getDeathYear());
        setJurisdictionId(messageToAck == null ? null : messageToAck.getJurisdictionId());
        if (messageToAck instanceof DeathRecordVoidMessage) {
            DeathRecordVoidMessage voidMessageToAck = (DeathRecordVoidMessage) messageToAck;
            UnsignedIntType blockCount = voidMessageToAck != null ? voidMessageToAck.getBlockCount() : null;
            if(blockCount != null)
                setBlockCount(blockCount.getValue());
        }
    }

    public AcknowledgementMessage(String messageId, String destination, String source) {
        super(MESSAGE_TYPE);
        messageHeader.getSource().setEndpoint(source);
        setMessageDestination(destination);
        MessageHeader.MessageHeaderResponseComponent resp = new MessageHeader.MessageHeaderResponseComponent();
        // strip off if messageId of header.id has prefix 'urn:uuid:'
        messageId = messageId != null && messageId.startsWith("urn:uuid:") ?  messageId.substring(9) : messageId;
        resp.setIdentifier(messageId);
        resp.setCode(MessageHeader.ResponseType.OK);
        messageHeader.setResponse(resp);
    }

    public AcknowledgementMessage(String messageId, String destination) {
        this(messageId, destination, DeathRecordSubmissionMessage.MESSAGE_TYPE);
    }

    public String getIGMessageType() {
        return MESSAGE_TYPE;
    }

    public String getAckedMessageId() {
        return messageHeader != null && messageHeader.getResponse() != null ? messageHeader.getResponse().getIdentifier() : null;
    }

    public void setAckedMessageId(String value) {
        if (messageHeader.getResponse() == null) {
            messageHeader.setResponse(new MessageHeader.MessageHeaderResponseComponent());
            messageHeader.getResponse().setCode(MessageHeader.ResponseType.OK);
        }
        messageHeader.getResponse().setIdentifier(value);
    }

    public UnsignedIntType getBlockCount() {
        if (messageParameters == null) {
            return null;
        }
        if (!messageParameters.hasParameter("block_count")) {
            return null;
        }
        if (!(messageParameters.getParameter("block_count") instanceof IntegerType)) {
            return null;
        }
        UnsignedIntType blockCountType = (UnsignedIntType) messageParameters.getParameter("block_count");
        if (blockCountType == null) {
            return null;
        }
        return blockCountType;
    }

    public void setBlockCount(Integer value) {
        if (value != null && value > 0) {
            messageParameters.setParameter("block_count", new UnsignedIntType(value));
        }
    }
}
