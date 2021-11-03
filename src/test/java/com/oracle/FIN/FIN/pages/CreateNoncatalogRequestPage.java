package com.oracle.FIN.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;

public class CreateNoncatalogRequestPage extends BrowserDriverUtil{
	
	private Logger log = Logger.getLogger(RequisitionsPage.class.getName());
	
	@FindBy(xpath="//select[contains(@id,'lineTypeId::content')]")
	public WebElement selectLineType;
	
	@FindBy(xpath="//label[text()='Item Description']/parent::td/parent::tr//textarea")
	public WebElement txtItemDescription;
	
	@FindBy(xpath="//label[text()='Category Name']/parent::td/parent::tr//input")
	public WebElement txtCategoryName;
	
	@FindBy(xpath="//label[text()='Quantity']/parent::td/parent::tr//input")
	public WebElement txtQuantity;
	
	@FindBy(xpath="//label[text()='UOM Name']/parent::td/parent::tr//input")
	public WebElement txtUOMName;
	
	@FindBy(xpath="//label[text()='Price']/parent::td/parent::tr//input")
	public WebElement txtPrice;
	
	@FindBy(xpath="//label[text()='Currency']/parent::td/parent::tr//input")
	public WebElement txtCurrency;
	
	@FindBy(xpath="//span[text()='Add to Cart']")
	public WebElement btnAddtoCart;
	
	@FindBy(xpath="//div[contains(@id,'pb1::content')]")
	public WebElement imgCart;
	
	@FindBy(xpath="//button[text()='Submit']")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//div[text()='Confirmation']")
	public WebElement msgConfirmation;
	
	@FindBy(xpath="//td[contains(@id,'dialog1::contentContainer')]/div/div")
	public WebElement msgReqNum;
	
	@FindBy(xpath="//button[text()='O']")
	public WebElement btnConfirmationOK;
	
	@FindBy(xpath="//a[contains(@id,':cl2')]/span[3]")
	public WebElement txtNumofCartItems;
	
	public CreateNoncatalogRequestPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Create Noncatalog Request Page is Initialized...");
	}
}
