package com.oracle.FIN.steps;

import org.junit.Assert;

import com.oracle.FIN.pages.ManageInvoicesPage;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.steps.CommonAppSteps;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import report.oracle.ofs.ReportGeneration;

public class ManageInvoicesSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	ManageInvoicesPage manageInvoices = new ManageInvoicesPage();
	CommonAppSteps appSteps = new CommonAppSteps();
	private String strInvoiceNumber;
	public static String strPaymentProcessProfile;

	@When("^Perform Search for an Invoice with \"([^\"]*)\"$")
	public void perform_Search_for_an_Invoice_with(String invoiceNumber) throws Throwable {
		rpt.enterStepHeader("Perform Search for an Invoice");
		strInvoiceNumber = invoiceNumber;
		if (cmnLib.enterDataInTextBox(manageInvoices.txtInvoiceNumber, invoiceNumber, true)) {
			rpt.generateReport("", "Enter Invoice Number", "", invoiceNumber, "Invoice Number must be entered",
					"Entered Invoice Number", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Invoice Number", "", invoiceNumber, "Invoice Number must be entered",
					"Invoice Number not entered", "Failed", "", true);
			Assert.fail("Invoice Number not entered");
		}

		if (cmnLib.clickOnWebElement(manageInvoices.btnSearch)
				&& cmnLib.waitForElementToBeVisible(manageInvoices.tblBodySearchResults)) {
			rpt.generateReport("", "Click Search button", "", "",
					"Search button must be clicked and Results must be displayed",
					"Clicked on Search button and Results displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Search button", "", "",
					"Search button must be clicked and Results must be displayed",
					"Either not clicked on Search button or Results not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Search button or Results not displayed");
		}
	}

	@When("^Click Invoice Number to open invoice$")
	public void click_Invoice_Number_to_open_invoice() throws Throwable {
		rpt.enterStepHeader("Cancel Invoice");
		if (manageInvoices.clickInvoiceNumber(strInvoiceNumber)
				&& cmnLib.waitForElementToBeVisible(manageInvoices.hdrInvoiceDetails)) {
			rpt.generateReport("", "Click Invoice Number in results", "", strInvoiceNumber,
					"Invoice Number must be clicked and Invoice Details must be displayed",
					"Clicked on Invoice Number and Invoice Details displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Invoice Number in results", "", strInvoiceNumber,
					"Invoice Number must be clicked and Invoice Details must be displayed",
					"Either not clicked on Invoice Number or Invoice Details not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Invoice Number or Invoice Details not displayed");
		}
	}

	@Then("^User clicks \"([^\"]*)\" under Actions dropdown$")
	public void user_clicks_Cancel_Invoice_under_Actions_dropdown(String optionValue) throws Throwable {
		if (appSteps.selectOptionFromInvoiceActionsDropdown(manageInvoices.drpdwnActions, optionValue)) {
			rpt.generateReport("", "Click Actions > " + optionValue, "", "",
					"Invoice Number must be clicked and Invoice Details must be displayed",
					"Clicked on Invoice Number and Invoice Details displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Actions > " + optionValue, "", "",
					"Invoice Number must be clicked and Invoice Details must be displayed",
					"Either not clicked on Invoice Number or Invoice Details not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Invoice Number or Invoice Details not displayed");
		}
	}

	@Then("^Warning message displayed$")
	public void warning_message_displayed() throws Throwable {
		if (cmnLib.waitForElementToBeVisible(manageInvoices.msgWarning)) {
			rpt.generateReport("", "Verify warning message displayed", "", "", "Warning message must be displayed",
					"Warning message displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify warning message displayed", "", "", "Warning message must be displayed",
					"Warning message not displayed", "Failed", "", true);
			Assert.fail("Warning message not displayed");
		}

	}

	@Then("^User clicks OK button$")
	public void user_clicks_OK_button() throws Throwable {
		
		if (cmnLib.clickOnWebElement(manageInvoices.btnOK)
				&& cmnLib.waitForElementToBeInvisible(manageInvoices.btnOK)) {
			rpt.generateReport("", "Click OK", "", "", "OK button must be clicked and Warning message must be closed",
					"Clicked on OK button and Warning message closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK", "", "", "OK button must be clicked and Warning message must be closed",
					"Either not clicked on OK button or Warning message not closed", "Failed", "", true);
			Assert.fail("Either not clicked on OK button or Warning message not closed");
		}
	}

	@Then("^Invoice validation status changes to \"([^\"]*)\"$")
	public void invoice_validation_status_changes_to(String status) throws Throwable {
		if (cmnLib.verifyTextOnElement(manageInvoices.lnkInvoiceValidationStatus, status)) {
			rpt.generateReport("", "Verify Invoice Validation Status", "", status,
					"Invoice Validation Status must be displayed as " + status,
					"Invoice Validation Status displayed as expected", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Invoice Validation Status", "", status,
					"Invoice Validation Status must be displayed as " + status,
					"Invoice Validation Status not displayed as expected", "Failed", "", true);
			Assert.fail("Invoice Validation Status not displayed as expected");
		}
	}

	@Then("^Invoice Amount changes to \"([^\"]*)\"$")
	public void invoice_Amount_changes_to(String amount) throws Throwable {
		if (cmnLib.verifyTextOnElement(manageInvoices.invoiceAmount, amount)) {
			rpt.generateReport("", "Verify Invoice Amount", "", amount, "Invoice Amount must be displayed as " + amount,
					"Invoice Amount displayed as expected", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Invoice Amount", "", amount, "Invoice Amount must be displayed as " + amount,
					"Invoice Amount not displayed as expected", "Failed", "", true);
			Assert.fail("Invoice Amount not displayed as expected");
		}
	}

	@When("^Select Row in Invoice Results$")
	public void select_Row_in_Invoice_Results() throws Throwable {
		if (manageInvoices.selectRowInInvoiceResults(strInvoiceNumber)) {
			rpt.generateReport("", "Select Invoice Row", "", strInvoiceNumber, "Row must be selected", "Row selected",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Select Invoice Row", "", strInvoiceNumber, "Row must be selected",
					"Row not selected", "Failed", "", true);
			Assert.fail("Row not selected");
		}
	}

	@When("^Click Pay in Full button$")
	public void click_Pay_in_Full_button() throws Throwable {
		if (cmnLib.clickOnWebElement(manageInvoices.btnPayInFull)
				&& cmnLib.waitForElementToBeVisible(manageInvoices.btnSubmitPayInFull)) {
			rpt.generateReport("", "Click Pay in Full button", "", "",
					"Pay In Full must be clicked and Pay in Full window must be opened",
					"Clicked on Pay in Full button and Pay in Full window opened", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Pay in Full button", "", "",
					"Pay In Full must be clicked and Pay in Full window must be opened",
					"Either not clicked on Pay in Full button or Pay in Full window not opened", "Failed", "", true);
			Assert.fail("Either not clicked on Pay in Full button or Pay in Full window not opened");
		}

	}

	@Then("^Enter Payment Details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void enter_Payment_Details(String bankAccount, String paymentProcessProfile, String paymentDocument)
			throws Throwable {
		rpt.enterStepHeader("Enter Payment Details");
		if (!cmnLib.enterDataInTextBox(manageInvoices.txtBankAccount, bankAccount, true)) {
			rpt.generateReport("", "Enter Bank Account", "", bankAccount, "Bank Account must be entered",
					"Bank Account not entered", "Failed", "", true);
			Assert.fail("Bank Account not entered");
		}

		strPaymentProcessProfile = paymentProcessProfile;
		if (!cmnLib.enterDataInTextBox(manageInvoices.txtPaymentProcessProfile, paymentProcessProfile, true)) {
			rpt.generateReport("", "Enter Payment Process Profile", "", paymentProcessProfile,
					"Payment Process Profile must be entered", "Payment Process Profile not entered", "Failed", "",
					true);
			Assert.fail("Payment Process Profile not entered");
		}

		if (!cmnLib.enterDataInTextBox(manageInvoices.txtPaymentDocument, paymentDocument, true)) {
			rpt.generateReport("", "Enter Payment Document", "", paymentDocument, "Payment Document must be entered",
					"Payment Document not entered", "Failed", "", true);
			Assert.fail("Payment Document not entered");
		}

		rpt.generateReport("", "Enter Payment Details", "",
				"Bank Accout:" + bankAccount + "\nPayment Process Profile: " + paymentProcessProfile
						+ "\nPayment Document: " + paymentDocument,
				"Payment details must be entered", "Payment details entered", "Passed", "", true);

	}

	@Then("^Click Submit button and verify Payment Confirmation message$")
	public void click_Submit_button() throws Throwable {
		if (cmnLib.clickOnWebElement(manageInvoices.btnSubmitPayInFull)
				&& cmnLib.waitForElementToBeVisible(manageInvoices.msgConfirmationPayment)) {
			rpt.generateReport("", "Click Submit button", "", "",
					"Submit button must be clicked and Payment Confirmation message must be displayed",
					"Clicked on Submit button and Payment Confirmation message displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Submit button", "", "",
					"Submit button must be clicked and Payment Confirmation message must be displayed",
					"Either not clicked on Submit button or Payment Confirmation message not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Submit button or Payment Confirmation message not displayed");
		}
	}

	@Then("^Click OK button in Payment Confirmation window$")
	public void click_OK_button() throws Throwable {
		if (cmnLib.clickOnWebElement(manageInvoices.btnOKConfirmationPayment)
				&& cmnLib.waitForElementToBeInvisible(manageInvoices.btnOKConfirmationPayment)) {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Payment Confirmation message must be closed",
					"Clicked on OK button and Payment Confirmation message closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Payment Confirmation message must be closed",
					"Either not clicked on OK button or Payment Confirmation message not closed", "Failed", "", true);
			Assert.fail("Either not clicked on OK button or Payment Confirmation message not closed");
		}
	}
}
