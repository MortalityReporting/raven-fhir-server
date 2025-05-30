package edu.gatech.chai.VRDR.model.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class DeathLocationUtil {
	public static final CodeableConcept VALUE_HOSPITAL = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"16983000","Death in hospital"));
	public static final CodeableConcept VALUE_EMERGENCY_OUTPATIENT = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"450391000124102","Death in hospital-based emergency department or outpatient department (event)"));
	public static final CodeableConcept VALUE_HOME = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"440081000124100","Death in home"));
	public static final CodeableConcept VALUE_HOSPICE = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"440071000124103","Death in hospice"));
	public static final CodeableConcept VALUE_ARRIVAL = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"63238001","Dead on arrival at hospital"));
	public static final CodeableConcept VALUE_NURSINGHOME = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"450381000124100","Death in nursing home or long term care facility (event)"));
	public static final CodeableConcept VALUE_UNKNOWN = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"UNK","Unknown"));
	public static final CodeableConcept VALUE_OTHER = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"OTH","Other"));
	public static final HashSet<CodeableConcept> placeOfDeathTypeSet = new HashSet<>(Arrays.asList(
			VALUE_HOSPITAL,
			VALUE_EMERGENCY_OUTPATIENT,
			VALUE_HOME,
			VALUE_HOSPICE,
			VALUE_ARRIVAL,
			VALUE_NURSINGHOME,
			VALUE_OTHER,
			VALUE_UNKNOWN
	));
	public static CodeableConcept typeCode = new CodeableConcept(new Coding().setSystem("http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-location-type-cs").setCode("death").setDisplay("death location"));
}
