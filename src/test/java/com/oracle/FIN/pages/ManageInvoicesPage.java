package com.oracle.FIN.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;

public class ManageInvoicesPage extends BrowserDriverUtil {

	private Logger log = Logger.getLogger(ManageInvoicesPage.class.getName());
	Common_Library cmnLib = new Common_Library();

	@FindBy(css = "input[id$='q1:value10::content']")
	public WebElement txtInvoiceNumber;

	@FindBy(css = "button[id$='q1::search']")
	public WebElement btnSearch;

	@FindBy(css = "table[summary='Search Results']>tbody")
	public WebElement tblBodySearchResults;

	// Invoice Details
	@FindBy(css = "div[title='Invoice Details']")
	public WebElement hdrInvoiceDetails;

	@FindBy(xpath = "//a[text()='Actions']")
	public WebElement drpdwnActions;

	@FindBy(xpath = "//td[contains(text(),'invoice will be canceled')]")
	public WebElement msgWarning;

	@FindBy(css = "button[id$='cb40']")
	public WebElement btnOK;

	@FindBy(css = "a[id$=':cl1']")
	public WebElement lnkInvoiceValidationStatus;

	@FindBy(xpath = "//label[text()='Invoice Amount']/parent::td/following-sibling::td//span")
	public WebElement invoiceAmount;

	@FindBy(xpath = "//a/span[text()='Pay in Full']")
	public WebElement btnPayInFull;

	@FindBy(css = "button[id$='pifbtn1']")
	public WebElement btnSubmitPayInFull;

	@FindBy(css = "input[id$='bankAccountNamePIFId::content']")
	public WebElement txtBankAccount;

	@FindBy(css = "input[id$='paymentProfileNameId::content']")
	public WebElement txtPaymentProcessProfile;

	@FindBy(css = "input[id$='paymentDocumentNameId::content']")
	public WebElement txtPaymentDocument;

	@FindBy(css = "div[id*='ta1::db']>table>tbody>tr")
	public List<WebElement> tblRows;
	
	@FindBy(xpath = "//div[contains(text(),'Payment') and contains(text(),'has been created')]")
	public WebElement msgConfirmationPayment;
	
	@FindBy(css = "button[id$='msgDlg::cancel']")
	public WebElement btnOKConfirmationPayment;

	public ManageInvoicesPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("ManageInvoicesPage is initialized...");
	}

	public boolean clickInvoiceNumber(String invoiceNumber) {

		boolean flag = false;
		try {
			List<WebElement> eleInvoices = getDriver()
					.findElements(By.xpath("//table[@summary='Search Results']/tbody/tr/td[2]//a"));
			for (WebElement ele : eleInvoices) {
				String text = ele.getText();
				if (text.equals(invoiceNumber)) {
					ele.click();
					flag = true;
					log.info("Clicked Invoice Number in results");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to click Invoice Number in results");
		}

		return flag;
	}

	public boolean selectRowInInvoiceResults(String invoiceNumber) {

		boolean flag = false;
		try {
			List<WebElement> invoiceRows = getDriver()
					.findElements(By.xpath("//table[@summary='Search Results']/tbody/tr"));
			for (int i = 1; i <= invoiceRows.size(); i++) {
				String strInvNum = getDriver()
						.findElement(By.xpath("//table[@summary='Search Results']/tbody/tr[" + i + "]/td[2]//a"))
						.getText();
				if (strInvNum.equalsIgnoreCase(invoiceNumber)) {
					getDriver().findElement(By.xpath("//table[@summary='Search Results']/tbody/tr[" + i + "]/td[1]"))
							.click();
					flag = true;
					log.info("Selected Row in Invoice Results");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to Select Row in Invoice Results");
		}

		return flag;
	}

	public int getColumnNumberFromResults(String columnName) {

		int colNum = 0;

		try {
			List<WebElement> headers = getDriver()
					.findElements(By.xpath("//table[contains(@id,'ta1::ch::d2')]//tr[2]/th"));
			int iHeadersCount = headers.size();
			log.info("No of headers: " + iHeadersCount);
			for (int i = 1; i <= iHeadersCount; i++) {
				WebElement headerElement = getDriver()
						.findElement(By.xpath("//table[contains(@id,'ta1::ch::d2')]//tr[2]/th[" + i + "]/div/span"));
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

	public String getInvoiceDetail(String invoiceNumber, String columnName) {

		String value = null;

		try {
			List<WebElement> tableRows = tblRows;
			for (int i = 1; i <= tableRows.size(); i++) {
				String strInvNum = getDriver()
						.findElement(By.xpath("//div[contains(@id,'ta1::db')]/table/tbody/tr[" + i + "]/td[2]"))
						.getText();
				if (strInvNum.equals(invoiceNumber)) {
					log.info("Invoice Number matched: " + strInvNum);
					int colNum = getColumnNumberFromResults(columnName);
					String colValue = getDriver().findElement(By.xpath("//div[contains(@id,'ta1::db')]/table/tbody/tr["
							+ i + "]/td[3]/div/table/tbody/tr/td[" + colNum + "]")).getText();
					log.info("Column value for " + columnName + " is " + colValue);
					value = colValue;
					log.info("Fetched Invoice detail: " + colValue);
					break;
				}
			}

		} catch (Exception e) {
			log.info("Unable to edit Invoice Detail");
			e.printStackTrace();
		}

		return value;

	}

}
