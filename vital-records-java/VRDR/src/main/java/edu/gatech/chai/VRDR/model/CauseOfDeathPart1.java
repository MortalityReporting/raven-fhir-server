package edu.gatech.chai.VRDR.model;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.StringType;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CauseOfDeathConditionUtil;
import edu.gatech.chai.VRDR.model.util.CommonUtil;

//NOTE: While the resource name is "CauseOfDeathPart1", it actually derived from Observation
@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-cause-of-death-part1")
public class CauseOfDeathPart1 extends Observation {
	public CauseOfDeathPart1() {
		super();
		CommonUtil.initResource(this);
		this.setCode(CauseOfDeathConditionUtil.code);
		this.setPerformer(new ArrayList<Reference>());
	}

	public CauseOfDeathPart1(Decedent decedent, Certifier certifier, String value, Quantity interval) {
		super();
		CommonUtil.initResource(this);
		this.setCode(CauseOfDeathConditionUtil.code);
		setDecedent(decedent);
		setPerformer(certifier);
		setValue(value);
		createInterval(interval);
	}

	public CauseOfDeathPart1(Decedent decedent, Certifier certifier, String value, String interval) {
		super();
		CommonUtil.initResource(this);
		this.setCode(CauseOfDeathConditionUtil.code);
		setDecedent(decedent);
		setPerformer(certifier);
		setValue(value);
		createInterval(interval);
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
		if((value.getValue().length() > 120)) {
			throw new IllegalArgumentException("CauseOfDeathPart1 value "+value.getValue()+" is too long, must be 120 characters or less.");
		}
		super.setValue(value);
	}

	public void setValue(String value) {
		setValue(new StringType(value));
	}

	public void createInterval(Quantity quantity) {
		ObservationComponentComponent component = new ObservationComponentComponent();
		component.setCode(CauseOfDeathConditionUtil.intervalComponentCode);
		component.setValue(quantity);

	}

	public void createInterval(String string) {
		ObservationComponentComponent component = new ObservationComponentComponent();
		component.setCode(CauseOfDeathConditionUtil.intervalComponentCode);
		component.setValue(new StringType(string));

	}

}