package edu.gatech.chai.MDI.model.resource.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class ObservationConditionCauseOfDeathUtil {
	public static final CodeableConcept code = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"69453-9", ""));
	public static final CodeableConcept lineNumberComponentCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.vrdrLocalComponentCS,"lineNumber", ""));
	public static final CodeableConcept intervalComponentCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"69440-6", ""));
}