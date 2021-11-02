package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;

public class ManageAdjustmentsPage extends BrowserDriverUtil{
	
	private Logger log = Logger.getLogger(ManageAdjustmentsPage.class.getName());
	Common_Library cmnLib = new Common_Library();
	
	@FindBy(css = "img[title='Create']")
	public WebElement imgCreate;
	
	@FindBy(xpath = "//label[text()='Receivables Activity']/parent::td/following-sibling::td//input")
	public WebElement txtReceivablesActivity;
	
	@FindBy(xpath = "//label[text()='Adjustment Type']/parent::td/following-sibling::td//select")
	public WebElement selAdjustmentType;
	
	@FindBy(xpath = "//label[text()='Adjustment Amount']/parent::td/following-sibling::td//input")
	public WebElement txtAdjustmentAmount;
	
	@FindBy(xpath = "//label[text()='Adjustment Reason']/parent::td/following-sibling::td//select")
	public WebElement selAdjustmentReason;
	
	@FindBy(css = "button[title='Save']")
	public WebElement btnSubmit;
	
	@FindBy(xpath = "//div[contains(text(),'adjustment') and contains(text(),'has been created')]")
	public WebElement msgInformation;
	
	@FindBy(css = "button[id$='msgDlg::cancel']")
	public WebElement btnOKInformation;
	
	@FindBy(css = "div[id$='activeCommandToolbarButton1']")
	public WebElement btnDone;
	
	public ManageAdjustmentsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("ManageAdjustmentsPage is initialized...");
	}
	
	

}
