package edu.gatech.chai.MDI.model.resource.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class ProcedureDeathCertificationUtil {
	public static final CodeableConcept category = new CodeableConcept().addCoding(
			new Coding(CommonUtil.snomedSystemUrl,"103693007","Diagnostic procedure"));
    public static final CodeableConcept code = new CodeableConcept().addCoding(
            new Coding(CommonUtil.snomedSystemUrl,"308646001","Death Certification"));
    public static final HashSet<CodeableConcept> certifierTypes = new HashSet<>(Arrays.asList(
            new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl, "455381000124109", "Medical Examiner/Coroner")),
            new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl, "434641000124105", "Pronouncing & Certifying physician")),
            new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl, "434651000124107", "Certifying physician")),
            new CodeableConcept().addCoding(new Coding("http://terminology.hl7.org/CodeSystem/v3-NullFlavor", "OTH", "Other"))));
}