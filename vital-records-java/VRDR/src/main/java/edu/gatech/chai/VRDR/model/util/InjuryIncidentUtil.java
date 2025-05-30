package edu.gatech.chai.VRDR.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

public class InjuryIncidentUtil {
	public static final ObservationStatus status = ObservationStatus.FINAL;
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "11374-6", "Injury incident description"));
	public static final CodeableConcept componentPlaceofInjuryCode = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "69450-5", "Place of injury Facility"));
	public static final CodeableConcept componentInjuryAtWorkCode = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "69444-8", "Did death result from injury at work"));
	public static final CodeableConcept componentTransportationEventIndicator = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "69448-9",
					"Injury leading to death associated with transportation event"));
	public static final String componentPlaceOfInjuryValueSystem = "urn:oid:2.16.840.1.114222.4.5.320";
	public static final CodeableConcept POI_HOME = new CodeableConcept().addCoding(new Coding(componentPlaceOfInjuryValueSystem,"0","Home"));
	public static final CodeableConcept POI_RES = new CodeableConcept().addCoding(new Coding(componentPlaceOfInjuryValueSystem,"1","Residential Institution"));
	public static final CodeableConcept POI_SCHOOL = new CodeableConcept().addCoding(new Coding(componentPlaceOfInjuryValueSystem,"2","School, Other Institutions, Public Administrative Area"));
	public static final CodeableConcept POI_SPORT = new CodeableConcept().addCoding(new Coding(componentPlaceOfInjuryValueSystem,"3","Sports and Athletics Area"));
	public static final CodeableConcept POI_STREET = new CodeableConcept().addCoding(new Coding(componentPlaceOfInjuryValueSystem,"4","Street/Highway"));
	public static final CodeableConcept POI_TRADE = new CodeableConcept().addCoding(new Coding(componentPlaceOfInjuryValueSystem,"5","Trade and Service Area"));
	public static final CodeableConcept POI_INDUSTRY = new CodeableConcept().addCoding(new Coding(componentPlaceOfInjuryValueSystem,"6","Industrial and Construction Area"));
	public static final CodeableConcept POI_FARM = new CodeableConcept().addCoding(new Coding(componentPlaceOfInjuryValueSystem,"7","Farm"));
	public static final CodeableConcept POI_OTHER = new CodeableConcept().addCoding(new Coding(componentPlaceOfInjuryValueSystem,"8","Other Specified Place"));
	public static final CodeableConcept POI_UNSPECIFIED = new CodeableConcept().addCoding(new Coding(componentPlaceOfInjuryValueSystem,"9","Unpecified Place"));
	public static Set<CodeableConcept> placeOfInjuryValueSet = new HashSet<CodeableConcept>(Arrays.asList(
			POI_HOME,POI_RES,POI_SCHOOL,POI_SPORT,POI_STREET,POI_TRADE,POI_INDUSTRY,POI_FARM,POI_OTHER,POI_UNSPECIFIED));
	public static final CodeableConcept transportationRoleCode = new CodeableConcept().addCoding(new Coding(CommonUtil.loincSystemUrl, "69451-3", "Transportation role of decedent"));
	public static final CodeableConcept driverRoleCode = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl, "236320001", "Vehicle driver"));
	public static final CodeableConcept passengerRoleCode = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl, "257500003", "Passenger"));
	public static final CodeableConcept pedestrianRoleCode = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl, "257518000", "Pedestrian"));
	public static Set<CodeableConcept> transportationRoleValueSet = new HashSet<CodeableConcept>(Arrays.asList(
		driverRoleCode,passengerRoleCode,pedestrianRoleCode,CommonUtil.otherCode, CommonUtil.unknownCode));
}