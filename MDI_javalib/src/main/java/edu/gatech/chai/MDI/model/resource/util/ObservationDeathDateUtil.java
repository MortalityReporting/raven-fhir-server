package edu.gatech.chai.MDI.model.resource.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

public class ObservationDeathDateUtil {
    public static final CodeableConcept code = new CodeableConcept()
            .addCoding(new Coding(CommonUtil.loincSystemUrl, "81956-5", "Date+time of death"));
    public static final ObservationStatus status = ObservationStatus.FINAL;
    public static final CodeableConcept componentDatePronouncedDeadCode = new CodeableConcept()
            .addCoding(new Coding(CommonUtil.loincSystemUrl, "80616-6",
                    "Date and time pronounced dead [US Standard Certificate of Death]"));
    public static final CodeableConcept componentPlaceOfDeathCode = new CodeableConcept()
            .addCoding(new Coding(CommonUtil.loincSystemUrl, "58332-8", "Location of Death"));
    public static final CodeableConcept method = new CodeableConcept()
            .addCoding(new Coding(CommonUtil.snomedSystemUrl, "414135002", "Estimated"));
    public static final String deathDeterminationMethodUrl = "http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-date-of-death-determination-methods-cs";
    public static final String patientLocationExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-observation-location";
    public static final HashSet<CodeableConcept> deathDeterminationMethodSet = new HashSet<>(Arrays.asList(
            new CodeableConcept().addCoding(new Coding(deathDeterminationMethodUrl, "exact", "Exact")),
            new CodeableConcept().addCoding(new Coding(deathDeterminationMethodUrl, "approximate", "Approximate")),
            new CodeableConcept().addCoding(new Coding(deathDeterminationMethodUrl, "presumed", "Presumed")),
            new CodeableConcept().addCoding(new Coding(deathDeterminationMethodUrl, "16983000", "Death in hospital")),
            new CodeableConcept()
                    .addCoding(new Coding(deathDeterminationMethodUrl, "court-appointed", "Court Appointed"))));
    public static final HashSet<CodeableConcept> dateEstablishmentMethods = new HashSet<>(Arrays.asList(
            new CodeableConcept().addCoding(new Coding(CommonUtil.mdiCodesSystemURL, "exact", "Exact")),
            new CodeableConcept().addCoding(new Coding(CommonUtil.mdiCodesSystemURL, "approximate", "Approximate")),
            new CodeableConcept()
                    .addCoding(new Coding(CommonUtil.mdiCodesSystemURL, "court-appointed", "Court Appointed"))));

    public static final CodeableConcept VALUE_HOSPITAL = new CodeableConcept()
            .addCoding(new Coding(CommonUtil.snomedSystemUrl, "16983000", "Death in hospital"));
    public static final CodeableConcept VALUE_EMERGENCY_OUTPATIENT = new CodeableConcept()
            .addCoding(new Coding(CommonUtil.snomedSystemUrl, "450391000124102",
                    "Death in hospital-based emergency department or outpatient department (event)"));
    public static final CodeableConcept VALUE_HOME = new CodeableConcept()
            .addCoding(new Coding(CommonUtil.snomedSystemUrl, "440081000124100", "Death in home"));
    public static final CodeableConcept VALUE_HOSPICE = new CodeableConcept()
            .addCoding(new Coding(CommonUtil.snomedSystemUrl, "440071000124103", "Death in hospice"));
    public static final CodeableConcept VALUE_ARRIVAL = new CodeableConcept()
            .addCoding(new Coding(CommonUtil.snomedSystemUrl, "63238001", "Dead on arrival at hospital"));
    public static final CodeableConcept VALUE_NURSINGHOME = new CodeableConcept().addCoding(new Coding(
            CommonUtil.snomedSystemUrl, "450381000124100", "Death in nursing home or long term care facility (event)"));
    public static final HashSet<CodeableConcept> placeOfDeathTypeSet = new HashSet<>(Arrays.asList(
            VALUE_HOSPITAL,
            VALUE_EMERGENCY_OUTPATIENT,
            VALUE_HOME,
            VALUE_HOSPICE,
            VALUE_ARRIVAL,
            VALUE_NURSINGHOME,
            CommonUtil.otherCode,
            CommonUtil.unknownCode));
}