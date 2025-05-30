package edu.gatech.chai.MDI.model.resource.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;


public class ObservationToxicologyLabResultUtil {
	public static final CodeableConcept category = new CodeableConcept().addCoding(
			new Coding("http://terminology.hl7.org/CodeSystem/observation-category","laboratory",""));
	public static final String agencyCaseHistoryNotesExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-agency-case-history-notes";
}
