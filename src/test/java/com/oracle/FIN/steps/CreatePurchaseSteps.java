package com.oracle.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.oracle.FIN.pages.CreatePurchasePage;
import com.oracle.FIN.pages.EditDocumentPage;
import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.acs.util.PropertyUtils;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.java.en.Then;
import report.oracle.ofs.ReportGeneration;

public class CreatePurchaseSteps extends BrowserDriverUtil {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	CreatePurchasePage createPurchase = new CreatePurchasePage();
	EditDocumentPage editDocument = new EditDocumentPage();

	@Then("^Enter Create details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void enter_Create_details(String style, String procurementBU, String RequisitioningBU, String supplier)
			throws Throwable {
		if (style.length() > 0) {
			if (!cmnLib.enterDataInTextBox(createPurchase.txtStyle, style, true)) {
				rpt.generateReport("", "Enter Style", "", style, "Style must be entered", "Style not entered", "Failed",
						"", true);
				Assert.fail("Style not entered");
			}
		}

		if (procurementBU.length() > 0) {
			if (!cmnLib.selectDropdownBy(createPurchase.selProcurementBU, procurementBU,
					DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Procurement BU", "", procurementBU, "Procurement BU must be selected",
						"Procurement BU not selected", "Failed", "", true);
				Assert.fail("Procurement BU not selected");
			}
		}

		if (RequisitioningBU.length() > 0) {
			if (!cmnLib.selectDropdownBy(createPurchase.selRequisitioningBU, RequisitioningBU,
					DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Requisitioning BU", "", procurementBU,
						"Requisitioning BU must be selected", "Requisitioning BU not selected", "Failed", "", true);
				Assert.fail("Requisitioning BU not selected");
			}
		}

		if (!cmnLib.enterDataInTextBox(createPurchase.txtSupplier, supplier, true)) {
			rpt.generateReport("", "Enter Supplier", "", supplier, "Supplier must be entered", "Supplier not entered",
					"Failed", "", true);
			Assert.fail("Supplier not entered");
		}

		rpt.generateReport("", "Enter Create details", "",
				"Style: " + style + "\nProcurement BU: " + procurementBU + "\nRequisitioning BU: " + RequisitioningBU
						+ "\nSupplier: " + supplier,
				"Required details must be entered", "Required details entered", "Passed", "", true);
	}

	@Then("^Click Create button in Create window$")
	public void click_Create_button_in_Create_window() throws Throwable {
		if (cmnLib.clickOnWebElement(createPurchase.btnCreate)
				&& cmnLib.waitForElementToBeVisible(editDocument.btnSubmit)) {
			rpt.generateReport("", "Click Create button", "", "",
					"Create button must be clicked and Edit Document page must be displayed",
					"Clicked on Create button and Edit Document page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Create button", "", "",
					"Create button must be clicked and Edit Document page must be displayed",
					"Either not clicked on Create button or Edit Document page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Create button or Edit Document page not displayed");
		}
	}

	@Then("^Verify \"([^\"]*)\" number is created$")
	public void verify_number_is_created(String label) throws Throwable {
		String strNumber = null;
		WebElement element = cmnLib
				.getElement(By.xpath("//label[text()='" + label + "']//parent::td//parent::tr//td[2]"));
		if (cmnLib.waitForElementToBeVisible(element)) {
			strNumber = element.getText();
			PropertyUtils.setProperty("number", strNumber);
			rpt.generateReport("", "Verify " + label + " Number", "", "" + label + " Number: " + strNumber,
					"" + label + " Number must be created", "" + label + " Number created", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify " + label + " Number", "", "" + label + " Number: " + strNumber,
					"" + label + " Number must be created", "" + label + " Number not created", "Failed", "", true);
			Assert.fail("" + label + " Number not created");
		}
	}

	@Then("^Enter General Details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void enter_General_Details_for_Purchase_Agreement(String supplier, String startDate, String endDate,
			String agreementAmount) throws Throwable {
		rpt.enterStepHeader("Enter General Details");
		if (supplier.length() > 0) {
			if (!cmnLib.enterDataInTextBox(editDocument.txtSupplier, supplier, true)) {
				rpt.generateReport("", "Enter Supplier", "", supplier, "Supplier must be entered",
						"Supplier not entered", "Failed", "", true);
				Assert.fail("Supplier not entered");
			}
		}

		if (startDate.length() > 0) {
			if (!cmnLib.enterDataInTextBox(editDocument.txtStartDate, startDate, true)) {
				rpt.generateReport("", "Enter Start Date", "", startDate, "Start Date must be entered",
						"Start Date not entered", "Failed", "", true);
				Assert.fail("Start Date not entered");
			}
		}

		if (endDate.length() > 0) {
			TimeUnit.SECONDS.sleep(2);
			if (!cmnLib.enterDataInTextBox(editDocument.txtEndDate, endDate, true)) {
				rpt.generateReport("", "Enter End Date", "", endDate, "End Date must be entered",
						"End Date not entered", "Failed", "", true);
				Assert.fail("End Date not entered");
			}
		}

		if (agreementAmount.length() > 0) {
			if (!cmnLib.enterDataInTextBox(editDocument.txtAgreementAmount, agreementAmount, true)) {
				rpt.generateReport("", "Enter Agreement Amount", "", agreementAmount,
						"Agreement Amount must be entered", "Agreement Amount not entered", "Failed", "", true);
				Assert.fail("Agreement Amount not entered");
			}
		}

		rpt.generateReport("", "Enter General details", "",
				"Supplier: " + supplier + "Start Date: " + startDate + "\nEnd Date: " + endDate + "\nAgreement Amount: "
						+ agreementAmount,
				"Required details must be entered", "Required details not entered", "Passed", "", true);
	}

	@Then("^Enter Line Details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void enter_Line_Details(String type, String item, String description, String categoryName, String quantity,
			String uom, String price) throws Throwable {
		rpt.enterStepHeader("Enter Line Details");
		if (cmnLib.clickOnWebElement(editDocument.imgAddRow)) {
			rpt.generateReport("", "Click Add Row icon", "", "", "Add Row icon must be clicked", "Clicked Add Row icon",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Click Add Row icon", "", "", "Add Row icon must be clicked",
					"Add Row icon not clicked", "Failed", "", true);
			Assert.fail("Add Row icon not clicked");
		}

		if (type.length() > 0) {
			if (!cmnLib.enterDataInTextBox(editDocument.txtType, type, true)) {
				rpt.generateReport("", "Enter Type", "", type, "Type must be entered", "Type not entered", "Failed", "",
						true);
				Assert.fail("Type not entered");
			}
			TimeUnit.SECONDS.sleep(2);
		}

		if (!cmnLib.enterDataInTextBox(editDocument.txtItem, item, true)) {
			rpt.generateReport("", "Enter Item", "", item, "Item must be entered", "Item not entered", "Failed", "",
					true);
			Assert.fail("Item not entered");
		}

		if (description.length() > 0) {
			if (!cmnLib.enterDataInTextBox(editDocument.txtDescription, description, true)) {
				rpt.generateReport("", "Enter Description", "", description, "Description must be entered",
						"Description not entered", "Failed", "", true);
				Assert.fail("Description not entered");
			}
		}

		if (categoryName.length() > 0) {
			if (!cmnLib.enterDataInTextBox(editDocument.txtCategoryName, categoryName, true)) {
				rpt.generateReport("", "Enter Category Name", "", categoryName, "Category Name must be entered",
						"Category Name not entered", "Failed", "", true);
				Assert.fail("Category Name not entered");
			}
		}

		if (quantity.length() > 0) {
			if (!cmnLib.enterDataInTextBox(editDocument.txtQuantity, quantity, true)) {
				rpt.generateReport("", "Enter Quantity", "", quantity, "Quantity must be entered",
						"Quantity not entered", "Failed", "", true);
				Assert.fail("Quantity not entered");
			}
		}

		if (uom.length() > 0) {
			if (!cmnLib.enterDataInTextBox(editDocument.txtUOM, uom, true)) {
				rpt.generateReport("", "Enter UOM", "", uom, "UOM must be entered", "UOM not entered", "Failed", "",
						true);
				Assert.fail("UOM not entered");
			}
		}

		TimeUnit.SECONDS.sleep(2);
		if (!cmnLib.enterDataInTextBox(editDocument.txtPrice, price, true)) {
			rpt.generateReport("", "Enter Price", "", price, "Price must be entered", "Price not entered", "Failed", "",
					true);
			Assert.fail("Price not entered");
		}

		rpt.generateReport("", "Enter Line details", "",
				"Type: " + type + "\nItem: " + item + "\nDescription: " + description + "\nCategory Name: "
						+ categoryName + "\nQuantity: " + quantity + "\nUOM: " + uom + "\nPrice: " + price,
				"Line details must be entered", "Line details entered", "Passed", "", true);

	}

	@Then("^Click Submit button$")
	public void click_Submit_button() throws Throwable {
		if (cmnLib.clickOnWebElement(editDocument.btnSubmit)) {
			rpt.generateReport("", "Click Submit button", "", "", "Submit button must be clicked",
					"Clicked on Submit button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Submit button", "", "", "Submit button must be clicked",
					"Not clicked on Submit button", "Failed", "", true);
			Assert.fail("Not clicked on Submit button");
		}
	}

	@Then("^Verify \"([^\"]*)\" submitted confirmation message and Click OK$")
	public void verify_submitted_confirmation_message_and_Click_OK(String message) throws Throwable {
		if (cmnLib.waitForElementToBeVisible(createPurchase.msgConfirmation)
				&& createPurchase.msgConfirmation.getText().contains(message)) {
			rpt.generateReport("", "Verify Confirmation message", "", "", "Confirmation message must be displayed",
					"Confirmation message displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Confirmation message", "", "", "Confirmation message must be displayed",
					"Confirmation message not displayed", "Failed", "", true);
			Assert.fail("Confirmation message not displayed");
		}

		if (cmnLib.clickOnWebElement(createPurchase.OKConfirmation)
				&& cmnLib.waitForElementToBeInvisible(createPurchase.OKConfirmation)) {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Confirmation window must be closed",
					"Clicked on OK button and Confirmation window closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Confirmation window must be closed",
					"Either not clicked on OK button or Confirmation window not closed", "Failed", "", true);
			Assert.fail("Either not clicked on OK button or Confirmation window not closed");
		}
	}

}
