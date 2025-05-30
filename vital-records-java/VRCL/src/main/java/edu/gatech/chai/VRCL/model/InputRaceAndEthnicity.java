package edu.gatech.chai.VRCL.model;

import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.StringType;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.USCore.model.util.CommonUtil;
import edu.gatech.chai.VRCL.model.util.CodedRaceAndEthnicityUtil;
import edu.gatech.chai.VRCL.model.util.InputRaceAndEthnicityUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vr-common-library/StructureDefinition/input-race-and-ethnicity-vr")
public class InputRaceAndEthnicity extends Observation {
	public InputRaceAndEthnicity() {
		super();
		CommonUtil.initResource(this);
		this.setCode(InputRaceAndEthnicityUtil.code);
	}
	
	public InputRaceAndEthnicity(String... racesAndEthnicities) {
		for(String raceOrEth:racesAndEthnicities) {
			if(InputRaceAndEthnicityUtil.raceBooleanSystemStrings.contains(raceOrEth)) {
				addRaceBooleanComponent(raceOrEth);
			}
			else if(InputRaceAndEthnicityUtil.raceLiteralSystemStrings.contains(raceOrEth)) {
				addRaceLiteralComponent(raceOrEth, (String)null);
			}
			else if(InputRaceAndEthnicityUtil.hispanicCodedSystemStrings.contains(raceOrEth)) {
				addHispanicBooleanComponent(raceOrEth);
			}
		}
	}

	public void addRaceBooleanComponent(String codeName) {
		addComponent(codeName, new BooleanType(true));
	}
	public void addRaceBooleanComponent(String codeName, boolean value) {
		addComponent(codeName, new BooleanType(value));
	}
	
	public void addRaceBooleanComponent(String codeName, BooleanType value) {
		addComponent(codeName, value);
	}

	public void addRaceLiteralComponent(String codeName, String value) {
		addComponent(codeName, new StringType(value));
	}

	public void addRaceLiteralComponent(String codeName, StringType value) {
		addComponent(codeName, value);
	}

	public void addHispanicBooleanComponent(String codeName) {
		addComponent(codeName, CommonUtil.findConceptFromCollectionUsingSimpleString("unknown", CommonUtil.yesNoUnknownSet));
	}

	public void addHispanicBooleanComponent(String codeName, boolean value) {
		addComponent(codeName, CommonUtil.findConceptFromCollectionUsingSimpleString(value ? "Yes" : "No", CommonUtil.yesNoUnknownSet));
	}

	public void addHispanicBooleanComponentUnknown(String codeName) {
		addComponent(codeName, CommonUtil.findConceptFromCollectionUsingSimpleString("unknown", CommonUtil.yesNoUnknownSet));
	}

	public void addMissingRaceValueReason(String value) {
		CodeableConcept mrvrCodeableConcept = CommonUtil.findConceptFromCollectionUsingSimpleString(value, InputRaceAndEthnicityUtil.raceMissingValueReasonList);
		addComponent("RACEMVR",mrvrCodeableConcept);
	}
	
	public void addComponent(String codeName, BooleanType value) {
		ObservationComponentComponent occ = addComponentCommon(codeName);
		occ.setValue(value);
		this.addComponent(occ);
	}
	
	public void addComponent(String codeName, CodeableConcept value) {
		ObservationComponentComponent occ = addComponentCommon(codeName);
		occ.setValue(value);
		this.addComponent(occ);
	}
	
	public void addComponent(String codeName, StringType value) {
		ObservationComponentComponent occ = addComponentCommon(codeName);
		occ.setValue(value);
		this.addComponent(occ);
	}
	
	private ObservationComponentComponent addComponentCommon(String codeName) {
		ObservationComponentComponent occ = new ObservationComponentComponent();
		occ.setCode(new CodeableConcept().addCoding(new Coding(InputRaceAndEthnicityUtil.componentSystemUrl,codeName,"")));
		addComponent(occ);
		return occ;
	}

	public String getValueCodeableConceptCodeForCoding(CodeableConcept codeableConcept) {
		return CommonUtil.findObservationComponentComponentValueCodeableConceptCodeForCoding(getComponent(),
				codeableConcept.getCodingFirstRep());
	}

	public String getHispanicMexicanCodedValue() {
		return getValueCodeableConceptCodeForCoding(CodedRaceAndEthnicityUtil.hispanicMexicanCodeComponentCode);
	}

