package edu.gatech.chai.VRCL.model.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

import edu.gatech.chai.USCore.model.util.CommonUtil;

public class ObservationUsualWorkUtil {
	public static final Observation.ObservationStatus status = ObservationStatus.FINAL;
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding().setSystem(CommonUtil.loincSystemUrl).setCode("21843-8").setDisplay("History of Usual occupation"));
	public static final CodeableConcept componentUsualIndustryCode = new CodeableConcept()
			.addCoding(new Coding().setSystem(CommonUtil.loincSystemUrl).setCode("21844-6").setDisplay("Usual Industry"));
	public static final CodeableConcept componentUsualOccupationDuraction= new CodeableConcept()
			.addCoding(new Coding().setSystem(CommonUtil.loincSystemUrl).setCode("7416307"));
}