package hu.euronics.euronicsautomation.application;

import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class SearchProduct extends Application{
	private static final String PROPERTY_TEXTBOX_SEARCHINPUT="TEXTBOX_SEARCHINPUT";
	private static final String PROPERTY_BUTTON_SEARCHSUBMIT="BUTTON_SEARCHSUBMIT";
	
	
	public void execute() throws TestException {
		String productID = getParameter(mapInputParameters,PRODUCTID);
		
		euronicsSeleniumHandler.waitForAjaxToLoad();
		euronicsSeleniumHandler.jsSendKeys(WebDriverConstants.Locators.ID, objectMap.getTestProperty(PROPERTY_TEXTBOX_SEARCHINPUT), productID, "Search Product");
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.XPATH,objectMap.getTestProperty(PROPERTY_BUTTON_SEARCHSUBMIT), "Search Submit");
		
	}


}
