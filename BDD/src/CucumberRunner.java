package com.API.runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.API.report.reportHelp;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import reusableFiles.Utilities;
import cucumber.api.testng.*;

@CucumberOptions(monochrome = true, features = "src/test/resources/features", glue = "com.API.BDDStepDef", plugin = {
		"pretty", "json:target/cucumber.json" })

public class CucumberRunner {

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		Utilities.openExcelWorkBook();
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(dataProvider = "features")
	public void feature(PickleEventWrapper eventwrapper, CucumberFeatureWrapper cucumberFeature) throws Throwable {
		// testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
		testNGCucumberRunner.runScenario(eventwrapper.getPickleEvent());
	}

	@DataProvider // (parallel=true)
	public Object[][] features() {
		// return testNGCucumberRunner.provideFeatures();
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		Utilities.Close();
		testNGCucumberRunner.finish();
	}

	@AfterSuite(alwaysRun = true)
	public void generateHTMLReports() {
		reportHelp.generateCucumberReport();
	}
}
