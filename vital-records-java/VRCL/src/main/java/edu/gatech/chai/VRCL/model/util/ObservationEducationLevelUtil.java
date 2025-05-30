package edu.gatech.chai.VRCL.model.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

import edu.gatech.chai.USCore.model.util.CommonUtil;

public class ObservationEducationLevelUtil {
	public static final ObservationStatus status = ObservationStatus.FINAL;
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "80913-7", "Highest level of education [US Standard Certificate of Death]s"));
	public static final String codeValueSystem = "urn:oid:2.16.840.1.114222.4.5.274";
	private static final String educationLevelV3SystemUrl = "http://terminology.hl7.org/CodeSystem/v3-EducationLevel";
	private static final String educationLevelV2SystemUrl = "http://terminology.hl7.org/CodeSystem/v2-0360";
	public static final CodeableConcept VALUE_8THGRADE = new CodeableConcept().addCoding(new Coding(educationLevelV3SystemUrl,"ELEM","Elementary School"));
	public static final CodeableConcept VALUE_12THGRADE = new CodeableConcept().addCoding(new Coding(educationLevelV3SystemUrl,"SEC","Some secondary or high school education"));
	public static final CodeableConcept VALUE_HIGHSCHOOL = new CodeableConcept().addCoding(new Coding(educationLevelV3SystemUrl,"HS","High School or secondary school degree complete"));
	public static final CodeableConcept VALUE_SOMECOLLEGE = new CodeableConcept().addCoding(new Coding(educationLevelV3SystemUrl,"SCOL","Some College education"));
	public static final CodeableConcept VALUE_ASSOCIATE = new CodeableConcept().addCoding(new Coding(educationLevelV2SystemUrl,"AA","Associate's or technical degree complete"));
	public static final CodeableConcept VALUE_BACHELORS = new CodeableConcept().addCoding(new Coding(educationLevelV2SystemUrl,"BA","Bachelor's degree"));
	public static final CodeableConcept VALUE_MASTERS = new CodeableConcept().addCoding(new Coding(educationLevelV2SystemUrl,"MA","Master's degree"));
	public static final CodeableConcept VALUE_DOCTORATE = new CodeableConcept().addCoding(new Coding(educationLevelV3SystemUrl,"POSTG","Doctoral or post graduate education"));
	
	public static final HashSet<CodeableConcept> valueSet = new HashSet<CodeableConcept>(Arrays.asList(
			VALUE_8THGRADE,
			VALUE_12THGRADE,
			VALUE_HIGHSCHOOL,
			VALUE_SOMECOLLEGE,
			VALUE_ASSOCIATE,
			VALUE_BACHELORS,
			VALUE_MASTERS,
			VALUE_DOCTORATE,
			CommonUtil.unknownCode
	));
}