package com.oracle.FIN.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.oracle.FIN.FIN.pages.PurchaseRequisitionsPage;
import com.oracle.acs.util.PropertyUtils;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.FIN.common.steps.CommonAppSteps;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.en.Then;
import com.oracle.acs.util.report.ReportGeneration;

public class CreatePurchaseRequisitionSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	CommonAppSteps appSteps = new CommonAppSteps();
	PurchaseRequisitionsPage prPage = new PurchaseRequisitionsPage();

	@Then("^Click \"([^\"]*)\" under More Tasks$")
	public void click_under_More_Tasks(String option) throws Throwable {
		if (appSteps.selectOptionFromActionsDropdown(prPage.lnkMoreTasks, option) && cmnLib.verifyHeader(option)) {
			rpt.generateReport("", "Select " + option + " under More Tasks", "", "", option + " page must be displayed",
					option + " page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Select " + option + " under More Tasks", "", "", option + " page must be displayed",
					"Either not clicked on " + option + " or " + option + " page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on " + option + " or " + option + " page not displayed");
		}
	}

	@Then("^Enter Requisition Line details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void enter_Requisition_Line_details(String lineType, String item, String quantity, String price)
			throws Throwable {
		rpt.enterStepHeader("Enter Requisition Line");
		if (lineType.length() > 0) {
			if (!cmnLib.selectDropdownBy(prPage.selLineTytpe, lineType, DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Line Type", "", lineType, "Line Type must be selected",
						"Line Type not selected", "Failed", "", true);
				Assert.fail("Line Type not selected");
			}
		}

		if (!cmnLib.enterDataInTextBox(prPage.txtItem, item, true)) {
			rpt.generateReport("", "Enter Item", "", item, "Item must be selected", "Item not selected", "Failed", "",
					true);
			Assert.fail("Item not selected");
		}

		TimeUnit.SECONDS.sleep(3);
		if (quantity.length() > 0) {
			if (!cmnLib.enterDataInTextBox(prPage.txtQuantity, quantity, true)) {
				rpt.generateReport("", "Enter Quantity", "", quantity, "Quantity must be selected",
						"Quantity not selected", "Failed", "", true);
				Assert.fail("Quantity not selected");
			}
		}

		if (!cmnLib.enterDataInTextBox(prPage.txtPrice, price, true)) {
			rpt.generateReport("", "Enter Price", "", price, "Price must be selected", "Price not selected", "Failed",
					"", true);
			Assert.fail("Price not selected");
		}

		rpt.generateReport("", "Enter Requisition Line Details", "",
				"Line Type: " + lineType + "\nItem: " + item + "\nQuantity: " + quantity + "\nPrice: " + price,
				"Requisition Line Details must be entered", "Requisition Line Details not entered", "Passed", "", true);

	}

	@Then("^Click Add to Cart and verify Item is added to Cart$")
	public void click_Add_to_Cart_and_verify_Item_is_added_to_Cart() throws Throwable {
		if (cmnLib.clickOnWebElement(prPage.btnAddToCart) && cmnLib.waitForElementToBeVisible(prPage.msgAddedToCart)) {
			rpt.generateReport("", "Click Add to Cart button", "", "", "Item must be added to Cart",
					"Item added to Cart", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Add to Cart button", "", "", "Item must be added to Cart",
					"Either not clicked Add to Cart button or Item not added to Cart", "Failed", "", true);
			Assert.fail("Either not clicked Add to Cart button or Item not added to Cart");
		}
	}

	@Then("^Click Cart icon and Click Submit button$")
	public void click_Cart_icon_and_Click_Submit_button() throws Throwable {
		if (cmnLib.clickOnWebElement(prPage.lnkCart) && cmnLib.waitForElementToBeVisible(prPage.btnSubmit)) {
			rpt.generateReport("", "Click Add to Cart button", "", "", "Item must be added to Cart",
					"Item added to Cart", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Add to Cart button", "", "", "Item must be added to Cart",
					"Either not clicked Add to Cart button or Item not added to Cart", "Failed", "", true);
			Assert.fail("Either not clicked Add to Cart button or Item not added to Cart");
		}

		if (cmnLib.clickOnWebElement(prPage.btnSubmit)) {
			rpt.generateReport("", "Click Submit button", "", "", "Submit button must be clicked",
					"Clicked Submit button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Submit button", "", "", "Submit button must be clicked",
					"Not clicked Submit button", "Failed", "", true);
			Assert.fail("Not clicked Submit button");
		}

	}

	@Then("^Verify Requisition submitted message and Click OK$")
	public void verify_Requisition_submitted_message_and_Click_OK() throws Throwable {
		if (cmnLib.waitForElementToBeVisible(prPage.msgConfirmation)) {
			String strPRNumber = prPage.msgConfirmation.getText().split(" ")[1];
			PropertyUtils.setProperty("number", strPRNumber);
			rpt.generateReport("", "Verify Confirmation message with PR number", "", strPRNumber,
					"Confirmation message must be displayed", "Confirmation message displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Confirmation message with PR number", "", "",
					"Confirmation message must be displayed", "Confirmation message not displayed", "Failed", "", true);
			Assert.fail("Confirmation message not displayed");
		}

		if (cmnLib.clickOnWebElement(prPage.okConfirmation)
				&& cmnLib.waitForElementToBeInvisible(prPage.okConfirmation)) {
			rpt.generateReport("", "Click OK button", "", "", "Confirmation window must be closed",
					"Confirmation window closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "", "Confirmation window must be closed",
					"Either not clicked on OK button or Confirmation window not closed", "Failed", "", true);
			Assert.fail("Either not clicked on OK button or Confirmation window not closed");
		}
	}
}
