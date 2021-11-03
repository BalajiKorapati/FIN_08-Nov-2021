package com.oracle.FIN.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;

public class CreateBankStatementsPage extends BrowserDriverUtil{
	
	private Logger log = Logger.getLogger(CreateBankStatementsPage.class.getName());
	
	@FindBy(xpath="//label[text()='Bank Account']/parent::td/parent::tr//input")
	public WebElement txtBankAcc;
	
	@FindBy(xpath="//label[text()='Period Start Date']/parent::td/parent::tr//input[contains(@id,'periodStartDateId::content')]")
	public WebElement txtPeriodStartDate	;
	
	@FindBy(xpath="//label[text()='Period End Date']/parent::td/parent::tr//input[contains(@id,'periodEndDateId::content')]")
	public WebElement txtPeriodEndDate;
	
	@FindBy(xpath="//label[text()='Statement ID']/parent::td/parent::tr//input")
	public WebElement txtStatementID;
	
	@FindBy(xpath="//div[contains(@id,'cbsshowDetailItem2::ti')]//a[text()='Statement Lines' and  contains(@id,':cbsshowDetailItem2::disAcr')]")
	public WebElement lnkStatementLines;
	
	@FindBy(xpath="//img[contains(@id,':_ATp:create::icon')]")
	public WebElement imgAddStatementLines;
	
	@FindBy(xpath="//input[contains(@id,'inputDate1::content')]")
	public WebElement txtBookingDate;
	
	@FindBy(xpath="//label[text()='Transaction Code']/parent::td/parent::tr//input")
	public WebElement txtTransactionCode;
	
	@FindBy(xpath="//select[contains(@id,':selectOneChoice3::content')]")
	public WebElement drpFlowIndicator;
	
	@FindBy(xpath="//label[text()='Amount']/parent::td/parent::tr//input")
	public WebElement txtAmount;
	
	@FindBy(xpath="//button[text()='O']")
	public WebElement btnOK;
	
	@FindBy(xpath="//div[contains(@id,':cbsshowDetailItem1::ti')]//a[contains(@id,':cbsshowDetailItem1::disAcr')]")
	public WebElement lnkBalances;
	
	@FindBy(xpath="//input[contains(@id,':1:balanceAmountId::content')]")
	public WebElement txtClosingAmt;
	
	@FindBy(xpath="//button[text()='ave and Close']")
	public WebElement btnSaveandClose;
	
	@FindBy(xpath="//div[text()='Warning']")
	public WebElement msgWarning;
	
	@FindBy(xpath="//div[text()='Confirmation']")
	public WebElement msgConfirmation;
	
	@FindBy(xpath="//button[text()='OK' and contains(@id,'::msgDlg::cancel')]")
	public WebElement btnOKConfirmation;
	
	public CreateBankStatementsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Create Bank Statements Page is Initialized...");
	}

}
