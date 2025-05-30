package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.ExaminerContactedUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-examiner-contacted")
public class ExaminerContacted extends Observation {
	public ExaminerContacted() {
		super();
		setStatus(ExaminerContactedUtil.status);
		setCode(ExaminerContactedUtil.code);
		CommonUtil.initResource(this);
	}
	public ExaminerContacted(boolean value) {
		this();
		if(value) {
			setValue(CommonUtil.yesCode);
		}
		else {
			setValue(CommonUtil.noCode);
		}
	}

	public ExaminerContacted(CodeableConcept yesNoUnknownCode) {
		this();
		setValue(yesNoUnknownCode);
	}

	public ExaminerContacted(String yesNoUnknownCode) {
		this();
		setValue(yesNoUnknownCode);
	}

	public void setValue(String code) {
		CodeableConcept concept = CommonUtil.findConceptFromCollectionUsingSimpleString(code, CommonUtil.yesNoUnknownSet);
		if(concept != null) {
			setValue(concept);
		}
	}
}