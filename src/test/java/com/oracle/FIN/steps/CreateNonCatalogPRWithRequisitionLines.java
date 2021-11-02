package com.oracle.FIN.steps;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;

import com.oracle.FIN.pages.CreateNoncatalogRequestPage;
import com.oracle.FIN.pages.RequisitionsPage;
import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.actions.Common_Library.DropDownSelectBy;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.java.en.When;
import report.oracle.ofs.ReportGeneration;

public class CreateNonCatalogPRWithRequisitionLines extends BrowserDriverUtil {
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	RequisitionsPage req= new RequisitionsPage();
	CreateNoncatalogRequestPage crtNonCat = new CreateNoncatalogRequestPage();

	@When("^Click on  More Tasks and select Enter Requisition Line$")
	public void click_on_More_Tasks_and_select_Enter_Requisition_Line() throws Throwable {
		rpt.enterStepHeader("Click on Requistions line");
		if(!cmnLib.waitForElementToBeVisible(req.lnkMoreTasks, 20)) {
			rpt.generateReport("", "Click More Tasks link", "", "",
					"More Tasks link should appear and should be clicked on it",
					"Either not More Tasks appeared or not clicked", "Failed", "", true);
			Assert.fail("Either not More Tasks appeared or not clicked");
		}else {
			cmnLib.clickOnWebElement(req.lnkMoreTasks);
			TimeUnit.SECONDS.sleep(2);
			cmnLib.clickOnWebElement(req.lnkEnterReqLine);
			rpt.generateReport("", "Click More Tasks link", "", "",
					"More Tasks link should appear and should be clicked on it",
					"More tasks appeared and clicked on it", "Passed", "", true);
		}
	}

	@When("^Enter Noncatalog requests details \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enter_Noncatalog_requests_details(String LineType, String ItemDesc, String CategoryName, String Qty, String UOM, String Price, String Currency) throws Throwable {

		rpt.enterStepHeader("Noncatalog details");

		if(cmnLib.waitForElementToBeVisible(crtNonCat.selectLineType, 30)) {

			if(!cmnLib.selectDropdownBy(crtNonCat.selectLineType, LineType, DropDownSelectBy.VisibleText)) {
				rpt.generateReport("", "Line type selection", "", "Line Type :"+LineType,
						"Line type should be selected",
						"Failed to select Line type", "Failed", "", true);
				Assert.fail("Failed to select Line type");
			}


			if(!cmnLib.enterDataInTextBox(crtNonCat.txtItemDescription, ItemDesc,true)) {
				rpt.generateReport("", "Item description", "", "Item description :"+ItemDesc,
						"Item description should be entered",
						"Failed to enter Item description", "Failed", "", true);
				Assert.fail("Failed to enter Item description");
			}

			if(!cmnLib.enterDataInTextBox(crtNonCat.txtCategoryName, CategoryName, true)) {
				rpt.generateReport("", "CategoryName", "", "Category Name :"+CategoryName,
						"CategoryName should be entered",
						"Failed to enter CategoryName", "Failed", "", true);
				Assert.fail("Failed to enter CategoryName");
			}

			if(!cmnLib.enterDataInTextBox(crtNonCat.txtQuantity, Qty, true)) {
				rpt.generateReport("", "Quantity", "", "Quantity :"+Qty,
						"Quantity should be entered",
						"Failed to enter Quantity", "Failed", "", true);
				Assert.fail("Failed to enter Quantity");
			}
			TimeUnit.SECONDS.sleep(2);
			if(!cmnLib.enterDataInTextBox(crtNonCat.txtUOMName, UOM, true)) {
				rpt.generateReport("", "UOM", "", "UOM :"+UOM,
						"UOM should be entered",
						"Failed to enter UOM", "Failed", "", true);
				Assert.fail("Failed to enter UOM");
			}

			if(!cmnLib.enterDataInTextBox(crtNonCat.txtPrice, Price, true)) {
				rpt.generateReport("", "Price", "", "Price :"+Price,
						"Price should be entered",
						"Failed to enter Price", "Failed", "", true);
				Assert.fail("Failed to enter Price");
			}

			if(!cmnLib.enterDataInTextBox(crtNonCat.txtCurrency, Currency, true)) {
				rpt.generateReport("", "Currency", "", "Currency :"+Currency,
						"Currency should be entered",
						"Failed to enter Currency", "Failed", "", true);
				Assert.fail("Failed to enter Currency");
			}

			rpt.generateReport("", "Non catalog details", "", "LineType :"+LineType+"\nItemDesc :"+ItemDesc+"\nCategoryName :"+CategoryName+
					"\nQuantity :"+Qty+"\nUOM :"+UOM+"\nPrice :"+Price+"\nCurrency :"+Currency,
					"Non catalog details should be entered",
					"Non catalod details are entered", "Passed", "", true);
		}
		else {
			rpt.generateReport("", "Non catalog page", "", "",
					"Non catalog page should appear",
					"Failed to open Non catalog page", "Failed", "", true);
			Assert.fail("Failed to open Non catalog page");
		}
		
		
		
	}
}
