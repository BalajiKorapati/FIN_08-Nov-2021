package com.oracle.FIN.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.oracle.FIN.FIN.pages.AddAssetPage;
import com.oracle.FIN.FIN.pages.AssetInquiryPage;
import com.oracle.FIN.FIN.pages.AssetsPage;
import com.oracle.FIN.FIN.pages.ManageInvoicesPage;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.FIN.common.steps.CommonAppSteps;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.oracle.acs.util.report.ReportGeneration;

public class AddAssestsSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	ManageInvoicesPage manageInvoices = new ManageInvoicesPage();
	CommonAppSteps appSteps = new CommonAppSteps();
	private String strInvoiceNumber;
	AddAssetPage addAsset = new AddAssetPage();
	AssetsPage assets = new AssetsPage();
	AssetInquiryPage assetInquiry=null;

	String strAssetNumber;
	String strBook;

	@When("^Enter Basic information \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_Basic_information(String Book, String AssetType, String Category, String Description, String Cost, String Units, String ExpenseAccount, String Location) throws Throwable {

		strBook=Book;
		rpt.enterStepHeader("Enter Basic information to Add Asset");
		if (cmnLib.waitForElementToBeVisible(addAsset.Book) && !cmnLib.selectDropdownBy(addAsset.Book, Book, DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Select Book", "", Book, "Book must be selected", "Book is not selected",
					"Failed", "", true);
			Assert.fail("Failed to select Book");
		}

		String strAssetType = AssetType;
		if (!cmnLib.selectDropdownBy(addAsset.AssetType, strAssetType, DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Select Asset Type", "", strAssetType, "Asset Type must be selected",
					"Asset Type not selected", "Failed", "", true);
			Assert.fail("Failed to select Asset Type");
		}

		TimeUnit.SECONDS.sleep(2);
		String strCategory = Category;
		if (!cmnLib.enterDataInTextBox(addAsset.Category, strCategory, false)) {
			rpt.generateReport("", "Enter Category", "", strCategory, "Category must be entered",
					"Category not entered", "Failed", "", true);
			Assert.fail("Failed to enter Category");
		}

		TimeUnit.SECONDS.sleep(2);
		String strDescription = Description;
		if (!cmnLib.enterDataInTextBox(addAsset.Description, strDescription, false)) {
			rpt.generateReport("", "Enter Description", "", strDescription, "Description must be entered",
					"Description not entered", "Failed", "", true);
			Assert.fail("Failed to enter Description");
		}

		TimeUnit.SECONDS.sleep(2);
		String strCost = null;
		if (!strAssetType.equalsIgnoreCase("CIP")) {
			strCost = Cost;
			if (!cmnLib.enterDataInTextBox(addAsset.Cost, strCost, true)) {
				rpt.generateReport("", "Enter Cost", "", strCost, "Cost must be entered", "Cost not entered",
						"Failed", "", true);
				Assert.fail("Failed to enter Cost");
			}
		}

		TimeUnit.SECONDS.sleep(2);
		String strUnits = Units;
		if (strUnits.length() > 0) {
			if (!cmnLib.enterDataInTextBox(addAsset.Units, strUnits, false)) {
				rpt.generateReport("", "Enter Units", "", strUnits, "Units must be entered", "Units not entered",
						"Failed", "", true);
				Assert.fail("Failed to enter Units");
			}
		}

		TimeUnit.SECONDS.sleep(2);
		String strExpenseAccount = ExpenseAccount;
		if (!cmnLib.enterDataInTextBox(addAsset.ExpenseAccount, strExpenseAccount, false)) {
			rpt.generateReport("", "Enter Expense Account", "", strExpenseAccount,
					"Expense Account must be entered", "Expense Account not entered", "Failed", "", true);
			Assert.fail("Failed to enter Expense Account");
		}

		TimeUnit.SECONDS.sleep(2);
		String strLocation = Location;
		if (!cmnLib.enterDataInTextBox(addAsset.Location, strLocation, false)) {
			rpt.generateReport("", "Enter Location", "", strLocation, "Location must be entered",
					"Location not entered", "Failed", "", true);
			Assert.fail("Failed to enter Location");
		}

		rpt.generateReport("", "Enter Asset Details", "",
				"Book: " + Book + "\nAsset Type: " + strAssetType + "\nCategory: " + strCategory
				+ "\nDescription: " + strDescription + "\nCost: " + strCost + "\nUnits: " + strUnits
				+ "\nExpense Account: " + strExpenseAccount + "\nLocation: " + strLocation,
				"Required details must be entered", "Required details entered", "Passed", "", true);

		if (cmnLib.clickOnWebElement(addAsset.NextButton)) {
			rpt.generateReport("", "Click on Next button", "", "", "Next button must be clicked",
					"Clicked on Next button", "Passed", "", false);
		} else {
			rpt.generateReport("", "Click on Next button", "", "", "Next button must be clicked",
					"Next button not clicked", "Failed", "", true);
		}



	}

	@When("^Enter Asset details \"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_Asset_details(String AssetNum, String InServiceDate) throws Throwable {

		rpt.enterStepHeader("Asset details");

		cmnLib.waitForElementToBeVisible(addAsset.AssetNumber, 20);
		strAssetNumber = cmnLib.randomNumber("ASN_");
		if (strAssetNumber.length() > 0) {
			if (cmnLib.waitForElementToBeVisible(addAsset.AssetNumber) && !cmnLib.enterDataInTextBox(addAsset.AssetNumber, strAssetNumber, true)) {
				rpt.generateReport("", "Enter Asset Number", "", strAssetNumber, "Asset Number must be entered",
						"Asset Number not entered", "Failed", "", true);
				Assert.fail("Failed to enter Asset Number");
			}
		}

		String strInServiceDate = InServiceDate;
		if (strInServiceDate.length() > 0) {
			if (!cmnLib.enterDataInTextBox(addAsset.InServiceDate, strInServiceDate, true)) {
				rpt.generateReport("", "Enter In Service Date", "", strInServiceDate,
						"In Service Date must be entered", "In Service Date not entered", "Failed", "", true);
				Assert.fail("Failed to enter In Service Date");
			}
		}

		rpt.generateReport("", "Enter Asset Details", "",
				"Asset Number: " + strAssetNumber + "\nIn Service Date: " + strInServiceDate,
				"Required details must be entered", "Required details are entered", "Passed", "", true);


	}

	@Then("^Enter Descriptive details \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_Descriptive_details(String TagNum, String SerialNum, String Manufacturer, String Model, String PropertyType, String Ownership, String Bought) throws Throwable {

		rpt.enterStepHeader("Descriptive Details");

		if (TagNum.length() > 0) {
			if (!cmnLib.enterDataInTextBox(addAsset.txtTagNum, TagNum, true)) {
				rpt.generateReport("", "Enter Tag number", "", TagNum,
						"Tag Number  must be entered", "Tag Number not entered", "Failed", "", true);
				Assert.fail("Failed to enter Tag Number");
			}
		}

		if (SerialNum.length() > 0) {
			if (!cmnLib.enterDataInTextBox(addAsset.txtSerialNum, SerialNum, true)) {
				rpt.generateReport("", "Enter Serial number", "", SerialNum,
						"Serial Number  must be entered", "Serial Number not entered", "Failed", "", true);
				Assert.fail("Failed to enter Serial Number");
			}
		}

		if (Manufacturer.length() > 0) {
			if (!cmnLib.enterDataInTextBox(addAsset.txtManufacturer, Manufacturer, true)) {
				rpt.generateReport("", "Enter Manufacturer", "", Manufacturer,
						"Manufacturer must be entered", "Manufacturer not entered", "Failed", "", true);
				Assert.fail("Failed to enter Manufacturer");
			}
		}

		if (Model.length() > 0) {
			if (!cmnLib.enterDataInTextBox(addAsset.txtModel, Model, true)) {
				rpt.generateReport("", "Enter Model", "", Model,
						"Model must be entered", "Model not entered", "Failed", "", true);
				Assert.fail("Failed to enter Model");
			}
		}


		String strPropertyType = PropertyType;
		if (strPropertyType.length() > 0) {
			if (!cmnLib.selectDropdownBy(addAsset.PropertyType, strPropertyType, DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Property Type", "", strPropertyType,
						"Property Type must be selected", "Property Type not selected", "Failed", "", true);
				Assert.fail("Failed to select Property Type");
			}
		}

		if (Ownership.length() > 0) {
			if (!cmnLib.enterDataInTextBox(addAsset.txtOwnership, Ownership, true)) {
				rpt.generateReport("", "Enter Ownership", "", Ownership,
						"Ownership must be entered", "Ownership not entered", "Failed", "", true);
				Assert.fail("Failed to enter Ownership");
			}
		}

		if (Bought.length() > 0) {
			if (!cmnLib.enterDataInTextBox(addAsset.txtBought, Bought, true)) {
				rpt.generateReport("", "Enter Bought", "", Bought,
						"Bought must be entered", "Bought not entered", "Failed", "", true);
				Assert.fail("Failed to enter Bought");
			}
		}

		rpt.generateReport("", "Enter Descriptive Details", "",
				"",
				"Required details must be entered", "Required details are entered", "Passed", "", true);



	}

	@Then("^Enter Assignments details \"([^\"]*)\"$")
	public void enter_Assignments_details(String EmployeeNum) throws Throwable {

		rpt.enterStepHeader("Assignment Details");
		String strEmployeeNumber = EmployeeNum;
		if (cmnLib.enterDataInTextBox(addAsset.EmployeeName, strEmployeeNumber, true)) {
			rpt.generateReport("", "Enter Employee Number", "", strEmployeeNumber,
					"Employee Number must be entered", "Employee Number entered", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Employee Number", "", strEmployeeNumber,
					"Employee Number must be entered", "Employee Number not entered", "Failed", "", true);
			Assert.fail("Failed to enter Employee Number");
		}
		TimeUnit.SECONDS.sleep(2);

	}

	@Then("^Enter Transaction details \"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_Transaction_details(String Comments, String Amortize) throws Throwable {

		rpt.enterStepHeader("Transaction Details");

		if (Comments.length() > 0) {
			if (!cmnLib.enterDataInTextBox(addAsset.txtComments, Comments, true)) {
				rpt.generateReport("", "Enter Comments", "", Comments,
						"Comments must be entered", "Comments not entered", "Failed", "", true);
				Assert.fail("Failed to enter Comments");
			}
		}

		if (Amortize.equalsIgnoreCase("Yes")) {
			if (!cmnLib.clickOnWebElement(addAsset.chkBoxAmortize)) {
				rpt.generateReport("", "Select Amortize", "", Amortize,
						"Amortize must be selected", "Amortize is selected", "Failed", "", true);
				Assert.fail("Failed to select Amortize");
			}
		}

		rpt.generateReport("", "Enter Transaction Details", "",
				"",
				"Required details must be entered", "Required details are entered", "Passed", "", true);
	}

	@Then("^Clicks on Submit button in Add Asset page$")
	public void clicks_on_Submit_button_in_Add_Asset_page() throws Throwable {

		if (cmnLib.clickByJSE(addAsset.Submit)) {
			rpt.generateReport("", "Click on Submit button", "", "", "Submit button must be clicked",
					"Clicked on Submit button", "Passed", "", false);
		} else {
			rpt.generateReport("", "Click on Submit button", "", "", "Submit button must be clicked",
					"Submit button not clicked", "Failed", "", true);
		}
	}

	@Then("^Verify the Asset creation$")
	public void verify_the_Asset_creation() throws Throwable {

		rpt.enterStepHeader("Verify the Asset creation");

		TimeUnit.SECONDS.sleep(5);
		cmnLib.waitForElementToBeVisible(assets.imgTaskPane,30);
		if(cmnLib.clickOnWebElement(assets.imgTaskPane)) {
			TimeUnit.SECONDS.sleep(5);
			cmnLib.clickOnWebElement(assets.lnkInquireAssets);
			assetInquiry = new AssetInquiryPage();
			rpt.generateReport("", "Navigate to Asset Inquiry page", "", "", "Navigate to Asset Inquiry page",
					"Navigated to Asset Inquiry page", "Passed", "", false);
		} else {
			rpt.generateReport("", "Navigate to Asset Inquiry page", "", "", "Navigate to Asset Inquiry page",
					"Could not Navigate to Asset Inquiry page", "Passed", "", false);
			Assert.fail("Could not Navigate to Asset Inquiry page");
		}

		rpt.enterStepHeader("Search for the Asset");
		TimeUnit.SECONDS.sleep(3);
		if (!cmnLib.selectDropdownBy(assetInquiry.Book, strBook, DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Select Book", "", strBook, "Book must be selected", "Book not selected",
					"Failed", "", true);
		}

		if (!cmnLib.enterDataInTextBox(assetInquiry.AssetNumber, strAssetNumber, false)) {
			rpt.generateReport("", "Enter Asseet Number", "", strAssetNumber, "Asset Number must be entered",
					"Asset Number not entered", "Failed", "", true);
		}

		rpt.generateReport("", "Enter Book and Asset Number", "", strBook + ", " + strAssetNumber,
				"Book and Asset Number must be entered", "Book and Asset Number must be entered", "Info", "", true);

		if (cmnLib.clickOnWebElement(assetInquiry.Search)) {
			rpt.generateReport("", "Click on Search button", "", "", "Search button must be clicked",
					"Clicked on Search button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Search button", "", "", "Search button must be clicked",
					"Search button not clicked", "Failed", "", true);
			Assert.fail("Failed to click Search button");
		}

		TimeUnit.SECONDS.sleep(5);
		if(cmnLib.verifySearchedRecordExists(strAssetNumber)) {
			rpt.generateReport("", "Verify Asset creation", "", "", "Asset number should appear in searched results",
					"Asset number appeared in searched results. Asset creation is successfull", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Asset creation", "", "", "Asset number should appear in searched results",
					"Asset number did not appear in searched results. Asset creation is Unsuccessfull", "failed", "", true);
			Assert.fail("Failed to create an Asset, number did not appear in searched results");


		}
	}

	
	@Then("^Unenable Depreciate flag$")
	public void unenable_Depreciate_flag() throws Throwable {
		rpt.enterStepHeader("Unenable Depreciate flag");
		
		if(cmnLib.clickOnWebElement(addAsset.chkboxDepreciate)) {
			rpt.generateReport("", "DeSelect Depreciate flag", "", "",
					"Depreciate flag must be Unenable", "Depreciate flag is Unenable", "Passed", "", true);
		}else {
			rpt.generateReport("", "DeSelect Depreciate flag", "", "",
					"Depreciate flag must be Unenable", "Depreciate flag is denable", "Failed", "", true);
			Assert.fail("Failed to Unenable Depreciate flag");
		}
		
	}

}



