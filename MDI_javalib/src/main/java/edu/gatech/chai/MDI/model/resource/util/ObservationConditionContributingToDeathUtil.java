package edu.gatech.chai.MDI.model.resource.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class ObservationConditionContributingToDeathUtil {
	public static final CodeableConcept code = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"69441-4", "Other significant causes or conditions of death"));
}
