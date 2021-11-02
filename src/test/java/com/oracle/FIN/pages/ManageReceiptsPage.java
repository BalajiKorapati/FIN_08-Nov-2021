package com.oracle.FIN.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.steps.ReportingSteps;

public class ManageReceiptsPage extends BrowserDriverUtil {

	private Logger log = Logger.getLogger(ManageReceiptsPage.class.getName());
	Common_Library cmnLib = ReportingSteps.cmnLib;

	@FindBy(xpath = "//label[text()='Receipt Number']/parent::td/following-sibling::td//input")
	public WebElement txtReceiptNumber;

	@FindBy(xpath = "//label[text()='Receipt Number']/parent::td/following-sibling::td//select")
	public WebElement selReceiptNumberOperator;

	@FindBy(xpath = "//label[text()='Customer Name']/parent::td/following-sibling::td//input")
	public WebElement txtCustomerName;

	@FindBy(xpath = "//label[text()='Write-Off Amount']/parent::td/following-sibling::td//input")
	public WebElement txtWriteOffAmount;

	@FindBy(xpath = "//label[text()='Receivables Activity']/parent::td/following-sibling::td//select")
	public WebElement selReceivablesActivity;

	@FindBy(css = "button[id$='q1::search']")
	public WebElement btnSearch;

	@FindBy(css = "table[summary='Search Results']>tbody")
	public WebElement tblBdySearchResults;

	@FindBy(css = "button[id$='ap1:AT1:cb12']")
	public WebElement btnOK;

	@FindBy(xpath = "//button[text()='Save']")
	public WebElement btnSave;

	@FindBy(css = "table[summary='Receipt Details: Application']>tbody>tr")
	public List<WebElement> tblRows;

	public ManageReceiptsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Manage Receipts Page is Initialized...");
	}
	
	public boolean clickReceiptsInResults(String receiptNumber) {
		boolean flag = false;
		try {
			cmnLib.waitForPageLoaded();
			List<WebElement> eleReceipts = getDriver()
					.findElements(By.xpath("//table[@summary='Search Results']/tbody/tr/td[3]//a"));
			for (WebElement ele : eleReceipts) {
				String text = ele.getText();
				if (text.equals(receiptNumber)) {
					ele.click();
					flag = true;
					log.info("Clicked Receipt Number in results");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to click Receipt Number in results");
		}

		return flag;
	}

	public int getColumnNumberFromResults(String columnName) {

		int colNum = 0;

		try {
			List<WebElement> headers = getDriver()
					.findElements(By.xpath("//table[contains(@id,'table3::ch::t')]//tr[2]/th"));
			int iHeadersCount = headers.size();
			log.info("No of headers: " + iHeadersCount);
			for (int i = 1; i <= iHeadersCount; i++) {
				WebElement headerElement = getDriver()
						.findElement(By.xpath("//table[contains(@id,'table3::ch::t')]//tr[2]/th[" + i + "]/div/span"));
				cmnLib.scrollTillVisibilityOfElement(headerElement);
				String headerName = headerElement.getText();
				log.info("Header Name: " + headerName);
				if (headerName.equalsIgnoreCase(columnName)) {
					colNum = i;
					log.info("Column Number for " + columnName + " is " + i);
					break;
				}
			}

		} catch (Exception e) {
			log.info("Unable to get Column Number from results for " + columnName);
			e.printStackTrace();
		}
		return colNum;
	}

	public String getReceiptDetail(String applicationReference, String columnName) {

		String value = null;

		try {
			List<WebElement> tableRows = tblRows;
			for (int i = 1; i <= tableRows.size(); i++) {
				String strapplicationReference = getDriver()
						.findElement(
								By.xpath("//table[@summary='Receipt Details: Application']/tbody/tr[" + i + "]/td[4]"))
						.getText();
				if (strapplicationReference.equals(applicationReference)) {
					log.info("Invoice Number matched: " + strapplicationReference);
					int colNum = getColumnNumberFromResults(columnName);
					String colValue = getDriver().findElement(By.xpath(
							"//table[@summary='Receipt Details: Application']/tbody/tr[" + i + "]/td[" + colNum + "]//input"))
							.getAttribute("value");
					log.info("Column value for " + columnName + " is " + colValue);
					value = colValue;
					log.info("Fetched Receipt detail: " + colValue);
					break;
				}
			}

		} catch (Exception e) {
			log.info("Unable to edit Receipt Detail");
			e.printStackTrace();
		}

		return value;

	}

}
