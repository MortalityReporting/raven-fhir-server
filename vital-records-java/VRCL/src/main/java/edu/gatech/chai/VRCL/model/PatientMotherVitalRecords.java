package edu.gatech.chai.VRCL.model;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.USCore.model.util.CommonUtil;

@ResourceDef(name = "Patient", profile = "http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Patient-mother-vr")
public class PatientMotherVitalRecords extends PatientVitalRecords {

	public PatientMotherVitalRecords() {
		super();
		CommonUtil.initResource(this);
	}

	
}