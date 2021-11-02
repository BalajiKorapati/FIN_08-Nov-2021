package com.oracle.FIN.steps;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.oracle.FIN.pages.BankStatementsAndReconciliationPage;
import com.oracle.FIN.pages.CreateBankStatementsPage;
import com.oracle.FIN.pages.ManageBankStatementPage;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import report.oracle.ofs.ReportGeneration;

public class RecordBankStatementsSteps {
	
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	BankStatementsAndReconciliationPage bnkStmtRec = new BankStatementsAndReconciliationPage();
	CreateBankStatementsPage crtBankStmt = new CreateBankStatementsPage();
	ManageBankStatementPage manageBankStmt = new ManageBankStatementPage();
	String GStatementID,GBankAcc;
	
	@Then("^Navigates to Create Bank Statment page$")
	public void navigates_to_Create_Bank_Statment_page() throws Throwable {
		rpt.enterStepHeader("Navigates to Create Bank Statment page");

		if (!(cmnLib.clickOnWebElement(bnkStmtRec.imgTasks)
				&& cmnLib.waitForElementToBeVisible(bnkStmtRec.lnkCreateBankStatement))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Create Bank Statement link must be displayed",
					"Either not clicked on Tasks icon or Create Bank Statement link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or Create Bank Statement link not displayed");
		}

