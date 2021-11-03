package com.oracle.FIN.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.oracle.FIN.FIN.pages.CreateInvoicePage;
import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.FIN.common.steps.CommonAppSteps;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.oracle.acs.util.report.ReportGeneration;

public class CreateInvoiceSteps extends BrowserDriverUtil {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	CreateInvoicePage createInvoice = new CreateInvoicePage();
	CommonAppSteps appSteps = new CommonAppSteps();
	private String strAmount;
	private String strInvoiceNumber;

	@When("^Enter Invoice Header details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void enter_Invoice_Header_details(String businessUnit, String supplier, String supplierSite,
			String legalEntity, String invoiceNumber, String amount, String type, String date, String paymentTerms,
			String termsDate) throws Throwable {
		rpt.enterStepHeader("Enter Invoice Header details");
		if (businessUnit.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createInvoice.txtBusinessUnit, businessUnit, true)) {
				rpt.generateReport("", "Enter Business Unit", "", businessUnit, "Business Unit must be entered",
						"Business Unit not entered", "Failed", "", true);
				Assert.fail("Business Unit not entered");
			}
		}

		if (!cmnLib.enterDataInTextBox(createInvoice.txtSupplier, supplier, true)) {
			rpt.generateReport("", "Enter Supplier", "", supplier, "Supplier must be entered", "Supplier not entered",
					"Failed", "", true);
			Assert.fail("Supplier not entered");
		}

		if (supplierSite.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createInvoice.txtSupplierSite, supplierSite, true)) {
				rpt.generateReport("", "Enter Supplier Site", "", supplierSite, "Supplier Site must be entered",
						"Supplier Site not entered", "Failed", "", true);
				Assert.fail("Supplier Site not entered");
			}
		}

		if (legalEntity.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createInvoice.txtlegalEntity, legalEntity, true)) {
				rpt.generateReport("", "Enter Legal Entity", "", legalEntity, "Legal Entity must be entered",
						"Legal Entity not entered", "Failed", "", true);
				Assert.fail("Legal Entity not entered");
			}
		}

		strInvoiceNumber = cmnLib.randomNumber(invoiceNumber);
		if (!cmnLib.enterDataInTextBox(createInvoice.txtInvoiceNumber, strInvoiceNumber, true)) {
			rpt.generateReport("", "Enter Invoice Number", "", strInvoiceNumber, "Invoice Number must be entered",
					"Invoice Number not entered", "Failed", "", true);
			Assert.fail("Invoice Number not entered");
		}

		strAmount = amount;
		if (!cmnLib.enterDataInTextBox(createInvoice.txtAmount, amount, true)) {
			rpt.generateReport("", "Enter Amount", "", amount, "Amount must be entered", "Amount not entered", "Failed",
					"", true);
			Assert.fail("Amount not entered");
		}

		if (type.length() > 0) {
			if (!cmnLib.selectDropdownBy(createInvoice.drpdwnType, type, DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Type", "", type, "Type must be selected", "Type not selected", "Failed",
						"", true);
				Assert.fail("Type not selected");
			}
		}
		
		if(type.equalsIgnoreCase("Credit memo") || type.equalsIgnoreCase("Debit memo")) {
			if(cmnLib.waitForElementToBeVisible(createInvoice.msgWarningCreditDebitMemo)) {
				cmnLib.clickOnWebElement(createInvoice.btnOKWarningCreditMemo);
			}
		}

		if (date.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createInvoice.txtDate, date, true)) {
				rpt.generateReport("", "Enter Date", "", date, "Date must be entered", "Date not entered", "Failed", "",
						true);
				Assert.fail("Date not entered");
			}
		}

		if (paymentTerms.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createInvoice.txtPaymentTerms, paymentTerms, true)) {
				rpt.generateReport("", "Enter Payment Terms", "", paymentTerms, "Payment Terms must be entered",
						"Payment Terms not entered", "Failed", "", true);
				Assert.fail("Payment Terms not entered");
			}
		}

		if (termsDate.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createInvoice.txtTermsDate, termsDate, true)) {
				rpt.generateReport("", "Enter Terms Date", "", termsDate, "Terms Date must be entered",
						"Terms Date not entered", "Failed", "", true);
				Assert.fail("Terms Date not entered");
			}
		}

		rpt.generateReport("", "Enter Invoice Header details", "",
				"Business Unit: " + businessUnit + "\nSupplier: " + supplier + "\nSupplier Site: " + supplierSite
						+ "\nLegal Entity: " + legalEntity + "\nInvoice Number: " + strInvoiceNumber + "\nAmount: "
						+ amount + "\nType: " + type + "\nDate: " + date + "\nPayment Terms: " + paymentTerms
						+ "\nTerms Date: " + termsDate,
				"Required details must be entered", "Required details entered", "Passed", "", true);

	}

	@When("^Enter Line Details$")
	public void enter_Line_Details() throws Throwable {
		rpt.enterStepHeader("Enter Line details");
		String LinesSection_ExpandCollapseStatus = null;
		if (cmnLib.waitForElementToBeClickable(createInvoice.Line_Expand_Collapse)) {
			LinesSection_ExpandCollapseStatus = createInvoice.Line_Expand_Collapse.getAttribute("title");
			System.out.println(LinesSection_ExpandCollapseStatus);
		}

		if (LinesSection_ExpandCollapseStatus.contains("Expand")) {
			if (cmnLib.clickOnWebElement(createInvoice.Line_Expand_Collapse)
					&& cmnLib.waitForElementToBeVisible(createInvoice.btnDistributions)) {
				rpt.generateReport("", "Expand Lines section", "Click on Expand/Collapse arrow", "",
						"Lines section must be expanded", "Lines section expanded", "Passed", "", false);
			} else {
				rpt.generateReport("", "Expand Lines section", "Click on Expand/Collapse arrow", "",
						"Lines section must be expanded", "Lines section not expanded", "Failed", "", true);
				Assert.fail("Lines section not expanded");
			}
		}

		if (cmnLib.clickOnWebElement(createInvoice.Amount_Line1)
				&& cmnLib.enterDataInTextBox(createInvoice.Amount_Line1, strAmount, true)) {
			cmnLib.waitForPageLoaded();
			rpt.generateReport("", "Enter Invoice Line Amount", "", strAmount, "Amount must be entered",
					"Amount entered", "Info", "", true);
		} else {
			rpt.generateReport("", "Enter invoice Line Amount", "", strAmount, "Amount must be entered",
					"Amount not entered", "Failed", "", true);
			Assert.fail("Failed to enter Invoice Line Amount");
		}

	}

	@When("^Save the Invoice$")
	public void save_the_Invoice() throws Throwable {

		cmnLib.clickOnWebElement(createInvoice.btnSave);
		if (cmnLib.waitForElementToBeVisible(createInvoice.Continue_Warning, 5)) {
			cmnLib.clickOnWebElement(createInvoice.Continue_Warning);
			cmnLib.waitForElementToBeInvisible(createInvoice.Continue_Warning);
		}
		if (cmnLib.waitForTextToBePresentInElement(createInvoice.hdrCreateInvoice,
				"Create Invoice: " + strInvoiceNumber)) {
			rpt.generateReport("", "Click Save button", "Verify created Invoice Number", strInvoiceNumber,
					"Save button must be clicked and Invoice Number must be created",
					"Save button clicked and Invoice Number created", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Save button", "Verify created Invoice Number", strInvoiceNumber,
					"Save button must be clicked and Invoice Number must be created",
					"Either Save button not clicked or Invoice Number not created", "Failed", "", true);
			Assert.fail("Either Save button not clicked or Invoice Number not created");
		}
	}

	@When("^Click \"([^\"]*)\" under Invoice Actions dropdown$")
	public void click_under_Actions_dropdown(String option) throws Throwable {
		if (appSteps.selectOptionFromInvoiceActionsDropdown(createInvoice.drpdwnActions, option)) {
			rpt.generateReport("", "Select " + option + " under Invoice Actions", "", option,
					option + " must be selected", "selected " + option, "Passed", "", false);
		} else {
			rpt.generateReport("", "Select " + option + " under Invoice Actions", "", option,
					option + " must be selected", option + " not selected", "Failed", "", true);
			Assert.fail("Failed to select " + option + " under Invoice Actions");
		}
	}

	@Then("^Verify Total Amount and Update Invoice Header Amount$")
	public void verify_Total_Amount_and_Update_Invoice_Header_Amount() throws Throwable {
		Thread.sleep(5);
		String strTotalValue = null;
		String strTotalStatus = null;
		if (cmnLib.waitForElementToBeVisible(createInvoice.Total_Status)
				&& cmnLib.waitForElementToBeVisible(createInvoice.TotalValue)) {
			strTotalValue = createInvoice.TotalValue.getText();
			strTotalStatus = createInvoice.Total_Status.getAttribute("title");
			System.out.println("Status: " + strTotalStatus);
			System.out.println("Total Value: " + strTotalValue);
		}

		if (strTotalStatus.contains("Out of Balance")) {
			rpt.enterStepHeader("Update Header Amount");
			TimeUnit.SECONDS.sleep(1);
			cmnLib.enterDataInTextBox(createInvoice.txtAmount, strTotalValue, true);
			if (cmnLib.clickOnWebElement(createInvoice.btnSave)) {
				if (cmnLib.waitForElementToBeVisible(createInvoice.Continue_Warning, 5)) {
					cmnLib.clickOnWebElement(createInvoice.Continue_Warning);
					cmnLib.waitForElementToBeInvisible(createInvoice.Continue_Warning);
				}
				rpt.generateReport("", "Update Invoice Amount", "", strTotalValue, "Invoice Amount must be updated",
						"Invoice Amount updated", "Info", "", true);
			} else {
				rpt.generateReport("", "Update Invoice Amount", "", strTotalValue, "Invoice Amount must be updated",
						"Invoice Amount not updated", "Info", "", false);
				Assert.fail("Failed to update Invoice Amount");
			}
		}
	}

	@Then("^\"([^\"]*)\" the Invoice and verify Invoice status changes to \"([^\"]*)\"$")
	public void the_Invoice_and_verify_Invoice_status_changes_to(String option, String status) throws Throwable {
		if (appSteps.selectOptionFromInvoiceActionsDropdown(createInvoice.drpdwnActions, option)) {
			rpt.generateReport("", "Select Validate under Invoice Actions", "", option, "Validate must be selected",
					"Validate selected", "Passed", "", false);
		} else {
			rpt.generateReport("", "Select Validate under Invoice Actions", "", option, "Validate must be selected",
					"Validate not selected", "Failed", "", true);
			Assert.fail("Failed to select Validate under Invoice Actions");
		}

		if (cmnLib.waitForTextToBePresentInElement(createInvoice.InvoiceValidationStatus, status)) {
			rpt.generateReport("", "Verify Invoice validation status", "", status,
					"Invoice validation must status change as " + status,
					"Invoice validation status changed as expected", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Invoice validation status", "", status,
					"Invoice validation must change as expected", "Invoice validation status not changed as " + status,
					"Failed", "", true);
			Assert.fail("Invoice validation status not changed as expected");
		}

	}

	@Then("^Verify Accounting complete message and Click OK$")
	public void verify_Accounting_is_complete_message_and_Click_OK() throws Throwable {
		if (cmnLib.waitForElementToBeVisible(createInvoice.msgConfirmationAccounting)) {
			rpt.generateReport("", "Verify Accounting COmplete message", "", "",
					"Accounting Complete confirmation message must be displayed",
					"Accounting Complete confirmation message displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Accounting COmplete message", "", "",
					"Accounting Complete confirmation message must be displayed",
					"Accounting Complete confirmation message not displayed", "Failed", "", true);
			Assert.fail("Accounting Complete confirmation message not displayed");
		}

		if (cmnLib.clickOnWebElement(createInvoice.OK_ConfirmationAccounting)
				&& cmnLib.waitForElementToBeInvisible(createInvoice.OK_ConfirmationAccounting)) {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Accounting Complete confirmation window must be closed",
					"Clicked on OK button and Accounting Complete confirmation window closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Accounting Complete confirmation window must be closed",
					"Either not clicked on OK button or Accounting Complete confirmation window not closed", "Failed",
					"", true);
			Assert.fail("Either not clicked on OK button or Accounting Complete confirmation window not closed");
		}
	}

	@Then("^Click Invoice Validation Status link to view Invoice Summary$")
	public void click_Invoice_Validation_Status_link_to_view_Invoice_Summary() throws Throwable {
		if (cmnLib.clickOnWebElement(createInvoice.InvoiceValidationStatus)
				&& cmnLib.waitForElementToBeVisible(createInvoice.InvoiceSummary_Container)) {
			rpt.generateReport("", "Click on Invoice validation status(Validated) link", "", "",
					"Invoice validation status link must be clicked and Invoice Summary popup menu must be opened",
					"Invoice validation status link clicked and Invoice Summary popup menu opened", "Passed", "",
					false);
		} else {
			rpt.generateReport("", "Click on Invoice validation status(Validated) link", "", "",
					"Invoice validation status link must be clicked and Invoice Summary popup menu must be opened",
					"Either Invoice validation status link not clicked or Invoice Summary popup menu not opened",
					"Failed", "", true);
			Assert.fail("Either Invoice validation status link not clicked or Invoice Summary popup menu not opened");
		}
	}

	@Then("^Verify Invoice \"([^\"]*)\" status displayed as \"([^\"]*)\"$")
	public void verify_Invoice_status_displayed_as(String attribute, String status) throws Throwable {
		if (createInvoice.validateStatusInInvoiceSummary(attribute, status)) {
			rpt.generateReport("", "Verify Invoice Status", "", status, attribute + " Status should be " + status,
					attribute + " Status is: " + status, "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Invoice Status", "", status, attribute + " Status should be " + status,
					attribute + " Status is not " + status, "Failed", "", true);
			Assert.fail(attribute + " Status is not " + status);
		}
	}

	@Then("^Close Invoice Summary pop up window$")
	public void close_Invoice_Summary_pop_up_window() throws Throwable {
		if (cmnLib.clickOnWebElement(createInvoice.Close_InvoiceSummary)) {
			rpt.generateReport("", "Click on Close icon in Invoice Summary popup", "", "",
					"Close icon must be clicked and Invoice Summary popup must be closed",
					"Close icon clicked and Invoice Summary popup closed", "Passed", "", false);
		} else {
			rpt.generateReport("", "Click on Close icon in Invoice Summary popup", "", "",
					"Close icon must be clicked and Invoice Summary popup must be closed",
					"Either Close icon not clicked or Invoice Summary popup not closed", "Failed", "", true);
			Assert.fail("Either Close icon not clicked or Invoice Summary popup not closed");
		}
	}

}
