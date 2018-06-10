package hu.euronics.euronicsautomation.application;

import org.apache.log4j.Logger;

import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class AddProduct extends Application {
	private static final Logger logger = Logger.getLogger(AddProduct.class.getSimpleName());
	private static final String PROPERTY_ID_BUTTON_ADDTOBASKET = "ID_BUTTON_ADDTOCART";
	private static final String PROPERTY_BUTTON_CHECKOUT = "XPATH_ADDTOCART_POPUP_SUBMIT";

	public void execute() throws TestException {
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.ID,
				objectMap.getTestProperty(PROPERTY_ID_BUTTON_ADDTOBASKET), "ADD TO BASKET");
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.XPATH, objectMap.getTestProperty(PROPERTY_BUTTON_CHECKOUT),
				"Go to Cart Page");
	}
}
