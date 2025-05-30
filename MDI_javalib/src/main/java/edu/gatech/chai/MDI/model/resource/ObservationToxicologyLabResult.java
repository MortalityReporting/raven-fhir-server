package edu.gatech.chai.MDI.model.resource;

import org.hl7.fhir.r4.model.Observation;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.MDI.model.resource.util.ObservationToxicologyLabResultUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/Observation-toxicology-lab-result")
public class ObservationToxicologyLabResult extends Observation {
	public ObservationToxicologyLabResult() {
		super();
		this.addCategory(ObservationToxicologyLabResultUtil.category);
	}
}