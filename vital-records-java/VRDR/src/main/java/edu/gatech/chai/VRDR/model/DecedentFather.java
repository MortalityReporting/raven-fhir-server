package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.RelatedPerson;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRCL.model.RelatedPersonFatherVitalRecords;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.DecedentFatherUtil;

@ResourceDef(name = "RelatedPerson", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent-father")
public class DecedentFather extends RelatedPersonFatherVitalRecords {
	public DecedentFather() {
		super();
		CommonUtil.initResource(this);
		addRelationship(DecedentFatherUtil.code);
	}
}
