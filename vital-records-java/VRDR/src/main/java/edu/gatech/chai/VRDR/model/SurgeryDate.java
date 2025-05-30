package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.IntegerType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CommonUtil;
import edu.gatech.chai.VRDR.model.util.SurgeryDateUtil;

@ResourceDef(name = "Observation", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-surgery-date")
public class SurgeryDate extends Observation {

	/**
	 *
	 */
	private static final long serialVersionUID = 1775205638319513588L;

	public SurgeryDate() {
		super();
		CommonUtil.initResource(this);
		this.setCode(SurgeryDateUtil.code);
	}

	public SurgeryDate(Decedent decedent) {
		this();
		setSubject(new Reference(decedent));
	}

	public SurgeryDate addPartialDateExtension(IntegerType year,String yearDataAbsentReason, IntegerType month,String monthDataAbsentReason,
			IntegerType day,String dayDataAbsentReason) {
		if(this.value == null) {
			this.value = new DateTimeType(); //Assuming a datetime value if uninitiated
		}
		Extension baseExtension = addPartialDateBaseExtension();
		addPartialDateYear(baseExtension,year,yearDataAbsentReason);
		addPartialDateMonth(baseExtension,month,monthDataAbsentReason);
		addPartialDateDay(baseExtension,day,dayDataAbsentReason);
		this.value.addExtension(baseExtension);
		return this;
	}

	private Extension addPartialDateBaseExtension() {
		Extension baseExtension = new Extension(CommonUtil.partialDatePartAbsentReasonURL);
		return baseExtension;
	}

	private SurgeryDate addPartialDateYear(Extension baseExtension, IntegerType year,String dataAbsentReason) {
		if(dataAbsentReason != null && !dataAbsentReason.isEmpty()) {
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateYearAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
		}
		else if(year != null && !year.isEmpty()){
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateYearURL,year));
		}
		return this;
	}

	private SurgeryDate addPartialDateMonth(Extension baseExtension, IntegerType month,String dataAbsentReason) {
		if(dataAbsentReason != null && !dataAbsentReason.isEmpty()) {
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateMonthAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
		}
		else if(month != null && !month.isEmpty()){
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateMonthURL,month));
		}
		return this;
	}

	private SurgeryDate addPartialDateDay(Extension baseExtension, IntegerType day,String dataAbsentReason) {
		if(dataAbsentReason != null || !dataAbsentReason.isEmpty()) {
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateDayAbsentReasonURL,CommonUtil.findCodeFromCollectionUsingSimpleString(dataAbsentReason, CommonUtil.dataAbsentReasonCodeSet)));
		}
		else if(day != null && !day.isEmpty()){
			baseExtension.addExtension(new Extension(CommonUtil.partialDateDateDayURL,day));
		}
		return this;
	}
}