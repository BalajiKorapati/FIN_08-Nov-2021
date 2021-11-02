package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;

public class InvoiceWorkbenchPage extends BrowserDriverUtil{
	
	private Logger log = Logger.getLogger(InvoiceWorkbenchPage.class.getName());
	
	@FindBy(xpath = "//h1[text()='Invoices']")
	public WebElement hdrInvoices;
	
	@FindBy(xpath = "//img[@title='Tasks']")
	public WebElement imgTasks;
	
	@FindBy(linkText = "Manage Invoices")
	public WebElement lnkManageInvoices;
	
	@FindBy(linkText = "Create Invoice")
	public WebElement lnkCreateInvoice;
	
	public InvoiceWorkbenchPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("InvoiceWorkbenchPage is initialized");
	}

}
