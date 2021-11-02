package com.oracle.FIN.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;
import com.oracle.common.pages.HomePage;

public class ReinststementOfAssetPage extends BrowserDriverUtil {

	private Logger log = Logger.getLogger(HomePage.class.getName());
	Common_Library cmnLib = new Common_Library();
	
	
	@FindBy(xpath = "//img[@title='Tasks']")
	public WebElement imgTasks;

	@FindBy(xpath = "//a[text()='Reinstate Assets']")
	public WebElement linkReinstateAsset;

	@FindBy(xpath = "//label[contains(text(),'Asset Number')]/..//input")
	public WebElement txtAssetNumber;

	@FindBy(xpath = "//button[contains(@id,'q1::search')]")
	public WebElement btnSearch;

	@FindBy(css = "table[summary='Search Results']>tbody")
	public WebElement tblBodySearchResults;

	@FindBy(xpath = "//button[text()='Reinstate']")
	public WebElement btnReinstate;
	
	@FindBy(xpath = "//button[contains(@id,'searchRetirementUIVOCriteriaQueryResultId:commandButton3')]")
	public WebElement btnWarningYes;

	@FindBy(xpath = "//button[contains(@id,'commandButton5')]")
	public WebElement btnDone;

	@FindBy(xpath = "//div[contains(@id,'showDetailHeader2')]//a[text()='Transactions']")
	public List<WebElement> lnkTransactions;

	@FindBy(xpath = "//label[contains(text(),'Book')]//..//..//../select")
	public WebElement selectBookName;

	@FindBy(xpath = "//span[text()='Transaction Number']//parent::div//preceding-sibling::div//a[@title='Sort Descending']")
	public WebElement TransactionNumber_SortDescending;

	@FindBy(xpath = "//div[contains(@id,'t4::db')]/table")
	public WebElement table_Transactions;

	@FindBy(xpath = "//div[contains(@id,'t4::db')]/table/tbody/tr//tr")
	public List<WebElement> tableRows_Transactions;

	public ReinststementOfAssetPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("ReinststementOfAssetPage is initialized...");
	}

	public boolean selectRowInAssetResults() {

		boolean flag = false;
		try {
			List<WebElement> assetRows = getDriver()
					.findElements(By.xpath("//table[@summary='Search Results']/tbody/tr"));
			System.out.println("AssetRows" + assetRows.size());
			for (int i = 1; i <= assetRows.size(); i++) {
				String strInvNum = getDriver()
						.findElements(By.xpath("//table[@summary='Search Results']/tbody/tr[" + i + "]/td[2]//a"))
						.get(0).getText();
				System.out.println("strInvNum:        " + strInvNum);
				Thread.sleep(2000);

				getDriver().findElement(By.xpath("//table[@summary='Search Results']/tbody/tr[" + i + "]/td[1]"))
						.click();
				flag = true;
				log.info("Selected Row in Asset Results");
				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to Select Row in Asset Results");
		}

		return flag;
	}

	public String getRecentTransactionDetail(String strColumNumber) {
		String strTransactionType = null;
		try {
			TimeUnit.SECONDS.sleep(3);
			if (cmnLib.waitForElementToBeVisible(table_Transactions)) {
				int iRowSize = tableRows_Transactions.size();
				System.out.println("No. of Rows: " + iRowSize);
				for (WebElement row : tableRows_Transactions) {
					WebElement tableDataTransactionType = row.findElement(By.xpath("td[2]"));
					strTransactionType = tableDataTransactionType.getText();
					System.out.println(strTransactionType);
					break;
				}
			} else {
				log.info("Transactions table not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to get transaction detail");
		}
		return strTransactionType;
	}
}
