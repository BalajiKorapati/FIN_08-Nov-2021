package com.oracle.FIN.common.steps;

import java.util.concurrent.TimeUnit;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;
import io.cucumber.java.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import com.oracle.acs.util.report.ReportGeneration;

public class ReportingSteps extends BrowserDriverUtil{

	private static ThreadLocal<ReportGeneration> rpt = new ThreadLocal<ReportGeneration>();
	private String scenarioName;
	public static Common_Library cmnLib;
	
	public static ReportGeneration getRpt() {
		return rpt.get();
	}

	public static void setRpt(ReportGeneration report) {
		rpt.set(report);
	}

	@Given("^Setup Reporting for Finance$")
	public void setup_Reporting_for_finance() throws Throwable {
		
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		
		String testCaseID;
		String testCaseName;
		cmnLib = new Common_Library();
		
		if(scenarioName.contains("_")) {
			testCaseID = scenarioName.substring(0, scenarioName.indexOf("_"));
			testCaseName = scenarioName.substring(scenarioName.indexOf("_") + 1);
				
		}else {
			testCaseID = "FIN";
			testCaseName = scenarioName;
		}
		
		setRpt(new ReportGeneration(testCaseID, testCaseName));
	}

	@Before
	public void setScenarioName(Scenario scenario) {
		this.scenarioName = scenario.getName();
	}

}
