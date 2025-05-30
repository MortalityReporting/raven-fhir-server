package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.RelatedPerson;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.DecedentSpouseUtil;

@ResourceDef(name = "RelatedPerson", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent-spouse")
public class DecedentSpouse extends RelatedPerson {
	public DecedentSpouse() {
		super();
		CommonUtil.initResource(this);
		addRelationship(DecedentSpouseUtil.code);
	}
}