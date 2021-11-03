package com.oracle.FIN.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.oracle.FIN.FIN.pages.ReviewCustomerAccDetailsPage;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
import io.cucumber.java.en.Then;
import com.oracle.acs.util.report.ReportGeneration;

public class ReviewCustomerAccDetailsSteps {
	
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	
	ReviewCustomerAccDetailsPage customerAccDetailsPage=new ReviewCustomerAccDetailsPage();
	@Then("^Click on the Task panel and Navigate to Review Customer Account Details Page$")
	public void click_on_the_Task_panel_and_Navigate_to_Review_Customer_Account_Details_Page() throws Throwable {
		
		
	rpt.enterStepHeader("Click on the Task panel and Navigate to Review Customer Account Details Page");
		
		if (!(cmnLib.clickOnWebElement(customerAccDetailsPage.imgTaskPane) && cmnLib.waitForElementToBeVisible(customerAccDetailsPage.lnkReviewCustomerAccountDetails))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Review Customer Account Details link must be displayed",
					"Either not clicked on Tasks icon or Review Customer Account Details link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or Review Customer Account Details link not displayed");
		}

		if (cmnLib.clickOnWebElement(customerAccDetailsPage.lnkReviewCustomerAccountDetails) && cmnLib.verifyHeader("Review Customer Account Details")) {
			rpt.generateReport("", "Click Review Customer Account Details under Tasks", "", "",
					"Review Customer Account Details link must be clicked and Review Customer Account Details page must be displayed",
					"Clicked on Review Customer Account Details link and Review Customer Account Details page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Review Customer Account Details under Tasks", "", "",
					"Review Customer Account Details link must be clicked and Review Customer Account Details page must be displayed",
					"Either not clicked on Review Customer Account Details link or Review Customer Account Details page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Review Customer Account Details link or Review Customer Account Details page not displayed");
		}
		
		
	    
	}
	
	@Then("^Enter Account Number \"([^\"]*)\"$")
	public void enter_Account_Number(String AccNumber) throws Throwable {
		
		if (cmnLib.enterDataInTextBox(customerAccDetailsPage.txtAccNumber, AccNumber, true)) {
			rpt.generateReport("", "Enter Account Number", "", AccNumber, "Account Number must be entered",
					"Entered Account Number", "Passed", "", true);
		} else {
			rpt.generateReport("", "Enter Account Number", "", AccNumber, "Account Number must be entered",
					"Account Number not entered", "Failed", "", true);
			Assert.fail("Account Number not entered");
		}

	  
	}

	@Then("^Click on Search for reviewing the Customer details$")
	public void click_on_Search_for_reviewing_the_Customer_details() throws Throwable {
		
		if (cmnLib.clickOnWebElement(customerAccDetailsPage.btnSearch)
				&& cmnLib.waitForElementToBeVisible(customerAccDetailsPage.OverviewTab)) {
			rpt.generateReport("", "Click on Search button", "", "",
					"Search button must be clicked and Account details must be displayed",
					"Clicked on Search button and Account details displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Search button", "", "",
					"Search button must be clicked and Account details must be displayed",
					"Either not clicked on Search button or Account details not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Search button or Account details not displayed");
		}
		
	   
	}

	@Then("^Click on the Activites tab for Viewing activity$")
	public void click_on_the_Activites_tab_for_Viewing_activity() throws Throwable {
		
		
		if (cmnLib.clickOnWebElement(customerAccDetailsPage.ActivitiesTab)) {
			rpt.generateReport("", "Click on the Activites tab for Viewing activity", "", "",
					"Activites tab must be clicked for viewing the account activity",
					"Clicked on Activites tab and account activity displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on the Activites tab for Viewing activity", "", "",
					"Activites tab must be clicked for viewing the account activity",
					"Either not clicked on Activites tab or account activity not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Activites tab or account activity not displayed");
		}
		
		if (cmnLib.scrollDownByJSE()) {
			TimeUnit.SECONDS.sleep(2);
			rpt.generateReport("", "Inquiry customer details", "", "",
					"Customer details must be inquired",
					"Customer details are inquired", "Passed", "", true);
		} else {
			rpt.generateReport("", "Inquiry customer details", "", "",
					"Customer details must be inquired",
					"Customer details are not inquired", "Failed", "", true);
			Assert.fail("Customer details are not inquired");
			
		}
	   
	}
	
	

}
