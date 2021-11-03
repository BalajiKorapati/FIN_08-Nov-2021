package com.oracle.FIN.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;

public class ManageRequisitionsPage extends BrowserDriverUtil {

	private Logger log = Logger.getLogger(ManageRequisitionsPage.class.getName());
	Common_Library cmnLib = new Common_Library();
	
	@FindBy(xpath = "//label[text()='Requisition']//parent::td//parent::tr//td[2]//input")
	public WebElement txtRequisition;
	
	@FindBy(css = "button[id$='q1::search']")
	public WebElement btnSearch;
	
	@FindBy(css = "table[summary='Search Results']>tbody")
	public WebElement tblBdySearchResults;

	public ManageRequisitionsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Manage Requisitions Page is initialized...");
	}

}
