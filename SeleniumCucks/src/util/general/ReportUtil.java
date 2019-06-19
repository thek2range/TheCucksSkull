package util.general;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import util.sharedData;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class ReportUtil {

	public static String generateJSONReport ( List<String> jsonReportFiles )
	{
		File reportOutputDirectory = new File (sharedData.generatedReportDir + sharedData.environmentUnderTest + File.separator + util.general.tools.getCurrentDate());
		String jenkinsBasePath = "";
		String buildNumber = "K2 Dev Build";
		String projectName = "The K2 Project";
	  
		boolean skippedFails = true;
		boolean pendingFails = false;
		boolean undefinedFails = true;
		boolean missingFails = true;
		boolean runWithJenkins = false;
		boolean parallelTesting = false;

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		/* optionally only if you need */
		configuration.setStatusFlags(skippedFails, pendingFails, undefinedFails, missingFails);
		configuration.setParallelTesting(parallelTesting);
		configuration.setJenkinsBasePath(jenkinsBasePath);
		configuration.setRunWithJenkins(runWithJenkins);
		configuration.setBuildNumber(buildNumber);
		ReportBuilder reportBuilder = new ReportBuilder(jsonReportFiles, configuration);
		reportBuilder.generateReports();
		util.general.tools.infoLogger ("Report Generated at : " + reportOutputDirectory );
		try {
			return reportOutputDirectory.getCanonicalPath();
		} catch (Exception e) {
			return reportOutputDirectory.toString();
		}
	}
}
