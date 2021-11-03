package com.oracle.FIN.common.steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.oracle.FIN.FIN.pages.AccountsReceivablePage;
import com.oracle.FIN.FIN.pages.AssetsPage;
import com.oracle.FIN.FIN.pages.BankStatementsAndReconciliationPage;
import com.oracle.FIN.FIN.pages.BillingPage;
import com.oracle.FIN.FIN.pages.CreatePositivePayFilePage;
import com.oracle.FIN.FIN.pages.CreateReceiptPage;
import com.oracle.FIN.FIN.pages.InvoiceWorkbenchPage;
import com.oracle.FIN.FIN.pages.ManageInvoicesPage;
import com.oracle.FIN.FIN.pages.ManagePaymentsPage;
import com.oracle.FIN.FIN.pages.PaymentsPage;
import com.oracle.FIN.FIN.pages.PurchaseRequisitionsPage;
import com.oracle.FIN.FIN.pages.RequisitionsPage;
import com.oracle.FIN.FIN.pages.ScheduledProcessesPage;
import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.pages.HomePage;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
public class HomePageSteps extends BrowserDriverUtil {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;

	HomePage hmPage = new HomePage();
	InvoiceWorkbenchPage iwbPage = new InvoiceWorkbenchPage();
	ManageInvoicesPage manageInvoices = new ManageInvoicesPage();
	ManagePaymentsPage managePayments = new ManagePaymentsPage();
	AssetsPage assets = new AssetsPage();
	BillingPage billing = new BillingPage();
	CreatePositivePayFilePage cppPage = new CreatePositivePayFilePage();
	AccountsReceivablePage arPage = new AccountsReceivablePage();
	BankStatementsAndReconciliationPage bankStmt = new BankStatementsAndReconciliationPage();
	RequisitionsPage req=new RequisitionsPage();

	@When("^User navigates to Manage Invoices page$")
	//public void user_navigates_to_Manage_Invoices_page(String pageName) throws Throwable {
	public void user_navigates_to_Manage_Invoices_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Manage Invoices Page");
		/*if (cmnLib.clickOnWebElement(hmPage.iconNavigator) && cmnLib.waitForElementToBeVisible(hmPage.navHdrPayables)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Payables navigation header must be displayed",
					"Clicked on Navigator icon and Payables navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Payables navigation header must be displayed",
					"Either not clicked on Navigator icon or Payables navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Payables navigation header not displayed");
		}*/
		Thread.sleep(3000);
		cmnLib.clickOnWebElement(hmPage.rightNavigator);
		Thread.sleep(3000);
		cmnLib.clickOnWebElement(hmPage.rightNavigator);
		
		if (cmnLib.clickOnWebElement(hmPage.navHdrPayables) && cmnLib.waitForElementToBeVisible(hmPage.lnkInvoices)) {
			rpt.generateReport("", "Click Payables navigation header", "", "",
					"Payables navigation header must be clicked and invoices link must be displayed",
					"Clicked on Payables navigation header and invoices link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Payables navigation header", "", "",
					"Payables navigation header must be clicked and invoices link must be displayed",
					"Either not clicked on Payables navigation header or invoices link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Payables navigation header or invoices link not displayed");
		}

		if (cmnLib.clickOnWebElement(hmPage.lnkInvoices) && cmnLib.waitForElementToBeVisible(iwbPage.imgTasks)) {
			rpt.generateReport("", "Click Invoices link", "", "",
					"Invoices link must be clicked and Invoice Workbench page must be displayed",
					"Clicked on Invoices link and Invoice Workbench page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Invoices link", "", "",
					"Invoices link must be clicked and Invoice Workbench page must be displayed",
					"Either not clicked on Invoices link or Invoice Workbench page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Invoices link or Invoice Workbench page not displayed");
		}

