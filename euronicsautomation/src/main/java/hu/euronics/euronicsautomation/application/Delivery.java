package hu.euronics.euronicsautomation.application;

import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class Delivery extends Application {

	private static final String PROPERTY_TEXTBOX_FIRSTNAME_ID = "TEXTBOX_FIRSTNAME";
	private static final String PROPERTY_TEXTBOX_LASTNAME_ID = "TEXTBOX_LASTNAME";
	private static final String PROPERTY_TEXTBOX_ADDRESSLINE1_ID = "TEXTBOX_ADDRESSLINE1";
	private static final String PROPERTY_TEXTBOX_CITY_ID = "TEXTBOX_CITY";
	private static final String PROPERTY_TEXTBOX_POSTCODE_ID = "TEXTBOX_POSTCODE";
	private static final String PROPERTY_BUTTON_SUBMIT_ID = "BUTTON_ADDRESS_SUBMIT";
	


	public void execute() throws TestException {

		String firstName = getParameter(mapInputParameters, FIRSTNAME);
		String lastName = getParameter(mapInputParameters, LASTNAME);
		String city = getParameter(mapInputParameters, CITY);
		String line1 = getParameter(mapInputParameters, ADDRESSLINE1);
		String postCode = getParameter(mapInputParameters, POSTCODE);
		euronicsSeleniumHandler.waitForAjaxToLoad();
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.ID,
				objectMap.getTestProperty(PROPERTY_TEXTBOX_LASTNAME_ID), lastName, "Last Name");
		
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.ID,
				objectMap.getTestProperty(PROPERTY_TEXTBOX_FIRSTNAME_ID), firstName, "First Name");
		
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.ID,
				objectMap.getTestProperty(PROPERTY_TEXTBOX_POSTCODE_ID), postCode, "post code");
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.ID,
				objectMap.getTestProperty(PROPERTY_TEXTBOX_CITY_ID), city, "City");
		
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.ID,
				objectMap.getTestProperty(PROPERTY_TEXTBOX_ADDRESSLINE1_ID), line1, "Address Line1");
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_BUTTON_SUBMIT_ID),
				"Submit Delivery method");
		
	}
}
