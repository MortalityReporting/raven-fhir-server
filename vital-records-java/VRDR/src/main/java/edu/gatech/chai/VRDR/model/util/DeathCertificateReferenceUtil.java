package edu.gatech.chai.VRDR.model.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class DeathCertificateReferenceUtil {
	public static final CodeableConcept type = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl,"64297-5","Death certificate"));
}
