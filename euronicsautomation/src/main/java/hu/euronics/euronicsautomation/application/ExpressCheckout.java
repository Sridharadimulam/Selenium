package hu.euronics.euronicsautomation.application;

import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class ExpressCheckout  extends Application{
	private static final String PROPERTY_EXPRESSCHECKOUT_BUTTON_XPATH = "EXPRESS_CHECKOUT";
	public void execute() throws TestException {
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.XPATH, objectMap.getTestProperty(PROPERTY_EXPRESSCHECKOUT_BUTTON_XPATH),
				"Expresscheckout Delivery method");
	}

}