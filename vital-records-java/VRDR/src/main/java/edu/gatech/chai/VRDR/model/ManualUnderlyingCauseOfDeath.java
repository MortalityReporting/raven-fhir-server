package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.ManualUnderlyingCauseOfDeathUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-manual-underlying-cause-of-death")
public class ManualUnderlyingCauseOfDeath extends Observation {

	/**
	 *
	 */
	private static final long serialVersionUID = 1775205638319513588L;

	public ManualUnderlyingCauseOfDeath() {
		super();
		CommonUtil.initResource(this);
		this.setCode(ManualUnderlyingCauseOfDeathUtil.code);
	}

	public ManualUnderlyingCauseOfDeath(Decedent decedent) {
		this();
		setSubject(new Reference(decedent));
	}
}