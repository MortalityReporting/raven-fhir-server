package edu.gatech.chai.VRCL.model;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Location;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.USCore.model.util.CommonUtil;

@ResourceDef(name = "Location", profile = "http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Location-vr")
public class LocationVitalRecords extends Location {
	public LocationVitalRecords() {
		super();
		CommonUtil.initResource(this);
	}
	public LocationVitalRecords(String name, String description, Address address) {
		this();
		setName(name);
		setDescription(description);
		setAddress(address);
	}

}