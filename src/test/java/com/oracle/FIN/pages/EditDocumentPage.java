package com.oracle.FIN.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;

public class EditDocumentPage extends BrowserDriverUtil{
	
	private Logger log = Logger.getLogger(EditDocumentPage.class.getName());
	Common_Library cmnLib = new Common_Library();
	
	@FindBy(xpath = "//label[text()='Agreement']//parent::td//parent::tr//td[2]")
	public WebElement agreement;
	
	@FindBy(xpath = "//label[text()='Order']//parent::td//parent::tr//td[2]")
	public WebElement order;
	
	@FindBy(css = "textarea[id$='inputText18::content']")
	public WebElement txtChangeOrderDescription;
	
	@FindBy(xpath = "//label[text()='Supplier']//parent::td//parent::tr//td[2]//input")
	public WebElement txtSupplier;
	
	@FindBy(xpath = "//label[text()='Start Date']//parent::td//parent::tr//td[2]//input[1]")
	public WebElement txtStartDate;
	
	@FindBy(xpath = "//label[text()='End Date']//parent::td//parent::tr//td[2]//input[1]")
	public WebElement txtEndDate;
	
	@FindBy(xpath = "//label[text()='Agreement Amount']//parent::td//parent::tr//td[2]//input")
	public WebElement txtAgreementAmount;
	
	@FindBy(xpath = "//label[text()='Payment Terms']//..//..//input")
	public WebElement txtPaymentTerms;
	
	@FindBy(css = "img[title='Add Row']")
	public WebElement imgAddRow;
	
	@FindBy(xpath = "//label[text()='Type']//..//input")
	public WebElement txtType;
	
	@FindBy(xpath = "//label[text()='Item']//..//input")
	public WebElement txtItem;
	
	@FindBy(xpath = "//label[text()='Description']//..//input")
	public WebElement txtDescription;
	
	@FindBy(xpath = "//label[text()='Category Name']//..//input")
	public WebElement txtCategoryName;
	
	@FindBy(xpath = "//label[text()='Quantity']//..//input")
	public WebElement txtQuantity;
	
	@FindBy(xpath = "//label[text()='UOM']//..//input")
	public WebElement txtUOM;
	
	@FindBy(xpath = "//label[contains(text(),'Price')]//..//input")
	public WebElement txtPrice;
	
	@FindBy(css = "div[id$='SPsb2']")
	public WebElement btnSubmit;
		
	public EditDocumentPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Edit Document Page is initialized...");
	}

}
