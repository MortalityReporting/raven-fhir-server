package edu.gatech.MDI;


import java.util.List;

import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;

import edu.gatech.chai.MDI.context.MDIFhirContext;
import edu.gatech.chai.MDI.model.resource.BundleDocumentMDIAndEDRS;
import edu.gatech.chai.MDI.model.resource.CompositionMDIAndEDRS;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	MDIFhirContext context;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
    	super( testName );
    	context = new MDIFhirContext();
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testProducingDeathCertificateDocument()
    {
    	BundleDocumentMDIAndEDRS bundle = BuildMDIAndEdrsDocument.buildExampleBundleDocumentMDIAndEDRS();
    	assertTrue( true );
    }

    public void testNameMDIAndEDRSBundleDoesNotCreateDuplicateComposition()
    {
        BundleDocumentMDIAndEDRS bundle = BuildMDIAndEdrsDocument.buildExampleBundleDocumentMDIAndEDRS();
    	String encoded = context.getCtx().newJsonParser().encodeResourceToString(bundle);
        BundleDocumentMDIAndEDRS roundTripBundle = context.getCtx().newJsonParser().parseResource(BundleDocumentMDIAndEDRS.class,encoded);
        assertFalse("Second Resource of MDI-And-EDRS Bundle after parsing was a Composition. Incorrect parsing issue.", roundTripBundle.getEntry().get(1).getResource() instanceof CompositionMDIAndEDRS);
        String roundTripEncoded = context.getCtx().newJsonParser().encodeResourceToString(roundTripBundle);
        System.out.println(roundTripEncoded);
    }

    public void testTypesFromSerializedBundle()
    {
        BundleDocumentMDIAndEDRS bundle = BuildMDIAndEdrsDocument.buildExampleBundleDocumentMDIAndEDRS();
    	String encoded = context.getCtx().newJsonParser().encodeResourceToString(bundle);
        BundleDocumentMDIAndEDRS roundTripBundle = context.getCtx().newJsonParser().parseResource(BundleDocumentMDIAndEDRS.class,encoded);
        for(BundleEntryComponent bec:roundTripBundle.getEntry()){
            System.out.println(bec.getResource().getClass().getName());
        }
    }
}