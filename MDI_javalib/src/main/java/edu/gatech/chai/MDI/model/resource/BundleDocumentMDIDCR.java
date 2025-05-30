package edu.gatech.chai.MDI.model.resource;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Identifier;

import ca.uhn.fhir.model.api.annotation.ResourceDef;

@ResourceDef(name = "Bundle", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/Bundle-document-mdi-dcr")
public class BundleDocumentMDIDCR extends Bundle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7622309533132493325L;

	public BundleDocumentMDIDCR() {
		super();
		this.setType(BundleType.DOCUMENT);
	}
	public BundleDocumentMDIDCR(Identifier identifier,CompositionMDIDCR compositionEntry) {
		super();
		this.setIdentifier(identifier);
		this.setType(BundleType.DOCUMENT);
		BundleEntryComponent bec = new BundleEntryComponent();
		bec.setResource(compositionEntry);
		this.addEntry(bec);
	}
	
	//This Composition must always be the correct case here.
	public CompositionMDIDCR getCompositionMDIDCR() {
		checkCompositionMDIDCR();
		return (CompositionMDIDCR) this.getEntryFirstRep().getResource();
	}

	//Helper function to help init full urls AFTER the id of thecomposition has been set.
	public BundleDocumentMDIDCR setFullUrlOnCompositionMDIDCR() {
		checkCompositionMDIDCR();
		this.getEntryFirstRep().setFullUrl(this.getEntryFirstRep().getResource().getResourceType()+"/"+this.getEntryFirstRep().getResource().getId());
		return this;
	}

	public boolean checkCompositionMDIDCR(){
		if(this.getEntry().isEmpty()){
			BundleEntryComponent bec = new BundleEntryComponent();
			CompositionMDIDCR compositionEntry = new CompositionMDIDCR();
			bec.setResource(compositionEntry);
			this.addEntry(bec);
			return true;
		}
		return false;
	}
}