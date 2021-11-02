package com.oracle.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.oracle.FIN.pages.BankStatementsAndReconciliationPage;
import com.oracle.FIN.pages.CreateExternalTransactionPage;
import com.oracle.FIN.pages.ManageExternalTransactionPage;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.common.pages.HomePage;
import com.oracle.common.steps.CommonAppSteps;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import report.oracle.ofs.ReportGeneration;

public class CreateExternalTransactionSteps {
	
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	CommonAppSteps appSteps = new CommonAppSteps();
	HomePage hmPage = new HomePage();
	Common_Library commonLibrary = new Common_Library();
	CreateExternalTransactionPage crtExtTran= new CreateExternalTransactionPage();
	BankStatementsAndReconciliationPage bnkStmtRec= new BankStatementsAndReconciliationPage();
	ManageExternalTransactionPage manageExtTrans= new ManageExternalTransactionPage();
	
	
	String GBankAccountNum,GAmount,GTranscationDate,GTransactionType,GDescription,GCashAccount,GOffsetAccount,TranscNum;

	@When("^Enter Transcation details \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_Transcation_details(String BankAccountNum, String Amount, String TranscationDate) throws Throwable {
		cmnLib.waitForElementToBeVisible(crtExtTran.txtBankAccount, 20);
		rpt.enterStepHeader("Create Transcation details are entered");
		
		GBankAccountNum=BankAccountNum;
		GAmount=Amount;
		
		
		if(!cmnLib.enterDataInTextBox(crtExtTran.txtBankAccount, BankAccountNum, true)) {
			rpt.generateReport("", "Enter Bank account number", "", BankAccountNum, "Bank Account Number must be entered",
					"Bank account Number not entered", "Failed", "", true);
		}
		
		if(!cmnLib.enterDataInTextBox(crtExtTran.txtAmount, Amount, true)) {
			rpt.generateReport("", "Enter Amount", "", Amount, "Amount must be entered",
					"Amount not entered", "Failed", "", true);
		}
		
		
		if(TranscationDate.length()>0) {
			if(!cmnLib.enterDataInTextBox(crtExtTran.txtTransactionDate, TranscationDate, true)) {
				rpt.generateReport("", "Enter Transcation Date", "", TranscationDate, "Transcation Date must be entered",
						"Transcation Date not entered", "Failed", "", true);
			}
			GTranscationDate=TranscationDate;
		}
	
	}

	@When("^Enter Transcation Type \"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_Transcation_Type(String TransactionType, String Description) throws Throwable {
		
		GTransactionType=TransactionType;
		GDescription=Description;
		
		if(!cmnLib.selectDropdownBy(crtExtTran.drpTransactionType, TransactionType, DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Enter Transcation Type", "", TransactionType, "Transcation Type must be entered",
					"Transcation Type not entered", "Failed", "", true);
		}
		
		if(!cmnLib.enterDataInTextBox(crtExtTran.txtDescription, Description, true)) {
			rpt.generateReport("", "Enter Description", "", Description, "Description must be entered",
					"Description not entered", "Failed", "", true);
		}
	}

	@Then("^Enter Accounting details \"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_Accounting_details(String CashAccount, String OffsetAccount) throws Throwable {
		GCashAccount=CashAccount;
		GOffsetAccount=OffsetAccount;
		
		if(!cmnLib.enterDataInTextBox(crtExtTran.txtCashAccount, CashAccount, true)) {
			rpt.generateReport("", "Enter CashAccount", "", CashAccount, "CashAccount must be entered",
					"CashAccount not entered", "Failed", "", true);
		}
		
		if(!cmnLib.enterDataInTextBox(crtExtTran.txtOffsetAccount, OffsetAccount, true)) {
			rpt.generateReport("", "Enter OffsetAccount", "", OffsetAccount, "OffsetAccount must be entered",
					"OffsetAccount not entered", "Failed", "", true);
		}
	
	}

