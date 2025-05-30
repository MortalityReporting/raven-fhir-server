package edu.gatech.chai.VRDR.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.gatech.chai.VRCL.model.AutopsyPerformedIndicator;
import edu.gatech.chai.VRCL.model.InputRaceAndEthnicity;
import edu.gatech.chai.VRCL.model.ObservationEducationLevel;
import edu.gatech.chai.VRCL.model.ObservationUsualWork;
import edu.gatech.chai.VRDR.context.VRDRFhirContext;

import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.Composition.CompositionStatus;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.DeathCertificateDocumentUtil;

@ResourceDef(name = "Bundle", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-death-certificate-document")
public class DeathCertificateDocument extends Bundle {

	public static final String LOINC_CODE_DATE_PRONOUNCED_DEAD = "80616-6";

	private static final long serialVersionUID = -429197004514766374L;
	public ExtraDateTimeType extraDateTimeType4Death;
	public ExtraDateTimeType extraDateTimeType4Injury;

	public ExtraDateTimeType getExtraDateTimeType4Death() {
		return this.extraDateTimeType4Death;
	}

	public void setExtraDateTimeType4Death(ExtraDateTimeType extraDateTimeType4Death) {
		this.extraDateTimeType4Death = extraDateTimeType4Death;
	}

	public ExtraDateTimeType getExtraDateTimeType4Injury() {
		return this.extraDateTimeType4Injury;
	}

	public void setExtraDateTimeType4Injury(ExtraDateTimeType extraDateTimeType4Injury) {
		this.extraDateTimeType4Injury = extraDateTimeType4Injury;
	}

	public DeathCertificateDocument() {
		super();
		CommonUtil.initResource(this);
		setType(BundleType.DOCUMENT);
	}

	public DeathCertificateDocument(CompositionStatus status, Decedent decedent, DeathCertificationProcedure deathCertificationProcedure) {
		super();
		CommonUtil.initResource(this);
		setType(BundleType.DOCUMENT);
		DeathCertificate deathCertificate = new DeathCertificate(status,decedent,deathCertificationProcedure);
		CommonUtil.initResource(deathCertificate);
		this.addEntry(new BundleEntryComponent().setResource(deathCertificate));
	}

	public void addAuxillaryStateIdentifier(String auxillaryStateIdentifierValue) {
		Extension extension = new Extension();
		extension.setUrl(DeathCertificateDocumentUtil.auxillaryStateIndentifierUrl);
		extension.setValue(new StringType(auxillaryStateIdentifierValue));
		this.getIdentifier().addExtension(extension);
	}

	public void addResource(Resource resource) {
		DeathCertificate deathCertificate = getDeathCertificate().get(0);
		deathCertificate.addResource(resource);
		this.addEntry(new BundleEntryComponent().setResource(resource));
	}

	//Helper Accessor methods

	private List<Resource> getRecords(Class<? extends Resource> type){
		List<Resource> returnList = new ArrayList<Resource>();
		for(BundleEntryComponent bec:this.getEntry()) {
			Resource resource = bec.getResource();
			if(type.isInstance(resource)) {
				returnList.add(resource);
			}
		}
		return returnList;
	}
	private <T extends Resource> List<T> castListOfRecords(List<Resource> inputList){
		List<T> outputList = inputList
				.stream()
				.map(e -> (T) e)
				.collect(Collectors.toList());
		return outputList;
	}

	public List<AutopsyPerformedIndicator> getAutopsyPerformedIndicator(){
		List<Resource> resources = getRecords(AutopsyPerformedIndicator.class);
		return castListOfRecords(resources);
	}

	public List<BirthRecordIdentifier> getBirthRecordIdentifier(){
		List<Resource> resources = getRecords(BirthRecordIdentifier.class);
		return castListOfRecords(resources);
	}

	public List<CauseOfDeathPart1> getCauseOfDeathCondition(){
		List<Resource> resources = getRecords(CauseOfDeathPart1.class);
		return castListOfRecords(resources);
	}

	public List<CauseOfDeathPart2> getConditionContributingToDeath(){
		List<Resource> resources = getRecords(CauseOfDeathPart2.class);
		return castListOfRecords(resources);
	}

	public List<Certifier> getCertifier(){
		List<Resource> resources = getRecords(Certifier.class);
		return castListOfRecords(resources);
	}

	public List<DeathCertificate> getDeathCertificate(){
		List<Resource> resources = getRecords(DeathCertificate.class);
		return castListOfRecords(resources);
	}


	public List<DeathCertificationProcedure> getDeathCertificationProcedure(){
		List<Resource> resources = getRecords(DeathCertificationProcedure.class);
		return castListOfRecords(resources);
	}

	public List<DeathDate> getDeathDate(){
		List<Resource> resources = getRecords(DeathDate.class);
		if(resources.size()>0) {
			DeathDate deathDate = (DeathDate)resources.get(0);
			if(deathDate == null || deathDate.getValue() == null || deathDate.dateTimeValue() == null || !deathDate.dateTimeValue().hasTime()) {
				extraDateTimeType4Death = new ExtraDateTimeType();
				extraDateTimeType4Death.setMissingOrUnknownDeathTime("null");
			}
			if(deathDate.getEffectiveDateTimeType().toString().equals("DateTimeType[null]")) {
				extraDateTimeType4Death.setMissingOrUnknownDeathTime("unknown");
			}
		}
		return castListOfRecords(resources);
	}


	public List<DeathLocation> getDeathLocation(){
		List<Resource> resources = getRecords(DeathLocation.class);
		return castListOfRecords(resources);
	}

	public List<Decedent> getDecedent(){
		List<Resource> resources = getRecords(Decedent.class);
		return castListOfRecords(resources);
	}

	public List<DecedentAge> getDecedentAge(){
		List<Resource> resources = getRecords(DecedentAge.class);
		return castListOfRecords(resources);
	}

	public List<DecedentDispositionMethod> getDecedentDispositionMethod(){
		List<Resource> resources = getRecords(DecedentDispositionMethod.class);
		return castListOfRecords(resources);
	}

	public List<ObservationEducationLevel> getDecedentEducationLevel(){
		return getObservationEducationLevel();
	}

	public List<ObservationEducationLevel> getObservationEducationLevel(){
		List<Resource> resources = getRecords(ObservationEducationLevel.class);
		return castListOfRecords(resources);
	}

	public List<DecedentFather> getDecedentFather(){
		List<Resource> resources = getRecords(DecedentFather.class);
		return castListOfRecords(resources);
	}

	public List<DecedentMilitaryService> getDecedentMilitaryService(){
		List<Resource> resources = getRecords(DecedentMilitaryService.class);
		return castListOfRecords(resources);
	}

	public List<DecedentMother> getDecedentMother(){
		List<Resource> resources = getRecords(DecedentMother.class);
		return castListOfRecords(resources);
	}

	public List<DecedentPregnancyStatus> getDecedentPregnancy(){
		List<Resource> resources = getRecords(DecedentPregnancyStatus.class);
		return castListOfRecords(resources);
	}

	public List<DecedentSpouse> getDecedentSpouse(){
		List<Resource> resources = getRecords(DecedentSpouse.class);
		return castListOfRecords(resources);
	}

	public List<ObservationUsualWork> getDecedentUsualWork(){
		return getObservationUsualWork();
	}

	public List<ObservationUsualWork> getObservationUsualWork(){
		List<Resource> resources = getRecords(ObservationUsualWork.class);
		return castListOfRecords(resources);
	}

	public List<DispositionLocation> getDispositionLocation(){
		List<Resource> resources = getRecords(DispositionLocation.class);
		return castListOfRecords(resources);
	}

	public List<ExaminerContacted> getExaminerContacted(){
		List<Resource> resources = getRecords(ExaminerContacted.class);
		return castListOfRecords(resources);
	}

	public List<FuneralHome> getFuneralHome(){
		List<Resource> resources = getRecords(FuneralHome.class);
		return castListOfRecords(resources);
	}

	public List<InjuryIncident> getInjuryIncident(){
		List<Resource> resources = getRecords(InjuryIncident.class);
		if(resources.size()>0) {
			InjuryIncident injuryIncident = (InjuryIncident)resources.get(0);
			if(injuryIncident == null || injuryIncident.getEffectiveDateTimeType() == null || injuryIncident.dateTimeValue() == null || !injuryIncident.dateTimeValue().hasTime()) {
				extraDateTimeType4Injury = new ExtraDateTimeType();
				extraDateTimeType4Injury.setMissingOrUnknownDeathTime("null");
			}
			if(injuryIncident.getEffectiveDateTimeType().toString().equals("DateTimeType[null]")) {
				extraDateTimeType4Injury.setMissingOrUnknownDeathTime("unknown");
			}
		}
		return castListOfRecords(resources);
	}

	public List<InjuryLocation> getInjuryLocation(){
		List<Resource> resources = getRecords(InjuryLocation.class);
		return castListOfRecords(resources);
	}

	public List<MannerOfDeath> getMannerOfDeath(){
		List<Resource> resources = getRecords(MannerOfDeath.class);
		return castListOfRecords(resources);
	}

	public List<TobaccoUseContributedToDeath> getTobaccoUseContributedToDeath(){
		List<Resource> resources = getRecords(TobaccoUseContributedToDeath.class);
		return castListOfRecords(resources);
	}

	public String getDateOfDeathPronouncement() {
		if (this == null || getDeathDate() == null || getDeathDate().size() == 0) {
			return null;
		}
		for (DeathDate date : getDeathDate()) {
			for (Observation.ObservationComponentComponent component : date.getComponent()) {
				for (Coding coding : component.getCode().getCoding()) {
					if (coding.getCode().equals(LOINC_CODE_DATE_PRONOUNCED_DEAD)) {
						Type value = component.getValue();
						if (value instanceof DateTimeType) {
							return ((DateTimeType) value).getValueAsString();
						}
						else if (value instanceof TimeType) {
							return ((TimeType) value).getValueAsString();
						}
					}
				}
			}
		}
		return null;
	}

	public List<InputRaceAndEthnicity> getInputRaceAndEthnicity() {
		List<Resource> resources = getRecords(InputRaceAndEthnicity.class);
		return castListOfRecords(resources);
	}

	public String toJson(VRDRFhirContext ctx) {
		return toJson(ctx, false);
	}

	public String toJson(VRDRFhirContext ctx, boolean prettyPrint) {
		return ctx.getCtx().newJsonParser().setPrettyPrint(prettyPrint).encodeResourceToString(this);
	}


}