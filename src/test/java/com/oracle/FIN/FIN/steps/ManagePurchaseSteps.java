package com.oracle.FIN.FIN.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.oracle.FIN.FIN.pages.CreatePurchasePage;
import com.oracle.FIN.FIN.pages.EditDocumentPage;
import com.oracle.FIN.FIN.pages.ManagePurchasePage;
import com.oracle.FIN.FIN.pages.PurchaseRequisitionsPage;
import com.oracle.acs.util.PropertyUtils;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.en.Then;
import com.oracle.acs.util.report.ReportGeneration;

public class ManagePurchaseSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	ManagePurchasePage managePurchase = new ManagePurchasePage();
	CreatePurchasePage createPurchase = new CreatePurchasePage();
	EditDocumentPage editDocument = new EditDocumentPage();

	@Then("^Perform Search for \"([^\"]*)\" and verify results displayed \"([^\"]*)\"$")
	public void perform_Search_and_verify_results_displayed(String label, String number) throws Throwable {
		rpt.enterStepHeader("Perform Search for " + label);

		String strNumber = null;
		if (number.length() > 0) {
			strNumber = number;
			PropertyUtils.setProperty("number", strNumber);
		} else {
			strNumber = PropertyUtils.getProperty("number");
		}

		WebElement element = cmnLib
				.getElement(By.xpath("//label[text()='" + label + "']//parent::td//parent::tr//td[2]//input"));
		if (cmnLib.enterDataInTextBox(element, strNumber, true)) {
			rpt.generateReport("", "Enter " + label, "", strNumber, label + " must be entered", label + " entered",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Enter " + label, "", strNumber, label + " must be entered", label + " not entered",
					"Passed", "", true);
			Assert.fail(label + " not entered");
		}

		if (cmnLib.waitForElementToBeVisible(managePurchase.txtBuyer, 1)) {
			managePurchase.txtBuyer.clear();
		}

		if (cmnLib.clickOnWebElement(managePurchase.btnSearch)) {
			rpt.generateReport("", "Click Search button", "", "", "Search button must be clicked",
					"Clicked Search button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Search button", "", "", "Search button must be clicked",
					"Not clicked on Search button", "Failed", "", true);
			Assert.fail("Not clicked on Search button");
		}

		if (cmnLib.waitForElementToBeVisible(managePurchase.tblBdySearchResults)) {
			rpt.generateReport("", "Verify results", "", "", "Results must be displayed based on the search criteria",
					"Search Results displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify results", "", "", "Results must be displayed based on the search criteria",
					"Search Results not displayed", "Failed", "", true);
			Assert.fail("Search Results not displayed");
		}
	}

	@Then("^Click \"([^\"]*)\" link in Results and verify \"([^\"]*)\" page displayed$")
	public void click_Results_and_verify_page_displayed(String item, String pageHeader) throws Throwable {
		String strOrderNum = PropertyUtils.getProperty("number");
		if (managePurchase.clickOrderInResults(strOrderNum) && cmnLib.verifyHeader(pageHeader)) {
			rpt.generateReport("", "Click Number in Results", "", strOrderNum,
					"Number must be clicked and " + pageHeader + " page must be displayed",
					"Clicked Number and " + pageHeader + " page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Number in Results", "", strOrderNum,
					"Number must be clicked and " + pageHeader + " page must be displayed",
					"Either not clicked Number or " + pageHeader + " page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked Number or " + pageHeader + " page not displayed");
		}
	}

	@Then("^Click Edit under Actions dropdown$")
	public void click_Edit_under_Actions_dropdown() throws Throwable {
		if (cmnLib.clickOnWebElement(managePurchase.btnActions)
				&& cmnLib.waitForElementToBeVisible(managePurchase.optionEdit)) {
			rpt.generateReport("", "Click Actions button", "", "", "Edit action must be displayed",
					"Edit action displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Actions button", "", "", "Edit action must be displayed",
					"Either not clicked on Actions button or Edit action not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Actions button or Edit action not displayed");
		}

		if (cmnLib.clickOnWebElement(managePurchase.optionEdit)
				&& cmnLib.waitForElementToBeInvisible(managePurchase.optionEdit)) {
			rpt.generateReport("", "Click Edit action", "", "", "Edit action must be clicked", "Clicked on Edit action",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Click Edit action", "", "", "Edit action must be clicked",
					"Not clicked on Edit action", "Failed", "", true);
			Assert.fail("Not clicked on Edit action");
		}

	}

	@Then("^Select Cancel Requisition under Actions dropdown$")
	public void select_Cancel_Requisition_under_Actions_dropdown() throws Throwable {
		if (cmnLib.clickOnWebElement(managePurchase.btnActions)
				&& cmnLib.waitForElementToBeVisible(managePurchase.optionCancelRequisition)) {
			rpt.generateReport("", "Click Actions button", "", "", "Cancel Requisition action must be displayed",
					"Cancel Requisition action displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Actions button", "", "", "Cancel Requisition action must be displayed",
					"Either not clicked on Actions button or Cancel Requisition action not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Actions button or Cancel Requisition action not displayed");
		}

		if (cmnLib.clickOnWebElement(managePurchase.optionCancelRequisition)
				&& cmnLib.waitForElementToBeInvisible(managePurchase.optionCancelRequisition)) {
			rpt.generateReport("", "Click Cancel Requisition action", "", "",
					"Cancel Requisition action must be clicked", "Clicked on Cancel Requisition action", "Passed", "",
					true);
		} else {
			rpt.generateReport("", "Click Cancel Requisition action", "", "",
					"Cancel Requisition action must be clicked", "Not clicked on Cancel Requisition action", "Failed",
					"", true);
			Assert.fail("Not clicked on Cancel Requisition action");
		}
	}

	@Then("^Update Purchase Order details \"([^\"]*)\", \"([^\"]*)\"$")
	public void update_Purchase_Order(String paymentTerms, String description) throws Throwable {
		rpt.enterStepHeader("Update Purchase Order details");
		if (cmnLib.enterDataInTextBox(editDocument.txtPaymentTerms, paymentTerms, true)) {
			rpt.generateReport("", "Enter Payment Terms", "", paymentTerms, "Payment Terms must be entered",
					"Entered Payment Terms", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Payment Terms", "", paymentTerms, "Payment Terms must be entered",
					"Payment Terms not entered", "Failed", "", true);
			Assert.fail("Payment Terms not entered");
		}

		if (cmnLib.enterDataInTextBox(editDocument.txtChangeOrderDescription, description, true)) {
			rpt.generateReport("", "Enter Description", "", description, "Description must be entered",
					"Entered Description", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Description", "", description, "Description must be entered",
					"Description not entered", "Failed", "", true);
			Assert.fail("Description not entered");
		}
	}

	@Then("^Verify Change Order warning message and Click Yes$")
	public void verify_Change_Order_warning_message_and_Click_Yes() throws Throwable {
		boolean warning = false;
		if (cmnLib.waitForElementToBeVisible(managePurchase.msgWarningChangeOrder, 10)) {
			warning = true;
			rpt.generateReport("", "Verify Change Order warning message", "", "", "Warning message must be displayed",
					"Warning message displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Change Order warning message", "", "", "Warning message must be displayed",
					"Warning message not displayed", "Failed", "", true);
		}

		if (warning) {
			if (cmnLib.clickOnWebElement(managePurchase.btnYes) && cmnLib.verifyHeader("Edit Change Order")) {
				rpt.generateReport("", "Click Yes button", "", "", "Edit Change Order page must be displayed",
						"Edit Change Order page displayed", "Passed", "", true);
			} else {
				rpt.generateReport("", "Click Yes button", "", "", "Edit Change Order page must be displayed",
						"Either not clicked on Yes button or Edit Change Order page not displayed", "Failed", "", true);
				Assert.fail("Either not clicked on Yes button or Edit Change Order page not displayed");
			}
		}

	}

	@Then("^Click Manage Requisitions link$")
	public void click_Manage_Requisitions_link() throws Throwable {
		PurchaseRequisitionsPage prPage = new PurchaseRequisitionsPage();
		if (cmnLib.clickOnWebElement(prPage.lnkManageRequisitions) && cmnLib.verifyHeader("Manage Requisitions")) {
			rpt.generateReport("", "Click Manage Requisitions link", "", "",
					"Manage Requisitions page must be displayed", "Manage Requisitions page displayed", "Passed", "",
					true);
		} else {
			rpt.generateReport("", "Click Manage Requisitions link", "", "",
					"Manage Requisitions page must be displayed",
					"Either not clicked on Manage Requisitions link or Manage Requisitions page not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked on Manage Requisitions link or Manage Requisitions page not displayed");
		}
	}

	@Then("^Enter \"([^\"]*)\" and Click OK in Cancel Requisition window$")
	public void enter_and_Click_OK_in_Cancel_Requisition_window(String reason) throws Throwable {
		if (cmnLib.enterDataInTextBox(managePurchase.txtReason, reason, true)) {
			rpt.generateReport("", "Enter Reason", "", reason, "Reason must be entered", "Entered Reason", "Passed", "",
					true);
		} else {
			rpt.generateReport("", "Enter Reason", "", reason, "Reason must be entered", "Reason not entered", "Failed",
					"", true);
			Assert.fail("Reason not entered");
		}

		if (cmnLib.clickOnWebElement(managePurchase.btnOKCancelReq)
				&& cmnLib.waitForElementToBeInvisible(managePurchase.btnOKCancelReq)) {
			rpt.generateReport("", "Click OK button", "", "", "OK button must be clicked", "Clicked OK button",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "", "OK button must be clicked", "Not clicked OK button",
					"Failed", "", true);
			Assert.fail("Not clicked OK button");
		}
	}

	@Then("^Verify error message and Click OK$")
	public void verify_error_message_and_Click_OK() throws Throwable {
		if (cmnLib.waitForElementToBeVisible(managePurchase.msgErrorPendingProcessing)) {
			rpt.generateReport("", "Verify Error message", "", "", "Error message must be displayed",
					"Error message displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Error message", "", "", "Error message must be displayed",
					"Error message not displayed", "Failed", "", true);
			Assert.fail("Error message not displayed");
		}

		if (cmnLib.clickOnWebElement(managePurchase.btnOKError)
				&& cmnLib.waitForElementToBeInvisible(managePurchase.btnOKError)) {
			rpt.generateReport("", "Click OK button", "", "", "Window must be closed", "Error window closed", "Passed",
					"", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "", "Window must be closed",
					"Either not clicked on OK button or Error window not closed", "Failed", "", true);
			Assert.fail("Either not clicked on OK button or Error window not closed");
		}
	}

	@Then("^Click Cancel Document under Actions dropdown$")
	public void click_Cancel_Document_under_Actions_dropdown() throws Throwable {
		if (cmnLib.clickOnWebElement(managePurchase.btnActions)
				&& cmnLib.waitForElementToBeVisible(managePurchase.optionCancelDocument)) {
			rpt.generateReport("", "Click Actions button", "", "", "Cancel Document action must be displayed",
					"Cancel Document action displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Actions button", "", "", "Cancel Document action must be displayed",
					"Either not clicked on Actions button or Cancel Document action not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Actions button or Cancel Document action not displayed");
		}

		if (cmnLib.clickOnWebElement(managePurchase.optionCancelDocument)
				&& cmnLib.waitForElementToBeInvisible(managePurchase.optionCancelDocument)) {
			rpt.generateReport("", "Click Cancel Document action", "", "", "Cancel Document action must be clicked",
					"Clicked on Cancel Document action", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Cancel Document action", "", "", "Cancel Document action must be clicked",
					"Not clicked on Cancel Document action", "Failed", "", true);
			Assert.fail("Not clicked on Cancel Document action");
		}
	}

	@Then("^Enter \"([^\"]*)\" and Click OK in Cancel Document window$")
	public void enter_and_Click_OK_in_Cancel_Document_window(String reason) throws Throwable {
		if (cmnLib.enterDataInTextBox(managePurchase.txtReason, reason, true)) {
			rpt.generateReport("", "Enter Reason", "", reason, "Reason must be entered", "Entered Reason", "Passed", "",
					true);
		} else {
			rpt.generateReport("", "Enter Reason", "", reason, "Reason must be entered", "Reason not entered", "Failed",
					"", true);
			Assert.fail("Reason not entered");
		}

		if (cmnLib.clickOnWebElement(managePurchase.btnOKCancelDoc)
				&& cmnLib.waitForElementToBeInvisible(managePurchase.btnOKCancelDoc)) {
			rpt.generateReport("", "Click OK button", "", "", "OK button must be clicked", "Clicked OK button",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "", "OK button must be clicked", "Not clicked OK button",
					"Failed", "", true);
			Assert.fail("Not clicked OK button");
		}
	}

	@Then("^Select PR row in Search results$")
	public void select_PR_PO_row_in_Search_results() throws Throwable {
		String strNumber = PropertyUtils.getProperty("number");
		if (managePurchase.SelectRowInResults(strNumber)) {
			rpt.generateReport("", "Select Row in Search results", "", strNumber, "Row must be selected",
					"Row selected", "Passed", "", true);
		} else {
			rpt.generateReport("", "Select Row in Search results", "", strNumber, "Row must be selected",
					"Row not selected", "Failed", "", true);
			Assert.fail("Row not selected");
		}
	}

	@Then("^Click Add to Document Builder button$")
	public void click_Add_to_Document_Builder_button() throws Throwable {
		if (cmnLib.clickOnWebElement(managePurchase.btnAddToDocBuilder)
				&& cmnLib.waitForElementToBeVisible(managePurchase.btnOKAddToDocBuilder)) {
			rpt.generateReport("", "Click Add to Document Builder button", "", "",
					"Add to Document Builder window must be displayed", "Add to Document Builder window displayed",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Click Add to Document Builder button", "", "",
					"Add to Document Builder window must be displayed",
					"Either not clicked on Add to Document Builder button or window not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Add to Document Builder button or window not displayed");
		}
	}

	@Then("^Click OK in Add to Document Builder window$")
	public void click_OK_in_Add_to_Document_Builder_window() throws Throwable {
		if (cmnLib.clickOnWebElement(managePurchase.btnOKAddToDocBuilder)
				&& cmnLib.waitForElementToBeInvisible(managePurchase.btnOKAddToDocBuilder)) {
			rpt.generateReport("", "Click OK button", "", "", "Add to Document Builder window must be closed",
					"Add to Document Builder window closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "", "Add to Document Builder window must be closed",
					"Either not clicked on OK button or Add to Document Builder window not closed", "Failed", "", true);
			Assert.fail("Either not clicked on OK button or Add to Document Builder window not closed");
		}
	}

	@Then("^Click Create button in Document Builder window$")
	public void click_Create_button_in_Document_Builder_window() throws Throwable {
		if (cmnLib.clickOnWebElement(managePurchase.btnCreate)) {
			rpt.generateReport("", "Click Create button", "", "", "Create button must be clicked",
					"Clicked Create button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Create button", "", "", "Create button must be clicked",
					"No clicked on Create button", "Failed", "", true);
			Assert.fail("No clicked on Create button");
		}
	}

	@Then("^Verify \"([^\"]*)\" value is displayed as \"([^\"]*)\"$")
	public void verify_value_is_displayed_as(String label, String value) throws Throwable {
		String strValue = null;
		WebElement element = cmnLib
				.getElement(By.xpath("//label[text()='" + label + "']//parent::td//parent::tr//td[2]"));
		if (cmnLib.waitForElementToBeVisible(element)) {
			strValue = element.getText();
			if (strValue.equalsIgnoreCase(value)) {
				rpt.generateReport("", "Verify " + label, "", "", label + " must be displayed as " + value,
						label + " displayed as " + strValue, "Passed", "", true);
			} else {
				rpt.generateReport("", "Verify " + label, "", "", label + " must be displayed as " + value,
						label + " not displayed as " + strValue, "Failed", "", true);
				Assert.fail(label + " not displayed as " + strValue);
			}
		}
	}
}