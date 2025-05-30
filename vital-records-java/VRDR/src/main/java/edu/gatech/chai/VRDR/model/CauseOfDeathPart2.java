package edu.gatech.chai.VRDR.model;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.StringType;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.ConditionContributingToDeathUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-cause-of-death-part2")
public class CauseOfDeathPart2 extends Observation {
	public CauseOfDeathPart2() {
		super();
		CommonUtil.initResource(this);
		this.setCode(ConditionContributingToDeathUtil.code);
		this.setPerformer(new ArrayList<Reference>());
	}

	public CauseOfDeathPart2(Decedent decedent, Certifier certifier, String value) {
		super();
		CommonUtil.initResource(this);
		this.setCode(ConditionContributingToDeathUtil.code);
		setDecedent(decedent);
		setPerformer(certifier);
		setValue(value);
	}

	public void setDecedent(Decedent decedent) {
		Reference reference = new Reference(decedent.getId());
		this.subject = reference;
	}

	public void setSubject(Decedent decedent) {
		setDecedent(decedent);
	}

	public Reference getDecedent() {
		return subject;
	}

	public void setCertifier(Certifier certifier) {
		Reference reference = new Reference(certifier.getId());
		this.performer.add(reference);
	}
	public void setAsserter(Certifier certifier) {
		setCertifier(certifier);
	}
	public void setPerformer(Certifier certifier) {
		setCertifier(certifier);
	}

	public List<Reference> getCertifiers(int index) {
		return performer;
	}

	public List<Reference> getAsserters(int index) {
		return performer;
	}

	public List<Reference> getPerformers(int index) {
		return performer;
	}

	public Reference getCertifier(int index) {
		return performer.get(index);
	}

	public void setValue(StringType value) {
		if((value.getValue().length() > 240)) {
			throw new IllegalArgumentException("CauseOfDeathPart1 value "+value.getValue()+" is too long, must be 240 characters or less.");
		}
		this.setValue(value);
	}

	public void setValue(String value) {
		setValue(new StringType(value));
	}
}