package hu.euronics.euronicsautomation.application;

import hu.euronics.euronicsautomation.common.utils.TestException;

public class LaunchApplication extends Application{
	
	public void execute() throws TestException {
		euronicsSeleniumHandler.open();
		euronicsSeleniumHandler.waitForAjaxToLoad();
	}
}
