package com.oracle.FIN.FIN.steps;

import org.junit.Assert;

import com.oracle.FIN.FIN.pages.CreateExternalCustomerPage;
import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.FIN.common.pages.HomePage;
import com.oracle.FIN.common.steps.CommonAppSteps;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.en.Then;
import com.oracle.acs.util.report.ReportGeneration;

public class CreateExternalCustomerSteps extends BrowserDriverUtil {

	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	CreateExternalCustomerPage createExternalCustomerPage = new CreateExternalCustomerPage();
	CommonAppSteps appSteps = new CommonAppSteps();
	HomePage hmPage = new HomePage();
	Common_Library commonLibrary = new Common_Library();

	@Then("^Click on Create Customer link$")
	public void click_on_Create_Customer_link() throws Throwable {
		if (cmnLib.clickOnWebElement(hmPage.lnkBilling)
				&& cmnLib.waitForElementToBeVisible(createExternalCustomerPage.imgTasks)) {
			rpt.generateReport("", "Click Billing link", "", "",
					"Billing link must be clicked and TransActions Billing page must be displayed",
					"Clicked on Billing link and TransActions Billing page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Billing link", "", "",
					"Billing link must be clicked and TransActions Billing page must be displayed",
					"Either not clicked on Billing link or TransActions Billing page not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Billing link or TransActions Billing page not displayed");
		}
		if (!(cmnLib.clickOnWebElement(createExternalCustomerPage.imgTasks)
				&& cmnLib.waitForElementToBeVisible(createExternalCustomerPage.lnkCreateCustomer))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and createExternalCustomerPage link must be displayed",
					"Either not clicked on Tasks icon or createExternalCustomerPage link not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Tasks icon or createExternalCustomerPage link not displayed");
		}

