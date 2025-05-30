package edu.gatech.chai.MDI.context;

import ca.uhn.fhir.context.FhirContext;
import edu.gatech.chai.MDI.model.resource.BundleDocumentMDIAndEDRS;
import edu.gatech.chai.MDI.model.resource.CompositionMDIAndEDRS;
import edu.gatech.chai.MDI.model.resource.ObservationCauseOfDeathPart1;
import edu.gatech.chai.MDI.model.resource.ObservationContributingCauseOfDeathPart2;
import edu.gatech.chai.MDI.model.resource.DiagnosticReportToxicologyToMDI;
import edu.gatech.chai.MDI.model.resource.DocumentReferenceMDICaseHistory;
import edu.gatech.chai.MDI.model.resource.DocumentReferenceMDICaseNotesSummary;
import edu.gatech.chai.MDI.model.resource.MessageHeaderToxicologyToMDI;
import edu.gatech.chai.MDI.model.resource.ObservationHowDeathInjuryOccurred;
import edu.gatech.chai.MDI.model.resource.SpecimenToxicologyLab;
import edu.gatech.chai.MDI.model.resource.USCoreObservationLab;
import edu.gatech.chai.MDI.model.resource.util.ObservationConditionContributingToDeathUtil;
import edu.gatech.chai.VRDR.context.VRDRFhirContextDataStructuresOnly;

public class MDIFhirContext extends VRDRFhirContextDataStructuresOnly{
	public MDIFhirContext() {
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/mdi/StructureDefinition/Bundle-document-mdi-and-edrs",
				BundleDocumentMDIAndEDRS.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/mdi/StructureDefinition/Composition-mdi-and-edrs",
				CompositionMDIAndEDRS.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/mdi/StructureDefinition/DiagnosticReport-toxicology-to-mdi",
				DiagnosticReportToxicologyToMDI.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/mdi/StructureDefinition/DocumentReference-mdi-case-history",
				DocumentReferenceMDICaseHistory.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/mdi/StructureDefinition/DocumentReference-mdi-case-notes-summary",
				DocumentReferenceMDICaseNotesSummary.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/mdi/StructureDefinition/MessageHeader-toxicology-to-mdi",
				MessageHeaderToxicologyToMDI.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/mdi/StructureDefinition/Observation-mdi-cause-of-death-part1",
				ObservationCauseOfDeathPart1.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/mdi/StructureDefinition/Observation-contributing-cause-of-death-part2",
				ObservationContributingCauseOfDeathPart2.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/mdi/StructureDefinition/Observation-how-death-injury-occurred",
				ObservationHowDeathInjuryOccurred.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/mdi/StructureDefinition/Specimen-toxicology-lab",
				SpecimenToxicologyLab.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/core/StructureDefinition/us-core-observation-lab",
				USCoreObservationLab.class);
	}

	public FhirContext getCtx() {
		return ctx;
	}

	public void setCtx(FhirContext ctx) {
		this.ctx = ctx;
	}

}