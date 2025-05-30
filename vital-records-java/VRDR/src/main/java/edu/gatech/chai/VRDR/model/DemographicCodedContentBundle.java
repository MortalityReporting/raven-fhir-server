package edu.gatech.chai.VRDR.model;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRCL.model.CodedRaceAndEthnicity;
import edu.gatech.chai.VRCL.model.InputRaceAndEthnicity;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Identifier;

@ResourceDef(name = "Bundle", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-demographic-coded-bundle")
public class DemographicCodedContentBundle extends Bundle {

	/**
	 *
	 */
	private static final long serialVersionUID = 1775205638319513588L;

	public DemographicCodedContentBundle() {
		super();
		CommonUtil.initResource(this);
		this.setType(BundleType.COLLECTION);
	}

	public DemographicCodedContentBundle addCertificateNumber(String value) {
		return addExtension("http://hl7.org/fhir/us/vrdr/StructureDefinition/CertificateNumber", value, 6);
	}

	public DemographicCodedContentBundle addAuxilaryStateIdentifier1 (String value) {
		return addExtension("http://hl7.org/fhir/us/vrdr/StructureDefinition/AuxiliaryStateIdentifier1", value, 12);
	}

	public DemographicCodedContentBundle addAuxilaryStateIdentifier2 (String value) {
		return addExtension("http://hl7.org/fhir/us/vrdr/StructureDefinition/AuxiliaryStateIdentifier2", value, 12);
	}

	public DemographicCodedContentBundle addExtension(String system, String value, int maxSize) {
		if(value.length() > maxSize) {
			throw new IllegalArgumentException(
					" Extension " + system + " value " + value + " is too long, must be " + maxSize + " characters or less.");
		}
		Identifier identifier = new Identifier();
		identifier.setSystem(system);
		identifier.setValue(value);
		setIdentifier(identifier);
		return this;
	}

	public CodedRaceAndEthnicity getCodedRaceAndEthnicity() {
		return CommonUtil.getSingleResource(CodedRaceAndEthnicity.class, this);
	}

	public InputRaceAndEthnicity getInputRaceAndEthnicity() {
		return CommonUtil.getSingleResource(InputRaceAndEthnicity.class, this);
	}
}