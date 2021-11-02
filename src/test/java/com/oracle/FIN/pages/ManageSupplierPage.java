package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;

public class ManageSupplierPage extends BrowserDriverUtil{ 
	
	private Logger log = Logger.getLogger(ManageSupplierPage.class.getName());
	
	@FindBy(xpath="//a[text()='Manage Suppliers']")
	public WebElement lnkManageSuppliers;
	
	@FindBy(xpath="//input[contains(@id,'value00::content')]")
	public WebElement txtKeywords;
	
	@FindBy(xpath="//button[text()='Search']")
	public WebElement btnSearch;
	
	@FindBy(xpath="//a[text()='Addresses']")
	public WebElement tabAddresses;
	
	@FindBy(xpath="//div[contains(@id,':sitesTab::ti')]//a[text()='Sites' and contains(@id,'sitesTab::disAcr')]")
	public WebElement tabSites;
	
	@FindBy(xpath="//span[text()='Edit']")
	public WebElement btnEdit;
	
	@FindBy(xpath="//label[text()='Address Name']/parent::td/parent::tr//input")
	public WebElement txtAddressName;
	
	@FindBy(xpath="//span[text()='ave and Close']")
	public WebElement btnSaveAndClose;
	
	@FindBy(xpath="//label[text()='Site']/parent::td/parent::tr//input")
	public WebElement txtSite;
	
	@FindBy(xpath="//div[text()='Confirmation']")
	public WebElement msgConfirmation;
	
	@FindBy(xpath="//button[contains(@id,':dialog1::ok')]")
	public WebElement btnOKConfirmation;
	
	@FindBy(xpath="//span[text()='Sub']")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//button[contains(@id,'msgDlg::cancel')]")
	public WebElement btnOkSubmit;
	
	public ManageSupplierPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Manage Supplier Page is Initialized...");
	}

}
