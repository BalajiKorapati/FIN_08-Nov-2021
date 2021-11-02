package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;

public class BillingPage extends BrowserDriverUtil{
	
	private Logger log = Logger.getLogger(BillingPage.class.getName());
	Common_Library cmnLib = new Common_Library();
	
	@FindBy(css = "img[title='Tasks']")
	public WebElement imgTasks;
	
	@FindBy(linkText = "Create Transaction")
	public WebElement lnkCreateTransaction;
	
	@FindBy(linkText = "Credit Transaction")
	public WebElement lnkCreditTransaction;
	
	@FindBy(linkText = "Manage Transactions")
	public WebElement lnkManageTransactions;
	
	public BillingPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("BillingPage is initialized...");
	}

}
