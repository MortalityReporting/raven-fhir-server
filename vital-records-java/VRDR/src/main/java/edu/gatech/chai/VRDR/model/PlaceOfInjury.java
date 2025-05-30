package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.PlaceOfInjuryUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-record-axis-cause-of-death")
public class PlaceOfInjury extends Observation {

	/**
	 *
	 */
	private static final long serialVersionUID = 1775205638319513588L;

	public PlaceOfInjury() {
		super();
		CommonUtil.initResource(this);
		this.setCode(PlaceOfInjuryUtil.code);
	}

	public PlaceOfInjury(Decedent decedent) {
		this();
		setSubject(new Reference(decedent));
	}
}