package com.oracle.FIN.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.oracle.FIN.FIN.pages.CreateReceiptPage;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.FIN.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.acs.util.report.ReportGeneration;

import io.cucumber.java.en.Then;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
public class CreateReceiptSteps {
	
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	CreateReceiptPage receipt = new CreateReceiptPage();
	
	private String strReceiptNumber;
	
	@Then("^Click on the Task panel and Navigate to Create Receipts Page$")
	public void click_on_the_Task_panel_and_Navigate_to_Create_Receipts_Page() throws Throwable {
		
		rpt.enterStepHeader("Click on the Task panel and Navigate to Create Receipts Page");
		
		if (!(cmnLib.clickOnWebElement(receipt.imgTaskPane) && cmnLib.waitForElementToBeVisible(receipt.lnkCreateReceipt))) {
			rpt.generateReport("", "Click Tasks icon", "", "",
					"Tasks icon must be clicked and Create Receipt link must be displayed",
					"Either not clicked on Tasks icon or Create Receipt link not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Tasks icon or Create Receipt link not displayed");
		}

		if (cmnLib.clickOnWebElement(receipt.lnkCreateReceipt) && cmnLib.verifyHeader("Create Receipt")) {
			rpt.generateReport("", "Click Create Receipt under Tasks", "", "",
					"Create Receipt link must be clicked and Create Receipt page must be displayed",
					"Clicked on Create Receipt link and Create Receipt page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Create Receipt under Tasks", "", "",
					"Create Receipt link must be clicked and Create Receipt page must be displayed",
					"Either not clicked on Create Receipt link or Create Receipt page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Create Receipt link or Create Receipt page not displayed");
		}
		
		
	  
	}

	@Then("^Create a Manual Receipt \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void create_a_Manual_Receipt(String ReceiptMethod, String ReceiptNumber, String EnteredAmount, String AccountNumber, String Site) throws Throwable {
		
		rpt.enterStepHeader("Create a Manual Receipt");
		
		if (ReceiptMethod.length() > 0) {
			if (!cmnLib.enterDataInTextBox(receipt.txtReceiptMethod, ReceiptMethod, true)) {
				rpt.generateReport("", "Enter Receipt Method", "", ReceiptMethod, "Receipt Method must be entered",
						"Receipt Method not entered", "Failed", "", true);
				Assert.fail("Receipt Method not entered");
			}
		}

		
		String ReceiptNumberUnique = cmnLib.randomNumber(ReceiptNumber);
		if (!cmnLib.enterDataInTextBox(receipt.txtReceiptNumber, ReceiptNumberUnique, true)) {
			rpt.generateReport("", "Enter Receipt Number", "", ReceiptNumberUnique, "Receipt Number must be entered", "Receipt Number not entered",
					"Failed", "", true);
			Assert.fail("Receipt Number not entered");
		}

		if (EnteredAmount.length() > 0) {
			if (!cmnLib.enterDataInTextBox(receipt.txtEnteredAmount, EnteredAmount, true)) {
				rpt.generateReport("", "Enter Entered Amount", "", EnteredAmount, "Entered Amount must be entered",
						"Entered Amount not entered", "Failed", "", true);
				Assert.fail("Entered Amount not entered");
			}
		}

		if (AccountNumber.length() > 0) {
			if (!cmnLib.enterDataInTextBox(receipt.txtAccountNumber, AccountNumber, true)) {
				rpt.generateReport("", "Enter Account Number", "", AccountNumber, "Account Number must be entered",
						"Account Number not entered", "Failed", "", true);
				Assert.fail("Account Number not entered");
			}
		}

		
		if (!cmnLib.enterDataInTextBox(receipt.txtSite, Site, true)) {
			rpt.generateReport("", "Enter Site", "", Site, "Site must be entered",
					"Site not entered", "Failed", "", true);
			Assert.fail("Site not entered");
		}

		
		rpt.generateReport("", "Enter details for Creating Manual receipt", "",
				"Receipt Method: " + ReceiptMethod + "\nReceipt Number: " + ReceiptNumber + "\nEntered Amount: " + EnteredAmount
						+ "\nAccount Number: " + AccountNumber  +"\nSite: " + Site ,
				"Required details must be entered", "Required details entered", "Passed", "", true);

	}
		
	    
	

