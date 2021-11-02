package com.oracle.FIN.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.steps.ReportingSteps;

public class CreateReceiptPage extends BrowserDriverUtil {
	
	private Logger log = Logger.getLogger(CreatePositivePayFilePage.class.getName());
	Common_Library cmnLib = ReportingSteps.cmnLib;
	
	@FindBy(xpath = "//img[@title='Tasks']")
	public WebElement imgTaskPane;
	
	@FindBy(linkText = "Create Receipt")
	public WebElement lnkCreateReceipt;
	
	@FindBy(xpath="//input[contains(@id,'receiptMethodId::content')]")
	public WebElement txtReceiptMethod;
	
	@FindBy(xpath="//input[contains(@id,'receiptNumberId::content')]")
	public WebElement txtReceiptNumber;
	
	@FindBy(xpath="//input[contains(@id,'amountId::content')]")
	public WebElement txtEnteredAmount;
	
	@FindBy(xpath="//input[contains(@id,'customerAccountId::content')]")
	public WebElement txtAccountNumber;
	
	@FindBy(xpath="//input[contains(@id,'locationId::content')]")
	public WebElement txtSite;
	
	@FindBy(xpath="//a[@title='Submit and Create Another']")
	public WebElement drpSubAndCreateAnother;
	
	@FindBy(xpath="//td[contains(text(),'Submit and Apply Manually')]")
	public WebElement btnSubmitAndApplyManually;
	
	@FindBy(xpath="//button[contains(@id,'ap1:AT1:_ATp:cb6')]")
	public WebElement btnAddApplication;
	
	@FindBy(xpath="//input[contains(@id,'trxNumberList::content')]")
	public WebElement txtApplicationRef;
	
	@FindBy(xpath="//button[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:2:pt1:REF:0:ap1:commandButton1']")
	public WebElement btnSave;
	
	@FindBy(xpath="//button[contains(@id,'ap1:AT1:_ATp:cb7')]")
	public WebElement btnUnapplyApplication;
	
	@FindBy(xpath="//input[contains(@id,'id7::content')]")
	public WebElement txtUnapplyAccountingDate;
	
	@FindBy(xpath="//button[contains(@id,'AT1:cb14')]")
	public WebElement btnSaveAndClose;
	
	@FindBy(xpath="//*[contains(@id,'ap1:menu1')]/div/table/tbody/tr/td[2]/a")
	public WebElement drpAction;
	
	@FindBy(xpath="//td[contains(text(),'Reverse')]")
	public WebElement Reverse;
	
	@FindBy(xpath="//select[contains(@id,'selectOneChoice13::content')]")
	public WebElement drpCategory;
	
	@FindBy(xpath="//select[contains(@id,'selectOneChoice14::content')]")
	public WebElement drpReason;
	
	@FindBy(xpath="//button[contains(@id,'ap1:reverseButton')]")
	public WebElement btnReverse;
	
	@FindBy(xpath="//span[contains(@id,'selectOneChoice4::content')]")
	public WebElement RecieptStatus;
	
	@FindBy(xpath="//div[text()='No rows to display']")
	public WebElement Norowstodisplay;
	
	@FindBy(xpath="//div[contains(text(),'Search and Select')]")
	public WebElement SearchAndSelect;
	
	@FindBy(xpath="//input[contains(@id,'afrLovInternalQueryId:value00::content')]")
	public WebElement txtTranscationNum;
	
	@FindBy(xpath="//button[contains(@id,'trxNumberList::_afrLovInternalQueryId::search')]")
	public WebElement btnSearch;
	
	public CreateReceiptPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Create Receipt Page is initialized...");
	}
	

	public boolean SearchAndSelect(String ApplicationReference) {
		try{
			if(!cmnLib.waitForElementToBeVisible(Norowstodisplay, 3)) {

				cmnLib.waitForElementToBeVisible(SearchAndSelect,30);
				TimeUnit.SECONDS.sleep(2);
				cmnLib.enterDataInTextBox(txtTranscationNum, ApplicationReference, true);
				cmnLib.clickOnWebElement(btnSearch);
				getDriver().findElement(By.xpath("(//span[contains(text(),'"+ApplicationReference+"')])[1]")).click();
				TimeUnit.SECONDS.sleep(2);
				getDriver().findElement(By.xpath("//button[text()='OK' and contains(@id,'lovDialogId::ok')]")).click();
				log.info("Selected");
				return true;
			}else {
				log.info("No rows to display");
				return false;
			}
		}catch (Exception e) {
			log.info("Not Selected");
			e.printStackTrace();
			return false;
		}


	}
}
