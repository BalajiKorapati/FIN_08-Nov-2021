package com.oracle.FIN.FIN.steps;

import com.oracle.FIN.FIN.pages.CreatePositivePayFilePage;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import com.oracle.acs.util.report.ReportGeneration;

public class CreatePositivePayFileSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	CreatePositivePayFilePage positivePayPage = new CreatePositivePayFilePage();
	public static String strProcessID;

	@Then("^Enter Positive Pay parameters \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void enter_Positive_Pay_parameters(String fromPaymentDate, String toPaymentDate,
			String allowSendingReplacementCopy, String status) throws Throwable {
		rpt.enterStepHeader("Enter Positive Pay parametres");
		if (!cmnLib.selectDropdownBy(positivePayPage.selPaymentProcessProfile,
				ManageInvoicesSteps.strPaymentProcessProfile, DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Select Payment Process Profile", "", ManageInvoicesSteps.strPaymentProcessProfile,
					"Payment Process Profile must be selected", "Payment Process Profile not selected", "Failed", "",
					true);
			Assert.fail("Payment Process Profile not selected");
		}

		if (fromPaymentDate.length() > 0) {
			if (!cmnLib.enterDataInTextBox(positivePayPage.txtFromPaymentDate, fromPaymentDate, true)) {
				rpt.generateReport("", "Enter From Payment Date", "", fromPaymentDate,
						"From Payment Date must be entered", "From Payment Date not entered", "Passed", "", true);
				Assert.fail("From Payment Date not entered");
			}
		}

		if (!cmnLib.enterDataInTextBox(positivePayPage.txtToPaymentDate, toPaymentDate, true)) {
			rpt.generateReport("", "Enter To Payment Date", "", toPaymentDate, "To Payment Date must be entered",
					"To Payment Date not entered", "Passed", "", true);
			Assert.fail("To Payment Date not entered");
		}

		if (allowSendingReplacementCopy.length() > 0) {
			if (!cmnLib.selectDropdownBy(positivePayPage.selReplacementCopy, allowSendingReplacementCopy,
					DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Allow sending replacement copy", "", "",
						"Allow sending replacement copy must be selected",
						"Allow sending replacement copy not selected", "Failed", "", true);
				Assert.fail("Allow sending replacement copy not selected");
			}
		}

		if (status.length() > 0) {
			if (!cmnLib.selectDropdownBy(positivePayPage.selStatus, status, DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Status", "", "", "Status must be selected", "Status not selected",
						"Failed", "", true);
				Assert.fail("Status not selected");
			}
		}

		rpt.generateReport("", "Enter Positive Pay parameters", "",
				"Payment Process Profile: " + ManageInvoicesSteps.strPaymentProcessProfile + "\nFrom Payment Date: "
						+ fromPaymentDate + "\nTo Payment Date: " + toPaymentDate + "\nAllow sending replacement copy: "
						+ allowSendingReplacementCopy + "\nStatus" + status,
				"Required details must be entered", "Required details entered", "Passed", "", true);

	}

	@Then("^Click Submit button and verify Process submitted confirmation message$")
	public void click_Submit_button_and_verify_Process_submitted_confirmation_message() throws Throwable {
		if (cmnLib.clickOnWebElement(positivePayPage.btnSubmit)
				&& cmnLib.waitForElementToBeVisible(positivePayPage.msgConfirmationProcessSubmitted)) {
			String strMsgConfirmation = positivePayPage.msgConfirmationProcessSubmitted.getText();
			strProcessID = strMsgConfirmation.split(" ")[1];
			rpt.generateReport("", "Click Submit button", "", "",
					"Submit button must be clicked and Process Confirmation message must be displayed",
					"Clicked on Submit button and Process Confirmation message displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Submit button", "", "",
					"Submit button must be clicked and Process Confirmation message must be displayed",
					"Either not clicked on Submit button or Process Confirmation message not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Submit button or Process Confirmation message not displayed");
		}
	}

	@Then("^Click OK button in Process Confirmation window$")
	public void click_OK_button() throws Throwable {
		if (cmnLib.clickOnWebElement(positivePayPage.btnOKProcessSubmitted)
				&& cmnLib.waitForElementToBeInvisible(positivePayPage.btnOKProcessSubmitted)) {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Process Confirmation message must be closed",
					"Clicked on OK button and Process Confirmation message closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Process Confirmation message must be closed",
					"Either not clicked on OK button or Process Confirmation message not closed", "Failed", "", true);
			Assert.fail("Either not clicked on OK button or Process Confirmation message not closed");
		}
	}
}
