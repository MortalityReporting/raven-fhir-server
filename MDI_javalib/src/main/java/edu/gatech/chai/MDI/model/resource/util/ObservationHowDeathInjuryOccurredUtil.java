package edu.gatech.chai.MDI.model.resource.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class ObservationHowDeathInjuryOccurredUtil {
	public static final CodeableConcept code = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"11374-6","Injury incident description Narrative"));
	public static final CodeableConcept placeOfInjuryComponentCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"69450-5","Place of injury Facility"));
	public static final CodeableConcept workInjuryComponentCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"69444-8","Did death result from injury at work"));
	public static final CodeableConcept transportationRoleComponentCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"69451-3","Transportation role of decedent"));

	public static final HashSet<CodeableConcept> transportationRoleValueSet = new HashSet<>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl, "236320001", "Vehicle driver")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl, "257500003", "Passenger")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl, "257518000", "Pedestrian")),
			CommonUtil.otherCode,
			CommonUtil.unknownCode,
			CommonUtil.notApplicableCode));
}