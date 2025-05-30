package edu.gatech.chai.VRCL.model;

import java.math.BigDecimal;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.USCore.model.util.CommonUtil;
import edu.gatech.chai.VRCL.model.util.ObservationUsualWorkUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vr-common-library/StructureDefinition/Observation-usual-work-vr")
public class ObservationUsualWork extends Observation {
	public ObservationUsualWork() {
		super();
		CommonUtil.initResource(this);
		setStatus(ObservationUsualWorkUtil.status);
		setCode(ObservationUsualWorkUtil.code);
	}
	
	public ObservationUsualWork(CodeableConcept usualWork, CodeableConcept usualIndustryValue, Integer occupationYears) {
		this();
		setValue(usualWork);
		addUsualIndustry(usualIndustryValue);
		addUsualOccupationDuration(occupationYears);
	}
	
	
	public void addUsualIndustry(CodeableConcept usualIndustryValue) {
		ObservationComponentComponent component = new ObservationComponentComponent();
		component.setCode(ObservationUsualWorkUtil.componentUsualIndustryCode);
		component.setValue(usualIndustryValue);
		addComponent(component);
	}
	
	public void addUsualOccupationDuration(Integer years) {
		ObservationComponentComponent component = new ObservationComponentComponent();
		component.setCode(ObservationUsualWorkUtil.componentUsualOccupationDuraction);
		Quantity yearsQuantity = new Quantity();
		yearsQuantity.setCode("a");
		yearsQuantity.setSystem("http://unitsofmeasure.org");
		yearsQuantity.setValue(new BigDecimal(years));
		component.setValue(yearsQuantity);
		addComponent(component);
	}
}