	@Then("^Click on Submit and Apply Manually button$")
	public void click_on_Submit_and_Apply_Manually_button() throws Throwable {
		
		rpt.enterStepHeader("Click on Submit and Apply Manually button");
		
		if (cmnLib.clickOnWebElement(receipt.drpSubAndCreateAnother)
				&& cmnLib.waitForElementToBeVisible(receipt.btnSubmitAndApplyManually)) {
			rpt.generateReport("", "Click on Submit and create Another dropdown", "", "",
					"Submit and create Another dopdown must be clicked and Submit and Apply Manually option must be visible",
					"Clicked on Submit and create Another dopdown and Submit and Apply Manually option is visible", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Submit and create Another dropdown", "", "",
					"Submit and create Another dopdown must be clicked and Submit and Apply Manually option must be visible",
					"Either not clicked on Submit and create Another dropdown or Submit and Apply Manually option not visible", "Failed", "", true);
			Assert.fail("Either not clicked on Submit and create Another dropdown or Submit and Apply Manually option not visible");
		}

	    cmnLib.clickOnWebElement(receipt.btnSubmitAndApplyManually);
	}

	@Then("^Click on Add Application button and Enter \"([^\"]*)\"$")
	public void click_on_Add_Application_button_and_Enter(String ApplicationReference) throws Throwable {
		
		if (cmnLib.clickOnWebElement(receipt.btnAddApplication)) {
			rpt.generateReport("", "Click on Add Application button", "", "",
					"Add Application button must be clicked",
					"Clicked on Add Application button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Add Application button", "", "",
					"Add Application button must be clicked",
					"Add Application button is not clicked", "Failed", "", true);
			Assert.fail("Add Application button is not clicked");
		}
		
		//Enter Application Reference
		
		cmnLib.enterDataInTextBox(receipt.txtApplicationRef, ApplicationReference, true);
		if (cmnLib.waitForElementToBeVisible(receipt.SearchAndSelect, 15)) 
		{
			
			receipt.SearchAndSelect(ApplicationReference);
		}
		TimeUnit.SECONDS.sleep(2);
		
		
	}

	@Then("^Click on Save button$")
	public void click_on_Save_button() throws Throwable {
		
		if (cmnLib.clickOnWebElement(receipt.btnSave)) {
			rpt.generateReport("", "Click on Save button", "", "",
					"Save button must be clicked",
					"Clicked on Save button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Save button", "", "",
					"Save button must be clicked",
					"Save button is not clicked", "Failed", "", true);
			Assert.fail("Save button is not clicked");
		}
	    
	}

	@Then("^Click on Unapply application button and Enter Accounting Date \"([^\"]*)\"$")
	public void click_on_Unapply_application_button_and_Enter_Accounting_Date(String AccountingDate) throws Throwable {
		
		if (cmnLib.clickOnWebElement(receipt.btnUnapplyApplication)) {
			rpt.generateReport("", "Click on Unapply application button", "", "",
					"Unapply application button must be clicked",
					"Clicked on Unapply application button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Save button", "", "",
					"Save button must be clicked",
					"Save button is not clicked", "Failed", "", true);
			Assert.fail("Save button is not clicked");
		}
		
		//Enter Accounting Date
		
		
		if (!cmnLib.enterDataInTextBox(receipt.txtUnapplyAccountingDate, AccountingDate, true)) {
			rpt.generateReport("", "Enter Accounting Date", "", AccountingDate, "Accounting Date must be entered",
					"Accounting Date not entered", "Failed", "", true);
			Assert.fail("Accounting Date not entered");
		}
		
		rpt.generateReport("", "Enter Accounting Date", "",
				"AccountingDate: " + AccountingDate ,
				"Accounting Date must be entered", "Accounting Date is entered", "Passed", "", true);
		
	 	
		
		
	   
	}

