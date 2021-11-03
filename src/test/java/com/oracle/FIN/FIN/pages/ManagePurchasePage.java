package com.oracle.FIN.FIN.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;

public class ManagePurchasePage extends BrowserDriverUtil {

	private Logger log = Logger.getLogger(ManagePurchasePage.class.getName());
	Common_Library cmnLib = new Common_Library();

	@FindBy(xpath = "//label[text()='Agreement']//parent::td//parent::tr//td[2]//input")
	public WebElement txtAgreement;

	@FindBy(xpath = "//label[text()='Buyer']//parent::td//parent::tr//td[2]//input")
	public WebElement txtBuyer;

	@FindBy(css = "button[id$='q1::search']")
	public WebElement btnSearch;

	@FindBy(css = "table[summary*='Search Results']>tbody")
	public WebElement tblBdySearchResults;

	@FindBy(xpath = "//a/span[text()='Actions']")
	public WebElement btnActions;

	@FindBy(xpath = "//div[contains(@id,':m2::ScrollBox')]//td[2][text()='Edit']")
	public WebElement optionEdit;

	@FindBy(xpath = "//div[contains(@id,':m2::ScrollBox')]//td[2][text()='Cancel Document']")
	public WebElement optionCancelDocument;

	@FindBy(xpath = "//div[contains(@id,'::ScrollBox')]//td[2][text()='Cancel Requisition']")
	public WebElement optionCancelRequisition;

	@FindBy(xpath = "//*[contains(text(),'This action will create a change order')]")
	public WebElement msgWarningChangeOrder;

	@FindBy(css = "button[id$='yes']")
	public WebElement btnYes;

	// Requisition Page
	@FindBy(xpath = "//label[text()='Reason']//parent::td//parent::tr//td[2]//textarea")
	public WebElement txtReason;

	@FindBy(css = "button[id$='cancelReasonDialog::ok']")
	public WebElement btnOKCancelReq;

	@FindBy(xpath = "//td[contains(text(),'pending processing by the buyer')]")
	public WebElement msgErrorPendingProcessing;

	@FindBy(css = "button[id$='d5::ok']")
	public WebElement btnOKError;

	// Purchase Order Page
	@FindBy(css = "button[id$='d13::ok']")
	public WebElement btnOKCancelDoc;

	// Process Requisitions Page
	@FindBy(css = "button[id$='AddToDocBuilder']")
	public WebElement btnAddToDocBuilder;

	// Add to Document Builder window
	@FindBy(css = "button[id$='AT5:cb1']")
	public WebElement btnOKAddToDocBuilder;

	@FindBy(xpath = "//button[text()='Create']")
	public WebElement btnCreate;
	
	public ManagePurchasePage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Manage Purchase Page is initialized...");
	}

	public boolean clickOrderInResults(String orderNumber) {

		boolean flag = false;
		try {
			cmnLib.waitForPageLoaded();
			List<WebElement> eleOrders = getDriver().findElements(By.xpath(
					"//table[contains(@summary,'Search Results')]/tbody/tr/td[2]/div[1]/table/tbody/tr/td[2]//a"));
			for (WebElement ele : eleOrders) {
				String text = ele.getText();
				if (text.equals(orderNumber)) {
					ele.click();
					flag = true;
					log.info("Clicked Number in results");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to click Number in results");
		}

		return flag;
	}

	public boolean SelectRowInResults(String orderNumber) {

		boolean flag = false;
		try {
			cmnLib.waitForPageLoaded();
			List<WebElement> tblRows = getDriver()
					.findElements(By.xpath("//table[contains(@summary,'Search Results')]/tbody/tr"));
			for (int i = 1; i <= tblRows.size(); i++) {
				String text = getDriver().findElement(By.xpath(
						"//table[contains(@summary,'Search Results')]/tbody/tr[" + i + "]/td[4]//table//td[1]//a"))
						.getText();
				if (text.equals(orderNumber)) {
					getDriver()
							.findElement(
									By.xpath("//table[contains(@summary,'Search Results')]/tbody/tr[" + i + "]/td[1]"))
							.click();
					flag = true;
					log.info("Selected Row in results");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to select row in results");
		}
		return flag;
	}

}
