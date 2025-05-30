package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.DecedentDispositionMethodUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent-disposition-method")
public class DecedentDispositionMethod extends Observation {
	public DecedentDispositionMethod() {
		super();
		CommonUtil.initResource(this);
		setStatus(DecedentDispositionMethodUtil.status);
		setCode(DecedentDispositionMethodUtil.code);
	}
	public DecedentDispositionMethod(CodeableConcept value) {
		this();
		setValue(value);
	}

	public DecedentDispositionMethod(String code) {
		this();
		setValue(code);
	}

	public DecedentDispositionMethod(String code, String display) {
		this();
		setValue(code,display);
	}

	public void setValue(String code) {
		CodeableConcept concept = CommonUtil.findConceptFromCollectionUsingSimpleString(code, DecedentDispositionMethodUtil.valueCodesetList);
		if(concept != null) {
			setValue(concept);
		}
	}

	public void setValue(String code, String display) {
		CodeableConcept concept = CommonUtil.findConceptFromCollectionUsingSimpleString(code, DecedentDispositionMethodUtil.valueCodesetList);
		if(concept == null) {
			concept = CommonUtil.findConceptFromCollectionUsingSimpleString(display, DecedentDispositionMethodUtil.valueCodesetList);
		}
		if(concept != null) {
			setValue(concept);
		}
	}
}
