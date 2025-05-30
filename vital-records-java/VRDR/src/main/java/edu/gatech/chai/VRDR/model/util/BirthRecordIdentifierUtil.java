package edu.gatech.chai.VRDR.model.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

public class BirthRecordIdentifierUtil {
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.identifierTypeHL7System, "BR", "Birth registry number"));
	public static final CodeableConcept componentBirthStateCode = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "21842-0", "Birthplace"));
	public static final String componentBirthStateValueCodeableConceptSystem = "urn:iso:std:iso:3166:-2";
	public static final CodeableConcept componentBirthYearCode = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "80904-6", "Birth year"));
	public static final ObservationStatus status = Observation.ObservationStatus.FINAL;
}