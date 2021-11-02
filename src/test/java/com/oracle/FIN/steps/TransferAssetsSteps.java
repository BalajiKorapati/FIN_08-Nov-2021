package com.oracle.FIN.steps;

import org.testng.Assert;

import com.oracle.FIN.pages.AssetInquiryPage;
import com.oracle.FIN.pages.TransferAssetsPage;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.java.en.Then;
import report.oracle.ofs.ReportGeneration;

public class TransferAssetsSteps {

	
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	TransferAssetsPage transferAssets = new TransferAssetsPage();
	AssetInquiryPage assetInquiry = new AssetInquiryPage();
	
	
	@Then("^Perform Search for an Asset and choose Adjust Unit \"([^\"]*)\", \"([^\"]*)\"$")
	public void perform_Search_for_an_Asset_and_choose_Adjust_Unit(String book, String assetNumber) throws Throwable {
		
		rpt.enterStepHeader("Perform Search for an Asset");
		if (book.length() > 0) {
			if (cmnLib.selectDropdownBy(transferAssets.Book, book, DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Book from the dropdown", "", book, "Book must be selected",
						"Selected Book", "Passed", "", true);
			} else {
				rpt.generateReport("", "Select Book from the dropdown", "", book, "Book must be selected",
						"Book not selected", "Failed", "", true);
				Assert.fail("Book not selected");
			}
		}

		if (cmnLib.enterDataInTextBox(transferAssets.txtAssetNumber, assetNumber, true)) {
			rpt.generateReport("", "Enter Asset Number", "", assetNumber, "Asset Number must be entered",
					"Entered Asset Number", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Asset Number", "", assetNumber, "Asset Number must be entered",
					"Asset Number not entered", "Failed", "", true);
			Assert.fail("Asset Number not entered");
		}

		if (cmnLib.clickOnWebElement(transferAssets.btnSearch)
				&& cmnLib.waitForElementToBeVisible(transferAssets.btnAdjustUnits)) {
			rpt.generateReport("", "Click on Search button", "", "",
					"Search button must be clicked and Adjust Units button must be displayed",
					"Clicked on Search button and Adjust Units button displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Search button", "", "",
					"Search button must be clicked and Adjust Units button must be displayed",
					"Either not clicked on Search button or Adjust Units button not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Search button or Adjust Units button not displayed");
		}
		
		
		if (cmnLib.clickOnWebElement(transferAssets.btnAdjustUnits)) {
			rpt.generateReport("", "Click on Adjust Units", "", "",
					"Adjust Units must be clicked ",
					"Clicked on Adjust Units", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Adjust Units", "", "",
					"Adjust Units must be clicked ",
					"Not Clicked on Adjust Units button", "Failed", "", true);
			Assert.fail("Not Clicked on Adjust Units button");
		}
		
	   
	}
	
	
	@Then("^Click on Add button$")
	public void click_on_Add_button() throws Throwable {
		
		if (cmnLib.clickOnWebElement(transferAssets.btnAdd)){
			rpt.generateReport("", "Click Add button", "", "", "Add button must be clicked",
					"Clicked on Add button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Add button", "", "", "Add button must be clicked ",
					"Add button is not clicked", "Failed", "", true);
			Assert.fail("Add button is not clicked");
		}
	   
	}

