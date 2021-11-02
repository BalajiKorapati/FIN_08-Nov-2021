package com.oracle.FIN.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;

public class AssetsPage extends BrowserDriverUtil {
	
	private Logger log = Logger.getLogger(AssetsPage.class.getName());
	Common_Library cmnLib = new Common_Library();

	@FindBy(xpath = "//div[text()='Asset Inquiry']//ancestor::a[1]")
	public WebElement AssetInquiry;

	@FindBy(xpath = "//img[@title='Tasks']")
	public WebElement imgTaskPane;

	@FindBy(xpath = "//a[contains(@id,'_FOTRaT:0:RAtl4')]")
	public WebElement AdjustAssets;

	@FindBy(xpath = "//a[text()='Create Accounting']")
	public WebElement CreateAccounting;

	@FindBy(xpath = "//a[text()='Retire Assets']")
	public WebElement RetireAssets;

	@FindBy(id = "_FOpt1:_FOr1:0:_FOSritemNode_fixed_assets_additions:0:_FOTRaT:0:RAtl13")
	public WebElement ReinstateAssets;

	@FindBy(xpath = "//span[text()='Depreciation']//ancestor::div[2]/div[2]//span")
	public WebElement Depreciation_InfoTile;

	@FindBy(xpath = "//table[@id='_FOpt1:_FOr1:0:_FOSritemNode_fixed_assets_additions:0:_FOTsr1:0:AP1:soc1']//td[1]")
	public WebElement TaxBookName;

	@FindBy(xpath = "//a[contains(@id,'soc1::drop')]")
	public WebElement TaxBookDropdownIcon;

	@FindBy(id = "_FOpt1:_FOr1:0:_FOSritemNode_fixed_assets_additions:0:_FOTsr1:0:AP1:SPtb2")
	public WebElement TaxBookDropdown_obscure;

	@FindBy(xpath = "//div[@class='AFPopupMenuPopup']//ul")
	public WebElement TaxBook_DropDownMenu;

	@FindBy(xpath = "//span[text()='Copy from Corporate']")
	public WebElement CopyFromCorporate;

	@FindBy(id = "_FOpt1:_FOr1:0:_FOSritemNode_fixed_assets_additions:0:_FOTsr1:0:AP1:region1:0:ITPdtl:1:displayAttrPeriodId::content")
	public WebElement CorporateBookPeriod;

	@FindBy(id = "_FOpt1:_FOr1:0:_FOSritemNode_fixed_assets_additions:0:_FOTsr1:0:AP1:region1:0:ITPdtl:1:commandButton1")
	public WebElement Submit;

	@FindBy(id = "_FOpt1:_FOr1:0:_FOSritemNode_fixed_assets_additions:0:_FOTRaT:0:RAtl6")
	public WebElement CapitalizeCIPAssets;

	@FindBy(xpath = "//span[text()='Calculate Depreciation']")
	public WebElement CalculateDepreciation;

	@FindBy(xpath = "//a[@title='Calculate Depreciation']")
	public WebElement CalculateDepreciation_DropdownIcon;

	@FindBy(xpath = "//td[text()='Close Period']")
	public WebElement ClosePeriod;

	@FindBy(xpath = "//a[text()='Add Asset']")
	public WebElement lnkAddAsset;

	@FindBy(xpath = "//a[text()='Prepare Source Lines']")
	public WebElement PrepareSourceLines;

	@FindBy(xpath = "//a[text()='Impair Assets']")
	public WebElement ImpairAssets;

	@FindBy(xpath = "//a[text()='Transfer Assets']")
	public WebElement TransferAssets;

	@FindBy(xpath = "//a[text()='Inquire Assets']")
	public WebElement InquireAssets;

	@FindBy(xpath = "//a[text()='Manage All Books']")
	public WebElement ManageAllBooks;
	
	@FindBy(xpath = "//a[text()='Perform What-if Analysis']")
	public WebElement PerformWhatIfAnalysis;
	
	@FindBy(xpath = "//label[text()='Book']//parent::td//parent::tr//td[2]//select")
	public WebElement txtBook;
	
	@FindBy(xpath="//a[text()='Inquire Assets']")
	public WebElement lnkInquireAssets;
	
	@FindBy(xpath="//a[text()='Retire Assets']")
	public WebElement lnkRetireAssets;
	
	@FindBy(xpath="//label[text()='Asset Number']/parent::td/parent::tr//input")
	public WebElement txtAssetNum;

	public AssetsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Assets page initialized...");
	}

	public boolean selectTaskFromTaskPane(String strTaskName) {
		boolean flag = false;
		try {
			if (cmnLib.clickOnWebElement(imgTaskPane)) {
				cmnLib.clickOnWebElement(getDriver().findElement(By.xpath("//a[text()='" + strTaskName + "']")));
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;

	}

	public boolean selectTaxBook(String strTaxBook) {
		boolean flag = false;
		try {
			TimeUnit.SECONDS.sleep(2);
			cmnLib.waitForPageLoaded();
			if (cmnLib.clickOnWebElement(TaxBookDropdownIcon)
					&& cmnLib.waitForElementToBeVisible(TaxBook_DropDownMenu)) {
				List<WebElement> TaxBookValues = TaxBook_DropDownMenu.findElements(By.tagName("li"));
				int iValuesSize = TaxBookValues.size();
				System.out.println("No. of Tax Books: " + iValuesSize);
				for (WebElement value : TaxBookValues) {
					if (value.getText().equalsIgnoreCase(strTaxBook)) {
						if (cmnLib.clickOnWebElement(value)) {
							log.info("Tax Book selected from the dropdown");
							WebDriverWait wait = new WebDriverWait(getDriver(), 15);
							if (wait.until(ExpectedConditions.textToBePresentInElement(TaxBookName, strTaxBook))) {
								flag = true;
								log.info("Tax Book Selected");
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to select Tax Book");
		}
		return flag;
	}

	/************************************************************************************************************
	 * Confirmation_CopyFromCorporate
	 ***********************************************************************************************************/
	@FindBy(id = "_FOpt1:_FOr1:0:_FOSritemNode_fixed_assets_additions:0:_FOTsr1:0:AP1:region1:0:ITPdtl:1:d5::contentContainer")
	public WebElement Message_Confirmation_CopyFromCorporate;

	@FindBy(id = "_FOpt1:_FOr1:0:_FOSritemNode_fixed_assets_additions:0:_FOTsr1:0:AP1:region1:0:ITPdtl:1:d5::ok")
	public WebElement OK_Confirmation_CopyFromCorporate;

	/************************************************************************************************************
	 * Confirmation_AssetDepreciaton
	 ***********************************************************************************************************/
	@FindBy(xpath = "//span[text()='Asset depreciation was submitted.']")
	public WebElement Message_Confirmation_AssetDepreciation;

	@FindBy(xpath = "//button[contains(@id,'d4::ok')]")
	public WebElement ok_Confirmation_AssetDepreciation;

	/************************************************************************************************************
	 * Warning
	 ***********************************************************************************************************/
	@FindBy(xpath = "//button[contains(@id,'cb2')]")
	public WebElement Yes_Warning;

	@FindBy(xpath = "//td[contains(@id,'d1::contentContainer')]")
	public WebElement Message_Warning;

	
	@FindBy(css = "img[title='Tasks']")
	public WebElement imgTasks;

	
	@FindBy(linkText = "Adjust Assets")
	public WebElement lnkAdjustAssets;
	
	@FindBy(linkText = "Transfer Assets")
	public WebElement lnkTransferAssets;
	

	

}
