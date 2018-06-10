package hu.euronics.euronicsautomation.application;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import hu.euronics.euronicsautomation.common.utils.TestException;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class ViewBasket extends Application {
	
		private static final Logger logger = Logger.getLogger(ViewBasket.class.getSimpleName());
	
	private static final String PROPERTY_XPATH_UPDATE_CART = "XPATH_UPDATE_CART";
	private static final String PROPERTY_XPATH_UPDATE_GUARENTEE = "XPATH_UPDATE_GUARENTEE";
	private static final String PROPERTY_ID_COMBO_GRT = "ID_COMBO_GRT";
	private static final String PROPERTY_XPATH_DELETE_CART = "XPATH_DELETE_CART";

	public void execute() throws TestException {
		String grtDropdown = getParameter(mapInputParameters, GRT);
		String updateQty = getParameter(mapInputParameters, UPDATEQTY);
		String updateGrt = getParameter(mapInputParameters, UPDATEGRT);
				euronicsSeleniumHandler.waitForAjaxToLoad();
		euronicsSeleniumHandler.setListBoxItem(WebDriverConstants.Locators.ID,
				objectMap.getTestProperty(PROPERTY_ID_COMBO_GRT),grtDropdown,"guarentee type");	
	
		if (updateGrt.equalsIgnoreCase("TRUE")) {
			updateWarrenty();
		}
		if (updateQty.equalsIgnoreCase("TRUE")) {
			updateBasket();
		}
		/*if (grt.equalsIgnoreCase("TRUE")) {
			grtDropdown();
		}*/
		
		
	}

	private void updateBasket() throws TestException {
				euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.XPATH,
				objectMap.getTestProperty(PROPERTY_XPATH_UPDATE_CART), "QTY + icon");
		euronicsSeleniumHandler.waitForAjaxToLoad();
	}
	
	/*s*/
	private void updateWarrenty() throws TestException {
		euronicsSeleniumHandler.jsClick(WebDriverConstants.Locators.XPATH,
				objectMap.getTestProperty(PROPERTY_XPATH_UPDATE_GUARENTEE), "ADD GUARENTEE");
		euronicsSeleniumHandler.waitForAjaxToLoad();
	}

	


}

