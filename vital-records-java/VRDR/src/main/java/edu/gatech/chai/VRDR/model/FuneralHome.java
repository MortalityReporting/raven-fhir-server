package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Organization;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.FuneralHomeUtil;

@ResourceDef(name = "Organization", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-funeral-home")
public class FuneralHome extends Organization {
	public FuneralHome() {
		super();
		CommonUtil.initResource(this);
	}
	public FuneralHome(String name, Address address) {
		this();
		addType(FuneralHomeUtil.type);
		setName(name);
		addAddress(address);
	}
}