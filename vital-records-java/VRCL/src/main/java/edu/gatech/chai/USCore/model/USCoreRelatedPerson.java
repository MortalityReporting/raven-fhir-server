package edu.gatech.chai.USCore.model;

import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.RelatedPerson;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.USCore.model.util.CommonUtil;

@ResourceDef(name = "RelatedPerson", profile = "http://hl7.org/fhir/us/core/StructureDefinition/us-core-relatedperson")
public class USCoreRelatedPerson extends RelatedPerson {

	public USCoreRelatedPerson() {
		super();
		CommonUtil.initResource(this);
	}

	public USCoreRelatedPerson(Reference patient, boolean active) {
		super();
		CommonUtil.initResource(this);
		this.setPatient(patient);
		this.setActive(active);
	}

}