/**
 * This code is developed by Techwave India Pvt Limited, Hyderabad.
 * Do not make any changes.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * Copyright (C) Techwave India Pvt Limited, Hyderabad.
 * All Rights Reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
 */
package hu.euronics.euronicsautomation.testdefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import hu.euronics.euronicsautomation.application.Application;
import hu.euronics.euronicsautomation.application.EuronicsSeleniumHandler;
import hu.euronics.euronicsautomation.common.utils.PropertyLoader;
import hu.euronics.euronicsautomation.common.utils.TestException;

/**
 * TestSuite class is used to represent testsuite definition a TestSuite is
 * represented a list of TestScenarios
 * 
 * @author Venugopal Bukkapatnam
 *
 */
public class TestSuite {
	private static final Logger logger = Logger.getLogger(TestSuite.class.getSimpleName());
	private String url;
	private String duration;
	private String timestamp;
	private int scenariosPassed;
	private int scenariosFailed;
	private List<TestScenarioDefinition> listTestScenarios = new ArrayList<TestScenarioDefinition>();

	/***
	 * Build Test Scenarios with EXCEL SHEET data Followed by building Test
	 * Cases in
	 * 
	 * @param pstrSheetName
	 * @param pstrXlFilePath
	 * @return
	 * @throws TestException
	 */
	public void buildTestScenarios(String url, String pstrXlFilePath) {
		try {
			this.url = url;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			this.timestamp = dateFormat.format(date);
			logger.info("reading testdata from location " + pstrXlFilePath);
			InputStream fs = new FileInputStream(pstrXlFilePath);
			XSSFWorkbook workBook = new XSSFWorkbook(fs);
			String pstrSheetName = TestConstants.TESTSCENARIOS_SHEET;
			XSSFSheet testScenariosSheet = workBook.getSheet(pstrSheetName);
			if (testScenariosSheet == null) {
				throw new TestException("Excel Sheet " + pstrSheetName + " Not Found in file " + pstrXlFilePath);
			}
			int totalrows = testScenariosSheet.getPhysicalNumberOfRows();
			for (int rowctr = 1; rowctr < totalrows; rowctr++) {
				TestScenarioDefinition testScenario = null;
				Row row = testScenariosSheet.getRow(rowctr);
				if (row != null) {
					String Id = getCellContents(row.getCell(TestConstants.TestScenarioSheet.SCENARIOID_COL));
					String execute = getCellContents(row.getCell(TestConstants.TestScenarioSheet.EXECUTE_COL));
					String scenarioName = getCellContents(
							row.getCell(TestConstants.TestScenarioSheet.SCENARIONAME_COL));
					//logger.debug("building TestScenario " + scenarioName);
					String testCases = getCellContents(
							row.getCell(TestConstants.TestScenarioSheet.LISTOFTESTCASES_COL));
					List<TestCaseDefinition> listTestCases = buildTestCases(testCases, TestConstants.TESTCASES_SHEET,
							workBook);
					testScenario = new TestScenarioDefinition();
					testScenario.setID(Id);
					testScenario.setExecute(execute);
					testScenario.setTestScenario(scenarioName);
					testScenario.setTestCases(listTestCases);
					//logger.debug(" test scenario  " + scenarioName + " built ");
				}
				if (testScenario != null) {
					listTestScenarios.add(testScenario);
				} else {
					//logger.error(" test scenario  on row " + rowctr + " could not be built ");
					throw new TestException(" test scenario  on row " + rowctr + " could not be built ");
				}

			}
		} catch (IOException ioe) {
			//logger.error("file not found exception " + ioe.getMessage());
		} catch (TestException te) {
		//	logger.error(te.getMessage());
		}
	}

