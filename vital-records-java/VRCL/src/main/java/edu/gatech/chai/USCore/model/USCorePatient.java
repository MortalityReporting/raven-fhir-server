package edu.gatech.chai.USCore.model;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.USCore.model.util.CommonUtil;
import edu.gatech.chai.USCore.model.util.USCorePatientUtil;

@ResourceDef(name = "USCorePatient", profile = "http://hl7.org/fhir/us/core/StructureDefinition/us-core-patient")
public class USCorePatient extends Patient {

	public USCorePatient() {
		super();
		CommonUtil.initResource(this);
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
		this.addExtension(extension);
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
		this.addExtension(extension);
		return extension;
	}

	public Extension getBirthSex() {
		return CommonUtil.getExtension(this, USCorePatientUtil.birthsexExtensionUrl);
	}

	public Extension setBirthSex(String value) {
		CodeableConcept ccSAD = CommonUtil.findConceptFromCollectionUsingSimpleString(value, USCorePatientUtil.birthSexExtensionSet);
		return setBirthSex(ccSAD);
	}
	
	public Extension setBirthSex(CodeableConcept value) {
		Extension extension = getBirthSex();
		if (extension == null) {
			extension = new Extension(USCorePatientUtil.birthsexExtensionUrl);
		}
		extension.setValue(value);
		this.addExtension(extension);
		return extension;
	}

	public Extension getGenderIdentity() {
		return CommonUtil.getExtension(this, USCorePatientUtil.genderIdentityExtensionURL);
	}

	public Extension setGenderIdentity(String value) {
		CodeableConcept ccSAD = CommonUtil.findConceptFromCollectionUsingSimpleString(value, USCorePatientUtil.genderIdentityExtensionSet);
		return setGenderIdentity(ccSAD);
	}
	
	public Extension setGenderIdentity(CodeableConcept value) {
		Extension extension = getGenderIdentity();
		if (extension == null) {
			extension = new Extension(USCorePatientUtil.genderIdentityExtensionURL);
		}
		extension.setValue(value);
		this.addExtension(extension);
		return extension;
	}

}