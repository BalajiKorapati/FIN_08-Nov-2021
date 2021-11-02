package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;

public class RetireAssetsPage  extends BrowserDriverUtil{
	
	private Logger log = Logger.getLogger(RetireAssetsPage.class.getName());
	Common_Library cmnLib = new Common_Library();
	
	@FindBy(xpath="//button[text()='Retire Cost']")
	public WebElement btnRetireCost;
	
	@FindBy(xpath="//button[text()='Retire Units']")
	public WebElement btnRetireUnits;
	
	@FindBy(xpath="//textarea[contains(@id,'AP2:inputText8::content')]")
	public WebElement txtRetireComments;
	
	@FindBy(xpath="//label[text()='Cost Retired']/parent::td/parent::tr//input")
	public WebElement txtCostRetired;
	
	@FindBy(xpath="//span[text()='Sub']")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//textarea[contains(@id,'::content')]")
	public WebElement txtRetireUnitsComments;
	
	@FindBy(xpath="//input[contains(@id,':UnitsRetired::content')]")
	public WebElement txtRetiredUnits;
	
	@FindBy(xpath="//label[text()='Cost']/../following-sibling::td//span")
	public WebElement strActualCost;
	
	@FindBy(xpath="//input[contains(@id,':UnitsRetired::content')]/ancestor::td[1]/following-sibling::td[1]/span[1]")
	public WebElement strActualUnits;
	
	@FindBy(xpath="//button[text()='D']")
	public WebElement btnDoneBtn;
	
	@FindBy(xpath="(//label[text()='Current Cost'])[2]/../following-sibling::td//td[2]")
	public WebElement strCurrentCost;
	
	public RetireAssetsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Retire Assets Page is initialized...");
	}
}
