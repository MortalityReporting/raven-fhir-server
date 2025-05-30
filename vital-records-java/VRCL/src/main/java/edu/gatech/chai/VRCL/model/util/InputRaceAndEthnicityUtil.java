package edu.gatech.chai.VRCL.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

import edu.gatech.chai.USCore.model.util.CommonUtil;
import edu.gatech.chai.USCore.model.util.USCorePatientUtil;

public class InputRaceAndEthnicityUtil {

    public static final String codeSystemUrl = "http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-observations-cs";
    public static final String componentSystemUrl = "http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs";

    public static final CodeableConcept code = new CodeableConcept().addCoding(new Coding(codeSystemUrl, "inputraceandethnicity", "Race and Ethnicity Data submitted by Jurisdictions to NCHS"));

    public static final String hispanicCodingSystemUrl= "http://hl7.org/CodeSystem/v2-0136";
    
	public static Set<String> raceBooleanSystemStrings = new HashSet<String>(USCorePatientUtil.raceBooleanNVSSSet);
	public static Set<String> raceLiteralSystemStrings = new HashSet<String>(USCorePatientUtil.raceLiteralNVSSSet);
	public static Set<String> hispanicCodedSystemStrings = new HashSet<String>(USCorePatientUtil.hispanicCodedNVSSSet);
	public static Set<CodeableConcept> raceMissingValueReasonList = new HashSet<CodeableConcept>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(CommonUtil.missingValueReasonUrl,"R","Refused")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.missingValueReasonUrl,"S","Sought, but unknown")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.missingValueReasonUrl,"C","Not obtainable"))
			));

}