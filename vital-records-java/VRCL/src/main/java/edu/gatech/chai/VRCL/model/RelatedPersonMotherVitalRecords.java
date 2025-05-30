package edu.gatech.chai.VRCL.model;

import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.USCore.model.util.CommonUtil;

@ResourceDef(name = "RelatedPerson", profile = "http://hl7.org/fhir/us/vr-common-library/StructureDefinition/RelatedPerson-mother-vr")
public class RelatedPersonMotherVitalRecords extends RelatedPersonParentVitalRecords {

	public RelatedPersonMotherVitalRecords() {
		super();
		CommonUtil.initResource(this);
	}

	public RelatedPersonMotherVitalRecords(Reference patient, boolean active,HumanName name) {
		super();
		CommonUtil.initResource(this);
		setPatient(patient);
		setActive(active);
		addName(name);
	}

}