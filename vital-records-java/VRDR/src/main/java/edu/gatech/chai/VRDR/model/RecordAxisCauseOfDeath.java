package edu.gatech.chai.VRDR.model;

import edu.gatech.chai.VRDR.model.util.*;
import org.hl7.fhir.r4.model.*;

import ca.uhn.fhir.model.api.annotation.ResourceDef;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-record-axis-cause-of-death")
public class RecordAxisCauseOfDeath extends Observation {

	private static final long serialVersionUID = 1775205638319513588L;

	public RecordAxisCauseOfDeath() {
		super();
		CommonUtil.initResource(this);
		this.setCode(RecordAxisCauseOfDeathUtil.code);
	}

	public RecordAxisCauseOfDeath(Decedent decedent) {
		this();
		setSubject(new Reference(decedent));
	}

	public Observation addPosition(int position) {
		return addComponent(EntityAxisCauseOfDeathUtil.positionComponentCode,new IntegerType(position));
	}

	public Observation addECodeIndicator(boolean eCodeIndicator) {
		return addComponent(EntityAxisCauseOfDeathUtil.positionComponentCode,new BooleanType(eCodeIndicator));
	}

	public Observation addComponent(CodeableConcept code, Type value) {
		ObservationComponentComponent occ = new ObservationComponentComponent();
		occ.setCode(code);
		occ.setValue(value);
		addComponent(occ);
		return this;
	}


	public void setICD10Code(String icd10Code) {
		CodeableConcept valueCodeableConcept = CauseOfDeathConditionUtil.createICD10CodeableConcept(icd10Code);
		this.setValue(valueCodeableConcept);
	}

	public String getValueCodeAsString() {
		return getValueCodeableConcept() == null ? null : getValueCodeableConcept().getCodingFirstRep().getCode();
	}

	public Integer getPosition() {
		return CommonUtil.findObservationComponentComponentValueForCoding(getComponent(), IntegerType.class,
				EntityAxisCauseOfDeathUtil.positionComponentCode.getCodingFirstRep());
	}

}