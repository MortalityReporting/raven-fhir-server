package edu.gatech.chai.VRDR.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

public class TobaccoUseContributedToDeathUtil {
	public static final ObservationStatus status = ObservationStatus.FINAL;
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding().setSystem(CommonUtil.loincSystemUrl).setCode("69443-0").setDisplay("Did tobacco use contribute to death"));
	public static final String codeValueSystem = "urn:oid:2.16.840.1.114222.4.5.274";
	public static final CodeableConcept VALUE_YESCODE = new CodeableConcept().addCoding(new Coding().setCode("373066001").setSystem(CommonUtil.snomedSystemUrl).setDisplay("Yes"));
	public static final CodeableConcept VALUE_NOCODE = new CodeableConcept().addCoding(new Coding().setCode("373067005").setSystem(CommonUtil.snomedSystemUrl).setDisplay("No"));
	public static final CodeableConcept VALUE_PROBABLECODE = new CodeableConcept().addCoding(new Coding().setCode("2931005").setSystem(CommonUtil.snomedSystemUrl).setDisplay("Probable diagnosis"));
	public static List<CodeableConcept> valueCodesetList = new ArrayList<CodeableConcept>(Arrays.asList(VALUE_YESCODE,VALUE_NOCODE,VALUE_PROBABLECODE,CommonUtil.unknownCode,CommonUtil.notAskedCode));
}