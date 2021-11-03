package com.oracle.FIN.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.oracle.FIN.FIN.pages.RequisitionsPage;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.en.Then;
import com.oracle.acs.util.report.ReportGeneration;

public class RequisitionSteps {
	
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	RequisitionsPage req= new RequisitionsPage();
	ProcessRequistionLines_ReturnSteps processReq=new ProcessRequistionLines_ReturnSteps();
	
	@Then("^Search for Requisition created$")
	public void search_for_Requisition_created() throws Throwable {
		rpt.enterStepHeader("Search for requisition");
		
		cmnLib.waitForPageLoaded();
		TimeUnit.SECONDS.sleep(2);
		if(cmnLib.clickOnLinkText(ProcessRequistionLines_ReturnSteps.reqNum)) {
			rpt.generateReport("", "Requisition search", "", "",
					"Click on Requistion num",
					"Clicked on Requistion num", "Passed", "", true);
		}else {
			rpt.generateReport("", "Requisition search", "", "",
					"Click on Requistion num",
					"Failed to click on Requisition number, number doesn't exists", "Failed", "", true);
			Assert.fail("Failed to click on Requisition number, number doesn't exists");
		}
		
	}

	@Then("^Review the Approval list$")
	public void review_the_Approval_list() throws Throwable {
		
		if(!cmnLib.waitForElementToBeVisible(req.btnActions, 30)) {
			rpt.generateReport("", "Requisition page", "", "",
					"Requisition page should appear",
					"Failed to open Requisition page", "Failed", "", true);
			Assert.fail("Failed to open Requisition page");
		}
		if(!cmnLib.clickOnLinkText("Pending approval")) {
			rpt.generateReport("", "Requisition status", "", "",
					"Requistion should be in Pending approval status",
					"Requistion is not in Pending approval status", "Failed", "", true);
			Assert.fail("Requistion is not in Pending approval status");
		}else {
			rpt.generateReport("", "Requisition status", "", "",
					"Requistion should be in Pending approval status",
					"Requistion is in Pending approval status", "Passed", "", true);
		}

		
		if(!cmnLib.waitForElementToBeVisible(req.btnMoreOptions, 30)) {
			rpt.generateReport("", "Approval details", "", "",
					"Approval details page should appear",
					"Approval details page did not appear", "Failed", "", true);
			Assert.fail("Approval details page did not appear");
		}else {
			rpt.generateReport("", "Approval details", "", "",
					"Approval details page should appear",
					"Approval details page appeared", "Passed", "", true);
			
			cmnLib.scrollDownByJSE();
			TimeUnit.SECONDS.sleep(3);
			
			rpt.generateReport("", "Approval details", "", "",
					"Approval Hierarchy appear should appear",
					"Approval Hierarchy appear ", "Passed", "", true);
		}
		
	}

}
