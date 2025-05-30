package edu.gatech.MDI;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Address.AddressUse;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition.CompositionStatus;
import org.hl7.fhir.r4.model.Composition.SectionComponent;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.HumanName.NameUse;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Location;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.Procedure.ProcedureStatus;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StringType;

import edu.gatech.chai.MDI.model.resource.BundleDocumentMDIAndEDRS;
import edu.gatech.chai.MDI.model.resource.CompositionMDIAndEDRS;
import edu.gatech.chai.MDI.model.resource.ObservationCauseOfDeathPart1;
import edu.gatech.chai.MDI.model.resource.ObservationContributingCauseOfDeathPart2;
import edu.gatech.chai.MDI.model.resource.ObservationHowDeathInjuryOccurred;
import edu.gatech.chai.MDI.model.resource.util.CommonUtil;
import edu.gatech.chai.VRCL.model.AutopsyPerformedIndicator;
import edu.gatech.chai.VRCL.model.PatientVitalRecords;
import edu.gatech.chai.VRDR.model.Certifier;
import edu.gatech.chai.VRDR.model.DeathCertificationProcedure;
import edu.gatech.chai.VRDR.model.DeathDate;
import edu.gatech.chai.VRDR.model.Decedent;
import edu.gatech.chai.VRDR.model.DecedentPregnancyStatus;
import edu.gatech.chai.VRDR.model.MannerOfDeath;
import edu.gatech.chai.VRDR.model.TobaccoUseContributedToDeath;

