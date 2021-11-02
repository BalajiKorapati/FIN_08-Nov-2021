package com.oracle.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.oracle.FIN.pages.AdjustAssetsPage;
import com.oracle.FIN.pages.AdjustAssetsPage.ResultsSelect;
import com.oracle.FIN.pages.AssetInquiryPage;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.java.en.Then;
import report.oracle.ofs.ReportGeneration;

public class AdjustAssetsSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	AdjustAssetsPage adjustAssets = new AdjustAssetsPage();
	AssetInquiryPage assetInquiry = new AssetInquiryPage();

	@Then("^Perform Search for an Asset \"([^\"]*)\", \"([^\"]*)\"$")
	public void enter_Search_Criteria(String book, String assetNumber) throws Throwable {
		rpt.enterStepHeader("Perform Search for an Asset");
		if (book.length() > 0) {
			if (cmnLib.selectDropdownBy(adjustAssets.Book, book, DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Book from the dropdown", "", book, "Book must be selected",
						"Selected Book", "Passed", "", false);
			} else {
				rpt.generateReport("", "Select Book from the dropdown", "", book, "Book must be selected",
						"Book not selected", "Failed", "", true);
				Assert.fail("Book not selected");
			}
		}

		if (cmnLib.enterDataInTextBox(adjustAssets.txtAssetNumber, assetNumber, true)) {
			rpt.generateReport("", "Enter Asset Number", "", assetNumber, "Asset Number must be entered",
					"Entered Asset Number", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Asset Number", "", assetNumber, "Asset Number must be entered",
					"Asset Number not entered", "Failed", "", true);
			Assert.fail("Asset Number not entered");
		}

		if (cmnLib.clickOnWebElement(adjustAssets.Search)
				&& cmnLib.waitForElementToBeVisible(adjustAssets.tBodySearchResults)) {
			rpt.generateReport("", "Click on Search button", "", "",
					"Search button must be clicked and Results must be displayed",
					"Clicked on Search button and Results displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Search button", "", "",
					"Search button must be clickedand Results must be displayed",
					"Either not clicked on Search button or Results not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Search button or Results not displayed");
		}

	}

	@Then("^Select Asset Line in results \"([^\"]*)\"$")
	public void select_row_in_results(String assetNumber) throws Throwable {
		if (adjustAssets.selectAssetFromResults(assetNumber, ResultsSelect.Row)) {
			rpt.generateReport("", "Select Asset Line in results", "", assetNumber, "Asset Line must be selected",
					"Selected Asset Line", "Passed", "", true);
		} else {
			rpt.generateReport("", "Select Asset Line in results", "", assetNumber, "Asset Line must be selected",
					"Asset Line not selected", "Failed", "", true);
			Assert.fail("Asset Line not selected in Results");
		}
	}

	@Then("^Select \"([^\"]*)\" under Actions dropdown$")
	public void select_under_Actions_dropdown(String option) throws Throwable {
		if (adjustAssets.selectValueFromActionsDropdown(option)) {
			rpt.generateReport("", "Select " + option + " from Actions dropdown", "", "", option + " must be selected",
					"Selected " + option, "Passed", "", false);
		} else {
			rpt.generateReport("", "Select " + option + " from Actions dropdown", "", "", option + " must be selected",
					option + " not selected", "Failed", "", true);
			Assert.fail(option + " not selected from Actions Dropdown");
		}

	}

	@Then("^Enter Comments \"([^\"]*)\" and Click OK$")
	public void enter_Comments_and_Click_OK(String comments) throws Throwable {
		if (cmnLib.enterDataInTextBox(adjustAssets.txtComments, comments, false)) {
			rpt.generateReport("", "Enter comments", "", comments, "Comments must be entered", "Entered Comments",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Enter comments", "", comments, "Comments must be entered", "Comments not entered",
					"Failed", "", true);
			Assert.fail("Comments not entered");
		}

		if (cmnLib.clickOnWebElement(adjustAssets.btnOK) && cmnLib.waitForElementToBeInvisible(adjustAssets.btnOK)) {
			rpt.generateReport("", "Click OK button", "", "", "OK button must be clicked and window must be closed",
					"Clicked OK button and window closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "", "OK button must be clicked and window must be closed",
					"Either not clicked OK button or window not closed", "Failed", "", true);
			Assert.fail("Either not clicked OK button or window not closed");
		}
	}

	@Then("^Verify \"([^\"]*)\" confirmation message and Click OK$")
	public void verify_confirmation_message_and_Click_OK(String message) throws Throwable {
		if (cmnLib.waitForElementToBeVisible(adjustAssets.msgConfirmation)
				&& adjustAssets.msgConfirmation.getText().contains(message)) {
			rpt.generateReport("", "Verify Confirmation Message", "", "", message + " message must be displayed",
					message + " message displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Confirmation Message", "", "", message + " message must be displayed",
					message + " message not displayed", "Passed", "", true);
			Assert.fail(message + " message not displayed");
		}

		if (cmnLib.clickOnWebElement(adjustAssets.btnOKConfirmation)
				&& cmnLib.waitForElementToBeInvisible(adjustAssets.btnOKConfirmation)) {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Confirmation window must be closed",
					"Clicked OK button and Confirmation window closed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click OK button", "", "",
					"OK button must be clicked and Confirmation window must be closed",
					"Either not clicked OK button or Confirmation window not closed", "Failed", "", true);
			Assert.fail("Either not clicked OK button or Confirmation window not closed");
		}
	}

	@Then("^Click \"([^\"]*)\" from Results to view Asset Details$")
	public void click_Asset_Number_from_Results_to_view_Asset_Details(String assetNumber) throws Throwable {
		TimeUnit.SECONDS.sleep(3);
		if (adjustAssets.selectAssetFromResults(assetNumber, ResultsSelect.AssetNumber)
				&& cmnLib.verifyHeader("Inquire Assets")) {
			rpt.generateReport("", "Click Asset Number in results", "", assetNumber,
					"Asset Number must be clicked and Inquire Assets page must be displayed",
					"Clicked Asset Number and Inquire Assets page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Asset Number in results", "", assetNumber,
					"Asset Number must be clicked and Inquire Assets page must be displayed",
					"Either not clicked Asset Number or Inquire Assets page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked Asset Number or Inquire Assets page not displayed");
		}
	}

	@Then("^Verify Depreciate is \"([^\"]*)\" in Financials tab$")
	public void verify_Depreciate_is_in_Financials_tab(String status) throws Throwable {
		String strDepreciate = assetInquiry.getDepreciateValue();
		if (strDepreciate != null && strDepreciate.equalsIgnoreCase(status)) {
			rpt.generateReport("", "Verify Depreciate value in Financial Details section", "", "",
					"Depreciate value must be: " + status, "Depreciate: " + strDepreciate, "Passed", "", true);
		} else {
			rpt.generateReport("", "Validate Depreciate value in Financial Details section", "", "",
					"Depreciate value must be: " + status, "Depreciate value not as expected: " + strDepreciate,
					"Failed", "", true);
			Assert.fail("Depreciate value verification failed");
		}
	}

	@Then("^Click Done button to navigate to \"([^\"]*)\" page$")
	public void click_Done_button_to_navigate_to_page(String hdrText) throws Throwable {
		if (cmnLib.clickOnWebElement(assetInquiry.btnDone) && cmnLib.verifyHeader(hdrText)) {
			rpt.generateReport("", "Click Done button", "", "",
					"Done button must be clicked and " + hdrText + " page must be displayed",
					"Clicked Done button and " + hdrText + " page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Done button", "", "",
					"Done button must be clicked and " + hdrText + " page must be displayed",
					"Either not clicked Done button or " + hdrText + " page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked Done button or " + hdrText + " page not displayed");
		}
	}
}
