package edu.gatech.chai.VRCL.model;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.IntegerType;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.HumanName.NameUse;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.USCore.model.USCorePatient;
import edu.gatech.chai.USCore.model.util.CommonUtil;
import edu.gatech.chai.USCore.model.util.USCorePatientUtil;
import edu.gatech.chai.VRCL.model.util.PatientVitalRecordsUtil;
import edu.gatech.chai.VRCL.util.HumanNameParser;

@ResourceDef(name = "Patient", profile = "http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Patient-vr")
public class PatientVitalRecords extends USCorePatient{
    
    public PatientVitalRecords() {
        super();
        CommonUtil.initResource(this);
    }
    public Extension getBirthPlace() {
		return CommonUtil.getExtension(this, PatientVitalRecordsUtil.birthPlaceExtensionURL);
	}

	public Extension setBirthPlace(Address address) {
		Extension extension = getBirthPlace();
		if (extension != null) {
			extension.setValue(address);
			return extension;
		}
		return addBirthPlace(address);
	}

	public Extension addBirthPlace(Address address) {
		Extension extension = new Extension(PatientVitalRecordsUtil.birthPlaceExtensionURL);
		extension.setValue(address);
		addExtension(extension);
		return extension;
	}

	public Identifier addSSNIdentifier(String value) {
		return addIdentifier(USCorePatientUtil.identifierTypeSSValue, USCorePatientUtil.identifierSSSystem, value);
	}

    public Identifier addMRNIdentifier(String system, String value) {
		return addIdentifier(USCorePatientUtil.identifierTypeMRValue, system, value);
	}

    public Identifier addIdentifier(CodeableConcept type, String system, String value) {
		Identifier identifier = new Identifier();
		identifier.setType(type);
		identifier.setValue(value);
		identifier.setSystem(system);
		addIdentifier(identifier);
		return identifier;
	}

    public Extension addParentsReportedAgeAtDelivery(double ageInYears, CodeableConcept motherOrFather, Reference reporter){
        Extension baseExtension = new Extension(PatientVitalRecordsUtil.parentsReportAgeAtDeliveryExtensionURL);
        Extension ageExtension = new Extension(PatientVitalRecordsUtil.parentsReportAgeBaseAgeUrl);
        ageExtension.setValue(new Quantity().setValue(ageInYears).setUnit("a"));
        baseExtension.addExtension(ageExtension);
        Extension motherOrFatherExtension = new Extension(PatientVitalRecordsUtil.motherOrFatherExtensionUrl);
        motherOrFatherExtension.setValue(motherOrFather);
        baseExtension.addExtension(motherOrFatherExtension);
        if(reporter != null){
            Extension reporterExtension = new Extension(PatientVitalRecordsUtil.parentsReportAgeBaseAgeUrl);
			reporterExtension.setValue(reporter);
			baseExtension.addExtension(reporterExtension);
        }
		addExtension(baseExtension);
        return baseExtension;
    }

	public HumanName addCurrentLegalName(HumanName name){
		name.setUse(NameUse.OFFICIAL);
		addName(name);
		return name;
	}

	public HumanName addCurrentLegalName(String name){
		return addCurrentLegalName(HumanNameParser.createHumanName(name));
	}

	public HumanName addNamePriorToMarriage(HumanName name){
		name.setUse(NameUse.MAIDEN);
		addName(name);
		return name;
	}

	public HumanName addNamePriorToMarriage(String name){
		return addNamePriorToMarriage(HumanNameParser.createHumanName(name));
	}

	public Extension addFetalDeath(boolean fetalDeathValue){
		Extension extension = new Extension(PatientVitalRecordsUtil.fetalDeathExtensionUrl);
		extension.setValue(new BooleanType(fetalDeathValue));
		return extension;
	}

	public PatientVitalRecords addPartialBirthDateExtension(IntegerType year,String yearDataAbsentReason, IntegerType month,String monthDataAbsentReason,
			IntegerType day,String dayDataAbsentReason) {
		if(this.birthDate == null) {
			this.birthDate = new DateType();
		}
		Extension baseExtension = addPartialBirthDateBaseExtension();
		addPartialDateYear(baseExtension,year,yearDataAbsentReason);
		addPartialDateMonth(baseExtension,month,monthDataAbsentReason);
		addPartialDateDay(baseExtension,day,dayDataAbsentReason);
		this.birthDate.addExtension(baseExtension);
		return this;
	}
	
	private Extension addPartialBirthDateBaseExtension() {
		Extension baseExtension = new Extension(CommonUtil.partialDatePartAbsentReasonURL);
		return baseExtension;
	}
	
	private PatientVitalRecords addPartialDateYear(Extension baseExtension, IntegerType year,String dataAbsentReason) {
		if(dataAbsentReason != null && !dataAbsentReason.isEmpty()) {
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateYearAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
		}
		else if(year != null && !year.isEmpty()){
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateYearURL,year));
		}
		return this;
	}
	
	private PatientVitalRecords addPartialDateMonth(Extension baseExtension, IntegerType month,String dataAbsentReason) {
		if(dataAbsentReason != null && !dataAbsentReason.isEmpty()) {
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateMonthAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
		}
		else if(month != null && !month.isEmpty()){
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateMonthURL,month));
		}
		return this;
	}
	
	private PatientVitalRecords addPartialDateDay(Extension baseExtension, IntegerType day,String dataAbsentReason) {
		if(dataAbsentReason != null && !dataAbsentReason.isEmpty()) {
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateDayAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
		}
		else if(day != null && !day.isEmpty()){
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateDayURL,day));
		}
		return this;
	}
}