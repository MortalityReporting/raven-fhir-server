package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Location;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.DeathLocationUtil;

@ResourceDef(name = "Location", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-death-location")
public class DeathLocation extends Location {
	public DeathLocation() {
		super();
		CommonUtil.initResource(this);
		// type is required and values are fixed as per VRDR IG http://hl7.org/fhir/us/vrdr/StructureDefinition-vrdr-death-location.html
		addType(DeathLocationUtil.typeCode);
	}
	public DeathLocation(String name, String description, Address address) {
		this();
		setName(name);
		setDescription(description);
		setAddress(address);
	}

}