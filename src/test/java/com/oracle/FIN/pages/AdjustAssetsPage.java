package com.oracle.FIN.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;

public class AdjustAssetsPage extends BrowserDriverUtil {

	private Logger log = Logger.getLogger(AdjustAssetsPage.class.getName());
	Common_Library cmnLib = new Common_Library();

	@FindBy(xpath = "//label[text()='Book']//parent::td//parent::tr//td[2]//select")
	public WebElement Book;

	@FindBy(css = "input[id$='q1:value20::content']")
	public WebElement txtAssetNumber;

	@FindBy(xpath = "//button[contains(@id,'SrchTF:0:AP2:q1::search')]")
	public WebElement Search;

	@FindBy(xpath = "//a[text()='Actions']")
	public WebElement Actions_Dropdown;

	@FindBy(xpath = "//table[contains(@id,'_ATp:ATm::ScrollContent')]")
	public WebElement ActionsPopupWindow;

	@FindBy(xpath = "//table[contains(@id,'_ATp:ATm::ScrollContent')]/tbody/tr")
	public List<WebElement> Actions_Dropdown_Values;

	@FindBy(id = "_FOpt1:_FOr1:0:_FOSritemNode_fixed_assets_additions:0:MAnt2:1:SrchTF:0:AP2:tbl1:_ATp:chgFinDetBtn")
	public WebElement ChangeFinancialDetails;

	@FindBy(css = "table[summary='Search Results']>tbody")
	public WebElement tBodySearchResults;

	@FindBy(xpath = "//div[contains(@id,'t1::db')]")
	public WebElement table_SearchResults;

	@FindBy(xpath = "//div[contains(@id,'t1::db')]/table/tbody/tr")
	public List<WebElement> tableRows_SearchResults;

	@FindBy(xpath = "//button[contains(@id,'SrchTF:0:AP2:done')]")
	public WebElement Done;

	@FindBy(xpath = "//label[text()='Comments']/parent::td/following-sibling::td/textarea")
	public WebElement txtComments;

	@FindBy(css = "td[id*='AP2:dialog']>span>button:nth-child(1)")
	public WebElement btnOK;

	@FindBy(css = "span[id$='AP2:activeOutputText1']")
	public WebElement msgConfirmation;

	@FindBy(css = "button[id$='AP2:cb6']")
	public WebElement btnOKConfirmation;

	public AdjustAssetsPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Adjust Assets Page is Initialized...");
	}

	public boolean selectAssetFromResults(String strAssetNumber, ResultsSelect by) {
		boolean flag = false;
		try {
			if (cmnLib.waitForElementToBeVisible(tBodySearchResults)) {
				int iRowSize = tableRows_SearchResults.size();
				System.out.println("No. of Rows: " + iRowSize);
				for (WebElement row : tableRows_SearchResults) {
					WebElement tableDataAssetNumber = row.findElement(By.xpath("td[2]/div[1]/table/tbody/tr/td[1]"));
					String AssetNumber = tableDataAssetNumber.getText();
					log.info(AssetNumber);
					if (AssetNumber.equalsIgnoreCase(strAssetNumber)) {
						log.info("Matched: " + AssetNumber);
						if (by.equals(ResultsSelect.Row)) {
							flag = cmnLib.clickOnWebElement(getDriver()
									.findElement(By.xpath("//div[contains(@id,'t1::db')]/table/tbody/tr/td[1]")));
						} else if (by.equals(ResultsSelect.AssetNumber)) {
							flag = cmnLib.clickOnWebElement(tableDataAssetNumber.findElement(By.tagName("a")));
						}
						log.info("Selected result item");
						break;
					}
				}
			} else {
				log.info("Results table not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to select row in results");
		}
		return flag;
	}

	public enum ResultsSelect {
		Row, AssetNumber;
	}

	public boolean selectValueFromActionsDropdown(String strValue) {
		boolean flag = false;
		try {
			List<WebElement> actionDropdowns = getDriver().findElements(By.xpath("//a[text()='Actions']"));
			WebElement element = actionDropdowns.get(actionDropdowns.size() - 1);

			String[] options = strValue.split(":");
			if (!(cmnLib.clickOnWebElement(element) && cmnLib.waitForElementToBeVisible(ActionsPopupWindow))) {
				log.info("Either not clicked on Actions dropdown or Actions menu not displayed");
				throw new RuntimeException("Either not clicked on Actions dropdown or Actions menu not displayed");
			}
			if (Actions_Dropdown_Values.size() > 0) {
				for (WebElement row : Actions_Dropdown_Values) {
					WebElement valueElement = row.findElement(By.xpath("td[2]"));
					if (valueElement.getText().equalsIgnoreCase(options[0])) {
						cmnLib.clickOnWebElement(valueElement);
						if (options.length == 1) {
							flag = true;
						}
						log.info("Selected value from Actions dropdown");
						break;
					}

				}
			} else {
				log.info("No values found");
			}
			// Second window
			if (options.length == 2) {
				List<WebElement> window2Elements = getDriver()
						.findElements(By.xpath("//table[contains(@id,'_ATp:m1::ScrollContent')]/tbody/tr"));
				for (WebElement row : window2Elements) {
					WebElement valueElement = row.findElement(By.xpath("td[2]"));
					if (valueElement.getText().equalsIgnoreCase(options[1])) {
						cmnLib.clickOnWebElement(valueElement);
						flag = true;
						log.info("Selected value from Actions dropdown second window");
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to select value from Actions dropdown");
		}
		return flag;
	}

}
