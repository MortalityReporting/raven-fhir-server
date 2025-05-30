package edu.gatech.chai.VRDR.model.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class DecedentSpouseUtil {
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding("http://terminology.hl7.org/CodeSystem/v3-RoleCode", "SPS", "spouse"));
}