package com.oracle.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.oracle.FIN.pages.AssetInquiryPage;
import com.oracle.FIN.pages.AssetsPage;
import com.oracle.FIN.pages.RetireAssetsPage;
import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import report.oracle.ofs.ReportGeneration;

public class RetireAnAssetSteps extends BrowserDriverUtil {
	
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	AssetsPage assets = new AssetsPage();
	AssetInquiryPage assetInquiry=new AssetInquiryPage();
	RetireAssetsPage retireAsset = new RetireAssetsPage();
	String GAssetNum;
	int CurrentCost;
	
	
	@When("^Search for an Asset \"([^\"]*)\"$")
	public void search_for_an_Asset(String AssetNum) throws Throwable {
		rpt.enterStepHeader("Search for an Asset");
		
		GAssetNum=AssetNum;
		System.out.println("Asset num :"+GAssetNum);

		/*
		 * TimeUnit.SECONDS.sleep(5);
		 * cmnLib.waitForElementToBeVisible(assets.imgTaskPane,30);
		 * if(cmnLib.clickOnWebElement(assets.imgTaskPane)) { TimeUnit.SECONDS.sleep(5);
		 * cmnLib.clickOnWebElement(assets.lnkInquireAssets); assetInquiry = new
		 * AssetInquiryPage(); rpt.generateReport("", "Navigate to Asset Inquiry page",
		 * "", "", "Navigate to Asset Inquiry page", "Navigated to Asset Inquiry page",
		 * "Passed", "", false); } else { rpt.generateReport("",
		 * "Navigate to Asset Inquiry page", "", "", "Navigate to Asset Inquiry page",
		 * "Could not Navigate to Asset Inquiry page", "Passed", "", false);
		 * Assert.fail("Could not Navigate to Asset Inquiry page"); }
		 */

		rpt.enterStepHeader("Search for the Asset");
		TimeUnit.SECONDS.sleep(3);

		if (!cmnLib.enterDataInTextBox(assetInquiry.AssetNumber, GAssetNum, true)) {
			rpt.generateReport("", "Enter Asseet Number", "", AssetNum, "Asset Number must be entered",
					"Asset Number not entered", "Failed", "", true);
		}

		rpt.generateReport("", "Enter Book and Asset Number", "",AssetNum,
				"Book and Asset Number must be entered", "Book and Asset Number must be entered", "Info", "", true);

		if (cmnLib.clickOnWebElement(assetInquiry.Search)) {
			rpt.generateReport("", "Click on Search button", "", "", "Search button must be clicked",
					"Clicked on Search button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Search button", "", "", "Search button must be clicked",
					"Search button not clicked", "Failed", "", true);
			Assert.fail("Failed to click Search button");
		}
		
		TimeUnit.SECONDS.sleep(4);
		if(cmnLib.verifySearchedRecordExists(AssetNum)) {
			rpt.generateReport("", "Asset number search", AssetNum, "", "Asset number should appear in searched results",
					"Asset number appeares in searched results", "Passed", "", true);
		} else {
			rpt.generateReport("", "Asset number search", AssetNum, "", "Asset number should appear in searched results",
					"Asset number did not appear in search results", "Failed", "", true);
			Assert.fail("Failed to find an Asset in searched results");
		}

	}

	@When("^Retire Cost of an Asset \"([^\"]*)\",\"([^\"]*)\"$")
	public void retire_Cost_of_an_Asset(String RetireCostComments, String RetireCost) throws Throwable {
		rpt.enterStepHeader("Retire Cost of an Asset");
		
		if(!cmnLib.clickOnWebElement(retireAsset.btnRetireCost)) {
			rpt.generateReport("", "Click on Retire Cost", "", "", "Should click on Retire Cost button",
					"Could not click on Retire Cost button", "Failed", "", true);
			Assert.fail("Failed to Click on Retire cost button");
		}
		
		if(cmnLib.waitForElementToBeVisible(retireAsset.txtRetireComments, 30)) {
			if(!cmnLib.enterDataInTextBox(retireAsset.txtRetireComments, RetireCostComments,true)) {
				rpt.generateReport("", "Enter Retire Cost comments", RetireCostComments, "", "Retire Cost Comments should be entered",
						"Could not enter Retire cost comments", "Failed", "", true);
				Assert.fail("Failed to enter on Retire cost comments");
			}
		}
		TimeUnit.SECONDS.sleep(3);
		String strActualCost = retireAsset.strActualCost.getText();
		String[] result = strActualCost.split("\\.");
		
		int actualCost = Integer.parseInt(result[0].replaceAll(",",""));
		
		if(Integer.parseInt(RetireCost)>actualCost) {
			rpt.generateReport("", "Enter Retire cost", RetireCost, "", "Retire Cost should be entered",
					"Retire cost value cannot be greater than Actual cost", "Failed", "", true);
			Assert.fail("Retire cost value cannot be greater than Actual cost");
		}else {
			if(!cmnLib.enterDataInTextBox(retireAsset.txtCostRetired, RetireCost, true)) {
				rpt.generateReport("", "Enter Retire cost", RetireCost, "", "Retire Cost should be entered",
						"Could not enter Retire Cost", "Failed", "", true);
				Assert.fail("Failed to enter Retire Cost value");
			}
			
			CurrentCost=actualCost-Integer.parseInt(RetireCost);
		}
		
		rpt.generateReport("", "Retire cost", "Retire Cost :"+RetireCost+"\n Retire Cost Comments :"+RetireCostComments, "", "Retire Cost and Retire Comments should be entered",
				"Retire Cost and Retire Comments are entered", "Passed", "", true);
	}

