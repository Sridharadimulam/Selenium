package hu.euronics.euronicsautomation.application;

import org.apache.log4j.Logger;

import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class PlaceOrder extends Application {
	
private static final Logger logger = Logger.getLogger(SelectDeliveryMode.class.getSimpleName());
	
	private static final String PROPERTY_CHECKBOX_TERMSCHECK_ID = "CHECKBOX_TERMSCHECK";
	private static final String PROPERTY_BUTTON_ORDERPLACE_ID = "BUTTON_ORDERPLACE";
	/**
	 * 
	 * @param pmapArguments
	 * @throws TestException
	 */
	public void execute() throws TestException {

		euronicsSeleniumHandler.waitForAjaxToLoad();

		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_CHECKBOX_TERMSCHECK_ID),
				"Select Delivery Mode");
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_BUTTON_ORDERPLACE_ID),
				"Submit Delivery method");
	}
}
