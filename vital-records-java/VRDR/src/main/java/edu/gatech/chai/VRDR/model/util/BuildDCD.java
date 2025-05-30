package edu.gatech.chai.VRDR.model.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.gatech.chai.VRCL.model.AutopsyPerformedIndicator;
import edu.gatech.chai.VRCL.model.InputRaceAndEthnicity;
import edu.gatech.chai.VRCL.model.ObservationEducationLevel;
import edu.gatech.chai.VRDR.model.BirthRecordIdentifier;
import edu.gatech.chai.VRDR.model.CauseOfDeathPart1;
import edu.gatech.chai.VRDR.model.CauseOfDeathPart2;
import edu.gatech.chai.VRDR.model.Certifier;
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
import edu.gatech.chai.VRDR.model.DispositionLocation;
import edu.gatech.chai.VRDR.model.ExaminerContacted;
import edu.gatech.chai.VRDR.model.FuneralHome;
import edu.gatech.chai.VRDR.model.InjuryIncident;
import edu.gatech.chai.VRDR.model.InjuryLocation;
import edu.gatech.chai.VRDR.model.MannerOfDeath;
import edu.gatech.chai.VRDR.model.TobaccoUseContributedToDeath;
import edu.gatech.chai.VRDR.model.*;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Address.AddressUse;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.HumanName.NameUse;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.IntegerType;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StringType;

import edu.gatech.chai.VRDR.model.util.AddressUtil;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.DecedentUtil;

