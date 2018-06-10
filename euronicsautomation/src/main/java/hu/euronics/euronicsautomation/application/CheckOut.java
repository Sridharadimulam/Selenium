package hu.euronics.euronicsautomation.application;

import org.apache.log4j.Logger;

import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class CheckOut extends Application {
	private static final Logger logger = Logger.getLogger(CheckOut.class.getSimpleName());
	private static final String PROPERTY_BUTTON_CHECKOUT_ID = "CHECKOUT_BUTTON";
	private static final String PROPERTY_TEXTBOX_EMAIL_ID = "TEXTBOX_EMAIL";
	private static final String TEXTBOX_EMAIL_PASSWORD_ID = "TEXTBOX_EMAIL_PASSWORD";
	private static final String BUTTON_LOGIN_SUBMIT_XPATH = "BUTTON_LOGIN_SUBMIT";
	
	
	public void execute() throws TestException {
		String email = getParameter(mapInputParameters, EMAIL);
		String password = getParameter(mapInputParameters, ACCOUNT);
		euronicsSeleniumHandler.waitForAjaxToLoad();

		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_BUTTON_CHECKOUT_ID),
				"Checkout Cart");
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_TEXTBOX_EMAIL_ID),
				email, "Email Address");
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.ID, objectMap.getTestProperty(TEXTBOX_EMAIL_PASSWORD_ID),
				password, "Password");
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.XPATH,objectMap.getTestProperty(BUTTON_LOGIN_SUBMIT_XPATH), "Submit login");

		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_BUTTON_CHECKOUT_ID),
				"Checkout Cart");
	}


}
