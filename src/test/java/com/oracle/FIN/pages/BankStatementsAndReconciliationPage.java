package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;

public class BankStatementsAndReconciliationPage extends BrowserDriverUtil {
	
	private Logger log = Logger.getLogger(BankStatementsAndReconciliationPage.class.getName());
	
	@FindBy(xpath="//img[@title='Tasks']")
	public WebElement imgTasks;
	
	@FindBy(xpath="//a[text()='Create Transaction']")
	public WebElement lnkCreateTransaction;
	
	@FindBy(xpath="//a[text()='Manage Transactions']")
	public WebElement lnkManageTransaction;
	
	@FindBy(xpath="//a[text()='Create Bank Statement']")
	public WebElement lnkCreateBankStatement;
	
	@FindBy(xpath="//a[text()='Manage Bank Statements']")
	public WebElement lnkManageBankStatement;
	
	
	
	public BankStatementsAndReconciliationPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Bank Statements And Reconciliation Page is Initialized...");
	}

}
