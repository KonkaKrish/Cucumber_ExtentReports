package com.testautomation.Listeners;

import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import reusableFiles.Utilities;

public class ExtentReportListener {

	public static ExtentHtmlReporter report = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;

	@BeforeMethod
	public static ExtentReports setUp() {
		//System.out.println("I'm Setup");
		String reportLocation = ".//Reports//Extent_Report_"+ Utilities.DynamicValue() +".html";
		report = new ExtentHtmlReporter(reportLocation);		
		report.config().setDocumentTitle("API Testing Report");
		report.config().setReportName("API Testing Report");
		report.config().setTheme(Theme.STANDARD);		
		//System.out.println("Extent Report location initialized . . .");
		report.start();
		
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Application", "API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		//System.out.println("System Info. set in Extent Report");		
		return extent;
	}
	
	public static void testStepHandle(String teststatus,ExtentTest extenttest,Throwable throwable) {
		switch (teststatus) {
		case "FAIL":		
			extenttest.fail(MarkupHelper.createLabel("Scenario is Failed : ", ExtentColor.RED));			
			extenttest.error(throwable.fillInStackTrace());	
		break;
		case "PASS":			
			extenttest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
			break;
		default:
			break;
		}
	}
	
	public static void Actual_Expected_Comp(String teststatus,ExtentTest extenttest) {
		if (teststatus == "FAIL") {
			extenttest.fail(MarkupHelper.createLabel("Actual And Expected are NOT Matching : ", ExtentColor.RED));
		} else if (teststatus == "PASS"){
			extenttest.pass(MarkupHelper.createLabel("Actual And Expected are Matching : ", ExtentColor.GREEN));
		}
	}
	
}