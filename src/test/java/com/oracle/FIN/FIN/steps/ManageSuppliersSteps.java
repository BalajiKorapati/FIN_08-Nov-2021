package com.oracle.FIN.FIN.steps;

import org.junit.Assert;

import com.oracle.FIN.FIN.pages.ManageSuppliersPage;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.en.Then;
import com.oracle.acs.util.report.ReportGeneration;

public class ManageSuppliersSteps {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	ManageSuppliersPage manageSuppliers = new ManageSuppliersPage();

	@Then("^Click Edit button to Edit Supplier details$")
	public void click_Edit_button_to_Edit_Supplier_details() throws Throwable {
		if (cmnLib.clickOnWebElement(manageSuppliers.btnEdit) && cmnLib.verifyHeader("Edit Supplier:")) {
			rpt.generateReport("", "Click Edit button", "", "", "Edit Supplier page must be displayed",
					"Edit Supplier page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Edit button", "", "", "Edit Supplier page must be displayed",
					"Either not clicked on Edit button or Edit Supplier page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Edit button or Edit Supplier page not displayed");
		}
	}

	@Then("^Enter Inactive Date \"([^\"]*)\"$")
	public void enter_Inactive_Date(String inactiveDate) throws Throwable {
		if (cmnLib.enterDataInTextBox(manageSuppliers.txtInactiveDate, inactiveDate, true)) {
			rpt.generateReport("", "Enter Inactive Date", "", inactiveDate, "Inactive Date must be entered",
					"Entered Inactive Date", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Inactive Date", "", inactiveDate, "Inactive Date must be entered",
					"Inactive Date not entered", "Failed", "", true);
			Assert.fail("Inactive Date not entered");
		}
	}

	@Then("^Click Submit button to save Supplier changes$")
	public void click_Submit_button_to_save_Supplier_changes() throws Throwable {
		if (cmnLib.clickOnWebElement(manageSuppliers.btnSubmit)) {
			rpt.generateReport("", "Click Submit button", "", "", "Submit button must be clicked",
					"Clicked on Submit button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Submit button", "", "", "Submit button must be clicked",
					"Not clicked on Submit button", "Failed", "", true);
			Assert.fail("Not clicked on Submit button");
		}
	}

	@Then("^Click \"([^\"]*)\" tab$")
	public void click_tab(String tab) throws Throwable {
		if (manageSuppliers.clickTab(tab)) {
			rpt.generateReport("", "Click " + tab + " tab", "", "", tab + " tab must be clicked",
					"Clicked " + tab + " tab", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click " + tab + " tab", "", "", tab + " tab must be clicked",
					tab + " tab not clicked", "Failed", "", true);
			Assert.fail(tab + " tab not clicked");
		}
	}

	@Then("^Select Site \"([^\"]*)\" and Click Edit icon$")
	public void select_Site_and_Click_Edit_icon(String site) throws Throwable {
		if (manageSuppliers.SelectSiteRow(site)) {
			rpt.generateReport("", "Select Site", "", site, "Site must be selected", "Selected Site", "Passed", "",
					true);
		} else {
			rpt.generateReport("", "Select Site", "", site, "Site must be selected", "Site not selected", "Failed", "",
					true);
			Assert.fail("Site not selected");
		}

		if (cmnLib.clickOnWebElement(manageSuppliers.icnEdit) && cmnLib.verifyHeader(site)) {
			rpt.generateReport("", "Click Edit icon", "", "", "Edit page must be displayed", "Edit page displayed",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Click Edit icon", "", "", "Edit page must be displayed",
					"Either not clicked on Edit icon or Edit page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Edit icon or Edit page not displayed");
		}
	}

	@Then("^Click Save and Close button to save Site changes$")
	public void click_Save_and_Close_button_to_save_Site_changes() throws Throwable {
		if (cmnLib.clickOnWebElement(manageSuppliers.btnSaveAndClose)) {
			rpt.generateReport("", "Click Save and Close button", "", "", "Save and Close button must be clicked",
					"Clicked Save and Close button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Save and Close button", "", "", "Save and Close button must be clicked",
					"Not clicked on Save and Close button", "Failed", "", true);
			Assert.fail("Not clicked on Save and Close button");
		}
	}

	@Then("^Verfiy changes saved Confirmation message displayed and Click OK$")
	public void verfiy_changes_saved_Confirmation_message_displayed_and_Click_OK() throws Throwable {
		if (cmnLib.waitForElementToBeVisible(manageSuppliers.msgConfirmationSaved)) {
			rpt.generateReport("", "Verify Confirmation message", "", "", "Confirmation message must be displayed",
					"Confirmation message displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify Confirmation message", "", "", "Confirmation message must be displayed",
					"Confirmation message not displayed", "Failed", "", true);
			Assert.fail("Confirmation message not displayed");
		}

		if (cmnLib.clickOnWebElement(manageSuppliers.btnOKConfirmation)) {
			rpt.generateReport("", "Click Ok button", "", "", "Ok button must be clicked", "Clicked OK button",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Click Ok button", "", "", "Ok button must be clicked", "Not clicked OK button",
					"Failed", "", true);
			Assert.fail("Not clicked OK button");
		}
	}

}
