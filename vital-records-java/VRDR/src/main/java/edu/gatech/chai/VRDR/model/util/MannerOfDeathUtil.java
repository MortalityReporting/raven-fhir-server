package edu.gatech.chai.VRDR.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;

public class MannerOfDeathUtil {
	public static final ObservationStatus status = ObservationStatus.FINAL;
	public static final CodeableConcept code = new CodeableConcept()
			.addCoding(new Coding(CommonUtil.loincSystemUrl, "69449-7", "Manner of death"));
	public static final CodeableConcept VALUE_NATURAL = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"38605008", "Natural death"));
	public static final CodeableConcept VALUE_ACCIDENTAL = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"7878000", "Accidental death"));
	public static final CodeableConcept VALUE_SUICIDE = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"44301001", "Suicide"));
	public static final CodeableConcept VALUE_HOMICIDE = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"27935005", "Homicide"));
	public static final CodeableConcept VALUE_INVESTIGATION = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"185973002", "Patient awaiting investigation"));
	public static final CodeableConcept VALUE_NBD = new CodeableConcept().addCoding(new Coding(CommonUtil.snomedSystemUrl,"65037004", "Death, manner undetermined"));
	public static Set<CodeableConcept> valueCodesetList = new HashSet<CodeableConcept>(Arrays.asList(
			VALUE_NATURAL,VALUE_ACCIDENTAL,VALUE_SUICIDE,VALUE_HOMICIDE,VALUE_INVESTIGATION,VALUE_NBD));
}