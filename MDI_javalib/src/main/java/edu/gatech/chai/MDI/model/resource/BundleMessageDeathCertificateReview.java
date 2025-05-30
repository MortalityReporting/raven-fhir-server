package edu.gatech.chai.MDI.model.resource;

import org.hl7.fhir.r4.model.Bundle;

import ca.uhn.fhir.model.api.annotation.ResourceDef;

@ResourceDef(name = "Bundle", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/Bundle-message-death-certificate-review")
public class BundleMessageDeathCertificateReview extends Bundle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1026420916074649288L;

	public BundleMessageDeathCertificateReview() {
		super();
	}

	public BundleMessageDeathCertificateReview(BundleType bundleType, MessageHeaderDCR messageHeaderEntry) {
		super();
		this.setType(bundleType);
		this.setIdentifier(identifier);
		BundleEntryComponent bec = new BundleEntryComponent();
		bec.setResource(messageHeaderEntry);
		this.addEntry(bec);
	}
	
	//This MessageHeader must always be the correct case here.
	public MessageHeaderDCR getMessageHeaderDCR() {
		checkEntries();
		return (MessageHeaderDCR) this.getEntry().get(0).getResource();
	}

	//This MessageHeader must always be the correct case here.
	public BundleDocumentMDIDCR getBundleDocumentMDIDCR() {
		checkEntries();
		return (BundleDocumentMDIDCR) this.getEntry().get(1).getResource();
	}

	//Helper function to help init full urls AFTER the id of thecomposition has been set.
	public BundleMessageDeathCertificateReview setFullUrls() {
		checkEntries();
		for(int i=0;i < 2; i++){
			BundleEntryComponent bec = this.getEntry().get(i);
			if(bec != null){
				bec.setFullUrl(bec.getResource().getResourceType() + "/" + bec.getResource().getId());
			}
		}
		return this;
	}

	public boolean checkEntries(){
		if(this.getEntry().isEmpty()){
			BundleEntryComponent bec = new BundleEntryComponent();
			bec.setResource(new MessageHeaderDCR());
			this.addEntry(bec);
			return true;
		}
		if(this.getEntry().size() == 1){
			BundleEntryComponent bec = new BundleEntryComponent();
			bec.setResource(new BundleDocumentMDIDCR());
			this.addEntry(bec);
			return true;
		}
		return false;
	}
}