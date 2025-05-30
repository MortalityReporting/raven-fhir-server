package edu.gatech.chai.MDI.model.resource;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.MDI.model.resource.util.CommonUtil;
import edu.gatech.chai.MDI.model.resource.util.ObservationHowDeathInjuryOccurredUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/Observation-how-death-injury-occurred")
public class ObservationHowDeathInjuryOccurred extends Observation {

	public ObservationHowDeathInjuryOccurred() {
		super();
		setCode(ObservationHowDeathInjuryOccurredUtil.code);
	}

	public ObservationHowDeathInjuryOccurred(Patient subject, String value) {
		this();
		Reference ref = new Reference(subject);
		setStatus(ObservationStatus.FINAL);
		setSubject(ref);
		setValue(new CodeableConcept().setText(value));
	}
	
	public ObservationHowDeathInjuryOccurred(Patient subject, Practitioner performer, String value) {
		this();
		Reference ref = new Reference(subject);
		setSubject(ref);
		ref = new Reference(subject);
		addPerformer(ref);
		setValue(new CodeableConcept().setText(value));
	}

	public ObservationHowDeathInjuryOccurred(Patient subject, Practitioner performer, String value, String placeOfDeath, String workInjuryIndicator, String transportationRole) {
		this();
		Reference ref = new Reference(subject);
		setSubject(ref);
		ref = new Reference(subject);
		addPerformer(ref);
		setValue(new CodeableConcept().setText(value));
		addPlaceOfInjury(placeOfDeath);
		addWorkInjuryIndicator(workInjuryIndicator);
		addTransportationRole(transportationRole);
	}

	public ObservationComponentComponent addPlaceOfInjury(String placeOfDeath){
		ObservationComponentComponent occ = new ObservationComponentComponent();
		occ.setCode(ObservationHowDeathInjuryOccurredUtil.placeOfInjuryComponentCode);
		occ.setValue(new CodeableConcept().setText(placeOfDeath));
		this.addComponent(occ);
		return occ;
	}
	
	public ObservationComponentComponent addWorkInjuryIndicator(String yesNoNA){
		ObservationComponentComponent occ = new ObservationComponentComponent();
		occ.setCode(ObservationHowDeathInjuryOccurredUtil.workInjuryComponentCode);
		CodeableConcept valueCodeableConcept = CommonUtil.findConceptFromCollectionUsingSimpleString(yesNoNA, CommonUtil.yesNoUnknownNASKSet);
		occ.setValue(valueCodeableConcept);
		this.addComponent(occ);
		return occ;
	}

	public ObservationComponentComponent addTransportationRole(String transportationRole){
		ObservationComponentComponent occ = new ObservationComponentComponent();
		occ.setCode(ObservationHowDeathInjuryOccurredUtil.transportationRoleComponentCode);
		CodeableConcept valueCodeableConcept = CommonUtil.findConceptFromCollectionUsingSimpleString(transportationRole, ObservationHowDeathInjuryOccurredUtil.transportationRoleValueSet);
		occ.setValue(valueCodeableConcept);
		this.addComponent(occ);
		return occ;
	}
}