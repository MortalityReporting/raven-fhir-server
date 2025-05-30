package edu.gatech.chai.MDI.model.resource.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;


public class DiagnosticReportToxicologyToMDIUtil {
	public static final CodeableConcept category = new CodeableConcept().addCoding(
			new Coding("http://terminology.hl7.org/CodeSystem/v2-0074","LAB","Laboratory"));
	public static final String trackingNumberExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-tracking-number";
	public static final String agencyCaseHistoryNotesExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-agency-case-history-notes";
}
