package edu.gatech.chai.VRDR.messaging;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.messaging.util.DocumentBundler;
import edu.gatech.chai.VRDR.model.DeathCertificateDocument;
import edu.gatech.chai.VRDR.model.DemographicCodedContentBundle;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.MessageHeader;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.ResourceType;

@ResourceDef(name = "DeathRecordSubmissionMessage", profile = "http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-DeathRecordSubmissionMessage")
public class DeathRecordSubmissionMessage extends BaseMessage implements DocumentBundler<DeathCertificateDocument> {

    private static final long serialVersionUID = 1L;

    public static final String MESSAGE_TYPE = "http://nchs.cdc.gov/vrdr_submission";

    protected DeathCertificateDocument deathRecord;

    public static DeathRecordSubmissionMessage fromDeathRecord(DeathCertificateDocument deathRecord) {
        DeathRecordSubmissionMessage submission = new DeathRecordSubmissionMessage();
        submission.setDeathRecord(deathRecord);
        submission.extractBusinessIdentifiers(deathRecord);
        return submission;
    }

    public DeathRecordSubmissionMessage() {
        super(MESSAGE_TYPE);
    }

    public DeathRecordSubmissionMessage(Bundle messageBundle) {
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
        if (deathRecord != null) {
            addBundleEntryAndLinkToHeader(this, messageHeader, deathRecord);
        }
    }


    public String getDeathLocationJurisdiction() {
        return getDeathLocationJurisdiction(deathRecord);
    }

}
