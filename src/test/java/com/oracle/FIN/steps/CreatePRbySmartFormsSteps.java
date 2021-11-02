package com.oracle.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.oracle.FIN.pages.RequisitionsPage;
import com.oracle.FIN.pages.SmartFormsPage;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.java.en.When;
import report.oracle.ofs.ReportGeneration;

public class CreatePRbySmartFormsSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	RequisitionsPage req= new RequisitionsPage();
	SmartFormsPage smart=new SmartFormsPage();


	@When("^Click on  Business cards from Request forms$")
	public void click_on_Business_cards_from_Request_forms() throws Throwable {

		rpt.enterStepHeader("Click on  Business cards from Request forms");
		if(!cmnLib.waitForElementToBeVisible(req.lnkBusinessCards, 20)) {
			rpt.generateReport("", "Smart Forms", "", "",
					"Wait for the Requistion page to appear",
					"Failed to open Requistion page", "Failed", "", true);
			Assert.fail("Failed to open Requistion page");
		}else {
			cmnLib.clickOnWebElement(req.lnkBusinessCards);
			TimeUnit.SECONDS.sleep(2);
			rpt.generateReport("", "Business cards", "", "",
					"Click on Business cards link",
					"Clicked on Business cards link", "Passed", "", true);
		}
	}


	@When("^Enter Requests details \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_Requests_details(String RequestType, String Qty, String Name, String Title, String Address, String PhoneNum, String EmailID) throws Throwable {
		rpt.enterStepHeader("Request details");

		if(!cmnLib.waitForElementToBeVisible(smart.selectRequestType, 30)) {
			rpt.generateReport("", "Request details", "", "",
					"Business cards page should be displayed",
					"Failed to open Business cards page", "Failed", "", true);
			Assert.fail("Failed to open Business cards page");
		}

		if(!cmnLib.selectDropdownBy(smart.selectRequestType, RequestType, DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Request type selection", "", "Request Type :"+RequestType,
					"Request type should be selected",
					"Failed to select Request type", "Failed", "", true);
			Assert.fail("Failed to select Request type");
		}
		
		if(!cmnLib.enterDataInTextBox(smart.txtQty, Qty, true)) {
			rpt.generateReport("", "Quantity", "", "Quantity :"+Qty,
					"Quantity should be entered",
					"Failed to enter Quantity", "Failed", "", true);
			Assert.fail("Failed to enter Quantity");
		}
		
		if(!cmnLib.enterDataInTextBox(smart.txtName, Name, true)) {
			rpt.generateReport("", "Name", "", "Name :"+Name,
					"Name should be entered",
					"Failed to enter Name", "Failed", "", true);
			Assert.fail("Failed to enter Name");
		}
		
		if(!cmnLib.enterDataInTextBox(smart.txtTitle, Title, true)) {
			rpt.generateReport("", "Title", "", "Title :"+Title,
					"Title should be entered",
					"Failed to enter Title", "Failed", "", true);
			Assert.fail("Failed to enter Title");
		}
		
		if(!cmnLib.enterDataInTextBox(smart.txtAddress, Address, true)) {
			rpt.generateReport("", "Address", "", "Address :"+Address,
					"Address should be entered",
					"Failed to enter Address", "Failed", "", true);
			Assert.fail("Failed to enter Address");
		}
		
		if(!cmnLib.enterDataInTextBox(smart.txtPhoneNum, PhoneNum, true)) {
			rpt.generateReport("", "PhoneNum", "", "PhoneNum :"+PhoneNum,
					"PhoneNum should be entered",
					"Failed to enter PhoneNum", "Failed", "", true);
			Assert.fail("Failed to enter PhoneNum");
		}
		
		if(!cmnLib.enterDataInTextBox(smart.txtEmail, EmailID, true)) {
			rpt.generateReport("", "EmailID", "", "EmailID :"+EmailID,
					"EmailID should be entered",
					"Failed to enter EmailID", "Failed", "", true);
			Assert.fail("Failed to enter EmailID");
		}
		
		rpt.generateReport("", "Enter request details", "", "RequestType :"+RequestType+"\nQty :"+Qty+"\nName :"+Name+
				"\nTitle :"+Title+"\nAddress :"+Address+"\nPhoneNum :"+PhoneNum+"\nEmailID :"+EmailID,
				"Request details should be entered",
				"Request details are entered", "Passed", "", true);
	}
}
