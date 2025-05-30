package edu.gatech.chai.VRDR.model;

import edu.gatech.chai.VRDR.model.util.CauseOfDeathConditionUtil;
import org.hl7.fhir.r4.model.*;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.EntityAxisCauseOfDeathUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-entity-axis-cause-of-death")
public class EntityAxisCauseOfDeath extends Observation {

	/**
	 *
	 */
	private static final long serialVersionUID = 1775205638319513588L;

	public EntityAxisCauseOfDeath() {
		super();
		CommonUtil.initResource(this);
		this.setCode(EntityAxisCauseOfDeathUtil.code);
	}

	public EntityAxisCauseOfDeath(Decedent decedent) {
		this();
		setSubject(new Reference(decedent));
	}

	public Observation addLineNumber(int lineNumber) {
		return addComponent(EntityAxisCauseOfDeathUtil.lineNumberComponentCode,new IntegerType(lineNumber));
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

	public Integer getLineNumber() {
		return CommonUtil.findObservationComponentComponentValueForCoding(getComponent(), IntegerType.class,
				EntityAxisCauseOfDeathUtil.lineNumberComponentCode.getCodingFirstRep());
	}

	public Boolean isECodeIndicator() {
		return CommonUtil.findObservationComponentComponentValueForCoding(getComponent(), BooleanType.class,
				EntityAxisCauseOfDeathUtil.eCodeIndicatorComponentCode.getCodingFirstRep());
	}

}