

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import util.sharedData;
import util.general.tools;
import Runner.TestRunner;

public class RunnerMain {

	public static void main(String[] args) {
		try {
			Result result = JUnitCore.runClasses(TestRunner.class);
			for (Failure failure : result.getFailures()) {
				System.out.println(failure.toString());
			}
			System.out.println(result.wasSuccessful());

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		List<String> reportFiles = new ArrayList<String>();
		reportFiles.add(sharedData.parentReportDir + sharedData.jsonReportName);

		String message = util.general.ReportUtil.generateJSONReport( reportFiles );
		System.out.println("Report Generated At : " + message );
	}
	
	
}
