package edu.gatech.chai.VRCL.model;

import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.USCore.model.util.CommonUtil;

@ResourceDef(name = "RelatedPerson", profile = "http://hl7.org/fhir/us/vr-common-library/StructureDefinition/RelatedPerson-father-vr")
public class RelatedPersonFatherVitalRecords extends RelatedPersonParentVitalRecords {

	public RelatedPersonFatherVitalRecords() {
		super();
		CommonUtil.initResource(this);
	}

	public RelatedPersonFatherVitalRecords(Reference patient, boolean active,HumanName name) {
		super();
		CommonUtil.initResource(this);
		setPatient(patient);
		setActive(active);
		addName(name);
	}

}