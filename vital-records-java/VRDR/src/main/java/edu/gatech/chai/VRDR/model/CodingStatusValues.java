package edu.gatech.chai.VRDR.model;

import org.hl7.fhir.r4.model.*;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.VRDR.model.util.CodingStatusValuesUtil;
import edu.gatech.chai.VRDR.model.util.CommonUtil;

@ResourceDef(name = "Parameters", profile = "http://hl7.org/fhir/us/vrdr/StructureDefinition/vrdr-coding-status-values")
public class CodingStatusValues extends Parameters {

	public CodingStatusValues() {
		super();
		CommonUtil.initResource(this);
	}

	public Parameters addShipmentNumber(String value) {
		return addParameter("shipmentNumber",value);
	}

	public Parameters addReceiptDate(String value) {
		return addParameter("receiptDate",value);
	}

	public Parameters addCoderStatus(String value) {
		return addParameter("coderStatus",value);
	}

	public Parameters addIntentionalReject(String value) {
		CodeableConcept ccValue = CommonUtil.findConceptFromCollectionUsingSimpleString(value, CodingStatusValuesUtil.intentionalRejectValueset);
		return addParameter("intentionalReject",ccValue);
	}

	public Parameters addAcmeSystemReject(String value) {
		CodeableConcept ccValue = CommonUtil.findConceptFromCollectionUsingSimpleString(value, CodingStatusValuesUtil.intentionalRejectValueset);
		return addParameter("acmeSystemReject",ccValue);
	}

	public Parameters addTransaxConversion(String value) {
		CodeableConcept ccValue = CommonUtil.findConceptFromCollectionUsingSimpleString(value, CodingStatusValuesUtil.intentionalRejectValueset);
		return addParameter("transaxConversion",ccValue);
	}

	public Parameters addParameter(String name, String value) {
		ParametersParameterComponent ppc = new ParametersParameterComponent();
		ppc.setName(name);
		ppc.setValue(new StringType(value));
		this.addParameter(ppc);
		return this;
	}

	public Parameters addParameter(String name, CodeableConcept value) {
		ParametersParameterComponent ppc = new ParametersParameterComponent();
		ppc.setName(name);
		ppc.setValue(value);
		this.addParameter(ppc);
		return this;
	}

	public Integer getCoderStatus() {
		return getParameter("coderStatus") == null ? null : ((IntegerType)getParameter("coderStatus")).getValue();
	}

	public String getShipmentNumber() {
		return getParameter("shipmentNumber") == null ? null : ((StringType)getParameter("shipmentNumber")).getValue();
	}

	public static final String PartialDateExtensionUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/PartialDate";
	public static final String DateYearExtensionUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/Date-Year";
	public static final String DateMonthExtensionUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/Date-Month";
	public static final String DateDayExtensionUrl = "http://hl7.org/fhir/us/vrdr/StructureDefinition/Date-Day";

	public Integer getReceiptDatePart(String datePartExtensionUrl) {
		if (getParameter("receiptDate") == null) {
			return null;
		}
		Extension partialDate = getParameter("receiptDate").getExtensionByUrl(PartialDateExtensionUrl);
		if (partialDate == null) {
			return null;
		}
		Extension datePart = partialDate.getExtensionByUrl(datePartExtensionUrl);
		if (datePart == null || datePart.getValue() == null) {
			return null;
		}
		return ((IntegerType)datePart.getValue()).getValue();
	}

	public Integer getReceiptYear() {
		return getReceiptDatePart(DateYearExtensionUrl);
	}

	public Integer getReceiptMonth() {
		return getReceiptDatePart(DateMonthExtensionUrl);
	}

	public Integer getReceiptDay() {
		return getReceiptDatePart(DateDayExtensionUrl);
	}

}