package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.AutomatedUnderlyingCauseOfDeathUtil;
import edu.gatech.chai.VRDR.model.util.CommonUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-automated-underlying-cause-of-death")
public class AutomatedUnderlyingCauseOfDeath extends Observation{

	/**
	 *
	 */
	private static final long serialVersionUID = -3974516224741393275L;

	public AutomatedUnderlyingCauseOfDeath() {
		super();
		CommonUtil.initResource(this);
		setCode(AutomatedUnderlyingCauseOfDeathUtil.code);
	}

	public AutomatedUnderlyingCauseOfDeath(CodeableConcept value) {
		this();
		setValue(value);
	}
}
