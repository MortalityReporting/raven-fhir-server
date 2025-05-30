package edu.gatech.chai.MDI.model.resource;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Identifier;

import ca.uhn.fhir.model.api.annotation.ResourceDef;

@ResourceDef(name = "Bundle", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/Bundle-message-tox-to-mdi")
public class BundleMessageToxToMDI extends Bundle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1026420916074649288L;

	public BundleMessageToxToMDI() {
		super();
		this.setType(BundleType.MESSAGE);
	}
	public BundleMessageToxToMDI(Identifier identifier,MessageHeaderToxicologyToMDI messageHeaderEntry) {
		super();
		this.setType(BundleType.MESSAGE);
		this.setIdentifier(identifier);
		BundleEntryComponent bec = new BundleEntryComponent();
		bec.setResource(messageHeaderEntry);
		this.addEntry(bec);
	}
	
	//This MessageHeader must always be the correct case here.
	public MessageHeaderToxicologyToMDI getMessageHeaderToxicologyToMDI() {
		checkMessageHeaderToxicologyToMDI();
		return (MessageHeaderToxicologyToMDI) this.getEntryFirstRep().getResource();
	}

	//Helper function to help init full urls AFTER the id of thecomposition has been set.
	public BundleMessageToxToMDI setFullUrlOnMessageHeaderToxicologyToMDI() {
		checkMessageHeaderToxicologyToMDI();
		this.getEntryFirstRep().setFullUrl(this.getEntryFirstRep().getResource().getResourceType()+"/"+this.getEntryFirstRep().getResource().getId());
		return this;
	}

	public boolean checkMessageHeaderToxicologyToMDI(){
		if(this.getEntry().isEmpty()){
			BundleEntryComponent bec = new BundleEntryComponent();
			bec.setResource(new MessageHeaderToxicologyToMDI());
			this.addEntry(bec);
			return true;
		}
		return false;
	}
}