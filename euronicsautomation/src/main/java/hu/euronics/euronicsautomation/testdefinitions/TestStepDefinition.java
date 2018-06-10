/**
 * This code is part of WS Publishing.
 * This code is developed by Techwave India Pvt Limited, Hyderabad.
 * Do not make any changes.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * Copyright (C) Techwave India Pvt Limited, Hyderabad.
 * All Rights Reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
 */


package hu.euronics.euronicsautomation.testdefinitions;

import java.util.Map;

/**
 * This class represents the teststeps that get executed using selenium webdriver
 * @author Venugopal Bukkapatnam
 *  
 */

/**
 * @author 10336
 *
 */
public class TestStepDefinition {
	
	private String rowNumber;
	private String action;
	private String description;
	private String arguments;
	private Map<String,String> mapArguments;
	private String sheetName;
	private String result;
	private String comments;
	private String imagePath;
	private String duration;
	
	
	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	/**
	 * 
	 * @return
	 */
	public String getRowNumber() {
		return rowNumber;
	}


	/**
	 * 
	 * @param rowNumber
	 */
	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}


	/**
	 * 
	 * @return
	 */
	public String getAction() {
		return action;
	}


	/**
	 * 
	 * @param action
	 */
	public void setAction(String action) {
		this.action = action;
	}



	public String getArguments() {
		return arguments;
	}


	/**
	 * 
	 * @param arguments
	 */
	public void setArguments(String arguments) {
		this.arguments = arguments;
	}


	/**
	 * 
	 * @return
	 */
	public Map<String,String> getMapArguments() {
		return mapArguments;
	}


	/**
	 * 
	 * @param mapArguments
	 */
	public void setMapArguments(Map<String,String> mapArguments) {
		this.mapArguments = mapArguments;
	}


	/**
	 * 
	 * @return
	 */
	public String getResult() {
		return result;
	}


	/**
	 * 
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
	/**
	 * 
	 * @param rowNumber
	 * @param action
	 * @param arguments
	 * @param mapArguments
	 * @param result
	 */
	public TestStepDefinition(String rowNumber, String action,String description, String arguments, Map<String,String> mapArguments,String sheetName, String result,String comments) {
		super();
		this.rowNumber = rowNumber;
		this.action = action;
		this.description = description;
		this.arguments = arguments;
		this.sheetName= sheetName;
		this.mapArguments = mapArguments;
		this.result = result;
		this.comments = comments;
	}
	
	/**
	 * constructor
	 */
	public TestStepDefinition() {
		super();
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	@Override
	public String toString() {
		return "TestStepDefinition [rowNumber=" + rowNumber + ", action=" + action + ", description=" + description + ", sheetname=" + sheetName + ", arguments=" + arguments +"]";
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
}
