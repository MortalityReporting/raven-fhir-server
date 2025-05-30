package edu.gatech.chai.USCore.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import ca.uhn.fhir.model.api.annotation.ResourceDef;

import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.Composition.SectionComponent;

public class CommonUtil {
	public static final String identifierTypeHL7System = "http://terminology.hl7.org/CodeSystem/v2-0203";
	public static final String basicBooleanHL7System = "http://terminology.hl7.org/CodeSystem/v2-0136";
	public static final String yesNoNASystemOID = "urn:oid:2.16.840.1.113883.12.136";
	public static final String nullFlavorSystemOID = "urn:oid:2.16.840.1.113883.5.1008";
	public static final String nullFlavorHL7System = "http://terminology.hl7.org/CodeSystem/v3-NullFlavor";
	public static final String snomedSystemUrl = "http://snomed.info/sct";
	public static final String loincSystemUrl = "http://loinc.org";
	public static final String icd10SystemUrl = "http://hl7.org/fhir/ValueSet/icd-10";

	public static final String locationJurisdictionURL = "https://www.usps.com/";
	public static final String dataAbsentReasonUrl = "http://terminology.hl7.org/CodeSystem/data-absent-reason";
	public static final String unitsOfMeasureUrl = "http://unitsofmeasure.org";
	public static final String missingValueReasonUrl = "http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-missing-value-reason-cs";
	public static final String partialDatePartAbsentReasonURL = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-Partial-date-part-absent-reason";
	public static final String partialDateDateYearURL = "date-year";
	public static final String partialDateDateYearAbsentReasonURL = "year-absent-reason";
	public static final String partialDateDateMonthURL = "date-month";
	public static final String partialDateDateMonthAbsentReasonURL = "month-absent-reason";
	public static final String partialDateDateDayURL = "date-day";
	public static final String partialDateDateDayAbsentReasonURL = "day-absent-reason";
	public static final String partialDateDateTimeURL = "date-time"; //"http://hl7.org/fhir/us/vrdr/StructureDefinition/PartialDateTime";
	public static final String partialDateDateTimeAbsentReasonURL = "time-absent-reason";