	@Then("^Save External Transaction page$")
	public void save_External_Transaction_page() throws Throwable {
		rpt.enterStepHeader("Save External bank transaction page");
		if(!cmnLib.clickOnWebElement(crtExtTran.btnSave)) {
			rpt.generateReport("", "Save the page", "", "", "All the details must be saved",
					"Failed to save the page", "Failed", "", true);
		}else {
			if(cmnLib.waitForElementToBeVisible(crtExtTran.msgConfirmation, 20)) {
				rpt.generateReport("", "Confirmation message", "", "", "Confirmation message should appear",
						"Confirmation message is visible", "Passed", "", true);
				cmnLib.clickOnWebElement(crtExtTran.btnConfirmationOK);
				
				TranscNum = crtExtTran.txtTransactionNum.getText();
				System.out.println("Transaction number generated :"+TranscNum);
				
			}else {
				rpt.generateReport("", "Confirmation message", "", "", "Confirmation message should appear",
						"Confirmation message did not appear", "Failed", "", true);
				Assert.fail("Confirmation message did not appear");
			}
			rpt.generateReport("", "Transcation page", "", "Bank Account :"+GBankAccountNum+"\nAmount :"+GAmount+"\nTransaction Date :"+GTranscationDate+"\nTransaction Type :"+GTransactionType+"\nDescription :"+GDescription+"\nCash Account :"+GCashAccount+"\nOffset Account :"+GOffsetAccount, "Confirmation message should appear",
					"Confirmation message is visible", "Passed", "", true);
			
		}
		cmnLib.clickOnWebElement(crtExtTran.btnCancel);
		
	}

	@Then("^Verify the External Transaction creation$")
	public void verify_the_External_Transaction_creation() throws Throwable {
		
		rpt.enterStepHeader("Verify the External Bank Transaction creation");
		if (!(cmnLib.waitForElementToBeVisible(bnkStmtRec.imgTasks, 30)
				&& cmnLib.clickOnWebElement(bnkStmtRec.imgTasks))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Task icon should be visible and clicked",
					"Either Tasks icon not visible or Task icon not clicked", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon is not visible or not clicked");
		}
		
		if (!(cmnLib.waitForElementToBeVisible(bnkStmtRec.lnkManageTransaction, 30)
				&& cmnLib.clickOnWebElement(bnkStmtRec.lnkManageTransaction))) {
			rpt.generateReport("", "Manage Transaction click", "", "",
					"Manage Transaction should be visible and clicked",
					"Either Manage Transaction link not visible or Manage Transaction link not clicked", "Failed", "", true);
			Assert.fail("Either Manage Transaction link not visible or Manage Transaction link not clicked");
		}
		
		if(cmnLib.waitForElementToBeVisible(manageExtTrans.inpTransactionNum, 30)) {
			cmnLib.enterDataInTextBox(manageExtTrans.inpTransactionNum, TranscNum, true);
			cmnLib.clickOnWebElement(manageExtTrans.btnSearch);
		}else {
			rpt.generateReport("", "Manage Transaction page", "", "",
					"Manage Transaction page should be visible",
					"Manage Transaction page is not visible", "Failed", "", true);
			Assert.fail("Manage Transaction page is not visible");
		}
		
		if(manageExtTrans.verifyExternalTranSearchedRecordExists(TranscNum)) {
			rpt.generateReport("", "Searched Record", "", "", "Transaction number should appear in searched record",
					"Transcation number appeared in searched record", "Passed", "", true);
		}else {
			rpt.generateReport("", "Searched Record", "", "", "Transaction number should appear in searched record",
					"Transcation number does not exists in searched results", "Failed", "", true);
			Assert.fail("Transcation number does not exists in searched results");
		}

		if(cmnLib.waitForElementToBeVisible(manageExtTrans.hdrTransactionDetails, 20)) {
			TimeUnit.SECONDS.sleep(3);
			rpt.generateReport("", "Verify Bank transcation creation", "", "", "Bank transaction should be successfully created",
					"Bank transaction successfully created", "Passed", "", true);
		}else {
			rpt.generateReport("", "Verify Bank transcation creation", "", "", "Bank transaction should be successfully created",
					"Bank transaction is not created successfully", "Failed", "", true);
			Assert.fail("Bank transaction is not created successfully");
		}
		
	}
	
	
	
	
	
	

}
