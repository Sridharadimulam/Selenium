package hu.euronics.euronicsautomation.application;

import org.apache.log4j.Logger;

import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.testdefinitions.TestConstants;
import hu.euronics.euronicsautomation.webdriver.utils.SeleniumHandler;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class EuronicsSeleniumHandler extends SeleniumHandler {
	private static final Logger logger = Logger.getLogger(EuronicsSeleniumHandler.class.getSimpleName());
	private final String JS_JQUERY_DEFINED = "return typeof window.jQuery != 'undefined';";
	private final String JS_JQUERY_INACTIVE = "return window.jQuery.active == 0;";
	private  final String JS_DOCUMENT_READY = "return document.readyState == 'complete';";
	
	/**
	 * used to initialize selenium driver
	 * 
	 * @param pstrBrowser
	 *            browser Name
	 * @param pDesiredCapabilities
	 *            desired capabilities of the browser
	 * @param pstrDriversPath
	 *            driver path
	 * @throws TestException
	 */
	public EuronicsSeleniumHandler() throws TestException {
		String browser = System.getProperty(TestConstants.SystemProperty.BROWSER);
		String capabilities = System.getProperty(TestConstants.SystemProperty.CAPABILITIES);
		String driverPath = System.getProperty(TestConstants.SystemProperty.DRIVERSPATH);
		if (isWindows()) {
			initWindows(browser, capabilities, driverPath);
		} else {
			initUnix(browser, capabilities, driverPath);
		}

	}


	/**
	 * methods waits for all the jquerys and primefaces events to get completed
	 * 
	 * @throws TestException
	 */
	public void waitForAjaxToLoad() throws TestException {
		boolean ajaxLoaded;		
		int totalAttempts = WebDriverConstants.Time.WAIT_TIME;
		int attempt = 0;
		do {
			ajaxLoaded = true;
			wait(WebDriverConstants.Time.POLLING_TIME);			
			boolean jQueryDefined = executeBooleanJavascript(JS_JQUERY_DEFINED);
			boolean documentReady =executeBooleanJavascript(JS_DOCUMENT_READY);
			if (jQueryDefined) {
				ajaxLoaded &= executeBooleanJavascript(JS_JQUERY_INACTIVE);
			}
			if (ajaxLoaded && documentReady) {
				break;
			}
			
			attempt = attempt + 1;
		} while (attempt < totalAttempts);
		if (!ajaxLoaded) {
			String filePath = captureScreenShoot();
			logger.error("ajax not loaded on page even after wating for " + totalAttempts + " seconds");
			throw new TestException("ajax not loaded on page even after wating for " + totalAttempts
					+ " seconds, please find the snapshot at location ", filePath);
		}
	
	}

	public void tearDown(){
		super.tearDown();
	}

	@Override
	public void setBrowser() {
		browser = System.getProperty(TestConstants.SystemProperty.BROWSER);
		
	}

	@Override
	public void setDesiredCapabilities() {
		desiredCapabilities = System.getProperty(TestConstants.SystemProperty.CAPABILITIES);
		
	}

	@Override
	public void setDriversPath() {
		driverPath = System.getProperty(TestConstants.SystemProperty.DRIVERSPATH);
		
	}

	@Override
	public void setUrl() {
		driverPath = System.getProperty(TestConstants.SystemProperty.URL);
		
	}
}
