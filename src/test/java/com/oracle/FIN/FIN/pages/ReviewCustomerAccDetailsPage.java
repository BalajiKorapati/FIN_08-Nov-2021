package com.oracle.FIN.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
public class ReviewCustomerAccDetailsPage extends BrowserDriverUtil {
	
	private Logger log = Logger.getLogger(CreatePositivePayFilePage.class.getName());
	Common_Library cmnLib = ReportingSteps.cmnLib;
	
	@FindBy(xpath = "//img[@title='Tasks']")
	public WebElement imgTaskPane;
	
	@FindBy(linkText = "Review Customer Account Details")
	public WebElement lnkReviewCustomerAccountDetails;
	
	@FindBy(xpath="//input[contains(@id,'value20::content')]")
	public WebElement txtAccNumber;
	
	@FindBy(xpath="//button[contains(@id,'q1::search')]")
	public WebElement btnSearch;
	
	@FindBy(xpath="//a[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:pt1:r1:0:AP1:sdi1::disAcr']")
	public WebElement OverviewTab;
	
	@FindBy(xpath="//a[@id='_FOpt1:_FOr1:0:_FONSr2:0:MAnt2:1:pt1:r1:0:AP1:sdi2::disAcr']")
	public WebElement ActivitiesTab;
	
	public ReviewCustomerAccDetailsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Review Customer Account Details Page is initialized...");
	}
	


}
