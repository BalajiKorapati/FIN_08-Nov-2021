package com.oracle.FIN.steps;

import org.testng.Assert;

import com.oracle.FIN.pages.ScheduledProcessesPage;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.java.en.Then;
import report.oracle.ofs.ReportGeneration;

public class ScheduledProcessesSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	ScheduledProcessesPage processPage = new ScheduledProcessesPage();

	@Then("^Verify \"([^\"]*)\" report Status is \"([^\"]*)\"$")
	public void verify_report_Status_is(String reportName, String status) throws Throwable {
		String strStatusActual = processPage.verifyReportStatus(CreatePositivePayFileSteps.strProcessID, reportName);
		if (strStatusActual != null) {
			rpt.generateReport("", "Search the report", "", "", "Report must appear in results",
					"Report appears in results", "Passed", "", false);
			rpt.generateReport("", "Verify Report Status",
					"Wait and Refresh until status changes to Succeeded/Error/Warning/Canceled",
					"\nReport Name: " + reportName, "Report status must change to Succeeded/Error/Warning/Canceled",
					"Report status changed to " + strStatusActual, "Passed", "", true);
		} else {
			rpt.generateReport("", "Search the report and verify Report Status",
					"Wait and Refresh until status changes to Succeeded/Error/Warning/Canceled",
					"Process ID: " + CreatePositivePayFileSteps.strProcessID + "\nReport Name: " + reportName,
					"Report status must change to Succeeded/Error/Warning/Canceled", "Unable to verify report status",
					"Failed", "", true);
			Assert.fail("Failed to verify report status");
		}
	}
	
	
	@Then("^Verify \"([^\"]*)\" program Status is \"([^\"]*)\"$")
	public void verify_program_Status_is(String reportName, String status) throws Throwable {
		
		String strStatus_Actual = processPage.verifyReportStatus(RunDepreciationSteps.strProcessID, reportName);
		if (strStatus_Actual != null) {
			rpt.generateReport("", "Search the report", "", "", "Report must appear in results",
					"Report appears in results", "Passed", "", false);
			rpt.generateReport("", "Verify Report Status",
					"Wait and Refresh until status changes to Succeeded/Error/Warning/Canceled",
					RunDepreciationSteps.strProcessID + ", " + reportName,
					"Report status must change to Succeeded/Error/Warning/Canceled",
					"Report status changed to " + strStatus_Actual, "Passed", "", true);
		} else {
			rpt.generateReport("", "Search the report and verify Report Status",
					"Wait and Refresh until status changes to Succeeded/Error/Warning/Canceled",
					RunDepreciationSteps.strProcessID + ", " + reportName,
					"Report status must change to Succeeded/Error/Warning/Canceled",
					"Unable to verify report status", "Failed", "", true);
			Assert.fail("Failed to verify report status");
		}
}
}
