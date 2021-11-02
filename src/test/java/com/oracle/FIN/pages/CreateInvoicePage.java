package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.steps.ReportingSteps;

public class CreateInvoicePage extends BrowserDriverUtil {

	private Logger log = Logger.getLogger(CreateInvoicePage.class.getName());
	Common_Library cmnLib = ReportingSteps.cmnLib;

	@FindBy(xpath = "//label[text()='Business Unit']/parent::td/following-sibling::td//input")
	public WebElement txtBusinessUnit;

	@FindBy(xpath = "//label[text()='Supplier']/parent::td/following-sibling::td//input")
	public WebElement txtSupplier;

	@FindBy(xpath = "//label[text()='Supplier Site']/parent::td/following-sibling::td//input")
	public WebElement txtSupplierSite;

	@FindBy(xpath = "//label[text()='Legal Entity']/parent::td/following-sibling::td//input")
	public WebElement txtlegalEntity;

	@FindBy(xpath = "//label[text()='Number']/parent::td/following-sibling::td//input")
	public WebElement txtInvoiceNumber;

	@FindBy(xpath = "//label[text()='Amount']/parent::td/following-sibling::td//input")
	public WebElement txtAmount;

	@FindBy(xpath = "//label[text()='Type']/parent::td/following-sibling::td//select")
	public WebElement drpdwnType;

	@FindBy(xpath = "//label[text()='Date']/parent::td/following-sibling::td//input[1]")
	public WebElement txtDate;

	@FindBy(xpath = "//label[text()='Payment Terms']/parent::td/following-sibling::td//input")
	public WebElement txtPaymentTerms;

	@FindBy(xpath = "//label[text()='Terms Date']/parent::td/following-sibling::td//input[1]")
	public WebElement txtTermsDate;

	@FindBy(xpath = "//span[contains(text(),'Credit and debit memos are usually set for immediate payment')]")
	public WebElement msgWarningCreditDebitMemo;

	@FindBy(css = "button[id$='msgDlg::cancel']")
	public WebElement btnOKWarningCreditMemo;

	@FindBy(xpath = "//a[contains(@id,'sh2::_afrDscl')]")
	public WebElement Line_Expand_Collapse;

	@FindBy(css = "div[id$='ctb3']")
	public WebElement btnDistributions;

	@FindBy(xpath = "//input[contains(@id,'i26::content')]")
	public WebElement Amount_Line1;

	@FindBy(css = "div[id$='ct5']")
	public WebElement btnSave;

	@FindBy(xpath = "//h1[contains(text(),'Create Invoice')]")
	public WebElement hdrCreateInvoice;

	@FindBy(xpath = "//a[text()='Invoice Actions']")
	public WebElement drpdwnActions;

	@FindBy(xpath = "//span[text()='Total']//parent::div//following-sibling::div//td[3]")
	public WebElement TotalValue;

	@FindBy(xpath = "//span[text()='Total']//parent::div//following-sibling::div//img[contains(@title,'Out of Balance')]")
	public WebElement Total_Status_OutOfBalance;

	@FindBy(xpath = "//span[text()='Total']//parent::div//following-sibling::div//img")
	public WebElement Total_Status;

	@FindBy(xpath = "//a[contains(@id,'ap1:cl3')]")
	public WebElement InvoiceValidationStatus;

	@FindBy(xpath = "//button[text()='Continue']")
	public WebElement Continue_Warning;

	/**************************************************************************************************
	 * Accounting Confirmation
	 *************************************************************************************************/
	@FindBy(xpath = "//button[contains(@id,'ap1:cb43')]")
	public WebElement OK_ConfirmationAccounting;

	@FindBy(xpath = "//td[contains(text(),'Accounting is complete')]")
	public WebElement msgConfirmationAccounting;

	/**************************************************************************************************
	 * Invoice Summary
	 *************************************************************************************************/

	@FindBy(xpath = "//td[contains(@id,'ap1:d37::contentContainer')]")
	public WebElement InvoiceSummary_Container;

	@FindBy(xpath = "//a[contains(@id,'ap1:d37::close')]")
	public WebElement Close_InvoiceSummary;

	public CreateInvoicePage() {
		PageFactory.initElements(getDriver(), this);
		log.info("CreateInvoicePage is initialized...");
	}

	public boolean validateStatusInInvoiceSummary(String strAttribute, String strStatus) {
		boolean result = false;
		try {
			String attrStatus = getDriver()
					.findElement(By.xpath(
							"//span[text()='" + strAttribute + "']//parent::span//parent::td//following-sibling::td"))
					.getText();
			System.out.println(attrStatus);
			if (attrStatus.equalsIgnoreCase(strStatus)) {
				result = true;
				log.info("Status validated");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to validate Status");
		}
		return result;
	}

}
