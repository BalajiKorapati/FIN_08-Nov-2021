package com.oracle.FIN.FIN.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.FIN.common.pages.HomePage;
import com.oracle.acs.util.report.ReportGeneration;

import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
public class CreateExternalCustomerPage extends BrowserDriverUtil {
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	private Logger log = Logger.getLogger(HomePage.class.getName());
	Common_Library cmnLib = new Common_Library();

	@FindBy(xpath = "//img[@title='Tasks']")
	public WebElement imgTasks;

	@FindBy(xpath = "//a[text()='Create Customer']")
	public WebElement lnkCreateCustomer;
	
	@FindBy(xpath = "//label[(text()='Name')]//..//..//input")
	public WebElement txtName;

	@FindBy(xpath = "//label[(text()='Account Description')]//..//..//input")
	public WebElement txtAccountDescription;

	@FindBy(xpath = "//label[(text()='Account Type')]//..//..//select")
	public WebElement ddAccountType;

	@FindBy(xpath = "//label[(text()='Account Address Set')]//..//..//input")
	public WebElement txtAccountAddressSet;

	@FindBy(xpath = "//label[(text()='Site Name')]//..//..//input")
	public WebElement txtSiteName;

	@FindBy(xpath = "//label[(text()='Address Line 1')]//..//..//input")
	public WebElement txtAddressLine1;

	@FindBy(xpath = "//a[(text()='Actions')]")
	public WebElement lnkActions;

	@FindBy(xpath = "//td[(text()='Add Row')]")
	public WebElement lnkAddRow;

	@FindBy(xpath = "//button[text()='O']")
	public WebElement btnWarningOK;

	@FindBy(xpath = "//label[(text()='Organization Name')]//..//..//input")
	public WebElement txtOrganizationName;

	@FindBy(xpath = "//button[(text()='Search')]")
	public List<WebElement> btnSearch;

	@FindBy(xpath = "//span[contains(@id,'commandLink1')]//span[@title]")
	public List<WebElement> lnkAccountNumber;

	@FindBy(xpath = "//label[(text()='Purpose')]//..//select")
	public List<WebElement> ddPurpose;
	
	@FindBy(id ="_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:cupanel1:pt_r3:0:AT1:_ATp:table1:0:SiteUseCode::content")
	public WebElement dPurpose;
	
	@FindBy(xpath = "//input[contains(@id,'billToSiteUseIdId::content')]//..//a")
	public List<WebElement> txtBillToSite;

	@FindBy(xpath = "//div[contains(@id,'cupanelTabbed1::tabh::cbc')]//a[text()='Communication']")
	public WebElement lnkCommunication;

	@FindBy(xpath = "//button[text()='Edit Contacts']")
	public WebElement btnEditContacts;

	@FindBy(xpath = "//a[(text()='Actions')]")
	public List<WebElement> lnkEditContactActions;
	
	@FindBy(xpath = "//td[(text()='Create Contact')]")
	public WebElement lnkCreateContacts;

	@FindBy(xpath = "//table[contains(@id,'AccountContactInfoEntry')]//label[(text()='First Name')]//..//..//input")
	public WebElement txtFirstName;

	@FindBy(xpath = "//button[@title='OK']")
	public WebElement btnCreateContactsOk;
	
	@FindBy(xpath = "//td[(text()='Create')]")
	public List<WebElement> lnkCreate;

	@FindBy(xpath = "//label[(text()='Phone')]//..//..//input")
	public WebElement txtPhoneNumber;
	
	@FindBy(xpath = "//td[contains(@id,'ContactPointInfoEntryDialog')]//button[text()='O']")
	public WebElement btnCreateContactPointOk;
	
	@FindBy(xpath = "//table//td[text()='Account Contact Responsibilities']//..//..//a[(text()='Actions')]")
	public WebElement lnkResponsibilityActions;

	@FindBy(xpath = "//label[(text()='Responsibility Type')]//..//..//select")
	public WebElement ddResponsibilityType;
	
