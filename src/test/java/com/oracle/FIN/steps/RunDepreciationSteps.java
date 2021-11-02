package com.oracle.FIN.steps;


import org.testng.Assert;

import com.oracle.FIN.pages.AssetsPage;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.steps.ReportingSteps;

import cucumber.api.java.en.Then;
import report.oracle.ofs.ReportGeneration;

public class RunDepreciationSteps {
	
	public static ReportGeneration rpt = ReportingSteps.getRpt();
	Common_Library cmnLib = ReportingSteps.cmnLib;
	
	AssetsPage assets=new AssetsPage();
	@Then("^Click on the Depreciation Infotile$")
	public void click_on_the_Depreciation_Infotile() throws Throwable {
		
		if (cmnLib.clickOnWebElement(assets.Depreciation_InfoTile)) {
			rpt.generateReport("", "Click on Depreciation InfoTile", "", "",
					"Depreciation InfoTile must be clicked", "Clicked on Depreciation InfoTile", "Passed", "",
					false);
		} else {
			rpt.generateReport("", "Click on Depreciation InfoTile", "", "",
					"Depreciation InfoTile must be clicked", "Depreciation InfoTile not clicked", "Failed", "",
					true);
			Assert.fail("Failed to click Depreciation InfoTile");
		}

	}

	public static String strProcessID = null;
	@Then("^Click on the Calculate Depreciation$")
	public void click_on_the_Calculate_Depreciation() throws Throwable {
		
		
		if (cmnLib.clickOnWebElement(assets.CalculateDepreciation)
				&& cmnLib.waitForElementToBeVisible(assets.Message_Confirmation_AssetDepreciation)) {
			String strMessage = assets.Message_Confirmation_AssetDepreciation.getText();
			System.out.println(strMessage);
			strProcessID = strMessage.split(" ")[1];
			rpt.generateReport("", "Click on Calculate Depreciation", "", "",
					"Calculate Depreciation must be clicked and confirmation message must appear",
					"Clicked on Calculate Depreciation and confirmation message appears", "Passed", "", true);
		} else {
			rpt.generateReport("", "Click on Calculate Depreciation", "", "",
					"Calculate Depreciation must be clicked and confirmation message must appear",
					"Either Calculate Depreciation not clicked or confirmation message did not appear", "Failed",
					"", true);
			Assert.fail("Either Calculate Depreciation not clicked or confirmation message did not appear");
		}

	    
	}
	
	

}