public class BuildDCD {
	public static DeathCertificateDocument buildExampleDeathCertificateDocument() {
		//DeathCertificateDocument contains the top-level item that represents the entire bundle
		DeathCertificateDocument deathCertificateDocument = new DeathCertificateDocument();
		initResourceForTesting(deathCertificateDocument);
		deathCertificateDocument.setIdentifier(new Identifier().setValue("123"));
		//DeathCertificate is the main fhir resource that contains sectional references to everything else
		DeathCertificate deathCertificate = new DeathCertificate();
		initResourceForTesting(deathCertificate);
		deathCertificateDocument.addEntry(new BundleEntryComponent().setResource(deathCertificate));
		//Every subsequent resource must be added to a contents section
		List<Resource> contents = new ArrayList<Resource>();
		//Decedent
		Decedent decedent = new Decedent();
		initResourceForTesting(decedent);
		Address decedentsHome = new Address().addLine("1808 Stroop Hill Road").setCity("Atlanta")
				.setState("GA").setPostalCode("30303").setCountry("USA").setUse(AddressUse.HOME);
		Extension withinCityLimits = new Extension();
		withinCityLimits.setUrl(AddressUtil.withinCityLimitsIndicatorUrl);
		withinCityLimits.setValue(new BooleanType(true));
		decedentsHome.addExtension(withinCityLimits);
		decedent.setGender(AdministrativeGender.MALE);
		decedent.addRace("Asian", new Coding("2034-7","Chinese","Chinese"), "Asian Chinese"); //Must provide a Coding for a "Detailed" section
		decedent.addEthnicity("Not Hispanic", new Coding(), "Not Hispanic Or Latino"); //Provide empty coding for no detailed section
		decedent.setBirthPlace(decedentsHome);
		decedent.addSSNIdentifier("1AN2BN3DE45");
		decedent.addName(new HumanName().setFamily("Wright").addGiven("Vivien Lee").setUse(NameUse.OFFICIAL));
		Reference decedentReference = new Reference(decedent.getId());
		deathCertificate.setSubject(decedentReference);
		contents.add(decedent);
		//Certifier
		Certifier certifier = new Certifier();
		initResourceForTesting(certifier);
		certifier.addName(new HumanName().setFamily("Black").addGiven("Jim").setUse(NameUse.OFFICIAL));
		certifier.addAddress(new Address().addLine("44 South Street").setCity("Bird in Hand")
				.setState("PA").setPostalCode("30303").setCountry("USA").setUse(AddressUse.WORK));
		certifier.addQualification("12345","Medical Examiner/Coroner");
		Calendar myCalendar = Calendar.getInstance();
		myCalendar.set(2021, 2, 11);
		Date today = myCalendar.getTime();
		deathCertificate.addAttester(certifier,today);
		Reference certifierReference = new Reference(certifier.getId());
		contents.add(certifier);

		CodeableConcept gaState = new CodeableConcept().addCoding(new Coding("","32","Georgia"));
		DateTimeType birthYear = new DateTimeType("1935");
		BirthRecordIdentifier birthRecordIdentifier = new BirthRecordIdentifier("June 3rd 1935",decedent,gaState,birthYear);
		initResourceForTesting(birthRecordIdentifier);
		birthRecordIdentifier.setSubject(decedentReference);
		contents.add(birthRecordIdentifier);
		//ObservationEducationLevel
		ObservationEducationLevel decedentEducationLevel = new ObservationEducationLevel("Bachelor's Degree", decedentReference);
		initResourceForTesting(decedentEducationLevel);
		decedentEducationLevel.setValue(new CodeableConcept().addCoding(new Coding("http://terminology.hl7.org/ValueSet/v3-EducationLevel","BD","College or baccalaureate degree complete")));
		decedentEducationLevel.setSubject(decedentReference);
		contents.add(decedentEducationLevel);
		//RelatedPersons resources
		DecedentFather decedentFather = new DecedentFather();
		initResourceForTesting(decedentFather);
		decedentFather.addName(new HumanName().setFamily("Baden").addGiven("George"));
		decedentFather.setPatient(decedentReference);
		contents.add(decedentFather);

		DecedentMother decedentMother = new DecedentMother();
		initResourceForTesting(decedentMother);
		decedentMother.addName(new HumanName().setFamily("Baden").addGiven("Beatrice"));
		decedentMother.setPatient(decedentReference);
		contents.add(decedentMother);

		DecedentSpouse decedentSpouse = new DecedentSpouse();
		initResourceForTesting(decedentSpouse);
		decedentSpouse.addName(new HumanName().setFamily("Baden").addGiven("Teresa"));
		decedentSpouse.setPatient(decedentReference);
		contents.add(decedentSpouse);
		//AutopsyPerformedIndicator: first string is autopsyPerformed, 2nd is resultsAvailable
		AutopsyPerformedIndicator autopsyPerformedIndicator = new AutopsyPerformedIndicator("No","No");
		initResourceForTesting(autopsyPerformedIndicator);
		autopsyPerformedIndicator.setSubject(decedentReference);
		contents.add(autopsyPerformedIndicator);
		//CauseOfDeathPart1
		CauseOfDeathPart1 causeOfDeathPart1 = new CauseOfDeathPart1();
		initResourceForTesting(causeOfDeathPart1);
		causeOfDeathPart1.setDecedent(decedent);
		causeOfDeathPart1.setAsserter(certifier);
		causeOfDeathPart1.setCode(new CodeableConcept().addCoding(new Coding("http://snomed.info/sct","42343007","Congestive heart failure (disorder)")));
		causeOfDeathPart1.createInterval("10 minutes");
		contents.add(causeOfDeathPart1);
		//CauseOfDeathPart2
		CauseOfDeathPart2 causeOfDeathPart2 = new CauseOfDeathPart2();
		initResourceForTesting(causeOfDeathPart2);
		causeOfDeathPart2.setDecedent(decedent);
		causeOfDeathPart2.setAsserter(certifier);
		causeOfDeathPart2.setCode(new CodeableConcept().addCoding(new Coding("http://snomed.info/sct","241006","Epilepsia partialis continua")));
		contents.add(causeOfDeathPart2);
		//DeathCertificationProcedure: represent the event of certification
		DeathCertificationProcedure deathCertificationProcedure = new DeathCertificationProcedure();
		initResourceForTesting(deathCertificationProcedure);
		deathCertificationProcedure.setPerformed(new DateTimeType(today));
		deathCertificationProcedure.addPerformer(certifier, "Medical Examiner/Coroner");
		deathCertificate.addEvent(deathCertificationProcedure);
		contents.add(deathCertificationProcedure);
		//DeathDate
		DeathDate deathDate = new DeathDate(today,today, "Death in hospital");
		initResourceForTesting(deathDate);
		deathDate.setSubject(decedentReference);
		contents.add(deathDate);
		//DeathLocation
		Address hospitalAddress = new Address().addLine("80 Jesse Hill Jr Dr SE").setCity("Atlanta")
				.setState("GA").setPostalCode("30303").setCountry("USA").setUse(AddressUse.WORK);
		//Address Utils to add extensions to addresses
		AddressUtil.addCityCode(11122, hospitalAddress);
		AddressUtil.addDistrictCode(22233, hospitalAddress);
		AddressUtil.addStateJurisdiction("GA", hospitalAddress); //Do not need to do this if the jurisdiction matches the state
		DeathLocation deathLocation = new DeathLocation("Grady Hospital", "Grady Hospital of Atlanta", hospitalAddress);
		initResourceForTesting(deathLocation);
		contents.add(deathLocation);
		//DecedentAge
		Quantity decedentAgeQuantity = new Quantity(79);
		decedentAgeQuantity.setCode("a"); //Can either be "a" or "mo"
		decedentAgeQuantity.setUnit("a");
		DecedentAge decedentAge = new DecedentAge(decedentAgeQuantity);
		initResourceForTesting(decedentAge);
		contents.add(decedentAge);
		//DecedentDispositionMethod
		DecedentDispositionMethod decedentDispostionMethod = new DecedentDispositionMethod("Burial");
		decedentDispostionMethod.setSubject(decedentReference);
		decedentDispostionMethod.addPerformer(certifierReference);
		initResourceForTesting(decedentDispostionMethod);
		contents.add(decedentDispostionMethod);
		//DecedentUsualWork
		/*
    	CodeableConcept occupationCode = new CodeableConcept().addCoding(new Coding("urn:oid:2.16.840.1.114222.4.5.314","1330","Agricultural engineers"));
    	CodeableConcept industryCode = new CodeableConcept().addCoding(new Coding("urn:oid:2.16.840.1.114222.4.5.315","2180","Agricultural chemical manufacturing"));
    	Integer occupationYears = new Integer(15);
    	DecedentUsualWork decedentUsualWork = new DecedentUsualWork(occupationCode,industryCode,occupationYears);
    	decedentUsualWork.setSubject(decedentReference);
    	initResourceForTesting(decedentUsualWork);
    	contents.add(decedentUsualWork);
		 */
		//DecedentMilitaryService
		DecedentMilitaryService decedentMilitaryService = new DecedentMilitaryService(CommonUtil.yesCode);
		decedentMilitaryService.setSubject(decedentReference);
		initResourceForTesting(decedentMilitaryService);
		contents.add(decedentMilitaryService);
		//DecedentPregnancyStatus
		DecedentPregnancyStatus decedentPregnancyStatus = new DecedentPregnancyStatus("No");
		decedentPregnancyStatus.setSubject(decedentReference);
		initResourceForTesting(decedentPregnancyStatus);
		contents.add(decedentPregnancyStatus);
		//DispositionLocation
		DispositionLocation dispositionLocation = new DispositionLocation(hospitalAddress);
		initResourceForTesting(dispositionLocation);
		contents.add(dispositionLocation);
		//ExaminerContacted
		ExaminerContacted examinerContacted = new ExaminerContacted(true);
		examinerContacted.setSubject(decedentReference);
		initResourceForTesting(examinerContacted);
		contents.add(examinerContacted);
		//FuneralHome
		FuneralHome funeralHome = new FuneralHome("Home Bodies Funeral Services", hospitalAddress);
		initResourceForTesting(funeralHome);
		contents.add(funeralHome);
		//InjuryIncident
		InjuryIncident injuryIncident = new InjuryIncident(decedent, "Home", "No", "No");
		initResourceForTesting(injuryIncident);
		contents.add(injuryIncident);
		//InjuryLocation
		InjuryLocation injuryLocation = new InjuryLocation();
		injuryLocation.setName("Hospital");
		injuryLocation.setDescription("Gracie Hospital");
		injuryLocation.addType(new CodeableConcept().addCoding(new Coding().setCode("HOSP").setSystem("http://hl7.org/fhir/ValueSet/v3-ServiceDeliveryLocationRoleType")));
		injuryLocation.setAddress(decedentsHome);
		initResourceForTesting(injuryLocation);
		contents.add(injuryLocation);
		//MannerOfDeath
		MannerOfDeath mannerOfDeath = new MannerOfDeath("Accidental death",decedent,certifier);
		initResourceForTesting(mannerOfDeath);
		contents.add(mannerOfDeath);
		//TobaccoUseContributedToDeath
		TobaccoUseContributedToDeath tobaccoUseContributedToDeath = new TobaccoUseContributedToDeath("Yes");
		tobaccoUseContributedToDeath.setSubject(decedentReference);
		initResourceForTesting(tobaccoUseContributedToDeath);
		contents.add(tobaccoUseContributedToDeath);
		// Input Race and Ethnicity
		InputRaceAndEthnicity inputRaceAndEthnicity = new InputRaceAndEthnicity();
		inputRaceAndEthnicity.addRaceBooleanComponent("AmericanIndianOrAlaskanNative", true);
		inputRaceAndEthnicity.addRaceLiteralComponent("FirstAmericanIndianOrAlaskanNativeLiteral", "Apache");
		inputRaceAndEthnicity.addRaceLiteralComponent("SecondAmericanIndianOrAlaskanNativeLiteral", "Lipan Apache");
		inputRaceAndEthnicity.addHispanicBooleanComponent("HispanicOther", true);
		initResourceForTesting(inputRaceAndEthnicity);
		contents.add(inputRaceAndEthnicity);

		for(Resource resource:contents) {
			deathCertificateDocument.addResource(resource);
		}
		StringType eventUri = new StringType("http://nchs.cdc.gov/vrdr_submission");
		return deathCertificateDocument;
	}

