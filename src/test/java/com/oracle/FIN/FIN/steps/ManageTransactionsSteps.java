package com.oracle.FIN.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.oracle.FIN.FIN.pages.ManageTransactionsPage;
import com.oracle.acs.util.BrowserDriverUtil;

import com.oracle.acs.util.PropertyUtils;
import com.oracle.FIN.common.steps.ReportingSteps;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.pages.HomePage;
import com.oracle.FIN.common.steps.CommonAppSteps;
import com.oracle.acs.util.report.ReportGeneration;

import io.cucumber.java.en.Then;
import com.oracle.acs.util.report.ReportGeneration;

public class ManageTransactionsSteps extends BrowserDriverUtil {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	ManageTransactionsPage manageTransactions = new ManageTransactionsPage();
	CommonAppSteps appSteps = new CommonAppSteps();
	HomePage hmPage = new HomePage();
	Common_Library commonLibrary = new Common_Library();

	@Then("^Click on Manage Transactions link$")
	public void click_on_Manage_Transactions_link() throws Throwable {
		if (cmnLib.clickOnWebElement(hmPage.lnkBilling)
				&& cmnLib.waitForElementToBeVisible(manageTransactions.imgTasks)) {
			rpt.generateReport("", "Click Billing link", "", "",
					"Billing link must be clicked and Transactions Billing page must be displayed",
					"Clicked on Billing link and Transactions Billing page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Billing link", "", "",
					"Billing link must be clicked and Transactions Billing page must be displayed",
					"Either not clicked on Billing link or Transactions Billing page not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Billing link or Transactions Billing page not displayed");
		}
		if (!(cmnLib.clickOnWebElement(manageTransactions.imgTasks)
				&& cmnLib.waitForElementToBeVisible(manageTransactions.lnkManageTransactions))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and ManageTransactions link must be displayed",
					"Either not clicked on Tasks icon or ManageTransactions link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or ManageTransactions link not displayed");
		}

