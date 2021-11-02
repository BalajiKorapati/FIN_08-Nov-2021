package com.oracle.FIN.steps;

import java.awt.color.CMMException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.oracle.FIN.pages.CreateNoncatalogRequestPage;
import com.oracle.FIN.pages.PurchaseAgreementPage;
import com.oracle.FIN.pages.RequisitionsPage;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.pages.HomePage;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import report.oracle.ofs.ReportGeneration;

public class ProcessRequistionLines_ReturnSteps {
	
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	RequisitionsPage req= new RequisitionsPage();
	CreateNoncatalogRequestPage crtNonCat = new CreateNoncatalogRequestPage();
	static String reqNum;
	HomePage hmPage = new HomePage();
	PurchaseAgreementPage purAgrement = new PurchaseAgreementPage();
	
	
	@When("^Click on  More Tasks and select Request Non Catalog item$")
	public void click_on_More_Tasks_and_select_Request_Non_Catalog_item() throws Throwable {
		rpt.enterStepHeader("Select Request Non Catalog item");
		
		if(!cmnLib.waitForElementToBeVisible(req.lnkMoreTasks, 30)) {
			rpt.generateReport("", "Non Catalog item selection", "", "",
					"Requistion page should be opened",
					"Failed to open Requistion page", "Failed", "", true);
			Assert.fail("Failed to open Requistion page");
		}
		
		if(!cmnLib.clickOnWebElement(req.lnkMoreTasks)) {
			rpt.generateReport("", "More Tasks", "", "",
					"More Tasks should be clicked",
					"Failed to click on More Tasks link", "Failed", "", true);
			Assert.fail("Failed to click on More Tasks link");
		}
		
		if(!cmnLib.clickOnWebElement(req.optionNonCatalogItem)){
			rpt.generateReport("", "Non Catalog Item", "", "",
					"Non Catalog Item should be clicked",
					"Failed to click on Non Catalog Item link", "Failed", "", true);
			Assert.fail("Failed to click on Non Catalog Item link");
		}
	
	}

	@When("^Enter Noncatalog requests details \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_Noncatalog_requests_details(String ItemDesc, String CategoryName, String Qty, String UOM, String Price, String Currency) throws Throwable {
		rpt.enterStepHeader("Noncatalog requests details");
		
		
		if(!cmnLib.enterDataInTextBox(crtNonCat.txtItemDescription, ItemDesc, true)) {
			rpt.generateReport("", "Item description", "", "Item description :"+ItemDesc,
					"Item description should be entered",
					"Failed to enter Item description", "Passed", "", true);
			Assert.fail("Failed to enter Item description");
		}
		
		if(!cmnLib.enterDataInTextBox(crtNonCat.txtCategoryName, CategoryName, true)) {
			rpt.generateReport("", "CategoryName", "", "CategoryName :"+CategoryName,
					"CategoryName should be entered",
					"Failed to enter CategoryName", "Passed", "", true);
			Assert.fail("Failed to enter CategoryName");
		}
		
		if(!cmnLib.enterDataInTextBox(crtNonCat.txtQuantity, Qty, true)) {
			rpt.generateReport("", "Quantity", "", "Quantity :"+Qty,
					"Quantity should be entered",
					"Failed to enter Quantity", "Passed", "", true);
			Assert.fail("Failed to enter Quantity");
		}
		
		if(!cmnLib.enterDataInTextBox(crtNonCat.txtUOMName, UOM, true)) {
			rpt.generateReport("", "UOM", "", "UOM :"+UOM,
					"UOM should be entered",
					"Failed to enter UOM", "Passed", "", true);
			Assert.fail("Failed to enter UOM");
		}
		
		if(!cmnLib.enterDataInTextBox(crtNonCat.txtPrice, Price, true)) {
			rpt.generateReport("", "Price", "", "Price :"+Price,
					"Price should be entered",
					"Failed to enter Price", "Passed", "", true);
			Assert.fail("Failed to enter Price");
		}
		
		if(!cmnLib.enterDataInTextBox(crtNonCat.txtCurrency, Currency, true)) {
			rpt.generateReport("", "Currency", "", "Currency :"+Currency,
					"Currency should be entered",
					"Failed to enter Currency", "Passed", "", true);
			Assert.fail("Failed to enter Currency");
		}
	
		rpt.generateReport("", "Non Catalog details", "", "Item description :"+ItemDesc+"\nCategoryName :"+CategoryName+"\nQty :"+Qty+
				"\nUOM :"+UOM+"\nPrice :"+Price+"\nCurrency :"+Currency,
				"All the details should be entered",
				"All the details are entered", "Passed", "", true);
	
	}

