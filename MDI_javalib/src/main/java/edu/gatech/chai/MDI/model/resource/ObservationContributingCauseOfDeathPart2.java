package edu.gatech.chai.MDI.model.resource;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.MDI.model.resource.util.ObservationConditionContributingToDeathUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/Observation-contributing-cause-of-death-part2")
public class ObservationContributingCauseOfDeathPart2 extends Observation {
	public ObservationContributingCauseOfDeathPart2() {
		super();
		this.setCode(ObservationConditionContributingToDeathUtil.code);
	}
	
	public ObservationContributingCauseOfDeathPart2(Patient patient, Practitioner practitioner, String value) {
		this();
		setPatient(patient);
		setPractitioner(practitioner);
		setValue(new CodeableConcept().setText(value));
	}
	
	public void setPatient(Patient patient) {
		Reference reference = new Reference(patient);
		this.subject = reference;
	}
	
	public void setSubject(Patient patient) {
		setPatient(patient);
	}
	
	public Reference getSubject() {
		return subject;
	}
	
	public void setPractitioner(Practitioner practitioner) {
		Reference reference = new Reference(practitioner);
		this.addPerformer(reference);
	}
	public void setPerformer(Practitioner practitioner) {
		setPractitioner(practitioner);
	}
	
	public Reference getPractitioner() {
		return this.performer.get(0);
	}
}
