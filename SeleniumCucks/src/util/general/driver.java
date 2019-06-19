package util.general;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import util.sharedData;

public class driver {

	public static WebDriver getAUT() {
		WebDriver appUnderTest = null;
		 try {
				if ( sharedData.browserUnderTest.trim().equalsIgnoreCase("Chrome") ) 
				{
				     System.setProperty("webdriver.chrome.driver", sharedData.chomeDriverPath );
				     //K2 : Implement conditional capabilities 
				     sharedData.appInstance = new ChromeDriver();
				     sharedData.appInstance.get( util.sharedData.URLUnderTest );
			    	 sharedData.appInstance.manage().window().maximize();
				     return sharedData.appInstance;
				}
				if ( sharedData.browserUnderTest.trim().equalsIgnoreCase("Browser Stack") ) 
				{
						final String USERNAME = "yourusername";
						final String AUTOMATE_KEY = "yourkeyhere";
						final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
					 	DesiredCapabilities caps = new DesiredCapabilities();
					    caps.setCapability("browser", "IE");
					    caps.setCapability("browser_version", "11.0");
					    caps.setCapability("os", "Windows");
					    caps.setCapability("os_version", "10");
					    caps.setCapability("browserstack.debug", "true");
					    sharedData.appInstance = new RemoteWebDriver(new URL(URL), caps);
					    sharedData.appInstance.get( util.sharedData.URLUnderTest );
					    sharedData.appInstance.manage().window().maximize();
					    System.out.println("sharedData.appInstance URL"  +sharedData.appInstance.getCurrentUrl());
				     return sharedData.appInstance;
				}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		 return null;
	}

}
