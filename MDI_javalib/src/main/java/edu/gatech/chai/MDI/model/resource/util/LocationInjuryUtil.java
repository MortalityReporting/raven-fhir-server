package edu.gatech.chai.MDI.model.resource.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class LocationInjuryUtil {
	public static final CodeableConcept type = new CodeableConcept().addCoding(
			new Coding(CommonUtil.mdiCodesSystemURL,"injury",""));
}
