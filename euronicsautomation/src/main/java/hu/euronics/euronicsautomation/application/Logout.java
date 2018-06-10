package hu.euronics.euronicsautomation.application;

import hu.euronics.euronicsautomation.common.utils.TestException;

public class Logout extends Application{
	
	/**
	 * 
	 * @param pmapArguments
	 * @throws TestException
	 */
	public void execute() throws TestException {
		tearDown();
	}

}
