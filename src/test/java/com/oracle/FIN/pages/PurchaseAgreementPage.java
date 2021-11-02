package com.oracle.FIN.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;

public class PurchaseAgreementPage extends BrowserDriverUtil{
	
	private Logger log = Logger.getLogger(PurchaseAgreementPage.class.getName());
	
	@FindBy(xpath="//div[@title='Tasks']/a")
	public WebElement imgTasks;
	
	@FindBy(xpath="//a[text()='Process Requisitions']")
	public WebElement lnkProcessRequistions;
	
	@FindBy(xpath="//label[text()='Requisition']/parent::td/parent::tr//input")
	public WebElement txtRequistion;
	
	@FindBy(xpath="//button[text()='Search']")
	public WebElement btnSearch;
	
	@FindBy(xpath="//button[text()='Return']")
	public WebElement btnReturn;
	
	@FindBy(xpath="//textarea[contains(@id,'it1::content')]")
	public WebElement txtReturnReason;
	
	@FindBy(xpath="//button[text()='Sub']")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//div[text()='Confirmation']")
	public WebElement msgConfirmation;
	
	@FindBy(xpath="//button[text()='OK' and contains(@id,'msgDlg::cancel')]")
	public WebElement btnOK_Confirmationmsg;
	
	@FindBy(xpath="//label[text()='Buyer']/parent::td/parent::tr//input")
	public WebElement txtBuyer;
	
	public PurchaseAgreementPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Requisitions Page is Initialized...");
	}
	
	
	
	public boolean verifySearchedRecordExists(String reqNum) {
		boolean exists = false;
		try {
			List<WebElement> TableRows = getDriver()
					.findElements(By.xpath("//table[@summary='Search Results: Requisition Lines']/tbody/tr"));
			System.out.println("No Of Rows in Search Results Table: " + TableRows.size());
			if (TableRows.size() > 0) {
				for (int i = 1; i <= TableRows.size(); i++) {
					if (getDriver()
							.findElement(By.xpath("//table[@summary='Search Results: Requisition Lines']/tbody/tr[" + i
									+ "]//*[contains(text(), '" + reqNum + "')]"))
							.isDisplayed()) {
						getDriver()
						.findElement(By.xpath("//table[@summary='Search Results: Requisition Lines']/tbody/tr[" + i	+ "]")).click();
						exists = true;
						log.info("Searched Requisition Number record exists");
						break;
					}
				}
			} else {
				log.info("TableRows row count is less than zero !!");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Searched Requisitionn Number record does not exist");
		}
		return exists;
	}

}
