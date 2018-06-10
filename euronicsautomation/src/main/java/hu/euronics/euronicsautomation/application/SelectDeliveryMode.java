package hu.euronics.euronicsautomation.application;

import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class SelectDeliveryMode extends Application {
	private static final String PROPERTY_RADIO_COURIER_ID = "RADIO_COURIER";
	private static final String PROPERTY_BUTTON_DELIVERYMETHOD_ID = "BUTTON_DELIVERYMETHOD";
	private static final String PROPERTY_RADIO_DELIVERYMODE1_ID = "RADIO_DELIVERYMODE1";
	private static final String PROPERTY_BUTTON_DELIVERYMODE1_XPATH = "BUTTON_DELIVERYMODE1";
	private static final String PROPERTY_RADIO_LOOKUP1_ID = "RADIO_LOOKUP1";
	private static final String PROPERTY_RADIO_DELIVERYMODE2_ID = "RADIO_DELIVERYMODE2";
	private static final String PROPERTY_BUTTON_DELIVERYMODE2_XPATH = "BUTTON_DELIVERYMODE2";
	private static final String PROPERTY_RADIO_LOOKUP2_ID = "RADIO_LOOKUP2";
	
	public void execute() throws TestException {
		String selectRadioforshop = getParameter(mapInputParameters, SHOP);
		String selectRadioforppp = getParameter(mapInputParameters, PPP);
		String selectRadioforcourier = getParameter(mapInputParameters, COURIER);
	if (selectRadioforshop.equalsIgnoreCase("TRUE")) {
		euronicsShop();
	}
	if (selectRadioforppp.equalsIgnoreCase("TRUE")) {
		euronicsPPP();
	}
	if (selectRadioforcourier.equalsIgnoreCase("TRUE")) {
		euronicsCourier();
	}
	}
	private void euronicsShop() throws TestException {
		euronicsSeleniumHandler.waitForAjaxToLoad();
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_RADIO_DELIVERYMODE1_ID),
				"select radio for euronics shop");
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.XPATH, objectMap.getTestProperty(PROPERTY_BUTTON_DELIVERYMODE1_XPATH),
				"Look for euronics shops");
		euronicsSeleniumHandler.waitForAjaxToLoad();
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_RADIO_LOOKUP1_ID),
				"Select lookup address for shop");
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_BUTTON_DELIVERYMETHOD_ID),
			"Submit Delivery method");
}
	private void euronicsPPP() throws TestException {
		euronicsSeleniumHandler.waitForAjaxToLoad();
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_RADIO_DELIVERYMODE2_ID),
				"select radio for ppp");
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.XPATH, objectMap.getTestProperty(PROPERTY_BUTTON_DELIVERYMODE2_XPATH),
				"Look for ppp");
		/*euronicsSeleniumHandler.waitForAjaxToLoad();
		euronicsSeleniumHandler.waitForAjaxToLoad();
		euronicsSeleniumHandler.waitForAjaxToLoad();*/
		euronicsSeleniumHandler.waitForAjaxToLoad();
		euronicsSeleniumHandler.wait(5);
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_RADIO_LOOKUP2_ID),
				"Select lookup address for ppp");
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_BUTTON_DELIVERYMETHOD_ID),
			"Submit Delivery method");
}
	private void euronicsCourier() throws TestException {
		euronicsSeleniumHandler.waitForAjaxToLoad();
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_RADIO_COURIER_ID),
				"select radio for courier");
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_BUTTON_DELIVERYMETHOD_ID),
				"Submit Delivery method");
}



	
	
	
		
		}