		if (cmnLib.clickOnWebElement(bnkStmtRec.lnkCreateBankStatement) && cmnLib.verifyHeader("Create Bank Statement")) {
			rpt.generateReport("", "Click Create Bank Statement under Tasks", "", "",
					"Create Bank Statement link must be clicked and Create Bank Statement page must be displayed",
					"Clicked on Create Bank Statement link and Create Bank Statement page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Create Bank Statement under Tasks", "", "",
					"Create Bank Statement link must be clicked and Create Bank Statement page must be displayed",
					"Either not clicked on Create Bank Statement link or Create Bank Statement page not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked on Create Bank Statement link or Create Bank Statement page not displayed");
		}
	
	}

	@Then("^Enter Bank Statement details \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_Bank_Statement_details(String BankAccount, String PeriodStartDate, String PeriodEndDate, String StatementID) throws Throwable {
		rpt.enterStepHeader("Enter Bank Statement details");
		
		GBankAcc=BankAccount;
		cmnLib.waitForElementToBeVisible(crtBankStmt.txtBankAcc, 20);
		/*
		 * if(!cmnLib.enterDataInTextfield(crtBankStmt.txtBankAcc, BankAccount)) {
		 * rpt.generateReport("", "Enter Bank account number", "", BankAccount,
		 * "Bank Account Number must be entered", "Bank account Number not entered",
		 * "Failed", "", true); Assert.fail("Bank account Number not entered"); }
		 * 
		 * if(!cmnLib.enterDataInTextfield(crtBankStmt.txtPeriodStartDate,
		 * PeriodStartDate)) { rpt.generateReport("", "Enter Period Start Date", "",
		 * PeriodStartDate, "Period Start Date must be entered",
		 * "Period Start Date not entered", "Failed", "", true);
		 * Assert.fail("Period Start Date not entered"); }
		 * 
		 * if(!cmnLib.enterDataInTextfield(crtBankStmt.txtPeriodEndDate, PeriodEndDate))
		 * { rpt.generateReport("", "Enter Period End Date", "", PeriodEndDate,
		 * "Period End Date must be entered", "Period End Date not entered", "Failed",
		 * "", true); Assert.fail("Period End Date not entered"); }
		 */
		
		if(StatementID.length()>=0) {
			GStatementID=cmnLib.randomNumber("Stmt_");
		}else {
			GStatementID=StatementID;
		}
		
		/*
		 * if(!cmnLib.enterDataInTextfield(crtBankStmt.txtStatementID, GStatementID)) {
		 * rpt.generateReport("", "Enter Statement ID", "", GStatementID,
		 * "StatementID must be entered", "StatementID not entered", "Failed", "",
		 * true); Assert.fail("StatementID not entered"); }
		 */
	
		cmnLib.enterDataInTextBox(crtBankStmt.txtBankAcc, BankAccount,true);
		cmnLib.enterDataInTextBox(crtBankStmt.txtPeriodStartDate, PeriodStartDate,true);
		cmnLib.enterDataInTextBox(crtBankStmt.txtPeriodEndDate, PeriodEndDate,true);
		cmnLib.enterDataInTextBox(crtBankStmt.txtStatementID, GStatementID,true);
		
		rpt.generateReport("", "Bank Statement details are entered", "", "Bank Account Num :"+BankAccount+"\nPeriodStartDate :"+PeriodStartDate+"\nPeriodEndDate :"+PeriodEndDate+"\nStatementID :"+GStatementID, "Bank Statement details must be entered",
				"Bank Statement details are entered", "Passed", "", true);
	
	}

	@Then("^Enter Statement Lines \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_Statement_Lines(String BookingDate, String TransactionCode, String FlowIndicator, String Amount) throws Throwable {
		rpt.enterStepHeader("Statement Lines are entered");
	
	if(cmnLib.clickOnWebElement(crtBankStmt.lnkStatementLines))
		if(cmnLib.clickOnWebElement(crtBankStmt.imgAddStatementLines))
			if(cmnLib.waitForElementToBeVisible(crtBankStmt.txtBookingDate, 20)) {
				
				cmnLib.enterDataInTextBox(crtBankStmt.txtBookingDate, BookingDate, true);
				cmnLib.enterDataInTextBox(crtBankStmt.txtTransactionCode, TransactionCode, true);
				cmnLib.selectDropdownBy(crtBankStmt.drpFlowIndicator, FlowIndicator, DropDownSelectBy.VisibleText);
				cmnLib.enterDataInTextBox(crtBankStmt.txtAmount, Amount, true);
				
			}
		rpt.generateReport("", "Statement Line details", "", "Booking Date :"+BookingDate+"\nTransactionCode :"+TransactionCode+"\nFlowIndicator :"+FlowIndicator+"\nAmount :"+Amount, "Statement line details must be entered",
			"Statement line details are entered", "Passed", "", true);
		cmnLib.clickOnWebElement(crtBankStmt.btnOK);
	}

	@Then("^Enter Balance details \"([^\"]*)\"$")
	public void enter_Balance_details(String ClosingAmount) throws Throwable {
		rpt.enterStepHeader("Closing Balance amount is entered");
		cmnLib.clickOnWebElement(crtBankStmt.lnkBalances);
		cmnLib.waitForElementToBeVisible(crtBankStmt.txtClosingAmt, 20);
		cmnLib.enterDataInTextBox(crtBankStmt.txtClosingAmt, ClosingAmount, true);
		
		rpt.generateReport("", "Closing balance", "", "Closing Balanace amount :"+ClosingAmount, "Closing Balanace amount should be entered",
				"Closing Balanace amount is entered", "Passed", "", true);
	}

	@Then("^Save Bank Statement page$")
	public void save_Bank_Statement_page() throws Throwable {
		
		cmnLib.clickOnWebElement(crtBankStmt.btnSaveandClose);
		
		if(cmnLib.waitForElementToBeVisible(crtBankStmt.msgWarning, 10)) {
			rpt.generateReport("", "Warning message", "", "", "Warning message should appear",
					"Warning message did appear", "Passed", "", true);
			
			cmnLib.clickOnWebElement(crtBankStmt.btnOK);
		}
		
		if(cmnLib.waitForElementToBeVisible(crtBankStmt.msgConfirmation, 10)) {
			rpt.generateReport("", "Confirmation message", "", "", "Confirmation message should appear",
					"Confirmation message did appear", "Passed", "", true);
			
			cmnLib.clickOnWebElement(crtBankStmt.btnOKConfirmation);
		}
		
		
	}

	@Then("^Verify the Bank Statement creation$")
	public void verify_the_Bank_Statement_creation() throws Throwable {
		
		rpt.enterStepHeader("Verify the Bank Statement creation");
		if (!(cmnLib.waitForElementToBeVisible(bnkStmtRec.imgTasks, 30)
				&& cmnLib.clickOnWebElement(bnkStmtRec.imgTasks))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Task icon should be visible and clicked",
					"Either Tasks icon not visible or Task icon not clicked", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon is not visible or not clicked");
		}
		
		if (!(cmnLib.waitForElementToBeVisible(bnkStmtRec.lnkManageBankStatement, 30)
				&& cmnLib.clickOnWebElement(bnkStmtRec.lnkManageBankStatement))) {
			rpt.generateReport("", "Manage Bank Statement click", "", "",
					"Manage Bank Statement should be visible and clicked",
					"Either Manage Bank Statement link not visible or Manage Bank Statement link not clicked", "Failed", "", true);
			Assert.fail("Either Manage Bank Statement link not visible or Manage Bank Statement link not clicked");
		}
		
		TimeUnit.SECONDS.sleep(8);
		if(cmnLib.waitForElementToBeVisible(manageBankStmt.lnkExpandSearch, 30)) {
			TimeUnit.SECONDS.sleep(4);
			cmnLib.clickOnWebElement(manageBankStmt.lnkExpandSearch);
			TimeUnit.SECONDS.sleep(4);
			cmnLib.enterDataInTextBox(manageBankStmt.txtStatementID, GStatementID,true);
			cmnLib.clickOnWebElement(manageBankStmt.btnSearch);
			
		}else {
			rpt.generateReport("", "Manage Bank Statement  page", "", "",
					"Manage Bank Statement page should be visible",
					"Manage Bank Statement page is not visible", "Failed", "", true);
			Assert.fail("Manage Bank Statement page is not visible");
		}
		
		if(manageBankStmt.verifyBankStatementSearchedRecordExists(GBankAcc, GStatementID)) {
			rpt.generateReport("", "Searched Record", "", "", "Bank Account and statement ID should appear in searched record",
					"Bank Account and statement ID should appeared in searched record", "Passed", "", true);
		}else {
			rpt.generateReport("", "Searched Record", "", "", "Bank Account and statement ID should appear in searched record",
					"Bank Account and statement ID should did  not appear in searched record", "Failed", "", true);
		}

		if(cmnLib.waitForElementToBeVisible(manageBankStmt.hdrBankStatement, 20)) {
			TimeUnit.SECONDS.sleep(3);
			rpt.generateReport("", "Verify Bank statement creation", "", "", "Bank statement should be successfully created",
					"Bank statement successfully created", "Passed", "", true);
		}else {
			rpt.generateReport("", "Verify Bank statement creation", "", "", "Bank statement should be successfully created",
					"Bank statement is not created successfully", "Failed", "", true);
			Assert.fail("Bank statement is not created successfully");
		}
		
	
	}

}
