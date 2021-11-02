package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;

public class TransferAssetsPage extends BrowserDriverUtil{

	
	private Logger log = Logger.getLogger(AdjustAssetsPage.class.getName());
	Common_Library cmnLib = new Common_Library();
	
	
	@FindBy(xpath = "//label[text()='Book']//parent::td//parent::tr//td[2]//select")
	public WebElement Book;
	
	@FindBy(xpath="//label[text()='Asset Number']//parent::td//parent::tr//td[2]//input")
	public WebElement txtAssetNumber;
	
	@FindBy(xpath="//button[contains(@id,'q1::search')]")
	public WebElement btnSearch;
	
	@FindBy(xpath="//button[contains(@id,'FindAssetsQueryResultId:_ATp:commandButton1')]")
	public WebElement btnTransferAsset;
	
	@FindBy(xpath="//button[contains(@id,'FindAssetsQueryResultId:_ATp:cb2')]")
	public WebElement btnAdjustUnits;
	
	@FindBy(xpath="//img[@title='Add Row']")
	public WebElement btnAdd;

	@FindBy(xpath="//table[@summary='Unit Details']/tbody/tr//tr//input[contains(@id,'0:newUnits::content')]")
	public WebElement txtOldUnit;
	
	@FindBy(xpath="//table[@summary='Unit Details']/tbody/tr//tr//input[contains(@id,'1:newUnits::content')]")
	public WebElement txtNewUnit;
	
	@FindBy(xpath="//table[@summary='Unit Details']/tbody/tr//tr//input[contains(@id,'1:kf1CS::content')]")
	public WebElement txtDepreciationAccount;
	
	@FindBy(xpath="//table[@summary='Unit Details']/tbody/tr//tr//input[contains(@id,'1:kf3CS::content')]")
	public WebElement txtLocation;
	
	@FindBy(xpath="//table[@summary='Unit Details']/tbody/tr//tr//input[contains(@id,'0:kf3CS::content')]")
	public WebElement txtOldLocation;
	
	@FindBy(xpath="//button[contains(@id,'Searc1:1:AP1:commandButton1')]")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//button[contains(@id,'AP1:done')]")
	public WebElement btnDone;

	
	public TransferAssetsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Transfer Assets Page is Initialized...");
	}

}
