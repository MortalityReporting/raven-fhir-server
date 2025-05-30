package edu.gatech.chai.VRDR.model.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class ActivityAtTimeOfDeathUtil {
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "80626-5", "Activity at time of death [CDC]"));
	public static final String valueSetSystemUrl = "http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-activity-at-time-of-death-cs";
	public static final HashSet<CodeableConcept> valueSet = new HashSet<>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(valueSetSystemUrl,"0","While engaged in sports activity")),
			new CodeableConcept().addCoding(new Coding(valueSetSystemUrl,"1","While engaged in leisure activities.")),
			new CodeableConcept().addCoding(new Coding(valueSetSystemUrl,"2","While working for income")),
			new CodeableConcept().addCoding(new Coding(valueSetSystemUrl,"3","While engaged in other types of work")),
			new CodeableConcept().addCoding(new Coding(valueSetSystemUrl,"4","While resting, sleeping, eating, or engaging in other vital activities")),
			new CodeableConcept().addCoding(new Coding(valueSetSystemUrl,"8","While engaged in other specified activities.")),
			new CodeableConcept().addCoding(new Coding(valueSetSystemUrl,"9","During unspecified activity")),
			CommonUtil.unknownCode
			));
}