	private static void initResourceForTesting(Resource resource) {
		CommonUtil.setUUID(resource);
	}
	//Example demonstrating how to give a partial birthdate extension component
	public static Decedent buildDecedentWithBirthDateAbsentReason() {
		Decedent decedent = new Decedent();
		initResourceForTesting(decedent);
		Address decedentsHome = new Address().addLine("1808 Stroop Hill Road").setCity("Atlanta")
				.setState("GA").setPostalCode("30303").setCountry("USA").setUse(AddressUse.HOME);
		Extension withinCityLimits = new Extension();
		withinCityLimits.setUrl(AddressUtil.withinCityLimitsIndicatorUrl);
		withinCityLimits.setValue(new BooleanType(true));
		decedentsHome.addExtension(withinCityLimits);
		decedent.setGender(AdministrativeGender.MALE);
		decedent.addRace("Asian", new Coding("2034-7","Chinese","Chinese"), "Asian Chinese"); //Must provide a Coding for a "Detailed" section
		decedent.addEthnicity("Not Hispanic", new Coding(), "Not Hispanic Or Latino"); //Provide empty coding for no detailed section
		decedent.setBirthPlace(decedentsHome);
		decedent.addSSNIdentifier("1AN2BN3DE45");
		decedent.addName(new HumanName().setFamily("Wright").addGiven("Vivien Lee").setUse(NameUse.OFFICIAL));
		//Adding partial where the specific date of birth is unknown, but the year is known
		decedent.addPartialBirthDateExtension(new IntegerType(1960), "", null, "unknown", null, "unknown");
		return decedent;
	}
	//Example demonstrating how to give a partial death date extension component
	public static DeathDate buildDeathWithPartialDateAbsentReason() {
		DeathDate deathDate = new DeathDate();
		//Adding partial where the year is 2019 and month is June, but the day is unknown.
		deathDate.addPartialDateExtension("death", new IntegerType(2019), "", new IntegerType(6), "", null, "unknown");
		return deathDate;
	}

