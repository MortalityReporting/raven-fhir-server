package edu.gatech.chai.MDI.model.resource;

import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.MDI.model.resource.util.DocumentReferenceMDICaseHistoryUtil;

@ResourceDef(name = "DocumentReference", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/DocumentReference-mdi-case-history")
public class DocumentReferenceMDICaseHistory extends DocumentReference{

	public DocumentReferenceMDICaseHistory() {
		super();
	}
	public DocumentReferenceMDICaseHistory(Patient subject) {
		super();
		setType(DocumentReferenceMDICaseHistoryUtil.type);
		addCategory(DocumentReferenceMDICaseHistoryUtil.category);
		setSubject(subject);
	}
	
	public void setPatient(Patient patient) {
		Reference reference = new Reference(patient);
		this.subject = reference;
	}
	
	public void setSubject(Patient patient) {
		setPatient(patient);
	}
}