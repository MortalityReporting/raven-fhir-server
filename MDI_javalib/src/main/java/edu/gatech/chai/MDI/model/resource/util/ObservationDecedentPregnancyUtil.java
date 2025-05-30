package edu.gatech.chai.MDI.model.resource.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class ObservationDecedentPregnancyUtil {
	public static final String preganancySystemUrl = "http://hl7.org/fhir/us/mdi/CodeSystem/CodeSystem-death-pregnancy-status";
	public static final CodeableConcept code = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"69442-2","Timing of recent pregnancy in relation to death"));
	public static final CodeableConcept VALUE_NOCODE = new CodeableConcept().addCoding(new Coding().setCode("1").setSystem(preganancySystemUrl).setDisplay("Not pregnant within past year"));
	public static final CodeableConcept VALUE_YESCODE = new CodeableConcept().addCoding(new Coding().setCode("2").setSystem(preganancySystemUrl).setDisplay("Pregnant at the time of death"));
	public static final CodeableConcept VALUE_42DAYSCODE = new CodeableConcept().addCoding(new Coding().setCode("3").setSystem(preganancySystemUrl).setDisplay("Not pregnant, but pregnant within 42 days of death"));
	public static final CodeableConcept VALUE_1YEARCODE = new CodeableConcept().addCoding(new Coding().setCode("4").setSystem(preganancySystemUrl).setDisplay("Not pregnant, but pregnant 43 days to 1 year before death"));
	public static final CodeableConcept VALUE_UNKNOWNCODE = new CodeableConcept().addCoding(new Coding().setCode("9").setSystem(preganancySystemUrl).setDisplay("Unknown if pregnant within the past year"));
	public static final HashSet<CodeableConcept> valueSet = new HashSet<>(Arrays.asList(
			VALUE_NOCODE,VALUE_YESCODE,VALUE_42DAYSCODE,VALUE_1YEARCODE,VALUE_UNKNOWNCODE,CommonUtil.notApplicableCode));
}