public class BuildMDIAndEdrsDocument {
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    public static BundleDocumentMDIAndEDRS buildExampleBundleDocumentMDIAndEDRS() {
        List<Resource> contents = new ArrayList<Resource>();
        //MDIToEDRSDocument contains the top-level item that represents the entire bundle
        BundleDocumentMDIAndEDRS bundleDocument = new BundleDocumentMDIAndEDRS();
        initResourceForTesting(bundleDocument);
        //DeathCertificate is the main fhir resource that contains sectional references to everything else
        CompositionMDIAndEDRS mainComposition = bundleDocument.getCompositionMDIAndEDRS();
        mainComposition.addMDICaseIdExtension("12345");
        mainComposition.addEDRSCaseIdExtension("67890");
        mainComposition.setStatus(CompositionStatus.FINAL);
        mainComposition.setTitle("Example MDI-To-EDRS Record. TEST ONLY");
        initResourceForTesting(mainComposition);
        //Every resource must have a fullUrl, and thefullurl may depend on the id, so set the id THEN set the full url
        bundleDocument.setFullUrlOnCompositionMDIAndEDRS();
        //Documents must have a date. This must be when the bundle was assembled
        bundleDocument.setTimestamp(new Date());
        bundleDocument.setIdentifier(new Identifier().setValue("123").setSystem("urn:test"));
        initResourceForTesting(bundleDocument);
        //Patient
        Decedent decedent = new Decedent();
        initResourceForTesting(decedent);
        //Setting USCore specific race, ethnicity, birthsex
        Extension raceExtension = new Extension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-race");
        raceExtension.addExtension("ombCategory", new Coding("urn:oid:2.16.840.1.113883.6.238","2028-9","Asian"));
        raceExtension.addExtension("detailed", new Coding("urn:oid:2.16.840.1.113883.6.238","2034-7","Chinese"));
        raceExtension.addExtension("text", new StringType("Chinese"));
        Extension ethnicityExtension = new Extension("http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity");
        ethnicityExtension.addExtension("ombCategory", new Coding("urn:oid:2.16.840.1.113883.6.238","2186-5","Non Hispanic or Latino"));
        ethnicityExtension.addExtension("text", new StringType("Not Hispanic Or Latino"));
        
        decedent.addExtension(raceExtension);
        decedent.addExtension(ethnicityExtension);
        
        Address decedentsHome = new Address().addLine("1808 Stroop Hill Road").setCity("Atlanta")
        .setState("GA").setPostalCode("30303").setCountry("US").setUse(AddressUse.HOME);
        decedent.setGender(AdministrativeGender.MALE);
        decedent.addIdentifier(new Identifier().setSystem("http://hl7.org/fhir/sid/us-ssn").setValue("123-45-6789"));
        decedent.addName(new HumanName().setFamily("Edgarson").addGiven("Edger").setUse(NameUse.OFFICIAL));
        decedent.addAddress(decedentsHome);
        Reference decedentReference = new Reference("Patient/"+decedent.getId());
        contents.add(decedent);
        //Practitioner
        //Note: The Practitioner used in MDI is a USCore Practitioner. He is the main author of the document as well as the certifier
        Certifier certifier = new Certifier();
        initResourceForTesting(certifier);
        certifier.getMeta().addProfile("http://hl7.org/fhir/us/core/StructureDefinition/us-core-practitioner");
        certifier.addIdentifier(new Identifier().setSystem("urn:example-system.case-number-system").setValue("1234567893"));
        certifier.addName(new HumanName().setFamily("Pratt").addGiven("Practitioner").setUse(NameUse.OFFICIAL));
        Reference practitionerReference = new Reference("Practitioner/"+certifier.getId());
        contents.add(certifier);

        //The practitioner is also the author of the whole document, and is the attestor and is set in the Composition
        mainComposition.setSubject(decedentReference);
        mainComposition.addAuthor(practitionerReference);
        mainComposition.addAttester(practitionerReference);
        //Compositions must have a date, generally when they are created.
        mainComposition.setDate(new Date());
        //Compositions must have a main identifier. In this case, we will use the a generic identifer.
        mainComposition.setIdentifier(new Identifier().setValue("123").setSystem("urn:test"));
        //Every subsequent resource must be added to a contents section
        //Demographics Section
        //Commenting out since we don't want to initialize a section if we have no data for it
        //SectionComponent demographicsSection = mainComposition.getDemographicsSection();
        
        //Circumstances Section
        SectionComponent circumstancesSection = mainComposition.getCircumstancesSection();
        //  Death Location
        Location deathLocation = new Location();
        initResourceForTesting(deathLocation);
        deathLocation.getMeta().addProfile("http://hl7.org/fhir/us/core/StructureDefinition/us-core-location");
        deathLocation.setName("Side street between corner of Main Street and Eagle Lane.");
        deathLocation.setAddress(new Address().setText("Side street between corner of Main Street and Eagle Lane."));
        circumstancesSection.addEntry(new Reference(deathLocation));
        contents.add(deathLocation);
        // TobaccoUseContributedToDeath
        TobaccoUseContributedToDeath tobacco = new TobaccoUseContributedToDeath();
        tobacco.setValue("No");
        tobacco.setSubject(decedentReference);
        //Depending on context, the observation may be initial, or if certified, may be final
        tobacco.setStatus(ObservationStatus.FINAL);
        initResourceForTesting(tobacco);
        circumstancesSection.addEntry(new Reference(tobacco));
        contents.add(tobacco);
        // DecedentPregnancy
        DecedentPregnancyStatus pregnancy = new DecedentPregnancyStatus("Not Applicable");
        pregnancy.setSubject(decedentReference);
        //Depending on context, the observation may be initial, or if certified, may be final
        pregnancy.setStatus(ObservationStatus.FINAL);
        initResourceForTesting(pregnancy);
        circumstancesSection.addEntry(new Reference(pregnancy));
        contents.add(pregnancy);
        //Injury Location
        Location injuryLocation = new Location();
        initResourceForTesting(injuryLocation);
        injuryLocation.getMeta().addProfile("http://hl7.org/fhir/us/core/StructureDefinition/us-core-location");
        injuryLocation.setName("Eagle Street Pub");
        injuryLocation.setAddress(new Address().setText("Eagle Street Pub"));
        circumstancesSection.addEntry(new Reference(injuryLocation));
        contents.add(injuryLocation);
        //Jurisdiction Section
        SectionComponent jurisdictionSection = mainComposition.getJurisdictionSection();
        // DeathDate
        DeathDate deathDate;
        try {
            deathDate = new DeathDate(formatter.parse("03-10-2022"),formatter.parse("03-12-2022"), "Eagle Street Pub");
            deathDate.setSubject(decedentReference);
            initResourceForTesting(deathDate);
            //Depending on context, the observation may be initial, or if certified, may be final
            deathDate.setStatus(ObservationStatus.FINAL);
            jurisdictionSection.addEntry(new Reference(deathDate));
            contents.add(deathDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Death Certification
        // For this example, the author of the document has also certified, but in many cases, 
        try{
            DeathCertificationProcedure deathCertification = new DeathCertificationProcedure(formatter.parse("03-12-2022"));
            deathCertification.setSubject(decedentReference);
            Procedure.ProcedurePerformerComponent procedurePerformerComponent = new Procedure.ProcedurePerformerComponent();
            procedurePerformerComponent.setActor(new Reference(certifier.getId()));
            procedurePerformerComponent.setFunction(CommonUtil.findConceptFromCollectionUsingSimpleString("Medical Examiner/Coroner", edu.gatech.chai.VRDR.model.util.CommonUtil.certifierTypeSet));
            initResourceForTesting(deathCertification);
            //Will probably always be completed if the deathCertification exists, but my be stopped, or in-progress, or entered-in-error given the context.
            deathCertification.setStatus(ProcedureStatus.COMPLETED);
            //Set a datetime for when the certification occured, can also be a period, string
            deathCertification.setPerformed(new DateTimeType(new Date()));
            jurisdictionSection.addEntry(new Reference(deathCertification));
            contents.add(deathCertification);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        //CauseManner Section
        SectionComponent causeMannerSection = mainComposition.getCauseMannerSection();
        // CauseOfDeathCondition
        // Note: the '1' is lineNumber 1, AKA cause of death A
        ObservationCauseOfDeathPart1 causeOfDeath = new ObservationCauseOfDeathPart1(decedent, certifier, "Heart Disease",1,"0 minutes");
        //Depending on context, the observation may be initial, or if certified, may be final
        causeOfDeath.setStatus(ObservationStatus.FINAL);
        initResourceForTesting(causeOfDeath);
        contents.add(causeOfDeath);
        // ConditionContributingToDeath
        ObservationContributingCauseOfDeathPart2 conditionContrib = new ObservationContributingCauseOfDeathPart2(decedent, certifier, "Diabetes");
        //Depending on context, the observation may be initial, or if certified, may be final
        conditionContrib.setStatus(ObservationStatus.FINAL);
        initResourceForTesting(conditionContrib);
        causeMannerSection.addEntry(new Reference(conditionContrib));
        contents.add(conditionContrib);
        // MannerOfDeath
        MannerOfDeath manner = new MannerOfDeath();
        manner.setValue("Natural death");
        manner.setSubject(decedentReference);
        manner.addPerformer(practitionerReference);
        //Depending on context, the observation may be initial, or if certified, may be final
        manner.setStatus(ObservationStatus.FINAL);
        initResourceForTesting(manner);
        causeMannerSection.addEntry(new Reference(manner));
        contents.add(manner);
        // How Death Injury Occurred
        ObservationHowDeathInjuryOccurred howDeathInjuryOccurred = new ObservationHowDeathInjuryOccurred(decedent, certifier, "Decedent was involved in car accident on highway while he was the driver.");
        initResourceForTesting(howDeathInjuryOccurred);
        //Depending on context, the observation may be initial, or if certified, may be final
        howDeathInjuryOccurred.setStatus(ObservationStatus.FINAL);
        howDeathInjuryOccurred.addPlaceOfInjury("Highway 16");
        howDeathInjuryOccurred.addWorkInjuryIndicator("No");
        howDeathInjuryOccurred.addTransportationRole("Vehicle driver");
        causeMannerSection.addEntry(new Reference(howDeathInjuryOccurred));
        contents.add(howDeathInjuryOccurred);
        //Medication-History Section
        SectionComponent medicalHistorySection = mainComposition.getMedicalHistorySection();
        //historicalCondition
        Condition historicalCondition = new Condition();
        initResourceForTesting(historicalCondition);
        historicalCondition.getMeta().addProfile("http://hl7.org/fhir/us/core/StructureDefinition/us-core-condition");
        historicalCondition.setClinicalStatus(new CodeableConcept().addCoding(new Coding("http://terminology.hl7.org/CodeSystem/condition-clinical","active","Active")));
        historicalCondition.setVerificationStatus(new CodeableConcept().addCoding(new Coding("http://terminology.hl7.org/CodeSystem/condition-ver-status","confirmed","Confirmed")));
        historicalCondition.addCategory(new CodeableConcept().addCoding(new Coding("http://terminology.hl7.org/CodeSystem/condition-category","problem-list-item","Problem List Item")));
        historicalCondition.setCode(new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"168000","Typhlolithiasis")));
        historicalCondition.setSubject(decedentReference);
        medicalHistorySection.addEntry(new Reference(historicalCondition));
        contents.add(historicalCondition);
        //Exam-Autopsy Section
        SectionComponent examAutopsySection = mainComposition.getExamAutopsySection();
        AutopsyPerformedIndicator autopsyPerformedIndicator = new AutopsyPerformedIndicator("yes", "no");
        autopsyPerformedIndicator.setSubject(decedentReference);
        initResourceForTesting(autopsyPerformedIndicator);
        //Depending on context, the observation may be initial, or if certified, may be final
        autopsyPerformedIndicator.setStatus(ObservationStatus.FINAL);
        examAutopsySection.addEntry(new Reference(autopsyPerformedIndicator));
        contents.add(autopsyPerformedIndicator);
        for(Resource resource:contents) {
            bundleDocument.addEntry().setResource(resource).setFullUrl(resource.getResourceType()+"/"+resource.getId());
        }
        return bundleDocument;
    }
    
    private static void initResourceForTesting(Resource resource) {
        CommonUtil.setUUID(resource);
    }
}
