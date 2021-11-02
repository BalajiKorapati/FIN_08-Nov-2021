package com.oracle.FIN.steps;

import org.junit.Assert;

import com.oracle.FIN.pages.RequisitionsPage;
import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import report.oracle.ofs.ReportGeneration;

public class EditPurchaseRequistionsSteps extends BrowserDriverUtil {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	RequisitionsPage req=new RequisitionsPage();

	@When("^Search for the requistion \"([^\"]*)\"$")
	public void search_for_the_requistion(String ReqNum) throws Throwable {
		rpt.enterStepHeader("Search for the Requistion");

		if(cmnLib.waitForElementToBeVisible(req.lnkViewMore, 20)) {
			if(cmnLib.clickOnWebElement(req.lnkViewMore) && cmnLib.waitForElementToBeVisible(req.txtRequisition, 20)) {

				if(!cmnLib.enterDataInTextBox(req.txtRequisition, ReqNum, true)) {
					rpt.generateReport("", "Enter Requistion num", "", "Requistion Num :"+ReqNum,
							"Requistion number must be entered",
							"Failed to enter Requistion number", "Failed", "", true);
					Assert.fail("Failed to enter Requistion number");
				}

				if(!cmnLib.clickOnWebElement(req.btnSearch)) {
					rpt.generateReport("", "Click on Search", "", "",
							"Search button should be clicked",
							"Failed to click on search button", "Failed", "", true);
					Assert.fail("Failed to click on search button");
				}

				if(cmnLib.verifySearchedRecordExistsAndClickonResult(ReqNum, true)) {
					rpt.generateReport("", "Search results", "", "Requisition num :"+ReqNum,
							"Requisition number should exists in searched results",
							"Requisition number in searched results", "Passed", "", true);
				}else {
					rpt.generateReport("", "Search results", "", "Requisition num :"+ReqNum,
							"Requisition number should exists in searched results",
							"Requisition number doesn't exists in searched results", "Failed", "", true);
				}

			}
		}else {
			rpt.generateReport("", "Requistion page", "", "", "Requistion page must be visible", "Failed to open Requistion page", "Failed", "", true);
		}


	}

	@When("^Click on Actions and Edit requistion \"([^\"]*)\",\"([^\"]*)\"$")
	public void click_on_Actions_and_Edit_requistion(String Description, String Quantity) throws Throwable {

		if(!cmnLib.waitForElementToBeVisible(req.btnActions, 20)) {
			rpt.generateReport("", "Requistion details", "", "",
					"Requisition number should exists in searched results",
					"Requisition number doesn't exists in searched results", "Failed", "", true);
		}

		if(cmnLib.clickOnWebElement(req.btnActions)&& cmnLib.waitForElementToBeVisible(req.optionWithdrawAndEdit, 20)) {
			if(!cmnLib.clickOnWebElement(req.optionWithdrawAndEdit)) {
				rpt.generateReport("", "Click on Withdraw And Edit", "", "",
						"Withdraw And Edit should be clicked",
						"Failed to click on Withdraw And Edit", "Failed", "", true);
				Assert.fail("Failed to click on Withdraw And Edit");
			}

			if(cmnLib.waitForElementToBeVisible(req.msgWarning, 20)) {
				rpt.generateReport("", "Warning message", "", "", "Warning message should appear",
						"Warning message did appear", "Passed", "", true);
				cmnLib.clickOnWebElement(req.btnYes_Warning);
			}

			if(!cmnLib.waitForElementToBeVisible(req.txtDescription, 20)){
				rpt.generateReport("", "Enter description", "", "",
						"Description should be entered",
						"Failed to enter description", "Failed", "", true);
				Assert.fail("Failed to enter description");
			}

			if(!cmnLib.enterDataInTextfield(req.txtDescription, Description)) {
				rpt.generateReport("", "Enter description", "", "",
						"Description should be entered",
						"Failed to enter description", "Failed", "", true);
				Assert.fail("Failed to enter description");
			}

			if(!cmnLib.enterDataInTextBox(req.txtReqQty, Quantity, true)) {
				rpt.generateReport("", "Enter Quantity", "", "",
						"Quantity should be entered",
						"Failed to enter Quantity", "Failed", "", true);
				Assert.fail("Failed to enter Quantity");
			}

		}

	}

	@When("^Save the page$")
	public void save_the_page() throws Throwable {

		if(!cmnLib.clickOnWebElement(req.btnSubmit)) {
			rpt.generateReport("", "Click on Submit", "", "",
					"Submit button should be clicked",
					"Failed to click on Submit button", "Failed", "", true);
			Assert.fail("Failed to click on Submit button");
		}else {
			rpt.generateReport("", "Click on Submit", "", "",
					"Submit button should be clicked",
					"Submit button is clicked", "Passed", "", true);
		}

		if(cmnLib.waitForElementToBeVisible(req.msgConfirmation, 10)) {
			rpt.generateReport("", "Confirmation message", "", "", "Confirmation message should appear",
					"Confirmation message did appear", "Passed", "", true);
			cmnLib.clickOnWebElement(req.btnOK_Confirmation);
		}else {
			rpt.generateReport("", "Confirmation message", "", "", "Confirmation message should appear",
					"Confirmation message did not appear.Save was unsuccessfull", "Failed", "", true);
			Assert.fail("Confirmation message did not appear.Save was unsuccessfull");
		}
	}


	@When("^Click on Actions and Edit Order \"([^\"]*)\",\"([^\"]*)\"$")
	public void click_on_Actions_and_Edit_Order(String Description, String POQty) throws Throwable {

		if(!cmnLib.waitForElementToBeVisible(req.btnActions, 20)) {
			rpt.generateReport("", "Requistion details", "", "",
					"Requisition number should exists in searched results",
					"Requisition number doesn't exists in searched results", "Failed", "", true);
		}

		if(cmnLib.clickOnWebElement(req.btnActions)&& cmnLib.waitForElementToBeVisible(req.optionEditOrder, 20)) {
			if(!cmnLib.clickOnWebElement(req.optionEditOrder)) {
				rpt.generateReport("", "Click on Edit Order", "", "",
						"Edit order should be clicked",
						"Failed to click on Edit order", "Failed", "", true);
				Assert.fail("Failed to click on Edit order");
			}

			if(cmnLib.waitForElementToBeVisible(req.msgWarning, 20)) {
				rpt.generateReport("", "Warning message", "", "", "Warning message should appear",
						"Warning message did appear", "Passed", "", true);
				cmnLib.clickOnWebElement(req.btnYesWarning);
			}

			if(!cmnLib.waitForElementToBeVisible(req.txtDescription, 20)){
				rpt.generateReport("", "Enter description", "", "",
						"Description should be entered",
						"Failed to enter description", "Failed", "", true);
				Assert.fail("Failed to enter description");
			}

			if(!cmnLib.enterDataInTextfield(req.txtDescription, Description)) {
				rpt.generateReport("", "Enter description", "", "",
						"Description should be entered",
						"Failed to enter description", "Failed", "", true);
				Assert.fail("Failed to enter description");
			}

			if(!cmnLib.enterDataInTextBox(req.txtPOQty, POQty, true)) {
				rpt.generateReport("", "Enter Quantity", "", "",
						"Quantity should be entered",
						"Failed to enter Quantity", "Failed", "", true);
				Assert.fail("Failed to enter Quantity");
			}


		}

	}
}
