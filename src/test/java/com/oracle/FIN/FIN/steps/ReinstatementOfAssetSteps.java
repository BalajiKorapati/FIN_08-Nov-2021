package com.oracle.FIN.FIN.steps;

import org.junit.Assert;

import com.oracle.FIN.FIN.pages.ReinststementOfAssetPage;
import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.FIN.common.pages.HomePage;
import com.oracle.FIN.common.steps.CommonAppSteps;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.oracle.acs.util.report.ReportGeneration;

public class ReinstatementOfAssetSteps extends BrowserDriverUtil {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	ReinststementOfAssetPage reinstateAssert = new ReinststementOfAssetPage();
	CommonAppSteps appSteps = new CommonAppSteps();
	HomePage hmPage = new HomePage();
	Common_Library commonLibrary = new Common_Library();

	@When("^Perform Search for an Asset with \"([^\"]*)\"$")
	public void perform_Search_for_an_Asset_with(String assetNumber) throws Throwable {

		rpt.enterStepHeader("Perform Search for Asset Number");

		if (cmnLib.clickOnWebElement(hmPage.lnkAssets) && cmnLib.waitForElementToBeVisible(reinstateAssert.imgTasks)) {
			rpt.generateReport("", "Click Assets link", "", "",
					"Assets link must be clicked and Asset Addition page must be displayed",
					"Clicked on Assets link and Asset Addition page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Assets link", "", "",
					"Assets link must be clicked and Asset Addition page must be displayed",
					"Either not clicked on Assets link or Asset Addition page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Assets link or Asset Addition page not displayed");
		}
		if (!(cmnLib.clickOnWebElement(reinstateAssert.imgTasks))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and ReinstateAsset link must be displayed",
					"Either not clicked on Tasks icon or ReinstateAsset link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or ReinstateAsset link not displayed");
		}
		Thread.sleep(2000);

		if ((cmnLib.clickOnWebElement(reinstateAssert.linkReinstateAsset))) {
			rpt.generateReport("", "Click ReinstateAsset link", "", "",
					"ReinstateAsset link must be clicked and ReinstateAsset Page must be displayed",
					"Either not clicked on ReinstateAsset link or ReinstateAsset page displayed", "Passed", "", true);

		} else {
			rpt.generateReport("", "Click ReinstateAsset link", "", "",
					"ReinstateAsset link must be clicked and ReinstateAsset Page must be displayed",
					"Either not clicked on ReinstateAsset link or ReinstateAsset page not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on ReinstateAsset link or ReinstateAsset page not displayed");
		}

