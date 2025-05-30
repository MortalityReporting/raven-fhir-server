package edu.gatech.chai.VRDR.model.util;

import java.util.Arrays;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

public class AutopsyPerformedIndicatorUtil {
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "85699-7", "Autopsy was performed"));
	public static final CodeableConcept componentAutopsyResultsAvailableCode = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "69436-4", "Autopsy results available"));
	public static final ObservationStatus status = Observation.ObservationStatus.FINAL;
	public static final HashSet<CodeableConcept> booleanSet = new HashSet<>(Arrays.asList(CommonUtil.yesCode, CommonUtil.noCode, CommonUtil.notApplicableCode));
}