package Runner;

import org.junit.runner.RunWith;

import util.sharedData;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(glue = "stepDef", 
                 features = "Features", format = { "html:" + sharedData.parentReportDir +  sharedData.htmlReportName, "json:" + sharedData.parentReportDir + sharedData.jsonReportName }, 
                 tags = { "@TestTag1"}, monochrome = true)

public class TestRunner {

}
