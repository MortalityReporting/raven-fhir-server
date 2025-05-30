package edu.gatech.chai.VRDR.model.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class ManualUnderlyingCauseOfDeathUtil {
	public static final CodeableConcept code = new CodeableConcept().addCoding(new Coding(CommonUtil.loincSystemUrl, "80359-3", "Cause of death.underlying [Manual]"));
}