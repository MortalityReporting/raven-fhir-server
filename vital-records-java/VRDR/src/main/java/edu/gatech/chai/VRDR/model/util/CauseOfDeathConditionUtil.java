package edu.gatech.chai.VRDR.model.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class CauseOfDeathConditionUtil {
	public static final String conditionalClinicalSystemUrl = "http://terminology.hl7.org/CodeSystem/condition-clinical";
	public static final String verificationSystemUrl = "http://terminology.hl7.org/CodeSystem/condition-ver-status";
	public static final HashSet<CodeableConcept> conditionClinicalStatusSet = new HashSet<>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(conditionalClinicalSystemUrl,"active","Active")),
			new CodeableConcept().addCoding(new Coding(conditionalClinicalSystemUrl,"recurrence","Recurrence")),
			new CodeableConcept().addCoding(new Coding(conditionalClinicalSystemUrl,"relapse","Relapse")),
			new CodeableConcept().addCoding(new Coding(conditionalClinicalSystemUrl,"inactive","Inactive")),
			new CodeableConcept().addCoding(new Coding(conditionalClinicalSystemUrl,"remission","Remission")),
			new CodeableConcept().addCoding(new Coding(conditionalClinicalSystemUrl,"resolved","Resolved"))));
	public static final HashSet<CodeableConcept> verificationStatusSet = new HashSet<>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(verificationSystemUrl,"unconfirmed","Unconfirmed")),
			new CodeableConcept().addCoding(new Coding(verificationSystemUrl,"provisional","Provisional")),
			new CodeableConcept().addCoding(new Coding(verificationSystemUrl,"differential","Differential")),
			new CodeableConcept().addCoding(new Coding(verificationSystemUrl,"confirmed","Confirmed")),
			new CodeableConcept().addCoding(new Coding(verificationSystemUrl,"refuted","Refuted")),
			new CodeableConcept().addCoding(new Coding(verificationSystemUrl,"entered-in-error","Entered-in-Error"))));
	public static final CodeableConcept code = new CodeableConcept().addCoding(new Coding(CommonUtil.loincSystemUrl, "69453-9", "Cause of death [US Standard Certificate of Death]"));
	public static final CodeableConcept intervalComponentCode = new CodeableConcept().addCoding(new Coding(CommonUtil.loincSystemUrl, "69440-6", "Disease onset to death interval"));

    public static CodeableConcept createICD10CodeableConcept(String code) {
        return new CodeableConcept().addCoding(new Coding(CommonUtil.icd10SystemUrl, code, null));
    }
}