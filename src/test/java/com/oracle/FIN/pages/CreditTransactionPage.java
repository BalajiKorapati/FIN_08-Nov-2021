package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;

public class CreditTransactionPage extends BrowserDriverUtil{
	
	private Logger log = Logger.getLogger(CreditTransactionPage.class.getName());
	Common_Library cmnLib = new Common_Library();
	
	@FindBy(xpath = "//label[text()='Number']/parent::td/following-sibling::td//input")
	public WebElement txtNumber;
	
	@FindBy(xpath = "//label[text()='Business Unit']/parent::td/following-sibling::td")
	public WebElement lblBusinessUnit;
	
	@FindBy(css = "input[id$='table1:0:AmountVCEvent::content']")
	public WebElement txtLineAmount;
	
	@FindBy(css = "input[id$='table1:1:AmountVCEvent::content']")
	public WebElement txtTaxAmount;
	
	@FindBy(css = "button[id$='EditDistMenuItem2Button']")
	public WebElement btnEditDistributions;
	
	@FindBy(css = "td[id$='dialog3::contentContainer']")
	public WebElement msgWarningDistribution;
	
	@FindBy(css = "button[id$='commandButton1']")
	public WebElement btnYesWarning;
	
	@FindBy(css = "button[id$='commandButton4']")
	public WebElement btnSaveAndCloseEditDistributions;
	
	@FindBy(xpath = "//a/span[text()='Complete and Close']")
	public WebElement btnCompleteAndClose;
	
	@FindBy(xpath = "//div[contains(text(),'Transaction') and contains(text(),'has been completed')]")
	public WebElement msgInformation;
	
	@FindBy(css = "button[id$='msgDlg::cancel']")
	public WebElement btnOKInformation;
	
	public CreditTransactionPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("CreditTransactionPage is initialized...");
	}

}
