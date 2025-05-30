package edu.gatech.chai.MDI.model.resource.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

public class ObservationAutopsyPerformedIndicatorUtil {
	public static final ObservationStatus status = ObservationStatus.FINAL;
	public static final CodeableConcept code = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"85699-7", ""));
	public static final CodeableConcept resultsAvailableComponentCode = new CodeableConcept().addCoding(
			new Coding(CommonUtil.loincSystemUrl,"69436-4", ""));
}