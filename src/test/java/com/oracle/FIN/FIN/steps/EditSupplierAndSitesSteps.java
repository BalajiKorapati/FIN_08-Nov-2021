package com.oracle.FIN.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.oracle.FIN.FIN.pages.ManageSupplierPage;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.pages.HomePage;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import com.oracle.acs.util.report.ReportGeneration;

public class EditSupplierAndSitesSteps {
	Common_Library cmnLib = ReportingSteps.cmnLib;
	HomePage hmPage = new HomePage();
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	ManageSupplierPage manageSup= new ManageSupplierPage();

	@Then("^User navigates to Manage Supplier page$")
	public void user_navigates_to_Manage_Supplier_page() throws Throwable {
		if(!(cmnLib.waitForElementToBeVisible(hmPage.imgTasks, 20) && cmnLib.clickOnWebElement(hmPage.imgTasks))) {
			rpt.generateReport("", "Click Suppliers link", "", "",
					"Suppliers link must be clicked and Suppliers page must be displayed",
					"Either not clicked on Suppliers link or Suppliers page not displayed",
					"Failed", "", true);
			Assert.fail("Either not clicked on Suppliers link or Suppliers page not displayed");
		}

		if(!(cmnLib.waitForElementToBeVisible(manageSup.lnkManageSuppliers, 20) && cmnLib.clickOnWebElement(manageSup.lnkManageSuppliers))) {
			rpt.generateReport("", "Click Manage Suppliers link", "", "",
					"Manage Suppliers link must be clicked",
					"Failed to click on Manage suppliers link",
					"Failed", "", true);
			Assert.fail("Failed to click on Manage suppliers link");
		}


	}

	@Then("^Search for the Supplier \"([^\"]*)\"$")
	public void search_for_the_Supplier(String SupplierName) throws Throwable {


		if(!(cmnLib.waitForElementToBeVisible(manageSup.txtKeywords, 20) && cmnLib.enterDataInTextBox(manageSup.txtKeywords,SupplierName,true))) {
			rpt.generateReport("", "Enter Supplier name for search", "", "",
					"Supplier name should be entered for search",
					"Failed to open Manage supplier page or Failed to enter Supplier name for search",
					"Failed", "", true);
			Assert.fail("Failed to open Manage supplier page or Failed to enter Supplier name for search");
		}

		cmnLib.clickOnWebElement(manageSup.btnSearch);
		
		if(cmnLib.verifySearchedRecordExists(SupplierName)) {
			rpt.generateReport("", "Requisition search results", "", "",
					"Requisition number should appear in searched results",
					"Requisition number appeared in searched results",
					"Passed", "", true);

			cmnLib.clickOnLinkText(SupplierName);
		}else {
			rpt.generateReport("", "Requisition search results", "", "",
					"Requisition number should appear in searched results",
					"Requisition number did not appear in searched results",
					"Failed", "", true);
			Assert.fail("Requisition number did not appear in searched results");
		}



	}

	@Then("^Click on Edit$")
	public void click_on_Edit() throws Throwable {

		if(!(cmnLib.waitForElementToBeVisible(manageSup.btnEdit, 20) && cmnLib.clickOnWebElement(manageSup.btnEdit))){
			rpt.generateReport("", "Edit Supplier page", "", "",
					"Supplier page should appear and  edit button should be clicked",
					"Failed to open  supplier page or Failed to click on Edit button ",
					"Failed", "", true);
			Assert.fail("Failed to open  supplier page or Failed to click on Edit button ");
		}

	}