	/***
	 * BuildTestCases method is used to build individual TestCases that are part
	 * of a TestScenarios
	 * 
	 * @param pstrTestCases
	 * @param pstrSheetName
	 * @param pxssfWorkBook
	 * @return
	 * @throws TestException
	 */
	private List<TestCaseDefinition> buildTestCases(String pstrTestCases, String pstrSheetName,
			XSSFWorkbook pxssfWorkBook) {
		List<TestCaseDefinition> listTestCases = new ArrayList<TestCaseDefinition>();
		try {
			XSSFSheet testCaseSheet = pxssfWorkBook.getSheet(pstrSheetName);
			if (testCaseSheet == null) {
				throw new TestException("Excel Sheet " + pstrSheetName + " Not Found");
			}
			if (StringUtils.isNotBlank(pstrTestCases)) {
				String aTestCases[] = pstrTestCases.split(Pattern.quote(TestConstants.DELIMITER));
				for (int i = 0; i < aTestCases.length; i++) {
					String expectedTestCaseID = aTestCases[i];
					int nRows = testCaseSheet.getPhysicalNumberOfRows();
					for (int ctr = 1; ctr < nRows; ctr++) {
						TestCaseDefinition testCase = null;
						Row row = testCaseSheet.getRow(ctr);
						if (row != null) {
							String actualTestCaseID = getCellContents(
									row.getCell(TestConstants.TestCaseSheet.TESTCASEID_COL));
							if (expectedTestCaseID.equalsIgnoreCase(actualTestCaseID)) {
								String testCaseName = getCellContents(
										row.getCell(TestConstants.TestCaseSheet.TESTCASENAME_COL));
								String testCaseDescription = getCellContents(
										row.getCell(TestConstants.TestCaseSheet.DESCRIPTION_COL));
								String testCaseDataSheet = getCellContents(
										row.getCell(TestConstants.TestCaseSheet.SHEET_COL));
								//logger.debug("building TestCase " + testCaseName + " with testcase Id--->"
								//		+ actualTestCaseID);
								String rowNumber = getCellContents(
										row.getCell(TestConstants.TestCaseSheet.ROWNUMBER_COL));
								List<TestStepDefinition> listTestSteps = buildTestSteps(rowNumber, testCaseDataSheet,
										pxssfWorkBook);
								testCase = new TestCaseDefinition();
								testCase.setTestCaseId(actualTestCaseID);
								testCase.setTestCaseName(testCaseName);
								testCase.setDescription(testCaseDescription);
								testCase.setRowNumber(rowNumber);
								testCase.setTestSteps(listTestSteps);
							}
						}
						if (testCase != null) {
							listTestCases.add(testCase);
						}
					}
				}
			}
		} catch (TestException te) {
			//logger.error(te.getMessage());
		}
		return listTestCases;
	}

	/**
	 * BuildTestSteps method is used to build individual TestSteps that are part
	 * of a TestCase
	 * 
	 * @param rowNumber
	 * @param sheetName
	 * @param pWorkBook
	 * @return
	 * @throws TestException
	 */
	private List<TestStepDefinition> buildTestSteps(String pstrRowNumber, String pstrSheetName,
			XSSFWorkbook pxssfWorkBook) {
		List<TestStepDefinition> listTestSteps = new ArrayList<TestStepDefinition>();
		try {
			XSSFSheet testStepSheet = pxssfWorkBook.getSheet(pstrSheetName);
			if (testStepSheet == null) {
				throw new TestException("Excel Sheet " + pstrSheetName + " Not Found");
			}
			int nRows = testStepSheet.getPhysicalNumberOfRows();
			for (int i = 1; i < nRows; i++) {
				TestStepDefinition testStep = null;
				Row row = testStepSheet.getRow(i);
				if (row != null) {
					String actualrowNumber = getCellContents(row.getCell(TestConstants.TestStepsSheet.ROWNUMBER_COL));
					String prefix = pstrRowNumber + TestConstants.ROWNUMBER_DELIMITER;
					if (actualrowNumber.equalsIgnoreCase(pstrRowNumber) || actualrowNumber.startsWith(prefix)) {
						String action = getCellContents(row.getCell(TestConstants.TestStepsSheet.ACTION_COL));
						String description = getCellContents(row.getCell(TestConstants.TestStepsSheet.DESCRIPTION_COL));
						String arguments = getCellContents(row.getCell(TestConstants.TestStepsSheet.ARGUMENTS_COL));
						Map<String, String> mapArgs = new LinkedHashMap<String, String>();
						String[] arrArgs = arguments.split(Pattern.quote(TestConstants.DELIMITER));
						for (int ctr = 1; ctr <= arrArgs.length; ctr++) {
							String key = arrArgs[ctr - 1];
							String value = getCellContents(
									row.getCell(TestConstants.TestStepsSheet.ARGUMENTS_COL + ctr));
							mapArgs.put(key, value);
						}
						testStep = new TestStepDefinition();
						testStep.setRowNumber(actualrowNumber);
						testStep.setAction(action);
						testStep.setDescription(description);
						testStep.setArguments(arguments);
						testStep.setMapArguments(mapArgs);
						testStep.setSheetName(pstrSheetName);
						//logger.debug(
							//	"TestCaseStep " + actualrowNumber + " ----------> " + testStep.toString() + " built ");
					}
					if (testStep != null) {
						listTestSteps.add(testStep);
					}
				}
			}
		} catch (TestException te) {
		//	logger.error(te.getMessage());
		}

		return listTestSteps;
	}

	public String getDuration() {
		return duration;
	}

	/**
	 * getCellContents method is used to get the contents of a excel cell object
	 * 
	 * @param c
	 *            : excel cell object
	 * @return : cell contents returned as string if the cell is valid else
	 *         empty string
	 */
	private static String getCellContents(Cell c) {
		if (c != null) {
			return c.toString().trim();
		} else {
			return "";
		}
	}

