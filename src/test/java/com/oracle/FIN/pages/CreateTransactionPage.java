package com.oracle.FIN.pages;

import java.util.List;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
 
import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;
 
public class CreateTransactionPage extends BrowserDriverUtil{

	
	private Logger log = Logger.getLogger(CreateTransactionPage.class.getName());
	Common_Library cmnLib = new Common_Library();
	
	@FindBy(xpath = "//label[text()='Transaction Class']/parent::td/following-sibling::td//select")
	public WebElement selTransactionClass;
	
	@FindBy(xpath = "//label[text()='Business Unit']/parent::td/following-sibling::td//input[2]")
	public WebElement txtBusinessUnit;
	
	@FindBy(xpath = "//label[text()='Transaction Source']/parent::td/following-sibling::td//input")
	public WebElement txtTransactionSource;
	
	@FindBy(xpath = "//label[text()='Transaction Type']/parent::td/following-sibling::td//input")
	public WebElement txtTransactionType;
	
	@FindBy(xpath = "//label[text()='Transaction Date']/parent::td/following-sibling::td//input[1]")
	public WebElement txtTransactionDate;
	
	@FindBy(xpath = "//label[text()='Accounting Date']/parent::td/following-sibling::td//input[1]")
	public WebElement txtAccountingDate;
	
	@FindBy(xpath = "//label[text()='Bill-to Name']/parent::td/following-sibling::td//input")
	public WebElement txtBillToName;
	
	@FindBy(xpath = "//label[text()='Ship-to Name']/parent::td/following-sibling::td//input")
	public WebElement txtShipToName;
	
	@FindBy(xpath = "//label[text()='Payment Terms']/parent::td/following-sibling::td//input")
	public WebElement txtPaymentTerms;
	
	@FindBy(css = "input[id$='table1:0:ip1:lovTxtId::content']")
	public WebElement txtItemLine1;
	
	@FindBy(css = "input[id$='table1:0:descriptionId::content']")
	public WebElement txtDescriptionLine1;
	
	@FindBy(css = "input[id$='table1:0:uOMId::content']")
	public WebElement txtUOMLine1;
	
	@FindBy(css = "input[id$='table1:0:quantity::content']")
	public WebElement txtQuantityLine1;
	
	@FindBy(css = "input[id$='table1:0:sellingPrice::content']")
	public WebElement txtUnitPriceLine1;
	
	@FindBy(css = "input[id$='table1:0:trxBusinessCategoryMirrorId::content']")
	public WebElement txtTrxBusinessCatLine1;
	
	@FindBy(css = "input[id$='table1:0:memoLineNameId::content']")
	public WebElement txtMemoLineNameLine1;
	
	@FindBy(xpath = "//a/span[text()='Save']")
	public WebElement btnSave;
	
	@FindBy(xpath = "//a[text()='Actions']")
	public WebElement drpdwnActions;
	
	@FindBy(xpath = "//td[text()='Edit Distributions']")
	public WebElement editDistributions;
	
	@FindBy(xpath = "//h1[contains(text(),'Edit Transaction: Invoice')]")
	public WebElement hdrEditTransaction;
	
	@FindBy(xpath = "//label[text()='Status']/parent::td/following-sibling::td")
	public WebElement tblDataStatus;
	
	@FindBy(css = "button[id$='ap110:cb5']")
	public WebElement btnSaveAndCloseEditDistributions;
	
	@FindBy(css = "a[title='Complete and Create Another']")
	public WebElement lnkCompleteAndCreate;
	
	@FindBy(css = "tr[id$='reviewBTN']>td:nth-child(2)")
	public WebElement eleCompleteAndReview;
	
	public CreateTransactionPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("CreateTransactionPage is initialized...");
	}
	
	public boolean selectBUFromSuggestionPopup(String labelName, String option) {

		boolean flag = false;
		try {
			cmnLib.waitForPageLoaded();
			if (cmnLib
					.clickOnWebElement(getDriver().findElement(
							By.xpath("//label[text()='" + labelName + "']/parent::td/following-sibling::td//input[2]")))
					&& cmnLib.waitForElementToBeVisible(
							getDriver().findElement(By.cssSelector("table[id$='sgstnBdy']")))) {
				List<WebElement> listOptions = getDriver()
						.findElements(By.xpath("//table[contains(@id,'sgstnBdy')]/tr/td"));
				for (WebElement ele : listOptions) {
					String text = ele.getText();
					if (text.equalsIgnoreCase(option)) {
						ele.click();
						flag = true;
						log.info("Selected option " + option);
						break;
					}
				}
				log.info("Expected option not found");
			} else {
				log.info("List Menu not visible");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to select option from Suggestions box");
		}
		return flag;
	}

}

