package edu.gatech.chai.VRDR.model.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class FuneralHomeUtil {
	// fixed value as per VRDR IG http://hl7.org/fhir/us/vrdr/StructureDefinition-vrdr-funeral-home.html
	// and http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-organization-type-cs
	public static final CodeableConcept type = new CodeableConcept()
			.addCoding(new Coding().setSystem("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-organization-type-cs")
					.setCode("funeralhome")
					.setDisplay("Funeral Home"));
}