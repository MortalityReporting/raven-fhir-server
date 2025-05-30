package edu.gatech.chai.USCore.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class USCorePatientUtil {
    
	public static final String raceExtensionURL = "http://hl7.org/fhir/us/core/StructureDefinition/us-core-race";
	public static final String raceSystem = "urn:oid:2.16.840.1.113883.6.238";
	public static final String ethnicityExtensionURL = "http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity";
	public static final String ethnicitySystem = "urn:oid:2.16.840.1.113883.6.238";
	public static final String sexAtDeathExtensionURL = "http://hl7.org/fhir/us/vrdr/StructureDefinition/NVSS-SexAtDeath";
	public static final String spouseAliveExtensionURL = "http://hl7.org/fhir/us/vrdr/StructureDefinition/SpouseAlive";
	public static final String birthSexValueSetURL = "http://hl7.org/fhir/us/core/ValueSet/us-core-birthsex";
	public static final String genderIdentityExtensionURL = "http://hl7.org/fhir/us/core/StructureDefinition/us-core-genderIdentity";
	
	public static final String maritalStatusURL = "http://terminology.hl7.org/CodeSystem/v3-MaritalStatus";
	public static final String administrativeGenderURL = "http://hl7.org/fhir/administrative-gender";
	public static final CodeableConcept identifierTypeSSValue = new CodeableConcept()
			.addCoding(new Coding().setSystem(CommonUtil.identifierTypeHL7System).setCode("SS").setDisplay("Social Security number"));
			public static final String identifierSSSystem = "http://hl7.org/fhir/sid/us-ssn";
	public static final CodeableConcept identifierTypeMRValue = new CodeableConcept()
			.addCoding(new Coding().setSystem(CommonUtil.identifierTypeHL7System).setCode("MR").setDisplay("Medical Record number"));
	
	public static final String birthsexExtensionUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/NVSS-SexAtDeath";
	public static final String birthsexCodeSystem = "http://hl7.org/fhir/administrative-gender";

	public static final CodeableConcept VALUE_ADMINISTRATIVE_GENDER_FEMALE = new CodeableConcept().addCoding(new Coding(administrativeGenderURL,"F","Female"));
	public static final CodeableConcept VALUE_ADMINISTRATIVE_GENDER_MALE = new CodeableConcept().addCoding(new Coding(administrativeGenderURL,"M","Male"));

	public static final HashSet<CodeableConcept> birthSexExtensionSet = new HashSet<>(Arrays.asList(
			VALUE_ADMINISTRATIVE_GENDER_FEMALE,
			VALUE_ADMINISTRATIVE_GENDER_MALE,
			CommonUtil.askuCode,
			CommonUtil.otherCode,
			CommonUtil.unknownCode
	));

	public static final CodeableConcept VALUE_MALE_TO_FEMALE_TRANSSEXUAL = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"407376001","Male-to-female transsexual (finding)"));
	public static final CodeableConcept VALUE_FEMALE_TO_MALE_TRANSSEXUAL = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"407377005","Female-to-male transsexual (finding)"));
	public static final CodeableConcept VALUE_IDENTIFIES_NONGENDER_CONFORMING = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"446131000124102","Identifies as non-conforming gender (finding)"));
	public static final CodeableConcept VALUE_IDENTIFIES_FEMALE = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"446141000124107","Identifies as female gender (finding)"));
	public static final CodeableConcept VALUE_IDENTIFIES_MALE = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"446151000124109","Identifies as male gender (finding)"));

	public static final HashSet<CodeableConcept> genderIdentityExtensionSet = new HashSet<>(Arrays.asList(
			VALUE_MALE_TO_FEMALE_TRANSSEXUAL,
			VALUE_FEMALE_TO_MALE_TRANSSEXUAL,
			VALUE_IDENTIFIES_NONGENDER_CONFORMING,
			VALUE_IDENTIFIES_FEMALE,
			VALUE_IDENTIFIES_MALE,
			CommonUtil.askuCode,
			CommonUtil.otherCode
	));

	public static final CodeableConcept VALUE_MALE = new CodeableConcept().addCoding(new Coding(birthsexCodeSystem,"male","Male"));
	public static final CodeableConcept VALUE_FEMALE = new CodeableConcept().addCoding(new Coding(birthsexCodeSystem,"female","Female"));
	public static final CodeableConcept VALUE_DIVORCED = new CodeableConcept().addCoding(new Coding(maritalStatusURL,"D","Divorced"));
	public static final CodeableConcept VALUE_SEPARATED = new CodeableConcept().addCoding(new Coding(maritalStatusURL,"L","Legally Separated"));
	public static final CodeableConcept VALUE_MARRIED = new CodeableConcept().addCoding(new Coding(maritalStatusURL,"M","Married"));
	public static final CodeableConcept VALUE_NEVERMARRIED = new CodeableConcept().addCoding(new Coding(maritalStatusURL,"S","Never Married"));
	public static final CodeableConcept VALUE_WIDOWED = new CodeableConcept().addCoding(new Coding(maritalStatusURL,"W","Widowed"));
	public static final HashSet<CodeableConcept> maritalStatusSet = new HashSet<>(Arrays.asList(
			VALUE_DIVORCED,
			VALUE_SEPARATED,
			VALUE_MARRIED,
			VALUE_NEVERMARRIED,
			VALUE_WIDOWED,
			CommonUtil.unknownCode
	));
	public static final List<String> raceBooleanNVSSSet = Arrays.asList("White", "BlackOrAfricanAmerican",
			"AmericanIndianOrAlaskanNative", "AsianIndian", "Chinese", "Filipino", "Japanese", "Korean", "Vietnamese",
			"OtherAsian", "NativeHawaiian", "GuamanianOrChamorro", "Samoan","OtherPacificIslander","OtherRace"
	);

	public static final List<String> raceLiteralNVSSSet = Arrays.asList(
		"FirstAmericanIndianOrAlaskanNativeLiteral",
		"SecondAmericanIndianOrAlaskanNativeLiteral",
		"FirstOtherAsianLiteral",
		"SecondOtherAsianLiteral",
		"FirstOtherPacificIslanderLiteral",
		"SecondOtherPacificIslanderLiteral",
		"FirstOtherRaceLiteral",
		"SecondOtherRaceLiteral",
		"HispanicLiteral");
	public static final List<String> hispanicCodedNVSSSet = Arrays.asList("HispanicMexican", "HispanicPuertoRican",
			"HispanicOther", "HispanicCuban");
	public static Set<CodeableConcept> sexAtDeathSet = new HashSet<CodeableConcept>(Arrays.asList(
			VALUE_MALE,
			VALUE_FEMALE,
			CommonUtil.unknownCode
	));

	public static Set<CodeableConcept> spouseAliveSet = CommonUtil.yesNoUnknownSet;	
}
