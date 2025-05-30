package edu.gatech.chai.VRDR.messaging;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.DeathCertificateDocument;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.MessageHeader;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.IntegerType;
import org.hl7.fhir.r4.model.UnsignedIntType;

@ResourceDef(name = "DeathRecordVoidMessage", profile = "http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-DeathRecordVoidMessage")
public class DeathRecordVoidMessage extends BaseMessage {

    public static final String MESSAGE_TYPE = "http://nchs.cdc.gov/vrdr_submission_void";

    public DeathRecordVoidMessage() {
        super(MESSAGE_TYPE);
    }

    public DeathRecordVoidMessage(DeathCertificateDocument deathRecord) {
        super(MESSAGE_TYPE);
        extractBusinessIdentifiers(deathRecord);
    }

    public DeathRecordVoidMessage(Bundle messageBundle) {
        super(messageBundle);
    }

    public DeathRecordVoidMessage(BaseMessage messageToVoid) {
        this(messageToVoid == null ? null : messageToVoid.getId(),
                messageToVoid == null ? null : messageToVoid.getMessageDestination(),
                messageToVoid == null ? null : messageToVoid.getMessageSource());
        setCertNo(messageToVoid == null ? null : messageToVoid.getCertNo());
        setStateAuxiliaryId(messageToVoid == null ? null : messageToVoid.getStateAuxiliaryId());
        setDeathYear(messageToVoid == null ? null : messageToVoid.getDeathYear());
        setJurisdictionId(messageToVoid == null ? null : messageToVoid.getJurisdictionId());
    }

    public DeathRecordVoidMessage(String messageId, String source, String destination) {
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
            return new UnsignedIntType(1);
        }
        return blockCountType;
    }

    public void setBlockCount(Integer value) {
        if (value != null && value > 0) {
            messageParameters.setParameter("block_count", new UnsignedIntType(value));
        }
    }

    @Override
    public ResourceType getResourceType() {
        return ResourceType.Bundle;
    }

}