	public static DeathDate buildDeathWithPartialDateTime() {
		DeathDate deathDate = new DeathDate();
		deathDate.addPartialDateTimeExtension("death", new IntegerType(2019), "", new IntegerType(7), "", null, "unknown", new StringType("T16:47:04-05:00"), "");
		return deathDate;
	}

	public static DeathDate buildDeathWithPartialDateTimeAbsentReason() {
		DeathDate deathDate = new DeathDate();
		deathDate.addPartialDateTimeExtension("death", new IntegerType(2019), "", new IntegerType(7), "", new IntegerType(16), "", null, "unknown");
		return deathDate;
	}

	public static InjuryIncident buildInjuryIncidentWithPartialDateTime() {
		InjuryIncident injuryIncident = new InjuryIncident();
		injuryIncident.addPartialDateTimeExtension("injury", new IntegerType(2019), "", new IntegerType(7), "", null, "unknown", new StringType("T16:47:04-05:00"), "");
		return injuryIncident;
	}

	public static InjuryIncident buildInjuryIncidentWithPartialDateTimeAbsentReason() {
		InjuryIncident injuryIncident = new InjuryIncident();
		injuryIncident.addPartialDateTimeExtension("injury", new IntegerType(2019), "", new IntegerType(7), "", new IntegerType(16), "", null, "unknown");
		return injuryIncident;
	}
}
