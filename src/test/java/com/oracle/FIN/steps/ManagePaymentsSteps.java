package com.oracle.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.oracle.FIN.pages.ManagePaymentsPage;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import report.oracle.ofs.ReportGeneration;

public class ManagePaymentsSteps {
	
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	ManagePaymentsPage managePayments = new ManagePaymentsPage();
	
	
	@When("^Perform Search for an Payment number with \"([^\"]*)\"$")
	public void perform_Search_for_an_Payment_number_with(String PaymentNumber) throws Throwable {
	    rpt.enterStepHeader("Search an payment number");
		
	    if(cmnLib.waitForElementToBeVisible(managePayments.lnkExpandSearch, 3)) {
	    	cmnLib.clickOnWebElement(managePayments.lnkExpandSearch);
	    }
	    
		if (cmnLib.enterDataInTextBox(managePayments.PaymentNumber, PaymentNumber, true)) {
			rpt.generateReport("", "Enter Payment Number", "", PaymentNumber, "Payment Number must be entered",
					"Payment Number entered", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Payment Number", "", PaymentNumber, "Payment Number must be entered",
					"Payment Number not entered", "Failed", "", true);
			Assert.fail("Payment Number not entered");
		}

		cmnLib.selectDropdownBy(managePayments.drpPaymentStatus, "Negotiable", DropDownSelectBy.VisibleText);
		
		if (cmnLib.clickOnWebElement(managePayments.Search)
				&& cmnLib.waitForElementToBeVisible(managePayments.table_Results)) {
			rpt.generateReport("", "Click on Search button", "", "",
					"Search Button must be clicked and Search results must be displayed",
					"Clicked on Search Button and Search results displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Search button", "", "",
					"Search Button must be clicked and Search results must be displayed",
					"Either not clicked on Search Button or Search results not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Search Button or Search results not displayed");
		}

		if (managePayments.selectPaymnetFromResults(PaymentNumber)) {
			rpt.generateReport("", "Select Payment from results", "", PaymentNumber, "Payment must be selected",
					"Payment selected", "Passed", "", true);
		} else {
			rpt.generateReport("", "Select Payment from results", "", PaymentNumber, "Payment must be selected",
					"Payment not selected", "Failed", "", true);
			Assert.fail("Payment not selected");
		}
	}
	
	@Then("^Enter Stop reason \"([^\"]*)\" and Submit$")
	public void enter_Stop_reason_and_Submit(String StopReason) throws Throwable {
		rpt.enterStepHeader("Stop a Payment Request");
		
		if(cmnLib.waitForElementToBeVisible(managePayments.inpStopReason)) {
			if (cmnLib.enterDataInTextBox(managePayments.inpStopReason, StopReason, true)) {
				rpt.generateReport("", "Enter Stop reason", "", StopReason, "Stop reason must be entered",
						"Stop reason entered", "Passed", "", true);
			} else {
				rpt.generateReport("", "Enter Stop reason", "", StopReason, "Stop reason must be entered",
						"Stop reason not entered", "Failed", "", true);
				Assert.fail("Stop reason not entered");
			}
		}
		
		if (!cmnLib.clickOnWebElement(managePayments.Submit_Void)) {
			rpt.generateReport("", "Click on Submit Button", "", "", "Submit Button must be clicked",
					"Not clicked on Submit Button", "Failed", "", true);
			Assert.fail("Not clicked on Submit Button");
		}
	}
	
	@Then("^Verify Status change \"([^\"]*)\" to \"([^\"]*)\"$")
	public void verify_Status_change_to(String StatusFrom, String StatusTo) throws Throwable {
		TimeUnit.SECONDS.sleep(3);
		if(cmnLib.waitForElementToBeVisible(managePayments.txtPaymentStatus)) {
			String payStatus=managePayments.txtPaymentStatus.getText();
			if(StatusTo.equalsIgnoreCase(payStatus)) {
				rpt.generateReport("", "Verify Payment status", "", "", "Payment status should change from "+StatusFrom+" to "+StatusTo,
						"Payment status has changed from "+StatusFrom+" to "+StatusTo, "Passed", "", true);
			} else {
				rpt.generateReport("", "Verify Payment status", "", "", "Payment status should change from "+StatusFrom+" to "+StatusTo,
						"Payment status has not from "+StatusFrom+" to "+StatusTo, "Failed", "", true);
				Assert.fail("Payment status has not from "+StatusFrom+" to "+StatusTo);
			}
		}
		cmnLib.clickOnWebElement(managePayments.btnDone);
	    
	}
	
	@Then("^Select Invoice Action \"([^\"]*)\" and Submit$")
	public void select_Invoice_Action_and_Submit(String InvoiceActions) throws Throwable {
		rpt.enterStepHeader("Void a Payment Request");
		
		if(cmnLib.waitForElementToBeVisible(managePayments.dropInvoiceActions)) {
			if(cmnLib.selectDropdownBy(managePayments.dropInvoiceActions, InvoiceActions, DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Invoice Actions", "", InvoiceActions, "Invoice Actions should be selected",
						"Invoice Actions is selected", "Passed", "", true);
			} else {
				rpt.generateReport("", "Invoice Actions", "", InvoiceActions, "Invoice Actions should be selected",
						"Invoice Actions is not selected", "Failed", "", true);
				Assert.fail("Invoice Actions is not selected");
			}
		}
		if (!cmnLib.clickOnWebElement(managePayments.Submit_Void)) {
			rpt.generateReport("", "Click on Submit Button", "", "", "Submit Button must be clicked",
					"Not clicked on Submit Button", "Failed", "", true);
			Assert.fail("Not clicked on Submit Button");
		}
	}

	@Then("^Clicks on \"([^\"]*)\" under Actions dropdown$")
	public void clicks_on_under_Actions_dropdown(String optionValue) throws Throwable {
		TimeUnit.SECONDS.sleep(5);
		if(managePayments.selectOptionFromActionsDropdown(managePayments.drpdwnActions, optionValue)) {
			
		}
	}

}
