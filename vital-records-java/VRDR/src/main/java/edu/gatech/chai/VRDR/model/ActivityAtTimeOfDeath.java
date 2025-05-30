package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.ActivityAtTimeOfDeathUtil;
import edu.gatech.chai.VRDR.model.util.CommonUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-activity-at-time-of-death")
public class ActivityAtTimeOfDeath extends Observation{
	/**
	 *
	 */
	private static final long serialVersionUID = 343890908919147936L;

	public ActivityAtTimeOfDeath() {
		super();
		CommonUtil.initResource(this);
		setCode(ActivityAtTimeOfDeathUtil.code);
	}

	public ActivityAtTimeOfDeath(CodeableConcept value) {
		this();
		setValue(value);
	}

	public ActivityAtTimeOfDeath(String value) {
		this();
		CodeableConcept valueCode = CommonUtil.findConceptFromCollectionUsingSimpleString(value, ActivityAtTimeOfDeathUtil.valueSet);
		setValue(valueCode);
	}
}
