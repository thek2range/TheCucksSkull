package stepDef.common;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import util.sharedData;

public class stepDefinitions {
	
	@Before 
	public void setUp(Scenario scenario)
	{ 
	    sharedData.thisScenario = scenario;
	} 
	@After 
	public void tearDown(Scenario scenario)
	{ 
	 //   sharedData.thisScenario = scenario;
		try {
			sharedData.appInstance.close();
			sharedData.appInstance.quit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	} 

	@Given("^The user Opened the AUT\\.$")
	public void the_user_Opened_to_AUT() throws Throwable {
	    sharedData.appInstance = util.general.driver.getAUT();
	    util.general.tools.captureScreenshot ();
	}
}
