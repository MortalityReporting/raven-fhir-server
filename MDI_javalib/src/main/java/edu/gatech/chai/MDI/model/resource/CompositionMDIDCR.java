package edu.gatech.chai.MDI.model.resource;

import java.util.Date;
import java.util.HashSet;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.MDI.model.resource.util.CommonUtil;
import edu.gatech.chai.MDI.model.resource.util.CompositionMDIDCRUtil;
import edu.gatech.chai.VRCL.model.PractitionerVitalRecords;
import edu.gatech.chai.VRDR.model.Decedent;

@ResourceDef(name = "Composition", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/Composition-mdi-dcr")
public class CompositionMDIDCR extends Composition{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5634970999252166773L;

	public CompositionMDIDCR() {
		super();
		this.setType(CompositionMDIDCRUtil.type);
	}
	
	public CompositionMDIDCR(Identifier identifier, CompositionStatus status, Date date, Decedent subject,PractitionerVitalRecords author) {
		super();
		Reference authorRef = new Reference(author);
		commonInit(identifier,status,date,subject,authorRef);
	}
	
	
	public void commonInit(Identifier identifier, CompositionStatus status, Date date, Decedent subject,Reference authorRef) {
		this.setIdentifier(identifier);
		this.setStatus(status);
		if(date == null) {
			date = new Date();
		}
		this.setDate(date);
		Reference subjectRef = new Reference(subject);
		this.setSubject(subjectRef);
		this.addAuthor(authorRef);
		//Generic Title
		this.setTitle("MDI-And-EDRS Record:"+identifier.getValue());
	}

	public CompositionAttesterComponent addAttester(Reference attestorRef){
		CompositionAttesterComponent cac = new CompositionAttesterComponent();
		cac.setParty(attestorRef);
		//Note: we're assuming a LEGAL attestor for now.
		cac.setMode(CompositionAttestationMode.LEGAL);
		this.addAttester(cac);
		return cac;
	}

	public CompositionAttesterComponent addAttester(String dataAbsentReason){
		CompositionAttesterComponent cac = new CompositionAttesterComponent();
		// CodeType dataAbsentReasonCode = CompositionMDIDCRUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CompositionMDIDCRUtil.dataAbsentReasonCodeSet);
		CommonUtil.setDataAbsentReason(cac.getParty(), CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet));
		this.addAttester(cac);
		return cac;
	}
	
	public SectionComponent createDecedentDemographicsSection() {
		return createSection(CompositionMDIDCRUtil.decedentDemographicsSectionCode);
	}
	
	public SectionComponent createDeathInvestigationSection() {
		return createSection(CompositionMDIDCRUtil.deathInvestigationSectionCode);
	}
	
	public SectionComponent createDeathCertificationSection() {
		return createSection(CompositionMDIDCRUtil.deathCertificationSectionCode);
	}
	
	public SectionComponent createDecedentDispositionSection() {
		return createSection(CompositionMDIDCRUtil.decdentDispositionSectionCode);
	}
	
	public SectionComponent createDeathCertificateDataReview() {
		return createSection(CompositionMDIDCRUtil.deathCertificateDataReviewSectionCode);
	}
	
	public SectionComponent createCreamationClearanceInfoSection() {
		return createSection(CompositionMDIDCRUtil.cremationClearanceInfoSectionCode);
	}
	
	protected SectionComponent createSection(CodeableConcept codeableConcept) {
		SectionComponent secComp = new SectionComponent();
		secComp.setCode(codeableConcept);
		this.addSection(secComp);
		return secComp;
	}
	
	public SectionComponent getDecedentDemographicsSection() {
		SectionComponent returnValue = getSection(CompositionMDIDCRUtil.decedentDemographicsSectionCode);
		if(returnValue == null) {
			returnValue = createDecedentDemographicsSection();
		}
		return returnValue;
	}
	
	public SectionComponent getDeathInvestigationSection() {
		SectionComponent returnValue = getSection(CompositionMDIDCRUtil.deathInvestigationSectionCode);
		if(returnValue == null) {
			returnValue = createDeathInvestigationSection();
		}
		return returnValue;
	}

	public SectionComponent getDeathCertificationSection() {
		SectionComponent returnValue = getSection(CompositionMDIDCRUtil.deathCertificationSectionCode);
		if(returnValue == null) {
			returnValue = createDeathCertificationSection();
		}
		return returnValue;
	}

	public SectionComponent getDeathCertificateDataReview() {
		SectionComponent returnValue = getSection(CompositionMDIDCRUtil.deathCertificateDataReviewSectionCode);
		if(returnValue == null) {
			returnValue = createDeathCertificateDataReview();
		}
		return returnValue;
	}

	public SectionComponent getCremationClearanceInfoSection() {
		SectionComponent returnValue = getSection(CompositionMDIDCRUtil.cremationClearanceInfoSectionCode);
		if(returnValue == null) {
			returnValue = createCreamationClearanceInfoSection();
		}
		return returnValue;
	}
	
	protected SectionComponent getSection(CodeableConcept codeableConcept) {
		if(this.section == null) {
			return null;
		}
		for(SectionComponent secComp:this.section) {
			if(secComp.getCode().equals(codeableConcept)) {
				return secComp;
			}
		}
		return null;
	}

	public Extension addMDICaseIdExtension(String mdiCaseValue) {
		return addCaseIdExtension(CompositionMDIDCRUtil.trackingNumberMDIType, "", mdiCaseValue);
	}

	public Extension addMDICaseIdExtension(String mdiCaseSystem, String mdiCaseValue) {
		return addCaseIdExtension(CompositionMDIDCRUtil.trackingNumberMDIType, mdiCaseSystem, mdiCaseValue);
	}

	public Extension addEDRSCaseIdExtension(String edrsCaseValue) {
		return addCaseIdExtension(CompositionMDIDCRUtil.trackingNumberEDRSType, "", edrsCaseValue);
	}

	public Extension addEDRSCaseIdExtension(String edrsCaseSystem, String edrsCaseValue) {
		return addCaseIdExtension(CompositionMDIDCRUtil.trackingNumberEDRSType, edrsCaseSystem, edrsCaseValue);
	}

	public Extension addTOXCaseIdExtension(String toxCaseValue) {
		return addCaseIdExtension(CompositionMDIDCRUtil.trackingNumberTOXType, "", toxCaseValue);
	}

	public Extension addTOXCaseIdExtension(String toxCaseSystem, String toxCaseValue) {
		return addCaseIdExtension(CompositionMDIDCRUtil.trackingNumberTOXType, toxCaseSystem, toxCaseValue);
	}

	public Extension addFHCaseIdExtension(String toxCaseValue) {
		return addCaseIdExtension(CompositionMDIDCRUtil.trackingNumberFHType, "", toxCaseValue);
	}

	public Extension addFHCaseIdExtension(String toxCaseSystem, String toxCaseValue) {
		return addCaseIdExtension(CompositionMDIDCRUtil.trackingNumberFHType, toxCaseSystem, toxCaseValue);
	}

	public Extension addDeathCertificateStatusExtension(){
		Extension returnExtension = new Extension();
		returnExtension.setUrl(CompositionMDIDCRUtil.deathCertificateStatusExtensionURL);
		this.addExtension(returnExtension);
		return returnExtension;
	}

	public Extension addDCCertificationStatus(String dCertificationValue){
		Extension deathCertificateStatusExtension = CommonUtil.getExtension(this, CompositionMDIDCRUtil.deathCertificateStatusExtensionURL);
		if(deathCertificateStatusExtension == null){
			deathCertificateStatusExtension = this.addDeathCertificateStatusExtension();
		}
		Extension dCCertificationStatusExtension = CommonUtil.getExtension(deathCertificateStatusExtension, CompositionMDIDCRUtil.dCCertificationExtensionURL);
		if(dCCertificationStatusExtension == null){
			dCCertificationStatusExtension = new Extension();
			dCCertificationStatusExtension.setUrl(CompositionMDIDCRUtil.dCCertificationExtensionURL);
			CodeableConcept value = CommonUtil.findConceptFromCollectionUsingSimpleString(dCertificationValue, CompositionMDIDCRUtil.dCCertificationSet);
			dCCertificationStatusExtension.setValue(value);
			deathCertificateStatusExtension.addExtension(dCCertificationStatusExtension);
		}
		return dCCertificationStatusExtension;
	}

	public Extension addDCRegistrationStatus(String dRegistrationValue){
		Extension deathCertificateStatusExtension = CommonUtil.getExtension(this, CompositionMDIDCRUtil.deathCertificateStatusExtensionURL);
		if(deathCertificateStatusExtension == null){
			deathCertificateStatusExtension = this.addDeathCertificateStatusExtension();
		}
		Extension dCRegistrationStatusExtension = CommonUtil.getExtension(deathCertificateStatusExtension, CompositionMDIDCRUtil.dCRegistrationExtensionURL);
		if(dCRegistrationStatusExtension == null){
			dCRegistrationStatusExtension = new Extension();
			dCRegistrationStatusExtension.setUrl(CompositionMDIDCRUtil.dCRegistrationExtensionURL);
			CodeableConcept value = CommonUtil.findConceptFromCollectionUsingSimpleString(dRegistrationValue, CompositionMDIDCRUtil.dCRRegistrationSet);
			dCRegistrationStatusExtension.setValue(value);
			deathCertificateStatusExtension.addExtension(dCRegistrationStatusExtension);
		}
		return dCRegistrationStatusExtension;
	}

	public static final String cremationClearanceStatusExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-cremation-clearance-status";
	public static final String meCertificationAffirmationExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-me-certification-affirmation";
	public static final String cremationClearanceCoronerExtensionURL = "http://hl7.org/fhir/us/mdi/StructureDefinition/Extension-cremation-clearance-coroner";

	public Extension addCremationClearanceStatusExtension(){
		return genericAddExtension(CompositionMDIDCRUtil.cremationClearanceCoronerExtensionURL);
	}

	public Extension addCremationClearanceStatusExtension(String creamationClearanceValue){
		return genericAddExtensionWithUrlAndValue(CompositionMDIDCRUtil.dCCertificationExtensionURL, creamationClearanceValue, CompositionMDIDCRUtil.creamationClearanceStatusSet);
	}

	public Extension addMeCertificationAffirmationExtension(){
		return genericAddExtension(CompositionMDIDCRUtil.meCertificationAffirmationExtensionURL);
	}

	public Extension addMeCertificationAffirmationExtension(String meCertificationValue){
		return genericAddExtensionWithUrlAndValue(CompositionMDIDCRUtil.dCCertificationExtensionURL, meCertificationValue, CompositionMDIDCRUtil.creamationClearanceStatusSet);
	}

	public Extension addCremationClearanceCoronerExtension(String cremationClearanceCoronerValue){
		return genericAddExtensionWithUrlAndValue(CompositionMDIDCRUtil.cremationClearanceCoronerExtensionURL, cremationClearanceCoronerValue, edu.gatech.chai.USCore.model.util.CommonUtil.yesNoUnknownSet);
	}

	protected Extension genericAddExtensionWithUrlAndValue(String URL, String value, HashSet<CodeableConcept> valueset){
		Extension returnExtension = CommonUtil.getExtension(this, CompositionMDIDCRUtil.dCCertificationExtensionURL);
		if(returnExtension == null){
			returnExtension = this.genericAddExtension(URL);
		}
		CodeableConcept valueCC = CommonUtil.findConceptFromCollectionUsingSimpleString(value, valueset);
		returnExtension.setValue(valueCC);
		return returnExtension;
	}

	protected Extension genericAddExtension(String URL){
		Extension returnExtension = new Extension();
		returnExtension.setUrl(URL);
		this.addExtension(returnExtension);
		return returnExtension;
	}

	public Extension addCaseIdExtension(CodeableConcept extensionType, String mdiCaseSystem, String mdiCaseValue) {
		Extension returnExtension = new Extension();
		returnExtension.setUrl(CompositionMDIDCRUtil.trackingNumberExtensionURL);
		Identifier identifier = new Identifier();
		identifier.setType(extensionType);
		if(mdiCaseSystem != null && !mdiCaseSystem.isEmpty()){
			identifier.setSystem(mdiCaseSystem);
		}
		if(mdiCaseValue != null && !mdiCaseValue.isEmpty()){
			identifier.setValue(mdiCaseValue);
		}
		returnExtension.setValue(identifier);
		this.addExtension(returnExtension);
		return returnExtension;
	}
}