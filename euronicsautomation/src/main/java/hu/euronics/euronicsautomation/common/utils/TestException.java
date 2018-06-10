/**
 * This code is part of WS Publishing.
 * This code is developed by Techwave India Pvt Limited, Hyderabad.
 * Do not make any changes.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * Copyright (C) Techwave India Pvt Limited, Hyderabad.
 * All Rights Reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
 */


package hu.euronics.euronicsautomation.common.utils;

public class TestException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -61438951198430304L;
	private String imagePath=null;
	public TestException() {
		super();
	}	

	public TestException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestException(String message) {
		super(message);
	}
	
	public TestException(String message,String FilePath) {
		super(message);
		this.imagePath =FilePath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public TestException(Throwable cause) {
		super(cause);
	}

}
