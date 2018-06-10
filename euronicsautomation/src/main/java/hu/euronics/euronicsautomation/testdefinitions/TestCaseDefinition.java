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

import java.util.List;

/**
 * TestCaseDefinition class is used to represent a TestCase
 * Each TestCase is represneted as a combination of mutiple testt steps
 * @author Venugopal Bukkapatnam
 *
 */
public class TestCaseDefinition {
	
	private String testCaseId;
	private String testCaseName;
	private String description;
	private String sheet;
	private String rowNumber;
	private String result;
	private List<TestStepDefinition> testSteps;
	private String duration;
	
	/**
	 * getTestCaseId method is used to get the TestCaseId member that represents TESTCASE_ID column of the excel sheet
	 * @return : TestCaseId 
	 */
	public String getTestCaseId(){
		return testCaseId;
	}
	
	/**
	 * setTestCaseId method is used to set the testCaseId member that represents TESTCASE_ID column of the excel sheet 
	 * @param :testCaseId
	 */
	public void setTestCaseId(String testCaseId){
		this.testCaseId =testCaseId;
	}
	/**
	 * getTestCaseName method is used to get the TestCaseId member that represents TESTCASE_NAME column of the excel sheet
	 * @return : TestCaseName 
	 */
	public String getTestCaseName(){
		return testCaseName;
	}
	
	/**
	 * setTestCaseName method is used to set the testCaseName member that represents TESTCASE_NAME column of the excel sheet 
	 * @param :TestCaseName
	 */
	public void setTestCaseName(String testCaseName){
		this.testCaseName =testCaseName;
	}
	
	/**
	 * getDescription method is used to get the description member that represents Description column of the excel sheet
	 * @return : description value
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * setDescription method is used to set the description member that represents Description column of the excel sheet 
	 * @param description
	 */
	public void setDescription(String description){
		this.description =description;
	}
	
	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public String getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}
	
	/**
	 * getTestSteps method is used to get the list of steps that represents TestSteps column of the excel sheet
	 * @return list of TestSteps
	 */
	public List<TestStepDefinition> getTestSteps(){
		return testSteps;
	}
	
	/**
	 * setTestSteps is used to set the testStep member that represents TestSteps column of the excel sheet
	 * @param teststeps
	 */
	public  void setTestSteps(List<TestStepDefinition> testSteps){	
		this.testSteps = testSteps;
	}
	
	/**
	 * setResult is used to set the Result member
	 * @param result
	 */
	public void setResult(String result)
	{
		this.result =result.toUpperCase();
	}
	
	/**
	 * getResult is used to return the execution result of the TestCase
	 * @return
	 */
	public String getResult()
	{
		return result;
	}
	
	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	/**
	 * Constructor
	 * @param description
	 * @param teststeps
	 */
	public TestCaseDefinition(String testCaseId,String testCaseName,String description,String sheet,String rowNumber,List<TestStepDefinition> testSteps,String result){
		super();
		this.testCaseId = testCaseId;
		this.testCaseName = testCaseName;
		this.sheet = sheet;
		this.rowNumber = rowNumber;
		this.testSteps =testSteps;
		this.description =description;
		this.result = result;
	}
	
	/**
	 * Constructor
	 */
	public TestCaseDefinition(){
		super();
	}

	
}