	public String getHispanicPuertoRicanCodedValue() {
		return getValueCodeableConceptCodeForCoding(CodedRaceAndEthnicityUtil.hispanicPuertoRicanCodeComponentCode);
	}

	public String getHispanicCubeanCodedValue() {
		return getValueCodeableConceptCodeForCoding(CodedRaceAndEthnicityUtil.hispanicCubanCodeComponentCode);
	}

	public String getHispanicOtherCodedValue() {
		return getValueCodeableConceptCodeForCoding(CodedRaceAndEthnicityUtil.hispanicOtherCodeComponentCode);
	}

	public String getHispanicLiteralValue() {
		return getStringValueForCode("HispanicLiteral");
	}

	public boolean getWhiteBooleanValue() {
		return getBooleanValueForCode("White");
	}

	public boolean getBlackOrAfricanAmericanBooleanValue() {
		return getBooleanValueForCode("BlackOrAfricanAmerican");
	}

	public boolean getAmericanIndianOrAlaskaNativeBooleanValue() {
		return getBooleanValueForCode("AmericanIndianOrAlaskanNative");
	}

	public boolean getAsianIndianBooleanValue() {
		return getBooleanValueForCode("AsianIndian");
	}

	public boolean getChineseBooleanValue() {
		return getBooleanValueForCode("Chinese");
	}

	public boolean getFilipinoBooleanValue() {
		return getBooleanValueForCode("Filipino");
	}

	public boolean getJapaneseBooleanValue() {
		return getBooleanValueForCode("Japanese");
	}

	public boolean getKoreanBooleanValue() {
		return getBooleanValueForCode("Korean");
	}

	public boolean getVietnameseBooleanValue() {
		return getBooleanValueForCode("Vietnamese");
	}

	public boolean getOtherAsianBooleanValue() {
		return getBooleanValueForCode("OtherAsian");
	}

	public boolean getNativeHawaiianBooleanValue() {
		return getBooleanValueForCode("NativeHawaiian");
	}

	public boolean getGuamanianOrChamorroBooleanValue() {
		return getBooleanValueForCode("GuamanianOrChamorro");
	}

	public boolean getSamoanBooleanValue() {
		return getBooleanValueForCode("Samoan");
	}

	public boolean getOtherPacificIslanderBooleanValue() {
		return getBooleanValueForCode("OtherPacificIslander");
	}

	public boolean getOtherRaceBooleanValue() {
		return getBooleanValueForCode("OtherRace");
	}

	public String getFirstAmericanIndianOrAlaskaNativeLiteralValue() {
		return getStringValueForCode("FirstAmericanIndianOrAlaskanNativeLiteral");
	}

	public String getSecondAmericanIndianOrAlaskaNativeLiteralValue() {
		return getStringValueForCode("SecondAmericanIndianOrAlaskanNativeLiteral");
	}

	public String getFirstOtherAsianLiteralValue() {
		return getStringValueForCode("FirstOtherAsianLiteral");
	}

	public String getSecondOtherAsianLiteralValue() {
		return getStringValueForCode("SecondOtherAsianLiteral");
	}

	public String getFirstOtherPacificIslanderLiteralValue() {
		return getStringValueForCode("FirstOtherPacificIslanderLiteral");
	}

	public String getSecondOtherPacificIslanderLiteralValue() {
		return getStringValueForCode("SecondOtherPacificIslanderLiteral");
	}

	public String getFirstOtherRaceLiteralValue() {
		return getStringValueForCode("FirstOtherRaceLiteral");
	}

	public String getSecondOtherRaceLiteralValue() {
		return getStringValueForCode("SecondOtherRaceLiteral");
	}

	public boolean getBooleanValueForCode(String codeName) {
		return getComponent().stream().filter(x ->
			x.getCode().getCodingFirstRep().getCode().equals(codeName))
			.findFirst()
			.orElse(new Observation.ObservationComponentComponent().setValue(new BooleanType(false)))
			.getValueBooleanType().booleanValue();
	}

	public String getStringValueForCode(String codeName) {
		return getComponent().stream().filter(x ->
			x.getCode().getCodingFirstRep().getCode().equals(codeName))
			.findFirst()
			.orElse(new Observation.ObservationComponentComponent())
			.getValueStringType().getValue();
	}
}