		if (cmnLib.enterDataInTextBox(reinstateAssert.txtAssetNumber, assetNumber, true)) {
			rpt.generateReport("", "Enter Asset Number", "", assetNumber, "Asset Number must be entered",
					"Entered Asset Number", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Asset Number", "", assetNumber, "Asset Number must be entered",
					"Asset Number not entered", "Failed", "", true);
			Assert.fail("Asset Number not entered");
		}
		if (cmnLib.clickOnWebElement(reinstateAssert.btnSearch)
				&& cmnLib.waitForElementToBeVisible(reinstateAssert.tblBodySearchResults)) {
			rpt.generateReport("", "Click Search button", "", "",
					"Search button must be clicked and Results must be displayed",
					"Clicked on Search button and Results displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Search button", "", "",
					"Search button must be clicked and Results must be displayed",
					"Either not clicked on Search button or Results not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Search button or Results not displayed");
		}
	}

	@When("^Select Row in Asset Results$")
	public void select_Row_in_Asset_Results() throws Throwable {
		if (reinstateAssert.selectRowInAssetResults()) {
			rpt.generateReport("", "Select Asset Row", "", "", "Row must be selected", "Row selected", "Passed", "",
					true);
		} else {
			rpt.generateReport("", "Select Asset Row", "", "", "Row must be selected", "Row not selected", "Failed", "",
					true);
			Assert.fail("Row not selected");
		}
	}

	@Then("^Click on Reinstate button$")
	public void click_on_Reinstate_button() throws Throwable {
		if (cmnLib.clickOnWebElement(reinstateAssert.btnReinstate)
				&& cmnLib.waitForElementToBeVisible(reinstateAssert.btnReinstate)) {
			rpt.generateReport("", "Click Reinstate button", "", "",
					"Reinstate must be clicked and Reinstate window must be opened",
					"Clicked on Reinstate button and Reinstate window opened", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Reinstate button", "", "",
					"Reinstate must be clicked and Reinstate window must be opened",
					"Either not clicked on Reinstate button or Reinstate window not opened", "Failed", "", true);
			Assert.fail("Either not clicked on Reinstate button or Reinstate window not opened");
		}

	}

	@Then("^Click on Warning Yes button$")
	public void click_on_Warning_Yes_button() throws Throwable {
		Thread.sleep(2000);
		if (cmnLib.clickOnWebElement(reinstateAssert.btnWarningYes)) {
			rpt.generateReport("", "Click Warning Yes button", "", "",
					"Waring Yes button must be clicked and Waring window must be closed",
					"Clicked on Warning Yes button and warning window opened", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Warning Yes button", "", "",
					"Warning Yes must be clicked and Warning window must be closed",
					"Either not clicked on Warning Yes button or Warning window not closed", "Failed", "", true);
			Assert.fail("Either not clicked on Warning Yes button or Warning window not closed");
		}
	}

	@Then("^Click on Done button$")
	public void click_on_Done_button() throws Throwable {
		if (!(cmnLib.clickOnWebElement(reinstateAssert.btnDone))) {
			rpt.generateReport("", "Click Done button", "", "", "Done button must be clicked ",
					"clicked on Done button", "Failed", "", true);
			Assert.fail("Clicked on Done button");
		}
	}

	@Then("^Perform Search for an Asset Inquiry with \"([^\"]*)\",\"([^\"]*)\"$")
	public void perform_Search_for_an_Asset_Inquiry_with(String bookName, String assetNumber) throws Throwable {
		if (cmnLib.selectDropdownBy(reinstateAssert.selectBookName, bookName, DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Select Book Name", "", bookName, "Book Name must be selected", "Selected Book Name",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Select Book Name", "", assetNumber, "Book Name must be selected",
					"Book Name not selected", "Failed", "", true);
			Assert.fail("Book Name not selected");
		}

		if (cmnLib.enterDataInTextBox(reinstateAssert.txtAssetNumber, assetNumber, true)) {
			rpt.generateReport("", "Enter Asset Number", "", assetNumber, "Asset Number must be entered",
					"Entered Asset Number", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Asset Number", "", assetNumber, "Asset Number must be entered",
					"Asset Number not entered", "Failed", "", true);
			Assert.fail("Asset Number not entered");
		}
		if (cmnLib.clickOnWebElement(reinstateAssert.btnSearch)) {
			rpt.generateReport("", "Click Search button", "", "", "Search button must be clicked",
					"Clicked on Search button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Search button", "", "", "Search button must be clicked ",
					"Not clicked on Search button ", "Failed", "", true);
			Assert.fail(" not clicked on Search button");
		}
	}

	@Then("^User navigates to Transactions \"([^\"]*)\"$")
	public void user_navigates_to_Transactions(String Transaction_Type) throws Throwable {
		Thread.sleep(2000);
		if (cmnLib.waitForElementToBeVisible(reinstateAssert.lnkTransactions.get(0))
				&& cmnLib.clickOnWebElement(reinstateAssert.lnkTransactions.get(0))) {
			rpt.generateReport("", "Click Transactions Link", "", "", "Transactions Link must be clicked",
					"Clicked on Transactions Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Transactions Link", "", "", "Transactions Link must be clicked ",
					"Not clicked on Transactions Link ", "Failed", "", true);
			Assert.fail(" not clicked on Transactions Link");
		}
		if (!cmnLib.moveToElementAndClick(reinstateAssert.TransactionNumber_SortDescending)) {
			rpt.generateReport("", "Click Sort Descending arrow in Transaction Number column", "", "",
					"Sort Descending arrow must be clicked", "Sort Descending arrow not clicked", "Failed", "", true);
			Assert.fail("Sort Descending arrow in Transaction Number column not clicked");
		}
		String strTransactionTypeBeforeTransaction = reinstateAssert.getRecentTransactionDetail("2");
		if (strTransactionTypeBeforeTransaction != null) {
			rpt.generateReport("", "Click Sort Descending arrow in Transaction Number column", "", "",
					"Sort Descending arrow must be clicked", "Sort Descending arrow clicked", "Passed", "", false);
			rpt.generateReport("", "Capture recent Transaction Type", "", "",
					"Recent Transaction Type must be captured",
					"Recent Transaction Type: " + strTransactionTypeBeforeTransaction, "Passed", "", true);
		} else {
			rpt.generateReport("", "Capture recent Transaction Type", "", "",
					"Recent Transaction Type must be captured", "Recent Transaction Type not captured", "Failed", "",
					true);
			Assert.fail("Recent Transaction Type not captured");
		}

	}

}