	@FindBy(xpath = "//button[contains(text(),'ave and Close')]")
	public WebElement btnSaveAndClose;

	@FindBy(xpath = "//div[contains(@id,'cupanelTabbed1::tabh::cbc')]//a[text()='Profile History']")
	public WebElement lnkProfileHistory;

	@FindBy(xpath = "//td[(text()='Correct Record')]")
	public WebElement lnkCorrectRecord;
	
	@FindBy(xpath = "//label[text()='Enable']")
	public WebElement checkboxEnable;

	@FindBy(xpath="//table[contains(@id,'bfbChangePopup')]//button[text()='Yes']")
	public WebElement btnWarningYes;
	@FindBy(xpath = "//label[(text()='Bill Level')]//..//..//select")
	public WebElement ddBillLevel;

	@FindBy(xpath = "//label[(text()='Bill Type')]//..//..//select")
	public WebElement ddBillType;

	@FindBy(xpath = "//label[(text()='Payment Terms')]//..//..//a[contains(@id,'bfbtermname::lovIconId')]")
	public WebElement PaymentTermsArrow;
	
	@FindBy(xpath ="//a[(text()='Search...')]")
	public WebElement lnkSearch;
	
	@FindBy(xpath ="//div[contains(@id,'afrLovInternalQueryId:criteria')]//label[(text()='Payment Terms')]//..//..//input")
	public WebElement txtPaymentTerms;
	
	@FindBy(xpath ="//div[contains(@id,'bfbtermname_afrLovInternalTableId')]//tr[@_afrrk='0']")
	public WebElement selectPaymentTerms;
	
	@FindBy(xpath ="//button[contains(@id,'bfbtermname::lovDialogId::ok')]")
	public WebElement btnOK;
	
	public CreateExternalCustomerPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("ManageTransactionsPage is initialized...");
	}

	public void Click_Actions() throws Throwable {
		if (cmnLib.clickOnWebElement(lnkActions)) {
			rpt.generateReport("", "Click Actions Link", "", "", "Actions Link must be clicked ",
					"Clicked on Actions Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Actions Link", "", "", "Actions Link must be clicked ",
					"Not clicked on Actions Link", "Failed", "", true);
			Assert.fail("Not clicked on Actions Link ");
		}
	}

	public void Select_AddRow() throws Throwable {
		if (cmnLib.clickOnWebElement(lnkAddRow)) {
			rpt.generateReport("", "Click Add Row Link", "", "", "Add Row Link must be clicked ",
					"Clicked on Add Row Link", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Add Row Link", "", "", "Add Row Link must be clicked ",
					"Not clicked on Add Row Link", "Failed", "", true);
			Assert.fail("Not clicked on Add Row Link ");
		}
	}

	public void select_Purpose(String Purpose) throws Throwable {
		cmnLib.scrollDownByJSE();
		Thread.sleep(2000);
		
		if (cmnLib.selectDropdownBy(ddPurpose.get(0), Purpose, DropDownSelectBy.VisibleText)) {
			rpt.generateReport("", "Enter Purpose", "", "Purpose: " + Purpose, "Purpose must be Entered ",
					"Entered Purpose ", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Purpose", "", "Purpose: " + Purpose, "Purpose must be Entered ",
					"Not Entered Purpose ", "Failed", "", true);
			Assert.fail("Not Entered Purpose");
		}

	}

	public void select_BillToSite() throws Throwable {
		if (cmnLib.clickOnWebElement(txtBillToSite.get(0))) {
			rpt.generateReport("", "Select BillToSite", "", " ", "BillToSite must be Selected ", "Entered BillToSite ",
					"Passed", "", true);
		} else {
			rpt.generateReport("", "Select BillToSite", "", "", "BillToSite must be Selected ",
					"Not Selected BillToSite ", "Failed", "", true);
			Assert.fail("Not Selected BillToSite");
		}
		Actions actions = new Actions(getDriver());
		actions.sendKeys(Keys.ARROW_DOWN).build().perform();
		log.info("Pressed Arrow down key");
		cmnLib.pressEnterKey();
	}
	

}