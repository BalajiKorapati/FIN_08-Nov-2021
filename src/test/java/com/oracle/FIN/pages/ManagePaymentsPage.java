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

public class ManagePaymentsPage extends BrowserDriverUtil {
	
	private Logger log = Logger.getLogger(InvoiceWorkbenchPage.class.getName());
	Common_Library cmnLib = ReportingSteps.cmnLib;
	
	@FindBy(linkText = "Manage Payments")
	public WebElement lnkManagePayments;
	
	@FindBy(xpath = "//label[text()='Payment Number']//parent::td//parent::tr//td[2]//input")
	public WebElement PaymentNumber;

	@FindBy(xpath = "//button[text()='Search']")
	public WebElement Search;
	
	@FindBy(xpath = "//table[@summary='Search Results']/tbody")
	public WebElement table_Results;
	
	@FindBy(xpath = "//a[text()='Actions']")
	public WebElement Actions_button;
	
	@FindBy(xpath = "//td[text()='Void']")
	public WebElement Void;
	
	@FindBy(xpath = "//button[text()='Sub']")
	public WebElement Submit_Void;
	
	@FindBy(xpath = "//div[@title='Search']//parent::td//preceding-sibling::td//a")
	public WebElement Search_Arrow;
	
	@FindBy(xpath="//label[text()='Stop Reason']/parent::td/parent::tr//input")
	public WebElement inpStopReason;
	
	@FindBy(xpath="//tr[contains(@id,'LabelAndMessage4')]/td[2]/span")
	public WebElement txtPaymentStatus;
	
	@FindBy(xpath="//select[contains(@id,'invoiceAction::content')]")
	public WebElement dropInvoiceActions;
	
	@FindBy(xpath="//a/span[text()='ne']")
	public WebElement btnDone;
	
	@FindBy(xpath = "//a[text()='Actions']")
	public WebElement drpdwnActions;
	
	@FindBy(xpath="//a[@title='Expand Search']")
	public WebElement lnkExpandSearch;
	
	@FindBy(xpath="//select[contains(@id,'value60::content')]")
	public WebElement drpPaymentStatus;
	
	public ManagePaymentsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Manage Payments page is initialized...");
	}
	
	
	public boolean selectPaymnetFromResults(String strPaymentNumber) {
		boolean exists = false;
		try {
			List<WebElement> TableRows = getDriver()
					.findElements(By.xpath("//table[@summary='Search Results']/tbody/tr"));
			System.out.println("No Of Rows in Search Results Table: " + TableRows.size());
			if (TableRows.size() > 0) {
				for (int i = 1; i <= TableRows.size(); i++) {
					if (getDriver() .findElement(By.xpath("//table[@summary='Search Results']/tbody/tr[" + i + "]//*[contains(text(), '" + strPaymentNumber + "')]"))
							.isDisplayed()) {
						getDriver().findElement(By.xpath("//table[@summary='Search Results']/tbody/tr["+i+"]//*[contains(text(), '"+strPaymentNumber+"')]")).click();
						exists = true;
						
						log.info("Searched Person Number record exists");
						break;
					}
				}
			} else {
				log.info("TableRows row count is less than zero !!");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Searched Person Number record does not exist");
		}
		return exists;
		
		
		
		
		
		
		
		
		
		/*
		 * boolean flag = false; try { if
		 * (cmnLib.waitForElementToBeVisible(table_Results)) { List<WebElement>
		 * tableRows_SearchResults = getDriver()
		 * .findElements(By.xpath("//table[@summary='Search Results']/tbody/tr")); int
		 * iRowSize = tableRows_SearchResults.size(); System.out.println("No. of Rows: "
		 * + iRowSize); for (WebElement row : tableRows_SearchResults) { WebElement
		 * tableDataPaymentNumber =
		 * row.findElement(By.xpath("td[2]/div[1]/table/tbody/tr/td[1]")); String
		 * PaymnentNumber = tableDataPaymentNumber.getText(); if
		 * (PaymnentNumber.equalsIgnoreCase(strPaymentNumber)) {
		 * row.findElement(By.xpath("td[1]")).click(); flag = true; break; } } }else {
		 * log.info("Results not found"); } } catch (Exception e) { // TODO: handle
		 * exception e.printStackTrace();
		 * log.info("Unable to select Payment in results"); } return flag;
		 */
	}
	
	
	public String getPaymnetDetail(String strPaymentNumber) {
		String returnValue = null;
		try {
			if (cmnLib.waitForElementToBeVisible(table_Results)) {
				List<WebElement> tableRows_SearchResults = getDriver()
						.findElements(By.xpath("//table[@summary='Search Results']/tbody/tr"));
				int iRowSize = tableRows_SearchResults.size();
				System.out.println("No. of Rows: " + iRowSize);
				for (WebElement row : tableRows_SearchResults) {
					WebElement tableDataPaymentNumber = row.findElement(By.xpath("td[2]/div[1]/table/tbody/tr/td[1]"));
					String PaymnentNumber = tableDataPaymentNumber.getText();
					if (PaymnentNumber.equalsIgnoreCase(strPaymentNumber)) {
						returnValue = row.findElement(By.xpath("td[2]/div[1]/table/tbody/tr/td[3]")).getText();
						break;
					}
				}
			}else {
				log.info("Results not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to get Payment Detail in results");
		}
		return returnValue;
	}

	public boolean selectOptionFromActionsDropdown(WebElement dropdownElement, String optionvalue) {

		boolean returnStatus = false;

		try {
			if (cmnLib.clickOnWebElement(dropdownElement) && cmnLib.waitForElementToBeVisible(
					getDriver().findElement(By.xpath("//table[contains(@id,'ScrollContent')]")))) {
				List<WebElement> ListOptions = getDriver()
						.findElements(By.xpath("//table[contains(@id,'ScrollContent')]//td[2]"));
				for (WebElement option : ListOptions) {
					if (option.getText().contains(optionvalue)) {
						option.click();
						returnStatus = true;
						log.info("Selected Option");
						break;
					}
				}
				log.info("Expected option not found");
			} else {
				log.info("List Menu not visible");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Unable to select option from Combobox");
		}
		return returnStatus;
	}

}