	public List<TestScenarioDefinition> runTestScenarios() {
		long testsuiteStartTime, testscenarioStartTime, testcaseStartTime, teststepStartTime;
		long testsuiteDuration, testscenarioDuration, testcaseDuration, teststepDuration;
		TestCaseDefinition testCase = null;
		TestStepDefinition testStep = null;
		testsuiteStartTime = testscenarioStartTime = testcaseStartTime = teststepStartTime = System.currentTimeMillis();
		Iterator<TestScenarioDefinition> iterTestScenarios = listTestScenarios.iterator();
		Application euronicsApp = new Application();
		this.scenariosFailed = 0;
		this.scenariosFailed = 0;
		while (iterTestScenarios.hasNext()) {
			TestScenarioDefinition testScenario = iterTestScenarios.next();
			String execute = testScenario.getExecute().toUpperCase();
			if ((TestConstants.YES).equalsIgnoreCase(execute)) {
				try {
					String objectMapper = System.getProperty(TestConstants.SystemProperty.OBJECTREPOSITORY);
					PropertyLoader objectMap = new PropertyLoader(objectMapper);
					euronicsApp.setObjectMap(objectMap);
					EuronicsSeleniumHandler euronicsSeleniumHandler = new EuronicsSeleniumHandler();
					euronicsApp.setEuronicsSeleniumHandler(euronicsSeleniumHandler);					
					logger.info("\n\nStarting Test Scenario==========>" + testScenario.getID());
					testscenarioStartTime = System.currentTimeMillis();
					List<TestCaseDefinition> listTestCases = testScenario.getTestCases();
					Iterator<TestCaseDefinition> iterTestCases = listTestCases.iterator();
					while (iterTestCases.hasNext()) {
						testCase = iterTestCases.next();
						testcaseStartTime = System.currentTimeMillis();
						List<TestStepDefinition> listTestSteps = testCase.getTestSteps();
						Iterator<TestStepDefinition> iterTestSteps = listTestSteps.iterator();
						while (iterTestSteps.hasNext()) {
							testStep = iterTestSteps.next();
							teststepStartTime = System.currentTimeMillis();
							TestStepHandler teststephandler = new TestStepHandler(euronicsApp, testStep);
							teststephandler.handle();
							teststepDuration = System.currentTimeMillis() - teststepStartTime;
							testStep.setDuration(getDurationInMMSS(teststepDuration));
							testStep.setResult("PASS");
						}
						testcaseDuration = System.currentTimeMillis() - testcaseStartTime;
						testCase.setDuration(getDurationInMMSS(testcaseDuration));
						testCase.setResult("PASS");
					}
					testscenarioDuration = System.currentTimeMillis() - testscenarioStartTime;
					testScenario.setDuration(getDurationInMMSS(testscenarioDuration));
					testScenario.setResult("PASS");
					scenariosPassed = scenariosPassed + 1;
					logger.info(" Scenarios Execution time==========>" + testScenario.getDuration());
					logger.info(" Scenario Result==========>" + testScenario.getResult());
					logger.info("End of Test Scenario==========>" + testScenario.getID());

				} catch (TestException te) {
					if (testStep != null) {
						teststepDuration = System.currentTimeMillis() - teststepStartTime;
						testStep.setDuration(getDurationInMMSS(teststepDuration));
						testStep.setResult("FAIL");
						testStep.setComments(te.getMessage());
						if (te.getImagePath() != null) {
							testStep.setImagePath(te.getImagePath());
						}
					}
					testcaseDuration = System.currentTimeMillis() - testcaseStartTime;
					testscenarioDuration = System.currentTimeMillis() - testscenarioStartTime;
					testCase.setDuration(getDurationInMMSS(testcaseDuration));
					testCase.setResult("FAIL");
					testScenario.setDuration(getDurationInMMSS(testscenarioDuration));
					testScenario.setResult("FAIL");
					scenariosFailed = scenariosFailed + 1;
					logger.info("End of Test Scenario==========>" + testScenario.getID());
					euronicsApp.tearDown();
					continue;
				}
			}
		}

		testsuiteDuration = System.currentTimeMillis() - testsuiteStartTime;
		duration = getDurationInMMSS(testsuiteDuration);
		return listTestScenarios;
	}

	private String getDurationInMMSS(long duration) {
		String result = String.format("%d min, %d seconds", TimeUnit.MILLISECONDS.toMinutes(duration),
				TimeUnit.MILLISECONDS.toSeconds(duration)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
		return result;
	}

	/**
	 * @return the scenariosPassed
	 */
	public int getScenariosPassed() {
		return scenariosPassed;
	}

	/**
	 * @return the scenariosFailed
	 */
	public int getScenariosFailed() {
		return scenariosFailed;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	
	/**
	 * 
	 * @returns the url
	 */
	public String getURL() {
		return url;
	}

	/**
	 * @return the listTestScenarios
	 */
	public List<TestScenarioDefinition> getListTestScenarios() {
		return listTestScenarios;
	}	
}
