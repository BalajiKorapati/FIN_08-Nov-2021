package com.oracle.FIN.FIN.pages;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.FIN.common.actions.Common_Library;
import com.oracle.acs.util.report.ReportGeneration;


public class AssetInquiryPage extends BrowserDriverUtil {

	private Logger log = Logger.getLogger(AssetInquiryPage.class.getName());
	Common_Library cmnLib = new Common_Library();

	@FindBy(id = "_FOpt1:_FOr1:0:_FOSsdiitemNode_fixed_assets_additions::disAcr")
	public WebElement AssetsIcon;

	@FindBy(xpath = "//label[text()='Book']//parent::td//parent::tr//td[2]//select")
	public WebElement Book;

	@FindBy(xpath = "//label[text()='Category']//parent::td//parent::tr//td[2]//input")
	public WebElement Category;

	@FindBy(xpath = "//label[text()='Asset Number']//parent::td//parent::tr//td[2]//input")
	public WebElement AssetNumber;

	@FindBy(xpath = "//button[text()='Reset']")
	public WebElement Reset;

	@FindBy(xpath = "//button[text()='Search']")
	public WebElement Search;

	@FindBy(xpath = "//div[contains(@id,'t1::db')]")
	public WebElement table_SearchResults;

	@FindBy(xpath = "//div[contains(@id,'t1::db')]/table/tbody/tr")
	public List<WebElement> tableRows_SearchResults;

	@FindBy(xpath = "//div[contains(@id,'t2::db')]/table")
	public WebElement table_Book;

	@FindBy(xpath = "//div[contains(@id,'t2::db')]/table//tr")
	public List<WebElement> tableRows_Book;

	@FindBy(xpath = "//div[contains(@id,'t1::db')]/table/tbody")
	public WebElement table_Assets;

	@FindBy(xpath = "//td[contains(@id,'showDetailHeader2::_afrTtxt')]")
	public WebElement BookDetails_Header;

	@FindBy(xpath = "//td[contains(@id,'showDetailHeader1::_afrTtxt')]")
	public WebElement TransactionTypeDetails_Header;

	@FindBy(xpath = "//div[contains(@id,'_FOTsr1:0:AP4:r1:0:r2:0:pt1::tabh::cbc')]//a")
	public List<WebElement> BookDetails_Tabs;

	@FindBy(xpath = "//table[contains(@summary,'Depreciation Details')]")
	public WebElement table_DepreciationDetails;

	@FindBy(xpath = "//table[contains(@summary,'Depreciation Details')]//tr//tr")
	public List<WebElement> tableRows_DepreciationDetails;

	@FindBy(xpath = "//table[contains(@id,'pgl28')]")
	public WebElement table_FinancialDetails;

	@FindBy(xpath = "//table[contains(@id,'pgl28')]/tbody/tr/td[1]/div/table/tbody/tr")
	public WebElement tableRows_FinancialDetails;

	@FindBy(xpath = "//div[contains(@id,'t4::db')]/table")
	public WebElement table_Transactions;

	@FindBy(xpath = "//div[contains(@id,'t4::db')]/table/tbody/tr//tr")
	public List<WebElement> tableRows_Transactions;

	@FindBy(xpath = "//div[contains(@id,'t5::db') and not(contains(text(),'No data to display'))]/table")
	public WebElement table_TransactionType;

	@FindBy(xpath = "//span[text()='Transaction Number']//parent::div//preceding-sibling::div//a[@title='Sort Descending']")
	public WebElement TransactionNumber_SortDescending;
	
	@FindBy(css = "div[id$='SPb']")
	public WebElement btnDone;

