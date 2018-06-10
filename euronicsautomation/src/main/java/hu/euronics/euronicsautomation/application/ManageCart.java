package hu.euronics.euronicsautomation.application;

import org.apache.log4j.Logger;

import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class ManageCart extends Application {
	private static final Logger logger = Logger.getLogger(ManageCart.class.getSimpleName());

	private static final String PROPERTY_BUTTON_CARTSPLIT_ID = "BUTTON_CARTSPLIT";

	/**
	 * 
	 * @param mapInputParameters
	 * @throws TestException
	 */
	public void execute() throws TestException {
		euronicsSeleniumHandler.waitForAjaxToLoad();

		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_BUTTON_CARTSPLIT_ID),
				"Checkout Cart");
	}

}