		if ((cmnLib.clickOnWebElement(manageTransactions.lnkManageTransactions))) {
			rpt.generateReport("", "Click ManageTransactions link", "", "",
					"ManageTransactions link must be clicked and ManageTransactions Page must be displayed",
					"Either not clicked on ManageTransactions link or ManageTransactions page displayed", "Passed", "",
					true);

		} else {
			rpt.generateReport("", "Click ManageTransactions link", "", "",
					"ManageTransactions link must be clicked and ManageTransactions Page must be displayed",
					"Either not clicked on ManageTransactions link or ManageTransactions page not displayed", "Failed",
					"", true);
			Assert.fail("Either not clicked on ManageTransactions link or ManageTransactions page not displayed");
		}
	}

	@Then("^Perform Search for Transaction with \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void perform_Search_for_Manage_Transaction_with(String Transaction_Source, String Transaction_Number,
			String Transaction_Date, String Bill_to_Customer) throws Throwable {


		if (cmnLib.enterDataInTextBox(manageTransactions.txtTransactionSource, Transaction_Source, true)) {
			rpt.generateReport("", "Enter TransactionSource", "", "TransactionSource:" + Transaction_Source,
					"TransactionSource must be Entered ", "Entered TransactionSource ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter TransactionSource", "", "TransactionSource: " + Transaction_Source,
					"TransactionSource must be Entered ", "Not Entered TransactionSource ", "Failed", "", true);
			Assert.fail("Not Entered TransactionSource");
		}

		if (cmnLib.enterDataInTextBox(manageTransactions.txtTransactionNumber, Transaction_Number, true)) {
			rpt.generateReport("", "Enter TransactionNumber", "", "TransactionNumber: " + Transaction_Number,
					"TransactionNumber must be Entered ", "Entered TransactionNumber ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter TransactionNumber", "", "TransactionNumber: " + Transaction_Number,
					"TransactionNumber must be Entered ", "Not Entered TransactionNumber ", "Failed", "", true);
			Assert.fail("Not Entered TransactionNumber");
		}

		if (cmnLib.enterDataInTextBox(manageTransactions.txtTransactionDate, Transaction_Date, true)) {
			rpt.generateReport("", "Enter TransactionDate", "", "TransactionDate: " + Transaction_Date,
					"TransactionDate must be Entered ", "Entered TransactionDate ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter TransactionDate", "", "TransactionDate: " + Transaction_Date,
					"TransactionDate must be Entered ", "Not Entered TransactionDate ", "Failed", "", true);
			Assert.fail("Not Entered TransactionDate");
		}
		if (cmnLib.enterDataInTextBox(manageTransactions.txtBillToCustomer, Bill_to_Customer, true)) {
			rpt.generateReport("", "Enter BillToCustomer", "", "BilltoCustomer: " + Bill_to_Customer,
					"BillToCustomer must be Entered ", "Entered BillToCustomer ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter BillToCustomer", "", "BilltoCustomer: " + Bill_to_Customer,
					"BillToCustomer must be Entered ", "Not Entered BillToCustomer ", "Failed", "", true);
			Assert.fail("Not Entered BillToCustomer");
		}

		if (Transaction_Source.length() > 0) {
			if (cmnLib.enterDataInTextBox(manageTransactions.txtTransactionSource, Transaction_Source, true)) {
				rpt.generateReport("", "Enter TransactionSource", "", "TransactionSource:" + Transaction_Source,
						"TransactionSource must be Entered ", "Entered TransactionSource ", "Passed", "", true);
			} else {
				rpt.generateReport("", "Enter TransactionSource", "", "TransactionSource: " + Transaction_Source,
						"TransactionSource must be Entered ", "Not Entered TransactionSource ", "Failed", "", true);
				Assert.fail("Not Entered TransactionSource");
			}
		}

		if (Transaction_Number.length() > 0) {
			if (cmnLib.enterDataInTextBox(manageTransactions.txtTransactionNumber, Transaction_Number, true)) {
				rpt.generateReport("", "Enter TransactionNumber", "", "TransactionNumber: " + Transaction_Number,
						"TransactionNumber must be Entered ", "Entered TransactionNumber ", "Passed", "", true);
			} else {
				rpt.generateReport("", "Enter TransactionNumber", "", "TransactionNumber: " + Transaction_Number,
						"TransactionNumber must be Entered ", "Not Entered TransactionNumber ", "Failed", "", true);
				Assert.fail("Not Entered TransactionNumber");
			}
		}

		if (Transaction_Date.length() > 0) {
			if (cmnLib.enterDataInTextBox(manageTransactions.txtTransactionDate, Transaction_Date, true)) {
				rpt.generateReport("", "Enter TransactionDate", "", "TransactionDate: " + Transaction_Date,
						"TransactionDate must be Entered ", "Entered TransactionDate ", "Passed", "", true);
			} else {
				rpt.generateReport("", "Enter TransactionDate", "", "TransactionDate: " + Transaction_Date,
						"TransactionDate must be Entered ", "Not Entered TransactionDate ", "Failed", "", true);
				Assert.fail("Not Entered TransactionDate");
			}
		}

		if (Bill_to_Customer.length() > 0) {
			if (cmnLib.enterDataInTextBox(manageTransactions.txtBillToCustomer, Bill_to_Customer, true)) {
				rpt.generateReport("", "Enter BillToCustomer", "", "BilltoCustomer: " + Bill_to_Customer,
						"BillToCustomer must be Entered ", "Entered BillToCustomer ", "Passed", "", true);
			} else {
				rpt.generateReport("", "Enter BillToCustomer", "", "BilltoCustomer: " + Bill_to_Customer,
						"BillToCustomer must be Entered ", "Not Entered BillToCustomer ", "Failed", "", true);
				Assert.fail("Not Entered BillToCustomer");
			}
		}

		if (cmnLib.clickOnWebElement(manageTransactions.btnSearch)
				&& cmnLib.waitForElementToBeVisible(manageTransactions.tblBodySearchResults)) {
			rpt.generateReport("", "Click Search button", "", "",
					"Search button must be clicked and Search Results must be displayed",
					"Clicked on Search button and Search Results displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Search button", "", "",
					"Search button must be clicked and Search Results must be displayed",
					"Either not clicked on Search button or Search Results not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Search button or Search Results not displayed");
		}

	}

	@Then("^click action and select duplicate$")
	public void click_action_and_then_duplicate() throws Throwable {
		if (cmnLib.clickOnWebElement(manageTransactions.lnkActions)) {
			rpt.generateReport("", "Click Action Link", "", "",
					"Action Link must be clicked and Duplicate link must be displayed",
					"Clicked on Action Link or Duplicate link is displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Action Link", "", "",
					"Action Link must be clicked and Duplicate link must be displayed",
					"Either not clicked on Action Link or Duplicate link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Action Link or Duplicate link not displayed");
		}

		if (cmnLib.clickOnWebElement(manageTransactions.lnkDuplicate)) {
			rpt.generateReport("", "Click Dupicate Link", "", "", "Dupicate Link must be clicked",
					"Clicked on Dupicate Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Dupicate Link", "", "", "Dupicate Link must be clicked",
					"Not clicked on Dupicate Link", "Failed", "", true);
			Assert.fail("Not clicked on Dupicate Link");
		}
	}

	@Then("^click on Complete and Review \"([^\"]*)\"$")
	public void click_on_Complete_and_Review(String New_Transaction_Source) throws Throwable {
		if (cmnLib.enterDataInTextBox(manageTransactions.txtTransactionSource, New_Transaction_Source, true)) {

			rpt.generateReport("", "Enter TransactionSource", "", "TransactionSource: " + New_Transaction_Source,
					"TransactionSource must be Entered ", "Entered TransactionSource ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter TransactionSource", "", "TransactionSource: " + New_Transaction_Source,
					"TransactionSource must be Entered ", "Not Entered TransactionSource ", "Failed", "", true);
			Assert.fail("Not Entered TransactionSource");
		}
		if (cmnLib.clickOnWebElement(manageTransactions.btnCompleteAndReview)) {

			rpt.generateReport("", "Click CompleteAndReview button", "", "",
					"CompleteAndReview button must be clicked ", "Clicked on CompleteAndReview button", "Passed", "",
					true);
		} else {

			rpt.generateReport("", "Click CompleteAndReview button", "", "",
					"CompleteAndReview button must be clicked ", "Not clicked on CompleteAndReview button", "Failed",
					"", true);
			Assert.fail("Not clicked on CompleteAndReview button");
		}
		String strStatus = manageTransactions.getStatus.getText();
		System.out.println("Status:     " + strStatus);
		if (strStatus.equalsIgnoreCase("Complete")) {
			rpt.generateReport("", "Verify Status in Review Transaction Page", "", "Status" + strStatus,
					"Status must appear", "Status appears: " + strStatus, "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Status in Review Transaction Page", "", "Status" + strStatus,
					"Status must appear", "Status did not appear", "Failed", "", true);
			Assert.fail("Failed to verify Status");
		}

		if (!(cmnLib.clickOnWebElement(manageTransactions.btnSave))) {
			rpt.generateReport("", "Click Save button", "", "", "Save button must be clicked ",
					"Not clicked on Save button ", "Failed", "", true);
			Assert.fail("Not clicked on Save button ");
		}
	}

	@Then("^Click Transaction Number in Search Results \"([^\"]*)\"$")
	public void click_Transaction_Number_in_Search_Results(String trxNumber) throws Throwable {
		if (manageTransactions.clickTransactionNumberInResults(trxNumber)
				&& cmnLib.waitForElementToBeVisible(manageTransactions.hdrReveiwTransaction)) {
			rpt.generateReport("", "Click Transaction Number in Results", "", "Transaction Number: " + trxNumber,
					"Transaction Number must be clicked and Review Transaction page must be displayed",
					"Clicked Transaction Number and Reveiw Transaction page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Transaction Number in Results", "", "Transaction Number: " + trxNumber,
					"Transaction Number must be clicked and Review Transaction page must be displayed",
					"Either not clicked Transaction Number or Reveiw Transaction page not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked Transaction Number or Reveiw Transaction page not displayed");
		}
	}

	@Then("^Select \"([^\"]*)\" under Actions$")
	public void select_under_Actions(String option) throws Throwable {
		if (appSteps.selectOptionFromActionsDropdown(manageTransactions.lnkActions, option)) {
			rpt.generateReport("", "Select " + option + " under Actions", "", "", option + " must be selected",
					"Selected " + option, "Passed", "", true);
		} else {
			rpt.generateReport("", "Select " + option + " under Actions", "", "", option + " must be selected",
					option + " not selected", "Failed", "", true);
			Assert.fail(option + " not selected");
		}
	}


	
	@Then("^Verify Transaction Activity status is \"([^\"]*)\"$")
	public void verify_Transaction_status_is(String status) throws Throwable {
		String strTrxNum = PropertyUtils.getProperty("trxnum");
		if (manageTransactions.selectTransactionNumberInTransactionActivitiesResults(strTrxNum)) {
			TimeUnit.SECONDS.sleep(3);
			rpt.generateReport("", "Select Transaction Number in Transaction Activities", "",
					"Transaction Number: " + strTrxNum, "Transaction Number must be selected",
					"Selected Transaction Number", "Passed", "", true);
		} else {
			rpt.generateReport("", "Select Transaction Number in Transaction Activities", "",
					"Transaction Number: " + strTrxNum, "Transaction Number must be selected",
					"Transaction Number not selected in Transaction Activities", "Failed", "", true);
			Assert.fail("Transaction Number not selected in Transaction Activities");
		}
		if (cmnLib.waitForElementToBeVisible(manageTransactions.lblTrxActivityStatus)
				&& manageTransactions.lblTrxActivityStatus.getText().equalsIgnoreCase(status)) {
			rpt.generateReport("", "Verify Transaction Activity Status", "", "",
					"Transaction Activity Status must be " + status, "Transaction Activity Status is " + status,
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Transaction Activity Status", "", "",
					"Transaction Activity Status must be " + status,
					"Transaction Activity Status not as expected: " + status, "Failed", "", true);
			Assert.fail("Transaction Activity Status not as expected: " + status);
		}
		if (!cmnLib.clickOnWebElement(manageTransactions.btnDone)
				&& cmnLib.waitForElementToBeInvisible(manageTransactions.btnDone)) {
			rpt.generateReport("", "Click Done button", "", "", "Transaction Activities window must be closed",
					"Either not clicked on Done button or Transaction Activities window not closed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Done button or Transaction Activities window not closed");
		}
		if (!cmnLib.clickOnWebElement(manageTransactions.btnSave)) {
			rpt.generateReport("", "Click Save button", "", "", "Save button must be clicked",
					"Not clicked on Save button", "Failed", "", true);
			Assert.fail("Not clicked on Save button");
		}
		TimeUnit.SECONDS.sleep(3);
	}

}