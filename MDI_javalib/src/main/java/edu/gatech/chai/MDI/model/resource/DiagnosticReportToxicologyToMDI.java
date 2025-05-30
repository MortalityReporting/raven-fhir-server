package edu.gatech.chai.MDI.model.resource;

import java.util.Date;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.MDI.model.resource.util.DiagnosticReportToxicologyToMDIUtil;

@ResourceDef(name = "DiagnosticReport", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/DiagnosticReport-toxicology-to-mdi")
public class DiagnosticReportToxicologyToMDI extends DiagnosticReport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4006245721303024706L;

	public DiagnosticReportToxicologyToMDI() {
		super();
	}
	
	public DiagnosticReportToxicologyToMDI(DiagnosticReportStatus status, Patient subject, CodeableConcept code, Date effective, Date issued) {
		super();
		this.setStatus(status);
		this.setCode(code);
		this.setSubject(new Reference(subject));
		this.setEffective(new DateTimeType(effective));
		this.setIssued(issued);
		this.addCategory(DiagnosticReportToxicologyToMDIUtil.category);
	}
	
	public void addTrackingNumberExtension(Identifier identifier) {
		Extension extension = new Extension(DiagnosticReportToxicologyToMDIUtil.trackingNumberExtensionURL);
		extension.setValue(identifier);
		this.addExtension(extension);
	}
	
	public void addAgencyCaseHistoryNotesExtension(DocumentReferenceMDICaseHistory mdiCaseHistory) {
		Extension extension = new Extension(DiagnosticReportToxicologyToMDIUtil.agencyCaseHistoryNotesExtensionURL);
		extension.setValue(new Reference(mdiCaseHistory.getId()));
		this.addExtension(extension);
	}
	
	public void addAgencyCaseHistoryNotesExtension(DocumentReferenceMDICaseNotesSummary mdiCaseNotesSummary) {
		Extension extension = new Extension(DiagnosticReportToxicologyToMDIUtil.agencyCaseHistoryNotesExtensionURL);
		extension.setValue(new Reference(mdiCaseNotesSummary.getId()));
		this.addExtension(extension);
	}
}