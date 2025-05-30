package edu.gatech.chai.MDI.model.resource;

import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MessageHeader;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import edu.gatech.chai.MDI.model.resource.util.CommonUtil;
import edu.gatech.chai.MDI.model.resource.util.MessageHeaderToxicologyToMDIUtil;

@ResourceDef(name = "MessageHeader", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/MessageHeader-toxicology-to-mdi")
public class MessageHeaderToxicologyToMDI extends MessageHeader{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MessageHeaderToxicologyToMDI() {
		this.setEvent(new Coding(CommonUtil.mdiCodesSystemURL, MessageHeaderToxicologyToMDIUtil.eventCode,""));
	}
}