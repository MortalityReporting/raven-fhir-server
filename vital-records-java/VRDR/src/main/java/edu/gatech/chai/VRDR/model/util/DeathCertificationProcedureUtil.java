package edu.gatech.chai.VRDR.model.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class DeathCertificationProcedureUtil {
	public static final CodeableConcept categoryFixedValue = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.snomedSystemUrl,"103693007","Diagnostic procedure"));
	public static final CodeableConcept codeFixedValue = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.snomedSystemUrl,"308646001","Death certification"));

}