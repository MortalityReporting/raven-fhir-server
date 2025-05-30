package edu.gatech.chai.VRDR.model;

import edu.gatech.chai.VRDR.model.util.CauseOfDeathConditionUtil;
import org.hl7.fhir.r4.model.*;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;

import java.util.List;

@ResourceDef(name = "Bundle", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-cause-of-death-coded-bundle")
public class CauseOfDeathCodedContentBundle extends Bundle {

	/**
	 *
	 */
	private static final long serialVersionUID = 1775205638319513588L;

	public CauseOfDeathCodedContentBundle() {
		super();
		CommonUtil.initResource(this);
		this.setType(BundleType.COLLECTION);
	}

	public CauseOfDeathCodedContentBundle addCertificateNumber(String value) {
		return addExtension("http://hl7.org/fhir/us/vrdr/StructureDefinition/CertificateNumber", value, 6);
	}

	public CauseOfDeathCodedContentBundle addAuxilaryStateIdentifier1 (String value) {
		return addExtension("http://hl7.org/fhir/us/vrdr/StructureDefinition/AuxiliaryStateIdentifier1", value, 12);
	}

	public CauseOfDeathCodedContentBundle addAuxilaryStateIdentifier2 (String value) {
		return addExtension("http://hl7.org/fhir/us/vrdr/StructureDefinition/AuxiliaryStateIdentifier2", value, 12);
	}

	public CauseOfDeathCodedContentBundle addExtension(String system, String value, int maxSize) {
		if(value.length() > maxSize) {
			throw new IllegalArgumentException(
					" Extension " + system + " value " + value + " is too long, must be " + maxSize + " characters or less.");
		}
		Identifier identifier = new Identifier();
		identifier.setSystem(system);
		identifier.setValue(value);
		setIdentifier(identifier);
		return this;
	}

	public void addAutomatedUnderlyingCauseOfDeathICD10Code(String icd10Code) {
		CodeableConcept valueCodeableConcept = CauseOfDeathConditionUtil.createICD10CodeableConcept(icd10Code);
		AutomatedUnderlyingCauseOfDeath automatedUnderlyingCauseOfDeath = new AutomatedUnderlyingCauseOfDeath(valueCodeableConcept);
		BundleEntryComponent entry = addEntry();
		entry.setResource(automatedUnderlyingCauseOfDeath);
	}

	public AutomatedUnderlyingCauseOfDeath getAutomatedUnderlyingCauseOfDeath() {
		return CommonUtil.getSingleResource(AutomatedUnderlyingCauseOfDeath.class, this);
	}

	public String getAutomatedUnderlyingCauseOfDeathCodedValue() {
		return CommonUtil.getCodedValue(getAutomatedUnderlyingCauseOfDeath().getValueCodeableConcept());
	}

	public CodingStatusValues getCodingStatusValues() {
		return CommonUtil.getSingleResource(CodingStatusValues.class, this);
	}

	public MannerOfDeath getMannerOfDeath() {
		return CommonUtil.getSingleResource(MannerOfDeath.class, this);
	}

	public List<RecordAxisCauseOfDeath> getRecordAxisCauseOfDeath() {
		return CommonUtil.getResources(RecordAxisCauseOfDeath.class, this);
	}

	public List<EntityAxisCauseOfDeath> getEntityAxisCauseOfDeath() {
		return CommonUtil.getResources(EntityAxisCauseOfDeath.class, this);
	}
}