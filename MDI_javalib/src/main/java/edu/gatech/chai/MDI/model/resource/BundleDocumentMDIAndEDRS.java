package edu.gatech.chai.MDI.model.resource;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Identifier;

import ca.uhn.fhir.model.api.annotation.ResourceDef;

@ResourceDef(name = "Bundle", profile = "http://hl7.org/fhir/us/mdi/StructureDefinition/Bundle-document-mdi-and-edrs")
public class BundleDocumentMDIAndEDRS extends Bundle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7622309533132493325L;

	public BundleDocumentMDIAndEDRS() {
		super();
		this.setType(BundleType.DOCUMENT);
	}
	public BundleDocumentMDIAndEDRS(Identifier identifier,CompositionMDIAndEDRS compositionEntry) {
		super();
		this.setIdentifier(identifier);
		this.setType(BundleType.DOCUMENT);
		BundleEntryComponent bec = new BundleEntryComponent();
		bec.setResource(compositionEntry);
		this.addEntry(bec);
	}
	
	//This Composition must always be the correct case here.
	public CompositionMDIAndEDRS getCompositionMDIAndEDRS() {
		checkCompositionMDIAndEDRS();
		return (CompositionMDIAndEDRS) this.getEntryFirstRep().getResource();
	}

	//Helper function to help init full urls AFTER the id of thecomposition has been set.
	public BundleDocumentMDIAndEDRS setFullUrlOnCompositionMDIAndEDRS() {
		checkCompositionMDIAndEDRS();
		this.getEntryFirstRep().setFullUrl(this.getEntryFirstRep().getResource().getResourceType()+"/"+this.getEntryFirstRep().getResource().getId());
		return this;
	}

	public boolean checkCompositionMDIAndEDRS(){
		if(this.getEntry().isEmpty()){
			BundleEntryComponent bec = new BundleEntryComponent();
			CompositionMDIAndEDRS compositionEntry = new CompositionMDIAndEDRS();
			bec.setResource(compositionEntry);
			this.addEntry(bec);
			return true;
		}
		return false;
	}
}