package edu.gatech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.UnsignedIntType;

import edu.gatech.chai.VRCL.model.CodedRaceAndEthnicity;
import edu.gatech.chai.VRCL.model.InputRaceAndEthnicity;
import edu.gatech.chai.VRCL.model.util.CodedRaceAndEthnicityUtil;
import edu.gatech.chai.VRDR.context.VRDRFhirContext;
import edu.gatech.chai.VRDR.messaging.AcknowledgementMessage;
import edu.gatech.chai.VRDR.messaging.BaseMessage;
import edu.gatech.chai.VRDR.messaging.CauseOfDeathCodingMessage;
import edu.gatech.chai.VRDR.messaging.CauseOfDeathCodingUpdateMessage;
import edu.gatech.chai.VRDR.messaging.DeathRecordAliasMessage;
import edu.gatech.chai.VRDR.messaging.DeathRecordSubmissionMessage;
import edu.gatech.chai.VRDR.messaging.DeathRecordUpdateMessage;
import edu.gatech.chai.VRDR.messaging.DeathRecordVoidMessage;
import edu.gatech.chai.VRDR.messaging.DemographicsCodingMessage;
import edu.gatech.chai.VRDR.messaging.DemographicsCodingUpdateMessage;
import edu.gatech.chai.VRDR.messaging.ExtractionErrorMessage;
import edu.gatech.chai.VRDR.messaging.StatusMessage;
import edu.gatech.chai.VRDR.messaging.util.MessageParseException;
import edu.gatech.chai.VRDR.model.CauseOfDeathCodedContentBundle;
import edu.gatech.chai.VRDR.model.CodingStatusValues;
import edu.gatech.chai.VRDR.model.DeathCertificate;
import edu.gatech.chai.VRDR.model.DeathCertificateDocument;
import edu.gatech.chai.VRDR.model.DemographicCodedContentBundle;
import edu.gatech.chai.VRDR.model.EntityAxisCauseOfDeath;
import edu.gatech.chai.VRDR.model.InjuryIncident;
import edu.gatech.chai.VRDR.model.RecordAxisCauseOfDeath;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.DeathDateUtil;
import edu.gatech.chai.VRDR.model.util.MannerOfDeathUtil;
import edu.gatech.chai.VRDR.model.util.UploadUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class MessagingTest extends TestCase {

    VRDRFhirContext ctx;

    private final DeathCertificateDocument deathRecord1FromXML;
    private final DeathCertificateDocument deathRecord1;
    private final DeathCertificateDocument deathRecordNoIdentifiers;
    private final DeathCertificateDocument deathRecordPronouncementTimeOnly;

    public MessagingTest(String testName) {
        super(testName);
        ctx = new VRDRFhirContext();
        deathRecord1FromXML = BaseMessage.parseXMLFile(DeathCertificateDocument.class, ctx, "src/test/resources/xml/DeathRecord1.xml");
        deathRecord1 = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "src/test/resources/json/DeathRecord1.json");
        deathRecordNoIdentifiers = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "src/test/resources/json/DeathRecordNoIdentifiers.json");
        deathRecordPronouncementTimeOnly = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "src/test/resources/json/DeathRecordPronouncementTimeOnly.json");
    }

    public static Test suite() {
        return new TestSuite(MessagingTest.class);
    }

    public void testCreateSubmission() {
        DeathRecordSubmissionMessage submission = new DeathRecordSubmissionMessage();
        assertEquals("http://nchs.cdc.gov/vrdr_submission", submission.getMessageType());
        assertNull(submission.getDeathRecord());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", submission.getMessageDestination());
        assertNotNull(submission.getTimestamp());
        assertNull(submission.getMessageSource());
        assertNotNull(submission.getId());
        assertNull(submission.getCertNo());
        assertNull(submission.getStateAuxiliaryId());
        assertNull(submission.getNCHSIdentifier());
    }


    public void testCreateSubmissionFromDeathRecordXML() {
        DeathRecordSubmissionMessage submission = DeathRecordSubmissionMessage.fromDeathRecord(deathRecord1FromXML);
        assertNotNull(submission.getDeathRecord());
        assertEquals("2019-02-20T16:48:06-05:00", submission.getDeathRecord().getDateOfDeathPronouncement());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", submission.getMessageType());
        assertEquals(10, 10);
        assertEquals(182, submission.getCertNo().intValue());
        assertEquals(2019, submission.getDeathYear().intValue());
        assertEquals("000000000042", submission.getStateAuxiliaryId());
        assertEquals("2019YC000182", submission.getNCHSIdentifier());
    }

    public void testCreateSubmissionFromDeathRecordJSON() {
        DeathRecordSubmissionMessage submission = DeathRecordSubmissionMessage.fromDeathRecord(deathRecord1);
        assertNotNull(submission.getDeathRecord());
        assertEquals("2018-02-20T16:48:06-05:00", submission.getDeathRecord().getDateOfDeathPronouncement());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", submission.getMessageType());
        assertEquals(182, submission.getCertNo().intValue());
        assertEquals(2019, submission.getDeathYear().intValue());
        assertEquals("000000000042", submission.getStateAuxiliaryId());
        assertEquals("2019YC000182", submission.getNCHSIdentifier());
    }

    public void testCreateSubmissionFromDeathRecordNull() {
        DeathRecordSubmissionMessage submission = DeathRecordSubmissionMessage.fromDeathRecord(null);
        assertNull(submission.getDeathRecord());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", submission.getMessageType());
        assertNull(submission.getCertNo());
        assertNull(submission.getStateAuxiliaryId());
        assertNull(submission.getNCHSIdentifier());
    }

    public void testCreateSubmissionFromDeathRecordNoIdentifiers() {
        DeathRecordSubmissionMessage submission = DeathRecordSubmissionMessage.fromDeathRecord(deathRecordNoIdentifiers);
        assertNotNull(submission.getDeathRecord());
        assertEquals("2019-02-20T16:48:06-05:00", submission.getDeathRecord().getDateOfDeathPronouncement());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", submission.getMessageType());
        assertNull(submission.getCertNo());
        assertNull(submission.getStateAuxiliaryId());
        assertNull(submission.getNCHSIdentifier());
    }

    public void testCreateSubmissionFromDeathRecordPronouncementTimeOnly() {
        DeathRecordSubmissionMessage submission = DeathRecordSubmissionMessage.fromDeathRecord(deathRecordPronouncementTimeOnly);
        assertNotNull(submission.getDeathRecord());
        assertEquals("16:48:06", submission.getDeathRecord().getDateOfDeathPronouncement());
    }

    public void testCreateSubmissionFromJson() {
        DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "src/test/resources/json/DeathRecordSubmissionMessage.json");
        assertEquals("2020-11-13T16:39:40-05:00", submission.getDeathRecord().getDateOfDeathPronouncement());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", submission.getMessageType());
        assertEquals("2018NY123456", submission.getNCHSIdentifier());
        assertEquals(123456, submission.getCertNo().intValue());
        assertEquals(2018, submission.getDeathYear().intValue());
        assertEquals("abcdef10", submission.getStateAuxiliaryId());
        assertEquals(submission.getJurisdictionId(), submission.getDeathLocationJurisdiction());
    }

    public void testCreateSubmissionFromJsonNoIdentifiers() {
        DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "src/test/resources/json/DeathRecordSubmissionNoIdentifiers.json");
        assertEquals("2018-02-20T16:48:06-05:00", submission.getDeathRecord().getDateOfDeathPronouncement());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", submission.getMessageType());
        assertNull(submission.getCertNo());
        assertNull(submission.getStateAuxiliaryId());
        assertNull(submission.getNCHSIdentifier());
    }

    public void testCreateSubmissionFromJsonEmptySubmission() {
        try {
            DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "src/test/resources/json/EmptySubmission.json");
            fail("Expected MessageParseException");
        } catch (MessageParseException ex) {
            assertEquals("Error processing entry in the message: Failed to find a Bundle Entry containing a Resource of type edu.gatech.chai.VRDR.model.DeathCertificateDocument", ex.getMessage());
            ExtractionErrorMessage errMsg = ex.createExtractionErrorMessage();
            assertEquals("http://nchs.cdc.gov/vrdr_submission", errMsg.getMessageSource());
            assertEquals("nightingale", errMsg.getMessageDestination());
            assertEquals("254b5d1e-6620-4b49-8d25-4f38f4562016", errMsg.getFailedMessageId());
            assertEquals("2018MA000001", errMsg.getNCHSIdentifier());
            assertEquals(1, errMsg.getCertNo().intValue());
            assertEquals(2018, errMsg.getDeathYear().intValue());
            assertEquals("42", errMsg.getStateAuxiliaryId());
        }
    }

    public void testCreateSubmissionFromJsonWrongStructure() {
        try {
            AcknowledgementMessage acknowledgementMessage = BaseMessage.parseJsonFile(AcknowledgementMessage.class, ctx, "src/test/resources/json/DeathRecordSubmissionMessage.json");
            fail("Expected MessageParseException");
        } catch (MessageParseException ex) {
            assertEquals("Message event uri type " + DeathRecordSubmissionMessage.MESSAGE_TYPE + " does not match the expected message type " + AcknowledgementMessage.MESSAGE_TYPE, ex.getMessage());
        }
    }

    public void testMissingOrUnknownTimeOfDeath() {
        try {
            // test full death date and death time as baseline
            DeathCertificateDocument deathCertificateDocument = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "src/test/resources/json/DeathRecord1.json");
            assertEquals("DateTimeType[2019-02-19T16:48:06-05:00]",deathCertificateDocument.getDeathDate().get(0).getValue().dateTimeValue().toString());

            // test death date with missing death time
            deathCertificateDocument = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "src/test/resources/json/DeathRecord1MissingDeathTime.json");
            assertEquals("DateTimeType[2019-02-19]",deathCertificateDocument.getDeathDate().get(0).getValue().toString());
            assertEquals("null", deathCertificateDocument.getExtraDateTimeType4Death().getMissingOrUnknownDeathTime());

            // test "unknown" death date and death time
            deathCertificateDocument = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "src/test/resources/json/UnknownDateOfDeath.json");
            assertEquals("DateTimeType[null]", deathCertificateDocument.getDeathDate().get(0).getValue().toString());
            assertEquals("unknown", deathCertificateDocument.getExtraDateTimeType4Death().getMissingOrUnknownDeathTime());

        } catch (MessageParseException ex) {
            assertEquals("Message event uri type " + DeathRecordSubmissionMessage.MESSAGE_TYPE + " does not match the expected message type " + AcknowledgementMessage.MESSAGE_TYPE, ex.getMessage());
        }
    }

    public void testMissingOrUnknownTimeOfInjury() {
        try {
            // test full injury date and injury time as baseline
            DeathCertificateDocument deathCertificateDocument = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "src/test/resources/json/DeathRecord1.json");
            InjuryIncident injuryIncident = deathCertificateDocument.getInjuryIncident().get(0);
            assertEquals("DateTimeType[2018-02-19T16:48:06-05:00]",deathCertificateDocument.getInjuryIncident().get(0).getEffectiveDateTimeType().dateTimeValue().toString());

            // test injury date with missing injury time
            deathCertificateDocument = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "src/test/resources/json/DeathRecord1MissingInjuryTime.json");
            assertEquals("DateTimeType[2018-02-19]",deathCertificateDocument.getInjuryIncident().get(0).getEffectiveDateTimeType().toString());
            assertEquals("null", deathCertificateDocument.getExtraDateTimeType4Injury().getMissingOrUnknownDeathTime());

            // test "unknown" injury date and injury time
            deathCertificateDocument = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "src/test/resources/json/UnknownDateOfInjury.json");
            assertEquals("DateTimeType[null]", deathCertificateDocument.getInjuryIncident().get(0).getEffectiveDateTimeType().toString());
            assertEquals("unknown", deathCertificateDocument.getExtraDateTimeType4Injury().getMissingOrUnknownDeathTime());

        } catch (MessageParseException ex) {
            assertEquals("Message event uri type " + DeathRecordSubmissionMessage.MESSAGE_TYPE + " does not match the expected message type " + AcknowledgementMessage.MESSAGE_TYPE, ex.getMessage());
        }
    }

    public void testCreateSubmissionFromBundle() {
        DeathRecordSubmissionMessage submission = new DeathRecordSubmissionMessage();
        submission.setDeathRecord(new DeathCertificateDocument());
        submission.setCertNo(42);
        submission.setStateAuxiliaryId("identifier");
        final String submissionBundleStr = submission.toJson(ctx);

        DeathRecordSubmissionMessage parsed = BaseMessage.parseJson(DeathRecordSubmissionMessage.class, ctx, submissionBundleStr);
        final String parsedBundleStr = parsed.toJson(ctx);
        assertEquals(submissionBundleStr, parsedBundleStr);
        assertEquals(submission.getMessageType(), parsed.getMessageType());
        assertEquals(submission.getCertNo(), parsed.getCertNo());
        assertEquals(submission.getStateAuxiliaryId(), parsed.getStateAuxiliaryId());
        assertEquals(submission.getNCHSIdentifier(), parsed.getNCHSIdentifier());
    }

    public void testCreateUpdate() {
        DeathRecordUpdateMessage submission = new DeathRecordUpdateMessage();
        assertEquals("http://nchs.cdc.gov/vrdr_submission_update", submission.getMessageType());
        assertNull(submission.getDeathRecord());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", submission.getMessageDestination());
        assertNotNull(submission.getTimestamp());
        assertNull(submission.getMessageSource());
        assertNotNull(submission.getId());
        assertNull(submission.getCertNo());
        assertNull(submission.getStateAuxiliaryId());
        assertNull(submission.getNCHSIdentifier());
    }

    public void testCreateUpdateFromDeathRecord() {
        DeathRecordUpdateMessage update = DeathRecordUpdateMessage.fromDeathRecord(deathRecord1FromXML);
        assertNotNull(update.getDeathRecord());
        assertEquals("2019-02-20T16:48:06-05:00", update.getDeathRecord().getDateOfDeathPronouncement());
        assertEquals("http://nchs.cdc.gov/vrdr_submission_update", update.getMessageType());
        assertEquals(182, update.getCertNo().intValue());
        assertEquals(2019, update.getDeathYear().intValue());
        assertEquals("000000000042", update.getStateAuxiliaryId());
        assertEquals("2019YC000182", update.getNCHSIdentifier());
    }

    public void testCreateUpdateFromDeathRecordNoIdentifiers() {
        DeathRecordUpdateMessage update = DeathRecordUpdateMessage.fromDeathRecord(deathRecordNoIdentifiers); // no ids in this death record (except jurisdiction id which is required)
        assertNotNull(update.getDeathRecord());
        assertEquals("2019-02-20T16:48:06-05:00", update.getDeathRecord().getDateOfDeathPronouncement());
        assertEquals("http://nchs.cdc.gov/vrdr_submission_update", update.getMessageType());
        assertNull(update.getCertNo());
        assertNull(update.getStateAuxiliaryId());
        assertNull(update.getNCHSIdentifier());
    }

    public void testCreateUpdateFromJSON() {
        DeathRecordUpdateMessage update = BaseMessage.parseJsonFile(DeathRecordUpdateMessage.class, ctx, "src/test/resources/json/DeathRecordUpdateMessage.json");
        assertEquals("2020-11-13T16:39:40-05:00", update.getDeathRecord().getDateOfDeathPronouncement());
        assertEquals("http://nchs.cdc.gov/vrdr_submission_update", update.getMessageType());
        assertEquals("2020NY000182", update.getNCHSIdentifier());
        assertEquals("NY", update.getJurisdictionId());
        assertEquals(182, update.getCertNo().intValue());
        assertEquals(2020, update.getDeathYear().intValue());
        assertEquals("000000000001", update.getStateAuxiliaryId());
    }

    public void testCreateUpdateFromJsonNoIdentifiers() {
        DeathRecordUpdateMessage update = BaseMessage.parseJsonFile(DeathRecordUpdateMessage.class, ctx, "src/test/resources/json/DeathRecordUpdateNoIdentifiers.json");
        assertEquals("2018-02-20T16:48:06-05:00", update.getDeathRecord().getDateOfDeathPronouncement());
        assertEquals("http://nchs.cdc.gov/vrdr_submission_update", update.getMessageType());
        assertNull(update.getCertNo());
        assertNull(update.getStateAuxiliaryId());
        assertNull(update.getNCHSIdentifier());
    }

    public void testCreateUpdateFromBundle() {
        DeathRecordUpdateMessage update = new DeathRecordUpdateMessage();
        update.setDeathRecord(new DeathCertificateDocument());
        update.setCertNo(42);
        update.setStateAuxiliaryId("identifier");
        String updateBundleString = update.toJson(ctx);

        DeathRecordUpdateMessage parsed = BaseMessage.parseJson(DeathRecordUpdateMessage.class, ctx, updateBundleString);
        assertEquals(update.getDeathRecord().toJson(ctx), parsed.getDeathRecord().toJson(ctx));
        assertEquals(update.getMessageType(), parsed.getMessageType());
        assertEquals(update.getCertNo(), parsed.getCertNo());
        assertEquals(update.getStateAuxiliaryId(), parsed.getStateAuxiliaryId());
        assertEquals(update.getNCHSIdentifier(), parsed.getNCHSIdentifier());
    }

    public void testCreateAckForMessage() {
        DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx,
                "src/test/resources/json/DeathRecordSubmissionMessage.json");
        AcknowledgementMessage ack = new AcknowledgementMessage(submission);
        assertEquals("http://nchs.cdc.gov/vrdr_acknowledgement", ack.getMessageType());
        assertEquals(submission.getMessageHeaderId(), ack.getAckedMessageId());
        assertEquals(submission.getMessageSource(), ack.getMessageDestination());
        assertEquals(submission.getMessageDestination(), ack.getMessageSource());
        assertEquals(submission.getStateAuxiliaryId(), ack.getStateAuxiliaryId());
        assertEquals(submission.getCertNo(), ack.getCertNo());
        assertEquals(submission.getNCHSIdentifier(), ack.getNCHSIdentifier());
    }

    public void testCreateAckForNullMessage() {
        AcknowledgementMessage ack = new AcknowledgementMessage(null);
        assertEquals("http://nchs.cdc.gov/vrdr_acknowledgement", ack.getMessageType());
        assertNull(ack.getAckedMessageId());
        assertNull(ack.getMessageDestination());
        assertNull(ack.getMessageSource());
        assertNull(ack.getCertNo());
        assertNull(ack.getStateAuxiliaryId());
        assertNull(ack.getNCHSIdentifier());
    }

    public void testCreateAckForMessageWithNoIdentifiers() {
        DeathRecordSubmissionMessage submission = new DeathRecordSubmissionMessage();
        AcknowledgementMessage ack = new AcknowledgementMessage(submission);
        assertEquals("http://nchs.cdc.gov/vrdr_acknowledgement", ack.getMessageType());
        assertEquals(submission.getMessageHeaderId(), ack.getAckedMessageId());
        assertEquals(submission.getMessageSource(), ack.getMessageDestination());
        assertEquals(submission.getMessageDestination(), ack.getMessageSource());
        assertEquals(submission.getStateAuxiliaryId(), ack.getStateAuxiliaryId());
        assertEquals(submission.getCertNo(), ack.getCertNo());
        assertEquals(submission.getNCHSIdentifier(), ack.getNCHSIdentifier());
    }

    public void testCreateCauseOfDeathCodingMessage() {
        DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx,
                "src/test/resources/json/DeathRecordSubmissionMessage.json");
        CauseOfDeathCodingMessage coding = new CauseOfDeathCodingMessage(submission);
        assertEquals("http://nchs.cdc.gov/vrdr_causeofdeath_coding", coding.getMessageType());
        assertEquals(submission.getMessageHeaderId(), coding.getCodedMessageId());
        assertEquals(submission.getMessageSource(), coding.getMessageDestination());
        assertEquals(submission.getMessageDestination(), coding.getMessageSource());
        assertEquals(submission.getStateAuxiliaryId(), coding.getStateAuxiliaryId());
        assertEquals(submission.getCertNo(), coding.getCertNo());
        assertEquals(submission.getNCHSIdentifier(), coding.getNCHSIdentifier());
    }

    public void testCreateCauseOfDeathCodingMessageWithNoIdentifiers() {
        CauseOfDeathCodingMessage coding = new CauseOfDeathCodingMessage(null);
        assertEquals("http://nchs.cdc.gov/vrdr_causeofdeath_coding", coding.getMessageType());
        assertNull(coding.getCodedMessageId());
        assertNull(coding.getMessageDestination());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", coding.getMessageSource());
        assertNull(coding.getCertNo());
        assertNull(coding.getStateAuxiliaryId());
        assertNull(coding.getNCHSIdentifier());
    }

    public void testCreateCauseOfDeathCodingMessageWithNoMessage() {
        DeathRecordSubmissionMessage submission = new DeathRecordSubmissionMessage();
        CauseOfDeathCodingMessage coding = new CauseOfDeathCodingMessage(submission);
        assertEquals("http://nchs.cdc.gov/vrdr_causeofdeath_coding", coding.getMessageType());
        assertEquals(submission.getMessageHeaderId(), coding.getCodedMessageId());
        assertEquals(submission.getMessageSource(), coding.getMessageDestination());
        assertEquals(submission.getMessageDestination(), coding.getMessageSource());
        assertEquals(submission.getStateAuxiliaryId(), coding.getStateAuxiliaryId());
        assertEquals(submission.getCertNo(), coding.getCertNo());
        assertEquals(submission.getNCHSIdentifier(), coding.getNCHSIdentifier());
    }

    public void testCreateDemographicsCodingMessage() {
        DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "src/test/resources/json/DeathRecordSubmissionMessage.json");
        DemographicsCodingMessage coding = new DemographicsCodingMessage(submission);
        assertEquals("http://nchs.cdc.gov/vrdr_demographics_coding", coding.getMessageType());
        assertEquals(submission.getMessageHeaderId(), coding.getCodedMessageId());
        assertEquals(submission.getMessageSource(), coding.getMessageDestination());
        assertEquals(submission.getMessageDestination(), coding.getMessageSource());
        assertEquals(submission.getStateAuxiliaryId(), coding.getStateAuxiliaryId());
        assertEquals(submission.getCertNo(), coding.getCertNo());
        assertEquals(submission.getNCHSIdentifier(), coding.getNCHSIdentifier());
    }

    public void testCreateDemographicsCodingMessageWithNoIdentifiers() {
        DemographicsCodingMessage coding = new DemographicsCodingMessage(null);
        assertEquals("http://nchs.cdc.gov/vrdr_demographics_coding", coding.getMessageType());
        assertNull(coding.getCodedMessageId());
        assertNull(coding.getMessageDestination());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", coding.getMessageSource());
        assertNull(coding.getCertNo());
        assertNull(coding.getStateAuxiliaryId());
        assertNull(coding.getNCHSIdentifier());
    }

    public void testCreateDemographicsCodingMessageWithNoMessage() {
        DeathRecordSubmissionMessage submission = new DeathRecordSubmissionMessage();
        DemographicsCodingMessage coding = new DemographicsCodingMessage(submission);
        assertEquals("http://nchs.cdc.gov/vrdr_demographics_coding", coding.getMessageType());
        assertEquals(submission.getMessageHeaderId(), coding.getCodedMessageId());
        assertEquals(submission.getMessageSource(), coding.getMessageDestination());
        assertEquals(submission.getMessageDestination(), coding.getMessageSource());
        assertEquals(submission.getStateAuxiliaryId(), coding.getStateAuxiliaryId());
        assertEquals(submission.getCertNo(), coding.getCertNo());
        assertEquals(submission.getNCHSIdentifier(), coding.getNCHSIdentifier());
    }

    public void testCreateAckFromJSON() {
        AcknowledgementMessage ack = BaseMessage.parseJsonFile(AcknowledgementMessage.class, ctx,
                "src/test/resources/json/AcknowledgementMessage.json");
        assertEquals("http://nchs.cdc.gov/vrdr_acknowledgement", ack.getMessageType());
        assertEquals("a9d66d2e-2480-4e8d-bab3-4e4c761da1b7", ack.getAckedMessageId());
        assertEquals("nightingale", ack.getMessageDestination());
        assertEquals("2018MA000001", ack.getNCHSIdentifier());
        assertEquals(1, ack.getCertNo().intValue());
        assertEquals(2018, ack.getDeathYear().intValue());
        assertEquals("42", ack.getStateAuxiliaryId());
    }

    public void testCreateAckFromXML() {
        AcknowledgementMessage ack = BaseMessage.parseXMLFile(AcknowledgementMessage.class, ctx, "src/test/resources/xml/AcknowledgementMessage.xml");
        assertEquals("http://nchs.cdc.gov/vrdr_acknowledgement", ack.getMessageType());
        assertEquals("a9d66d2e-2480-4e8d-bab3-4e4c761da1b7", ack.getAckedMessageId());
        assertEquals("nightingale", ack.getMessageDestination());
        assertEquals("2018MA000001", ack.getNCHSIdentifier());
        assertEquals(1, ack.getCertNo().intValue());
        assertEquals(2018, ack.getDeathYear().intValue());
        assertEquals("42", ack.getStateAuxiliaryId());
    }

    public void testCreateAckFromBundle() {
        AcknowledgementMessage ackBundle = BaseMessage.parseJsonFile(AcknowledgementMessage.class, ctx, "src/test/resources/json/AcknowledgementMessage.json");
        String bundleString = ackBundle.toJson(ctx, false);
        AcknowledgementMessage ack = BaseMessage.parseJson(AcknowledgementMessage.class, ctx, bundleString);
        assertEquals("a9d66d2e-2480-4e8d-bab3-4e4c761da1b7", ack.getAckedMessageId());
        assertEquals("nightingale", ack.getMessageDestination());
        assertEquals("2018MA000001", ack.getNCHSIdentifier());
        assertEquals(1, ack.getCertNo().intValue());
        assertEquals(2018, ack.getDeathYear().intValue());
        assertEquals("42", ack.getStateAuxiliaryId());
    }

    public void testCreateCauseOfDeathCodingResponseFromJSON() {
        CauseOfDeathCodingMessage message = BaseMessage.parseJsonFile(CauseOfDeathCodingMessage.class, ctx,
                "src/test/resources/json/CauseOfDeathCodingMessage.json");

        assertEquals(CauseOfDeathCodingMessage.MESSAGE_TYPE, message.getMessageType());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", message.getMessageDestination());
        assertEquals(100000, (long) message.getCertNo());
        assertEquals(2019, (long) message.getDeathYear());
        assertNull(message.getStateAuxiliaryId());
        assertEquals("2019AK100000", message.getNCHSIdentifier());

        CauseOfDeathCodedContentBundle bundle = message.getCauseOfDeathCodedContentBundle();
        assertEquals("I25.1", bundle.getAutomatedUnderlyingCauseOfDeathCodedValue());

        List<RecordAxisCauseOfDeath> recordAxis = bundle.getRecordAxisCauseOfDeath();
        assertEquals(3, recordAxis.size());
        RecordAxisCauseOfDeath recordAxis1 = recordAxis.get(0);
        RecordAxisCauseOfDeath recordAxis2 = recordAxis.get(1);
        RecordAxisCauseOfDeath recordAxis3 = recordAxis.get(2);
        assertEquals(1, recordAxis1.getPosition().intValue());
        assertEquals("I25.1", recordAxis1.getValueCodeAsString());
        assertEquals(2, recordAxis2.getPosition().intValue());
        assertEquals("I25.0", recordAxis2.getValueCodeAsString());
        assertEquals(3, recordAxis3.getPosition().intValue());
        assertEquals("I51.7", recordAxis3.getValueCodeAsString());

        List<EntityAxisCauseOfDeath> entityAxis = bundle.getEntityAxisCauseOfDeath();
        assertEquals(4, entityAxis.size());
        EntityAxisCauseOfDeath entityAxis1 = entityAxis.get(0);
        EntityAxisCauseOfDeath entityAxis2 = entityAxis.get(1);
        EntityAxisCauseOfDeath entityAxis3 = entityAxis.get(2);
        EntityAxisCauseOfDeath entityAxis4 = entityAxis.get(3);
        assertEquals(1, entityAxis1.getLineNumber().intValue());
        assertEquals(1, entityAxis1.getPosition().intValue());
        assertEquals("I25.9", entityAxis1.getValueCodeAsString());
        assertFalse(entityAxis1.isECodeIndicator());
        assertEquals(2, entityAxis2.getLineNumber().intValue());
        assertEquals(1, entityAxis2.getPosition().intValue());
        assertEquals("I25.1", entityAxis2.getValueCodeAsString());
        assertFalse(entityAxis2.isECodeIndicator());
        assertEquals(3, entityAxis3.getLineNumber().intValue());
        assertEquals(1, entityAxis3.getPosition().intValue());
        assertEquals("I25.0", entityAxis3.getValueCodeAsString());
        assertFalse(entityAxis3.isECodeIndicator());
        assertEquals(3, entityAxis4.getLineNumber().intValue());
        assertEquals(2, entityAxis4.getPosition().intValue());
        assertEquals("I51.7", entityAxis4.getValueCodeAsString());
        assertTrue(entityAxis4.isECodeIndicator());

        CodingStatusValues codingStatusValues = bundle.getCodingStatusValues();
        assertEquals(3, codingStatusValues.getCoderStatus().intValue());
        assertEquals("555", codingStatusValues.getShipmentNumber());
        assertEquals(2021, codingStatusValues.getReceiptYear().intValue());
        assertEquals(6, codingStatusValues.getReceiptMonth().intValue());
        assertEquals(1, codingStatusValues.getReceiptDay().intValue());
        assertTrue(MannerOfDeathUtil.VALUE_NATURAL.equalsDeep(bundle.getMannerOfDeath().getValueCodeableConcept()));
    }

    public void testCreateCauseOfDeathCodingResponseFromSubmissionBundle() {
        CauseOfDeathCodingMessage message = new CauseOfDeathCodingMessage();
        CauseOfDeathCodedContentBundle bundle = new CauseOfDeathCodedContentBundle();
        message.setCauseOfDeathCodedContentBundle(bundle);

        bundle.addAutomatedUnderlyingCauseOfDeathICD10Code("A04.7");

        RecordAxisCauseOfDeath recordAxis = new RecordAxisCauseOfDeath();
        recordAxis.addPosition(1);
        recordAxis.setICD10Code("A41.9");
        recordAxis.addECodeIndicator(false);
        bundle.addEntry().setResource(recordAxis);

        EntityAxisCauseOfDeath entityAxis = new EntityAxisCauseOfDeath();
        entityAxis.addLineNumber(1);
        entityAxis.addPosition(1);
        entityAxis.setICD10Code("J18.9");
        entityAxis.addECodeIndicator(false);
        bundle.addEntry().setResource(entityAxis);

        String json = message.toJson(ctx);
        assertTrue(json.indexOf("A04.7") > -1);
        assertTrue(json.indexOf("A41.9") > -1);
        assertTrue(json.indexOf("J18.9") > -1);
    }

    public void testCreateCauseOfDeathCodingUpdateFromJSON() {
        CauseOfDeathCodingUpdateMessage message = BaseMessage.parseJsonFile(CauseOfDeathCodingUpdateMessage.class,
                ctx, "src/test/resources/json/CauseOfDeathCodingUpdateMessage.json");
        assertEquals(CauseOfDeathCodingUpdateMessage.MESSAGE_TYPE, message.getMessageType());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", message.getMessageDestination());
        assertEquals(100000, message.getCertNo().intValue());
        assertEquals(2019, message.getDeathYear().intValue());
        assertNull(message.getStateAuxiliaryId());
        assertEquals("2019AK100000", message.getNCHSIdentifier());

        CauseOfDeathCodedContentBundle bundle = message.getCauseOfDeathCodedContentBundle();
        assertEquals("I25.1", bundle.getAutomatedUnderlyingCauseOfDeathCodedValue());

        List<RecordAxisCauseOfDeath> recordAxisCodes = bundle.getRecordAxisCauseOfDeath();
        assertEquals(3, recordAxisCodes.size());
        assertEquals("I25.1", recordAxisCodes.get(0).getValueCodeAsString());
        assertEquals("I25.0", recordAxisCodes.get(1).getValueCodeAsString());
        assertEquals("I51.7", recordAxisCodes.get(2).getValueCodeAsString());

        List<EntityAxisCauseOfDeath> entityAxisEntryList = bundle.getEntityAxisCauseOfDeath();
        assertEquals(4, entityAxisEntryList.size());
        assertEquals(1, entityAxisEntryList.get(0).getLineNumber().intValue());
        assertEquals(1, entityAxisEntryList.get(0).getPosition().intValue());
        assertEquals("I25.9", entityAxisEntryList.get(0).getValueCodeAsString());
        assertFalse(entityAxisEntryList.get(0).isECodeIndicator());
        assertEquals(2, entityAxisEntryList.get(1).getLineNumber().intValue());
        assertEquals(1, entityAxisEntryList.get(1).getPosition().intValue());
        assertEquals("I25.1", entityAxisEntryList.get(1).getValueCodeAsString());
        assertFalse(entityAxisEntryList.get(1).isECodeIndicator());
        assertEquals(3, entityAxisEntryList.get(2).getLineNumber().intValue());
        assertEquals(1, entityAxisEntryList.get(2).getPosition().intValue());
        assertEquals("I25.0", entityAxisEntryList.get(2).getValueCodeAsString());
        assertFalse(entityAxisEntryList.get(2).isECodeIndicator());
        assertEquals(3, entityAxisEntryList.get(3).getLineNumber().intValue());
        assertEquals(2, entityAxisEntryList.get(3).getPosition().intValue());
        assertEquals("I51.7", entityAxisEntryList.get(3).getValueCodeAsString());
        assertTrue(entityAxisEntryList.get(3).isECodeIndicator());

        CodingStatusValues codingStatusValues = bundle.getCodingStatusValues();
        assertEquals(3, codingStatusValues.getCoderStatus().intValue());
        assertEquals("555", codingStatusValues.getShipmentNumber());
        assertEquals(2021, codingStatusValues.getReceiptYear().intValue());
        assertEquals(6, codingStatusValues.getReceiptMonth().intValue());
        assertEquals(1, codingStatusValues.getReceiptDay().intValue());

        assertTrue(MannerOfDeathUtil.VALUE_NATURAL.equalsDeep(bundle.getMannerOfDeath().getValueCodeableConcept()));
    }

    public void testCreateDemographicCodingResponseFromJSON() {
        DemographicsCodingMessage message = BaseMessage.parseJsonFile(DemographicsCodingMessage.class,
                ctx, "src/test/resources/json/DemographicsCodingMessage.json");
        DemographicCodedContentBundle bundle = message.getDemographicCodedContentBundle();

        assertEquals(DemographicsCodingMessage.MESSAGE_TYPE, message.getMessageType());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", message.getMessageDestination());
        assertEquals(123, message.getCertNo().intValue());
        assertEquals(2022, message.getDeathYear().intValue());
        assertEquals("500", message.getStateAuxiliaryId());
        assertEquals("2022YC000123", message.getNCHSIdentifier());

        CodedRaceAndEthnicity codedRaceAndEthnicity = bundle.getCodedRaceAndEthnicity();
        assertEquals("199", codedRaceAndEthnicity.getValueCodeableConceptCodeForCoding(CodedRaceAndEthnicityUtil.firstEditedCodeComponentCode));
        assertEquals("B40", codedRaceAndEthnicity.getValueCodeableConceptCodeForCoding(CodedRaceAndEthnicityUtil.firstAmericanIndianCodeComponentCode));

        InputRaceAndEthnicity inputRaceAndEthnicity = bundle.getInputRaceAndEthnicity();
        assertEquals("N", inputRaceAndEthnicity.getValueCodeableConceptCodeForCoding(CodedRaceAndEthnicityUtil.hispanicMexicanCodeComponentCode));
        assertEquals("Y", inputRaceAndEthnicity.getValueCodeableConceptCodeForCoding(CodedRaceAndEthnicityUtil.hispanicCubanCodeComponentCode));
    }

    public void testCreateDemographicsCodingUpdateFromJSON() {
        DemographicsCodingUpdateMessage message = BaseMessage.parseJsonFile(DemographicsCodingUpdateMessage.class,
                ctx, "src/test/resources/json/DemographicsCodingUpdateMessage.json");
        DemographicCodedContentBundle bundle = message.getDemographicCodedContentBundle();

        assertEquals(DemographicsCodingUpdateMessage.MESSAGE_TYPE, message.getMessageType());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", message.getMessageDestination());
        assertEquals(123, message.getCertNo().intValue());
        assertEquals(2022, message.getDeathYear().intValue());
        assertEquals("500", message.getStateAuxiliaryId());
        assertEquals("2022YC000123", message.getNCHSIdentifier());

        CodedRaceAndEthnicity codedRaceAndEthnicity = bundle.getCodedRaceAndEthnicity();
        assertEquals("199", codedRaceAndEthnicity.getValueCodeableConceptCodeForCoding(CodedRaceAndEthnicityUtil.firstEditedCodeComponentCode));
        assertEquals("B40", codedRaceAndEthnicity.getValueCodeableConceptCodeForCoding(CodedRaceAndEthnicityUtil.firstAmericanIndianCodeComponentCode));

        InputRaceAndEthnicity inputRaceAndEthnicity = bundle.getInputRaceAndEthnicity();
        assertEquals("N", inputRaceAndEthnicity.getValueCodeableConceptCodeForCoding(CodedRaceAndEthnicityUtil.hispanicMexicanCodeComponentCode));
        assertEquals("Y", inputRaceAndEthnicity.getValueCodeableConceptCodeForCoding(CodedRaceAndEthnicityUtil.hispanicCubanCodeComponentCode));
    }

    public void testCreateDeathRecordVoidMessage() {
        DeathRecordVoidMessage message = new DeathRecordVoidMessage();
        assertEquals("http://nchs.cdc.gov/vrdr_submission_void", message.getMessageType());
        assertNull(message.getCertNo());
        message.setCertNo(11);
        assertEquals(11, message.getCertNo().intValue());
        assertNull(message.getStateAuxiliaryId());
        message.setStateAuxiliaryId("bar");
        assertEquals("bar", message.getStateAuxiliaryId());
        assertNull(message.getNCHSIdentifier());
        assertNull(message.getBlockCount());
        message.setBlockCount(-100);
        assertNull(message.getBlockCount());
        message.setBlockCount(100);
        assertEquals(new UnsignedIntType(100).getValue(), message.getBlockCount().getValue());
    }

    public void testCreateStatusMessage() {
        DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "src/test/resources/json/DeathRecordSubmissionMessage.json");
        StatusMessage status = new StatusMessage(submission, "manualCauseOfDeathCoding");
        assertEquals("http://nchs.cdc.gov/vrdr_status", status.getMessageType());
        assertEquals("manualCauseOfDeathCoding", status.getStatus());
        assertEquals(submission.getMessageHeaderId(), status.getStatusedMessageId());
        assertEquals(submission.getMessageSource(), status.getMessageDestination());
        assertEquals(submission.getMessageDestination(), status.getMessageSource());
        assertEquals(submission.getStateAuxiliaryId(), status.getStateAuxiliaryId());
        assertEquals(submission.getCertNo(), status.getCertNo());
        assertEquals(submission.getNCHSIdentifier(), status.getNCHSIdentifier());

        submission = null;
        status = new StatusMessage(submission, "manualCauseOfDeathCoding");
        assertEquals("http://nchs.cdc.gov/vrdr_status", status.getMessageType());
        assertEquals("manualCauseOfDeathCoding", status.getStatus());
        assertNull(status.getStatusedMessageId());
        assertNull(status.getMessageDestination());
        assertNull(status.getMessageSource());
        assertNull(status.getCertNo());
        assertNull(status.getStateAuxiliaryId());
        assertNull(status.getNCHSIdentifier());

        submission = new DeathRecordSubmissionMessage();
        status = new StatusMessage(submission, "manualCauseOfDeathCoding");
        assertEquals("http://nchs.cdc.gov/vrdr_status", status.getMessageType());
        assertEquals("manualCauseOfDeathCoding", status.getStatus());
        assertEquals(submission.getMessageHeaderId(), status.getStatusedMessageId());
        assertEquals(submission.getMessageSource(), status.getMessageDestination());
        assertEquals(submission.getMessageDestination(), status.getMessageSource());
        assertEquals(submission.getStateAuxiliaryId(), status.getStateAuxiliaryId());
        assertEquals(submission.getCertNo(), status.getCertNo());
        assertEquals(submission.getNCHSIdentifier(), status.getNCHSIdentifier());

    }

    public void testCreateAckForDeathRecordVoidMessage() {
        DeathRecordVoidMessage voidMessage = BaseMessage.parseJsonFile(DeathRecordVoidMessage.class, ctx, "src/test/resources/json/DeathRecordVoidMessage.json");
        AcknowledgementMessage ack = new AcknowledgementMessage(voidMessage);
        assertEquals("http://nchs.cdc.gov/vrdr_acknowledgement", ack.getMessageType());
        assertEquals(voidMessage.getMessageHeaderId(), ack.getAckedMessageId());
        assertEquals(voidMessage.getMessageSource(), ack.getMessageDestination());
        assertEquals(voidMessage.getMessageDestination(), ack.getMessageSource());
        assertEquals(voidMessage.getStateAuxiliaryId(), ack.getStateAuxiliaryId());
        assertEquals(voidMessage.getCertNo(), ack.getCertNo());
        assertEquals(voidMessage.getNCHSIdentifier(), ack.getNCHSIdentifier());
        assertEquals(voidMessage.getBlockCount().getValue(), ack.getBlockCount().getValue());

        voidMessage = null;
        ack = new AcknowledgementMessage(voidMessage);
        assertEquals("http://nchs.cdc.gov/vrdr_acknowledgement", ack.getMessageType());
        assertNull(ack.getAckedMessageId());
        assertNull(ack.getMessageDestination());
        assertNull(ack.getMessageSource());
        assertNull(ack.getCertNo());
        assertNull(ack.getStateAuxiliaryId());
        assertNull(ack.getNCHSIdentifier());
        assertNull(ack.getBlockCount());

        voidMessage = new DeathRecordVoidMessage();
        ack = new AcknowledgementMessage(voidMessage);
        assertEquals("http://nchs.cdc.gov/vrdr_acknowledgement", ack.getMessageType());
        assertEquals(voidMessage.getMessageHeaderId(), ack.getAckedMessageId());
        assertEquals(voidMessage.getMessageSource(), ack.getMessageDestination());
        assertEquals(voidMessage.getMessageDestination(), ack.getMessageSource());
        assertEquals(voidMessage.getStateAuxiliaryId(), ack.getStateAuxiliaryId());
        assertEquals(voidMessage.getCertNo(), ack.getCertNo());
        assertEquals(voidMessage.getNCHSIdentifier(), ack.getNCHSIdentifier());
        assertEquals(voidMessage.getBlockCount(), ack.getBlockCount());
    }

    public void testSetBlockCountDeathRecordVoidMessage() {
        DeathRecordVoidMessage voidMessage = new DeathRecordVoidMessage();
        Integer value = null;
        voidMessage.setBlockCount(value);
        assertNull(voidMessage.getBlockCount());
        value = -1;
        voidMessage.setBlockCount(value);
        assertNull(voidMessage.getBlockCount());
        value = 0;
        voidMessage.setBlockCount(value);
        assertNull(voidMessage.getBlockCount());
        value = 1;
        voidMessage.setBlockCount(value);
        assertEquals(voidMessage.getBlockCount().getValue(), new UnsignedIntType(value).getValue());
        value = 2;
        voidMessage.setBlockCount(value);
        assertEquals(voidMessage.getBlockCount().getValue(), new UnsignedIntType(value).getValue());
    }

    public void testCreateAckForStatusMessage() {
        StatusMessage statusMessage = BaseMessage.parseJsonFile(StatusMessage.class, ctx, "src/test/resources/json/StatusMessage.json");
        AcknowledgementMessage ack = new AcknowledgementMessage(statusMessage);
        assertEquals("http://nchs.cdc.gov/vrdr_acknowledgement", ack.getMessageType());
        assertEquals(statusMessage.getMessageHeaderId(), ack.getAckedMessageId());
        assertEquals(statusMessage.getMessageSource(), ack.getMessageDestination());
        assertEquals(statusMessage.getMessageDestination(), ack.getMessageSource());
        assertEquals(statusMessage.getStateAuxiliaryId(), ack.getStateAuxiliaryId());
        assertEquals(statusMessage.getCertNo(), ack.getCertNo());
        assertEquals(statusMessage.getNCHSIdentifier(), ack.getNCHSIdentifier());

        statusMessage = null;
        ack = new AcknowledgementMessage(statusMessage);
        assertEquals("http://nchs.cdc.gov/vrdr_acknowledgement", ack.getMessageType());
        assertNull(ack.getAckedMessageId());
        assertNull(ack.getMessageDestination());
        assertNull(ack.getMessageSource());
        assertNull(ack.getCertNo());
        assertNull(ack.getStateAuxiliaryId());
        assertNull(ack.getNCHSIdentifier());

        statusMessage = new StatusMessage();
        ack = new AcknowledgementMessage(statusMessage);
        assertEquals("http://nchs.cdc.gov/vrdr_acknowledgement", ack.getMessageType());
        assertEquals(statusMessage.getMessageHeaderId(), ack.getAckedMessageId());
        assertEquals(statusMessage.getMessageSource(), ack.getMessageDestination());
        assertEquals(statusMessage.getMessageDestination(), ack.getMessageSource());
        assertEquals(statusMessage.getStateAuxiliaryId(), ack.getStateAuxiliaryId());
        assertEquals(statusMessage.getCertNo(), ack.getCertNo());
        assertEquals(statusMessage.getNCHSIdentifier(), ack.getNCHSIdentifier());
    }

    public void testCreateDeathRecordVoidMessageFromJson() {
        DeathRecordVoidMessage message = BaseMessage.parseJsonFile(DeathRecordVoidMessage.class, ctx, "src/test/resources/json/DeathRecordVoidMessage.json");
        assertEquals("http://nchs.cdc.gov/vrdr_submission_void", message.getMessageType());
        assertEquals(123456, message.getCertNo().intValue());
        assertEquals(new UnsignedIntType(10).getValue(), message.getBlockCount().getValue());
        assertEquals("abcdef10", message.getStateAuxiliaryId());
        assertEquals("2018NY123456", message.getNCHSIdentifier());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", message.getMessageDestination());
        assertEquals("https://sos.nh.gov/vitalrecords", message.getMessageSource());

        message = BaseMessage.parseJsonFile(DeathRecordVoidMessage.class, ctx, "src/test/resources/json/DeathRecordVoidMessageNoIdentifiers.json");
        assertEquals("http://nchs.cdc.gov/vrdr_submission_void", message.getMessageType());
        assertNull(message.getCertNo());
        assertNull(message.getStateAuxiliaryId());
        assertNull(message.getNCHSIdentifier());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", message.getMessageDestination());
        assertEquals("nightingale", message.getMessageSource());
    }

    public void testGetDeathRecordVoidMessageBlockCount16FromJson() {
        DeathRecordVoidMessage message = BaseMessage.parseJsonFile(DeathRecordVoidMessage.class, ctx, "src/test/resources/json/DeathRecordVoidMessageBlockCount16.json");
        assertEquals(new UnsignedIntType(16).getValue(), message.getBlockCount().getValue());
    }

    public void testCreateVoidForDocument() {
        DeathCertificateDocument deathRecord = deathRecord1FromXML;
        DeathRecordVoidMessage message = new DeathRecordVoidMessage(deathRecord);
        assertEquals("http://nchs.cdc.gov/vrdr_submission_void", message.getMessageType());
        assertEquals(182, message.getCertNo().intValue());
        assertEquals("000000000042", message.getStateAuxiliaryId());
        assertEquals("2019YC000182", message.getNCHSIdentifier());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", message.getMessageDestination());
        assertNull(message.getMessageSource());

        deathRecord = null;
        message = new DeathRecordVoidMessage(deathRecord);
        assertEquals("http://nchs.cdc.gov/vrdr_submission_void", message.getMessageType());
        assertNull(message.getCertNo());
        assertNull(message.getStateAuxiliaryId());
        assertNull(message.getNCHSIdentifier());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", message.getMessageDestination());
        assertNull(message.getMessageSource());
    }

    public void testCreateDeathRecordAliasMessage() {
        DeathRecordAliasMessage message = new DeathRecordAliasMessage();
        assertEquals("http://nchs.cdc.gov/vrdr_alias", message.getMessageType());
        assertNull(message.getCertNo());
        message.setCertNo(12);
        assertEquals(12, message.getCertNo().intValue());
        assertNull(message.getStateAuxiliaryId());
        message.setStateAuxiliaryId("SAI");
        assertEquals("SAI", message.getStateAuxiliaryId());
        assertNull(message.getNCHSIdentifier());
        assertNull(message.getAliasDecedentFirstName());
        message.setAliasDecedentFirstName("DecedentFirstName");
        assertEquals("DecedentFirstName", message.getAliasDecedentFirstName());
        assertNull(message.getAliasDecedentLastName());
        message.setAliasDecedentLastName("DecedentLastName");
        assertEquals("DecedentLastName", message.getAliasDecedentLastName());
        assertNull(message.getAliasDecedentMiddleName());
        message.setAliasDecedentMiddleName("DecedentMiddleName");
        assertEquals("DecedentMiddleName", message.getAliasDecedentMiddleName());
        assertNull(message.getAliasDecedentNameSuffix());
        message.setAliasDecedentNameSuffix("DecedentNameSuffix");
        assertEquals("DecedentNameSuffix", message.getAliasDecedentNameSuffix());
        assertNull(message.getAliasFatherSurname());
        message.setAliasFatherSurname("FatherSurname");
        assertEquals("FatherSurname", message.getAliasFatherSurname());
        assertNull(message.getAliasSocialSecurityNumber());
        message.setAliasSocialSecurityNumber("SocialSecurityNumber");
        assertEquals("SocialSecurityNumber", message.getAliasSocialSecurityNumber());
    }

    public void testCreateAckForDeathRecordAliasMessage() {
        DeathRecordAliasMessage message = BaseMessage.parseJsonFile(DeathRecordAliasMessage.class, ctx, "src/test/resources/json/DeathRecordAliasMessage.json");
        AcknowledgementMessage ack = new AcknowledgementMessage(message);
        assertEquals("http://nchs.cdc.gov/vrdr_acknowledgement", ack.getMessageType());
        assertEquals(message.getMessageHeaderId(), ack.getAckedMessageId());
        assertEquals(message.getMessageSource(), ack.getMessageDestination());
        assertEquals(message.getMessageDestination(), ack.getMessageSource());
        assertEquals(message.getStateAuxiliaryId(), ack.getStateAuxiliaryId());
        assertEquals(message.getCertNo(), ack.getCertNo());
        assertEquals(message.getNCHSIdentifier(), ack.getNCHSIdentifier());
    }

    public void testCreateDeathRecordAliasMessageFromJson() {
        DeathRecordAliasMessage message = BaseMessage.parseJsonFile(DeathRecordAliasMessage.class, ctx, "src/test/resources/json/DeathRecordAliasMessage.json");
        assertEquals("http://nchs.cdc.gov/vrdr_alias", message.getMessageType());
        assertEquals(123456, message.getCertNo().intValue());
        assertEquals("abcdef10", message.getStateAuxiliaryId());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", message.getMessageDestination());
        assertEquals("John", message.getAliasDecedentFirstName());
        assertEquals("Schmidt", message.getAliasDecedentLastName());
        assertEquals("Jacob", message.getAliasDecedentMiddleName());
        assertEquals("III", message.getAliasDecedentNameSuffix());
        assertEquals("Jingleheimer", message.getAliasFatherSurname());
        assertEquals("123-45-6789", message.getAliasSocialSecurityNumber());
    }

    public void testCreateAliasForDocument()
    {
        DeathRecordAliasMessage message = new DeathRecordAliasMessage(deathRecord1FromXML);
        assertEquals("http://nchs.cdc.gov/vrdr_alias", message.getMessageType());
        assertEquals(182, message.getCertNo().intValue());
        assertEquals("000000000042", message.getStateAuxiliaryId());
        assertEquals("2019YC000182", message.getNCHSIdentifier());
        assertEquals("http://nchs.cdc.gov/vrdr_submission", message.getMessageDestination());
        assertNull(message.getMessageSource());
    }

    public void testCreateAliasForRecordWithEmptyMiddleNameString() {
        // This is a regression test for a bug where an empty middle name field led to an empty string in the alias
        DeathCertificateDocument record = BaseMessage.parseJsonFile(DeathCertificateDocument.class, ctx, "src/test/resources/json/DeathRecord1.json");
        record.setId("321");
        record.getDecedent().get(0).getAddress().get(0).setState("MA");
        record.getDeathLocation().get(0).getAddress().setState("MA");
        record.getDecedent().get(0).getName().get(0).setGiven(Arrays.asList(new StringType("Rocket"), new StringType("")));
        DeathRecordAliasMessage message = new DeathRecordAliasMessage(record);
        assertNull(message.getAliasDecedentMiddleName());
    }

    public void testCreateExtractionErrorForMessage() {
        DeathRecordSubmissionMessage submission = BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "src/test/resources/json/DeathRecordSubmissionMessage.json");
        ExtractionErrorMessage err = new ExtractionErrorMessage(submission);
        assertEquals("http://nchs.cdc.gov/vrdr_extraction_error", err.getMessageType());
        assertEquals(submission.getId(), err.getFailedMessageId());
        assertEquals(submission.getMessageSource(), err.getMessageDestination());
        assertEquals(submission.getStateAuxiliaryId(), err.getStateAuxiliaryId());
        assertEquals(submission.getCertNo(), err.getCertNo());
        assertEquals(submission.getNCHSIdentifier(), err.getNCHSIdentifier());
        assertEquals(0, err.getIssues().size());

        List<ExtractionErrorMessage.Issue> issues = new ArrayList<>();
        ExtractionErrorMessage.Issue issue = new ExtractionErrorMessage.Issue(OperationOutcome.IssueSeverity.FATAL, OperationOutcome.IssueType.INVALID, "The message was invalid");
        issues.add(issue);
        issue = new ExtractionErrorMessage.Issue(OperationOutcome.IssueSeverity.WARNING, OperationOutcome.IssueType.EXPIRED, "The message was very old");
        issues.add(issue);
        err.setIssues(issues);
        issues = err.getIssues();
        assertEquals(2, issues.size());
        assertEquals(OperationOutcome.IssueSeverity.FATAL, issues.get(0).getIssueSeverity());
        assertEquals(OperationOutcome.IssueType.INVALID, issues.get(0).getIssueType());
        assertEquals("The message was invalid", issues.get(0).getDescription());
        assertEquals(OperationOutcome.IssueSeverity.WARNING, issues.get(1).getIssueSeverity());
        assertEquals(OperationOutcome.IssueType.EXPIRED, issues.get(1).getIssueType());
        assertEquals("The message was very old", issues.get(1).getDescription());

        submission = null;
        err = new ExtractionErrorMessage(submission);
        assertEquals("http://nchs.cdc.gov/vrdr_extraction_error", err.getMessageType());
        assertNull(err.getFailedMessageId());
        assertNull(err.getMessageDestination());
        assertNull(err.getCertNo());
        assertNull(err.getStateAuxiliaryId());
        assertNull(err.getNCHSIdentifier());
        assertEquals(0, err.getIssues().size());
    }

    public void testCreateExtractionErrorFromJson() {
        ExtractionErrorMessage err = BaseMessage.parseJsonFile(ExtractionErrorMessage.class, ctx, "src/test/resources/json/ExtractionErrorMessage.json");
        assertEquals("http://nchs.cdc.gov/vrdr_extraction_error", err.getMessageType());
        assertEquals(1, (long) err.getCertNo());
        assertEquals("42", err.getStateAuxiliaryId());
        assertEquals("2018MA000001", err.getNCHSIdentifier());
        List<ExtractionErrorMessage.Issue> issues = err.getIssues();
        assertEquals(2, issues.size());
        assertEquals(OperationOutcome.IssueSeverity.FATAL, issues.get(0).getIssueSeverity());
        assertEquals(OperationOutcome.IssueType.INVALID, issues.get(0).getIssueType());
        assertEquals("The message was invalid", issues.get(0).getDescription());
        assertEquals(OperationOutcome.IssueSeverity.WARNING, issues.get(1).getIssueSeverity());
        assertEquals(OperationOutcome.IssueType.EXPIRED, issues.get(1).getIssueType());
        assertEquals("The message was very old", issues.get(1).getDescription());
    }

    public void testCreateSubmissionWithRaceLiterals() {
        // create a new death certificate and add it to a death record
        DeathCertificate deathCertificate = new DeathCertificate();
        DeathCertificateDocument deathRecord = new DeathCertificateDocument();
        CommonUtil.initResource(deathCertificate);
        deathRecord.addEntry(new Bundle.BundleEntryComponent().setResource(deathCertificate));

        // create input race and ethnicity resource and add it to the death record
        InputRaceAndEthnicity inputRaceAndEthnicity = new InputRaceAndEthnicity();
        inputRaceAndEthnicity.addRaceBooleanComponent("AmericanIndianOrAlaskanNative", true);
        inputRaceAndEthnicity.addRaceLiteralComponent("FirstAmericanIndianOrAlaskanNativeLiteral", "Apache");
        inputRaceAndEthnicity.addRaceLiteralComponent("SecondAmericanIndianOrAlaskanNativeLiteral", "Lipan Apache");
        inputRaceAndEthnicity.addHispanicBooleanComponent("HispanicOther", true);
        CommonUtil.setUUID(inputRaceAndEthnicity);
        deathRecord.addResource(inputRaceAndEthnicity);

        // test that bundle literals are readable
        InputRaceAndEthnicity race = deathRecord.getInputRaceAndEthnicity().get(0);
        assertTrue(race.getAmericanIndianOrAlaskaNativeBooleanValue());
        assertEquals("Apache", race.getFirstAmericanIndianOrAlaskaNativeLiteralValue());
        assertEquals("Lipan Apache", race.getSecondAmericanIndianOrAlaskaNativeLiteralValue());
        assertEquals("Y", race.getHispanicOtherCodedValue());
        assertFalse(race.getSamoanBooleanValue()); // should be false because it is not set
        assertNull(race.getFirstOtherRaceLiteralValue()); // should be null because it is not set

        // create a new submission message and add the death record to it
        DeathRecordSubmissionMessage submission = new DeathRecordSubmissionMessage();
        submission.setDeathRecord(deathRecord);
        submission.setCertNo(42);
        submission.setStateAuxiliaryId("identifier");

        // check that the added input race and ethnicity display in the json submission
        String submissionBundleStr = submission.toJson(ctx);
        assertTrue(submissionBundleStr.contains("{\"code\":{\"coding\":[{\"system\":\"http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs\",\"code\":\"AmericanIndianOrAlaskanNative\"}]},\"valueBoolean\":true}"));
        assertTrue(submissionBundleStr.contains("{\"code\":{\"coding\":[{\"system\":\"http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs\",\"code\":\"FirstAmericanIndianOrAlaskanNativeLiteral\"}]},\"valueString\":\"Apache\"}"));
        assertTrue(submissionBundleStr.contains("{\"code\":{\"coding\":[{\"system\":\"http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs\",\"code\":\"SecondAmericanIndianOrAlaskanNativeLiteral\"}]},\"valueString\":\"Lipan Apache\"}"));
        assertTrue(submissionBundleStr.contains("{\"code\":{\"coding\":[{\"system\":\"http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs\",\"code\":\"HispanicOther\"}]},\"valueCodeableConcept\":{\"coding\":[{\"system\":\"http://terminology.hl7.org/CodeSystem/v2-0136\",\"code\":\"Y\",\"display\":\"Yes\"}]}}"));
        DeathRecordSubmissionMessage parsed = BaseMessage.parseJson(DeathRecordSubmissionMessage.class, ctx, submissionBundleStr);
        assertNotNull(parsed); // make sure we can parse the race values without crashing
    }

    public void testParseBundleOfBundles() {
        // create the outer bundle
        BaseMessage responseBundle = BaseMessage.parseJsonFile(AcknowledgementMessage.class, ctx, "src/test/resources/json/AcknowledgementMessage.json");

        // add different types of messages or inner bundles to the outer bundle
        responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(BaseMessage.parseJsonFile(DeathRecordSubmissionMessage.class, ctx, "src/test/resources/json/DeathRecordSubmissionMessage.json")));
        responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(BaseMessage.parseJsonFile(DeathRecordUpdateMessage.class, ctx, "src/test/resources/json/DeathRecordUpdateMessage.json")));
        responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(BaseMessage.parseJsonFile(AcknowledgementMessage.class, ctx, "src/test/resources/json/AcknowledgementMessage.json")));
        responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(BaseMessage.parseJsonFile(DeathRecordVoidMessage.class, ctx, "src/test/resources/json/DeathRecordVoidMessage.json")));
        responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(BaseMessage.parseJsonFile(DeathRecordAliasMessage.class, ctx, "src/test/resources/json/DeathRecordAliasMessage.json")));
        responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(BaseMessage.parseJsonFile(CauseOfDeathCodingMessage.class, ctx, "src/test/resources/json/CauseOfDeathCodingMessage.json")));
        responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(BaseMessage.parseJsonFile(CauseOfDeathCodingUpdateMessage.class, ctx, "src/test/resources/json/CauseOfDeathCodingUpdateMessage.json")));
        responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(BaseMessage.parseJsonFile(DemographicsCodingMessage.class, ctx, "src/test/resources/json/DemographicsCodingMessage.json")));
        responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(BaseMessage.parseJsonFile(DemographicsCodingUpdateMessage.class, ctx, "src/test/resources/json/DemographicsCodingUpdateMessage.json")));
        responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(BaseMessage.parseJsonFile(StatusMessage.class, ctx, "src/test/resources/json/StatusMessage.json")));

        // convert the loaded outer bundle, or bundle of bundles, to String
        String bundleString = responseBundle.toJson(ctx);

        // parse the stringified bundle of bundles by invoking static function
        List<BaseMessage> listOfMessages = BaseMessage.parseBundleOfBundles(ctx, bundleString);

        // test size of list of messages returned
        assertEquals(listOfMessages.size(), 10);

        // test parsing different types of legit messages returned
        assertTrue(listOfMessages.get(0).toString().contains("DeathRecordSubmissionMessage"));
        assertEquals(listOfMessages.get(0).getMessageType(), DeathRecordSubmissionMessage.MESSAGE_TYPE);
        assertEquals(((DeathRecordSubmissionMessage)listOfMessages.get(0)).getDeathRecord().getDeathLocation().get(0).getAddress().getCity(), "Albany");
        assertTrue(((DeathRecordSubmissionMessage)listOfMessages.get(0)).getDeathRecord().getDeathCertificate().get(0).getId().contains("DeathCertificate"));

        assertTrue(listOfMessages.get(1).toString().contains("DeathRecordUpdateMessage"));
        assertEquals(listOfMessages.get(1).getMessageType(), DeathRecordUpdateMessage.MESSAGE_TYPE);
        assertTrue(listOfMessages.get(2).toString().contains("AcknowledgementMessage"));
        assertEquals(listOfMessages.get(2).getMessageType(), AcknowledgementMessage.MESSAGE_TYPE);
        assertTrue(listOfMessages.get(3).toString().contains("DeathRecordVoidMessage"));
        assertEquals(listOfMessages.get(3).getMessageType(), DeathRecordVoidMessage.MESSAGE_TYPE);
        assertTrue(listOfMessages.get(4).toString().contains("DeathRecordAliasMessage"));
        assertEquals(listOfMessages.get(4).getMessageType(), DeathRecordAliasMessage.MESSAGE_TYPE);
        assertTrue(listOfMessages.get(5).toString().contains("CauseOfDeathCodingMessage"));
        assertEquals(listOfMessages.get(5).getMessageType(), CauseOfDeathCodingMessage.MESSAGE_TYPE);
        assertTrue(listOfMessages.get(6).toString().contains("CauseOfDeathCodingUpdateMessage"));
        assertEquals(listOfMessages.get(6).getMessageType(), CauseOfDeathCodingUpdateMessage.MESSAGE_TYPE);

        assertTrue(listOfMessages.get(7).toString().contains("DemographicsCodingMessage"));
        assertEquals(listOfMessages.get(7).getMessageType(), DemographicsCodingMessage.MESSAGE_TYPE);
        assertTrue(((DemographicsCodingMessage)listOfMessages.get(7)).getDemographicCodedContentBundle().getEntry().get(0).getResource().toString().contains("CodedRaceAndEthnicity"));
        assertTrue(((DemographicsCodingMessage)listOfMessages.get(7)).getDemographicCodedContentBundle().getEntry().get(1).getResource().toString().contains("InputRaceAndEthnicity"));

        assertTrue(listOfMessages.get(8).toString().contains("DemographicsCodingUpdateMessage"));
        assertEquals(listOfMessages.get(8).getMessageType(), DemographicsCodingUpdateMessage.MESSAGE_TYPE);
        assertTrue(listOfMessages.get(9).toString().contains("StatusMessage"));
        assertEquals(listOfMessages.get(9).getMessageType(), StatusMessage.MESSAGE_TYPE);

        // test parsing deficient messages that cause thrown exceptions
        try {
            responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(BaseMessage.parseJsonFile(ExtractionErrorMessage.class, ctx, "src/test/resources/json/ExtractionErrorMessage.json")));
            bundleString = responseBundle.toJson(ctx);
            listOfMessages = BaseMessage.parseBundleOfBundles(ctx, bundleString);
            fail("Expected MessageParseException");
        } catch (MessageParseException ex) {
            assertEquals("Error processing entry in the message: edu.gatech.chai.VRDR.messaging.util.MessageParseException: Failed to find a Bundle Entry containing a Resource of type org.hl7.fhir.r4.model.OperationOutcome", ex.getMessage());
        }

        try {
            DeathCertificate DeathCertificate =  new DeathCertificate();
            responseBundle.addEntry(new Bundle.BundleEntryComponent().setResource(DeathCertificate));
            bundleString = responseBundle.toJson(ctx);
            listOfMessages = BaseMessage.parseBundleOfBundles(ctx, bundleString);
            fail("Expected MessageParseException");
        } catch (MessageParseException ex) {
            assertEquals("Error processing entry in the message: edu.gatech.chai.VRDR.messaging.util.MessageParseException: Failed to find a Bundle Entry containing a Resource of type org.hl7.fhir.r4.model.OperationOutcome", ex.getMessage());
        }
    }

    public void testCreateBulkUploadPayload() {
        // set message counter to a value > 0. Note that the higher the value, the longer the run time
        int msgCounter = 100;

        // create a list
        List<BaseMessage> messages = new ArrayList<BaseMessage>();

        // create a new death certificate "deathCertificate" and add it to a death record "deathRecord"
        DeathCertificate deathCertificate = new DeathCertificate();
        DeathCertificateDocument deathRecord = new DeathCertificateDocument();
        CommonUtil.initResource(deathCertificate);
        deathRecord.addEntry((new Bundle.BundleEntryComponent()).setResource(deathCertificate));

        // loop from 1 to 100 to create 100 messages and their contents
        for(int i=1; i<=msgCounter; i++) {
            // create a new submission message "message" and add the death record "deathRecord" to it
            DeathRecordSubmissionMessage message = new DeathRecordSubmissionMessage();
            message.setDeathRecord(deathRecord);
            message.setCertNo(Integer.valueOf(i));
            message.setJurisdictionId("TT");
            message.setStateAuxiliaryId(Integer.toString((int) Math.random() * msgCounter + 1));
            messages.add(message);
        }
        // test method
        String strBundleInJson = UploadUtil.CreateBulkUploadPayload(this.ctx, messages, "http://nchs.cdc.gov/vrdr_submission", true);
        assertTrue((strBundleInJson != null && strBundleInJson.length() > 0));
        assertTrue(strBundleInJson.contains("\"method\": \"POST\""));
        assertEquals(StringUtils.countMatches(strBundleInJson, "\"method\": \"POST\""), msgCounter);
        assertEquals(StringUtils.countMatches(strBundleInJson, "http://cdc.gov/nchs/nvss/fhir/vital-records-messaging/StructureDefinition/VRM-DeathRecordSubmissionMessage"), msgCounter);
        assertEquals(StringUtils.countMatches(strBundleInJson, "http://nchs.cdc.gov/vrdr_submission"), 3 * msgCounter);
    }

    public void testCodeableConceptPlaceOfDeath() {
        CodeableConcept codeableConceptPlaceofDeath = CommonUtil.findConceptFromCollectionUsingSimpleString("Death in hospital", DeathDateUtil.placeOfDeathTypeSet);
        assertEquals("16983000", codeableConceptPlaceofDeath.getCoding().get(0).getCode());
        codeableConceptPlaceofDeath = CommonUtil.findConceptFromCollectionUsingSimpleString("Death in hospital-based emergency department or outpatient department (event)", DeathDateUtil.placeOfDeathTypeSet);
        assertEquals("450391000124102", codeableConceptPlaceofDeath.getCoding().get(0).getCode());
        codeableConceptPlaceofDeath = CommonUtil.findConceptFromCollectionUsingSimpleString("Death in home", DeathDateUtil.placeOfDeathTypeSet);
        assertEquals("440081000124100", codeableConceptPlaceofDeath.getCoding().get(0).getCode());
        codeableConceptPlaceofDeath = CommonUtil.findConceptFromCollectionUsingSimpleString("Death in hospice", DeathDateUtil.placeOfDeathTypeSet);
        assertEquals("440071000124103", codeableConceptPlaceofDeath.getCoding().get(0).getCode());
        codeableConceptPlaceofDeath = CommonUtil.findConceptFromCollectionUsingSimpleString("Dead on arrival at hospital", DeathDateUtil.placeOfDeathTypeSet);
        assertEquals("63238001", codeableConceptPlaceofDeath.getCoding().get(0).getCode());
        codeableConceptPlaceofDeath = CommonUtil.findConceptFromCollectionUsingSimpleString("Death in nursing home or long term care facility (event)", DeathDateUtil.placeOfDeathTypeSet);
        assertEquals("450381000124100", codeableConceptPlaceofDeath.getCoding().get(0).getCode());
        codeableConceptPlaceofDeath = CommonUtil.findConceptFromCollectionUsingSimpleString("Unknown", DeathDateUtil.placeOfDeathTypeSet);
        assertEquals("UNK", codeableConceptPlaceofDeath.getCoding().get(0).getCode());
        codeableConceptPlaceofDeath = CommonUtil.findConceptFromCollectionUsingSimpleString("Other", DeathDateUtil.placeOfDeathTypeSet);
        assertEquals("OTH", codeableConceptPlaceofDeath.getCoding().get(0).getCode());
    }

}
