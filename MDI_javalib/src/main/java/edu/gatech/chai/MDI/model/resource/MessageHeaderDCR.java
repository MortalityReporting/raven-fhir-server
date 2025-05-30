package edu.gatech.chai.MDI.model.resource;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MessageHeader;
import org.hl7.fhir.r4.model.Reference;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.MDI.model.resource.util.CommonUtil;
import edu.gatech.chai.MDI.model.resource.util.MessageHeaderDCRUtil;

@ResourceDef(name = "MessageHeader", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/MessageHeader-death-certificate-review")
public class MessageHeaderDCR extends MessageHeader{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MessageHeaderDCR() {
		this.setEvent(new Coding(MessageHeaderDCRUtil.eventSystem, MessageHeaderDCRUtil.eventCode,""));
	}

	public MessageHeaderDCR(String urlEndpoint, CodeableConcept reason, BundleDocumentMDIDCR focus){
		this.setReason(reason);
		this.addFocus(new Reference("Bundle/" +focus.getId()));
	}

	public MessageHeaderDCR(String urlEndpoint, String reason, BundleDocumentMDIDCR focus){
		this.setReason(reason);
		this.addFocus(new Reference("Bundle/" +focus.getId()));
	}

	public MessageHeader setReason(String reason){
		CodeableConcept reasonCC = CommonUtil.findConceptFromCollectionUsingSimpleString(reason, MessageHeaderDCRUtil.reasonValueSet);
		this.setReason(reasonCC);
		return this;
	}
}