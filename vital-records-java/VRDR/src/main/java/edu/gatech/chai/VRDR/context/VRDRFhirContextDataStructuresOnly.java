package edu.gatech.chai.VRDR.context;

import ca.uhn.fhir.context.FhirContext;
import edu.gatech.chai.USCore.model.USCorePractitioner;
import edu.gatech.chai.VRCL.context.VRCLFhirContext;
import edu.gatech.chai.VRCL.model.AutopsyPerformedIndicator;
import edu.gatech.chai.VRCL.model.CodedRaceAndEthnicity;
import edu.gatech.chai.VRCL.model.EmergingIssues;
import edu.gatech.chai.VRCL.model.InputRaceAndEthnicity;
import edu.gatech.chai.VRCL.model.ObservationUsualWork;
import edu.gatech.chai.VRDR.model.AutomatedUnderlyingCauseOfDeath;
import edu.gatech.chai.VRDR.model.BirthRecordIdentifier;
import edu.gatech.chai.VRDR.model.CauseOfDeathCodedContentBundle;
import edu.gatech.chai.VRDR.model.CauseOfDeathPart1;
import edu.gatech.chai.VRDR.model.CauseOfDeathPart2;
import edu.gatech.chai.VRDR.model.Certifier;
import edu.gatech.chai.VRDR.model.CodingStatusValues;
import edu.gatech.chai.VRDR.model.DeathCertificate;
import edu.gatech.chai.VRDR.model.DeathCertificateDocument;
import edu.gatech.chai.VRDR.model.DeathCertificationProcedure;
import edu.gatech.chai.VRDR.model.DeathDate;
import edu.gatech.chai.VRDR.model.DeathLocation;
import edu.gatech.chai.VRDR.model.Decedent;
import edu.gatech.chai.VRDR.model.DecedentAge;
import edu.gatech.chai.VRDR.model.DecedentDispositionMethod;
import edu.gatech.chai.VRDR.model.DecedentFather;
import edu.gatech.chai.VRDR.model.DecedentMilitaryService;
import edu.gatech.chai.VRDR.model.DecedentMother;
import edu.gatech.chai.VRDR.model.DecedentPregnancyStatus;
import edu.gatech.chai.VRDR.model.DecedentSpouse;
import edu.gatech.chai.VRDR.model.DemographicCodedContentBundle;
import edu.gatech.chai.VRDR.model.DispositionLocation;
import edu.gatech.chai.VRDR.model.EntityAxisCauseOfDeath;
import edu.gatech.chai.VRDR.model.ExaminerContacted;
import edu.gatech.chai.VRDR.model.FuneralHome;
import edu.gatech.chai.VRDR.model.InjuryIncident;
import edu.gatech.chai.VRDR.model.InjuryLocation;
import edu.gatech.chai.VRDR.model.MannerOfDeath;
import edu.gatech.chai.VRDR.model.ManualUnderlyingCauseOfDeath;
import edu.gatech.chai.VRDR.model.PlaceOfInjury;
import edu.gatech.chai.VRDR.model.RecordAxisCauseOfDeath;
import edu.gatech.chai.VRDR.model.SurgeryDate;
import edu.gatech.chai.VRDR.model.TobaccoUseContributedToDeath;
import edu.gatech.chai.VRDR.model.*;
import org.hl7.fhir.r4.model.Practitioner;

public class VRDRFhirContextDataStructuresOnly extends VRCLFhirContext{
	public VRDRFhirContextDataStructuresOnly() {
		// these types come from http://hl7.org/fhir/us/vrdr/index.html
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-activity-at-time-of-death",
				ActivityAtTimeOfDeath.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-automated-underlying-cause-of-death",
				AutomatedUnderlyingCauseOfDeath.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-birth-record-identifier",
				BirthRecordIdentifier.class);
		ctx.setDefaultTypeForProfile( "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-cause-of-death-coded-bundle",
				CauseOfDeathCodedContentBundle.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-cause-of-death-part1",
				CauseOfDeathPart1.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-cause-of-death-part2",
				CauseOfDeathPart2.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-certifier",
				Certifier.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-coding-status-values",
				CodingStatusValues.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-death-certificate",
				DeathCertificate.class);
		ctx.setDefaultTypeForProfile( "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-death-certificate-document",
				DeathCertificateDocument.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-death-certification",
				DeathCertificationProcedure.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-death-date",
				DeathDate.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-death-location",
				DeathLocation.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent",
				Decedent.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent-age",
				DecedentAge.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent-disposition-method",
				DecedentDispositionMethod.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent-father",
				DecedentFather.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-military-service",
				DecedentMilitaryService.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent-mother",
				DecedentMother.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent-pregnancy-status",
				DecedentPregnancyStatus.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent-spouse",
				DecedentSpouse.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-decedent-usual-work",
				ObservationUsualWork.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-demographic-coded-bundle",
				DemographicCodedContentBundle.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-disposition-location",
				DispositionLocation.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-entity-axis-cause-of-death",
				EntityAxisCauseOfDeath.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-examiner-contacted",
				ExaminerContacted.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-funeral-home",
				FuneralHome.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-injury-incident",
				InjuryIncident.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-injury-location",
				InjuryLocation.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-manner-of-death",
				MannerOfDeath.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-manual-underlying-cause-of-death",
				ManualUnderlyingCauseOfDeath.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-place-of-injury",
				PlaceOfInjury.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-record-axis-cause-of-death",
				RecordAxisCauseOfDeath.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-surgery-date",
				SurgeryDate.class);
		ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-tobacco-use-contributed-to-death",
				TobaccoUseContributedToDeath.class);
	}

	public FhirContext getCtx() {
		return ctx;
	}

	public void setCtx(FhirContext ctx) {
		this.ctx = ctx;
	}

}
