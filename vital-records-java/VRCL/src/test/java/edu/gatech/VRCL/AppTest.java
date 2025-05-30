package edu.gatech.VRCL;

import edu.gatech.chai.VRCL.context.VRCLFhirContext;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
		extends TestCase
{
	VRCLFhirContext context;
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest( String testName )
	{
		super( testName );
		context = new VRCLFhirContext();
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( AppTest.class );
	}

	public void testBasic() {
		assertTrue(true);
	}
}