package Selenium.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsTestNG {

	public static ExtentReports getReportObject() {
		String pathString = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(pathString);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		 ExtentReports extenet = new ExtentReports();
		extenet.attachReporter(reporter);
		extenet.setSystemInfo("Tester", "Thrupthi");

		return extenet;
	}

}
