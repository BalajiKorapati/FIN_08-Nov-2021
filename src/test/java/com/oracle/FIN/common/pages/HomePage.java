package com.oracle.FIN.common.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;

public class HomePage extends BrowserDriverUtil {

	private Logger log = Logger.getLogger(HomePage.class.getName());
	Common_Library cmnLib = new Common_Library();

	@FindBy(xpath = "//table[contains(@id,'pt1:_UISmmp2')]")
	public WebElement tblNavigatorOptions;

	@FindBy(xpath = "//a[@title='Navigator']")
	public WebElement iconNavigator;

	@FindBy(xpath = "//*[contains(@id,'pt1:_UIShome::icon')]")
	public WebElement HomePage_HomeIcon;

	@FindBy(xpath = "//a[@id='_FOpt1:_UIShome']")
	public WebElement HomeIcon;

	@FindBy(css = "a[id*='UIShome']")
	public WebElement iconHome;

	@FindBy(xpath = "//a[text()='Me']")
	public WebElement lnkMe;

	@FindBy(xpath = "//a[text()='My Client Groups']")
	public WebElement MyClientGroupsTab;

	@FindBy(xpath = "//a[text()='Person Management']")
	public WebElement PersonManagement;

	@FindBy(xpath = "//a[text()='New Person']")
	public WebElement NewPersonLink;

	@FindBy(xpath = "//a[@title='Navigator']")
	public WebElement NavigatorIcon;

	@FindBy(css = "a[id$='nvcil2']")
	public WebElement nvrMenuIcnHome;

	@FindBy(xpath = "//div[@title='My Client Groups']")
	public WebElement secMyClientGroups;

	@FindBy(xpath = "//a[@title='Workforce Structures']")
	public WebElement lnkWorkforceStructures;

	/*@FindBy(css = "div[title='Payables']")
	public WebElement navHdrPayables;*/
	
	@FindBy(xpath = "//a[text()='Payables']")
	public WebElement navHdrPayables;
	
	@FindBy(css = "div[title='Fixed Assets']")
	public WebElement navHdrFixedAssets;
	
	@FindBy(css = "div[title='Receivables']")
	public WebElement navHdrReceivables;
	
	@FindBy(css = "div[title='Procurement']")
	public WebElement navHdrProcurement;

	@FindBy(css = "div[title='Tools']")
	public WebElement navHdrTools;

	/*@FindBy(css = "a[title='Invoices']")
	public WebElement lnkInvoices;*/
	
	@FindBy(xpath = "//a[text()='Invoices']")
	public WebElement lnkInvoices;
	
	@FindBy(xpath="//a[@title='Payments']")
	public WebElement lnkPayments;
	
	@FindBy(xpath="//img[@title='Tasks']")
	public WebElement imgTasks;
	
	
	@FindBy(xpath="//a[contains(@id,'_itemNode_fixed_assets_additions')]/span[text()='Assets']")
	public WebElement lnkAssets;
	
	@FindBy(css = "a[title='Billing']")
	public WebElement lnkBilling;
	
	@FindBy(css = "a[title='Accounts Receivable']")
	public WebElement lnkAccountsReceivable;
	
	@FindBy(css = "a[title='Purchase Requisitions']")
	public WebElement lnkPurchaseRequisitions;

	@FindBy(css = "a[title='Scheduled Processes']")
	public WebElement lnkScheduledProcesses;
	
	@FindBy(xpath = "//span[text()='Asset Inquiry']")
	public WebElement lnkAssetInquiry;
	

	@FindBy(xpath="//span[text()='Cash Management']")
	public WebElement navHdrCashManagement;
	
	@FindBy(xpath="//span[text()='Bank Statements and Reconciliation']")
	public WebElement lnkBankStatementsReconciliation;
	
	@FindBy(xpath="//span[text()='Purchase Requisitions']")
	public WebElement lnkPurchaseRequistions;
	
	@FindBy(xpath="//span[text()='Purchase Agreements']")
	public WebElement lnkPurchaseAgreements;
	
	@FindBy(xpath="//span[text()='Suppliers']")
	public WebElement lnkSuppliers;

	
	@FindBy(xpath="//*[@id='clusters-right-nav']")
	public WebElement rightNavigator;
	
	public void clickOnNavigationIcon() throws InterruptedException {
		cmnLib.clickOnWebElement(NavigatorIcon);
		log.info("Navigation Icon is clicked");
		TimeUnit.SECONDS.sleep(5);
	}

	public HomePage() {
		PageFactory.initElements(getDriver(), this);
		log.info("HomePage is initialized...");
	}

	public String getUserNameFromHomePage() {
		String strUserName = null;
		try {
			getDriver().findElement(By.xpath("//img[contains(@id,'pt1:_UIScmil2u::icon')]")).isDisplayed();
			strUserName = getDriver().findElement(By.xpath("//img[contains(@id,'pt1:_UIScmil2u::icon')]"))
					.getAttribute("title");
			System.out.println("UserName captured from HomePage: " + strUserName);
		} catch (Exception e) {
			System.out.println("UserName could not be captured from HomePage");
		}
		return strUserName;
	}

	public boolean clickOnHomeIcon() {

		boolean flag = false;
		try {
			iconHome.click();
			log.info("Clicked on Home icon");
			flag = true;
		} catch (Exception e) {
			log.info("Unable to click Home icon");
			e.printStackTrace();
		}
		return flag;
	}

}