		if (!(cmnLib.clickOnWebElement(iwbPage.imgTasks)
				&& cmnLib.waitForElementToBeVisible(iwbPage.lnkManageInvoices))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Mnage Invoices link must be displayed",
					"Either not clicked on Tasks icon or Manage Invoices link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or Manage Invoices link not displayed");
		}

		if (cmnLib.clickOnWebElement(iwbPage.lnkManageInvoices) && cmnLib.verifyHeader("Manage Invoices")) {
			rpt.generateReport("", "Click Manage Invoices under Tasks", "", "",
					"Manage Invoices link must be clicked and Manage Invoices page must be displayed",
					"Clicked on Manage Invoices link and Manage Invoices page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Manage Invoices under Tasks", "", "",
					"Manage Invoices link must be clicked and Manage Invoices page must be displayed",
					"Either not clicked on Manage Invoices link or Manage Invoices page not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Manage Invoices link or Manage Invoices page not displayed");
		}

	}

	@Then("^Navigate to Create Positive Pay File page$")
	public void navigate_to_Create_Positive_Pay_File_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Create Positive Pay File page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator) && cmnLib.waitForElementToBeVisible(hmPage.navHdrPayables)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Payables navigation header must be displayed",
					"Clicked on Navigator icon and Payables navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Payables navigation header must be displayed",
					"Either not clicked on Navigator icon or Payables navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Payables navigation header not displayed");
		}

		if (!cmnLib.waitForElementToBeVisible(hmPage.lnkPayments, 1)) {
			if (cmnLib.clickOnWebElement(hmPage.navHdrPayables)
					&& cmnLib.waitForElementToBeVisible(hmPage.lnkPayments)) {
				rpt.generateReport("", "Click Payables navigation header", "", "",
						"Payables navigation header must be clicked and Payments link must be displayed",
						"Clicked on Payables navigation header and Payments link displayed", "Passed", "", true);
			} else {
				rpt.generateReport("", "Click Payables navigation header", "", "",
						"Payables navigation header must be clicked and Payments link must be displayed",
						"Either not clicked on Payables navigation header or Payments link not displayed", "Failed", "",
						true);
				Assert.fail("Either not clicked on Payables navigation header or Payments link not displayed");
			}
		}

		PaymentsPage paymentsPage = new PaymentsPage();
		if (cmnLib.clickOnWebElement(hmPage.lnkPayments) && cmnLib.waitForElementToBeVisible(paymentsPage.imgTasks)) {
			rpt.generateReport("", "Click Payments link", "", "",
					"Payments link must be clicked and Payments page must be displayed",
					"Clicked on Payments link and Payments page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Payments link", "", "",
					"Payments link must be clicked and Payments page must be displayed",
					"Either not clicked on Payments link or Payments page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Payments link or Payments page not displayed");
		}

		if (!(cmnLib.clickOnWebElement(paymentsPage.imgTasks)
				&& cmnLib.waitForElementToBeVisible(paymentsPage.lnkCreatePositivePayFile))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Create Positive Pay File link must be displayed",
					"Either not clicked on Tasks icon or Create Positive Pay File link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Tasks icon or Create Positive Pay File link not displayed");
		}

		if (cmnLib.clickOnWebElement(paymentsPage.lnkCreatePositivePayFile)
				&& cmnLib.waitForElementToBeVisible(cppPage.lblCreatePositivePayFile)) {
			rpt.generateReport("", "Click Create Positive Pay File under Tasks", "", "",
					"Create Positive Pay File link must be clicked and Create Positive Pay File page must be displayed",
					"Clicked on Create Positive Pay File link and Create Positive Pay File page displayed", "Passed",
					"", true);
		} else {
			rpt.generateReport("", "Click Create Positive Pay File under Tasks", "", "",
					"Create Positive Pay File link must be clicked and Create Positive Pay File page must be displayed",
					"Either not clicked on Create Positive Pay File link or Create Positive Pay File page not displayed",
					"Failed", "", true);
			Assert.fail(
					"Either not clicked on Create Positive Pay File link or Create Positive Pay File page not displayed");
		}
	}

	@Then("^Navigate to Scheduled Processes$")
	public void navigate_to_Scheduled_Processes() throws Throwable {
		rpt.enterStepHeader("Navigate to Scheduled Processes page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator) && cmnLib.waitForElementToBeVisible(hmPage.navHdrTools)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Tools navigation header must be displayed",
					"Clicked on Navigator icon and Tools navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Tools navigation header must be displayed",
					"Either not clicked on Navigator icon or Tools navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Tools navigation header not displayed");
		}

		if (cmnLib.clickOnWebElement(hmPage.navHdrTools)
				&& cmnLib.waitForElementToBeVisible(hmPage.lnkScheduledProcesses)) {
			rpt.generateReport("", "Click Tools navigation header", "", "",
					"Tools navigation header must be clicked and Scheduled Processes link must be displayed",
					"Clicked on Tools navigation header and Scheduled Processes link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Tools navigation header", "", "",
					"Tools navigation header must be clicked and Scheduled Processes link must be displayed",
					"Either not clicked on Tools navigation header or Scheduled Processes link not displayed", "Failed",
					"", true);
			Assert.fail("Either not clicked on Tools navigation header or Scheduled Processes link not displayed");
		}

		ScheduledProcessesPage scheduledProcesses = new ScheduledProcessesPage();
		if (cmnLib.clickOnWebElement(hmPage.lnkScheduledProcesses)
				&& cmnLib.waitForElementToBeVisible(scheduledProcesses.btnScheduleNewProcess)) {
			rpt.generateReport("", "Click Scheduled Processes link", "", "",
					"Scheduled Processes link must be clicked and Scheduled Processes page must be displayed",
					"Clicked on Scheduled Processes link and Scheduled Processes page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Scheduled Processes link", "", "",
					"Scheduled Processes link must be clicked and Scheduled Processes page must be displayed",
					"Either not clicked on Scheduled Processes link or Scheduled Processes page not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked on Scheduled Processes link or Scheduled Processes page not displayed");
		}
	}

	@When("^User navigates to Create Invoice page$")
	public void user_navigates_to_Create_Invoice_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Create Invoice Page");
		Thread.sleep(3000);
		cmnLib.clickOnWebElement(hmPage.rightNavigator);
		Thread.sleep(3000);
		cmnLib.clickOnWebElement(hmPage.rightNavigator);
		if (cmnLib.clickOnWebElement(hmPage.navHdrPayables) && cmnLib.waitForElementToBeVisible(hmPage.lnkInvoices)) {
			rpt.generateReport("", "Click Payables navigation header", "", "",
					"Payables navigation header must be clicked and invoices link must be displayed",
					"Clicked on Payables navigation header and invoices link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Payables navigation header", "", "",
					"Payables navigation header must be clicked and invoices link must be displayed",
					"Either not clicked on Payables navigation header or invoices link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Payables navigation header or invoices link not displayed");
		}

		if (cmnLib.clickOnWebElement(hmPage.lnkInvoices) && cmnLib.waitForElementToBeVisible(iwbPage.imgTasks)) {
			rpt.generateReport("", "Click Invoices link", "", "",
					"Invoices link must be clicked and Invoice Workbench page must be displayed",
					"Clicked on Invoices link and Invoice Workbench page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Invoices link", "", "",
					"Invoices link must be clicked and Invoice Workbench page must be displayed",
					"Either not clicked on Invoices link or Invoice Workbench page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Invoices link or Invoice Workbench page not displayed");
		}

		if (!(cmnLib.clickOnWebElement(iwbPage.imgTasks)
				&& cmnLib.waitForElementToBeVisible(iwbPage.lnkCreateInvoice))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Create Invoice link must be displayed",
					"Either not clicked on Tasks icon or Create Invoice link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or Create Invoice link not displayed");
		}

		if (cmnLib.clickOnWebElement(iwbPage.lnkCreateInvoice) && cmnLib.verifyHeader("Create Invoice")) {
			rpt.generateReport("", "Click Create Invoice under Tasks", "", "",
					"Create Invoice link must be clicked and Create Invoice page must be displayed",
					"Clicked on Create Invoice link and Create Invoice page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Create Invoice under Tasks", "", "",
					"Create Invoice link must be clicked and Create Invoice page must be displayed",
					"Either not clicked on Create Invoice link or Create Invoice page not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Create Invoice link or Create Invoice page not displayed");
		}
	}

	@When("^User navigates to Manage Payments page$")
	public void user_navigates_to_Manage_Payments_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Manage Payments Page");

		if (cmnLib.clickOnWebElement(hmPage.iconNavigator) && cmnLib.waitForElementToBeVisible(hmPage.navHdrPayables)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Payables navigation header must be displayed",
					"Clicked on Navigator icon and Payables navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Payables navigation header must be displayed",
					"Either not clicked on Navigator icon or Payables navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Payables navigation header not displayed");
		}

		if (cmnLib.clickOnWebElement(hmPage.navHdrPayables) && cmnLib.waitForElementToBeVisible(hmPage.lnkPayments)) {
			rpt.generateReport("", "Click Payables navigation header", "", "",
					"Payables navigation header must be clicked and Payments link must be displayed",
					"Clicked on Payables navigation header and Payments link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Payables navigation header", "", "",
					"Payables navigation header must be clicked and invoices link must be displayed",
					"Either not clicked on Payables navigation header or Payments link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Payables navigation header or Payments link not displayed");
		}

		if (cmnLib.clickOnWebElement(hmPage.lnkPayments)) {
			if (cmnLib.waitForElementToBeVisible(hmPage.imgTasks, 50)) {
				rpt.generateReport("", "Click Payments link", "", "",
						"Payments link must be clicked and Payments Process Requests page must be displayed",
						"Clicked on Payments link and Payments Process Requests page displayed", "Passed", "", true);
			}
		} else {
			rpt.generateReport("", "Click Payments link", "", "",
					"Payments link must be clicked and Payments Process Requests page must be displayed",
					"Either not clicked on Payments link or Payments Process Requests page not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Payments link or Payments Process Requests page not displayed");
		}

		if (!(cmnLib.clickOnWebElement(hmPage.imgTasks)
				&& cmnLib.waitForElementToBeVisible(managePayments.lnkManagePayments))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Manage Payments link must be displayed",
					"Either not clicked on Tasks icon or Manage Payments link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or Manage Payments link not displayed");
		}

		if (cmnLib.clickOnWebElement(managePayments.lnkManagePayments) && cmnLib.verifyHeader("Manage Payments")) {
			rpt.generateReport("", "Click Manage Payments under Tasks", "", "",
					"Manage Payments link must be clicked and Manage Payments page must be displayed",
					"Clicked on Manage Payments link and Manage Payments page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Manage Payments under Tasks", "", "",
					"Manage Payments link must be clicked and Manage Payments page must be displayed",
					"Either not clicked on Manage Payments link or Manage Payments page not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Manage Payments link or Manage Payments page not displayed");
		}

	}

	@When("^User navigates to Add Asset page$")
	public void user_navigates_to_Add_Asset_page() throws Throwable {

		rpt.enterStepHeader("Navigate to Add Asset Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator) && cmnLib.waitForElementToBeVisible(hmPage.navHdrPayables)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Payables navigation header must be displayed",
					"Clicked on Navigator icon and Payables navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Payables navigation header must be displayed",
					"Either not clicked on Navigator icon or Payables navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Payables navigation header not displayed");
		}

		if (cmnLib.clickByJSE(hmPage.navHdrFixedAssets) && cmnLib.waitForElementToBeVisible(hmPage.lnkAssets)) {
			rpt.generateReport("", "Click Fixed Assests navigation header", "", "",
					"Fixed Assests navigation header must be clicked and Assets link must be displayed",
					"Clicked on Fixed Assests navigation header and Assets link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Fixed Assests navigation header", "", "",
					"Fixed Assests navigation header must be clicked and Assets link must be displayed",
					"Either not clicked on Fixed Assests navigation header or Assets link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Fixed Assests navigation header or Assets link not displayed");
		}

		if (cmnLib.clickByJSE(hmPage.lnkAssets) && cmnLib.waitForElementToBeVisible(assets.imgTaskPane)) {
			if (!(cmnLib.clickOnWebElement(assets.imgTaskPane)
					&& cmnLib.waitForElementToBeVisible(assets.lnkAddAsset))) {
				rpt.generateReport("", "Click Tasks icon", "", "",
						"Tasks icon must be clicked and Add Assets link must be displayed",
						"Either not clicked on Tasks icon or Assets link not displayed", "Failed", "", true);
				Assert.fail("Either not clicked on Tasks icon or Assets link not displayed");
			}

			if (cmnLib.clickOnWebElement(assets.lnkAddAsset) && cmnLib.waitForElementToBeVisible(assets.txtBook)) {
				rpt.generateReport("", "Click Add Assets under Tasks", "", "",
						"Add Assets link must be clicked and Add Assets page must be displayed",
						"Clicked on Add Assets link and Add Assets page displayed", "Passed", "", true);
			} else {
				rpt.generateReport("", "Click Add Assets under Tasks", "", "",
						"Add Assets link must be clicked and Add Assets page must be displayed",
						"Either not clicked on Add Assets link or Add Assets page not displayed", "Failed", "", true);
				Assert.fail("Either not clicked on Add Assets link or Add Assets page not displayed");
			}
		}

	}

	@When("^User navigates to Adjust Assets page$")
	public void user_navigates_to_adjust_assets_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Adjust Assets Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrFixedAssets)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Fixed Assets navigation header must be displayed",
					"Clicked on Navigator icon and Fixed Assets navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Fixed Assets navigation header must be displayed",
					"Either not clicked on Navigator icon or Fixed Assets navigation header not displayed", "Failed",
					"", true);
			Assert.fail("Either not clicked on Navigator icon or Fixed Assets navigation header not displayed");
		}

		if (cmnLib.clickOnWebElement(hmPage.navHdrFixedAssets) && cmnLib.waitForElementToBeVisible(hmPage.lnkAssets)) {
			rpt.generateReport("", "Click Fixed Assets navigation header", "", "",
					"Fixed Assets navigation header must be clicked and Assets link must be displayed",
					"Clicked on Fixed Assets navigation header and Assets link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Fixed Assets navigation header", "", "",
					"Fixed Assets navigation header must be clicked and Assets link must be displayed",
					"Either not clicked on Fixed Assets navigation header or Assets link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Fixed Assets navigation header or Assets link not displayed");
		}

		AssetsPage assets = new AssetsPage();
		if (cmnLib.clickOnWebElement(hmPage.lnkAssets) && cmnLib.waitForElementToBeVisible(assets.imgTasks)) {

			if (!(cmnLib.clickOnWebElement(assets.imgTasks)
					&& cmnLib.waitForElementToBeVisible(assets.lnkAdjustAssets))) {
				rpt.generateReport("", "Click Tasks icon", "", "",
						"Tasks icon must be clicked and Adjust Assets link must be displayed",
						"Either not clicked on Tasks icon or Adjust Assets link not displayed", "Failed", "", true);
				Assert.fail("Either not clicked on Tasks icon or Adjust Assets link not displayed");
			}

			if (cmnLib.clickOnWebElement(assets.lnkAdjustAssets) && cmnLib.verifyHeader("Adjust Assets")) {
				rpt.generateReport("", "Click Adjust Assets under Tasks", "", "",
						"Adjust Assets link must be clicked and Adjust Assets page must be displayed",
						"Clicked on Adjust Assets link and Adjust Assets page displayed", "Passed", "", true);
			} else {
				rpt.generateReport("", "Click Adjust Assets under Tasks", "", "",
						"Adjust Assets link must be clicked and Adjust Assets page must be displayed",
						"Either not clicked on Adjust Assets link or Adjust Assets page not displayed", "Failed", "",
						true);
				Assert.fail("Either not clicked on Adjust Assets link or Adjust Assets page not displayed");
			}
		}
	}

	@When("^User navigates to Create Transaction page$")
	public void user_navigates_to_Create_Transaction_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Create Transaction Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrReceivables)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Receivables navigation header must be displayed",
					"Clicked on Navigator icon and Receivables navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Receivables navigation header must be displayed",
					"Either not clicked on Navigator icon or Receivables navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Receivables navigation header not displayed");
		}

		if (cmnLib.clickOnWebElement(hmPage.navHdrReceivables) && cmnLib.waitForElementToBeVisible(hmPage.lnkBilling)) {
			rpt.generateReport("", "Click Receivables navigation header", "", "",
					"Receivables navigation header must be clicked and Billing link must be displayed",
					"Clicked on Receivables navigation header and Billing link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Receivables navigation header", "", "",
					"Receivables navigation header must be clicked and Billing link must be displayed",
					"Either not clicked on Receivables navigation header or Billing link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Receivables navigation header or Billing link not displayed");
		}

		BillingPage billing = new BillingPage();
		if (cmnLib.clickOnWebElement(hmPage.lnkBilling) && cmnLib.waitForElementToBeVisible(billing.imgTasks)) {
			rpt.generateReport("", "Click Billing link", "", "",
					"Billing link must be clicked and Billing page must be displayed",
					"Clicked on Billing link and Billing page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Billing link", "", "",
					"Billing link must be clicked and Billing page must be displayed",
					"Either not clicked on Billing link or Billing page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Billing link or Billing page not displayed");
		}

		if (!(cmnLib.clickOnWebElement(billing.imgTasks)
				&& cmnLib.waitForElementToBeVisible(billing.lnkCreateTransaction))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Create Transaction link must be displayed",
					"Either not clicked on Tasks icon or Create Transaction link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or Create Transaction link not displayed");
		}

		if (cmnLib.clickOnWebElement(billing.lnkCreateTransaction) && cmnLib.verifyHeader("Create Transaction")) {
			rpt.generateReport("", "Click Create Transaction under Tasks", "", "",
					"Create Transaction link must be clicked and Create Transaction page must be displayed",
					"Clicked on Create Transaction link and Create Transaction page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Create Transaction under Tasks", "", "",
					"Create Transaction link must be clicked and Create Transaction page must be displayed",
					"Either not clicked on Create Transaction link or Create Transaction page not displayed", "Failed",
					"", true);
			Assert.fail("Either not clicked on Create Transaction link or Create Transaction page not displayed");
		}
	}

	@When("^User navigates to Transfer Assets page$")
	public void user_navigates_to_Tansfer_Assets_page() throws Throwable {

		rpt.enterStepHeader("Navigate to Transfer Assets Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrFixedAssets)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Fixed Assets navigation header must be displayed",
					"Clicked on Navigator icon and Fixed Assets navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Fixed Assets navigation header must be displayed",
					"Either not clicked on Navigator icon or Fixed Assets navigation header not displayed", "Failed",
					"", true);
			Assert.fail("Either not clicked on Navigator icon or Fixed Assets navigation header not displayed");
		}

		if (cmnLib.clickOnWebElement(hmPage.navHdrFixedAssets) && cmnLib.waitForElementToBeVisible(hmPage.lnkAssets)) {
			rpt.generateReport("", "Click Fixed Assets navigation header", "", "",
					"Fixed Assets navigation header must be clicked and Assets link must be displayed",
					"Clicked on Fixed Assets navigation header and Assets link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Fixed Assets navigation header", "", "",
					"Fixed Assets navigation header must be clicked and Assets link must be displayed",
					"Either not clicked on Fixed Assets navigation header or Assets link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Fixed Assets navigation header or Assets link not displayed");
		}

		AssetsPage assets = new AssetsPage();
		if (cmnLib.clickOnWebElement(hmPage.lnkAssets) && cmnLib.waitForElementToBeVisible(assets.imgTasks)) {
			rpt.generateReport("", "Click Assets link", "", "",
					"Assets link must be clicked and Assets page must be displayed",
					"Clicked on Assets link and Assets page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Assets link", "", "",
					"Assets link must be clicked and Assets page must be displayed",
					"Either not clicked on Assets link or Assets page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Assets link or Assets page not displayed");
		}

		if (!(cmnLib.clickOnWebElement(assets.imgTasks)
				&& cmnLib.waitForElementToBeVisible(assets.lnkTransferAssets))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Transfer Assets link must be displayed",
					"Either not clicked on Tasks icon or Transfer Assets link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or Transfer Assets link not displayed");
		}

		if (cmnLib.clickOnWebElement(assets.lnkTransferAssets) && cmnLib.verifyHeader("Transfer Assets")) {
			rpt.generateReport("", "Click Transfer Assets under Tasks", "", "",
					"Transfer Assets link must be clicked and Transfer Assets page must be displayed",
					"Clicked on Transfer Assets link and Transfer Assets page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Transfer Assets under Tasks", "", "",
					"Transfer Assets link must be clicked and Transfer Assets page must be displayed",
					"Either not clicked on Transfer Assets link or Transfer Assets page not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Transfer Assets link or Transfer Assets page not displayed");
		}
	}

	@When("^User navigates to Assets page$")
	public void user_navigates_to_Assets_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Assets Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrFixedAssets)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Fixed Assets navigation header must be displayed",
					"Clicked on Navigator icon and Fixed Assets navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Fixed Assets navigation header must be displayed",
					"Either not clicked on Navigator icon or Fixed Assets navigation header not displayed", "Failed",
					"", true);
			Assert.fail("Either not clicked on Navigator icon or Fixed Assets navigation header not displayed");
		}

		if (cmnLib.clickOnWebElement(hmPage.navHdrFixedAssets) && cmnLib.waitForElementToBeVisible(hmPage.lnkAssets)) {
			rpt.generateReport("", "Click Fixed Assets navigation header", "", "",
					"Fixed Assets navigation header must be clicked and Assets link must be displayed",
					"Clicked on Fixed Assets navigation header and Assets link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Fixed Assets navigation header", "", "",
					"Fixed Assets navigation header must be clicked and Assets link must be displayed",
					"Either not clicked on Fixed Assets navigation header or Assets link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Fixed Assets navigation header or Assets link not displayed");
		}

		AssetsPage assets = new AssetsPage();
		if (cmnLib.clickOnWebElement(hmPage.lnkAssets) && cmnLib.waitForElementToBeVisible(assets.imgTasks)) {
			rpt.generateReport("", "Click Assets link", "", "",
					"Assets link must be clicked and Assets page must be displayed",
					"Clicked on Assets link and Assets page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Assets link", "", "",
					"Assets link must be clicked and Assets page must be displayed",
					"Either not clicked on Assets link or Assets page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Assets link or Assets page not displayed");
		}
	}

	@When("^User navigates to Retire Assets page$")
	public void user_navigates_to_Retire_Assets_page() throws Throwable {

		rpt.enterStepHeader("Navigate to Retire Asset Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator) && cmnLib.waitForElementToBeVisible(hmPage.navHdrPayables)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Payables navigation header must be displayed",
					"Clicked on Navigator icon and Payables navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Payables navigation header must be displayed",
					"Either not clicked on Navigator icon or Payables navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Payables navigation header not displayed");
		}

		if (cmnLib.clickByJSE(hmPage.navHdrFixedAssets) && cmnLib.waitForElementToBeVisible(hmPage.lnkAssets)) {
			rpt.generateReport("", "Click Fixed Assests navigation header", "", "",
					"Fixed Assests navigation header must be clicked and Assets link must be displayed",
					"Clicked on Fixed Assests navigation header and Assets link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Fixed Assests navigation header", "", "",
					"Fixed Assests navigation header must be clicked and Assets link must be displayed",
					"Either not clicked on Fixed Assests navigation header or Assets link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Fixed Assests navigation header or Assets link not displayed");
		}

		if (cmnLib.clickByJSE(hmPage.lnkAssets) && cmnLib.waitForElementToBeVisible(assets.imgTaskPane)) {
			if (!(cmnLib.clickOnWebElement(assets.imgTaskPane)
					&& cmnLib.waitForElementToBeVisible(assets.lnkAddAsset))) {
				rpt.generateReport("", "Click Tasks icon", "", "",
						"Tasks icon must be clicked and Add Assets link must be displayed",
						"Either not clicked on Tasks icon or Assets link not displayed", "Failed", "", true);
				Assert.fail("Either not clicked on Tasks icon or Assets link not displayed");
			}

			if (cmnLib.clickOnWebElement(assets.lnkRetireAssets)
					&& cmnLib.waitForElementToBeVisible(assets.txtAssetNum)) {
				rpt.generateReport("", "Click Retire Assets under Tasks", "", "",
						"Retire Assets link must be clicked and Retire Assets page must be displayed",
						"Clicked on Retire Assets link and Retire Assets page displayed", "Passed", "", true);
			} else {
				rpt.generateReport("", "Click Retire Assets under Tasks", "", "",
						"Retire Assets link must be clicked and Retire Assets page must be displayed",
						"Either not clicked on Retire Assets link or Retire Assets page not displayed", "Failed", "",
						true);
				Assert.fail("Either not clicked on Retire Assets link or Retire Assets page not displayed");
			}
		}
	}

	@When("^User Navigates to Reinstate Assets page$")
	public void user_Navigates_to_Reinstate_Asset_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Reinstate Asset Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrFixedAssets)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and FixedAssets navigation header must be displayed",
					"Clicked on Navigator icon and FixedAssets navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and FixedAssets navigation header must be displayed",
					"Either not clicked on Navigator icon or FixedAssets navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or FixedAssets navigation header not displayed");
		}

		if (cmnLib.clickOnWebElement(hmPage.navHdrFixedAssets) && cmnLib.waitForElementToBeVisible(hmPage.lnkAssets)) {
			rpt.generateReport("", "Click FixedAssets navigation header", "", "",
					"FixedAssets navigatio n header must be clicked and Asset link must be displayed",
					"Clicked on FixedAssets navigation header and Asset link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click FixedAssets navigation header", "", "",
					"FixedAssets navigation header must be clicked and Asset link must be displayed",
					"Either not clicked on FixedAssets navigation header or Asset link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on FixedAssets navigation header or Asset link not displayed");
		}

	}

	@Then("^User navigates to Asset Inquiry page$")
	public void user_navigates_to_Assets_inquiry_page() throws Throwable {
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrFixedAssets)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and FixedAssets navigation header must be displayed",
					"Clicked on Navigator icon and FixedAssets navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and FixedAssets navigation header must be displayed",
					"Either not clicked on Navigator icon or FixedAssets navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or FixedAssets navigation header not displayed");
		}
		Thread.sleep(2000);
		if (cmnLib.clickOnWebElement(hmPage.navHdrFixedAssets)
				&& cmnLib.waitForElementToBeVisible(hmPage.lnkAssetInquiry)) {
			rpt.generateReport("", "Click FixedAssets navigation header", "", "",
					"FixedAssets navigatio n header must be clicked and Asset link must be displayed",
					"Clicked on FixedAssets navigation header and Asset link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click FixedAssets navigation header", "", "",
					"FixedAssets navigation header must be clicked and Asset link must be displayed",
					"Either not clicked on FixedAssets navigation header or Asset link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on FixedAssets navigation header or Asset link not displayed");
		}
		if (cmnLib.clickOnWebElement(hmPage.lnkAssetInquiry)) {
			rpt.generateReport("", "Click FixedAssets navigation header", "", "",
					"FixedAssets navigatio n header must be clicked and Asset link must be displayed",
					"Clicked on FixedAssets navigation header and Asset link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click FixedAssets navigation header", "", "",
					"FixedAssets navigation header must be clicked and Asset link must be displayed",
					"Either not clicked on FixedAssets navigation header or Asset link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on FixedAssets navigation header or Asset link not displayed");
		}
	}

	@When("^User Navigates to Billing page$")
	public void user_Navigates_to_Billing_page() throws Throwable {
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrReceivables)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Receivables navigation header must be displayed",
					"Clicked on Navigator icon and Receivables navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Receivables navigation header must be displayed",
					"Either not clicked on Navigator icon or Receivables navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Receivables navigation header not displayed");
		}
		if (cmnLib.clickOnWebElement(hmPage.navHdrReceivables) && cmnLib.waitForElementToBeVisible(hmPage.lnkBilling)) {
			rpt.generateReport("", "Click Receivables navigation header", "", "",
					"Receivables navigatio n header must be clicked and Billing link must be displayed",
					"Clicked on Receivables navigation header and Billing link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Receivables navigation header", "", "",
					"Receivables navigation header must be clicked and Billing link must be displayed",
					"Either not clicked on Receivables navigation header or Billing link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Receivables navigation header or Billing link not displayed");
		}
	}

	@When("^User navigates to Credit Transaction page$")
	public void user_navigates_to_Credit_Transaction_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Credit Transaction Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrReceivables)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Receivables navigation header must be displayed",
					"Clicked on Navigator icon and Receivables navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Receivables navigation header must be displayed",
					"Either not clicked on Navigator icon or Receivables navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Receivables navigation header not displayed");
		}

		if (cmnLib.clickOnWebElement(hmPage.navHdrReceivables) && cmnLib.waitForElementToBeVisible(hmPage.lnkBilling)) {
			rpt.generateReport("", "Click Receivables navigation header", "", "",
					"Receivables navigation header must be clicked and Billing link must be displayed",
					"Clicked on Receivables navigation header and Billing link displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Receivables navigation header", "", "",
					"Receivables navigation header must be clicked and Billing link must be displayed",
					"Either not clicked on Receivables navigation header or Billing link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Receivables navigation header or Billing link not displayed");
		}

		BillingPage billing = new BillingPage();
		if (cmnLib.clickOnWebElement(hmPage.lnkBilling) && cmnLib.waitForElementToBeVisible(billing.imgTasks)) {
			rpt.generateReport("", "Click Billing link", "", "",
					"Billing link must be clicked and Billing page must be displayed",
					"Clicked on Billing link and Billing page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Billing link", "", "",
					"Billing link must be clicked and Billing page must be displayed",
					"Either not clicked on Billing link or Billing page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Billing link or Billing page not displayed");
		}

		if (!(cmnLib.clickOnWebElement(billing.imgTasks)
				&& cmnLib.waitForElementToBeVisible(billing.lnkCreditTransaction))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Credit Transaction link must be displayed",
					"Either not clicked on Tasks icon or Credit Transaction link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or Credit Transaction link not displayed");
		}

		if (cmnLib.clickOnWebElement(billing.lnkCreditTransaction) && cmnLib.verifyHeader("Credit Transaction")) {
			rpt.generateReport("", "Click Credit Transaction under Tasks", "", "",
					"Credit Transaction link must be clicked and Credit Transaction page must be displayed",
					"Clicked on Credit Transaction link and Credit Transaction page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Credit Transaction under Tasks", "", "",
					"Credit Transaction link must be clicked and Credit Transaction page must be displayed",
					"Either not clicked on Credit Transaction link or Credit Transaction page not displayed", "Failed",
					"", true);
			Assert.fail("Either not clicked on Credit Transaction link or Credit Transaction page not displayed");
		}
	}

	@Then("^User navigates to Manage Transactions page$")
	public void user_navigates_to_Manage_Transactions_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Manage Transactions Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrReceivables)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Receivables navigation header must be displayed",
					"Clicked on Navigator icon and Receivables navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Receivables navigation header must be displayed",
					"Either not clicked on Navigator icon or Receivables navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Receivables navigation header not displayed");
		}

		if (!cmnLib.waitForElementToBeVisible(hmPage.lnkBilling, 1)) {
			if (cmnLib.clickOnWebElement(hmPage.navHdrReceivables)
					&& cmnLib.waitForElementToBeVisible(hmPage.lnkBilling)) {
				rpt.generateReport("", "Click Receivables navigation header", "", "",
						"Receivables navigation header must be clicked and Billing link must be displayed",
						"Clicked on Receivables navigation header and Billing link displayed", "Passed", "", true);
			} else {
				rpt.generateReport("", "Click Receivables navigation header", "", "",
						"Receivables navigation header must be clicked and Billing link must be displayed",
						"Either not clicked on Receivables navigation header or Billing link not displayed", "Failed",
						"", true);
				Assert.fail("Either not clicked on Receivables navigation header or Billing link not displayed");
			}
		}

		if (cmnLib.clickOnWebElement(hmPage.lnkBilling) && cmnLib.waitForElementToBeVisible(billing.imgTasks)) {
			rpt.generateReport("", "Click Billing link", "", "",
					"Billing link must be clicked and Billing page must be displayed",
					"Clicked on Billing link and Billing page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Billing link", "", "",
					"Billing link must be clicked and Billing page must be displayed",
					"Either not clicked on Billing link or Billing page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Billing link or Billing page not displayed");
		}

		if (!(cmnLib.clickOnWebElement(billing.imgTasks)
				&& cmnLib.waitForElementToBeVisible(billing.lnkManageTransactions))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Manage Transactions link must be displayed",
					"Either not clicked on Tasks icon or Manage Transactions link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or Manage Transactions link not displayed");
		}

		if (cmnLib.clickOnWebElement(billing.lnkManageTransactions) && cmnLib.verifyHeader("Manage Transactions")) {
			rpt.generateReport("", "Click Manage Transactions under Tasks", "", "",
					"Manage Transactions link must be clicked and Manage Transactions page must be displayed",
					"Clicked on Manage Transactions link and Manage Transactions page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Manage Transactions under Tasks", "", "",
					"Manage Transactions link must be clicked and Manage Transactions page must be displayed",
					"Either not clicked on Manage Transactions link or Manage Transactions page not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked on Manage Transactions link or Manage Transactions page not displayed");
		}
	}

	@When("^User navigates to \"([^\"]*)\" > \"([^\"]*)\"$")
	public void user_navigates_to(String groupNode, String itemNode) throws Throwable {
		rpt.enterStepHeader("Navigate to " + itemNode);
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator) && cmnLib
				.waitForElementToBeVisible(cmnLib.getElement(By.cssSelector("div[title='" + groupNode + "']")))) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and " + groupNode + " navigation header must be displayed",
					"Clicked on Navigator icon and " + groupNode + " navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and " + groupNode + " navigation header must be displayed",
					"Either not clicked on Navigator icon or " + groupNode + " navigation header not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked on Navigator icon or " + groupNode + " navigation header not displayed");
		}

		if (!cmnLib.waitForElementToBeVisible(cmnLib.getElement(By.cssSelector("a[title='" + itemNode + "']")), 1)) {
			if (cmnLib.clickOnWebElement(cmnLib.getElement(By.cssSelector("div[title='" + groupNode + "']"))) && cmnLib
					.waitForElementToBeVisible(cmnLib.getElement(By.cssSelector("a[title='" + itemNode + "']")))) {
				rpt.generateReport("", "Click " + groupNode + " navigation header", "", "",
						"" + groupNode + " navigation header must be clicked and " + itemNode
								+ " link must be displayed",
						"Clicked on " + groupNode + " navigation header and " + itemNode + " link displayed", "Passed",
						"", true);
			} else {
				rpt.generateReport("", "Click " + groupNode + " navigation header", "", "",
						"" + groupNode + " navigation header must be clicked and " + itemNode
								+ " link must be displayed",
						"Either not clicked on " + groupNode + " navigation header or " + itemNode
								+ " link not displayed",
						"Failed", "", true);
				Assert.fail("Either not clicked on " + groupNode + " navigation header or " + itemNode
						+ " link not displayed");
			}
		}

		if (cmnLib.clickOnWebElement(cmnLib.getElement(By.cssSelector("a[title='" + itemNode + "']")))
				&& cmnLib.waitForElementToBeVisible(hmPage.imgTasks)) {
			rpt.generateReport("", "Click " + itemNode + " link", "", "",
					"" + itemNode + " link must be clicked and " + itemNode + " page must be displayed",
					"Clicked on " + itemNode + " link and " + itemNode + " page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click " + itemNode + " link", "", "",
					"" + itemNode + " link must be clicked and " + itemNode + " page must be displayed",
					"Either not clicked on " + itemNode + " link or " + itemNode + " page not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on " + itemNode + " link or " + itemNode + " page not displayed");
		}

	}

	@Then("^Click \"([^\"]*)\" under Tasks$")
	public void click_under_Tasks(String link) throws Throwable {
		if (!cmnLib.clickOnWebElement(hmPage.imgTasks)
				&& cmnLib.waitForElementToBeVisible(getDriver().findElement(By.linkText(link)))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and " + link + " link must be displayed",
					"Either not clicked on Tasks icon or " + link + " link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or " + link + " link not displayed");
		}

		if (cmnLib.clickOnWebElement(getDriver().findElement(By.linkText(link))) && cmnLib.verifyHeader(link)) {
			rpt.generateReport("", "Click " + link + " under Tasks", "", "",
					link + " link must be clicked and " + link + " page must be displayed",
					"Clicked on " + link + " link and " + link + " page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click " + link + " under Tasks", "", "",
					link + " link must be clicked and " + link + " page must be displayed",
					"Either not clicked on " + link + " link or " + link + " page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on " + link + " link or " + link + " page not displayed");
		}
	}

	@When("^User navigates to Manage Receipts page$")
	public void user_navigates_to_Manage_Receipts_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Manage Receipts Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrReceivables)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Receivables navigation header must be displayed",
					"Clicked on Navigator icon and Receivables navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Receivables navigation header must be displayed",
					"Either not clicked on Navigator icon or Receivables navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Receivables navigation header not displayed");
		}

		if (!cmnLib.waitForElementToBeVisible(hmPage.lnkAccountsReceivable, 1)) {
			if (cmnLib.clickOnWebElement(hmPage.navHdrReceivables)
					&& cmnLib.waitForElementToBeVisible(hmPage.lnkAccountsReceivable)) {
				rpt.generateReport("", "Click Receivables navigation header", "", "",
						"Receivables navigation header must be clicked and Accounts Receivable link must be displayed",
						"Clicked on Receivables navigation header and Accounts Receivable link displayed", "Passed", "",
						true);
			} else {
				rpt.generateReport("", "Click Receivables navigation header", "", "",
						"Receivables navigation header must be clicked and Accounts Receivable link must be displayed",
						"Either not clicked on Receivables navigation header or Accounts Receivable link not displayed",
						"Failed", "", true);
				Assert.fail(
						"Either not clicked on Receivables navigation header or Accounts Receivable link not displayed");
			}
		}

		if (cmnLib.clickOnWebElement(hmPage.lnkAccountsReceivable)
				&& cmnLib.waitForElementToBeVisible(arPage.imgTasks)) {
			rpt.generateReport("", "Click Accounts Receivable link", "", "",
					"Accounts Receivable link must be clicked and Accounts Receivable page must be displayed",
					"Clicked on Accounts Receivable link and Accounts Receivable page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Accounts Receivable link", "", "",
					"Accounts Receivable link must be clicked and Accounts Receivable page must be displayed",
					"Either not clicked on Accounts Receivable link or Accounts Receivable page not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked on Accounts Receivable link or Accounts Receivable page not displayed");
		}

		if (!(cmnLib.clickOnWebElement(arPage.imgTasks)
				&& cmnLib.waitForElementToBeVisible(arPage.lnkManageReceipts))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Manage Receipts link must be displayed",
					"Either not clicked on Tasks icon or Manage Receipts link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or Manage Receipts link not displayed");
		}

		if (cmnLib.clickOnWebElement(arPage.lnkManageReceipts) && cmnLib.verifyHeader("Manage Receipts")) {
			rpt.generateReport("", "Click Manage Receipts under Tasks", "", "",
					"Manage Receipts link must be clicked and Manage Receipts page must be displayed",
					"Clicked on Manage Receipts link and Manage Receipts page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Manage Receipts under Tasks", "", "",
					"Manage Receipts link must be clicked and Manage Receipts page must be displayed",
					"Either not clicked on Manage Receipts link or Manage Receipts page not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Manage Receipts link or Manage Receipts page not displayed");
		}
	}

	@When("^User navigates to Accounts Receivable page$")
	public void user_navigates_to_Accounts_Receivable_page() throws Throwable {

		rpt.enterStepHeader("Navigate to Accounts Receivable page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrReceivables)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Receivables navigation header must be displayed",
					"Clicked on Navigator icon and Receivables navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Receivables navigation header must be displayed",
					"Either not clicked on Navigator icon or Receivables navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Receivables navigation header not displayed");
		}

		if (cmnLib.clickOnWebElement(hmPage.navHdrReceivables)
				&& cmnLib.waitForElementToBeVisible(hmPage.lnkAccountsReceivable)) {
			rpt.generateReport("", "Click Receivables navigation header", "", "",
					"Receivables navigation header must be clicked and Accounts Receivable link must be displayed",
					"Clicked on Receivables navigation header and Accounts Receivable link displayed", "Passed", "",
					true);
		} else {
			rpt.generateReport("", "Click Fixed Assets navigation header", "", "",
					"Receivables navigation header must be clicked and Accounts Receivable link must be displayed",
					"Either not clicked on Receivables navigation header or Accounts Receivable link not displayed",
					"Failed", "", true);
			Assert.fail(
					"Either not clicked on Receivables navigation header or Accounts Receivable link not displayed");
		}

		CreateReceiptPage receipt = new CreateReceiptPage();

		if (cmnLib.clickOnWebElement(hmPage.lnkAccountsReceivable)
				&& cmnLib.waitForElementToBeVisible(receipt.imgTaskPane)) {
			rpt.generateReport("", "Click Accounts Receivable link", "", "",
					"Accounts Receivable link must be clicked and Accounts Receivable page must be displayed",
					"Clicked on Accounts Receivable link and Accounts Receivable page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Accounts Receivable link", "", "",
					"Accounts Receivable link must be clicked and Accounts Receivable page must be displayed",
					"Either not clicked on Accounts Receivable link or Accounts Receivable page not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked on Accounts Receivable link or Accounts Receivable page not displayed");
		}

	}

	@When("^User navigates to Purchase Requisitions page$")
	public void user_navigates_to_Purchase_Requisitions_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Purchase Requisitions Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.tblNavigatorOptions)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Navigation options must be displayed",
					"Clicked on Navigator icon and Navigation options displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Navigation options must be displayed",
					"Either not clicked on Navigator icon or Navigation options not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Navigator icon or Navigation options not displayed");
		}

		if (!cmnLib.waitForElementToBeVisible(hmPage.lnkPurchaseRequisitions, 1)) {
			if (cmnLib.clickOnWebElement(hmPage.navHdrProcurement)
					&& cmnLib.waitForElementToBeVisible(hmPage.lnkPurchaseRequisitions)) {
				rpt.generateReport("", "Click Procurement navigation header", "", "",
						"Procurement navigation header must be clicked and Purchase Requisitions link must be displayed",
						"Clicked on Procurement navigation header and Purchase Requisitions link displayed", "Passed",
						"", true);
			} else {
				rpt.generateReport("", "Click Procurement navigation header", "", "",
						"Procurement navigation header must be clicked and Purchase Requisitions link must be displayed",
						"Either not clicked on Procurement navigation header or Purchase Requisitions link not displayed",
						"Failed", "", true);
				Assert.fail(
						"Either not clicked on Procurement navigation header or Purchase Requisitions link not displayed");
			}
		}

		PurchaseRequisitionsPage prPage = new PurchaseRequisitionsPage();
		if (cmnLib.clickOnWebElement(hmPage.lnkPurchaseRequisitions)
				&& cmnLib.waitForElementToBeVisible(prPage.lnkMoreTasks)) {
			rpt.generateReport("", "Click Purchase Requisitions link", "", "",
					"Purchase Requisitions link must be clicked and Purchase Requisitions page must be displayed",
					"Clicked on Purchase Requisitions link and Purchase Requisitions page displayed", "Passed", "",
					true);
		} else {
			rpt.generateReport("", "Click Purchase Requisitions link", "", "",
					"Purchase Requisitions link must be clicked and Purchase Requisitions page must be displayed",
					"Either not clicked on Purchase Requisitions link or Purchase Requisitions page not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked on Purchase Requisitions link or Purchase Requisitions page not displayed");
		}
	}

	
	@When("^User navigates to Create Transactions page$")
	public void user_navigates_to_Create_Transactions_page() throws Throwable {
		rpt.enterStepHeader("Navigate to Create Transactions Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrCashManagement)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Cash Management navigation header must be displayed",
					"Clicked on Navigator icon and Cash Management navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Cash Management navigation header must be displayed",
					"Either not clicked on Navigator icon or Cash Management navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Cash Management navigation header not displayed");
		}

		if (cmnLib.waitForElementToBeVisible(hmPage.navHdrCashManagement, 10)) {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollBy(0,1000)");
			if (cmnLib.clickByJSE(hmPage.navHdrCashManagement)) {
					cmnLib.waitForElementToBeVisible(hmPage.lnkBankStatementsReconciliation); 
				rpt.generateReport("", "Click Cash Management navigation header", "", "",
						"Cash Management navigation header must be clicked and Bank Statements and Reconciliation link must be displayed",
						"Clicked on Cash Management navigation header and Bank Statements and Reconciliation link displayed", "Passed", "", true);
			} else {
				rpt.generateReport("", "Click Cash Management navigation header", "", "",
						"Cash Management navigation header must be clicked and Bank Statements and Reconciliation link must be displayed",
						"Either not clicked on Cash Management navigation header or Bank Statements and Reconciliation link not displayed", "Failed",
						"", true);
				Assert.fail("Either not clicked on Receivables navigation header or Bank Statements and Reconciliation link not displayed");
			}
		}

		if (cmnLib.clickOnWebElement(hmPage.lnkBankStatementsReconciliation) && cmnLib.waitForElementToBeVisible(bankStmt.imgTasks)) {
			rpt.generateReport("", "Click Bank Statements and Reconciliation link", "", "",
					"Bank Statements and Reconciliation link must be clicked and Bank Statements and Reconciliation page must be displayed",
					"Clicked on Bank Statements and Reconciliation link and Bank Statements and Reconciliation page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Bank Statements and Reconciliation link", "", "",
					"Bank Statements and Reconciliation link must be clicked and Bank Statements and Reconciliation page must be displayed",
					"Either not clicked on Bank Statements and Reconciliation link or Bank Statements and Reconciliation page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Bank Statements and Reconciliation link or Bank Statements and Reconciliation page not displayed");
		}

		if (!(cmnLib.clickOnWebElement(bankStmt.imgTasks)
				&& cmnLib.waitForElementToBeVisible(bankStmt.lnkCreateTransaction))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Create Transaction link must be displayed",
					"Either not clicked on Tasks icon or Create Transaction link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or Create Transaction link not displayed");
		}

		if (cmnLib.clickOnWebElement(bankStmt.lnkCreateTransaction) && cmnLib.verifyHeader("Create External Transaction")) {
			rpt.generateReport("", "Click Create Transaction under Tasks", "", "",
					"Create Transaction link must be clicked and Create External Transaction page must be displayed",
					"Clicked on Create Transaction link and Create External Transaction page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Create Transaction under Tasks", "", "",
					"Create Transaction link must be clicked and Create External Transaction page must be displayed",
					"Either not clicked on Create Transaction link or Create External Transaction page not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked on Create Transaction link or Create External Transaction page not displayed");
		}
	}
	
	@When("^User navigates to Bank Statements Reconciliation  page$")
	public void user_navigates_to_Bank_Statements_Reconciliation_page() throws Throwable {

		rpt.enterStepHeader("Navigate to Bank Statements Reconciliation Page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrCashManagement)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Cash Management navigation header must be displayed",
					"Clicked on Navigator icon and Cash Management navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Cash Management navigation header must be displayed",
					"Either not clicked on Navigator icon or Cash Management navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Cash Management navigation header not displayed");
		}

		if (cmnLib.waitForElementToBeVisible(hmPage.navHdrCashManagement, 10)) {
			if (cmnLib.clickByJSE(hmPage.navHdrCashManagement)) {
					cmnLib.waitForElementToBeVisible(hmPage.lnkBankStatementsReconciliation); 
				rpt.generateReport("", "Click Cash Management navigation header", "", "",
						"Cash Management navigation header must be clicked and Bank Statements and Reconciliation link must be displayed",
						"Clicked on Cash Management navigation header and Bank Statements and Reconciliation link displayed", "Passed", "", true);
			} else {
				rpt.generateReport("", "Click Cash Management navigation header", "", "",
						"Cash Management navigation header must be clicked and Bank Statements and Reconciliation link must be displayed",
						"Either not clicked on Cash Management navigation header or Bank Statements and Reconciliation link not displayed", "Failed",
						"", true);
				Assert.fail("Either not clicked on Receivables navigation header or Bank Statements and Reconciliation link not displayed");
			}
		}

		if (cmnLib.clickOnWebElement(hmPage.lnkBankStatementsReconciliation) && cmnLib.waitForElementToBeVisible(bankStmt.imgTasks)) {
			rpt.generateReport("", "Click Bank Statements and Reconciliation link", "", "",
					"Bank Statements and Reconciliation link must be clicked and Bank Statements and Reconciliation page must be displayed",
					"Clicked on Bank Statements and Reconciliation link and Bank Statements and Reconciliation page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Bank Statements and Reconciliation link", "", "",
					"Bank Statements and Reconciliation link must be clicked and Bank Statements and Reconciliation page must be displayed",
					"Either not clicked on Bank Statements and Reconciliation link or Bank Statements and Reconciliation page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Bank Statements and Reconciliation link or Bank Statements and Reconciliation page not displayed");
		}
	
	}
	
	@When("^User navigates to Requisitions page$")
	public void user_navigates_to_Requisitions_page() throws Throwable {



		rpt.enterStepHeader("Navigate to Requisitions page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrProcurement)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Procurement navigation header must be displayed",
					"Clicked on Navigator icon and Procurement navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Procurement navigation header must be displayed",
					"Either not clicked on Navigator icon or Procurement navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Procurement navigation header not displayed");
		}

		if (cmnLib.waitForElementToBeVisible(hmPage.navHdrProcurement, 10)) {
			if (cmnLib.clickByJSE(hmPage.navHdrProcurement)) {
					cmnLib.waitForElementToBeVisible(hmPage.lnkPurchaseRequistions); 
				rpt.generateReport("", "Click Procurement navigation header", "", "",
						"Procurement navigation header must be clicked and Purchase Requistions link must be displayed",
						"Clicked on Procurement navigation header and Purchase Requistions link displayed", "Passed", "", true);
			} else {
				rpt.generateReport("", "Click Procurement navigation header", "", "",
						"Procurement navigation header must be clicked and Purchase Requistions link must be displayed",
						"Either not clicked on Procurement navigation header or Purchase Requistions link not displayed", "Failed",
						"", true);
				Assert.fail("Either not clicked on Procurement navigation header or Purchase Requistions link not displayed");
			}
		}

		if (cmnLib.clickOnWebElement(hmPage.lnkPurchaseRequistions) && cmnLib.waitForElementToBeVisible(req.lnkMoreTasks)) {
			rpt.generateReport("", "Click Purchase Requistions", "", "",
					"Purchase Requistions link must be clicked and Purchase Requistions page must be displayed",
					"Clicked on BPurchase Requistions link and Purchase Requistions page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Purchase Requistions link", "", "",
					"Purchase Requistions link must be clicked and Purchase Requistions page must be displayed",
					"Either not clicked on Purchase Requistions link or Purchase Requistions page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Purchase Requistions link or Purchase Requistions page not displayed");
		}
	}
	
	@When("^User navigates to Supplier page$")
	public void user_navigates_to_Supplier_page() throws Throwable {
		rpt.enterStepHeader("^User navigates to Supplier page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.tblNavigatorOptions)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Navigation options must be displayed",
					"Clicked on Navigator icon and Navigation options displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Navigation options must be displayed",
					"Either not clicked on Navigator icon or Navigation options not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Navigator icon or Navigation options not displayed");
		}

		if (!cmnLib.waitForElementToBeVisible(hmPage.lnkPurchaseRequisitions, 1)) {
			if (cmnLib.clickOnWebElement(hmPage.navHdrProcurement)
					&& cmnLib.waitForElementToBeVisible(hmPage.lnkSuppliers)) {
				rpt.generateReport("", "Click Procurement navigation header", "", "",
						"Procurement navigation header must be clicked and Suppliers link must be displayed",
						"Clicked on Procurement navigation header and Suppliers link displayed", "Passed",
						"", true);
			} else {
				rpt.generateReport("", "Click Procurement navigation header", "", "",
						"Procurement navigation header must be clicked and Suppliers link must be displayed",
						"Either not clicked on Procurement navigation header or Suppliers link not displayed",
						"Failed", "", true);
				Assert.fail(
						"Either not clicked on Procurement navigation header or Suppliers link not displayed");
			}
		}

		if (cmnLib.clickOnWebElement(hmPage.lnkSuppliers)
				&& cmnLib.waitForElementToBeVisible(hmPage.imgTasks)) {
			rpt.generateReport("", "Click Suppliers link", "", "",
					"Suppliers link must be clicked and Suppliers page must be displayed",
					"Clicked on Suppliers link and Suppliers page displayed", "Passed", "",
					true);
		} else {
			rpt.generateReport("", "Click Suppliers link", "", "",
					"Suppliers link must be clicked and Suppliers page must be displayed",
					"Either not clicked on Suppliers link or Suppliers page not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked on Suppliers link or Suppliers page not displayed");
		}
		
	}
}