	@Then("^Enter the Unit details \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_the_Unit_details(String NewUnit, String DepreciationExpenseAccount, String Location) throws Throwable {
		
		rpt.enterStepHeader("Enter the Unit details");
		
		if (!cmnLib.enterDataInTextBox(transferAssets.txtNewUnit, NewUnit, true)) {
			rpt.generateReport("", "Enter Bank Account", "", NewUnit, "Bank Account must be entered",
					"Bank Account not entered", "Failed", "", true);
			Assert.fail("Bank Account not entered");
		}

		
		if (!cmnLib.enterDataInTextBox(transferAssets.txtDepreciationAccount, DepreciationExpenseAccount, true)) {
			rpt.generateReport("", "Enter Depreciation Expense Account", "", DepreciationExpenseAccount,
					"Depreciation Expense Account must be entered", "Depreciation Expense Account not entered", "Failed", "",
					true);
			Assert.fail("Depreciation Expense Account not entered");
		}

		if (!cmnLib.enterDataInTextBox(transferAssets.txtLocation, Location, true)) {
			rpt.generateReport("", "Enter Location", "", Location, "Location must be entered",
					"Location not entered", "Failed", "", true);
			Assert.fail("Location not entered");
		}

		rpt.generateReport("", "Enter the Unit details", "",
				"New Unit:" + NewUnit + "\nDepreciation Expense Account: " + DepreciationExpenseAccount
						+ "\nLocation: " + Location,
				"Unit details must be entered", "Unit details entered", "Passed", "", true);

	}

		
	  
	

	@Then("^Click on Submit button$")
	public void click_on_Submit_button() throws Throwable {
		
		if (cmnLib.clickOnWebElement(transferAssets.btnSubmit)
				&& cmnLib.waitForElementToBeVisible(transferAssets.btnDone)) {
			rpt.generateReport("", "Click on Submit button", "", "",
					"Submit button must be clicked and Transfer Assets page must be displayed",
					"Clicked on Submit button and Transfer Assets page is displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Submit button", "", "",
					"Submit button must be clicked and Transfer Assets page must be displayed",
					"Either not clicked on Submit button or Transfer Assets page not opened", "Failed", "", true);
			Assert.fail("Either not clicked on Submit button or Transfer Assets page not opened");
		}
	    
		
		cmnLib.clickOnWebElement(transferAssets.btnDone);
	}

	
	@Then("^Perform Search for an Asset and choose Transfer Asset \"([^\"]*)\", \"([^\"]*)\"$")
	public void perform_Search_for_an_Asset_and_choose_Transfer_Asset(String book, String assetNumber) throws Throwable {
		
		rpt.enterStepHeader("Perform Search for an Asset");
		if (book.length() > 0) {
			if (cmnLib.selectDropdownBy(transferAssets.Book, book, DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Book from the dropdown", "", book, "Book must be selected",
						"Selected Book", "Passed", "", true);
			} else {
				rpt.generateReport("", "Select Book from the dropdown", "", book, "Book must be selected",
						"Book not selected", "Failed", "", true);
				Assert.fail("Book not selected");
			}
		}

		if (cmnLib.enterDataInTextBox(transferAssets.txtAssetNumber, assetNumber, true)) {
			rpt.generateReport("", "Enter Asset Number", "", assetNumber, "Asset Number must be entered",
					"Entered Asset Number", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Asset Number", "", assetNumber, "Asset Number must be entered",
					"Asset Number not entered", "Failed", "", true);
			Assert.fail("Asset Number not entered");
		}

		if (cmnLib.clickOnWebElement(transferAssets.btnSearch)
				&& cmnLib.waitForElementToBeVisible(transferAssets.btnTransferAsset)) {
			rpt.generateReport("", "Click on Search button", "", "",
					"Search button must be clicked and Transfer Asset button must be displayed",
					"Clicked on Search button and Transfer Asset button displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Search button", "", "",
					"Search button must be clicked and Transfer Asset button must be displayed",
					"Either not clicked on Search button or Transfer Asset button not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Search button or Transfer Asset button not displayed");
		}
		
		
		if (cmnLib.clickOnWebElement(transferAssets.btnTransferAsset)) {
			rpt.generateReport("", "Click on  Transfer Asset", "", "",
					"Transfer Asset must be clicked ",
					"Clicked on Transfer Asset", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Transfer Asset", "", "",
					"Transfer Asset must be clicked ",
					"Not Clicked on Transfer Asset button", "Failed", "", true);
			Assert.fail("Not Clicked on Transfer Asset button");
		}
	  
	}
	@Then("^Enter the Unit details \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_the_Unit_details(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		
		
	    
	}


	
}
