package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Location;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;

@ResourceDef(name = "Location", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-disposition-location")
public class DispositionLocation extends Location {
	public DispositionLocation() {
		super();
		CommonUtil.initResource(this);
	}
	public DispositionLocation(Address address) {
		this();
		setAddress(address);
	}
}
