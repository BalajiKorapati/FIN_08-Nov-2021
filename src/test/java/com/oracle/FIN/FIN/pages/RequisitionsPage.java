package com.oracle.FIN.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;

public class RequisitionsPage extends BrowserDriverUtil {
	
	private Logger log = Logger.getLogger(RequisitionsPage.class.getName());
	
	@FindBy(xpath="//a[text()='View More']")
	public WebElement lnkViewMore;
	
	@FindBy(xpath="//label[text()='Requisition']/parent::td/parent::tr//input")
	public WebElement txtRequisition;
	
	@FindBy(xpath="//button[text()='Search']")
	public WebElement btnSearch;
	
	@FindBy(xpath="//span[text()='Actions']")
	public WebElement btnActions;
	
	@FindBy(xpath="//td[text()='Withdraw and Edit']")
	public WebElement optionWithdrawAndEdit;
	
	@FindBy(xpath="//div[text()='Warning']")
	public WebElement msgWarning;
	
	@FindBy(xpath="//button[text()='es']")
	public WebElement btnYes_Warning;
	
	@FindBy(xpath="//label[text()='Description']/parent::td/parent::tr//textarea")
	public WebElement txtDescription;
	
	@FindBy(xpath="//input[contains(@id,'0:quantityField::content')]")
	public WebElement txtReqQty;
	
	@FindBy(xpath="//span[text()='Sub']")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//div[text()='Confirmation']")
	public WebElement msgConfirmation;
	
	@FindBy(xpath="//button[text()='O']")
	public WebElement btnOK_Confirmation;
	
	@FindBy(xpath="//tr[contains(@id,':EditOrderMenuItem')]/td[text()='Edit Order']")
	public WebElement optionEditOrder;
	
	@FindBy(xpath="//input[contains(@id,'Quantity::content')]")
	public WebElement txtPOQty;
	
	@FindBy(xpath="//button[text()='OK']")
	public WebElement btnOKConfirmation;
	
	@FindBy(xpath="//button[text()='Yes']")
	public WebElement btnYesWarning;
	
	@FindBy(xpath="//a[text()='More Tasks']")
	public WebElement lnkMoreTasks;
	
	@FindBy(xpath="//td[text()='Request Noncatalog Item']")
	public WebElement lnkRequestNoncatalogItem;
	
	@FindBy(xpath="//td[text()='Enter Requisition Line']")
	public WebElement lnkEnterReqLine;
	
	@FindBy(xpath="//a[text()='Business Cards']")
	public WebElement lnkBusinessCards;
	
	@FindBy(xpath="//div[@title='More options...']")
	public WebElement btnMoreOptions;
	
	@FindBy(xpath="//td[text()='Request Noncatalog Item']")
	public WebElement optionNonCatalogItem;
	
	
	
	public RequisitionsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Requisitions Page is Initialized...");
	}

}
