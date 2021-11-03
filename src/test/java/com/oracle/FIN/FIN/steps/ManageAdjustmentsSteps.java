package com.oracle.FIN.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.oracle.FIN.FIN.pages.ManageAdjustmentsPage;
import com.oracle.FIN.FIN.pages.ManageTransactionsPage;
import com.oracle.acs.util.PropertyUtils;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.en.Then;
import com.oracle.acs.util.report.ReportGeneration;

public class ManageAdjustmentsSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	ManageAdjustmentsPage adjustmentsPage = new ManageAdjustmentsPage();
	ManageTransactionsPage manageTrxPage = new ManageTransactionsPage();

	@Then("^Click Create Adjustment icon$")
	public void click_Create_Adjustment_icon() throws Throwable {
		if (cmnLib.clickOnWebElement(adjustmentsPage.imgCreate)
				&& cmnLib.waitForElementToBeVisible(adjustmentsPage.btnSubmit)) {
			rpt.generateReport("", "Click Create icon", "", "",
					"Create icon must be clicked and Create Adjustment window must be opened",
					"Clicked on Create icon and Create Adjustment window opened", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Create icon", "", "",
					"Create icon must be clicked and Create Adjustment window must be opened",
					"Either not clicked on Create icon or Create Adjustment window not opened", "Failed", "", true);
			Assert.fail("Either not clicked on Create icon or Create Adjustment window not opened");
		}
	}

	@Then("^Enter Adjustment Details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void enter_Adjustment_Details(String activity, String type, String reason) throws Throwable {
		rpt.enterStepHeader("Enter Adjustment Details");
		if (!cmnLib.enterDataInTextBox(adjustmentsPage.txtReceivablesActivity, activity, true)) {
			rpt.generateReport("", "Enter Receivables Activity", "", activity, "Receivables Activity must be entered",
					"Receivables Activity not entered", "Failed", "", true);
			Assert.fail("Receivables Activity not entered");
		}

		TimeUnit.SECONDS.sleep(3);
		if (!cmnLib.selectDropdownBy(adjustmentsPage.selAdjustmentType, type, DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Select Adjustment Type", "", type, "Adjustment Type must be selected",
					"Adjustment Type not selected", "Failed", "", true);
			Assert.fail("Adjustment Type not selected");
		}

		if (reason.length() > 0) {
			if (!cmnLib.selectDropdownBy(adjustmentsPage.selAdjustmentReason, reason, DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Adjustment Reason", "", type, "Adjustment Reason must be selected",
						"Adjustment Reason not selected", "Failed", "", true);
				Assert.fail("Adjustment Reason not selected");
			}
		}

		rpt.generateReport("", "Enter Adjustment Details", "",
				"Receivables Activity: " + activity + "\nAdjustment Type: " + type + "\nAdjustment Reason: " + reason,
				"Adjustment Details must be selected", "Entered Adjustment details", "Passed", "", true);

	}

	@Then("^Submit Adjustment Details and Verify transaction created message$")
	public void submit_Adjustment_Details_and_Verify_transaction_created_message() throws Throwable {
		if (cmnLib.clickOnWebElement(adjustmentsPage.btnSubmit)
				&& cmnLib.waitForElementToBeVisible(adjustmentsPage.msgInformation)) {
			String strTrxNum = adjustmentsPage.msgInformation.getText().split(" ")[2];
			PropertyUtils.setProperty("trxnum", strTrxNum);
			rpt.generateReport("", "Click Submit button", "", "",
					"Submit button must be clicked and Transaction created message must be displayed",
					"Clicked Submit button and Transaction created message displayed" + "\nTransaction Number: "
							+ strTrxNum,
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Click Submit button", "", "",
					"Submit button must be clicked and Transaction created message must be displayed",
					"Either not clicked Submit button or Transaction created message not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked Submit button or Transaction created message not displayed");
		}

		if (cmnLib.clickOnWebElement(adjustmentsPage.btnOKInformation)
				&& cmnLib.waitForElementToBeInvisible(adjustmentsPage.btnOKInformation)) {
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

	@Then("^Click Done and Click Save$")
	public void click_Done_and_Click_Save() throws Throwable {
		if (!(cmnLib.clickOnWebElement(adjustmentsPage.btnDone)
				&& cmnLib.waitForElementToBeVisible(manageTrxPage.btnSave))) {
			rpt.generateReport("", "Click Done button", "", "",
					"Done button must be clicked and Review Transaction page must be displayed",
					"Either not clicked Done button or Review Transaction page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked Done button or Review Transaction page not displayed");
		}

		if (!cmnLib.clickOnWebElement(manageTrxPage.btnSave)) {
			rpt.generateReport("", "Click Save button", "", "", "Save button must be clicked",
					"Not clicked on Save button", "Failed", "", true);
			Assert.fail("Not clicked on Save button");
		}

		TimeUnit.SECONDS.sleep(3);

		if (!cmnLib.clickOnWebElement(manageTrxPage.btnCancel)) {
			rpt.generateReport("", "Click Cancel button", "", "", "Cancel button must be clicked",
					"Not clicked on Cancel button", "Failed", "", true);
			Assert.fail("Not clicked on Cancel button");
		}
	}

}
