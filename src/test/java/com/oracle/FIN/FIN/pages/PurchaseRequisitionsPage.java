package com.oracle.FIN.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;

public class PurchaseRequisitionsPage extends BrowserDriverUtil{

	private Logger log = Logger.getLogger(PurchaseRequisitionsPage.class.getName());
	Common_Library cmnLib = new Common_Library();
	
	@FindBy(xpath = "//a[text()='More Tasks']")
	public WebElement lnkMoreTasks;
	
	@FindBy(xpath = "//label[text()='Line Type']//parent::td//parent::tr//td[2]//select")
	public WebElement selLineTytpe;
	
	@FindBy(xpath = "//label[text()='Item']//parent::td//parent::tr//td[2]//input")
	public WebElement txtItem;
	
	@FindBy(xpath = "//label[text()='Quantity']//parent::td//parent::tr//td[2]//input")
	public WebElement txtQuantity;
	
	@FindBy(xpath = "//label[text()='Price']//parent::td//parent::tr//td[2]//input")
	public WebElement txtPrice;
	
	@FindBy(xpath = "//a/span[text()='Add to Cart']")
	public WebElement btnAddToCart;
	
	@FindBy(xpath = "//span[text()='Added to Cart']")
	public WebElement msgAddedToCart;
	
	@FindBy(linkText = "Manage Requisitions")
	public WebElement lnkManageRequisitions;
	
	@FindBy(css = "a[id$='s6:cl2']")
	public WebElement lnkCart;
	
	@FindBy(xpath = "//button[text()='Submit']")
	public WebElement btnSubmit;
	
	@FindBy(xpath = "//div[contains(text(),'Requisition') and contains(text(),'was submitted')]")
	public WebElement msgConfirmation;
	
	@FindBy(css = "button[id$='commandButton2']")
	public WebElement okConfirmation;
	
	public PurchaseRequisitionsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Purchase Requisitions Page is initialized...");
	}
}
