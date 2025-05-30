package edu.gatech.chai.VRDR.model;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRCL.model.RelatedPersonMotherVitalRecords;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.DecedentMotherUtil;

@ResourceDef(name = "RelatedPerson", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent-mother")
public class DecedentMother extends RelatedPersonMotherVitalRecords {
	public DecedentMother() {
		super();
		CommonUtil.initResource(this);
		addRelationship(DecedentMotherUtil.code);
	}
}