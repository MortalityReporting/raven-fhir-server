package edu.gatech.chai.VRDR.model.util;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ListResource;

public class CauseOfDeathPathwayUtil {
	public static final ListResource.ListStatus status = ListResource.ListStatus.CURRENT;
	public static final ListResource.ListMode mode = ListResource.ListMode.SNAPSHOT;
	public static final CodeableConcept orderedByCodeFixedValue = new CodeableConcept()
			.addCoding(new Coding("http://terminology.hl7.org/CodeSystem/list-order", "priority", "Sorted by Priority"));
}
