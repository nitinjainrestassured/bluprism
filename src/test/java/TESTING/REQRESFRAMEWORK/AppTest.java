package TESTING.REQRESFRAMEWORK;


import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;


import org.testng.TestNG;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	@Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
        System.out.println("Hello World!");
		String xmlPath = System.getProperty("user.dir") + "/testng.xml";

		// Run all the tests in testng.xml
		// Create object of TestNG Class
		TestNG runner = new TestNG();

		// Create a list of String
		List<String> suitefiles = new ArrayList<String>();

		// Add xml file which you have to execute
		suitefiles.add(xmlPath);

		// now set xml file for execution
		runner.setTestSuites(suitefiles);

		// finally execute the runner using run method
		runner.run();
    }
}

