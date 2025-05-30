package edu.gatech.chai.MDI.model.resource.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class ODHUsualWorkUtil {
	public static final CodeableConcept category = new CodeableConcept().addCoding(
			new Coding("http://terminology.hl7.org/CodeSystem/observation-category","social-history","social-history"));
	public static final CodeableConcept code = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"21843-8","History of Usual occupation"));
	public static final CodeableConcept usualIndustryComponentCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"21844-6","History of Usual industry"));
	public static final CodeableConcept occupationDurationComponentCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"74163-7","Usual occupation duration"));
}