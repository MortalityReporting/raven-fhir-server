package edu.gatech.chai.VRDR.model.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

public class DecedentPregnancyStatusUtil {
	public static final Observation.ObservationStatus status = ObservationStatus.FINAL;
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding().setSystem(CommonUtil.loincSystemUrl).setCode("69442-2")
					.setDisplay("Timing of recent pregnancy in relation to death"));
	public static final String codeValueSystem = "http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-pregnancy-status-cs";
	public static final CodeableConcept VALUE_NOCODE = new CodeableConcept().addCoding(new Coding(codeValueSystem, "1", "Not pregnant within the past year"));
	public static final CodeableConcept VALUE_YESCODE = new CodeableConcept().addCoding(new Coding(codeValueSystem, "2", "Pregnant at time of death"));
	public static final CodeableConcept VALUE_42DAYSCODE = new CodeableConcept().addCoding(new Coding(codeValueSystem, "3", "Not pregnant, but pregnant within 42 days of death"));
	public static final CodeableConcept VALUE_1YEARCODE = new CodeableConcept().addCoding(new Coding(codeValueSystem, "4", "Not pregnant, but pregnant 43 days to 1 year before death"));
	public static final CodeableConcept VALUE_NOTREPORTEDCODE =new CodeableConcept().addCoding(new Coding(codeValueSystem, "7", "Not reported on certificate"));
	public static final CodeableConcept VALUE_UNKNOWNCODE =new CodeableConcept().addCoding(new Coding(codeValueSystem, "9", "Unknown if pregnant within the past year"));
	public static final HashSet<CodeableConcept> valueSet = new HashSet<>(Arrays.asList(
			VALUE_NOCODE,
			VALUE_YESCODE,
			VALUE_42DAYSCODE,
			VALUE_1YEARCODE,
			VALUE_NOTREPORTEDCODE,
			VALUE_UNKNOWNCODE,
			CommonUtil.notApplicableCode
	));
}