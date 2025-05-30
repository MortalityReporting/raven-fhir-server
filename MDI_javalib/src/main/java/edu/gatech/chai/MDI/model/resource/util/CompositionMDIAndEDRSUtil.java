package edu.gatech.chai.MDI.model.resource.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class CompositionMDIAndEDRSUtil {
	public static final CodeableConcept type = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"86807-5","Death administrative information Document"));
	public static final CodeableConcept demographicsSectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"demographics", ""));
	public static final CodeableConcept circumstancesSectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"circumstances",""));
	public static final CodeableConcept jurisdictionSectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"jurisdiction",""));
	public static final CodeableConcept causeMannerSectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"cause-manner",""));
	public static final CodeableConcept medicalHistorySectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"medical-history",""));
	public static final CodeableConcept examAutopsySectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"exam-autopsy",""));
	public static final CodeableConcept narrativeSectionCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"narratives",""));
	public static final String trackingNumberExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-tracking-number";
	public static final CodeableConcept trackingNumberMDIType = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"mdi-case-number","MDI Case Number"));
	public static final CodeableConcept trackingNumberEDRSType = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"edrs-file-number","EDRS File Number"));
	public static final CodeableConcept trackingNumberTOXType = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"tox-lab-case-number","Toxicology Laboratory Case Number"));
}