package com.oracle.FIN.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;

public class CreatePurchasePage extends BrowserDriverUtil{

	private Logger log = Logger.getLogger(CreatePurchasePage.class.getName());
	Common_Library cmnLib = new Common_Library();
	
	@FindBy(xpath = "//label[text()='Style']//parent::td//parent::tr//td[2]//input")
	public WebElement txtStyle;
	
	@FindBy(xpath = "//label[text()='Supplier']//parent::td//parent::tr//td[2]//input")
	public WebElement txtSupplier;
	
	@FindBy(xpath = "//label[text()='Procurement BU']//parent::td//parent::tr//td[2]//select")
	public WebElement selProcurementBU;
	
	@FindBy(xpath = "//label[text()='Requisitioning BU']//parent::td//parent::tr//td[2]//select")
	public WebElement selRequisitioningBU;
	
	@FindBy(xpath = "//button[text()='Create']")
	public WebElement btnCreate;
	
	@FindBy(xpath = "//div[contains(text(),'was submitted') or contains(text(),'was created')]")
	public WebElement msgConfirmation;
	
	@FindBy(css = "button[id$='msgDlg::cancel']")
	public WebElement OKConfirmation;
	
	public CreatePurchasePage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Create Agreement Page is initialized...");
	}
}
