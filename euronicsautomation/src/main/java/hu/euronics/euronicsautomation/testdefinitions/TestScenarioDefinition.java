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
 * TestScenarioDefinition class is used to represent a TestScenario
 * each TestScenario is represented as a list of individual TestCases 
 * @author Venugopal Bukkapatnam
 *
 */
public class TestScenarioDefinition {
	private String ID;
	private String Execute;
	private String TestScenario;
	private List<TestCaseDefinition> TestCases;
	private String Result;
	private String ResultsFolder;
	private String duration;
	
	
	/**
	 * getExecute method is used to fetch the value of Execute member that represents Execute column in the worksheet
	 * @return Execute 
	 */
	public String getExecute(){
		return Execute;
	}
	
	/**
	 *  setExecute method is used to set the value of Execute member that represents Execute column in the worksheet  
	 */
	public void setExecute(String Execute){
		this.Execute = Execute;
	}
	
	/**
	 * getTestScenario method is used to fetch the value of TestScenario member that represents TestScenario Column in the worksheet
	 * @return TestScenario
	 */
	public String getTestScenario()
	{
		return TestScenario;
	}
	
	/**
	 * setTestScenario method is used to set the value of TestScenario member that represents TestScenario Column in the worksheet
	 * @param TestScenario
	 */
	public void setTestScenario(String TestScenario)
	{
		this.TestScenario = TestScenario;
	}
	/**
	 * getTestCases method is used to fetch the value of TestCases member that represents TestCases column in
	 * the worksheet.
	 * @return : list of testcases
	 */
	public List<TestCaseDefinition> getTestCases(){
		return TestCases;
	}
	
	/**
	 * setTestCases is used to set the value of TestCases member that represents TestCases column in the worksheet
	 * @param list of testcases
	 */
	public  void setTestCases(List<TestCaseDefinition> TestCases){	
		this.TestCases = TestCases;
	}	
	
	/**
	 * getResult method is used to fetch the value of Result member that represents Result Column in the worksheet
	 * @return
	 */
	public String getResult()
	{
		return Result;
	}
	
	/**
	 * setResult method is used to set the value of Result member that represents Result Column in the worksheet
	 * @param Result
	 */
	public void setResult(String Result)
	{
		this.Result = Result;
	}
	
	/**
	 * getResultsFolder method is used to fetch the value of ResultsFolder member that represents ResultsFolder Column in the worksheet
	 * @return
	 */
	public String getResultsFolder()
	{
		return ResultsFolder;
	}
	
	/**
	 * setResultsFolder method is used to set the value of ResultsFolder member that represents ResultsFolder Column in the worksheet
	 * @param ResultsFolder
	 */
	public void setResultsFolder(String ResultsFolder)
	{
		this.ResultsFolder = ResultsFolder;
	}
	
	/**
	 * Constructor
	 * @param TestScenario
	 * @param Execute
	 * @param TestCases
	 * @param Result
	 * @param ResultsFolder
	 */
	public TestScenarioDefinition(String Id,String TestScenario,String Execute, List<TestCaseDefinition> TestCases,String Result,String ResultsFolder)
	{
		super();
		this.ID=Id;
		this.TestScenario =TestScenario;		
		this.Execute = Execute;
		this.TestCases = TestCases;
		this.Result =	Result;
		this.ResultsFolder = ResultsFolder;
	}
	
	/**
	 * Constructor
	 */
	public TestScenarioDefinition() {
		super();
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}


}
