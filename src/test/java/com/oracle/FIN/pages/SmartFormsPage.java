package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;

public class SmartFormsPage extends BrowserDriverUtil {
	
	private Logger log = Logger.getLogger(RequisitionsPage.class.getName());

	
	@FindBy(xpath="//select[contains(@id,':SmartFormId::content')]")
	public WebElement selectRequestType;
	
	@FindBy(xpath="//label[text()='Quantity']/parent::td/parent::tr//input")
	public WebElement txtQty;
	
	@FindBy(xpath="//label[text()='Name']/parent::td/parent::tr//input")
	public WebElement txtName;
	
	@FindBy(xpath="//label[text()='Title']/parent::td/parent::tr//input")
	public WebElement txtTitle;
	
	@FindBy(xpath="//label[text()='Address, City, State, Zip Code']/parent::td/parent::tr//input")
	public WebElement txtAddress;
	
	@FindBy(xpath="//label[text()='Phone Numbers (Work, Cell, Others)']/parent::td/parent::tr//input")
	public WebElement txtPhoneNum;
	
	@FindBy(xpath="//label[text()='E-Mail Address']/parent::td/parent::tr//input")
	public WebElement txtEmail;
	
	public SmartFormsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Smart Form Page is Initialized...");
	}
}
