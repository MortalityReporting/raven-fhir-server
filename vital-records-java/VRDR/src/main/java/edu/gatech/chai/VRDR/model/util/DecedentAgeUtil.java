package edu.gatech.chai.VRDR.model.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

public class DecedentAgeUtil {
	public static final ObservationStatus status = ObservationStatus.FINAL;
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "30525-0", "Age"));
	public static final HashSet<String> values = new HashSet<>(Arrays.asList("mo","a"));
}