	@Then("^Edit Site details \"([^\"]*)\", \"([^\"]*)\" and save and close$")
	public void edit_Site_details_and_save_and_close(String SiteName, String NewSiteName) throws Throwable {

		if(!(cmnLib.waitForElementToBeVisible(manageSup.tabSites, 20) && cmnLib.clickOnWebElement(manageSup.tabSites))){
			rpt.generateReport("", "Site Name", "", "",
					"Site option should be clicked",
					"Failed to click on Sites",
					"Failed", "", true);
			Assert.fail("Failed to click on Sites");
		}

		//cmnLib.waitForPageLoaded();
		TimeUnit.SECONDS.sleep(3);

		if(!cmnLib.clickOnLinkText(SiteName)) {
			rpt.generateReport("", "Site Name", "", "",
					"Site name should be selected",
					"Failed to select the Sites or Site doesn't exisits",
					"Failed", "", true);
			Assert.fail("Failed to select the Sites or Site doesn't exisits");
		}

		if(cmnLib.waitForElementToBeVisible(manageSup.txtSite, 20)){
			if(!cmnLib.enterDataInTextBox(manageSup.txtSite, NewSiteName, true)) {
				rpt.generateReport("", "Site Name", "", "",
						"Site name should be entered",
						"Failed to enter Site name",
						"Failed", "", true);
				Assert.fail("Failed to enter Site name");
			}else {
				rpt.generateReport("", "Site Name", "", "Site name :"+NewSiteName,
						"Site name should be entered",
						"Site name is entered",
						"Passed", "", true);
			}
		}else {
			Assert.fail("Site Edit page doesn't appear");
		}

		TimeUnit.SECONDS.sleep(5);
		cmnLib.clickOnWebElement(manageSup.btnSaveAndClose);

	}

	@Then("^Handle the confirmation message$")
	public void handle_the_confirmation_message() throws Throwable {
		if(cmnLib.waitForElementToBeVisible(manageSup.msgConfirmation, 30)) {
			rpt.generateReport("", "Confirmation message", "", "",
					"Confirmation message should appear",
					"Confirmation message appeared",
					"Passed", "", true);
			
			cmnLib.clickOnWebElement(manageSup.btnOKConfirmation);
		}else {
			rpt.generateReport("", "Confirmation message", "", "",
					"Confirmation message should appear",
					"Confirmation message did not appear",
					"Failed", "", true);
			Assert.fail("Confirmation message did not appear");
		}


	}

	@Then("^Edit Address \"([^\"]*)\",\"([^\"]*)\"$")
	public void edit_Address(String AddressName, String NewAddressName) throws Throwable {
		if(!(cmnLib.waitForElementToBeVisible(manageSup.tabAddresses, 20) && cmnLib.clickOnWebElement(manageSup.tabAddresses))){
			rpt.generateReport("", "Address Name", "", "",
					"Address option should be clicked",
					"Failed to click on Address",
					"Failed", "", true);
			Assert.fail("Failed to click on Address");
		}

		//cmnLib.waitForPageLoaded();
		TimeUnit.SECONDS.sleep(3);

		if(!cmnLib.clickOnLinkText(AddressName)) {
			rpt.generateReport("", "Address Name", "", "",
					"Address name should be selected",
					"Failed to select the Address or Address doesn't exisits",
					"Failed", "", true);
			Assert.fail("Failed to select the Address or Address doesn't exisits");
		}

		if(cmnLib.waitForElementToBeVisible(manageSup.txtAddressName, 20)){
			if(!cmnLib.enterDataInTextBox(manageSup.txtAddressName, NewAddressName, true)) {
				rpt.generateReport("", "Site Name", "", "",
						"Address name should be entered",
						"Failed to enter Address name",
						"Failed", "", true);
				Assert.fail("Failed to enter Address name");
			}else {
				rpt.generateReport("", "Address Name", "", "Address name :"+NewAddressName,
						"Address name should be entered",
						"Address name is entered",
						"Passed", "", true);
			}
		}else {
			Assert.fail("Address Edit page doesn't appear");
		}

		TimeUnit.SECONDS.sleep(3);
		cmnLib.clickOnWebElement(manageSup.btnSaveAndClose);

	
	
	
	}

	@Then("^Submit the changes$")
	public void submit_the_changes() throws Throwable {
		
		if(!cmnLib.clickOnWebElement(manageSup.btnSubmit)) {
			rpt.generateReport("", "Submit", "", "",
					"Submit button should be clicked",
					"Failed to click on Submit button",
					"Failed", "", true);
			Assert.fail("Failed to click on Submit button");
		}
		TimeUnit.SECONDS.sleep(3);
		if(cmnLib.waitForElementToBeVisible(manageSup.msgConfirmation, 30)) {
			rpt.generateReport("", "Confirmation message", "", "",
					"Confirmation message should appear",
					"Confirmation message appeared",
					"Passed", "", true);
			
			cmnLib.clickOnWebElement(manageSup.btnOkSubmit);
		}else {
			rpt.generateReport("", "Confirmation message", "", "",
					"Confirmation message should appear",
					"Confirmation message did not appear",
					"Failed", "", true);
			Assert.fail("Confirmation message did not appear");
		}
	}

}
