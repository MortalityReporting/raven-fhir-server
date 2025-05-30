package edu.gatech.chai.VRCL.context;

import org.hl7.fhir.r4.model.Bundle;

import ca.uhn.fhir.context.FhirContext;
import edu.gatech.chai.USCore.context.USCoreFhirContext;
import edu.gatech.chai.VRCL.model.AutopsyPerformedIndicator;
import edu.gatech.chai.VRCL.model.CodedRaceAndEthnicity;
import edu.gatech.chai.VRCL.model.EmergingIssues;
import edu.gatech.chai.VRCL.model.InputRaceAndEthnicity;
import edu.gatech.chai.VRCL.model.LocationVitalRecords;
import edu.gatech.chai.VRCL.model.ObservationEducationLevel;
import edu.gatech.chai.VRCL.model.ObservationUsualWork;
import edu.gatech.chai.VRCL.model.PatientChildVitalRecords;
import edu.gatech.chai.VRCL.model.PatientMotherVitalRecords;
import edu.gatech.chai.VRCL.model.PatientVitalRecords;
import edu.gatech.chai.VRCL.model.PractitionerVitalRecords;
import edu.gatech.chai.VRCL.model.RelatedPersonFatherVitalRecords;
import edu.gatech.chai.VRCL.model.RelatedPersonMotherVitalRecords;
import edu.gatech.chai.VRCL.model.RelatedPersonParentVitalRecords;

public class VRCLFhirContext extends USCoreFhirContext {
    public VRCLFhirContext() {
        super();
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Observation-autopsy-performed-indicator-vr",
                AutopsyPerformedIndicator.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/coded-race-and-ethnicity-vr",
                CodedRaceAndEthnicity.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Observation-emerging-issues-vr",
                EmergingIssues.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/input-race-and-ethnicity-vr",
                InputRaceAndEthnicity.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Location-vr",
                LocationVitalRecords.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Observation-education-level-vr",
                ObservationEducationLevel.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Observation-usual-work-vr",
                ObservationUsualWork.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Patient-child-vr",
                PatientChildVitalRecords.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Patient-mother-vr",
                PatientMotherVitalRecords.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Patient-vr",
                PatientVitalRecords.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Practitioner-vr",
                PractitionerVitalRecords.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/RelatedPerson-father-vr",
                RelatedPersonFatherVitalRecords.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/RelatedPerson-mother-vr",
                RelatedPersonMotherVitalRecords.class);
        ctx.setDefaultTypeForProfile("http://hl7.org/fhir/us/vr-common-library/StructureDefinition/RelatedPerson-parent-vr",
                RelatedPersonParentVitalRecords.class);
    }
}
