package edu.gatech.chai.VRDR.model.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class SurgeryDateUtil {
	public static final CodeableConcept code = new CodeableConcept().addCoding(new Coding(CommonUtil.loincSystemUrl, "80992-1", "Date and time of surgery"));
}