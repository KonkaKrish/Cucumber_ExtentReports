package com.API.BDDStepDef;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.testautomation.Listeners.ExtentReportListener;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import reusableFiles.Utilities;

public class BDDStepDef extends ExtentReportListener{

	ExtentTest logInfo=null;
	String ExpectedValue = null;
	String ActualValue = null;
	String NoBody = "noBody";
	String Expected = "Expected";
	String apiType = "API_Type";
	
	@Given("Setup the Account Details")
	public void mdAInitialization() throws Exception {
		try {
			// Writing test and Node to extent report
			test = extent.createTest(Feature.class, "Get API");
			test=test.createNode(Scenario.class, "Employee Account Details");
			logInfo=test.createNode(new GherkinKeyword("Then"), "Execute Get API");
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",logInfo,e);
		}
	}

	@Then("Execute Account details API")
	public void mdAAPIexecute() throws Exception {
		try {
			// Get Expected value from excel sheet by providing key
			ExpectedValue = Utilities.GetCellData("GetTest",Expected);
			ActualValue = Utilities.getActualValue(Utilities.GetCellData("GetTest",apiType),Utilities.ReadPropertiesFile("GetURL"),NoBody);
			Utilities.Compare_WriteResults(ExpectedValue, ActualValue, logInfo);
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",logInfo,e);
		}

	}

	@Given("EMP Details")
	public void PostGiven() throws Exception{
		try {
			// Writing test and Node to extent report
			test = extent.createTest(Feature.class, "Post API");
			test=test.createNode(Scenario.class, "Get the EMP Details");
			logInfo=test.createNode(new GherkinKeyword("Then"), "Execute Post API");
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",logInfo,e);
		}
	}
	
	@Then("Get the EMP Details")
	public void PostExecution() throws Exception{
		try {
			// Get Expected value from excel sheet by providing key
			ExpectedValue = Utilities.GetCellData("PostTest",Expected);
			ActualValue = Utilities.getActualValue(Utilities.GetCellData("PostTest",apiType),Utilities.ReadPropertiesFile("PostURL"),Utilities.ReadPropertiesFile("PostAPI"));
			Utilities.Compare_WriteResults(ExpectedValue, ActualValue, logInfo);
		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL",logInfo,e);
		}
		
	}
}
