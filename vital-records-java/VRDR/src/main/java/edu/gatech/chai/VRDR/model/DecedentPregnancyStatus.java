package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.DecedentPregnancyStatusUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent-pregnancy-status")
public class DecedentPregnancyStatus extends Observation {
	/**
	 *
	 */
	private static final long serialVersionUID = 910393233068767768L;

	public DecedentPregnancyStatus() {
		super();
		CommonUtil.initResource(this);
		setStatus(DecedentPregnancyStatusUtil.status);
		setCode(DecedentPregnancyStatusUtil.code);
	}
	public DecedentPregnancyStatus(CodeableConcept value) {
		this();
		setValue(value);
	}

	public DecedentPregnancyStatus(String code) {
		this();
		setValue(code);
	}

	public DecedentPregnancyStatus(String code, String display) {
		this();
		setValue(code,display);
	}


	public void setValue(String code) {
		CodeableConcept concept = CommonUtil.findConceptFromCollectionUsingSimpleString(code, DecedentPregnancyStatusUtil.valueSet);
		setValue(concept);
	}

	public void setValue(String code, String display) {
		CodeableConcept concept = new CodeableConcept().addCoding(new Coding().setCode(code).setSystem(DecedentPregnancyStatusUtil.codeValueSystem).setDisplay(display));
		setValue(concept);
	}

}