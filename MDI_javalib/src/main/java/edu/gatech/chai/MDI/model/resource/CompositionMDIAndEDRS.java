package edu.gatech.chai.MDI.model.resource;

import java.util.Date;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.MDI.model.resource.util.CompositionMDIAndEDRSUtil;
import edu.gatech.chai.USCore.model.util.CommonUtil;
import edu.gatech.chai.VRCL.model.PatientVitalRecords;
import edu.gatech.chai.VRCL.model.PractitionerVitalRecords;

@ResourceDef(name = "Composition", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/Composition-mdi-and-edrs")
public class CompositionMDIAndEDRS extends Composition{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5634970999252166773L;

	public CompositionMDIAndEDRS() {
		super();
		this.setType(CompositionMDIAndEDRSUtil.type);
	}
	
	public CompositionMDIAndEDRS(Identifier identifier, CompositionStatus status, Date date, PatientVitalRecords subject,PractitionerVitalRecords author) {
		super();
		Reference authorRef = new Reference(author);
		commonInit(identifier,status,date,subject,authorRef);
	}
	
	
	public void commonInit(Identifier identifier, CompositionStatus status, Date date, PatientVitalRecords subject,Reference authorRef) {
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
		// CodeType dataAbsentReasonCode = CompositionMDIAndEDRSUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CompositionMDIAndEDRSUtil.dataAbsentReasonCodeSet);
		CommonUtil.setDataAbsentReason(cac.getParty(), CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet));
		this.addAttester(cac);
		return cac;
	}
	
	public SectionComponent createDemographicsSection() {
		return createSection(CompositionMDIAndEDRSUtil.demographicsSectionCode);
	}
	
	public SectionComponent createCircumstancesSection() {
		return createSection(CompositionMDIAndEDRSUtil.circumstancesSectionCode);
	}
	
	public SectionComponent createJurisdictionSection() {
		return createSection(CompositionMDIAndEDRSUtil.jurisdictionSectionCode);
	}
	
	public SectionComponent createCauseMannerSection() {
		return createSection(CompositionMDIAndEDRSUtil.causeMannerSectionCode);
	}
	
	public SectionComponent createMedicalHistorySection() {
		return createSection(CompositionMDIAndEDRSUtil.medicalHistorySectionCode);
	}
	
	public SectionComponent createExamAutopsySection() {
		return createSection(CompositionMDIAndEDRSUtil.examAutopsySectionCode);
	}
	
	public SectionComponent createNarrativeSection() {
		return createSection(CompositionMDIAndEDRSUtil.narrativeSectionCode);
	}
	
	protected SectionComponent createSection(CodeableConcept codeableConcept) {
		SectionComponent secComp = new SectionComponent();
		secComp.setCode(codeableConcept);
		this.addSection(secComp);
		return secComp;
	}
	
	public SectionComponent getDemographicsSection() {
		SectionComponent returnValue = getSection(CompositionMDIAndEDRSUtil.demographicsSectionCode);
		if(returnValue == null) {
			returnValue = createDemographicsSection();
		}
		return returnValue;
	}
	
	public SectionComponent getCircumstancesSection() {
		SectionComponent returnValue = getSection(CompositionMDIAndEDRSUtil.circumstancesSectionCode);
		if(returnValue == null) {
			returnValue = createCircumstancesSection();
		}
		return returnValue;
	}
	
	public SectionComponent getJurisdictionSection() {
		SectionComponent returnValue = getSection(CompositionMDIAndEDRSUtil.jurisdictionSectionCode);
		if(returnValue == null) {
			returnValue = createJurisdictionSection();
		}
		return returnValue;
	}
	
	public SectionComponent getCauseMannerSection() {
		SectionComponent returnValue = getSection(CompositionMDIAndEDRSUtil.causeMannerSectionCode);
		if(returnValue == null) {
			returnValue = createCauseMannerSection();
		}
		return returnValue;
	}
	
	public SectionComponent getMedicalHistorySection() {
		SectionComponent returnValue = getSection(CompositionMDIAndEDRSUtil.medicalHistorySectionCode);
		if(returnValue == null) {
			returnValue = createMedicalHistorySection();
		}
		return returnValue;
	}
	
	public SectionComponent getExamAutopsySection() {
		SectionComponent returnValue = getSection(CompositionMDIAndEDRSUtil.examAutopsySectionCode);
		if(returnValue == null) {
			returnValue = createExamAutopsySection();
		}
		return returnValue;
	}
	
	public SectionComponent getNarrativeSection() {
		return getSection(CompositionMDIAndEDRSUtil.narrativeSectionCode);
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
		return addCaseIdExtension(CompositionMDIAndEDRSUtil.trackingNumberMDIType, "", mdiCaseValue);
	}

	public Extension addMDICaseIdExtension(String mdiCaseSystem, String mdiCaseValue) {
		return addCaseIdExtension(CompositionMDIAndEDRSUtil.trackingNumberMDIType, mdiCaseSystem, mdiCaseValue);
	}

	public Extension addEDRSCaseIdExtension(String edrsCaseValue) {
		return addCaseIdExtension(CompositionMDIAndEDRSUtil.trackingNumberEDRSType, "", edrsCaseValue);
	}

	public Extension addEDRSCaseIdExtension(String edrsCaseSystem, String edrsCaseValue) {
		return addCaseIdExtension(CompositionMDIAndEDRSUtil.trackingNumberEDRSType, edrsCaseSystem, edrsCaseValue);
	}

	public Extension addTOXCaseIdExtension(String toxCaseValue) {
		return addCaseIdExtension(CompositionMDIAndEDRSUtil.trackingNumberTOXType, "", toxCaseValue);
	}

	public Extension addTOXCaseIdExtension(String toxCaseSystem, String toxCaseValue) {
		return addCaseIdExtension(CompositionMDIAndEDRSUtil.trackingNumberTOXType, toxCaseSystem, toxCaseValue);
	}

	public Extension addCaseIdExtension(CodeableConcept extensionType, String mdiCaseSystem, String mdiCaseValue) {
		Extension returnExtension = new Extension();
		returnExtension.setUrl(CompositionMDIAndEDRSUtil.trackingNumberExtensionURL);
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