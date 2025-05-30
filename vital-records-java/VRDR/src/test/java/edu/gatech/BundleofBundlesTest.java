package edu.gatech;

import edu.gatech.chai.VRDR.model.DeathCertificateDocument;
import edu.gatech.chai.VRDR.model.util.BuildDCD;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;

public class BundleofBundlesTest {
	public void testBundleOfBundles() {
		Bundle outerBundle = new Bundle();
		DeathCertificateDocument deathRecordDocument = BuildDCD.buildExampleDeathCertificateDocument();
		outerBundle.addEntry(new BundleEntryComponent().setResource(deathRecordDocument));
	}
}