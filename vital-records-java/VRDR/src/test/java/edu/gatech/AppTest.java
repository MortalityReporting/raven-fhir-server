package edu.gatech;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import ca.uhn.fhir.context.ConfigurationException;
import ca.uhn.fhir.parser.DataFormatException;
import edu.gatech.chai.VRDR.context.VRDRFhirContextDataStructuresOnly;
import edu.gatech.chai.VRDR.model.DeathCertificateDocument;
import edu.gatech.chai.VRDR.model.DeathDate;
import edu.gatech.chai.VRDR.model.Decedent;
import edu.gatech.chai.VRDR.model.InjuryIncident;
import edu.gatech.chai.VRDR.model.MannerOfDeath;
import edu.gatech.chai.VRDR.model.*;
import edu.gatech.chai.VRDR.model.util.BuildDCD;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
		extends TestCase
{
	VRDRFhirContextDataStructuresOnly context;
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest( String testName )
	{
		super( testName );
		context = new VRDRFhirContextDataStructuresOnly();
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
		DeathCertificateDocument deathCertificateDocument = BuildDCD.buildExampleDeathCertificateDocument();
		String encoded = context.getCtx().newJsonParser().encodeResourceToString(deathCertificateDocument);
		assertTrue(encoded != null && encoded.length() > 0);
	}

	public void testConsumingDeathCertificateDocument()
	{
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("Testcase_Certificate.json").getFile());
		DeathCertificateDocument deathCertificateDocument = null;
		try {
			deathCertificateDocument = (DeathCertificateDocument) context.getCtx().newJsonParser().parseResource(new FileInputStream(file));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (DataFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String encoded = context.getCtx().newJsonParser().encodeResourceToString(deathCertificateDocument);
		assertTrue(encoded != null && encoded.length() > 0);
	}

	public void testGetResourceFromDocument() {
		DeathCertificateDocument deathCertificateDocument = BuildDCD.buildExampleDeathCertificateDocument();
		List<MannerOfDeath> mannerOfDeathList = deathCertificateDocument.getMannerOfDeath();
		for(MannerOfDeath manner:mannerOfDeathList) {
			String jsonForm = context.getCtx().newJsonParser().encodeResourceToString(manner);
			assertTrue(jsonForm != null && jsonForm.length() > 0);
		}
	}

	public void testPartialDecedentBirthDateRecord() {
		Decedent decedent = BuildDCD.buildDecedentWithBirthDateAbsentReason();
		String jsonForm = context.getCtx().newJsonParser().encodeResourceToString(decedent);
		assertTrue(jsonForm != null && jsonForm.length() > 0);
	}

	public void testPartialDeathDateRecord() {
		DeathDate deathDate = BuildDCD.buildDeathWithPartialDateAbsentReason();
		String jsonForm = context.getCtx().newJsonParser().encodeResourceToString(deathDate);
		assertTrue(jsonForm != null && jsonForm.length() > 0);
	}

	public void testPartialDeathDateTimeRecord() {
		DeathDate deathDate = BuildDCD.buildDeathWithPartialDateTimeAbsentReason();

		// test known date day, unknown time
		assertEquals("date-day", deathDate.deathDate.getValueDateTimeType().getExtension().get(0).getExtension().get(2).getUrl());
		assertEquals("16", deathDate.deathDate.getValueDateTimeType().getExtension().get(0).getExtension().get(2).getValue().primitiveValue());
		assertEquals("time-absent-reason", deathDate.deathDate.getValueDateTimeType().getExtension().get(0).getExtension().get(3).getUrl());
		assertEquals("unknown", deathDate.deathDate.getValueDateTimeType().getExtension().get(0).getExtension().get(3).getValue().primitiveValue());

		// test unknown date day, known time
		deathDate = BuildDCD.buildDeathWithPartialDateTime();
		assertEquals("day-absent-reason", deathDate.deathDate.getValueDateTimeType().getExtension().get(0).getExtension().get(2).getUrl());
		assertEquals("unknown", deathDate.deathDate.getValueDateTimeType().getExtension().get(0).getExtension().get(2).getValue().primitiveValue());
		assertEquals("date-time", deathDate.deathDate.getValueDateTimeType().getExtension().get(0).getExtension().get(3).getUrl());
		assertEquals("T16:47:04-05:00", deathDate.deathDate.getValueDateTimeType().getExtension().get(0).getExtension().get(3).getValue().primitiveValue());
	}

	public void testPartialInjuryDateTimeRecord() {
		InjuryIncident injuryIncident = BuildDCD.buildInjuryIncidentWithPartialDateTimeAbsentReason();

		// test known date day, unknown time
		assertEquals("date-day", injuryIncident.injuryIncident.getValueDateTimeType().getExtension().get(0).getExtension().get(2).getUrl());
		assertEquals("16", injuryIncident.injuryIncident.getValueDateTimeType().getExtension().get(0).getExtension().get(2).getValue().primitiveValue());
		assertEquals("time-absent-reason", injuryIncident.injuryIncident.getValueDateTimeType().getExtension().get(0).getExtension().get(3).getUrl());
		assertEquals("unknown", injuryIncident.injuryIncident.getValueDateTimeType().getExtension().get(0).getExtension().get(3).getValue().primitiveValue());

		// test unknown date day, known time
		injuryIncident = BuildDCD.buildInjuryIncidentWithPartialDateTime();
		assertEquals("day-absent-reason", injuryIncident.injuryIncident.getValueDateTimeType().getExtension().get(0).getExtension().get(2).getUrl());
		assertEquals("unknown", injuryIncident.injuryIncident.getValueDateTimeType().getExtension().get(0).getExtension().get(2).getValue().primitiveValue());
		assertEquals("date-time", injuryIncident.injuryIncident.getValueDateTimeType().getExtension().get(0).getExtension().get(3).getUrl());
		assertEquals("T16:47:04-05:00", injuryIncident.injuryIncident.getValueDateTimeType().getExtension().get(0).getExtension().get(3).getValue().primitiveValue());
	}
}