package edu.gatech.chai.VRDR.model;

import java.util.Date;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.DeathCertificationProcedureUtil;

@ResourceDef(name = "Procedure", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-death-certification")
public class DeathCertificationProcedure extends Procedure {

	public DeathCertificationProcedure() {
		super();
		CommonUtil.initResource(this);
		setStatus(ProcedureStatus.COMPLETED);
		setCategory(DeathCertificationProcedureUtil.categoryFixedValue);
		setCode(DeathCertificationProcedureUtil.codeFixedValue);
	}

	public DeathCertificationProcedure(Date performed) {
		this();
		this.setPerformed(new DateTimeType(performed));
	}

	public void addPerformer(Certifier certifier, CodeableConcept function) {
		ProcedurePerformerComponent procedurePerformerComponent = new ProcedurePerformerComponent();
		procedurePerformerComponent.setActor(new Reference(certifier.getId()));
		procedurePerformerComponent.setFunction(function);
		this.addPerformer(procedurePerformerComponent);
	}

	public void addPerformer(Certifier certifier, String function) {
		CodeableConcept functionCode = CommonUtil.findConceptFromCollectionUsingSimpleString(function, CommonUtil.certifierTypeSet);
		this.addPerformer(certifier, functionCode);
	}

}