package com.oracle.FIN.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.oracle.FIN.FIN.pages.CreditTransactionPage;
import com.oracle.FIN.common.steps.ReportingSteps;
import com.oracle.acs.util.PropertyUtils;

import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.acs.util.report.ReportGeneration;

import io.cucumber.java.en.Then;
import com.oracle.acs.util.report.ReportGeneration;

public class CreditTransactionSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	CreditTransactionPage creditTrxPage = new CreditTransactionPage();

	public static String strTrxNum;


	@Then("^Search for a Transaction Number \"([^\"]*)\"$")
	public void search_for_a_Transaction_Number(String number) throws Throwable {
		rpt.enterStepHeader("Perform Search for a Transaction Number");
		if (cmnLib.enterDataInTextBox(creditTrxPage.txtNumber, number, true)
				&& cmnLib.waitForTextToBePresentInElement(creditTrxPage.lblBusinessUnit)) {
			rpt.generateReport("", "Search for a Transaction", "", number, "Transaction details must be displayed",
					"Transaction details displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Search for a Transaction", "", number, "Transaction details must be displayed",
					"Transaction details not displayed", "Passed", "", true);
			Assert.fail("Transaction details not displayed");
		}
	}

	@Then("^Enter Credit Amount \"([^\"]*)\" and verify tax amount is calculated$")
	public void enter_Credit_Amount_and_verify_tax_amount_is_calculated(String amount) throws Throwable {
		if (cmnLib.enterDataInTextBox(creditTrxPage.txtLineAmount, amount, true)) {
			rpt.generateReport("", "Enter Credit Amount", "", amount, "Credit Amount must be entered",
					"Entered Credit Amount", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Credit Amount", "", amount, "Credit Amount must be entered",
					"Credit Amount not entered", "Failed", "", true);
			Assert.fail("Credit Amount not entered");
		}

		TimeUnit.SECONDS.sleep(3);
		if (cmnLib.waitForElementToBeVisible(creditTrxPage.txtTaxAmount)) {
			String value = creditTrxPage.txtTaxAmount.getAttribute("value");
			if (!value.isEmpty()) {
				rpt.generateReport("", "Verify Tax amount is calculated", "", "", "Tax amount must be calculated",
						"Tax amount is calculated: " + value, "Passed", "", true);
			} else {
				rpt.generateReport("", "Verify Tax amount is calculated", "", "", "Tax amount must be calculated",
						"Tax amount not calculated", "Failed", "", true);
				Assert.fail("Tax amount not calculated");
			}
		}

	}

	@Then("^Click Edit Distributions button$")
	public void click_Edit_Distributions_button() throws Throwable {
		if (cmnLib.clickOnWebElement(creditTrxPage.btnEditDistributions)
				&& cmnLib.waitForElementToBeVisible(creditTrxPage.msgWarningDistribution)) {
			rpt.generateReport("", "Click Edit Distributions button", "", "",
					"Edit Distributions button must be clicked and Warning message must be displayed",
					"Clicked Edit Distributions button and Warning message displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Edit Distributions button", "", "",
					"Edit Distributions button must be clicked and Warning message must be displayed",
					"Either not clicked Edit Distributions button or Warning message not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked Edit Distributions button or Warning message not displayed");
		}

		if (cmnLib.clickOnWebElement(creditTrxPage.btnYesWarning)
				&& cmnLib.waitForElementToBeVisible(creditTrxPage.btnSaveAndCloseEditDistributions)) {
			rpt.generateReport("", "Click Yes button", "", "",
					"Yes button must be clicked and Edit Distributions window must be displayed",
					"Clicked Yes button and Edit Distributions window displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Yes button", "", "",
					"Yes button must be clicked and Edit Distributions window must be displayed",
					"Either not clicked Yes button or Edit Distributions window not displayed", "Failed", "", true);
			Assert.fail("Either not clicked Yes button or Edit Distributions window not displayed");
		}
	}

	@Then("^Reveiw Distributions and Click Save and Close$")
	public void reveiw_Distributions_and_Click_Save_and_Close() throws Throwable {
		if (cmnLib.clickOnWebElement(creditTrxPage.btnSaveAndCloseEditDistributions)
				&& cmnLib.waitForElementToBeInvisible(creditTrxPage.btnSaveAndCloseEditDistributions)) {
			rpt.generateReport("", "Click Save and Close button", "", "",
					"Save and Close button must be clicked and Edit Distributions window must be closed",
					"Clicked Save and Close button and Edit Distributions window closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Save and Close button", "", "",
					"Save and Close button must be clicked and Edit Distributions window must be closed",
					"Either not clicked Save and Close button or Edit Distributions window not closed", "Failed", "",
					true);
			Assert.fail("Either not clicked Save and Close button or Edit Distributions window not closed");
		}
	}

	@Then("^Click Complete and Close button and Verify the transaction created message$")
	public void click_Complete_and_Close_button() throws Throwable {
		if (cmnLib.clickOnWebElement(creditTrxPage.btnCompleteAndClose)
				&& cmnLib.waitForElementToBeVisible(creditTrxPage.msgInformation)) {

			strTrxNum = creditTrxPage.msgInformation.getText().split(" ")[1];

			String strTrxNum = creditTrxPage.msgInformation.getText().split(" ")[1];
			PropertyUtils.setProperty("trxnum", strTrxNum);

			rpt.generateReport("", "Click Complete and Close button", "", "",
					"Complete and Close button must be clicked and Transaction created message must be displayed",
					"Clicked Complete and Close button and Transaction created message displayed"
							+ "\nTransaction Number: " + strTrxNum,
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Click Complete and Close button", "", "",
					"Complete and Close button must be clicked and Transaction created message must be displayed",
					"Either not clicked Complete and Close button or Transaction created message not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked Complete and Close button or Transaction created message not displayed");
		}

		if (cmnLib.clickOnWebElement(creditTrxPage.btnOKInformation)
				&& cmnLib.waitForElementToBeInvisible(creditTrxPage.btnOKInformation)) {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Transaction created message must be closed",
					"Clicked OK button and Transaction created message closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Transaction created message must be closed",
					"Either not clicked OK button or Transaction created message not closed", "Failed", "", true);
			Assert.fail("Either not clicked OK button or Transaction created message not closed");
		}
	}

}
