package com.oracle.FIN.FIN.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.acs.util.report.ReportGeneration;
import com.oracle.FIN.common.steps.ReportingSteps;
public class ManageBankStatementPage extends BrowserDriverUtil{
	
	private Logger log = Logger.getLogger(CreateBankStatementsPage.class.getName());
	Common_Library cmnLib = ReportingSteps.cmnLib;
	
	@FindBy(xpath="//a[contains(@id,'_afrDscl')]")
	public WebElement lnkExpandSearch;
	
	@FindBy(xpath="//label[text()='Statement ID']/parent::td/parent::tr//input")
	public WebElement txtStatementID;
	
	@FindBy(xpath="//button[text()='Search']")
	public WebElement btnSearch;
	
	@FindBy(xpath="//*[contains(text(),'Bank Statement')]")
	public WebElement hdrBankStatement;
	
	@FindBy(xpath="")
	public WebElement abc5;
	
	@FindBy(xpath="")
	public WebElement abc6;
	
	
	
	public ManageBankStatementPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Manage Bank Statement Page is Initialized...");
	}
	
	
	public boolean verifyBankStatementSearchedRecordExists(String BankAcc, String StatementID) {
		boolean exists = false;
		try {
			List<WebElement> TableRows = getDriver()
					.findElements(By.xpath("//table[@summary='Manage Bank Statements']/tbody/tr"));
			System.out.println("No Of Rows in Search Results Table: " + TableRows.size());
			if (TableRows.size() > 0) {
				for (int i = 1; i <= TableRows.size(); i++) {
					if (getDriver() .findElement(By.xpath("//table[@summary='Manage Bank Statements']/tbody/tr[" + i + "]//*[contains(text(), '" + BankAcc + "')]")) 
							.isDisplayed()) {

						getDriver() .findElement(By.xpath("//table[@summary='Manage Bank Statements']/tbody/tr[" + i + "]//*[contains(text(), '" + BankAcc + "')]/../../span/a")) 
						.click();
						cmnLib.clickOnLinkText(" "+StatementID);
						exists = true;
						log.info("Searched ID record exists");
						break;
						/*
						 * if(cmnLib.clickOnLinkText(" "+StatementID)) { exists = true;
						 * log.info("Searched ID record exists"); break;
						 */
						/*}else {
							log.info("Statement ID not found");
							break;
						}*/
						
					}
				}
			} else {
				log.info("TableRows row count is less than zero !!");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Statement ID record does not exist");
		}
		return exists;
	}

}
