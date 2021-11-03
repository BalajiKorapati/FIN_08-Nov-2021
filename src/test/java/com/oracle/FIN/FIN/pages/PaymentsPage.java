package com.oracle.FIN.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;

public class PaymentsPage extends BrowserDriverUtil{

	private Logger log = Logger.getLogger(PaymentsPage.class.getName());
	
	@FindBy(css = "img[title='Tasks']")
	public WebElement imgTasks;
	
	@FindBy(linkText = "Create Positive Pay File")
	public WebElement lnkCreatePositivePayFile;
	
	public PaymentsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("PaymentsPage is initialized");
	}
}
