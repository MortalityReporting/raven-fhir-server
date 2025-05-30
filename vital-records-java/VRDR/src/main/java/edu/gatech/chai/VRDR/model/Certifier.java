package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Practitioner;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRCL.model.PractitionerVitalRecords;
import edu.gatech.chai.VRDR.model.util.CommonUtil;

@ResourceDef(name = "Practitioner", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-certifier")
public class Certifier extends PractitionerVitalRecords {

	public Certifier() {
		super();
		CommonUtil.initResource(this);
	}

	public PractitionerQualificationComponent addQualification(String identifier, String type) {
		CodeableConcept identifierTypeCodeableConcept = CommonUtil.findConceptFromCollectionUsingSimpleString(type, CommonUtil.certifierTypeSet);
		PractitionerQualificationComponent component = new PractitionerQualificationComponent(identifierTypeCodeableConcept);
		component.addIdentifier(new Identifier().setValue(identifier));
		addQualification(component);
		return component;
	}
}