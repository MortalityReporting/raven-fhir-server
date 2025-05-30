package edu.gatech.chai.VRDR.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class DecedentUtil {

	public static final String raceExtensionURL = "http://hl7.org/fhir/us/core/StructureDefinition/us-core-race";
	public static final String raceSystem = "urn:oid:2.16.840.1.113883.6.238";
	public static final String ethnicityExtensionURL = "http://hl7.org/fhir/us/core/StructureDefinition/us-core-ethnicity";
	public static final String ethnicitySystem = "urn:oid:2.16.840.1.113883.6.238";
	public static final String sexAtDeathExtensionURL = "http://hl7.org/fhir/us/vrdr/StructureDefinition/NVSS-SexAtDeath";
	public static final String spouseAliveExtensionURL = "http://hl7.org/fhir/us/vrdr/StructureDefinition/SpouseAlive";
	public static final String birthSexValueSetURL = "http://hl7.org/fhir/us/core/ValueSet/us-core-birthsex";
	public static final String birthPlaceExtensionURL = "http://hl7.org/fhir/StructureDefinition/patient-birthPlace";
	public static final String maritalStatusURL = "http://terminology.hl7.org/CodeSystem/v3-MaritalStatus";
	public static final String administrativeGenderURL = "http://hl7.org/fhir/administrative-gender";
	public static final CodeableConcept identifierTypeFixedValue = new CodeableConcept()
			.addCoding(new Coding().setSystem(CommonUtil.identifierTypeHL7System).setCode("SB").setDisplay("Social Beneficiary Identifier"));
	public static final String identifierSystem = "http://hl7.org/fhir/sid/us-ssn";

	public static final String birthsexExtensionUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/NVSS-SexAtDeath";
	public static final String birthsexCodeSystem = "http://hl7.org/fhir/administrative-gender";
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
