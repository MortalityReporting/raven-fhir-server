package edu.gatech.chai.VRDR.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;

public class CodingStatusValuesUtil {
	public static final String intentionalRejectSystemUrl = "http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-intentional-reject-cs";
	public static final String systemRejectSystemUrl = "http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-system-reject-cs";
	public static Set<CodeableConcept> intentionalRejectValueset = new HashSet<CodeableConcept>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(intentionalRejectSystemUrl, "0", "Not Rejected")),
			new CodeableConcept().addCoding(new Coding(intentionalRejectSystemUrl, "1", "MICAR Reject Dictionary Match")),
			new CodeableConcept().addCoding(new Coding(intentionalRejectSystemUrl, "2", "ACME Reject")),
			new CodeableConcept().addCoding(new Coding(intentionalRejectSystemUrl, "3", "MICAR Reject Rule Application")),
			new CodeableConcept().addCoding(new Coding(intentionalRejectSystemUrl, "4", "Record Reviewed"))));
	public static Set<CodeableConcept> systemRejectValueset = new HashSet<CodeableConcept>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(systemRejectSystemUrl, "1", "Reject1")),
			new CodeableConcept().addCoding(new Coding(systemRejectSystemUrl, "2", "Reject2")),
			new CodeableConcept().addCoding(new Coding(systemRejectSystemUrl, "3", "Reject3")),
			new CodeableConcept().addCoding(new Coding(systemRejectSystemUrl, "4", "Reject4")),
			new CodeableConcept().addCoding(new Coding(systemRejectSystemUrl, "5", "Reject5")),
			new CodeableConcept().addCoding(new Coding(systemRejectSystemUrl, "9", "Reject9"))));
}