	@Then("^Click on Submit in Retire Assets page$")
	public void click_on_Submit_in_Retire_Assets_page() throws Throwable {
		
		if(cmnLib.clickOnWebElement(retireAsset.btnSubmit)) {
			rpt.generateReport("", "Submit button", "", "", "Submit button should be clicked",
					"Clicked on Submit button", "Passed", "", true);
		}else {
			rpt.generateReport("", "Submit button", "", "", "Submit button should be clicked",
					"Could not click on Submit button", "Failed", "", true);
			Assert.fail("Failed to Click on Submit button");
		}
	}

	@Then("^Retire Units of an Asset \"([^\"]*)\",\"([^\"]*)\"$")
	public void retire_Units_of_an_Asset(String RetireUnitComments, String RetireUnit) throws Throwable {
		if(cmnLib.waitForElementToBeVisible(retireAsset.btnRetireUnits, 30) && cmnLib.clickOnWebElement(retireAsset.btnRetireUnits)) {
			rpt.generateReport("", "Retire Unit", "", "", "Should click on Retire Unit button",
					"Clicked on Retire unit button", "Passed", "", true);
		}else {
			rpt.generateReport("", "Retire Unit", "", "", "Should click on Retire Unit button",
					"Either Retire Unit button is not visible or not clicked on Retire Unit button", "Failed", "", true);
			Assert.fail("Either Retire Unit button is not visible or not clicked on Retire Unit button");
		}
		
		cmnLib.waitForElementToBeVisible(retireAsset.txtRetireUnitsComments, 30);
		
		
		if(!cmnLib.enterDataInTextBox(retireAsset.txtRetireUnitsComments, RetireUnitComments, true)) {
			rpt.generateReport("", "Retire Unit comments", "", RetireUnitComments, "Retire unit comments should be entered",
					"Retire unit comments is not entered", "Failed", "", true);
			Assert.fail("Failed to enter Retire Unit comments");
		}
		
		int actualUnit=Integer.parseInt(retireAsset.strActualUnits.getText());
		
		if(Integer.parseInt(RetireUnit)>actualUnit) {
			rpt.generateReport("", "Enter Retire Unit", RetireUnit, "", "Retire Unit should be entered",
					"Retire Unit value cannot be greater than Actual Unit", "Failed", "", true);
			Assert.fail("Retire Unit value cannot be greater than Actual Unit");
		}else {
			if(!cmnLib.enterDataInTextBox(retireAsset.txtRetiredUnits, RetireUnit, true)) {
				rpt.generateReport("", "Enter Retire Unit", RetireUnit, "", "Retire Unit should be entered",
						"Could not enter Retire Unit", "Failed", "", true);
				Assert.fail("Failed to enter Retire Unit value");
			}
		}
	}

	@Then("^Verify the Retire Assests$")
	public void verify_the_Retire_Assests() throws Throwable {
		if(!(cmnLib.waitForElementToBeVisible(retireAsset.btnDoneBtn, 30) && cmnLib.clickOnWebElement(retireAsset.btnDoneBtn))) {
			rpt.generateReport("", "Done button", "", "",
					"Done button should be visible and then click on Done button",
					"Either Done button is not visible or not clicked on Done button", "Failed", "", true);
			Assert.fail("Either Done button is not visible or not clicked on Done button");
		}
		
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

		if (!cmnLib.enterDataInTextBox(assetInquiry.AssetNumber, GAssetNum, false)) {
			rpt.generateReport("", "Enter Asseet Number", "", GAssetNum, "Asset Number must be entered",
					"Asset Number not entered", "Failed", "", true);
		}

		if (cmnLib.clickOnWebElement(assetInquiry.Search)) {
			rpt.generateReport("", "Click on Search button", "", "", "Search button must be clicked",
					"Clicked on Search button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Search button", "", "", "Search button must be clicked",
					"Search button not clicked", "Failed", "", true);
			Assert.fail("Failed to click Search button");
		}

		TimeUnit.SECONDS.sleep(5);
		if(cmnLib.verifySearchedRecordExists(GAssetNum)) {
			rpt.generateReport("", "Verify Asset Number updation", "", "", "Asset number should appear in searched results",
					"Asset number appeared in searched results", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Asset Number updation", "", "", "Asset number should appear in searched results",
					"Asset number did not appear in searched results", "Failed", "", true);
			Assert.fail("Failed to search an Asset number did not appear in searched results");
		}
		
		cmnLib.scrollToPageBottomByJSE();
		TimeUnit.SECONDS.sleep(5);
		
		retireAsset.strCurrentCost.getText();
		String strCurrentCost = retireAsset.strCurrentCost.getText();
		String[] result = strCurrentCost.split("\\.");
		int LCurrentCost = Integer.parseInt(result[0].replaceAll(",",""));
		
		if(LCurrentCost==CurrentCost) {
			rpt.generateReport("", "Verify Asset Number updation", "", "", "Retire cost must be updated",
					"Retire cost is updated", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Asset Number updation", "", "", "Retire cost must be updated",
					"Retire cost is not updated", "Failed", "", true);
			Assert.fail("Failed to retire an Asset");
		}	}

}
