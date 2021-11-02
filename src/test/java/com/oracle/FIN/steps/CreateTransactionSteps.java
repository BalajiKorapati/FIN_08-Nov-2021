package com.oracle.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.oracle.FIN.pages.CreateTransactionPage;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.java.en.Then;
import report.oracle.ofs.ReportGeneration;

public class CreateTransactionSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	CreateTransactionPage createTrxPage = new CreateTransactionPage();
	public static String strTrxNum;

	@Then("^Enter Transaction Header details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void enter_Transaction_Header_details(String transactionClass, String businessUnit, String source,
			String type, String transactionDate, String accountingDate, String billToName) throws Throwable {
		rpt.enterStepHeader("Enter Transaction Header details");
		if (transactionClass.length() > 0) {
			if (!cmnLib.selectDropdownBy(createTrxPage.selTransactionClass, transactionClass,
					DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Transaction Class", "", transactionClass,
						"Transaction Class must be selected", "Transaction Class not selected", "Failed", "", true);
				Assert.fail("Transaction Class not selected");
			}
		}

		if (businessUnit.length() > 0) {
			if (!createTrxPage.selectBUFromSuggestionPopup("Business Unit", businessUnit)) {
				rpt.generateReport("", "Select Business Unit", "", businessUnit, "Business Unit must be selected",
						"Business Unit not selected", "Failed", "", true);
				Assert.fail("Business Unit not selected");
			}
		}

		if (source.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createTrxPage.txtTransactionSource, source, true)) {
				rpt.generateReport("", "Enter Transaction Source", "", source, "Transaction Source must be entered",
						"Transaction Source not entered", "Failed", "", true);
				Assert.fail("Transaction Source not entered");
			}
		}

		if (type.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createTrxPage.txtTransactionType, type, true)) {
				rpt.generateReport("", "Enter Transaction Type", "", type, "Transaction Type must be entered",
						"Transaction Type not entered", "Failed", "", true);
				Assert.fail("Transaction Type not entered");
			}
		}

		if (transactionDate.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createTrxPage.txtTransactionDate, transactionDate, true)) {
				rpt.generateReport("", "Enter Transaction Date", "", transactionDate,
						"Transaction Date must be entered", "Transaction Date not entered", "Failed", "", true);
				Assert.fail("Transaction Date not entered");
			}
		}

		if (accountingDate.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createTrxPage.txtAccountingDate, accountingDate, true)) {
				rpt.generateReport("", "Enter Accounting Date", "", accountingDate, "Accounting Date must be entered",
						"Accounting Date not entered", "Failed", "", true);
				Assert.fail("Accounting Date not entered");
			}
		}

		if (!cmnLib.enterDataInTextBox(createTrxPage.txtBillToName, billToName, true)) {
			TimeUnit.SECONDS.sleep(2);
			rpt.generateReport("", "Enter Bill-to Name", "", billToName, "Bill-to Name must be entered",
					"Bill-to Name not entered", "Failed", "", true);
			Assert.fail("Bill-to Name not entered");
		}

		if (!cmnLib.enterDataInTextBox(createTrxPage.txtShipToName, "", true)) {
			rpt.generateReport("", "Enter Ship-to Name", "", billToName, "Ship-to Name must be entered",
					"Ship-to Name not entered", "Failed", "", true);
			Assert.fail("Ship-to Name not entered");
		}

		rpt.generateReport("", "Enter Transaction Header details", "",
				"Transaction Class: " + transactionClass + "\nBusiness Unit: " + businessUnit + "\nTransaction Source: "
						+ source + "\nTransaction Type: " + type + "\nTransaction Date: " + transactionDate
						+ "\nAccounting Date: " + accountingDate + "\nBill-to Name: " + billToName,
				"Required details must be entered", "Required details entered", "Passed", "", true);
	}

	@Then("^Enter Transaction Line Details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void enter_Transaction_Line_Details(String memoLine, String description, String quantity, String unitPrice,
			String trxBusinessCategory) throws Throwable {
		rpt.enterStepHeader("Enter Line Details");
		if (memoLine.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createTrxPage.txtMemoLineNameLine1, memoLine, true)) {
				rpt.generateReport("", "Enter Memo Line", "", memoLine, "Memo Line must be entered",
						"Memo Line not entered", "Failed", "", true);
				Assert.fail("Memo Line not entered");
			}
			TimeUnit.SECONDS.sleep(3);
		}

		if (description.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createTrxPage.txtDescriptionLine1, description, true)) {
				rpt.generateReport("", "Enter Description", "", description, "Description must be entered",
						"Description not entered", "Failed", "", true);
				Assert.fail("Description not entered");
			}
		}

		if (quantity.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createTrxPage.txtQuantityLine1, quantity, true)) {
				rpt.generateReport("", "Enter Quantity", "", quantity, "Quantity must be entered",
						"Quantity not entered", "Failed", "", true);
				Assert.fail("Quantity not entered");
			}
		}

		if (unitPrice.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createTrxPage.txtUnitPriceLine1, unitPrice, true)) {
				rpt.generateReport("", "Enter Unit Price", "", unitPrice, "Unit Price must be entered",
						"Unit Price not entered", "Failed", "", true);
				Assert.fail("Unit Price not entered");
			}
		}

		if (trxBusinessCategory.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createTrxPage.txtTrxBusinessCatLine1, trxBusinessCategory, true)) {
				rpt.generateReport("", "Enter Transaction Business Category", "", trxBusinessCategory,
						"Transaction Business Category must be entered", "Transaction Business Category not entered",
						"Failed", "", true);
				Assert.fail("Transaction Business Category not entered");
			}
		}

		rpt.generateReport("", "Enter Line Details", "",
				"Memo Line: " + memoLine + "\nDescription: " + description + "\nQuantity: " + quantity
						+ "\nUnit Price: " + unitPrice + "\nTransaction Business Category: " + trxBusinessCategory,
				"Line details must be entered", "Entered Line details", "true", "", true);

	}

	@Then("^Click Save and verify status of the transaction is \"([^\"]*)\"$")
	public void click_Save_and_verify_status_of_the_transaction_is(String status) throws Throwable {
		if (cmnLib.clickOnWebElement(createTrxPage.btnSave)
				&& cmnLib.waitForElementToBeVisible(createTrxPage.hdrEditTransaction)) {
			strTrxNum = createTrxPage.hdrEditTransaction.getText().split(" ")[3];
			rpt.generateReport("", "Click Save button", "", "",
					"Save button must be clicked and Transaction must be created",
					"Clicked on Save button and Transaction is created" + "\nTransaction Number: " + strTrxNum,
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Click Save button", "", "",
					"Save button must be clicked and Transaction must be created",
					"Either not clicked on Save button or Transaction not created", "Failed", "", true);
			Assert.fail("Either not clicked on Save button or Transaction not created");
		}

		if (cmnLib.waitForTextToBePresentInElement(createTrxPage.tblDataStatus, status)) {
			rpt.generateReport("", "Verify Status", "", "", "Status must be " + status, "Status is " + status, "Passed",
					"", true);
		} else {
			rpt.generateReport("", "Verify Status", "", "", "Status must be " + status, "Status is " + status, "Failed",
					"", true);
			Assert.fail("Status not displayed as expected " + status);
		}

		if (!cmnLib.clickOnWebElement(createTrxPage.btnSave)) {
			TimeUnit.SECONDS.sleep(2);
			rpt.generateReport("", "Click Save button", "", "",
					"Save button must be clicked and Transaction must be created",
					"Either not clicked on Save button or Transaction not created", "Failed", "", true);
			Assert.fail("Either not clicked on Save button or Transaction not created");
		}
	}

	@Then("^Click Edit Distributions under Actions dropdown$")
	public void click_Edit_Distributions_under_Actions_dropdown() throws Throwable {
		if (cmnLib.clickOnWebElement(createTrxPage.drpdwnActions)
				&& cmnLib.waitForElementToBeVisible(createTrxPage.editDistributions)) {
			rpt.generateReport("", "Click Actions dropdown", "", "",
					"Actions dropdown must be clicked and Edit Distributions option must be displayed",
					"Clicked on Actions dropdown and Edit Distributions option displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Actions dropdown", "", "",
					"Actions dropdown must be clicked and Edit Distributions option must be displayed",
					"Either not clicked on Actions dropdown or Edit Distributions option not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Actions dropdown or Edit Distributions option not displayed");
		}

		if (cmnLib.clickOnWebElement(createTrxPage.editDistributions)
				&& cmnLib.waitForElementToBeInvisible(createTrxPage.btnSaveAndCloseEditDistributions)) {
			rpt.generateReport("", "Click Edit Distributions option", "", "",
					"Edit Distributions option must be clicked and Edit Distributions window must be displayed",
					"Clicked on Edit Distributions option and Edit Distributions window displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Edit Distributions option", "", "",
					"Edit Distributions option must be clicked and Edit Distributions window must be dsiplayed",
					"Either not clicked on Edit Distributions option or Edit Distributions window not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked on Edit Distributions option or Edit Distributions window not displayed");
		}
	}

	@Then("^Review Distributions and Click Save and Close$")
	public void review_Distributions_and_Click_Save_and_Close() throws Throwable {
		if (cmnLib.clickOnWebElement(createTrxPage.btnSaveAndCloseEditDistributions)
				&& cmnLib.waitForElementToBeInvisible(createTrxPage.btnSaveAndCloseEditDistributions)) {
			rpt.generateReport("", "Click Save and Close button", "", "",
					"Save and Close button must be clicked and Edit Distributions window must be closed",
					"Clicked on Save and Close button and Edit Distributions window closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Save and Close button", "", "",
					"Save and Close button must be clicked and Edit Distributions window must be closed",
					"Either not clicked on Save and Close button or Edit Distributions window not closed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Save and Close button or Edit Distributions window not closed");
		}

	}

	@Then("^Click Complete and Review and verify status of the transaction is \"([^\"]*)\"$")
	public void click_Complete_and_Review_and_verify_status_of_the_transaction_is(String status) throws Throwable {
		if (cmnLib.clickOnWebElement(createTrxPage.lnkCompleteAndCreate)
				&& cmnLib.waitForElementToBeVisible(createTrxPage.eleCompleteAndReview)) {
			rpt.generateReport("", "Click Complete dropdown Arrow", "", "",
					"Complete Arrow must be clicked and Complete and Review option must be displayed",
					"Clicked on Complete Arrow and Complete and Review option displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Complete dropdown Arrow", "", "",
					"Complete Arrow must be clicked and Complete and Review option must be displayed",
					"Either not clicked on Complete Arrow or Complete and Review option not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Complete Arrow or Complete and Review option not displayed");
		}

		if (cmnLib.clickOnWebElement(createTrxPage.eleCompleteAndReview)
				&& cmnLib.waitForTextToBePresentInElement(createTrxPage.tblDataStatus, status)) {
			rpt.generateReport("", "Click Complete and Review", "", "",
					"Complete and Review option must be clicked and Status must be " + status,
					"Clicked on Complete and Review option and Status is " + status, "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Complete and Review", "", "",
					"Complete and Review option must be clicked and Status must be " + status,
					"Either not clicked on Complete and Review option or Status not displayed as expected - " + status,
					"Passed", "", true);
			Assert.fail(
					"Either not clicked on Complete and Review option or Status not displayed as expected - " + status);
		}

	}

}
