package edu.gatech.chai.MDI.model.resource.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class MessageHeaderDCRUtil {
	public static final String eventSystem = "http://hl7.org/fhir/us/mdi/CodeSystem/cs-mdi-codes";
	public static final String eventCode = "death-certificate-review-event";
	
	public static final HashSet<CodeableConcept> reasonValueSet = new HashSet<>(Arrays.asList(
            new CodeableConcept().addCoding(new Coding(CommonUtil.deathCertificateReviewValuesetURL, "DC_MED_DATA_Q_REQ", "Death Certificate Medical Data Quality Review Request")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.deathCertificateReviewValuesetURL, "DC_MED_DATA_Q_RSP", "Death Certificate Medical Data Quality Review Response")),
            new CodeableConcept().addCoding(new Coding(CommonUtil.deathCertificateReviewValuesetURL, "DC_PER_DATA_Q_REQ", "Death Certificate Personal Data Quality Review Request")),
            new CodeableConcept().addCoding(new Coding(CommonUtil.deathCertificateReviewValuesetURL, "DC_PER_DATA_Q_RSP", "Death Certificate Personal Data Quality Review Response")),
            new CodeableConcept().addCoding(new Coding(CommonUtil.deathCertificateReviewValuesetURL, "CREM_C_REQ", "Cremation Clearance Request")),
            new CodeableConcept().addCoding(new Coding(CommonUtil.deathCertificateReviewValuesetURL, "CREM_C_RSP", "Cremation Clearance Response"))));
}