	public AssetInquiryPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Asset Inquiry page is initialized...");
	}

	public boolean selectAssetFromResults(String strAssetNumber, ResultsSelect by) {
		boolean flag = false;
		try {
			if (cmnLib.waitForElementToBeVisible(table_SearchResults)) {
				int iRowSize = tableRows_SearchResults.size();
				System.out.println("No. of Rows: " + iRowSize);
				for (WebElement row : tableRows_SearchResults) {
					WebElement tableDataAssetNumber = row.findElement(By.xpath("td[2]/div[1]/table/tbody/tr/td[2]"));
					String AssetNumber = tableDataAssetNumber.getText();
					System.out.println(AssetNumber);
					if (AssetNumber.equalsIgnoreCase(strAssetNumber)) {
						System.out.println("Matched: " + AssetNumber);
						if (by.equals(ResultsSelect.Row)) {
							cmnLib.clickOnWebElement(row.findElement(By.xpath("td[2]")));
						} else if (by.equals(ResultsSelect.AssetNumber)) {
							cmnLib.clickOnWebElement(tableDataAssetNumber.findElement(By.tagName("a")));
						}
						flag = true;
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

	public String getAssetBookDetails(String strBook, String strColumnNumber) {
		String returnValue = null;
		try {
			if (cmnLib.waitForElementToBeVisible(table_Book)) {
				int iRowSize = tableRows_Book.size();
				System.out.println("No. of Rows: " + iRowSize);
				for (WebElement row : tableRows_Book) {
					WebElement tableDataBook = row.findElement(By.xpath("td[1]"));
					String Book = tableDataBook.getText();
					System.out.println(Book);
					if (Book.equalsIgnoreCase(strBook)) {
						returnValue = row.findElement(By.xpath("td[" + strColumnNumber + "]")).getText();
						log.info("Asset Book Details captured");
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to select Book");
		}
		return returnValue;
	}

	public boolean selectTab_BookDetails(String strTabName) {
		boolean flag = false;
		try {
			int iTabSize = BookDetails_Tabs.size();
			if (iTabSize > 0) {
				for (WebElement tab : BookDetails_Tabs) {
					if (tab.getText().equalsIgnoreCase(strTabName)) {
						System.out.println("Tab Name matched: " + strTabName);
						cmnLib.clickOnWebElement(tab);
						flag = true;
						log.info("Selected tab");
						break;
					}

				}
			} else {
				log.info("No Tabs found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to select Tab");
		}
		return flag;
	}

	public boolean verifyBook(String strBook) {
		boolean flag = false;
		try {
			Thread.sleep(3000);
			int iRowSize = tableRows_Book.size();
			System.out.println("No. of Rows: " + iRowSize);
			if (iRowSize > 0) {
				for (WebElement row : tableRows_Book) {
					WebElement tableDataBook = row.findElement(By.xpath("td[1]"));
					String Book = tableDataBook.getText();
					System.out.println(Book);
					if (Book.equalsIgnoreCase(strBook)) {
						flag = true;
						log.info("Book verified");
						break;
					}
				}
			} else {
				log.info("No Books found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to verify Book");
		}
		return flag;
	}

	public boolean selectBook(String strBook) {
		boolean flag = false;
		try {
			Thread.sleep(3000);
			int iRowSize = tableRows_Book.size();
			System.out.println("No. of Rows: " + iRowSize);
			if (iRowSize > 0) {
				for (WebElement row : tableRows_Book) {
					WebElement tableDataBook = row.findElement(By.xpath("td[1]"));
					String Book = tableDataBook.getText();
					System.out.println(Book);
					if (Book.equalsIgnoreCase(strBook)) {
						cmnLib.clickOnWebElement(tableDataBook);
						flag = true;
						log.info("Book selected");
						break;
					}
				}
			} else {
				log.info("No Books found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to select Book");
		}
		return flag;
	}

	public String getDepreciationDetails(String strPeriod, String strColumnNumber) {
		String returnValue = null;
		try {
			if (cmnLib.waitForElementToBeVisible(table_DepreciationDetails)) {
				cmnLib.scrollTillVisibilityOfElement(table_DepreciationDetails);
				int iRowSize = tableRows_DepreciationDetails.size();
				System.out.println("No. of Rows: " + iRowSize);
				for (WebElement row : tableRows_DepreciationDetails) {

					String Period = row.findElement(By.xpath("td[1]")).getText();
					if (Period.equalsIgnoreCase(strPeriod)) {
						log.info("Period found");
						returnValue = row.findElement(By.xpath("td[" + strColumnNumber + "]")).getText();
						log.info("Depreciation Details Captured");
						break;
					}
				}
				log.info("Period not found");
			} else {
				log.info("Depreciation details table not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to get Depreciation Details");
		}
		return returnValue;
	}

	public String getFinancialDetails(String strAttributeName) {
		String strAttributeValue = null;
		try {
			if (cmnLib.waitForElementToBeVisible(table_FinancialDetails)) {
				cmnLib.scrollTillVisibilityOfElement(table_FinancialDetails);
				List<WebElement> finDetails = tableRows_FinancialDetails.findElements(By.xpath("td//td"));
				for (WebElement data : finDetails) {
					if (data.getText().equalsIgnoreCase(strAttributeName)) {
						System.out.println("Attribute: " + data.getText());
						strAttributeValue = data.findElement(By.xpath("following-sibling::td//td[2]")).getText();
						System.out.println("Attribute Value: " + strAttributeValue);
						log.info("Financial Detail found");
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to get Financial Details");
		}
		return strAttributeValue;
	}

	public String getDepreciateValue() {
		String returnValue = null;
		try {
			WebElement DepreciateValue = getDriver().findElement(
					By.xpath("//label[text()='Depreciate']//parent::td//following-sibling::td/span/span/img"));
			if (cmnLib.waitForElementToBeVisible(DepreciateValue)) {
				returnValue = DepreciateValue.getAttribute("title");
				log.info("Depreciate value captured");
			} else {
				log.info("Depreciate value not visible");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to get Depreciate value");
		}
		return returnValue;
	}

	public String getInServiceDate() {
		String returnValue = null;
		try {
			WebElement InServiceDate = getDriver()
					.findElement(By.xpath("//tr[contains(@id,'panelLabelAndMessage41')]//td[2]"));
			cmnLib.scrollTillVisibilityOfElement(InServiceDate);
			if (cmnLib.waitForElementToBeVisible(InServiceDate)) {
				returnValue = InServiceDate.getText();
				log.info("In Service Date captured");
			} else {
				log.info("In Service Date not visible");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to get In Service Date");
		}
		return returnValue;
	}

	public String getAssetDetails(String strAssetNumber, String strColumnNumber) {

		String returnValue = null;
		try {
			if (cmnLib.waitForElementToBeVisible(table_Assets)) {
				List<WebElement> tableRows_Assets = table_Assets.findElements(By.xpath("tr/td[2]/div/table/tbody/tr"));
				int iRowSize = tableRows_Assets.size();
				System.out.println("No. of Rows: " + iRowSize);
				for (WebElement row : tableRows_Assets) {
					WebElement tableDataAssetNumber = row.findElement(By.xpath("td[1]"));
					String AssetNumber = tableDataAssetNumber.getText();
					System.out.println(AssetNumber);
					if (AssetNumber.equalsIgnoreCase(strAssetNumber)) {
						returnValue = row.findElement(By.xpath("td[" + strColumnNumber + "]")).getText();
						log.info("Asset Details Captured");
						break;
					}
				}
			} else {
				log.info("Results table not found");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to get Asset Detail");
		}
		return returnValue;
	}

	public HashMap<String, String> getTransactionDetails(String strTransactionType) {
		HashMap<String, String> hashTransDetails = new HashMap<>();
		try {
			if (cmnLib.waitForElementToBeVisible(table_Transactions)) {
				int iRowSize = tableRows_Transactions.size();
				System.out.println("No. of Rows: " + iRowSize);
				for (WebElement row : tableRows_Transactions) {
					System.out.println(row);
					WebElement tableDataTransactionType = row.findElement(By.xpath("td[2]"));
					String TransactionType = tableDataTransactionType.getText();
					System.out.println(TransactionType);
					if (TransactionType.equalsIgnoreCase(strTransactionType)) {
						hashTransDetails.put("Transaction Date", row.findElement(By.xpath("td[3]")).getText());
						log.info("Transaction Details Captured");
						break;
					}
				}
			} else {
				log.info("Transactions table not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to get Transaction Details");
		}
		return hashTransDetails;

	}

	public String getTransactionTypeDetails(String strColumnNumber) {
		String returnValue = null;
		int iColumnNum = Integer.parseInt(strColumnNumber) + 2;
		String columnNumber = Integer.toString(iColumnNum);
		try {
			if (cmnLib.waitForElementToBeVisible(table_TransactionType)) {
				List<WebElement> table_rows = table_TransactionType.findElements(By.tagName("tr"));
				int iRowSize = table_rows.size();
				System.out.println("No. of Rows: " + iRowSize);
				for (WebElement row : table_rows) {
					returnValue = row.findElement(By.xpath("td[" + columnNumber + "]")).getText();
					System.out.println("Column Value found: " + returnValue);
					break;
				}
			} else {
				log.info("Transaction Type table not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to get Transaction Type detail");
		}
		return returnValue;
	}

	public String validateTransactionType(String strTransactionTypeBeforeTransaction,
			String strTransactionTypeAfterTransaction, boolean selectTransaction) {
		String strTransactionNumber = null;
		try {
			TimeUnit.SECONDS.sleep(3);
			if (cmnLib.waitForElementToBeVisible(table_Transactions)) {
				int iRowSize = tableRows_Transactions.size();
				System.out.println("No. of Rows: " + iRowSize);
				for (WebElement row : tableRows_Transactions) {
					WebElement tableDataTransactionType = row.findElement(By.xpath("td[2]"));
					String TransactionType = tableDataTransactionType.getText();
					System.out.println(TransactionType);
					if (!TransactionType.equalsIgnoreCase(strTransactionTypeBeforeTransaction)) {
						if (TransactionType.equalsIgnoreCase(strTransactionTypeAfterTransaction)) {
							strTransactionNumber = row.findElement(By.xpath("td[1]")).getText();
							System.out.println("Transaction Number: " + strTransactionNumber);
							if (selectTransaction) {
								cmnLib.clickOnWebElement(tableDataTransactionType);
							}
							break;
						}
					} else {
						log.info("Transaction not found");
						break;
					}
				}
			} else {
				log.info("Transactions table not found");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to get Transaction Details");
		}
		return strTransactionNumber;
	}

	public boolean validateCalculatedDepreciation(String valueBeforeDepreciation, String valueAfterDepreciation,
			String compareValue) {
		boolean flag = false;
		try {
			double dBeforeDepreciation = Double.parseDouble(valueBeforeDepreciation.replace(",", ""));
			double dAfterDepreciation = Double.parseDouble(valueAfterDepreciation.replace(",", ""));
			double dCompareValue = Double.parseDouble(compareValue.replace(",", ""));
			if ((dAfterDepreciation - dBeforeDepreciation) == dCompareValue) {
				flag = true;
				log.info("Compare value successful");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to compare values");
		}
		return flag;
	}

	
	
	

	public String getCalculatedDepreciation(String valueBeforeDepreciation, String depreciationAmount) {

		String strCalculatedDepreciation = null;
		try {
			double dBeforeDepreciation = Double.parseDouble(valueBeforeDepreciation.replace(",", ""));
			double dDepreciationAmount = Double.parseDouble(depreciationAmount.replace(",", ""));
			double calculatedDepreciation = dBeforeDepreciation + dDepreciationAmount;

			strCalculatedDepreciation = String.format("%.2f", calculatedDepreciation);


		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to calculate depreciation");
		}



		return strCalculatedDepreciation;

	}

}
