package com.oracle.FIN.common.actions;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.oracle.acs.util.BrowserDriverUtil;

public class Common_Library extends BrowserDriverUtil {
	static final Logger log = LoggerFactory.getLogger(Common_Library.class);

	public boolean result;

	/**
	 * -----------------------------------------------------------------------------------
	 *
	 * @param :
	 *            Null
	 *            ------------------------------------------------------------------------------------
	 * @Function_Name : waitForPageLoaded
	 * @Description : This function will Wait for up to complete page loading
	 */
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		// wait for jQuery to load
		ExpectedCondition<Boolean> jQuery = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) getDriver()).executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// TODO: handle exception
					return true;
				}

			}
		};

		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(getDriver(), 30);
			// wait.until(expectation);
			// wait.until(jsLoad);
			result = wait.until(expectation) && wait.until(jQuery);
		} catch (Throwable error) {
			System.err.println("Timeout waiting for Page Load Request to complete.");
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	/**
	 * -----------------------------------------------------------------------------------
	 *
	 * @param :
	 *            name : input name
	 *            --------------------------------------------------------------------------------------
	 * @Function_Name : randomNumber
	 * @Description : Generate the randomNumber
	 */

	public String randomNumber(String name) throws Exception {

		Calendar lCDateTime = Calendar.getInstance();
		String time = lCDateTime.getTimeInMillis() + "";
		if (time.length() > 10) {
			time = time.substring(4);
		}
		return name + time;
	}

	/**
	 * <b>Description</b>: Popup will appear with objects passed and will exist till
	 * the timer mention (if no action performed in popup)
	 *
	 * @param title
	 *            Title for JDialog
	 * @param timerInSec
	 *            Jdialog will exist till the timer mention (if no action performed
	 *            in popup). 0 means no timer will be started.
	 * @param obj
	 *            Array of objects
	 * @param obj
	 * @author : deepu
	 * @Created : Feb 24, 2017
	 */
	public void popup(String title, int timerInSec, Object... obj) {
		JOptionPane jP = new JOptionPane(obj);
		JDialog jD = jP.createDialog(title);
		jD.setAlwaysOnTop(true);
		if (timerInSec != 0) {
			timerThread(jD, timerInSec);
		}
		jD.setTitle("Manual Intervention ");
		jD.setVisible(true);
	}

	/**
	 * <b>Description</b>: Timer for Jdialog
	 *
	 * @param jD
	 * @param counter
	 *            : in secs
	 * @author : deepu
	 * @Created : Feb 24, 2017
	 */
	public void timerThread(final JDialog jD, final int counter) {
		Thread timer = new Thread(new Runnable() {
			public void run() {
				try {
					int i = counter;
					// Thread.sleep(1000);
					while (i >= 0) {
						jD.setTitle("Manual Intervention " + i + "secs left");
						Thread.sleep(1000);
						i--;
						if (!jD.isVisible()) {
							break;
						}
					}
					if (jD.isVisible()) {
						jD.dispose();
					}
				} catch (Throwable th) {
				}
			}
		});
		timer.start();
	}

	/**
	 * This method is used to select the Value from DropDown. If selecting by ID
	 * then Pass the 'textValue' as integer value ex: "2"
	 *
	 * @param ele
	 *            WebElement of the DropDown
	 * @param textValue
	 *            String Value to be selected from the dropdown.
	 * @param by
	 *            enum value ex: DropDownSelectBy.VisibleText
	 * @return boolean
	 * @author ampn (Amit PN)
	 */

	public boolean selectDropdownBy(WebElement ele, String textValue, DropDownSelectBy by) {
		boolean result = false;
		try {
			TimeUnit.SECONDS.sleep(1);
			ele.isDisplayed();
			Select sel = new Select(ele);
			switch (by) {
			case VisibleText:
				sel.selectByVisibleText(textValue);
				log.info("DropDown is selected using selectByVisibleText");
				result = true;
				ele.sendKeys(Keys.TAB);
				break;

			case Value:
				sel.selectByValue(textValue);
				log.info("DropDown is selected using selectByValue");
				result = true;
				ele.sendKeys(Keys.TAB);
				break;

			case ID:
				int ID = Integer.parseInt(textValue);
				sel.selectByIndex(ID);
				log.info("DropDown is selected using selectByIndex");
				result = true;
				ele.sendKeys(Keys.TAB);
				break;
			default:
				log.info("Invlid textValue: " + textValue + ", Could not select");
				result = false;
				break;
			}
			TimeUnit.SECONDS.sleep(1);
			waitForPageLoaded();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Dropdown is not selected");
		}
		return result;

	}

	public enum DropDownSelectBy {
		VisibleText, Value, ID;
	}

	/**
	 * This method is used to enter data into the TextBox
	 *
	 * @param ele
	 *            WebElement of the TextBox
	 * @param strValue
	 *            String value to be entered in the TextBox
	 * @return boolean
	 * @author ampn (Amit PN)
	 */
	public boolean enterDataInTextBox(WebElement ele, String strValue, boolean isPressTabTrue) {
		boolean result = false;
		try {
			TimeUnit.SECONDS.sleep(1);
			if (ele.isDisplayed() && ele.isEnabled()) {
				ele.clear();
				ele.sendKeys(strValue);
			}
			if (isPressTabTrue) {
				ele.sendKeys(Keys.TAB);
			}
			log.info("String value entered in TextBox is: " + strValue);
			result = true;
			waitForPageLoaded();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("String value '" + strValue + "' is not entered in TextBox");
			result = false;
		}
		return result;
	}

	/**
	 * @param element
	 *            WebElement
	 * @return boolean
	 * @author Mehavarnan Murugan This method will wait for the Element to be
	 *         clickable
	 */
	public boolean waitForElementToBeClickable(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 60);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.isDisplayed();
			log.info("WebElement exists and clickable");
			return true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			log.info("No Such Element Exception");
			return false;
		} catch (TimeoutException e) {
			e.printStackTrace();
			log.info("TimeoutException Exception");
			return false;
		} catch (Exception e) {
			log.info("WebElement does not exist");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param element
	 *            WebElement
	 * @return boolean
	 * @author Mehavarnan Murugan This method will wait for the Element to be
	 *         visible
	 */
	public boolean waitForElementToBeVisible(WebElement element) {

		if (element == null) {
			return false;
		}
		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(getDriver(), 60)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.isDisplayed();
			Thread.sleep(3000);
			waitForPageLoaded();
			log.info("WebElement exists");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("WebElement does not exist");
			return false;
		}
	}

	/**
	 * @param element
	 *            WebElement
	 * @author Mehavarnan Murugan This method clicks the WebElement using
	 *         JavaScriptExecutor
	 */
	public boolean clickByJSE(WebElement element) {
		boolean result = false;
		try {
			element.isDisplayed();
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].click();", element);
			log.info("WebElement Clicked");
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("WebElement is not Clicked");
		}
		return result;
	}

	/**
	 * This method wait for the Text to be present in the element
	 *
	 * @param element
	 *            WebElement
	 * @return boolean
	 */
	public boolean waitForTextToBePresentInElement(WebElement element) {
		boolean result = false;
		try {
			element.isDisplayed();
			WebDriverWait wait = new WebDriverWait(getDriver(), 20);

			ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver arg0) {
					return element.getText().length() > 0;
				}
			};
			try {
				wait.until(condition);
				result = true;
				log.info("Text appears on the element");

			} catch (Exception e) {
				e.printStackTrace();
				log.info("Text does not appear on the element");
				result = false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Text does not appear on the element");
			result = false;
		}
		return result;
	}

	public enum FrameSelectBy {
		intFrameNo, StringFrameName, FrameWebElement;
	}

	/**
	 * This Method is used to Switch To frames using FrameNo, Frame Name or Frame
	 * WebElement
	 *
	 * @param obj
	 *            This object can be FrameNo(int) or FrameName(String) or
	 *            FrameWebElement(WebElement)
	 * @param by
	 *            enum value
	 * @return boolean
	 * @author ampn (Amit PN)
	 */
	public boolean switchToFrame(Object obj, FrameSelectBy by) {
		result = false;
		try {
			switch (by) {
			case intFrameNo:
				getDriver().switchTo().frame((int) obj);
				log.info("Switched to Frame using FrameNo");
				result = true;
				break;

			case FrameWebElement:
				((WebElement) obj).isDisplayed();
				getDriver().switchTo().frame(((WebElement) obj));
				log.info("Switched to Frame using Frame WebElement");
				result = true;
				break;

			case StringFrameName:
				getDriver().switchTo().frame((String) obj);
				log.info("Switched to Frame using Frame Name");
				result = true;
				break;
			default:
				result = false;
				log.info("Invalid Switch case: " + by);
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
			log.info("Could not switch to Frame using FrameBy: " + by);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * This Method will click on the WebElement
	 *
	 * @param ele
	 *            WebElement
	 * @return boolean
	 * @author ampn (Amit PN)
	 */
	public boolean clickOnWebElement(WebElement ele) {
		result = false;
		try {
			TimeUnit.SECONDS.sleep(1);
			if (waitForElementToBeClickable(ele) && ele.isEnabled()) {
				ele.click();
				log.info("WebElement is clicked");
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("WebElement is not clicked");
		}
		return result;
	}

	public boolean scrollDownByJSE() {
		boolean result = false;
		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scroll(1000, 1000)");
			log.info("Page scrolldown");
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Page not Scroll down");
		}
		return result;
	}

	public boolean clickOnLinkText(String linkText) throws InterruptedException {
		try {

			boolean verify = getDriver().findElement(By.linkText(linkText)).isDisplayed();
			if (verify == true) {
				Thread.sleep(3000);
				getDriver().findElement(By.linkText(linkText)).click();
				return true;
			} else {
				System.err.println("Unable to click on link with text " + linkText);
				return false;
			}
		} catch (NoSuchElementException e) {
			System.err.println("Unable to click on link with text " + linkText);
			e.printStackTrace();
			return false;
		} catch (Error e) {
			System.err.println("Unable to click on link with text " + linkText);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This Method is used to switch between window Tabs
	 *
	 * @param strTitle
	 *            Title of the WebPage(If null/empty, it will switch to the 1st tab
	 *            other than current tab)
	 * @return boolean
	 * @throws InterruptedException
	 * @author ampn (Amit PN)
	 */
	public boolean switchToWindowTab(String strTitle) throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
		boolean result = false;
		String currentWindowTab = null;
		try {

			Set<String> allWindowTabs = getDriver().getWindowHandles();
			System.out.println("No. of windows: " + allWindowTabs.size());
			if (allWindowTabs.size() > 1) {
				currentWindowTab = getDriver().getWindowHandle();
				System.out.println("cur winTab: " + currentWindowTab);
				System.out.println("cur winTabTitle: " + getDriver().switchTo().window(currentWindowTab).getTitle());
				for (String windowTab : allWindowTabs) {

					String winTabTitle = getDriver().switchTo().window(windowTab).getTitle();
					System.out.println("winTab: " + windowTab);
					System.out.println("winTabTitle: " + winTabTitle);
					// If window Title is empty or null, It will return windowTab other than current
					// window Tab
					if (!currentWindowTab.equals(windowTab) && (strTitle == null || strTitle.length() == 0)) {
						getDriver().switchTo().window(windowTab);
						log.info("Switching to windowTab other than current window Tab");
						result = true;
						break;
					}

					// If window Title is not empty then it will look for window Tab having strTitle
					if (!currentWindowTab.equals(windowTab) && winTabTitle.trim().equalsIgnoreCase(strTitle)) {
						getDriver().switchTo().window(windowTab);
						log.info("Window Tab found with Title: " + strTitle + " and Switching to it");
						result = true;
						break;
					}
				}
			} else {
				for (String windowTab : allWindowTabs) {
					if (getDriver().switchTo().window(windowTab).getTitle().equalsIgnoreCase(strTitle)) {
						result = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			try {
				getDriver().switchTo().window(currentWindowTab);
				log.info("Could not switch to Window Tab with Title: " + strTitle);
				e.printStackTrace();
			} catch (Exception e1) {
				log.info("Could not switch to Window Tab with Title: " + strTitle);
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * This Method closes all the opened tabs except window title passed as the
	 * parameter
	 *
	 * @param strParentTitle
	 *            Title of the Page except which all other window tabs will be
	 *            closed
	 * @return boolean
	 */
	public boolean closeWindowTabsExcept(String strParentTitle) {
		boolean result = false;
		try {
			Set<String> windowTabs = getDriver().getWindowHandles();
			for (String winTab : windowTabs) {
				if (!getDriver().switchTo().window(winTab).getTitle().equalsIgnoreCase(strParentTitle)) {
					log.info("Closing the Window Tab with Title: " + getDriver().switchTo().window(winTab).getTitle());
					getDriver().close();
					result = true;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Exception in closeWindowTabsExcept");
		}

		return result;
	}

	/**
	 * Description: This method is used to click on the button using its label text
	 *
	 * @param strButtonLabel
	 *            Exact Label name
	 * @return boolean
	 * @author ampn (Amit PN)
	 */
	public boolean clickOnButtonUsingLabelText(String strButtonLabel) {

		try {
			getDriver().findElement(By.xpath("//button[(text()='" + strButtonLabel + "')]")).isDisplayed();
			getDriver().findElement(By.xpath("//button[(text()='" + strButtonLabel + "')]")).click();
			waitForPageLoaded();
			TimeUnit.SECONDS.sleep(2);
			log.info("Clicked on Button with Label: " + strButtonLabel);
			return true;
		} catch (Exception e) {
			log.info("Could not find button element using (text() = 'strButtonLabel')");
		}

		try {
			getDriver().findElement(By.xpath("//button[(.='" + strButtonLabel + "')]")).isDisplayed();
			getDriver().findElement(By.xpath("//button[(text()='" + strButtonLabel + "')]")).click();
			log.info("Clicked on Button with Label: " + strButtonLabel);
			return true;
		} catch (Exception e) {
			log.info("Could not find button element using (. = 'strButtonLabel')");
		}
		return false;
	}

	/**
	 * Description: This methods gives the random 5 digit number
	 *
	 * @return String
	 * @throws Exception
	 */
	public String uniqueNum() throws Exception {
		String s_uniquenum = "" + System.currentTimeMillis();
		s_uniquenum = s_uniquenum.substring(s_uniquenum.length() - 5, s_uniquenum.length());
		// Get last 5 digits of Number generated
		return s_uniquenum;
	}

	public String getCurrentWindowTitle() {
		return getDriver().getTitle();
	}

	public boolean enterProgramParameters(String strProgramParameters) {
		try {
			// Fetch All commma "," separated parameter
			String[] parameters = strProgramParameters.split(",");
			System.out.println("No Of Parameter to be entered: " + parameters.length);
			String[] paramValues = new String[parameters.length * 2];

			// Fetch All equals "=" separated parameter
			for (int i = 0, k = 0; i < parameters.length; i++, k = k + 2) {
				paramValues[k] = parameters[i].split("=")[0].trim();
				paramValues[k + 1] = parameters[i].split("=")[1].trim();
			}

			// Verify # of parameters and its values are matching
			if (paramValues.length % 2 != 0) {
				System.err.println("# of Paramaeters and Its values are not matching..");
				return false;
			}
			for (int i = 0; i < paramValues.length; i = i + 2) {
				result = false;
				System.out.println("Parameter: " + paramValues[i] + " = " + paramValues[i + 1]);
				boolean isElementInputField = false;
				// Get the first character of the paramValues[i]
				// If 1st char = 1 then Its input field and If 1st char = 2 then Its select
				// field
				if (paramValues[i].startsWith("1")) {
					isElementInputField = true;
					paramValues[i] = paramValues[i].replace("1", "");
				} else if (paramValues[i].startsWith("2")) {
					paramValues[i] = paramValues[i].replace("2", "");
				}

				TimeUnit.SECONDS.sleep(2);
				if (isElementInputField) {
					// When Element is TextField
					try {
						// WebElement element_value1 =
						// getDriver().findElement(By.xpath("//tr[contains(@id,
						// 'basicReqBody:paramDynForm') and contains(.,'"+ paramValues[i] +
						// "')]//input"));
						WebElement element_value1 = getDriver()
								.findElement(By.xpath("//tr[contains(@id, 'basicReqBody:') and contains(.,'"
										+ paramValues[i] + "')]//input"));
						result = enterDataInTextBox(element_value1, paramValues[i + 1], true);

					} catch (Exception e) {
						// TODO: handle exception
						log.info("Element is not input field");
					}
				}

				// When Element is Select Field
				if (!result && !isElementInputField) {
					try {
						// WebElement element_value2 =
						// getDriver().findElement(By.xpath("//tr[contains(@id,
						// 'basicReqBody:paramDynForm') and contains(.,'" + paramValues[i] +
						// "')]//select"));
						WebElement element_value2 = getDriver()
								.findElement(By.xpath("//tr[contains(@id, 'basicReqBody:') and contains(.,'"
										+ paramValues[i] + "')]//select"));
						result = selectDropdownBy(element_value2, paramValues[i + 1], DropDownSelectBy.VisibleText);
					} catch (Exception e) {
						// TODO: handle exception
						log.info("Element is not Select field");
					}
				}

				if (!result) {
					log.info("Could not enter/select the value for Parameter: " + paramValues[i]);
					return false;
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;

	}

	public boolean scrollTillVisibilityOfElement(WebElement ele) {

		boolean result = false;
		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();

			// if (cmnLib.waitForElementToBeVisible(ele)) {
			// This will scroll the page till the element is found
			js.executeScript("arguments[0].scrollIntoView();", ele);
			log.info("Scrolled till VisibilityOfElement");
			result = true;
			// }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Exception: scrollTillVisibilityOfElement");
		}
		return result;
	}

	public boolean waitForElementToBeInvisible(WebElement element) {

		try {
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(getDriver(), 60)
					.ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.invisibilityOf(element));
			log.info("WebElement is Invisible");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	public boolean waitForTextToDisappearOnElement(WebElement element, String text) {
		boolean flag = false;
		try {
			if (waitForElementToBeVisible(element)) {
				WebDriverWait wait = new WebDriverWait(getDriver(), 20);
				if (wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, text)))) {
					log.info("Text disappeared from element");
					flag = true;
				}
			} else {
				log.info("WebElement not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to wait for text to disappear from element");
		}
		return flag;
	}

	public boolean moveToElementAndClick(WebElement element) {
		boolean result = false;
		try {
			Actions actions = new Actions(getDriver());
			actions.moveToElement(element).click().build().perform();
			log.info("Moved to given element and clicked");
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to move to given element and click");
		}
		return result;

	}

	public boolean moveToElement(WebElement element) {
		boolean result = false;
		try {
			Actions actions = new Actions(getDriver());
			actions.moveToElement(element).build().perform();
			log.info("Moved to given element");
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to move to given element");
		}
		return result;

	}

	public boolean waitForTextToBePresentInElement(WebElement element, String text) {
		boolean flag = false;

		try {
			new WebDriverWait(getDriver(), 60).until(ExpectedConditions.textToBePresentInElement(element, text));
			flag = true;
			log.info("Text present on element");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to wait for exact text to present on element");
		}
		return flag;
	}

	public boolean pressEnterKey() {
		boolean flag = false;
		try {
			Actions actions = new Actions(getDriver());
			actions.sendKeys(Keys.ENTER).build().perform();
			log.info("Pressed ENTER key");
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to Press Enter key");
		}
		return flag;
	}

	public boolean waitForStalenessOfElement(WebElement element) {
		boolean flag = false;
		try {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.stalenessOf(element));
			flag = true;
			log.info("Element becomes stale");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Unable to wait for element to be stale");
		}
		return flag;
	}

	public boolean waitForElementToBeVisible(WebElement element, long lSeconds) {

		if (element == null) {
			return false;
		}
		try {
			getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(getDriver(), lSeconds)
					.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.isDisplayed();
			log.info("WebElement exists");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("WebElement does not exist");
			return false;
		} finally {
			getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
	}

	public boolean closeCurrentWindow() {
		boolean result = false;

		try {
			getDriver().close();
			result = true;
			log.info("Closed current window");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to close current window");
		}
		return result;
	}

	public void maximizeWindow() {
		getDriver().manage().window().maximize();
	}

	/**
	 * Returns date. If number of days mentioned then days will be added to today's
	 * date. If todays date needed, then pass number of days as 0
	 *
	 * @param format
	 *            : format in which date has to be generated
	 * @return String with value date
	 * @throws Exception
	 */
	public String futureDate(String format, long noOfDays) {
		String reqDateFormat = format;
		String returnDate = "";
		Date date = new Date();
		if (reqDateFormat.indexOf("\"") != -1) {
			reqDateFormat = reqDateFormat.replace("\"", "");
		}
		SimpleDateFormat reqFormat = new SimpleDateFormat(reqDateFormat);
		if (noOfDays != 0) {
			int oneDay = 1000 * 60 * 60 * 24;
			String nextDate = reqFormat.format(date.getTime() + (oneDay * noOfDays));
			returnDate = nextDate;
		} else {
			returnDate = reqFormat.format(date.getTime());
		}
		return returnDate;

	}

	/**
	 * @return boolean
	 * @author Shailee Jha This method is used to enter data into the TextBox
	 */
	public boolean enterDataInTextfield(WebElement ele, String strValue) {
		boolean result = false;
		try {
			TimeUnit.SECONDS.sleep(1);
			ele.isDisplayed();
			ele.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, strValue);
			log.info("String value entered in TextBox is: " + strValue);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("String value '" + strValue + "' is not entered in TextBox");
			result = false;
		}
		return result;
	}

	public boolean selectValueFromInputComboBox(String inputElementLabelName, String strValueToSelect) {
		boolean result = false;
		try {
			getDriver()
					.findElement(By
							.xpath("//tr/td/label[contains(text(),'" + inputElementLabelName + "')]/../parent::tr//a"))
					.click();
			TimeUnit.SECONDS.sleep(2);
			getDriver().findElement(By.xpath("//li[text()='" + strValueToSelect + "']")).click();
			TimeUnit.SECONDS.sleep(1);
			result = true;
			log.info("Selected the Value from the input combobox");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Exception in selectValueFromInputComboBox");
		}
		return result;

	}

	public Set<String> getAllWindowHandles() {
		return getDriver().getWindowHandles();
	}

	public String getCurrentHandle() {
		return getDriver().getWindowHandle();
	}

	public void switchToWindow(String strTitle) throws InterruptedException {
		if (strTitle.isEmpty())
			getDriver().switchTo().defaultContent();
		else
			getDriver().switchTo().window(strTitle);
	}

	public boolean verifySearchedRecordExists(String strPersonName_OR_PersonNumber) {
		boolean exists = false;
		try {
			List<WebElement> TableRows = getDriver()
					.findElements(By.xpath("//table[@summary='Search Results']/tbody/tr"));
			System.out.println("No Of Rows in Search Results Table: " + TableRows.size());
			if (TableRows.size() > 0) {
				for (int i = 1; i <= TableRows.size(); i++) {
					if (getDriver()
							.findElement(By.xpath("//table[@summary='Search Results']/tbody/tr[" + i
									+ "]//*[contains(text(), '" + strPersonName_OR_PersonNumber + "')]"))
							.isDisplayed()) {
						// driver.findElement(By.xpath("//table[@summary='Search
						// Results']/tbody/tr["+i+"]//*[contains(text(),
						// '"+strPersonName_OR_PersonNumber+"')]")).click();
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

	public boolean scrollToPageBottomByJSE() {
		boolean result = false;
		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			log.info("Page scrolldown");
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Page not Scroll down");
		}
		return result;
	}

	public boolean scrollUpByJSE() {
		boolean result = false;
		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scroll(0, 0)");
			log.info("Page scrollup");
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Page not Scroll up");
		}
		return result;
	}

	public boolean verifyHeader(String hdrName) {

		boolean flag = false;
		try {
			WebElement hdrElement = getDriver().findElement(By.xpath("//*[contains(text(),'" + hdrName
					+ "') and (contains(@class,'xnq') or contains(@class,'xnp') or contains(@class,'x1lg'))]"));
			hdrElement.isDisplayed();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to click element " + hdrName);
		}
		return flag;
	}

	public boolean verifyTextOnElement(WebElement element, String text) {

		boolean flag = false;
		try {
			String strValue = element.getText();
			if (strValue.equalsIgnoreCase(text)) {
				flag = true;
				log.info("Text on element displayed as expected: " + strValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Unable to verify text on element");
		}
		return flag;
	}

	public boolean verifySearchedRecordExistsAndClickonResult(String strPersonName_OR_PersonNumber,
			boolean clickonResult) {
		boolean exists = false;
		try {
			List<WebElement> TableRows = getDriver()
					.findElements(By.xpath("//table[@summary='Search Results']/tbody/tr"));
			System.out.println("No Of Rows in Search Results Table: " + TableRows.size());
			if (TableRows.size() > 0) {
				for (int i = 1; i <= TableRows.size(); i++) {
					if (getDriver()
							.findElement(By.xpath("//table[@summary='Search Results']/tbody/tr[" + i
									+ "]//*[contains(text(), '" + strPersonName_OR_PersonNumber + "')]"))
							.isDisplayed()) {

						if (clickonResult == true) {
							getDriver()
									.findElement(By.xpath("//table[@summary='Search Results']/tbody/tr[" + i
											+ "]//*[contains(text(), '" + strPersonName_OR_PersonNumber + "')]"))
									.click();
						}
						exists = true;
						log.info("Searched record exists");
						break;
					}
				}
			} else {
				log.info("TableRows row count is less than zero !!");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.info("Searched record does not exist");
		}
		return exists;
	}

	public WebElement getElement(By by) {

		try {
			WebElement element = getDriver().findElement(by);
			return element;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			log.info("Unable to getElement " + by);
			return null;
		}
	}

}
