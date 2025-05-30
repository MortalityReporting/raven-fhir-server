package edu.gatech.chai.VRCL.model;

import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.IntegerType;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.StringType;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.USCore.model.USCoreRelatedPerson;
import edu.gatech.chai.USCore.model.util.CommonUtil;
import edu.gatech.chai.USCore.model.util.USCorePatientUtil;
import edu.gatech.chai.VRCL.model.util.PatientVitalRecordsUtil;
import edu.gatech.chai.VRCL.model.util.RelatedPersonParentUtil;

@ResourceDef(name = "RelatedPerson", profile = "http://hl7.org/fhir/us/vr-common-library/StructureDefinition/RelatedPerson-parent-vr")
public class RelatedPersonParentVitalRecords extends USCoreRelatedPerson {

	public RelatedPersonParentVitalRecords() {
		super();
		CommonUtil.initResource(this);
	}

	public RelatedPersonParentVitalRecords(Reference patient, boolean active,HumanName name) {
		super();
		CommonUtil.initResource(this);
		setPatient(patient);
		setActive(active);
		addName(name);
	}

	public Extension getRace() {
		return CommonUtil.getExtension(this, USCorePatientUtil.raceExtensionURL);
	}

	public Extension setRace(String ombCategory, Coding detailed, String text) {
		Extension extension = getRace();
		if (extension != null) {
			for (Extension subExtension : extension.getExtension()) {
				if (subExtension.getUrl().equals("ombCategory")) {
					((Coding) subExtension.getValue()).setCode(ombCategory);
				}
				if (subExtension.getUrl().equals("detailed")) {
					subExtension.setValue(detailed);
				}
				if (subExtension.getUrl().equals("text")) {
					subExtension.setValue(new StringType(text));
				}
			}
			return extension;
		}
		return addRace(ombCategory, detailed, text);
	}

	public Extension addRace(String ombCategory, Coding detailed, String text) {
		Extension extension = new Extension(USCorePatientUtil.raceExtensionURL);
		if(!ombCategory.isEmpty()) {
			Extension ombCategoryExt = new Extension("ombCategory",
					new Coding().setCode(ombCategory).setSystem(USCorePatientUtil.raceSystem));
			extension.addExtension(ombCategoryExt);
		}
		if(!detailed.isEmpty()) {
			Extension detailedExt = new Extension("detailed",detailed);
			extension.addExtension(detailedExt);
		}
		if(!text.isEmpty()) {
			Extension textExt = new Extension("text", new StringType(text));
			extension.addExtension(textExt);
		}
		addExtension(extension);
		return extension;
	}

	public Extension getEthnicity() {
		return CommonUtil.getExtension(this, USCorePatientUtil.ethnicityExtensionURL);
	}

	public Extension setEthnicity(String ombCategory, Coding detailed, String text) {
		Extension extension = getEthnicity();
		if (extension != null) {
			for (Extension subExtension : extension.getExtension()) {
				if (subExtension.getUrl().equals("ombCategory")) {
					((Coding) subExtension.getValue()).setCode(ombCategory);
				}
				if (subExtension.getUrl().equals("detailed")) {
					subExtension.setValue(detailed);
				}
				if (subExtension.getUrl().equals("text")) {
					subExtension.setValue(new StringType(text));
				}
			}
			return extension;
		}
		return addEthnicity(ombCategory, detailed, text);
	}

	public Extension addEthnicity(String ombCategory, Coding detailed, String text) {
		Extension extension = new Extension(USCorePatientUtil.ethnicityExtensionURL);
		if(!ombCategory.isEmpty()) {
			Extension ombCategoryExt = new Extension("ombCategory",
					new Coding().setCode(ombCategory).setSystem(USCorePatientUtil.ethnicitySystem));
			extension.addExtension(ombCategoryExt);
		}
		if(!detailed.isEmpty()) {
			Extension detailedExt = new Extension("detailed",detailed);
			extension.addExtension(detailedExt);
		}
		if(!text.isEmpty()) {
			Extension textExt = new Extension("text", new StringType(text));
			extension.addExtension(textExt);
		}
		addExtension(extension);
		return extension;


	}

	public Extension getDeceased() {
		return CommonUtil.getExtension(this, RelatedPersonParentUtil.deceasedExtensionUrl);
	}

	public Extension setDeceased(BooleanType deceased) {
		Extension extension = getDeceased();
		extension.setValue(deceased);
		addExtension(extension);
		return extension;
	}

	public Extension setDeceased(DateTimeType deceased) {
		Extension extension = getDeceased();
		extension.setValue(deceased);
		addExtension(extension);
		return extension;
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

    public Identifier addIdentifier(CodeableConcept type, String system, String value) {
		Identifier identifier = new Identifier();
		identifier.setType(type);
		identifier.setValue(value);
		identifier.setSystem(system);
		addIdentifier(identifier);
		return identifier;
	}

	public RelatedPersonParentVitalRecords addPartialBirthDateExtension(IntegerType year,String yearDataAbsentReason, IntegerType month,String monthDataAbsentReason,
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
	
	private RelatedPersonParentVitalRecords addPartialDateYear(Extension baseExtension, IntegerType year,String dataAbsentReason) {
		if(dataAbsentReason != null && !dataAbsentReason.isEmpty()) {
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateYearAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
		}
		else if(year != null && !year.isEmpty()){
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateYearURL,year));
		}
		return this;
	}
	
	private RelatedPersonParentVitalRecords addPartialDateMonth(Extension baseExtension, IntegerType month,String dataAbsentReason) {
		if(dataAbsentReason != null && !dataAbsentReason.isEmpty()) {
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateMonthAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
		}
		else if(month != null && !month.isEmpty()){
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateMonthURL,month));
		}
		return this;
	}
	
	private RelatedPersonParentVitalRecords addPartialDateDay(Extension baseExtension, IntegerType day,String dataAbsentReason) {
		if(dataAbsentReason != null && !dataAbsentReason.isEmpty()) {
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateDayAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
		}
		else if(day != null && !day.isEmpty()){
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateDayURL,day));
		}
		return this;
	}
}