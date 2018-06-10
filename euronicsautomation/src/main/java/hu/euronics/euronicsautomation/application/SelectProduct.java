package hu.euronics.euronicsautomation.application;

import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class SelectProduct extends Application {

	private static final String PROPERTY_HTMLTAG_STOREPRODUCT = "HTMLTAG_STOREPRODUCT";
	private static final String PROPERTY_XPATH_HTMLTAG_LINK_PRODUCTTEXT = "XPATH_HTMLTAG_LINK_PRODUCTTEXT";
	

	/**
	 * 
	 * @param mapInputParameters
	 * @throws TestException
	 */
	public void execute() throws TestException {

		String productName = getParameter(mapInputParameters,PRODUCTNAME );
		euronicsSeleniumHandler.waitForAjaxToLoad();
		/*euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.XPATH,
				objectMap.getTestProperty(PROPERTY_XPATH_HTMLTAG_LINK_PRODUCTTEXT), productName);
				*/
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.XPATH,objectMap.getTestProperty(PROPERTY_XPATH_HTMLTAG_LINK_PRODUCTTEXT), productName);

	}
	
}
