package edu.gatech.chai.MDI.model.resource.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class CompositionMDIDCRUtil {
	public static final CodeableConcept type = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"death-certificate-data-review-doc",""));
	
	public static final String deathCertificateStatusExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-death-certificate-status";
	public static final String cremationClearanceStatusExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-cremation-clearance-status";
	public static final String meCertificationAffirmationExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-me-certification-affirmation";
	public static final String cremationClearanceCoronerExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-cremation-clearance-coroner";
	public static final String dCCertificationExtensionURL = "DCcertification";
	public static final String dCRegistrationExtensionURL = "DCregistration";

	public static final HashSet<CodeableConcept> dCCertificationSet = new HashSet<>(Arrays.asList(
            new CodeableConcept().addCoding(new Coding(CommonUtil.dcrReviewExampleURL, "DEATH_CERT_CERT", "Certified")),
            new CodeableConcept().addCoding(new Coding(CommonUtil.dcrReviewExampleURL, "DEATH_CERT_NOT_CERT", "Not Certified"))));

	public static final HashSet<CodeableConcept> dCRRegistrationSet = new HashSet<>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(CommonUtil.dcrReviewExampleURL, "DEATH_CERT_REG", "Registered")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dcrReviewExampleURL, "DEATH_CERT_NOT_REG", "Not Registered"))));

	public static final HashSet<CodeableConcept> creamationClearanceStatusSet = new HashSet<>(Arrays.asList(
		new CodeableConcept().addCoding(new Coding(CommonUtil.dcrReviewExampleURL, "CREM_C_REQUESTED", "Requested")),
		new CodeableConcept().addCoding(new Coding(CommonUtil.dcrReviewExampleURL, "CREM_C_PENDING", "Pending")),
		new CodeableConcept().addCoding(new Coding(CommonUtil.dcrReviewExampleURL, "CREM_C_REJECTED", "Rejected")),
		new CodeableConcept().addCoding(new Coding(CommonUtil.dcrReviewExampleURL, "CREM_C_APPROVED", "Approved"))));

	public static final HashSet<CodeableConcept> meExaminerCertificationSet = new HashSet<>(Arrays.asList(
		new CodeableConcept().addCoding(new Coding(CommonUtil.dcrReviewExampleURL, "ME_AFFIRM_CERTIFICATION_AFFIRMED", "ME Certification Affirmation Affirmed")),
		new CodeableConcept().addCoding(new Coding(CommonUtil.dcrReviewExampleURL, "ME_AFFIRM_CERTIFICATION_NOT_AFFIRMED", "ME Certification Affirmation Not Affirmed"))));

	public static final HashSet<CodeableConcept> crematationClearanceSignatureSet = new HashSet<>(Arrays.asList(
		new CodeableConcept().addCoding(new Coding(CommonUtil.dcrReviewExampleURL, "CREM_C_SIGNED", "Signed")),
		new CodeableConcept().addCoding(new Coding(CommonUtil.dcrReviewExampleURL, "CREM_C_UNSIGNED", "Unsigned"))));

	public static final CodeableConcept decedentDemographicsSectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.vrdrDocumentSectionCS,"DecedentDemographics", ""));
	public static final CodeableConcept deathInvestigationSectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.vrdrDocumentSectionCS,"DeathInvestigation", ""));
	public static final CodeableConcept deathCertificationSectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.vrdrDocumentSectionCS,"DeathCertification", ""));
	public static final CodeableConcept decdentDispositionSectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.vrdrDocumentSectionCS,"DecedentDisposition", ""));
	public static final CodeableConcept deathCertificateDataReviewSectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.vrdrDocumentSectionCS,"death-certificate-data-review", ""));
	public static final CodeableConcept cremationClearanceInfoSectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.vrdrDocumentSectionCS,"cremation-clearance-info", ""));
	public static final String trackingNumberExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-tracking-number";
	public static final CodeableConcept trackingNumberMDIType = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"mdi-case-number","MDI Case Number"));
	public static final CodeableConcept trackingNumberEDRSType = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"edrs-file-number","EDRS File Number"));
	public static final CodeableConcept trackingNumberTOXType = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"tox-lab-case-number","Toxicology Laboratory Case Number"));
	public static final CodeableConcept trackingNumberFHType = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"funeral-home-case-number","Funeral Home Case Number"));
}