package edu.gatech.chai.VRDR.messaging;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.messaging.util.DocumentBundler;
import edu.gatech.chai.VRDR.model.DeathCertificateDocument;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.ResourceType;

@ResourceDef(name = "DeathRecordUpdateMessage", profile = "http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-DeathRecordUpdateMessage")
public class DeathRecordUpdateMessage extends BaseMessage implements DocumentBundler<DeathCertificateDocument> {

    public static final String MESSAGE_TYPE = "http://nchs.cdc.gov/vrdr_submission_update";

    protected DeathCertificateDocument deathRecord;

    public static DeathRecordUpdateMessage fromDeathRecord(DeathCertificateDocument deathRecord) {
        DeathRecordUpdateMessage submission = new DeathRecordUpdateMessage();
        submission.deathRecord = deathRecord;
        submission.extractBusinessIdentifiers(deathRecord);
        return submission;
    }

    public DeathRecordUpdateMessage() {
        super(MESSAGE_TYPE);
    }

    public DeathRecordUpdateMessage(Bundle messageBundle) {
        super(messageBundle);
        setDocumentBundleFromMessageBundle(DeathCertificateDocument.class, this, messageBundle);
    }


    public DeathCertificateDocument getDocumentBundle() {
        return getDeathRecord();
    }

    public void setDocumentBundle(DeathCertificateDocument deathRecord) {
        setDeathRecord(deathRecord);
    }

    public String getIGMessageType() {
        return MESSAGE_TYPE;
    }

    public DeathCertificateDocument getDeathRecord() {
        return deathRecord;
    }

    public void setDeathRecord(DeathCertificateDocument deathRecord) {
        this.deathRecord = deathRecord;
        extractBusinessIdentifiers(deathRecord);
        if (deathRecord != null) {
            addBundleEntryAndLinkToHeader(this, messageHeader, deathRecord);
        }
    }

    public String getDeathLocationJurisdiction() {
        return getDeathLocationJurisdiction(deathRecord);
    }

}
