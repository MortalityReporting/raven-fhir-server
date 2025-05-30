package edu.gatech.chai.VRDR.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

public class DecedentTransportationRoleUtil {
	public static final Observation.ObservationStatus status = ObservationStatus.FINAL;
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding().setSystem(CommonUtil.loincSystemUrl).setCode("69451-3")
					.setDisplay("Transportation role of decedent"));
	public static final CodeableConcept VALUE_DRIVER = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"236320001","Driver/Operator"));
	public static final CodeableConcept VALUE_PASSENGER = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"25750003","Passenger"));
	public static final CodeableConcept VALUE_PEDESTRIAN = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"25751800","Pedestrian"));
	public static Set<CodeableConcept> valueCodesetList = new HashSet<CodeableConcept>(Arrays.asList(VALUE_DRIVER,VALUE_PASSENGER,VALUE_PEDESTRIAN));
}