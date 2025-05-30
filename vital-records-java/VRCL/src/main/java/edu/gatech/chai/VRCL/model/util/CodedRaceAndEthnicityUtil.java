package edu.gatech.chai.VRCL.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class CodedRaceAndEthnicityUtil {
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "codedraceandethnicity", "Coded Race and Ethnicity"));
	public static final CodeableConcept firstEditedCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "FirstEditedCode", "FirstEditedCode"));
	public static final CodeableConcept secondEditedCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "SecondEditedCode", "SecondEditedCode"));
	public static final CodeableConcept thirdEditedCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "ThirdEditedCode", "ThirdEditedCode"));
	public static final CodeableConcept fourthEditedCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "FourthEditedCode", "FourthEditedCode"));
	public static final CodeableConcept fifthEditedCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "FifthEditedCode", "FifthEditedCode"));
	public static final CodeableConcept sixthEditedCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "SixthEditedCode", "SixthEditedCode"));
	public static final CodeableConcept seventhEditedCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "SeventhEditedCode", "SeventhEditedCode"));
	public static final CodeableConcept eigthEditedCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "EigthEditedCode", "EigthEditedCode"));
	public static final CodeableConcept firstAmericanIndianCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "FirstAmericanIndianCode", "FirstAmericanIndianCode"));
	public static final CodeableConcept secondAmericanIndianCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "SecondAmericanIndianCode", "SecondAmericanIndianCode"));
	public static final CodeableConcept firstOtherAsianCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "FirstOtherAsianCode", "FirstOtherAsianCode"));
	public static final CodeableConcept secondOtherAsianCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "SecondOtherAsianCode", "SecondOtherAsianCode"));
	public static final CodeableConcept firstOtherPacificIslanderCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "FirstOtherPacificIslanderCode", "FirstOtherPacificIslanderCode"));
	public static final CodeableConcept secondOtherPacificIslanderCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "SecondOtherPacificIslanderCode", "SecondOtherPacificIslanderCode"));
	public static final CodeableConcept firstOtherRaceComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "FirstOtherRaceComponentCode", "FirstOtherRaceComponentCode"));
	public static final CodeableConcept secondOtherRaceComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "SecondOtherRaceComponentCode", "SecondOtherRaceComponentCode"));
	public static final CodeableConcept raceRecode40ComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "RaceRecode40", "RaceRecode40"));
	public static final CodeableConcept hispanicCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "HispanicCode", "HispanicCode"));
	public static final CodeableConcept hispanicCodeForLiteralComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "HispanicCodeForLiteral", "HispanicCodeForLiteral"));
	public static final CodeableConcept hispanicMexicanCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "HispanicMexican", "Hispanic Mexican"));
	public static final CodeableConcept hispanicPuertoRicanCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "HispanicPuertoRican", "Hispanic Puerto Rican"));
	public static final CodeableConcept hispanicCubanCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "HispanicCuban", "Hispanic Cuban"));
	public static final CodeableConcept hispanicOtherCodeComponentCode = new CodeableConcept()
			.addCoding(new Coding("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs", "HispanicOther", "Hispanic Other"));
	public static Set<CodeableConcept> valueCodesetList = new HashSet<CodeableConcept>(Arrays.asList(
			firstEditedCodeComponentCode,secondEditedCodeComponentCode,thirdEditedCodeComponentCode,fourthEditedCodeComponentCode,
			fifthEditedCodeComponentCode,sixthEditedCodeComponentCode,seventhEditedCodeComponentCode,eigthEditedCodeComponentCode,
			firstAmericanIndianCodeComponentCode,secondAmericanIndianCodeComponentCode,firstOtherAsianCodeComponentCode,
			secondOtherAsianCodeComponentCode,firstOtherPacificIslanderCodeComponentCode,secondOtherPacificIslanderCodeComponentCode,
			firstOtherRaceComponentCode,secondOtherRaceComponentCode,raceRecode40ComponentCode,hispanicCodeComponentCode,
			hispanicCodeForLiteralComponentCode,hispanicMexicanCodeComponentCode,hispanicPuertoRicanCodeComponentCode,
			hispanicCubanCodeComponentCode,hispanicOtherCodeComponentCode));
}