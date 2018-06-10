/**
 * This code is part of WS Publishing.
 * This code is developed by Techwave India Pvt Limited, Hyderabad.
 * Do not make any changes.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * Copyright (C) Techwave India Pvt Limited, Hyderabad.
 * All Rights Reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
 */


package hu.euronics.euronicsautomation.webdriver.utils;

public class WebDriverConstants {	
	public static final String ERROR="ERROR_MESSAGE";
	public static final String BROWSER="BROWSER";
	public interface Time{
		 int WAIT_TIME = 60;
		 int POLLING_TIME=1;
		 int SLEEP_MILLISECONDS = 500;
		 int ALERT_TIME = 20;
		 int WAIT_FOR_ERROR = 5;
		 int MIN_WAIT = 3;
		 
	}
	
	public interface HTML{ 
		 String INPUT= "input";
		 String VALUE= "value";
		 String TITLE= "title";
		 String IMAGE="img";
		 String CLASS="class";
		 String SPAN="span";
		 String INDEX="index";
	     String DIV = "div";
		 String TEXTAREA = "textarea";
		 String LINK = "link";
		 String HREF = "href";
		 String A = "a";
		 String H2 = "h2";
	}
		
	public interface ALERT{
		String ACCEPT ="ACCEPT";
		String DISMISS = "DISMISS";
	}
	public interface Locators{
		String ID ="ID";
		String NAME = "NAME";
		String XPATH = "XPATH";
		String TAGNAME ="TAGNAME";
		String LINKTEXT = "LINKTEXT";
		String CLASSNAME ="CLASSNAME";
		String CSSSELECTOR = "CSSSELECTOR";
		String PARTIALLINKTEXT = "PARTIALLINKTEXT";
	}
	public interface Browsers{
		String FIREFOX ="FIREFOX";
		String CHROME = "CHROME";
		String INTERNETEXPLORER = "INTERNETEXPLORER";
	}
	public interface Driver{
		String CHROME_SYSTEM_PROPERTY ="webdriver.chrome.driver";
		String FIREFOX_SYSTEM_PROPERTY = "webdriver.firefox.bin";
		String INTERNETEXPLORER_SYSTEM_PROPERTY = "webdriver.ie.driver";
	}
	
	public interface Icon{
		String RED ="pim-validation-error fa fa-circle";
		String GREEN ="pim-validation-success fa fa-check-circle";
		String EXPANDED="ui-icon ui-icon-triangle-1-s";
		String COLLAPSED="ui-icon ui-icon-triangle-1-e";
	}
	

}