	@When("^Add to cart and Submit the request$")
	public void add_to_cart_and_Submit_the_request() throws Throwable {
		rpt.enterStepHeader("Add to cart and Submit the request");
		
	  if(! cmnLib.clickOnWebElement(crtNonCat.btnAddtoCart)) {
		  rpt.generateReport("", "Add to cart", "", "",
					"Add to cart button should be clicked",
					"Failed to click on Add to cart button", "Failed", "", true);
			Assert.fail("Failed to click on Add to cart button");
	  }
	  cmnLib.waitForPageLoaded();
	  
	 TimeUnit.SECONDS.sleep(5);
	  
	 if(! cmnLib.clickOnWebElement(crtNonCat.imgCart)) {
		 rpt.generateReport("", "Add to cart icon", "", "",
					"Add to cart icon should be clicked",
					"Failed to click on Add to cart icon", "Failed", "", true);
			Assert.fail("Failed to click on Add to cart icon");
	 }
	 rpt.generateReport("", "Submit", "", "",
				"Submit button should be clicked",
				"Submit button is  clicked", "Passed", "", true);
	 
	 if(! (cmnLib.waitForElementToBeVisible(crtNonCat.btnSubmit, 20)&& cmnLib.clickOnWebElement(crtNonCat.btnSubmit))) {
		 rpt.generateReport("", "Submit", "", "",
					"Submit button should be clicked",
					"Failed to click on Submit button", "Failed", "", true);
			Assert.fail("Failed to click on Submit button");
	 }
	 
	 
	}

	@Then("^Capture the requistion number$")
	public void capture_the_requistion_number() throws Throwable {
		
		if(cmnLib.waitForElementToBeVisible(crtNonCat.msgConfirmation, 60)) {
			String msg= crtNonCat.msgReqNum.getText();
			String[] split = msg.split(" ");
			reqNum=split[1];
			rpt.generateReport("", "Requisition Number", "", "",
					"Requisition Number should be captured",
					"Requisition Number is captured, Requistion is successfully created with number :"+reqNum, "Passed", "", true);
			cmnLib.clickOnWebElement(crtNonCat.btnConfirmationOK);
		}else {
			rpt.generateReport("", "Requisition Number", "", "",
					"Requisition Number should be captured",
					"Failed to create Requisition", "Passed", "", true);
		}
	}

	@Then("^Navigate to Purchase agreements page$")
	public void navigate_to_Purchase_agreements_page() throws Throwable {
	
		rpt.enterStepHeader("Navigate to Purchase agreements page");
		if (cmnLib.clickOnWebElement(hmPage.iconNavigator)
				&& cmnLib.waitForElementToBeVisible(hmPage.navHdrProcurement)) {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Procurement navigation header must be displayed",
					"Clicked on Navigator icon and Procurement navigation header displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Navigator icon", "", "",
					"Navigator icon must be clicked and Procurement navigation header must be displayed",
					"Either not clicked on Navigator icon or Procurement navigation header not displayed", "Failed", "",
					true);
			Assert.fail("Either not clicked on Navigator icon or Procurement navigation header not displayed");
		}

		if (cmnLib.waitForElementToBeVisible(hmPage.navHdrProcurement, 20)) {
			if (cmnLib.clickByJSE(hmPage.navHdrProcurement)) {
					cmnLib.waitForElementToBeVisible(hmPage.lnkPurchaseAgreements); 
				rpt.generateReport("", "Click Procurement navigation header", "", "",
						"Procurement navigation header must be clicked and Purchase Agreements link must be displayed",
						"Clicked on Procurement navigation header and Purchase Agreements link displayed", "Passed", "", true);
			} else {
				rpt.generateReport("", "Click Procurement navigation header", "", "",
						"Procurement navigation header must be clicked and Purchase Agreements link must be displayed",
						"Either not clicked on Procurement navigation header or Purchase Agreements link not displayed", "Failed",
						"", true);
				Assert.fail("Either not clicked on Procurement navigation header or Purchase Agreements link not displayed");
			}
		}

