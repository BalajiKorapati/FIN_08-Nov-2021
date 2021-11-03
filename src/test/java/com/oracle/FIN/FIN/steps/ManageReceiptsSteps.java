package com.oracle.FIN.FIN.steps;

import org.junit.Assert;

import com.oracle.FIN.FIN.pages.ManageReceiptsPage;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.en.Then;
import com.oracle.acs.util.report.ReportGeneration;

public class ManageReceiptsSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	ManageReceiptsPage manageReceipts = new ManageReceiptsPage();
	private String strWriteOffAmount;

	@Then("^Perform Search for a Receipt \"([^\"]*)\", \"([^\"]*)\"$")
	public void perform_Search_for_a_Receipt(String receiptNumber, String customerName) throws Throwable {
		rpt.enterStepHeader("Perform Search for Receipt");
		if (!cmnLib.selectDropdownBy(manageReceipts.selReceiptNumberOperator, "Equals", DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Select Receipt Number Operator", "", "Equals",
					"Receipt Number Operator must be selected", "Receipt Number Operator not selected", "Failed", "",
					true);
			Assert.fail("Receipt Number Operator not selected");
		}

		if (!cmnLib.enterDataInTextBox(manageReceipts.txtReceiptNumber, receiptNumber, true)) {
			rpt.generateReport("", "Enter Receipt Number", "", receiptNumber, "Receipt Number must be entered",
					"Receipt Number not entered", "Failed", "", true);
			Assert.fail("Receipt Number not entered");
		}

		if (customerName.length() > 0) {
			if (!cmnLib.enterDataInTextBox(manageReceipts.txtCustomerName, customerName, true)) {
				rpt.generateReport("", "Enter Customer Name", "", customerName, "Customer Name must be entered",
						"Customer Name not entered", "Failed", "", true);
				Assert.fail("Customer Name not entered");
			}
		}

		rpt.generateReport("", "Enter Search Criteria", "",
				"Receipt Number: " + receiptNumber + "\nCustomer Name: " + customerName,
				"Search Criteria must be entered", "Search Criteria not entered", "Passed", "", true);

		if (cmnLib.clickOnWebElement(manageReceipts.btnSearch)
				&& cmnLib.waitForElementToBeVisible(manageReceipts.tblBdySearchResults)) {
			rpt.generateReport("", "Click Search button", "", "",
					"Search button must be clicked and Search Results must be displayed",
					"Clicked Search button and Search Results displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Search button", "", "",
					"Search button must be clicked and Search Results must be displayed",
					"Either not clicked Search button or Search Results not displayed", "Failed", "", true);
			Assert.fail("Either not clicked Search button or Search Results not displayed");
		}
	}

	@Then("^Click Receipt \"([^\"]*)\" in Search Results$")
	public void click_receipt_in_Search_Results(String receiptNumber) throws Throwable {
		if (manageReceipts.clickReceiptsInResults(receiptNumber) && cmnLib.verifyHeader("Edit Receipt")) {
			rpt.generateReport("", "Click Receipt Number in results", "", receiptNumber,
					"Receipt Number must be clicked and Edit Receipt page must be displayed",
					"Clicked Receipt Number and Edit Receipt page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Receipt Number in results", "", receiptNumber,
					"Receipt Number must be clicked and Edit Receipt page must be displayed",
					"Either not clicked Receipt Number or Edit Receipt page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked Receipt Number or Edit Receipt page not displayed");
		}
	}

	@Then("^Enter Write-Off details \"([^\"]*)\", \"([^\"]*)\"$")
	public void enter_Write_Off_details(String writeOffAmount, String receivableActivity) throws Throwable {
		rpt.enterStepHeader("Enter Write-Off details");
		strWriteOffAmount = writeOffAmount;
		if (!cmnLib.enterDataInTextBox(manageReceipts.txtWriteOffAmount, writeOffAmount, true)) {
			rpt.generateReport("", "Enter Write-Off Amount", "", writeOffAmount, "Write-Off Amount must be entered",
					"Write-Off Amount not entered", "Failed", "", true);
			Assert.fail("Write-Off Amount not entered");
		}

		if (!cmnLib.selectDropdownBy(manageReceipts.selReceivablesActivity, receivableActivity,
				DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Select Receivable Activity", "", receivableActivity,
					"Receivable Activity must be selected", "Receivable Activity not selected", "Failed", "", true);
			Assert.fail("Receivable Activity not selected");
		}

		rpt.generateReport("", "Enter Write-Off details", "",
				"Write-Off Amount: " + writeOffAmount + "\nReceivable Activity: " + receivableActivity,
				"Required details must be entered", "Entered required details", "Passed", "", true);
	}

	@Then("^Click OK to Create Write-Off and Click Save$")
	public void click_OK_to_Create_Write_Off_and_Click_Save() throws Throwable {
		if (cmnLib.clickOnWebElement(manageReceipts.btnOK)
				&& cmnLib.waitForElementToBeInvisible(manageReceipts.btnOK)) {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Create Write-Off window must be closed",
					"Clicked OK button and Create Write-Off window closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Create Write-Off window must be closed",
					"Either not clicked OK button or Create Write-Off window not closed", "Failed", "", true);
			Assert.fail("Either not clicked OK button or Create Write-Off window not closed");
		}

		if (cmnLib.clickOnWebElement(manageReceipts.btnSave)) {
			rpt.generateReport("", "Click Save button", "", "", "Save button must be clicked", "Clicked Save button",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Click Save button", "", "", "Save button must be clicked",
					"Not clicked Save button", "Passed", "", true);
			Assert.fail("Not clicked Save button");
		}
	}

	@Then("^Verify \"([^\"]*)\" and \"([^\"]*)\" in Receipt Details$")
	public void verify_and_in_Receipt_Details(String receivablesActivity, String columnName) throws Throwable {
		if (manageReceipts.getReceiptDetail(receivablesActivity, columnName).equals(strWriteOffAmount)) {
			rpt.generateReport("", "Verify " + receivablesActivity, "", "",
					receivablesActivity + " must be created in the Receipt Detail",
					receivablesActivity + " created in the Receipt Detail", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify " + receivablesActivity, "", "",
					receivablesActivity + " must be created in the Receipt Detail",
					receivablesActivity + " not created in the Receipt Detail", "Failed", "", true);
			Assert.fail(receivablesActivity + " not created in the Receipt Detail");
		}
	}
}
