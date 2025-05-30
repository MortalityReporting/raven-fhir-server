package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.TobaccoUseContributedToDeathUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-tobacco-use-contributed-to-death")
public class TobaccoUseContributedToDeath extends Observation {
	public TobaccoUseContributedToDeath() {
		super();
		setStatus(TobaccoUseContributedToDeathUtil.status);
		setCode(TobaccoUseContributedToDeathUtil.code);
		CommonUtil.initResource(this);
	}

	public TobaccoUseContributedToDeath(String code) {
		this();
		setValue(code);
	}

	public TobaccoUseContributedToDeath(String code, String display) {
		this();
		setValue(code,display);
	}

	public void setValue(String code) {
		CodeableConcept concept = CommonUtil.findConceptFromCollectionUsingSimpleString(code, TobaccoUseContributedToDeathUtil.valueCodesetList);
		if(concept != null) {
			setValue(concept);
		}
	}

	public void setValue(String code, String display) {
		CodeableConcept concept = CommonUtil.findConceptFromCollectionUsingSimpleString(code, TobaccoUseContributedToDeathUtil.valueCodesetList);
		if(concept == null) {
			concept = CommonUtil.findConceptFromCollectionUsingSimpleString(display, TobaccoUseContributedToDeathUtil.valueCodesetList);
		}
		if(concept != null) {
			setValue(concept);
		}
	}
}