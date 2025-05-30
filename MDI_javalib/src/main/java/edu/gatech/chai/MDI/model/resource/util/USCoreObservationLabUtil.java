package edu.gatech.chai.MDI.model.resource.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class USCoreObservationLabUtil {
	public static final String hl7ObservationCategoryURL = "http://terminology.hl7.org/CodeSystem/observation-category";
	public static final CodeableConcept category = new CodeableConcept().addCoding(
			new Coding(hl7ObservationCategoryURL,"laboratory","laboratory"));
}