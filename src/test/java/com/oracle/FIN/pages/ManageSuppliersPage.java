package com.oracle.FIN.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oracle.acs.util.BrowserDriverUtil;
import com.oracle.common.actions.Common_Library;

public class ManageSuppliersPage extends BrowserDriverUtil {

	private Logger log = Logger.getLogger(ManageSuppliersPage.class.getName());
	Common_Library cmnLib = new Common_Library();

	@FindBy(xpath = "//a/span[text()='Edit']")
	public WebElement btnEdit;

	@FindBy(xpath = "//label[text()='Inactive Date']//parent::td//parent::tr//td[2]//input[1]")
	public WebElement txtInactiveDate;

	@FindBy(xpath = "//a/span[text()='Save']")
	public WebElement btnSave;

	@FindBy(css = "div[id$='ap1:cb4']")
	public WebElement btnSubmit;

	@FindBy(xpath = "//span[text()='Last Saved']")
	public WebElement lblLastSaved;

	// Sites tab
	@FindBy(xpath = "//div[contains(@id,'panelTabbed1::tabh::cbc')]//a")
	public List<WebElement> lstPanelTab;

	@FindBy(css = "table[summary='Sites']>tbody")
	public WebElement tblBdySites;

	@FindBy(css = "img[id$='edit::icon']")
	public WebElement icnEdit;
	
	@FindBy(css = "div[id$='ap1:cb3']")
	public WebElement btnSaveAndClose;
	
	@FindBy(xpath = "//td[contains(text(),'Your changes were saved')]")
	public WebElement msgConfirmationSaved;
	
	@FindBy(css = "button[id$='dialog1::ok']")
	public WebElement btnOKConfirmation;

	public ManageSuppliersPage() {
		PageFactory.initElements(getDriver(), this);
		log.info("Manage Suppliers Page is initialized...");
	}

	public boolean clickTab(String tab) {

		boolean flag = false;
		try {
			for (WebElement ele : lstPanelTab) {
				String tabName = ele.getText();
				if (tabName.equalsIgnoreCase(tab)) {
					ele.click();
					flag = true;
					log.info("Clicked tab " + tab);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to click tab " + tab);
		}
		return flag;
	}

	public boolean SelectSiteRow(String siteName) {

		boolean flag = false;
		try {
			List<WebElement> siteRows = getDriver()
					.findElements(By.xpath("//table[contains(@summary,'Sites')]/tbody/tr"));
			for (int i = 1; i <= siteRows.size(); i++) {
				String strSiteName = getDriver()
						.findElement(By.xpath("//table[contains(@summary,'Sites')]/tbody/tr[" + i
								+ "]/td[2]/div[1]/table/tbody/tr/td[3]//a[not(contains(@title,'More'))]/span"))
						.getText();
				if (strSiteName.equalsIgnoreCase(siteName)) {
					getDriver().findElement(By.xpath("//table[contains(@summary,'Sites')]/tbody/tr[" + i + "]/td[1]"))
							.click();
					flag = true;
					log.info("Selected Site row " + siteName);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to selected Site row " + siteName);
		}
		return flag;

	}

}
