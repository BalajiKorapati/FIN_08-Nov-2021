package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.steps.ReportingSteps;

public class CreatePositivePayFilePage extends BrowserDriverUtil{
	
	private Logger log = Logger.getLogger(CreatePositivePayFilePage.class.getName());
	Common_Library cmnLib = ReportingSteps.cmnLib;
	
	@FindBy(xpath = "//td[text()='Create Positive Pay File']")
	public WebElement lblCreatePositivePayFile;
	
	@FindBy(xpath = "//label[text()='Payment Process Profile']/parent::td/following-sibling::td//select")
	public WebElement selPaymentProcessProfile;
	
	@FindBy(xpath = "//label[text()='From Payment Date']/parent::td/following-sibling::td//input[1]")
	public WebElement txtFromPaymentDate;
	
	@FindBy(xpath = "//label[text()='To Payment Date']/parent::td/following-sibling::td//input[1]")
	public WebElement txtToPaymentDate;
	
	@FindBy(xpath = "//label[text()='Allow sending replacement copy']/parent::td/following-sibling::td//select")
	public WebElement selReplacementCopy;
	
	@FindBy(xpath = "//label[text()='Select Status']/parent::td/following-sibling::td//select")
	public WebElement selStatus;
	
	@FindBy(xpath = "//label[contains(text(),'Process') and contains(text(),'was submitted')]")
	public WebElement msgConfirmationProcessSubmitted;
	
	@FindBy(css = "div[id$='submitButton']")
	public WebElement btnSubmit;
	
	@FindBy(css = "button[id$='confirmSubmitDialog::ok']")
	public WebElement btnOKProcessSubmitted;
	
	public CreatePositivePayFilePage() {
		PageFactory.initElements(getDriver(), this);
		log.info("CreatePositivePayFilePage is initialized...");
	}

}
