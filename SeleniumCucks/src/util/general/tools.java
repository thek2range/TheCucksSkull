package util.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilePermission;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import util.sharedData;

public class tools {
	public static void customWait ( Thread currentThread )
	{
		try {
			currentThread.sleep( util.sharedData.maximPageLoadWaitTime);
		} catch (Exception e) {}
	}

	public static WebElement pointTheElement (String identifierType, String identifierValue)
	{
		try {
			if (identifierType.equalsIgnoreCase("ID"))
				return sharedData.appInstance.findElement(By.id(identifierValue));
			if (identifierType.equalsIgnoreCase("xPath"))
				return sharedData.appInstance.findElement(By.xpath(identifierValue));
			if (identifierType.equalsIgnoreCase("Name"))
				return sharedData.appInstance.findElement(By.name(identifierValue));
			if (identifierType.equalsIgnoreCase("linkText"))
				return sharedData.appInstance.findElement(By.linkText(identifierValue));
			if (identifierType.equalsIgnoreCase("partialLinkText"))
				return sharedData.appInstance.findElement(By.partialLinkText(identifierValue));
			if (identifierType.equalsIgnoreCase("Class"))
				return sharedData.appInstance.findElement(By.className(identifierValue));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static WebElement getElement ( String key )
	{
		String identifierType = null;
		String identifierValue = null;
		WebElement element = null;
		String value = null;
		try {
			identifierType = util.general.ExcelUtility.getValue(sharedData.pomSheetInstance, key ,sharedData.appIdentifierTypePosition);
			identifierValue = util.general.ExcelUtility.getValue(sharedData.pomSheetInstance, key ,sharedData.appIdentifierValuePosition);
			return util.general.tools.pointTheElement(identifierType, identifierValue);
		} catch (Exception e) {
			return null;
		}
	}
	public static boolean copyFiles ( String sourceDirectory, String destDirectory , boolean willAddTimeStamp )
	{
		try {
            File[] files = new File ( sourceDirectory ).listFiles();
            for(File file:files)
            {
            	String newFileName = willAddTimeStamp ? file.getName().trim().concat("_").concat( String.valueOf(util.general.tools.getTimeStamp() ) ):file.getName().trim();
                InputStream in = new FileInputStream(file);
                OutputStream out = new FileOutputStream(destDirectory+"/"+ newFileName);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0)
                {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }            
            return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public static String copyFile( String sourceFile, String destDirectory,  boolean willAddTimeStamp){
		File source = new File ( sourceFile  );
    	String newFileName = willAddTimeStamp ?
    			source.getName().trim().substring( 0, source.getName().trim().lastIndexOf(".")).concat("_").concat(String.valueOf(util.general.tools.getTimeStamp() )).concat(".").concat(source.getName().trim().substring(source.getName().trim().lastIndexOf(".") + 1)):source.getName().trim();
    	String canonicalPathOfNewFile = null;
		File dest = new File ( destDirectory + File.separator + newFileName );
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	        canonicalPathOfNewFile = dest.getCanonicalPath();
	        sourceChannel.close();
	        destChannel.close();
	        return canonicalPathOfNewFile;
	       } catch (Exception e) { return null;}
	}

	public static long getTimeStamp() {
		return System.currentTimeMillis();
	}
	
	public static String getCurrentDate()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static void infoLogger( String message ) {
		//K2 : Implement conditional print.
		System.out.println("INFO : " + message);
	}
	public static void scenarioLogger( String imagePath ) {
		if (sharedData.willEmbeddedScreenshot && sharedData.willTakeScreenshot ) 
			sharedData.thisScenario.write("<a href=\"" + util.general.tools.takeScreenshot() + "\" target=\"_blank\">ScreenShot</a> ");
	}

	public static String takeScreenshot ()
	{
		if ( sharedData.willTakeScreenshot )
		{
			File src=((TakesScreenshot)sharedData.appInstance ).getScreenshotAs(OutputType.FILE); 
			File scrFile = new File (sharedData.generatedScreenShotsDir+sharedData.environmentUnderTest+File.separator+getCurrentDate()+File.separator+sharedData.environmentUnderTest + "_" + getCurrentDate() + "_" + getTimeStamp() + ".jpg");;
			try {
				FileUtils.copyFile(src,  scrFile );
				return scrFile.getCanonicalPath();
			} catch (Exception e) {
				return null;
			}
			
		}
		return null;
	}

	public static void captureScreenshot() {
		scenarioLogger(takeScreenshot());
	}
}