	public static final String vrdrObservationCsUrl = "http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-observations-cs";
	public static final String vrdrComponentCsUrl = "http://hl7.org/fhir/us/vrdr/CodeSystem/vrdr-component-cs";
	public static CodeableConcept noCode = new CodeableConcept().addCoding(new Coding(basicBooleanHL7System,"N","No"));
	public static CodeableConcept yesCode = new CodeableConcept().addCoding(new Coding(basicBooleanHL7System,"Y","Yes"));
	public static CodeableConcept unknownCode = new CodeableConcept().addCoding(new Coding(nullFlavorHL7System,"UNK","unknown"));
	public static CodeableConcept otherCode = new CodeableConcept().addCoding(new Coding(nullFlavorHL7System, "OTH", "other"));
	public static CodeableConcept notApplicableCode = new CodeableConcept().addCoding(new Coding(nullFlavorHL7System, "NA", "not applicable"));	public static CodeableConcept askuCode = new CodeableConcept().addCoding(new Coding(nullFlavorHL7System,"ASKU","asked but unknown"));
	public static Coding otherCoding = new Coding(nullFlavorHL7System, "OTH", "other");
	public static Coding notApplicableCoding = new Coding(nullFlavorHL7System, "NA", "not applicable");
	public static Coding noCoding = new Coding(basicBooleanHL7System,"N","No");
	public static Coding yesCoding = new Coding(basicBooleanHL7System,"Y","Yes");
	public static Coding unknownCoding = new Coding(nullFlavorHL7System,"UNK","unknown");
	public static CodeableConcept notAskedCode = new CodeableConcept().addCoding(new Coding(nullFlavorHL7System,"NASK","not asked"));
	public static final String deathReportingIdentifierTypeSystem = "urn:oid:2.16.840.1.114222.4.11.7382";
	public static CodeableConcept deathCertificateIdCode = new CodeableConcept().addCoding(new Coding(deathReportingIdentifierTypeSystem,"DC","Death Certificate Id"));
	public static CodeableConcept deathCertificateFileNumberCode = new CodeableConcept().addCoding(new Coding(deathReportingIdentifierTypeSystem,"DCFN","Death Certificate File Number"));
	public static CodeableConcept deathCertificateLicenseNumberCode = new CodeableConcept().addCoding(new Coding(deathReportingIdentifierTypeSystem,"LN","Death Certificate License Number"));
	public static CodeableConcept nationalProviderIdentifierCode = new CodeableConcept().addCoding(new Coding(deathReportingIdentifierTypeSystem,"NPI","National provider identifier"));
	public static CodeableConcept stateRegistryIdCode = new CodeableConcept().addCoding(new Coding(deathReportingIdentifierTypeSystem,"SR","State Registry ID"));
	public static CodeableConcept SocialSecurityNumberCode = new CodeableConcept().addCoding(new Coding(deathReportingIdentifierTypeSystem,"SS","Social Security Number"));
	public static List<CodeableConcept> deathReportingIdentifierTypeCodes = new ArrayList<CodeableConcept>(Arrays.asList(deathCertificateIdCode,deathCertificateFileNumberCode,deathCertificateLicenseNumberCode,
			nationalProviderIdentifierCode,stateRegistryIdCode,SocialSecurityNumberCode));
	public static final HashSet<CodeableConcept> certifierTypeSet = new HashSet<>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"455381000124109","Medical Examiner/Coroner")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"434641000124105","Physician certified and pronounced death certificate")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"434651000124107","Physician certified death certificate")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.nullFlavorHL7System,"OTH","Other"))
			));
	public static final HashSet<CodeableConcept> yesNoUnknownSet = new HashSet<>(Arrays.asList(yesCode,noCode,unknownCode));
	public static final HashSet<CodeableConcept> yesNoNASet = new HashSet<>(Arrays.asList(yesCode,noCode,notApplicableCode));
	public static final HashSet<CodeType> dataAbsentReasonCodeSet = new HashSet<>(Arrays.asList(
			new CodeType("unknown"),
			new CodeType("asked-unknown"),
			new CodeType("not-asked"),
			new CodeType("asked-declined"),
			new CodeType("masked"),
			new CodeType("not-applicable"),
			new CodeType("unsupported"),
			new CodeType("as-text"),
			new CodeType("error"),
			new CodeType("not-a-number"),
			new CodeType("negative-infinity"),
			new CodeType("positive-infinity"),
			new CodeType("not-performed"),
			new CodeType("not-permitted")));
	public static final HashSet<CodeableConcept> dataAbsentReasonConceptSet = new HashSet<>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"unknown","Unknown")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"asked-unknown","Asked But Unknown")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"not-asked", "Not Asked")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"asked-declined", "Asked By Declined")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"masked", "Masked")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"not-applicable", "Not Applicable")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"unsupported", "Unsuppported")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"as-text", "As Text")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"error", "Error")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"not-a-number", "Not a Number (NaN)")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"negative-infinity", "Negative infinifty (NINF)")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"positive-infinity", "Positive Inifinity (PINF)")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"not-performed", "Not Performed")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.dataAbsentReasonUrl,"not-permitted", "Not Permitted"))));
	public static final HashSet<CodeableConcept> locationJurisdictionalConceptSet = new HashSet<>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"01","Alabama")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"02","Alaska")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"05", "Arkansas")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"60", "American Samoa")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL, "04", "Arizona")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"06", "California")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"08", "Colorado")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"09", "Connecticut")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"10", "Delaware")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"11", "District of Columbia")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"64", "Federated States of Micronesia")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"12", "Florida")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"13", "Georgia")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"66", "Guam")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"15", "Hawaii")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"84", "Howland Island")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"16", "Idaho")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"17", "Illinois")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"18", "Indiana")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"19", "Iowa")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"20", "Kansas")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"21", "Kentucky")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"22", "Louisiana")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"23", "Maine")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"24", "Maryland")),	
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"25", "Massachusetts")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"26", "Michigan")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"27", "Minnesota")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"28", "Mississippi")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"29", "Missouri")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"MP", "Northern Mariana Islands")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"30", "Montana")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"31", "Nebraska")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"32", "Nevada")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"33", "New Hampshire")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"34", "New Jersey")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"35", "New Mexico")),
			new CodeableConcept().addCoding(new Coding("urn:oid:2.16.840.1.113883.6.245","9755772", "New York City")), //Unique codesystem just for New York City
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"36", "New York")), //Unique codesystem just for New York City
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"37", "North Carolina")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"38", "North Dekota")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"69", "Northern Mariana Islands")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"39", "Ohio")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"40", "Oklahoma")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"41", "Oregon")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"42", "Pennsylvania")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"72", "Puerto Rico")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"44", "Rhode Island")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"45", "South Carolina")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"46", "South Dakota")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"47", "Tennessee")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"48", "Texas")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"78", "U.S. Virgin Islands")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"49", "Utah")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"50", "Vermont")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"51", "Virginia")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"53", "Washington")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"54", "West Virginia")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"55", "Wisconsin")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.locationJurisdictionURL,"56", "Wyoming"))));
	public static final HashSet<CodeableConcept> ucumUnitsConceptSet = new HashSet<>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(CommonUtil.unitsOfMeasureUrl,"min","Minutes")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.unitsOfMeasureUrl,"d","Days")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.unitsOfMeasureUrl,"h", "Hours")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.unitsOfMeasureUrl,"mo", "Months")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.unitsOfMeasureUrl,"a", "Years"))));
	public static final HashSet<CodeableConcept> missingValueConceptSet = new HashSet<>(Arrays.asList(
			new CodeableConcept().addCoding(new Coding(CommonUtil.missingValueReasonUrl,"R","Refused")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.missingValueReasonUrl,"S","Sought, but unknown")),
			new CodeableConcept().addCoding(new Coding(CommonUtil.missingValueReasonUrl,"C", "Not obtainable"))));
	
	public static Extension getExtension(DomainResource resource, String url) {
		for (Extension extension : resource.getExtension()) {
			if (extension.getUrl().equals(url)) {
				return extension;
			}
		}
		return null;
	}

	public static void initResource(Resource resource) {
		setUUID(resource);
	}
	
	public static void setUUID(Resource resource) {
		resource.setId(new IdType(UUID.randomUUID().toString()));
	}
	
	
	public static CodeableConcept findConceptFromCollectionUsingSimpleString(String key,Collection<CodeableConcept> collection) {
		for(CodeableConcept conceptIter:collection) {
			Coding coding = conceptIter.getCodingFirstRep();
			if(coding.getCode().equalsIgnoreCase(key) || coding.getDisplay().equalsIgnoreCase(key)) {
				return conceptIter;
			}
		}
		return null;
	}
	
	public static CodeType findCodeFromCollectionUsingSimpleString(String key,Collection<CodeType> collection) {
		for(CodeType conceptIter:collection) {
			if(conceptIter.getValue().equalsIgnoreCase(key)) {
				return conceptIter;
			}
		}
		return null;
	}
	
	public static boolean assignableFrom(Class test, Class[] candidates){
	    for(Class candidate : candidates){
	        if(candidate.isAssignableFrom(test)){
	            return true;
	        }
	    }
	    return false;
	}

	public static <T extends Resource> T getSingleResource(Class<T> resourceClass, Bundle bundle) {
		String profile = resourceClass.getAnnotation(ResourceDef.class).profile();
		for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
			if (getResourceMetaProfile(entry.getResource()).equals(profile)) {
				return resourceClass.cast(entry.getResource());
			}
		}
		return null;
	}

    public static <T extends Resource> List<T> getResources(Class<T> resourceClass, Bundle bundle) {
		List<T> resources = new ArrayList<>();
        String profile = resourceClass.getAnnotation(ResourceDef.class).profile();
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (getResourceMetaProfile(entry.getResource()).equals(profile)) {
                resources.add(resourceClass.cast(entry.getResource()));
            }
        }
        return resources;
    }

    public static String getResourceMetaProfile(Resource resource) {
        if (resource == null
                || resource.getMeta() == null
                || resource.getMeta().getProfile() == null
                || resource.getMeta().getProfile().size() == 0) {
            return null;
        }
        return resource.getMeta().getProfile().get(0).getValueAsString();
    }

    public static String getCodedValue(CodeableConcept codeableConcept) {
        if (codeableConcept == null
                || codeableConcept.getCoding() == null
                || codeableConcept.getCoding().size() == 0) {
            return null;
        }
        return codeableConcept.getCoding().get(0).getCode();
    }

    public static <T extends Resource> T findEntry(Bundle bundle, Class<T> tClass, boolean ignoreMissingEntries) {
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.getResource() != null && tClass.isAssignableFrom(entry.getResource().getClass())) {
                return (T) entry.getResource();
            }
        }
        return null;
    }

	public static <T, U extends PrimitiveType<T>> T findObservationComponentComponentValueForCoding(
			List<Observation.ObservationComponentComponent> occs, Class<U> fhirValueTypeClass, Coding coding) {
		for (Observation.ObservationComponentComponent occ : occs) {
			if (occ.getCode().getCodingFirstRep().getSystem().equals(coding.getSystem())
					&& occ.getCode().getCodingFirstRep().getCode().equals(coding.getCode())
					&& occ.getValue() != null
					&& fhirValueTypeClass.isAssignableFrom(occ.getValue().getClass())) {
				U fhirValue = (U)occ.getValue();
				return fhirValue.getValue();
			}
		}
		return null;
	}

	public static String findObservationComponentComponentValueCodeableConceptCodeForCoding(
			List<Observation.ObservationComponentComponent> occs, Coding coding) {
		for (Observation.ObservationComponentComponent occ : occs) {
			if (occ.getCode().getCodingFirstRep().getSystem().equals(coding.getSystem())
					&& occ.getCode().getCodingFirstRep().getCode().equals(coding.getCode())
					&& occ.getValue() != null) {
				return occ.getValueCodeableConcept().getCodingFirstRep().getCode();
			}
		}
		return null;
	}

	public static <T extends Resource> T findEntry(Bundle messageBundle, Class<T> tClass) {
		return findEntry(messageBundle, tClass, false);
	}

	public static Type setDataAbsentReason(Type element, CodeType dataAbsentReason){
		Extension dabExtension = new Extension(CommonUtil.dataAbsentReasonUrl);
		dabExtension.setValue(dataAbsentReason);
		element.addExtension(dabExtension);
		return element;
	}
}