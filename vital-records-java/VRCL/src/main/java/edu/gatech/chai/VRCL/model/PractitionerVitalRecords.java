package edu.gatech.chai.VRCL.model;

import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.USCore.model.USCorePractitioner;
import edu.gatech.chai.USCore.model.util.CommonUtil;

@ResourceDef(name = "Practitioner", profile = "http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Practitioner-vr")
public class PractitionerVitalRecords extends USCorePractitioner {

	public PractitionerVitalRecords() {
		super();
		CommonUtil.initResource(this);
	}

	public PractitionerVitalRecords(Identifier identifier, HumanName name) {
		super();
		CommonUtil.initResource(this);
		addIdentifier(identifier);
		addName(name);
	}

}