package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;

public class CreateExternalTransactionPage extends BrowserDriverUtil {
	
	private Logger log = Logger.getLogger(BankStatementsAndReconciliationPage.class.getName());
	
	@FindBy(xpath="	//label[text()='Bank Account']/parent::td/parent::tr//input")
	public WebElement txtBankAccount;
	
	@FindBy(xpath="	//label[text()='Amount']/parent::td/parent::tr//input")
	public WebElement txtAmount;
	
	@FindBy(xpath="	//label[text()='Description']/parent::td/parent::tr//textarea")
	public WebElement txtDescription;
	
	@FindBy(xpath="//label[text()='Cash Account']/parent::td/parent::tr//input")
	public WebElement txtCashAccount;
	
	@FindBy(xpath="//label[text()='Offset Account']/parent::td/parent::tr//input")
	public WebElement txtOffsetAccount;
	
	@FindBy(xpath="//span[text()='Save']")
	public WebElement btnSave;
	
	@FindBy(xpath="	//label[text()='Date']/parent::td/parent::tr//input[contains(@id,'1::content')]")
	public WebElement txtTransactionDate;
	
	@FindBy(xpath="//select[contains(@id,'1:soc1::content')]")
	public WebElement drpTransactionType;
	
	@FindBy(xpath="//div[text()='Your changes were saved.']")
	public WebElement msgConfirmation;
	
	@FindBy(xpath="//button[text()='OK' and contains(@id,'cancel')]")
	public WebElement btnConfirmationOK;
	
	@FindBy(xpath="//span[text()='ancel']")
	public WebElement btnCancel;
	
	@FindBy(xpath="//span[contains(@id,'1:it1::content')]")
	public WebElement txtTransactionNum;
	
	public CreateExternalTransactionPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Create External Transaction Page is Initialized...");
	}

}
