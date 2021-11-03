package com.oracle.FIN.FIN.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.pages.HomePage;

public class ManageTransactionsPage extends BrowserDriverUtil {

	private Logger log = Logger.getLogger(HomePage.class.getName());
	Common_Library cmnLib = new Common_Library();

	@FindBy(xpath = "//img[@title='Tasks']")
	public WebElement imgTasks;

	@FindBy(xpath = "//a[text()='Manage Transactions']")
	public WebElement lnkManageTransactions;

	@FindBy(xpath = "//label[contains(text(),'Transaction Source')]//..//input")
	public WebElement txtTransactionSource;

	@FindBy(xpath = "//label[contains(text(),'Transaction Number')]//..//input")
	public WebElement txtTransactionNumber;

	@FindBy(xpath = "//label[contains(text(),'Transaction Date')]//..//input[not(@type='hidden')]")
	public WebElement txtTransactionDate;

	@FindBy(xpath = "//label[contains(text(),'Bill-to Customer')]//..//input")
	public WebElement txtBillToCustomer;

	@FindBy(xpath = "//button[text()='Search']")
	public WebElement btnSearch;

	@FindBy(xpath = "//table[@summary='Search Results']//tr[1]//td[3]//a")
	public WebElement lnkTransactionNumber;

	@FindBy(xpath = "//a[text()='Actions']")
	public WebElement lnkActions;

	@FindBy(xpath = "//td[text()='Duplicate']")
	public WebElement lnkDuplicate;

	@FindBy(xpath = "//span[text()='Complete and Review']")
	public WebElement btnCompleteAndReview;

	@FindBy(xpath = "//button[contains(@id,'dialogCancel::yes')]")
	public WebElement btnWarningYes;

	@FindBy(xpath = "//label[contains(text(),'Status')]//..//..//a")
	public WebElement getStatus;

	@FindBy(xpath = "//span[text()='Save']")
	public WebElement btnSave;

	@FindBy(xpath = "//a[text()='Actions']")
	public WebElement drpdwnActions;

	@FindBy(xpath = "//h1[contains(text(),'Review Transaction')]")
	public WebElement hdrReveiwTransaction;

	@FindBy(css = "table[summary='Transaction Activities']")
	public WebElement tblTransactionActivites;

	@FindBy(css = "table[summary='Search Results']>tbody")
	public WebElement tblBodySearchResults;

	@FindBy(css = "table[summary='Transaction Activities']>tbody")
	public WebElement tblBodyTransactionActivites;
	
	@FindBy(xpath = "//label[text()='Status']//parent::td/following-sibling::td/span/span")
	public WebElement lblTrxActivityStatus;
	
	@FindBy(css = "button[id$='d10::ok']")
	public WebElement btnDone;

	
	@FindBy(css = "div[id$='commandToolbarButton2']")
	public WebElement btnCancel;
	
	


	public ManageTransactionsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("ManageTransactionsPage is initialized...");
	}

	public boolean clickTransactionNumberInResults(String transactionNumber) {

		boolean flag = false;
		try {
			List<WebElement> eleInvoices = getDriver()
					.findElements(By.xpath("//table[@summary='Search Results']/tbody/tr/td[3]//a"));
			for (WebElement ele : eleInvoices) {
				String text = ele.getText();
				if (text.equals(transactionNumber)) {
					ele.click();
					flag = true;
					log.info("Clicked Transaction Number in results");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to click Transaction Number in results");
		}

		return flag;
	}

	public boolean selectTransactionNumberInTransactionActivitiesResults(String transactionNumber) {

		boolean flag = false;
		try {
			cmnLib.waitForElementToBeVisible(tblBodyTransactionActivites);
			List<WebElement> trxNum = getDriver()
					.findElements(By.xpath("//table[@summary='Transaction Activities']/tbody/tr/td[1]"));
			for (WebElement ele : trxNum) {
				String text = ele.getText();
				if (text.equals(transactionNumber)) {
					ele.click();
					flag = true;
					log.info("Clicked Transaction Number in Transaction Activities");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to click Transaction Number in Transaction Activities");
		}

		return flag;
	}

}