		if (cmnLib.clickOnWebElement(hmPage.lnkPurchaseAgreements) && cmnLib.waitForElementToBeVisible(purAgrement.imgTasks)) {
			rpt.generateReport("", "Click Purchase Requistions", "", "",
					"Purchase Agreements link must be clicked and Purchase Agreements page must be displayed",
					"Clicked on Purchase Agreements link and Purchase Agreements page displayed", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click Purchase Agreements link", "", "",
					"Purchase Agreements link must be clicked and Purchase Agreements page must be displayed",
					"Either not clicked on Purchase Agreements link or Purchase Agreements page not displayed", "Failed", "", true);
			Assert.fail("Either not clicked on Purchase Agreements link or Purchase Agreements page not displayed");
		}
		cmnLib.clickOnWebElement(purAgrement.imgTasks);
		TimeUnit.SECONDS.sleep(2);
		cmnLib.clickOnWebElement(purAgrement.lnkProcessRequistions);
	}

	@Then("^Search requisition in Process requisitions page$")
	public void search_requisition_in_Process_requisitions_page() throws Throwable {
		
		if(!cmnLib.waitForElementToBeVisible(purAgrement.txtRequistion)) {
			rpt.generateReport("", "Click Purchase Agreements link", "", "",
					"Purchase Agreements page must be displayed",
					"Purchase Agreements page not displayed", "Failed", "", true);
			Assert.fail("Purchase Agreements page not displayed");
		}
		
		if(!cmnLib.enterDataInTextBox(purAgrement.txtRequistion, reqNum, true)) {
			rpt.generateReport("", "Requisition number", "", "",
					"Requisition number should be entered",
					"Failed to enter Requisition number", "Failed", "", true);
			Assert.fail("Failed to enter Requisition number");
		}
		
		purAgrement.txtBuyer.clear();
		
		if(!cmnLib.clickOnWebElement(purAgrement.btnSearch)) {
			rpt.generateReport("", "Search button", "", "",
					"Search button should be clicked",
					"Failed to click on Search button", "Failed", "", true);
			Assert.fail("Failed to click on Search button");
		}
		
		if(purAgrement.verifySearchedRecordExists(reqNum)) {
			rpt.generateReport("", "Searched results", "", "",
					"Requisition number should appear in searched results",
					"Requisition number appeared in searched results", "Passed", "", true);
		}else {
			rpt.generateReport("", "Searched results", "", "",
					"Requisition number should appear in searched results",
					"Requisition number did not appear in searched results", "Failed", "", true);
			Assert.fail("Requisition number did not appear in searched results");
		}
	}

	@Then("^Click on Return action \"([^\"]*)\"$")
	public void click_on_Return_action(String ReturnComment) throws Throwable {
		if(!cmnLib.clickOnWebElement(purAgrement.btnReturn)) {
			rpt.generateReport("", "Return button", "", "",
					"Return button should be clicked",
					"Failed to click on Return button", "Failed", "", true);
			Assert.fail("Failed to click on Return button");
		}
		
		if(!cmnLib.waitForElementToBeVisible(purAgrement.txtReturnReason, 30)) {
			rpt.generateReport("", "Return reason", "", "",
					"Return reason page should appear",
					"Return reason page did not appear", "Failed", "", true);
			Assert.fail("Purchase Agreements page not displayed");
		}
		
		if(!cmnLib.enterDataInTextBox(purAgrement.txtReturnReason, ReturnComment, true)) {
			rpt.generateReport("", "Return reason", "", "",
					"Return reason should be entered",
					"Failed to enter Return reason", "Failed", "", true);
			Assert.fail("Failed to enter Return reason");
		}
		if(!cmnLib.clickOnWebElement(purAgrement.btnSubmit)) {
			rpt.generateReport("", "Submit button", "", "",
					"Submit button should be clicked",
					"Failed to click on Submit button", "Failed", "", true);
			Assert.fail("Failed to click on Submit button");
		}else {
			rpt.generateReport("", "Submit button", "", "",
					"Submit button should be clicked",
					"Clicked on Submit button", "Passed", "", true);
		}
		
		if(!cmnLib.waitForElementToBeVisible(purAgrement.msgConfirmation, 30)) {
			rpt.generateReport("", "Confirmation", "", "",
					"Confirmation message should appear",
					"Confirmation message did not appear", "Failed", "", true);
			Assert.fail("Confirmation message did not appear");
		}else {
			rpt.generateReport("", "Confirmation", "", "",
					"Confirmation message should appear",
					"Confirmation message appeared", "Passed", "", true);
		}
		cmnLib.clickOnWebElement(purAgrement.btnOK_Confirmationmsg);
	}

}
