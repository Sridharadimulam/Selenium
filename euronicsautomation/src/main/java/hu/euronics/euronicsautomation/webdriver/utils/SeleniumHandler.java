/**
 * This code is part of WS Publishing.
 * This code is developed by Techwave India Pvt Limited, Hyderabad.
 * Do not make any changes.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * Copyright (C) Techwave India Pvt Limited, Hyderabad.
 * All Rights Reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
 */

package hu.euronics.euronicsautomation.webdriver.utils;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.testdefinitions.TestConstants;

public abstract class SeleniumHandler implements Handler {
	private static final Logger logger = Logger.getLogger(SeleniumHandler.class.getSimpleName());
	private DesiredCapabilities capabilities = null;
	private WebDriver driver = null;

	protected String browser;
	protected String desiredCapabilities;
	protected String driverPath;
	protected String url;

	/**
	 * isWindows checks whether the os on which the tests are running is windows
	 * 
	 * @return
	 */
	public static boolean isWindows() {
		String osName = System.getProperty(TestConstants.SystemProperty.OS_NAME).toUpperCase();
		if (osName.startsWith("WINDOWS")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * initWindows initializes the webdriver given required browser name,browser
	 * capabilities and driver path on windows environment
	 * 
	 * @param pstrBrowser
	 * @param pDesiredCapabilities
	 * @param pstrDriversPath
	 * @throws TestException
	 */
	protected void initWindows(String browser, String desiredCapabilities, String driverPath) throws TestException {
		String driverPathFull;
		try {
			DesiredCapabilities dc = getCapabilityMap(desiredCapabilities);

			if (WebDriverConstants.Browsers.FIREFOX.equalsIgnoreCase(browser.toUpperCase().trim())) {
				driverPathFull = driverPath + File.separator + "geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", driverPathFull);
				capabilities = DesiredCapabilities.firefox();
				capabilities.merge(dc);
				driver = new FirefoxDriver(capabilities);
			} else if (WebDriverConstants.Browsers.CHROME.equalsIgnoreCase(browser.toUpperCase().trim())) {
				driverPathFull = driverPath + File.separator + "chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPathFull);
				capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = getChromeOptions("--lang=en");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities.merge(dc);
				driver = new ChromeDriver(capabilities);
			} else {
				driverPathFull = driverPath + File.separator + "IEDriverServer.exe";
				System.setProperty("webdriver.ie.driver", driverPathFull);
				capabilities = DesiredCapabilities.internetExplorer();
				capabilities.merge(dc);
				driver = new InternetExplorerDriver(capabilities);
			}
		} catch (Exception e) {
			logger.error("initialising selenium driver failed with error" + e.getMessage());
			throw new TestException("initialising selenium driver failed with error" + e.getMessage());
		}
	}

	private ChromeOptions getChromeOptions(String language) {
		ChromeOptions chromeoptions = new ChromeOptions();
		chromeoptions.addArguments(language);
		return chromeoptions;
	}

	/**
	 * initUnix initializes the webdriver given required browser name,browser
	 * capabilities and driver path on unix environment
	 * 
	 * @param pstrBrowser
	 * @param pDesiredCapabilities
	 * @param pstrDriversPath
	 * @throws TestException
	 */
	protected void initUnix(String browser, String desiredCapabilities, String driverPath) throws TestException {
		String driverPathFull;
		try {
			DesiredCapabilities dc = getCapabilityMap(desiredCapabilities);

			if (WebDriverConstants.Browsers.CHROME.equalsIgnoreCase(browser.toUpperCase().trim())) {
				driverPathFull = driverPath + File.separator + "chromedriver";
				System.setProperty("webdriver.chrome.driver", driverPathFull);
				capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = getChromeOptions("--lang=en");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities.merge(dc);
				driver = new ChromeDriver(capabilities);
			} else {
				throw new TestException("unsupported browser");
			}
		} catch (Exception e) {
			logger.error("initialising selenium driver failed with error" + e.getMessage());
			throw new TestException("initialising selenium driver failed with error" + e.getMessage());
		}
	}

	/**
	 * getCapabilityMap is used to use a map of capabilties from a string when a
	 * string of capabilities having ; as separator in provided
	 * 
	 * @param pstrCapabilities
	 * @return
	 */
	protected static DesiredCapabilities getCapabilityMap(String pstrCapabilities) {
		DesiredCapabilities desiredCapabilites = new DesiredCapabilities();
		String[] aCapabilities = pstrCapabilities.split(TestConstants.CAPABILITIES_FIELD_DELIMITER);
		for (int i = 0; i < aCapabilities.length; i++) {
			String[] aNameValue = aCapabilities[i].split(TestConstants.CAPABILITIES_VALUES_DELIMITER);
			String name = aNameValue[0];
			String value = aNameValue[1];
			if ("TRUE".equalsIgnoreCase(value.trim()) || "FALSE".equalsIgnoreCase(value.trim())) {
				desiredCapabilites.setCapability(name, Boolean.parseBoolean(value));
			} else {
				desiredCapabilites.setCapability(name, value);
			}
		}
		return desiredCapabilites;
	}

	/**
	 * Open method is used to navigate to a URL
	 * 
	 * @param pstrUL
	 * @return
	 * @throws TestException
	 */
	public void open() throws TestException {
		try {
			if (driver == null) {
				throw new TestException("selenium webdriver not initialised");
			}
			String url = System.getProperty(TestConstants.SystemProperty.URL);
			logger.info("opening the url " + url);
			driver.navigate().to(url);
			driver.manage().window().maximize();
			String driverName = driver.getClass().toString();
			if (driverName.contains("InternetExplorerDriver") && driver.getTitle().contains("Certificate")) {
				driver.navigate().to("javascript:document.getElementById('overridelink').click()");
			}
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("opening url failed with with error" + e.getMessage());
			throw new TestException(
					"opening url failed with with error " + e.getMessage() + " ,please find the snapshot at location",
					filePath);
		}

	}

	/**
	 * getLocator method is used to build and return By locator to identify an
	 * element
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @return
	 * @throws TestException
	 */
	private By getLocator(String pstrLocatorType, String pstrLocatorValue) throws TestException {
		By byLocator;
		if (pstrLocatorType.equalsIgnoreCase(WebDriverConstants.Locators.ID)) {
			byLocator = By.id(pstrLocatorValue);
		} else if (pstrLocatorType.equalsIgnoreCase(WebDriverConstants.Locators.XPATH)) {
			byLocator = By.xpath(pstrLocatorValue);
		} else if (pstrLocatorType.equalsIgnoreCase(WebDriverConstants.Locators.NAME)) {
			byLocator = By.name(pstrLocatorValue);
		} else if (pstrLocatorType.equalsIgnoreCase(WebDriverConstants.Locators.LINKTEXT)) {
			byLocator = By.linkText(pstrLocatorValue);
		} else if (pstrLocatorType.equalsIgnoreCase(WebDriverConstants.Locators.CLASSNAME)) {
			byLocator = By.className(pstrLocatorValue);
		} else if (pstrLocatorType.equalsIgnoreCase(WebDriverConstants.Locators.CSSSELECTOR)) {
			byLocator = By.cssSelector(pstrLocatorValue);
		} else if (pstrLocatorType.equalsIgnoreCase(WebDriverConstants.Locators.PARTIALLINKTEXT)) {
			byLocator = By.partialLinkText(pstrLocatorValue);
		} else if (pstrLocatorType.equalsIgnoreCase(WebDriverConstants.Locators.TAGNAME)) {
			byLocator = By.tagName(pstrLocatorValue);
		} else {
			throw new TestException("invalid/unsupported locator type " + pstrLocatorType);
		}
		return byLocator;
	}

	/**
	 * fluentDisplayed is used to for synchronizing a WebElement this function
	 * checks whether a particular exists and is displayed for a time as
	 * specified in the WebDriverConstants file
	 * 
	 * @param pbyLocator
	 * @param driver
	 * @param elementName
	 * @return
	 * @throws TestException
	 */
	private WebElement fluentDisplayed(final By pbyLocator, WebDriver driver, String elementName) throws TestException {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(WebDriverConstants.Time.WAIT_TIME, TimeUnit.SECONDS)
					.pollingEvery(WebDriverConstants.Time.POLLING_TIME, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
			WebElement element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					WebElement element = driver.findElement(pbyLocator);
					if (element.isDisplayed()) {
						return element;
					} else {
						return null;
					}

				}
			});
			return element;
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("Could not locate the element  " + elementName + " after waiting for "
					+ WebDriverConstants.Time.WAIT_TIME + "  Seconds");
			throw new TestException("Could not locate the element  " + elementName + " after waiting for "
					+ WebDriverConstants.Time.WAIT_TIME + "  Seconds," + " please find the screen shot at location ",
					filePath);
		}

	}

	/**
	 * fluentWait is used to for synchronizing a WebElement, it differs from
	 * fluentDisplayed as this function as this does not check for element
	 * display
	 * 
	 * @param pbyLocator
	 * @param driver
	 * @param elementName
	 * @return
	 * @throws TestException
	 */
	private WebElement fluentWait(final By pbyLocator, WebDriver driver, String elementName) throws TestException {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(WebDriverConstants.Time.WAIT_TIME, TimeUnit.SECONDS)
					.pollingEvery(WebDriverConstants.Time.POLLING_TIME, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
			WebElement element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					WebElement element = driver.findElement(pbyLocator);
					return element;

				}
			});
			return element;
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("Could not locate the element  " + elementName + " after waiting for "
					+ WebDriverConstants.Time.WAIT_TIME + "  Seconds");
			throw new TestException("Could not locate the element  " + elementName + " after waiting for "
					+ WebDriverConstants.Time.WAIT_TIME + "  Seconds," + " please find the screen shot at location ",
					filePath);
		}

	}

	/**
	 * findElement is used to locate an element given a locator and value
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @return
	 * @throws TestException
	 */
	public WebElement findElement(String pstrLocatorType, String pstrLocatorValue, String elementName)
			throws TestException {
		By byLocator = getLocator(pstrLocatorType, pstrLocatorValue);
		WebElement element = fluentDisplayed(byLocator, driver, elementName);
		return element;
	}

	/**
	 * findElement is used to find an element which is child of a given element
	 * 
	 * @param parent
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @return
	 * @throws TestException
	 */
	public WebElement findElement(WebElement parent, String pstrLocatorType, String pstrLocatorValue,
			String elementName) throws TestException {
		int timer = 0;
		boolean bFound = false;
		WebElement child = null;
		By byLocator = getLocator(pstrLocatorType, pstrLocatorValue);
		do {
			try {
				timer = timer + 1;
				wait(WebDriverConstants.Time.POLLING_TIME);
				child = parent.findElement(byLocator);
				if (child.isDisplayed() && child.isEnabled()) {
					bFound = true;
					break;
				}
			} catch (Exception e) {
				continue;
			}
		} while (timer < WebDriverConstants.Time.WAIT_TIME);
		if (bFound) {
			return child;
		} else {
			String filePath = captureScreenShoot();
			logger.error("element " + elementName + " not found after waiting for " + WebDriverConstants.Time.WAIT_TIME
					+ " Seconds");
			throw new TestException("element " + elementName + " not found after waiting for "
					+ WebDriverConstants.Time.WAIT_TIME + " Seconds please find the error snashot at location",
					filePath);
		}

	}

	/**
	 * findElement is used to find an element which is child of a given element
	 * and with a given attribute and its value
	 * 
	 * @param parent
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @param attribute
	 * @param expectedvalue
	 * @return
	 * @throws TestException
	 */
	public WebElement findElement(WebElement parent, String pstrLocatorType, String pstrLocatorValue, String attribute,
			String expectedvalue) throws TestException {
		WebElement child = null;
		boolean bFound = false;
		String actualvalue;
		try {
			By byLocator = getLocator(pstrLocatorType, pstrLocatorValue);
			List<WebElement> listChildren = parent.findElements(byLocator);
			Iterator<WebElement> iterChildern = listChildren.iterator();
			while (iterChildern.hasNext()) {
				child = iterChildern.next();
				if (attribute.equals("GETTEXT")) {
					actualvalue = child.getText().trim();
				} else {
					actualvalue = child.getAttribute(attribute).trim();
				}
				if (actualvalue.equals(expectedvalue.trim())) {
					bFound = true;
					break;
				}
			}
			if (bFound) {
				return child;
			} else {
				throw new TestException("Could not locate WebElement with attribute name" + attribute
						+ " and having attribute value " + expectedvalue);
			}

		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("findElement failed with error " + e.getMessage());
			throw new TestException(
					"findElement failed with error " + e.getMessage() + ", please find the screen shot at location ",
					filePath);
		}
	}

	/**
	 * elementExists searches for the existence of a element
	 * 
	 * @param pstrParentLocatorType
	 * @param pstrParentLocatorValue
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @param attribute
	 * @param expectedvalue
	 * @param elementName
	 * @throws TestException
	 */
	public void elementExists(String pstrParentLocatorType, String pstrParentLocatorValue, String pstrLocatorType,
			String pstrLocatorValue, String attribute, String expectedvalue, String elementName) throws TestException {
		if (StringUtils.isBlank(expectedvalue)) {
			return;
		}
		By byParent = getLocator(pstrParentLocatorType, pstrParentLocatorValue);
		WebElement parent = fluentWait(byParent, driver, elementName);
		findElement(parent, pstrLocatorType, pstrLocatorValue, attribute, expectedvalue);
		logger.info(elementName + " exists");
	}

	public void elementExists(String pstrParentLocatorType, String pstrParentLocatorValue, String pstrLocatorType,
			String pstrLocatorValue, String elementName) throws TestException {
		try {
			WebElement parent = findElement(pstrParentLocatorType, pstrParentLocatorValue, elementName);
			By byLocator = getLocator(pstrLocatorType, pstrLocatorValue);
			// parent.findElement(byLocator);
			findElement(parent, pstrLocatorType, pstrLocatorValue, elementName);
			logger.info(elementName + " exists");
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("element " + elementName + " not found");
			throw new TestException("element " + elementName + " not found, elementExists failed with error "
					+ e.getMessage() + ", please find the screen shot at location ", filePath);
		}
	}

	public void elementExists(String pstrLocatorType, String pstrLocatorValue, String elementName)
			throws TestException {
		logger.info("verifying " + elementName + " is displayed");
		By byLocator = getLocator(pstrLocatorType, pstrLocatorValue);
		fluentWait(byLocator, driver, elementName);
		logger.info(elementName + " exists");

	}

	/**
	 * assertElementEmpty is a assertion to make sure that a element has no text
	 * associated with it
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @param elementName
	 * @throws TestException
	 */
	public void assertElementEmpty(String pstrLocatorType, String pstrLocatorValue, String elementName)
			throws TestException {
		WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
		String text = element.getText();
		if (StringUtils.isNotBlank(text)) {
			logger.error("field " + elementName + " is not empty");
			String filePath = captureScreenShoot();
			throw new TestException(
					"Element " + elementName + " is not empty, please find the screen shot at location ", filePath);
		}
		logger.info("field " + elementName + " is verified as empty");
	}

	/**
	 * isElementEnabled checks for a elements state
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @param expectedEnableStatus
	 * @param elementName
	 * @throws TestException
	 */
	public void isElementEnabled(String pstrLocatorType, String pstrLocatorValue, String expectedEnableStatus,
			String elementName) throws TestException {

		WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
		String currentEnableStatus = "false";
		if (element.isEnabled()) {
			currentEnableStatus = "true";
		}
		if (expectedEnableStatus.equalsIgnoreCase(currentEnableStatus)) {
			logger.info(elementName + " - ENABLE CHECK - found to be : " + currentEnableStatus + "  --[Expected =  "
					+ expectedEnableStatus + " ]");
		} else {
			logger.error("Expected Condition : " + expectedEnableStatus + " failed with Actual Condition"
					+ currentEnableStatus);
			String filePath = captureScreenShoot();
			throw new TestException("Element " + elementName + " enable check - found to be : " + currentEnableStatus
					+ " --[ Expected =  " + expectedEnableStatus + " ] , Please find the screen shot at location ",
					filePath);
		}
	}

	/**
	 * 
	 * @param element
	 * @throws TestException
	 */
	public void waitForElementClickable(WebElement element, String elementName) throws TestException {
		int timer = 0;
		boolean clickable = false;
		try {
			while (timer < WebDriverConstants.Time.WAIT_TIME) {
				Thread.sleep(WebDriverConstants.Time.SLEEP_MILLISECONDS);
				if (element.isDisplayed() && element.isEnabled()) {
					clickable = true;
					break;
				}
				timer = timer + 1;
			}
			if (!clickable) {
				throw new TestException("element " + elementName + " not clickable after waiting for  "
						+ WebDriverConstants.Time.WAIT_TIME + " seconds");
			}
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("waitForElementClickable Failed with error " + e.getMessage());
			throw new TestException("waitForElementClickable Failed with error " + e.getMessage()
					+ ", please find the screen shot at location ", filePath);
		}

	}

	/**
	 * SendKeys method is used to set a value to a WebElement
	 * 
	 * @param pstrLocatorType
	 *            : the locator method used to identify an element
	 * @param pstrLocatorValue
	 *            : the locator value used to identify the element
	 * @param pstrValue
	 *            : value to be set
	 * @return : true/false based of functions success
	 * @throws TestException
	 * @throws WebDriverException
	 */
	public void sendKeys(String pstrLocatorType, String pstrLocatorValue, String pstrValue, String elementName)
			throws TestException {
		if (StringUtils.isBlank(pstrValue) || pstrValue.equalsIgnoreCase("NULL")) {
			return;
		}
		try {
			logger.info("setting " + elementName + " as " + pstrValue);
			WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
			element.clear();
			element.sendKeys(pstrValue);
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error(" sendKeys failed with with error " + e.getMessage());
			throw new TestException(
					" sendKeys failed with with error " + e.getMessage() + " ,please find the snapshot at location",
					filePath);
		}

	}

	public void sendKeys(WebElement parent, String pstrLocatorType, String pstrLocatorValue, String pstrValue,
			String elementName) throws TestException {
		if (StringUtils.isBlank(pstrValue)) {
			return;
		}
		try {
			logger.info("setting " + elementName + " as " + pstrValue);
			WebElement element = findElement(parent, pstrLocatorType, pstrLocatorValue, elementName);
			element.clear();
			element.sendKeys(pstrValue);
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error(" sendKeys failed with with error " + e.getMessage());
			throw new TestException(
					" sendKeys failed with with error " + e.getMessage() + " ,please find the snapshot at location",
					filePath);
		}

	}

	public void jsSendKeys(String pstrLocatorType, String pstrLocatorValue, String value, String elementName)
			throws TestException {
		try {
			logger.info("clicking on " + elementName);
			WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			value = "'" + value + "'";
			executor.executeScript("arguments[0].value=" + value + ";", element);
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("clicking element " + elementName + "failed with error " + e.getMessage());
			throw new TestException("clicking element " + elementName + "failed with error " + e.getMessage()
					+ ", please find the screeshot at location", filePath);
		}
	}

	public void sendKeysWithoutClear(String pstrLocatorType, String pstrLocatorValue, String pstrValue,
			String elementName) throws TestException {
		logger.info("setting " + elementName + " as " + pstrValue);
		WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
		if (StringUtils.isNotBlank(pstrValue)) {
			element.sendKeys(pstrValue);
		}
	}

	/**
	 * getTitle gets the current window title
	 * 
	 * @return
	 */
	public String getTitle() {
		String result = driver.getTitle();
		return result;
	}

	/**
	 * CheckAndSwitchToAlert is used to check if an alert is present
	 * 
	 * @return
	 */
	private boolean checkAndSwitchToAlert() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	/**
	 * Click method is used to click on a WebElement
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @return
	 * @throws TestException
	 */
	public void click(WebElement element, String elementName) throws TestException {
		try {
			element.click();
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error(elementName + " click failed with error " + e.getMessage());
			throw new TestException(elementName + " click failed with error " + e.getMessage()
					+ ", please find the screen shot at location ", filePath);
		}
	}

	/**
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @param state
	 * @param elementName
	 * @throws TestException
	 */
	public void setCheckBox(String pstrLocatorType, String pstrLocatorValue, boolean state, String elementName)
			throws TestException {
		WebElement chkBox = findElement(pstrLocatorType, pstrLocatorValue, elementName);
		boolean bClick = state ^ chkBox.isSelected();
		if (bClick) {
			chkBox.click();
		}
	}

	public String isCheckBoxSelected(String pstrLocatorType, String pstrLocatorValue, String elementName)
			throws TestException {
		WebElement chkBox = findElement(pstrLocatorType, pstrLocatorValue, elementName);
		if (chkBox.isSelected()) {
			return "TRUE";
		} else {
			return "FALSE";
		}
	}

	/**
	 * Click method is used to click on a Button WebElement using javascript
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @return
	 * @throws TestException
	 */
	public void jsClick(String pstrLocatorType, String pstrLocatorValue, String elementName) throws TestException {
		try {
			logger.info("clicking on " + elementName);
			WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("clicking element " + elementName + "failed with error " + e.getMessage());
			throw new TestException("clicking element " + elementName + "failed with error " + e.getMessage()
					+ ", please find the screeshot at location", filePath);
		}
	}

	public void jsClick(WebElement element, String elementName) throws TestException {
		try {
			element.click();
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("clicking element " + elementName + "failed with error " + e.getMessage());
			throw new TestException("clicking element " + elementName + "failed with error " + e.getMessage()
					+ ", please find the screeshot at location", filePath);
		}
	}

	public void jsClick(String pstrParentLocatorType, String pstrParentLocatorValue, String pstrLocatorType,
			String pstrLocatorValue, String elementName) throws TestException {
		try {

			logger.info("clicking on " + elementName);
			WebElement parent = findElement(pstrParentLocatorType, pstrParentLocatorValue, elementName);
			WebElement child = findElement(parent, pstrLocatorType, pstrLocatorValue, elementName);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", child);
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("clicking element " + elementName + "failed with error " + e.getMessage());
			throw new TestException("clicking element " + elementName + "failed with error " + e.getMessage()
					+ ", please find the screeshot at location", filePath);
		}
	}

	public void jsClick(String pstrParentLocatorType, String pstrParentLocatorValue, String pstrLocatorType,
			String pstrLocatorValue, String attributeName, String expected, String elementName) throws TestException {
		try {
			logger.info("clicking on " + elementName);
			WebElement parent = findElement(pstrParentLocatorType, pstrParentLocatorValue, elementName);
			WebElement child = findElement(parent, pstrLocatorType, pstrLocatorValue, attributeName, expected);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", child);
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("clicking element " + elementName + "failed with error " + e.getMessage());
			throw new TestException("clicking element " + elementName + "failed with error " + e.getMessage()
					+ ", please find the screeshot at location", filePath);
		}
	}

	/**
	 * SwitchToWindow switches the control of Selenium WebDriver to a window
	 * with a given title
	 * 
	 * @param title
	 *            : the title of the window to which the webdrivers control has
	 *            to switch
	 * @throws TestException
	 */
	public void switchToWindow(String pstrTitle) throws TestException {
		boolean bFound = false;
		try {

			Thread.sleep(WebDriverConstants.Time.SLEEP_MILLISECONDS);
			Iterator<String> windowIterator = driver.getWindowHandles().iterator();
			while (windowIterator.hasNext()) {
				String windowHandle = windowIterator.next();
				WebDriver window = driver.switchTo().window(windowHandle);
				String windowTitle = window.getTitle();
				if (pstrTitle.equalsIgnoreCase(windowTitle)) {
					driver.switchTo().window(windowHandle);
					bFound = true;
					break;
				}
			}
			if (!bFound) {
				throw new TestException("PopUp Window with title " + pstrTitle + " not found");
			}
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("switchToWindow failed with error " + e.getMessage());
			throw new TestException(
					"switchToWindow failed with error " + e.getMessage() + ", please find the screen shot at location ",
					filePath);
		}

	}

	/**
	 * SwitchToFrame switches the control to a frame
	 * 
	 * @param framename
	 *            : the frame name to which the control has to be transferred
	 * @throws TestException
	 */
	public void switchToFrame(String pstrLocatorType, String pstrLocatorValue, String elementName)
			throws TestException {
		driver.switchTo().defaultContent();
		WebElement frame = findElement(pstrLocatorType, pstrLocatorValue, elementName);
		driver.switchTo().frame(frame);
	}

	/**
	 * switchToChildFrame switches to a child frame,this is useful when we have
	 * nested frames
	 * 
	 * @param frame
	 * @param elementName
	 * @throws TestException
	 */
	public void switchToChildFrame(String pstrParentLocatorType, String pstrParentLocatorValue, String pstrLocatorType,
			String pstrLocatorValue, String elementName) throws TestException {
		WebElement parent = findElement(pstrParentLocatorType, pstrParentLocatorValue, elementName);
		WebElement child = findElement(parent, pstrLocatorType, pstrLocatorValue, elementName);
		driver.switchTo().frame(child);
	}

	/**
	 * teardown method is a used to close the driver
	 * 
	 * @throws TestException
	 */
	public void tearDown() {
		if (driver != null) {
			driver.close();
		}
	}

	/**
	 * CheckPopupWindow method is used to check if a pop up window exists with a
	 * given title. Note: This is useful when a popup windows whose title is
	 * smae as that of parent window title
	 * 
	 * @param title
	 * @param curWindowHandle
	 * @return
	 */
	private boolean checkPopupWindow(String pstrTitle, String pstrCurWindowHandle) {
		boolean result = false;
		Iterator<String> itrWindowHandles = driver.getWindowHandles().iterator();
		while (itrWindowHandles.hasNext()) {
			String windowHandle = itrWindowHandles.next();
			driver.switchTo().window(windowHandle);
			String newWindowTitle = driver.getTitle();
			String newWindowHandle = driver.getWindowHandle();
			if (newWindowTitle.equalsIgnoreCase(pstrTitle) && !newWindowHandle.equalsIgnoreCase(pstrCurWindowHandle)) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * SwitchToPopUpWindow method is used to check if a pop up window exists
	 * with a given title. Note: This is useful when a popup windows whose title
	 * is same as that of parent window title
	 * 
	 * @param pstrTitle
	 * @param pstrCurWindowHandle
	 * @return
	 * @throws TestException
	 */
	public void switchToPopUpWindow(String pstrTitle, String pstrCurWindowHandle) throws TestException {
		boolean result = false;
		int count = 0;
		try {
			int timeoutSeconds = WebDriverConstants.Time.WAIT_TIME;
			while (count < timeoutSeconds && !result) {
				result = checkPopupWindow(pstrTitle, pstrCurWindowHandle);
				if (!result) {
					driver.switchTo().window(pstrCurWindowHandle);
				}
				count = count + 1;
				Thread.sleep(WebDriverConstants.Time.POLLING_TIME);
			}
			if (!result) {
				throw new TestException("PopUp Window with title " + pstrTitle + " not found");
			}
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("switchToPopUpWindow failed with error " + e.getMessage());
			throw new TestException("switchToPopUpWindow failed with error " + e.getMessage()
					+ ", please find the screen shot at location ", filePath);
		}
	}

	/**
	 * GetCurrentWindowHandle returns the WindowHandle to which the Selenium
	 * Wendriver is set
	 * 
	 * @return
	 * @throws TestException
	 */
	public String getCurrentWindowHandle() {
		String curWindowHandle = "";
		curWindowHandle = driver.getWindowHandle();
		return curWindowHandle;
	}

	/**
	 * Alert method is used to handle an alert window
	 * 
	 * @param alertValue
	 * @return
	 * @throws TestException
	 */

	public void alert(String pstrAlertValue) throws TestException {
		boolean result = false;
		int count = 0;
		try {
			while (count < WebDriverConstants.Time.WAIT_TIME && !result) {
				result = checkAndSwitchToAlert();
				Thread.sleep(WebDriverConstants.Time.SLEEP_MILLISECONDS);
				count = count + 1;
			}
			if (result) {
				if (pstrAlertValue.equalsIgnoreCase(WebDriverConstants.ALERT.ACCEPT)) {
					driver.switchTo().alert().accept();
				} else if (pstrAlertValue.equalsIgnoreCase(WebDriverConstants.ALERT.DISMISS)) {
					driver.switchTo().alert().dismiss();
				}
			}

		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("alert failed with error " + e.getMessage());
			throw new TestException(
					"alert failed with error " + e.getMessage() + ", please find the screen shot at location ",
					filePath);
		}

	}

	/**
	 * 
	 * @param parent
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @param pstrValue
	 * @return
	 * @throws TestException
	 */
	public void textAreaSelectAll(String pstrLocatorType, String pstrLocatorValue, String elementName)
			throws TestException {
		try {
			WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
			element.click();
			Thread.sleep(WebDriverConstants.Time.SLEEP_MILLISECONDS);
			String selectAll = Keys.chord(Keys.CONTROL, "a");
			element.sendKeys(selectAll);
			Thread.sleep(WebDriverConstants.Time.SLEEP_MILLISECONDS);
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("textAreaSelectAll failed with error " + e.getMessage());
			throw new TestException("textAreaSelectAll failed with error " + e.getMessage()
					+ ", please find the screen shot at location ", filePath);
		}
	}

	/**
	 * comboSelect is used to select an item in a combo
	 * 
	 * @param locatorType
	 * @param locatorValue
	 * @param item
	 * @return
	 * @throws TestException
	 */
	public void comboSelect(String pstrLocatorType, String pstrLocatorValue, String tagName, String pstrItem,
			String elementName) throws TestException {
		try {
			WebElement combo = findElement(pstrLocatorType, pstrLocatorValue, elementName);
			WebElement comboitem = findElement(combo, WebDriverConstants.Locators.TAGNAME, tagName, "GETTEXT",
					pstrItem);
			comboitem.click();
			Thread.sleep(WebDriverConstants.Time.SLEEP_MILLISECONDS);
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("comboSelect failed with error " + e.getMessage());
			throw new TestException(
					"comboSelect failed with error " + e.getMessage() + ", please find the screen shot at location ",
					filePath);
		}

	}

	/**
	 * comboSelect is used to select a item in a combo which is child of a
	 * parent element
	 * 
	 * @param parent
	 * @param locatorType
	 * @param locatorValue
	 * @param comboItem
	 * @return
	 * @throws TestException
	 */
	public void comboSelect(String pstrParentLocatorType, String pstrParentLocatorValue, String pstrLocatorType,
			String pstrLocatorValue, String tagName, String pstrItem, String elementName) throws TestException {
		try {
			WebElement parent = findElement(pstrParentLocatorType, pstrParentLocatorValue, elementName);
			WebElement combo = findElement(parent, pstrLocatorType, pstrLocatorValue, elementName);
			combo.click();
			WebElement comboitem = findElement(parent, WebDriverConstants.Locators.TAGNAME, tagName, "GETTEXT",
					pstrItem);
			comboitem.click();
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("comboSelect failed with error " + e.getMessage());
			throw new TestException(
					"comboSelect failed with error " + e.getMessage() + ", please find the screen shot at location ",
					filePath);
		}

	}
	
	public void setListBoxItem(String locatorType,String locatorValue,String valueToBeSet,String description) throws TestException {
		WebElement combo = findElement(locatorType,locatorValue,description);
		try {
		Select selectList = new Select(combo);
		selectList.selectByVisibleText(valueToBeSet);
		logger.info(description + " set to---> " + valueToBeSet);
		} catch (Exception e) {
		throw new TestException( "selecting " + valueToBeSet + " from " + description 	+ " failed with error " + e.getMessage(), captureScreenShoot());
		}
		}

	
	

	/**
	 * MouseClick is used to click a WebElement
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @return
	 * @throws TestException
	 */
	public void mouseClick(String pstrLocatorType, String pstrLocatorValue, String elementName) throws TestException {
		try {
			WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
			element.sendKeys(Keys.ENTER);
			/*
			 * Actions action = new Actions(driver);
			 * action.moveToElement(element).click(element).build().perform();
			 */

		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("mouseClick operation on " + elementName + " failed with error " + e.getMessage());
			throw new TestException("mouseClick operation on " + elementName + " failed with error " + e.getMessage()
					+ ", please find the screen shot at location ", filePath);
		}
	}

	public void doubleClick(String pstrParentLocatorType, String pstrParentLocatorValue, String pstrLocatorType,
			String pstrLocatorValue, String elementName) throws TestException {
		try {
			WebElement parent = findElement(pstrParentLocatorType, pstrParentLocatorValue, elementName);
			By byLocator = getLocator(pstrLocatorType, pstrLocatorValue);
			WebElement element = parent.findElement(byLocator);
			if (element == null) {
				throw new TestException("Element to double click not found in " + elementName);
			}
			Actions action = new Actions(driver);
			action.moveToElement(element).doubleClick(element).build().perform();
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("doubleClick operation failed on " + elementName + "with error " + e.getMessage());
			throw new TestException("doubleClick operation failed on " + elementName + "with error " + e.getMessage()
					+ " please find the screen shot at location ", filePath);
		}
	}

	/**
	 * MouseOver does a mouse move action on an element
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @throws TestException
	 */
	public void mouseOver(String pstrLocatorType, String pstrLocatorValue, String elementName) throws TestException {
		try {
			logger.debug("performing mouse over on " + elementName);
			WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
			Actions Mouse = new Actions(driver);
			Mouse.moveToElement(element);
			Action builder = Mouse.build();
			builder.perform();
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("mouseOver operation failed on " + elementName + " with error " + e.getMessage());
			throw new TestException("mouseOver operation failed on " + elementName + " with error " + e.getMessage()
					+ ", please find the screen shot at location ", filePath);
		}
	}

	public void mouseOver(String pstrParentLocatorType, String pstrParentLocatorValue, String pstrLocatorType,
			String pstrLocatorValue, String elementName) throws TestException {
		try {
			logger.debug("performing mouse over on " + elementName);
			WebElement parent = findElement(pstrParentLocatorType, pstrParentLocatorValue, elementName);
			WebElement element = findElement(parent, pstrLocatorType, pstrLocatorValue, elementName);
			Actions Mouse = new Actions(driver);
			Mouse.moveToElement(element);
			Action builder = Mouse.build();
			builder.perform();
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("mouseOver operation failed on " + elementName + " with error " + e.getMessage());
			throw new TestException("mouseOver operation failed on " + elementName + " with error " + e.getMessage()
					+ ", please find the screen shot at location ", filePath);
		}
	}

	public void jsMouseOver(String pstrParentLocatorType, String pstrParentLocatorValue, String pstrLocatorType,
			String pstrLocatorValue, String elementName) throws TestException {
		try {
			logger.debug("performing mouse over on " + elementName);
			WebElement parent = findElement(pstrParentLocatorType, pstrParentLocatorValue, elementName);
			WebElement element = findElement(parent, pstrLocatorType, pstrLocatorValue, elementName);
			String strJavaScript = "var element = arguments[0];"
					+ "var mouseEventObj = document.createEvent('MouseEvents');"
					+ "mouseEventObj.initEvent( 'mouseover', true, true );" + "element.dispatchEvent(mouseEventObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(strJavaScript, element);

		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("mouseOver operation failed on " + elementName + " with error " + e.getMessage());
			throw new TestException("mouseOver operation failed on " + elementName + " with error " + e.getMessage()
					+ ", please find the screen shot at location ", filePath);
		}

	}

	/**
	 * ReadText is used to read text from a web element
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @return
	 * @throws TestException
	 */
	public String readText(String pstrLocatorType, String pstrLocatorValue, String elementName) throws TestException {
		WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
		String result = element.getText();
		return result;
	}

	public String readText(String pstrParentLocatorType, String pstrParentLocatorValue, String pstrLocatorType,
			String pstrLocatorValue, String elementName) throws TestException {
		By byParent = getLocator(pstrParentLocatorType, pstrParentLocatorValue);
		WebElement parent = fluentWait(byParent, driver, elementName);
		WebElement child = findElement(parent, pstrLocatorType, pstrLocatorValue, elementName);
		String result = child.getText();
		return result;
	}

	public String readText(String pstrParentLocatorType, String pstrParentLocatorValue, String pstrLocatorType,
			String pstrLocatorValue, String pstrAttribute, String pstrAttributeValue, String elementName)
			throws TestException {
		By byParent = getLocator(pstrParentLocatorType, pstrParentLocatorValue);
		WebElement parent = fluentWait(byParent, driver, elementName);
		WebElement child = findElement(parent, pstrLocatorType, pstrLocatorValue, pstrAttribute, pstrAttributeValue);
		String result = child.getText();
		return result;
	}

	public String readText(WebElement parent, String pstrLocatorType, String pstrLocatorValue, String elementName)
			throws TestException {
		WebElement child = findElement(parent, pstrLocatorType, pstrLocatorValue, elementName);
		String result = child.getText();
		logger.info(elementName + "text is " + result);
		return result;
	}

	/**
	 * Scroll method is used to scroll to an WebElement
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @return
	 * @throws TestException
	 */
	public void scroll(String pstrLocatorType, String pstrLocatorValue, String elementName) throws TestException {
		try {
			WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(WebDriverConstants.Time.SLEEP_MILLISECONDS);
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("scroll operation failed on " + elementName + " with error " + e.getMessage());
			throw new TestException("scroll operation failed on " + elementName + " with error " + e.getMessage()
					+ ", please find the screen shot at location ", filePath);
		}

	}

	/**
	 * 
	 * @param pstrAlertValue
	 * @return
	 * @throws TestException
	 */
	public void alertOptional(String pstrAlertValue) throws TestException {
		boolean result = false;
		int count = 0;
		try {
			while (count < WebDriverConstants.Time.ALERT_TIME && !result) {
				Thread.sleep(WebDriverConstants.Time.POLLING_TIME);
				result = checkAndSwitchToAlert();
				count = count + 1;
			}
			if (result) {
				if (pstrAlertValue.equalsIgnoreCase(WebDriverConstants.ALERT.ACCEPT)) {
					driver.switchTo().alert().accept();
				} else if (pstrAlertValue.equalsIgnoreCase(WebDriverConstants.ALERT.DISMISS)) {
					driver.switchTo().alert().dismiss();
				}
			}
		} catch (InterruptedException e) {
			throw new TestException("INTERRUPTEDEXCEPTION: Test interrupted");
		}

	}

	/**
	 * Wait method stops the script execution for a specified amount of time
	 * 
	 * @param time
	 * @throws TestException
	 */
	public void wait(int time) throws TestException {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			throw new TestException("INTERRUPTEDEXCEPTION: Test interrupted");
		}

	}

	/**
	 * isElementPresent is used to check for presence visibility of an
	 * webelement within a specifies period of time
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @param time
	 * @return
	 * @throws TestException
	 */
	public boolean isElementPresent(String pstrLocatorType, String pstrLocatorValue, int time) throws TestException {
		try {
			By byLocator = getLocator(pstrLocatorType, pstrLocatorValue);
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
			return true;
		} catch (TimeoutException te) {
			return false;
		}
	}

	/**
	 * getAttribute method is used to read an attribute of an webelement
	 * 
	 * @param element
	 * @param attributeName
	 * @return
	 * @throws TestException
	 */
	public String getAttribute(String pstrLocatorType, String pstrLocatorValue, String attributeName,
			String elementName) throws TestException {
		WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
		String result = element.getAttribute(attributeName);
		return result;
	}

	public String getAttribute(WebElement element, String attributeName) throws TestException {
		String result = element.getAttribute(attributeName);
		return result;
	}

	public String getAttribute(String pstrParentLocatorType, String pstrParentLocatorValue, String pstrLocatorType,
			String pstrLocatorValue, String pstrAttributeName, String elementName) throws TestException {
		WebElement parent = findElement(pstrParentLocatorType, pstrParentLocatorValue, elementName);
		WebElement child = findElement(parent, pstrLocatorType, pstrLocatorValue, elementName);
		String result = child.getAttribute(pstrAttributeName);
		return result;
	}

	/**
	 * 
	 * @param pstrLocatorType
	 * @param pstrLocatorValue
	 * @param propertyname
	 * @param elementName
	 * @return
	 * @throws TestException
	 */
	public String getCSSValue(String pstrLocatorType, String pstrLocatorValue, String propertyname, String elementName)
			throws TestException {
		WebElement element = findElement(pstrLocatorType, pstrLocatorValue, elementName);
		String result = element.getCssValue(propertyname);
		return result;
	}

	/**
	 * getParent method is used to find parent element
	 * 
	 * @param webElement
	 * @return
	 * @throws TestException
	 */
	public WebElement getParent(WebElement webElement) throws TestException {
		try {
			WebElement element = webElement.findElement(By.xpath(".."));
			return element;
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("error finding parent of webElement");
			throw new TestException("getParent of WebElement failed with with error " + e.getMessage()
					+ " ,please find the snapshot at location", filePath);
		}

	}

	/**
	 * SwitchToDefaultFrame is used to switch the control to default frame
	 */
	public void switchToDefaultFrame() {
		driver.switchTo().defaultContent();
	}

	public String captureScreenShoot() throws TestException {
		String filePath = "";
		String imagesFolder = System.getProperty(TestConstants.SystemProperty.IMAGES);
		File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String fileName = new Date().getTime() + "_error.png";
		filePath = imagesFolder + File.separator + fileName;
		try {
			FileUtils.copyFile(imageFile, new File(filePath));
		} catch (Exception e) {
			logger.error("captureScreenShoot failed with error " + e.getMessage());
			throw new TestException("captureScreenShoot failed with error " + e.getMessage());
		}
		return fileName;
	}

	public boolean executeBooleanJavascript(String javascript) throws TestException {
		try {
			boolean result = (Boolean) ((JavascriptExecutor) driver).executeScript(javascript);
			return result;
		} catch (Exception e) {
			String filePath = captureScreenShoot();
			logger.error("Executing java script failed with error " + e.getMessage());
			throw new TestException("Executing java script failed with error " + e.getMessage()
					+ " please find the snapshot at location", filePath);
		}
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	abstract public void setBrowser();

	abstract public void setDesiredCapabilities();

	abstract public void setDriversPath();

	abstract public void setUrl();

}
