package com.oracle.FIN.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;

public class ManageExternalTransactionPage extends BrowserDriverUtil{
	
	private Logger log = Logger.getLogger(BankStatementsAndReconciliationPage.class.getName());
	
	@FindBy(xpath="//label[text()='Transaction Number']/parent::td/parent::tr//input")
	public WebElement inpTransactionNum;
	
	@FindBy(xpath="//button[text()='Search']")
	public WebElement btnSearch;
	
	@FindBy(xpath="//*[text()='Transaction Details']")
	public WebElement hdrTransactionDetails;
	
	@FindBy(xpath="")
	public WebElement abc5;
	
	@FindBy(xpath="")
	public WebElement abc6;
	
	public ManageExternalTransactionPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Manage External Transaction Page is Initialized...");
	}

	
	public boolean verifyExternalTranSearchedRecordExists(String TransNum) {
		boolean exists = false;
		try {
			List<WebElement> TableRows = getDriver()
					.findElements(By.xpath("//table[@summary='External Transaction']/tbody/tr"));
			System.out.println("No Of Rows in Search Results Table: " + TableRows.size());
			if (TableRows.size() > 0) {
				for (int i = 1; i <= TableRows.size(); i++) {
					if (getDriver() .findElement(By.xpath("//table[@summary='External Transaction']/tbody/tr[" + i + "]//*[contains(text(), '" + TransNum + "')]")) 
							.isDisplayed()) {

						getDriver() .findElement(By.xpath("//table[@summary='External Transaction']/tbody/tr[" + i + "]//*[contains(text(), '" + TransNum + "')]")) 
						.click();
						
						exists = true;
						log.info("Searched Person Number record exists");
						break;
					}
				}
			} else {
				log.info("TableRows row count is less than zero !!");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Searched Person Number record does not exist");
		}
		return exists;
	}
}