		if ((cmnLib.clickOnWebElement(createExternalCustomerPage.lnkCreateCustomer))) {
			rpt.generateReport("", "Click CreateCustomer link", "", "",
					"CreateCustomer link must be clicked and CreateCustomer Page must be displayed",
					"Clicked on CreateCustomer link or CreateCustomer page displayed", "Passed", "", true);

		} else {
			rpt.generateReport("", "Click CreateCustomer link", "", "",
					"CreateCustomer link must be clicked and CreateCustomer Page must be displayed",
					"Either not clicked on CreateCustomer link or CreateCustomer page not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on CreateCustomer link or CreateCustomer page not displayed");
		}
	}

	@Then("^Enter at Organization Information\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_at_Organization_Information(String OrganizationName, String AccountDescription,
			String AccountType, String AccountAddressSet, String SiteName) throws Throwable {

		if (cmnLib.enterDataInTextBox(createExternalCustomerPage.txtName, OrganizationName, true)) {
			rpt.generateReport("", "Enter OrganizationName", "", "OrganizationName:" + OrganizationName,
					"OrganizationName must be Entered ", "Entered OrganizationName ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter OrganizationName", "", "OrganizationName: " + OrganizationName,
					"OrganizationName must be Entered ", "Not Entered OrganizationName ", "Failed", "", true);
			Assert.fail("Not Entered OrganizationName");
		}
		if (cmnLib.enterDataInTextBox(createExternalCustomerPage.txtAccountDescription, AccountDescription, true)) {
			rpt.generateReport("", "Enter AccountDescription", "", "AccountDescription: " + AccountDescription,
					"AccountDescription must be Entered ", "Entered AccountDescription ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter AccountDescription", "", "AccountDescription: " + AccountDescription,
					"AccountDescription must be Entered ", "Not Entered AccountDescription ", "Failed", "", true);
			Assert.fail("Not Entered AccountDescription");
		}
		if (cmnLib.selectDropdownBy(createExternalCustomerPage.ddAccountType, AccountType,
				DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Enter AccountType", "", "AccountType: " + AccountType,
					"AccountType must be Entered ", "Entered AccountType ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter AccountType", "", "AccountType: " + AccountType,
					"AccountType must be Entered ", "Not Entered AccountType ", "Failed", "", true);
			Assert.fail("Not Entered AccountType");
		}
		if (cmnLib.enterDataInTextBox(createExternalCustomerPage.txtAccountAddressSet, AccountAddressSet, true)) {
			rpt.generateReport("", "Enter AccountAddressSet", "", "AccountAddressSet: " + AccountAddressSet,
					"AccountAddressSet must be Entered ", "Entered AccountAddressSet ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter AccountAddressSet", "", "AccountAddressSet: " + AccountAddressSet,
					"AccountAddressSet must be Entered ", "Not Entered AccountAddressSet ", "Failed", "", true);
			Assert.fail("Not Entered AccountAddressSet");
		}
		if (cmnLib.enterDataInTextBox(createExternalCustomerPage.txtSiteName, SiteName, true)) {
			rpt.generateReport("", "Enter SiteName", "", "SiteName: " + SiteName, "SiteName must be Entered ",
					"Entered SiteName ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter SiteName", "", "SiteName: " + SiteName, "SiteName must be Entered ",
					"Not Entered SiteName ", "Failed", "", true);
			Assert.fail("Not Entered SiteName");
		}

	}

	@Then("^Click on Actions and Add row \"([^\"]*)\",\"([^\"]*)\"$")
	public void click_on_Actions_and_Add_row(String Purpose1, String Purpose2) throws Throwable {

		createExternalCustomerPage.Click_Actions();
		createExternalCustomerPage.Select_AddRow();
		System.out.println("Purpose1      " + Purpose1);
		createExternalCustomerPage.select_Purpose(Purpose1);
		createExternalCustomerPage.Click_Actions();
		createExternalCustomerPage.Select_AddRow();
		System.out.println("Purpose2    " + Purpose2);
		createExternalCustomerPage.select_Purpose(Purpose2);
		createExternalCustomerPage.select_BillToSite();

	}

	@Then("^Click Save and Close button$")
	public void click_Save_and_Close_button() throws Throwable {
		if (cmnLib.clickOnWebElement(createExternalCustomerPage.btnSaveAndClose)) {
			rpt.generateReport("", "Click Save and Close button", "", "", "Save and Close button must be clicked ",
					"Clicked on Save and Close button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Save and Close button", "", "", "Save and Close button must be clicked ",
					"Not clicked on Save and Close button", "Failed", "", true);
			Assert.fail("Not clicked on Save and Close button ");
		}
	}

	@Then("^Click On Warning OK button$")
	public void click_On_Warning_OK_button() throws Throwable {

		if (cmnLib.clickOnWebElement(createExternalCustomerPage.btnWarningOK)) {
			rpt.generateReport("", "Click WarningOK button", "", "", "WarningOK button must be clicked ",
					"Clicked on WarningOK button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click WarningOK button", "", "", "WarningOK button must be clicked ",
					"Not clicked on WarningOK button", "Failed", "", true);
			Assert.fail("Not clicked on WarningOK button ");
		}

	}

	@Then("^Perform Search for an AccountNumber with \"([^\"]*)\"$")
	public void perform_Search_for_an_AccountNumber_with(String OrganizationName) throws Throwable {

		if (cmnLib.enterDataInTextBox(createExternalCustomerPage.txtOrganizationName, OrganizationName, true)) {
			rpt.generateReport("", "Enter OrganizationName", "", "OrganizationName:" + OrganizationName,
					"OrganizationName must be Entered ", "Entered OrganizationName ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter OrganizationName", "", "OrganizationName: " + OrganizationName,
					"OrganizationName must be Entered ", "Not Entered OrganizationName ", "Failed", "", true);
			Assert.fail("Not Entered OrganizationName");
		}
		if (cmnLib.clickOnWebElement(createExternalCustomerPage.btnSearch.get(0))) {
			rpt.generateReport("", "Click Save and Close button", "", "", "Save and Close button must be clicked ",
					"Clicked on Save and Close button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Save and Close button", "", "", "Save and Close button must be clicked ",
					"Not clicked on Save and Close button", "Failed", "", true);
			Assert.fail("Not clicked on Save and Close button ");
		}
	}

	@Then("^Click on Account Number$")
	public void click_on_Account_Number() throws Throwable {
		if (cmnLib.clickOnWebElement(createExternalCustomerPage.lnkAccountNumber.get(0))) {
			rpt.generateReport("", "Click AccountNumber Link", "", "", "AccountNumber Link must be clicked ",
					"Clicked on AccountNumber Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click AccountNumber Link", "", "", "AccountNumber Link must be clicked ",
					"Not clicked on AccountNumber Link", "Failed", "", true);
			Assert.fail("Not clicked on AccountNumber Link ");
		}
	}

	@Then("^Click Communication$")
	public void click_Communication() throws Throwable {
		if (cmnLib.clickOnWebElement(createExternalCustomerPage.lnkCommunication)) {
			rpt.generateReport("", "Click Communication Link", "", "", "Communication Link must be clicked ",
					"Clicked on Communication Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Communication Link", "", "", "Communication Link must be clicked ",
					"Not clicked on Communication Link", "Failed", "", true);
			Assert.fail("Not clicked on Communication Link ");
		}
	}

	@Then("^Click Edit Contacts$")
	public void click_Edit_Contacts() throws Throwable {
		if (cmnLib.clickOnWebElement(createExternalCustomerPage.btnEditContacts)) {
			rpt.generateReport("", "Click EditContacts button", "", "", "EditContacts button must be clicked ",
					"Clicked on EditContacts button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click EditContacts button", "", "", "EditContacts button must be clicked ",
					"Not clicked on EditContacts button", "Failed", "", true);
			Assert.fail("Not clicked on EditContacts button ");
		}
	}

	@Then("^Click on Actions and select Create Contacts \"([^\"]*)\"$")
	public void click_on_Actions_and_select_Create_Contacts(String FirstName) throws Throwable {
		if (cmnLib.clickOnWebElement(createExternalCustomerPage.lnkEditContactActions.get(0))) {
			rpt.generateReport("", "Click Actions Link", "", "", "Actions Link must be clicked ",
					"Clicked on Actions Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Actions Link", "", "", "Actions Link must be clicked ",
					"Not clicked on Actions Link", "Failed", "", true);
			Assert.fail("Not clicked on Actions Link ");
		}

		if (cmnLib.clickOnWebElement(createExternalCustomerPage.lnkCreateContacts)) {
			rpt.generateReport("", "Click Create Contacts Link", "", "", "Create Contacts Link must be clicked ",
					"Clicked on Create Contacts Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Create Contacts Link", "", "", "Create Contacts Link must be clicked ",
					"Not clicked on Create Contacts Link", "Failed", "", true);
			Assert.fail("Not clicked on Create Contacts Link ");
		}
		if (cmnLib.enterDataInTextBox(createExternalCustomerPage.txtFirstName, FirstName, true)) {
			rpt.generateReport("", "Enter FirstName", "", "FirstName: " + FirstName, "FirstName must be Entered ",
					"Entered FirstName ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter FirstName", "", "FirstName: " + FirstName, "FirstName must be Entered ",
					"Not Entered FirstName ", "Failed", "", true);
			Assert.fail("Not Entered FirstName");
		}
		cmnLib.clickOnWebElement(createExternalCustomerPage.btnCreateContactsOk);
	}

	@Then("^Click On Contact points Actions and select create \"([^\"]*)\"$")
	public void click_On_Contact_points_Actions_and_select_create(String PhoneNumber) throws Throwable {
		if (cmnLib.clickOnWebElement(createExternalCustomerPage.lnkEditContactActions.get(1))) {
			rpt.generateReport("", "Click Actions Link", "", "", "Actions Link must be clicked ",
					"Clicked on Actions Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Actions Link", "", "", "Actions Link must be clicked ",
					"Not clicked on Actions Link", "Failed", "", true);
			Assert.fail("Not clicked on Actions Link ");
		}
		if (cmnLib.clickOnWebElement(createExternalCustomerPage.lnkCreate.get(1))) {
			rpt.generateReport("", "Click Create  Link", "", "", "Create  Link must be clicked ",
					"Clicked on Create  Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Create  Link", "", "", "Create  Link must be clicked ",
					"Not clicked on Create  Link", "Failed", "", true);
			Assert.fail("Not clicked on Create  Link ");
		}
		if (cmnLib.enterDataInTextBox(createExternalCustomerPage.txtPhoneNumber, PhoneNumber, true)) {
			rpt.generateReport("", "Enter PhoneNumber", "", "PhoneNumber: " + PhoneNumber,
					"PhoneNumber must be Entered ", "Entered PhoneNumber ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter PhoneNumber", "", "PhoneNumber: " + PhoneNumber,
					"PhoneNumber must be Entered ", "Not Entered PhoneNumber ", "Failed", "", true);
			Assert.fail("Not Entered PhoneNumber");
		}
		cmnLib.clickOnWebElement(createExternalCustomerPage.btnCreateContactPointOk);
	}

	@Then("^Click On Account Contact Responsibilities Actions and select Add row \"([^\"]*)\",\"([^\"]*)\"$")
	public void click_On_Account_Contact_Responsibilities_Actions_and_select_Add_row(String ResponsibilityType1,
			String ResponsibilityType2) throws Throwable {
		cmnLib.scrollDownByJSE();
		if (cmnLib.clickOnWebElement(createExternalCustomerPage.lnkResponsibilityActions)) {
			rpt.generateReport("", "Click Actions Link", "", "", "Actions Link must be clicked ",
					"Clicked on Actions Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Actions Link", "", "", "Actions Link must be clicked ",
					"Not clicked on Actions Link", "Failed", "", true);
			Assert.fail("Not clicked on Actions Link ");
		}
		createExternalCustomerPage.Select_AddRow();
		if (cmnLib.selectDropdownBy(createExternalCustomerPage.ddResponsibilityType, ResponsibilityType1,
				DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Enter ResponsibilityType1", "", "ResponsibilityType1: " + ResponsibilityType1,
					"ResponsibilityType1 must be Entered ", "Entered ResponsibilityType1 ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter ResponsibilityType1", "", "ResponsibilityType1: " + ResponsibilityType1,
					"ResponsibilityType1 must be Entered ", "Not Entered ResponsibilityType1 ", "Failed", "", true);
			Assert.fail("Not Entered ResponsibilityType1");
		}
		if (cmnLib.clickOnWebElement(createExternalCustomerPage.lnkResponsibilityActions)) {
			rpt.generateReport("", "Click Actions Link", "", "", "Actions Link must be clicked ",
					"Clicked on Actions Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Actions Link", "", "", "Actions Link must be clicked ",
					"Not clicked on Actions Link", "Failed", "", true);
			Assert.fail("Not clicked on Actions Link ");
		}
		createExternalCustomerPage.Select_AddRow();
		if (cmnLib.selectDropdownBy(createExternalCustomerPage.ddResponsibilityType, ResponsibilityType2,
				DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Enter ResponsibilityType2", "", "ResponsibilityType2: " + ResponsibilityType2,
					"ResponsibilityType2 must be Entered ", "Entered ResponsibilityType2 ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter ResponsibilityType2", "", "ResponsibilityType2: " + ResponsibilityType2,
					"ResponsibilityType2 must be Entered ", "Not Entered ResponsibilityType2 ", "Failed", "", true);
			Assert.fail("Not Entered ResponsibilityType2");
		}
	}

	@Then("^Click on Account Information Profile History$")
	public void click_on_Account_Information_Profile_History() throws Throwable {
		if (cmnLib.clickOnWebElement(createExternalCustomerPage.lnkProfileHistory)) {
			rpt.generateReport("", "Click Profile History  Link", "", "", "Profile History  Link must be clicked ",
					"Clicked on Profile History  Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Profile History  Link", "", "", "Profile History  Link must be clicked ",
					"Not clicked on Profile History  Link", "Failed", "", true);
			Assert.fail("Not clicked on Profile History  Link ");
		}
	}

	@Then("^Click on Actions and select Correct Record \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void click_on_Actions_and_select_Correct_Record(String BillLevel, String BillType, String PaymentTerms)
			throws Throwable {
		createExternalCustomerPage.Click_Actions();

		if (cmnLib.clickOnWebElement(createExternalCustomerPage.lnkCorrectRecord)) {
			rpt.generateReport("", "Click Correct Record  Link", "", "", "Correct Record  Link must be clicked ",
					"Clicked on Correct Record  Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Correct Record  Link", "", "", "Correct Record  Link must be clicked ",
					"Not clicked on Correct Record  Link", "Failed", "", true);
			Assert.fail("Not clicked on Correct Record  Link ");
		}

		if (cmnLib.waitForElementToBeVisible(createExternalCustomerPage.checkboxEnable)
				&& cmnLib.clickOnWebElement(createExternalCustomerPage.checkboxEnable)) {
			rpt.generateReport("", "Click Enable checkbox", "", "", "Enable checkbox must be clicked ",
					"Clicked on Enable checkbox", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Enable checkbox", "", "", "Enable checkbox must be clicked ",
					"Not clicked on Enable checkbox", "Failed", "", true);
			Assert.fail("Not clicked on Enable checkbox ");
		}
		cmnLib.clickOnWebElement(createExternalCustomerPage.btnWarningYes);

		if (cmnLib.selectDropdownBy(createExternalCustomerPage.ddBillLevel, BillLevel, DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "select BillLevel", "", "BillLevel: " + BillLevel, "BillLevel must be selected ",
					"selected BillLevel ", "Passed", "", true);
		} else {
			rpt.generateReport("", "select BillLevel", "", "BillLevel: " + BillLevel, "BillLevel must be selected ",
					"Not selected BillLevel ", "Failed", "", true);
			Assert.fail("Not selected BillLevel");
		}
		if (cmnLib.selectDropdownBy(createExternalCustomerPage.ddBillType, BillType, DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "select BillType", "", "BillType: " + BillType, "BillType must be selected ",
					"selected BillType ", "Passed", "", true);
		} else {
			rpt.generateReport("", "select BillType", "", "BillType: " + BillType, "BillType must be selected ",
					"Not selected BillType ", "Failed", "", true);
			Assert.fail("Not selected BillType");
		}
		cmnLib.clickOnWebElement(createExternalCustomerPage.PaymentTermsArrow);
		cmnLib.clickOnWebElement(createExternalCustomerPage.lnkSearch);

		if (cmnLib.enterDataInTextBox(createExternalCustomerPage.txtPaymentTerms, PaymentTerms, true)) {

			cmnLib.clickOnWebElement(createExternalCustomerPage.btnSearch.get(0));
			cmnLib.clickOnWebElement(createExternalCustomerPage.selectPaymentTerms);
			cmnLib.clickOnWebElement(createExternalCustomerPage.btnOK);
			rpt.generateReport("", "Enter PaymentTerms", "", "PaymentTerms: " + PaymentTerms,
					"PaymentTerms must be Entered ", "Entered PaymentTerms ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter PaymentTerms", "", "PaymentTerms: " + PaymentTerms,
					"PaymentTerms must be Entered ", "Not Entered PaymentTerms ", "Failed", "", true);
			Assert.fail("Not Entered PaymentTerms");
		}

	}

}