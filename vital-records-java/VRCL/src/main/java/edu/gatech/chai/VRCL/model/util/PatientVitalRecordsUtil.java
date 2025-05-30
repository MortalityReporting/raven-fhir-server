package edu.gatech.chai.VRCL.model.util;

import org.hl7.fhir.r4.model.Coding;

import edu.gatech.chai.USCore.model.util.CommonUtil;

public class PatientVitalRecordsUtil {
    public static final String birthPlaceExtensionURL = "http://hl7.org/fhir/StructureDefinition/patient-birthPlace";
    public static final String parentsReportAgeAtDeliveryExtensionURL = "http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Extension-reported-parent-age-at-delivery-vr";
    public static final String parentsReportAgeBaseAgeUrl = "reportedAge";
    public static final String motherOrFatherExtensionUrl = "reportedAge";
    public static final String parentsReportAgeReporterUrl = "reporter";
    public static final String fetalDeathExtensionUrl = "http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Extension-patient-fetal-death-vr";
    public static final Coding feathDeathCodingStaticValue = new Coding(CommonUtil.snomedSystemUrl, "276507005", "Fetal death (event)");
}
