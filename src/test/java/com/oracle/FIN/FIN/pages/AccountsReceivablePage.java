package com.oracle.FIN.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;

public class AccountsReceivablePage extends BrowserDriverUtil{

	private Logger log = Logger.getLogger(AccountsReceivablePage.class.getName());
	Common_Library cmnLib = new Common_Library();
	
	@FindBy(css = "img[title='Tasks']")
	public WebElement imgTasks;
	
	@FindBy(linkText = "Manage Receipts")
	public WebElement lnkManageReceipts;
	
	public AccountsReceivablePage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Accounts Receivable Page Page is Initialized...");
	}
}
