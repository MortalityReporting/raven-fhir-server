package edu.gatech.chai.VRDR.model.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

public class DeathDateUtil {
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "81956-5", "Date+time of death"));
	public static final ObservationStatus status = ObservationStatus.FINAL;
	public static final CodeableConcept componentDatePronouncedDeadCode = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "80616-6", "Date and time pronounced dead [US Standard Certificate of Death]"));
	public static final CodeableConcept componentPlaceOfDeathCode = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "58332-8", "Location of Death"));
	public static final CodeableConcept method = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.snomedSystemUrl, "414135002", "Estimated"));
	public static final String deathDeterminationMethodUrl = "http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-date-of-death-determination-methods-cs";
	public static final HashSet<CodeableConcept> deathDeterminationMethodSet = new HashSet<>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(deathDeterminationMethodUrl,"exact","Exact")),
			new CodeableConcept().addCoding(new Coding(deathDeterminationMethodUrl,"approximate","Approximate")),
			new CodeableConcept().addCoding(new Coding(deathDeterminationMethodUrl,"presumed","Presumed")),
			new CodeableConcept().addCoding(new Coding(deathDeterminationMethodUrl,"16983000","Death in hospital")),
			new CodeableConcept().addCoding(new Coding(deathDeterminationMethodUrl,"court-appointed","Court Appointed"))
			));
	public static final HashSet<CodeableConcept> placeOfDeathTypeSet = DeathLocationUtil.placeOfDeathTypeSet;
}