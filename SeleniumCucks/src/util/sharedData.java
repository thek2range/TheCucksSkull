package util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;

public class sharedData {
	public static WebDriver appInstance = null;

	public static final String suitConfigurationFile = "./TestData/SuitConfiguration.xls";
	public static final String envConfigurationFile = "./TestData/EnvironmentConfiguration.xls";
	public static final String elementObjectModelFile = "./TestData/ElementObjectModel.xls";

	public static final String suitConfigurationSheetName = "SuitProp";
	public static final String driverConfigurationSheetName = "DriverProp";
	public static final String autConfigurationSheetName = "AUTProp";

	public static final String envDataSheet = "EnvironmentDataSheet";
	
	public static final String pomDataSheet = "Page Object Model";


	
	public static HSSFSheet suitConfigurationSheetInstance = util.general.ExcelUtility.getSheet(util.sharedData.suitConfigurationFile, util.sharedData.suitConfigurationSheetName );
	public static HSSFSheet driverConfigurationSheetInstance = util.general.ExcelUtility.getSheet(util.sharedData.suitConfigurationFile, util.sharedData.driverConfigurationSheetName );
	public static HSSFSheet autConfigurationSheetInstance = util.general.ExcelUtility.getSheet(util.sharedData.suitConfigurationFile, util.sharedData.autConfigurationSheetName );
	public static HSSFSheet envConfigurationSheetInstance = util.general.ExcelUtility.getSheet(util.sharedData.envConfigurationFile, util.sharedData.envDataSheet );

	public static HSSFSheet pomSheetInstance = util.general.ExcelUtility.getSheet(elementObjectModelFile, pomDataSheet );

	/******************************************* Start of Suit Variables *************************************************/
	public static Scenario thisScenario = null;
	public static  String browserUnderTest = util.general.ExcelUtility.getValue(suitConfigurationSheetInstance, "Browser Under Test",1);
	public static  String chomeDriverPath = util.general.ExcelUtility.getValue(driverConfigurationSheetInstance, "Chrome Driver Path");
	//public static  int maximPageLoadWaitTime = Integer.parseInt(util.general.ExcelUtility.getValue(suitConfigurationSheetInstance, "Maximum time to Wait For a Page to load"));
	public static  int maximPageLoadWaitTime = 5000;
	public static  String environmentUnderTest = util.general.ExcelUtility.getValue(suitConfigurationSheetInstance, "AUT Alias",1);
	public static  String URLUnderTest = util.general.ExcelUtility.getValue(autConfigurationSheetInstance, environmentUnderTest.trim() ,2);
    //Consider 0 as Browser, 1 as Mobile Device
	public static int testDevice = util.general.ExcelUtility.getValue(suitConfigurationSheetInstance, "Test Run Equipment" ,1).equalsIgnoreCase("Browser")?0:1;
	public static  boolean willTakeScreenshot = util.general.ExcelUtility.getValue(suitConfigurationSheetInstance, "Will take Screenshot",1).toLowerCase().equals("yes")?true:false;
	public static  boolean willEmbeddedScreenshot = util.general.ExcelUtility.getValue(suitConfigurationSheetInstance, "Will Embedded Screenshots in log",1).toLowerCase().equals("yes")?true:false;

	/******************************************* End of Suit Variables *************************************************/

	public static int appIdentifierTypePosition = ( testDevice == 0 ) ? 1: 3;
	public static int appIdentifierValuePosition = appIdentifierTypePosition + 1;
	public static int autPosition = Integer.parseInt(util.general.ExcelUtility.getValue(autConfigurationSheetInstance, environmentUnderTest,1));
	//public static int appInputValuePosition = appIdentifierTypePosition + 1;

	/******************************************* Start of Report Variables *************************************************/
	public static final String parentReportDir = "./Reports/";
	public static final String backupReportDir = "./Reports/BackUpReports/";
	public static final String generatedReportDir = "./Reports/GeneratdReports/";
	public static final String generatedScreenShotsDir = "./Reports/GeneratdScreenShots/";

	public static final String jsonReportName = "generatedJSONReport.json";
	public static final String htmlReportName = "generatedHTMLReport.html";

	/*******************************************  End of Report Variables  *************************************************/
	
	/******************************************* Start of Mail Variables *************************************************/
	public static final String mailSendersAddress = "logSendersAddress";
	public static final String mailSendersPassword = "logSendersPassword";
	public static final String mailReceiversAddress = util.general.ExcelUtility.getValue(suitConfigurationSheetInstance, "Log receivers address" ,1);
	public static final boolean willSendLogsOvereMail = util.general.ExcelUtility.getValue(suitConfigurationSheetInstance, "Will Send logs over eMail" ,1).equalsIgnoreCase("Yes")?true:false;
	/******************************************* End of Mail Variables *************************************************/

}
