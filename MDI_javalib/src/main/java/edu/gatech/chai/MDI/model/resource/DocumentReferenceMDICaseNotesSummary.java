package edu.gatech.chai.MDI.model.resource;

import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.MDI.model.resource.util.DocumentReferenceMDICaseNotesSummaryUtil;

@ResourceDef(name = "DocumentReference", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/DocumentReference-mdi-case-notes-summary")
public class DocumentReferenceMDICaseNotesSummary extends DocumentReference{
	public DocumentReferenceMDICaseNotesSummary() {
		super();
	}

	public DocumentReferenceMDICaseNotesSummary(Patient subject) {
		this.setType(DocumentReferenceMDICaseNotesSummaryUtil.type);
		this.addCategory(DocumentReferenceMDICaseNotesSummaryUtil.category);
	}
	
	public void setPatient(Patient patient) {
		Reference reference = new Reference(patient);
		this.subject = reference;
	}
	
	public void setSubject(Patient patient) {
		setPatient(patient);
	}
}