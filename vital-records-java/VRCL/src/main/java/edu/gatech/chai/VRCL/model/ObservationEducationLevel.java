package edu.gatech.chai.VRCL.model;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.USCore.model.util.CommonUtil;
import edu.gatech.chai.VRCL.model.util.ObservationEducationLevelUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Observation-education-level-vr")
public class ObservationEducationLevel extends Observation {
	public ObservationEducationLevel() {
		super();
		CommonUtil.initResource(this);
		setStatus(ObservationEducationLevelUtil.status);
		setCode(ObservationEducationLevelUtil.code);
	}
	
	public ObservationEducationLevel(String code, Reference subject) {
		this();
		setValue(code);
		setSubject(subject);
	}
	
	public ObservationEducationLevel(String code, String display, Reference subject) {
		this();
		setValue(code,display);
		setSubject(subject);
	}
	
	public void setValue(String code) {
		CodeableConcept concept = CommonUtil.findConceptFromCollectionUsingSimpleString(code, ObservationEducationLevelUtil.valueSet);
		if(concept != null) {
			setValue(concept);
		}
	}
	
	public void setValue(String code, String display) {
		CodeableConcept concept = CommonUtil.findConceptFromCollectionUsingSimpleString(code, ObservationEducationLevelUtil.valueSet);
		if(concept == null) {
			concept = CommonUtil.findConceptFromCollectionUsingSimpleString(display, ObservationEducationLevelUtil.valueSet);
		}
		if(concept != null) {
			setValue(concept);
		}
	}
}