	@Then("^Click on Save and Close$")
	public void click_on_Save_and_Close() throws Throwable {
		
		if (cmnLib.clickOnWebElement(receipt.btnSaveAndClose)) {
			rpt.generateReport("", "Click on Save and Close", "", "",
					"Save and Close button must be clicked",
					"Clicked on Save and Close button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Save and Close", "", "",
					"Save and Close button must be clicked",
					"Save and Close button is not clicked", "Failed", "", true);
			Assert.fail("Save and Close button is not clicked");
		}
	    
	}

	@Then("^Click on the Action dropdown and select Reverse$")
	public void click_on_the_Action_dropdown_and_select_Reverse() throws Throwable {
		
		rpt.enterStepHeader("Click on the Action dropdown and select Reverse");
		
		//Click on Action DropDown
		
		if (cmnLib.clickOnWebElement(receipt.drpAction)
				&& cmnLib.waitForElementToBeVisible(receipt.Reverse)) {
			rpt.generateReport("", "Click on Action DropDown", "", "",
					"Action dropdown must be clicked and Reverse option must be visible",
					"Clicked on Action dropdown and Reverse option is visible", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Action DropDown", "", "",
					"Action dropdown must be clicked and Reverse option must be visible",
					"Either not clicked on Action dropdown or Reverse option not visible", "Failed", "", true);
			Assert.fail("Either not clicked on Action dropdown or Reverse option not visible");
		}
		
		
		//Click on Reverse
		
		if (cmnLib.clickOnWebElement(receipt.Reverse)) {
			rpt.generateReport("", "Click on Reverse", "", "",
					"Reverse must be clicked",
					"Clicked on Reverse", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Reverse", "", "",
					"Reverse must be clicked",
					"Reverse is not clicked", "Failed", "", true);
			Assert.fail("Reverse is not clicked");
		}
	    
		
	   
	}

	@Then("^Enter the details \"([^\"]*)\", \"([^\"]*)\" and Click on Reverse button$")
	public void enter_the_details_and_Click_on_Reverse_button(String Category, String Reason) throws Throwable {
		
	  rpt.enterStepHeader("Enter the Reverse Receipt details");
	  
	  //Select the Category
	  
	  if (Category.length() > 0) {
			if (cmnLib.selectDropdownBy(receipt.drpCategory, Category, DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Category", "", Category, "Category must be selected",
						"Selected Category", "Passed", "", true);
			} else {
				rpt.generateReport("", "Select Category", "", Category, "Category must be selected",
						"Category not selected", "Failed", "", true);
				Assert.fail("Category not selected");
			}
		}
	  
	  //Select the Reason
	  
	  if (Reason.length() > 0) {
			if (cmnLib.selectDropdownBy(receipt.drpReason, Reason, DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Select Reason", "", Reason, "Reason must be selected",
						"Selected Reason", "Passed", "", true);
			} else {
				rpt.generateReport("", "Select Reason", "", Reason, "Reason must be selected",
						"Reason not selected", "Failed", "", true);
				Assert.fail("Reason not selected");
			}
		}
	  
	  //Click on the Reverse button
	  
	  if (cmnLib.clickOnWebElement(receipt.btnReverse)) {
			rpt.generateReport("", "Click on Reverse button", "", "",
					"Reverse button must be clicked",
					"Clicked on Reverse button", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Reverse button", "", "",
					"Reverse button must be clicked",
					"Reverse button is not clicked", "Failed", "", true);
			Assert.fail("Reverse button is not clicked");
		}
	     
	  
  }

	@Then("^Verify the status of the Reciept$")
	public void verify_the_status_of_the_Reciept() throws Throwable {
		
		String strStatus =receipt.RecieptStatus.getText();
		
		System.out.println(strStatus);
		if(strStatus.equalsIgnoreCase("Reversed")) {
			rpt.generateReport("", "Verify the status of the Reciept", "", "",
					"Receipt status should be reversed",
					"Receipt status is reversed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Verify the status of the Reciept", "", "",
					"Receipt status should be reversed",
					"Receipt status is not reversed", "Failed", "", true);
			Assert.fail("Receipt Status is not : "+strStatus);
		}
	   
	}


	
	

}
