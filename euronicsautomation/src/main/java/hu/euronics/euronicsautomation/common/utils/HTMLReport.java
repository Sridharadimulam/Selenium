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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

import hu.euronics.euronicsautomation.testdefinitions.TestCaseDefinition;
import hu.euronics.euronicsautomation.testdefinitions.TestScenarioDefinition;
import hu.euronics.euronicsautomation.testdefinitions.TestStepDefinition;
import hu.euronics.euronicsautomation.testdefinitions.TestSuite;

public class HTMLReport {
	private static final Logger logger = Logger.getLogger(HTMLReport.class.getSimpleName());
	private static final String css = "D:/Softwares/OneDrive_2018-02-02/automationworkspace/euronicsautomation/Reports/StyleSheets";
	private static final String PASS = "PASS";

	/**
	 * Building HTML Report once the after completion of Test Scenario Run
	 * 
	 * @param resultsFile
	 * @param listTS
	 */
	public static void buildHTMLReport(String resultsFile,TestSuite testSuite){

		Writer htmlWriter = null;
		FileOutputStream fos = null;
		try {
			String td;
			File file = new File(resultsFile);
			fos = new FileOutputStream(file);
			Writer w = new OutputStreamWriter(fos, "UTF-8");
			htmlWriter = new BufferedWriter(w);
			htmlWriter.write("<Html>");
			htmlWriter.write("<Head><link rel=\"stylesheet\" href=\"" + css + File.separator
					+ "bootstrap.min.css\"><title>Test Report - Result Log</title></Head>");
			htmlWriter.write("<link rel=\"stylesheet\" href=\"" + css + File.separator + "site.css\">");
			htmlWriter.write("<body>");
			htmlWriter.write("<div class='container'><div id='mainContainer'><div id='logocontainer'><img src=" + css
					+ File.separator
					+ "euronics-logo.png></div></div><h4><div id='headingcontainer'>Test Automation Results Report</div></h4><div align='left' style='margin-left:-40px;'>");
			htmlWriter.write("<br/>");
			htmlWriter.write("<table width='80%' border='0px' cellspacing='2' align='center'><tr>");			
			htmlWriter.write("<td colspan='1'><strong>Environment:</strong>&nbsp;&nbsp;<font color='blue'>"+testSuite.getURL()+"</font></td>");
			htmlWriter.write("<td colspan='1'><strong>Total Scenarios:</strong>&nbsp;&nbsp;<font color='blue'>"+(testSuite.getScenariosPassed()+ testSuite.getScenariosFailed())+"</font></u></td>");
			htmlWriter.write("<td colspan='1' class='success'><strong>Scenarios Passed:</strong>&nbsp;&nbsp;<u><font color='green'>"+testSuite.getScenariosPassed()+"</font></u></td>");
			htmlWriter.write("<td colspan='1'  class='default'><strong>Scenarios Failed:</strong>&nbsp;&nbsp;<u><font color='red'>"+testSuite.getScenariosFailed()+"</font></u></td>");
			htmlWriter.write("</tr>");
			htmlWriter.write("<tr>");
			htmlWriter.write("<td colspan='1'><strong>Date:</strong>&nbsp;&nbsp;<font color='blue'>"+testSuite.getTimestamp()+"</font></td>");
			htmlWriter.write("<td colspan='1'><strong>Total Execution Time:</strong>&nbsp;&nbsp;<font color='blue'>"+testSuite.getDuration()+"</font></td>");
			htmlWriter.write("</tr>");
			htmlWriter.write("</table><br/>");
			htmlWriter.write("<table class='table'  width='80%' border='1px'>");
			htmlWriter.write("<tr>");
			htmlWriter.write(
					"<tr class='header'><th align='center' class='default'>TestScenario</th>");
			htmlWriter.write(
					"<th  align='center' class='default'>Duration</th>");
			htmlWriter.write(
					"<th  align='center' class='default'>Scenario Description</th>");
			htmlWriter.write(" <th  class='default' align='center' class='default'>TestCase Description</th>");
			htmlWriter.write("<th  class='default' align='center'>TestStep Description</th>");
			htmlWriter.write(" <th class='default' align='center' class='default'>Result</th>");
			htmlWriter.write("<th  class='default'align='center'>Comments</th>");
			htmlWriter.write("</tr>");
			List<TestScenarioDefinition> listTS = testSuite.getListTestScenarios();
			Iterator<TestScenarioDefinition> iterTestScenarios = listTS.iterator();
			while (iterTestScenarios.hasNext()  ) {
				TestScenarioDefinition ts = iterTestScenarios.next();
				if(StringUtils.isNotBlank(ts.getResult())){
					if (PASS.equalsIgnoreCase(ts.getResult())) {
						td = "<td class=\"success\">";

					} else {
						td = "<td class=\"danger\">";
					}
					htmlWriter.write("<tr>");
					htmlWriter.write(td + ts.getID() + "</td>");
					htmlWriter.write(td + ts.getDuration() + "</td>");
					htmlWriter.write(td + ts.getTestScenario() + "</td>");
					List<TestCaseDefinition> listTC = ts.getTestCases();
					Iterator<TestCaseDefinition> iterTestCases = listTC.iterator();
					while (iterTestCases.hasNext()) {
						TestCaseDefinition tc = iterTestCases.next();
						writeTestCase(tc, htmlWriter);
						if (iterTestCases.hasNext()) {
							htmlWriter.write("<tr>");
							htmlWriter.write("<td class='default'>&nbsp;" + " " + "</td>");
							htmlWriter.write("<td class='default'>&nbsp;" + " " + "</td>");
							htmlWriter.write("<td class='default'>&nbsp;" + " " + "</td>");
						}
					}
				}
			}
			htmlWriter.write("</tr>");
			htmlWriter.close();
			fos.close();

		} catch (IOException e) {
			//logger.error("error creating file " + resultsFile);
		}finally{

			try {
				if(htmlWriter!=null){
					htmlWriter.close();
				}
				if(fos!=null){
					fos.close();
				}
			} catch (IOException e) {
				//ignore
				//logger.warn("ignoring IOException in finally block");
			}

		}
	}

	/**
	 * Adding the Test Case Info To HTML Report
	 * @param tc
	 * @param htmlWriter
	 */
	private static void writeTestCase(TestCaseDefinition tc, Writer htmlWriter) {

		String td;
		try {
			if (tc.getResult() == null) {
				td = "<td class=\"default\">";
			} else if (PASS.equalsIgnoreCase(tc.getResult())) {
				td = "<td class=\"success\">";
			} else {
				td = "<td class=\"danger\">";
			}
			htmlWriter.write(td + tc.getDescription() + "</td>");
			List<TestStepDefinition> listTestSteps = tc.getTestSteps();
			Iterator<TestStepDefinition> iterTestSteps = listTestSteps.iterator();
			while (iterTestSteps.hasNext()) {
				TestStepDefinition testStep = iterTestSteps.next();
				writeTestStep(testStep, htmlWriter);
				if (iterTestSteps.hasNext()) {
					htmlWriter.write("<tr>");
					htmlWriter.write("<td class='default'>&nbsp;" + " " + "</td>");
					htmlWriter.write("<td class='default'>&nbsp;" + " " + "</td>");
					htmlWriter.write("<td class='default'>&nbsp;" + " " + "</td>");
					htmlWriter.write("<td class='default'>&nbsp;" + " " + "</td>");
				}
			}

		} catch (IOException e) {
			//logger.error("error writing testcase to html report file ");
		}

	}

	/**
	 * Adding the Test Step Info To HTML Report
	 * @param ts
	 * @param htmlWriter
	 */
	private static void writeTestStep(TestStepDefinition ts, Writer htmlWriter) {
		try {
			String td;
			if (ts.getResult() == null) {
				td = "<td class=\"default\">";
			} else if (PASS.equalsIgnoreCase(ts.getResult())) {
				td = "<td class=\"success\">";
			} else {
				td = "<td class=\"danger\">";
			}

			htmlWriter.write(td + ts.getDescription() + "</td>");
			if (StringUtils.isNotBlank(ts.getResult())) {
				htmlWriter.write(td + ts.getResult() + "</td>");
			} else {
				htmlWriter.write(td + "</td>");
			}
			if (StringUtils.isNotBlank(ts.getComments())) {
				htmlWriter.write(td + ts.getComments() + "<a target=\"_blank\" href=" + ts.getImagePath()
				+ " class=\"btn btn-info btn-xs\"><span class=\"glyphicon glyphicon-file\"></span>File</a></td>");

			} else {
				htmlWriter.write(td + "</td>");
			}
			htmlWriter.write("</tr>");
		} catch (IOException e) {
			//logger.error("error writing testsetep to html report file ");
		}

	}

	/**
	 * Once the HTML Report built with Test Case & Test Step ; HTML Report will be opened in Browser 
	 *
	 * @param browser_exe
	 *            the command to be used for showing the results
	 * @param url
	 *            URL to results
	 */
	public static void openTestResults(String browser_exe, String url) {
		try {
			Runtime.getRuntime().exec(browser_exe + " " + url);
		} catch (IOException e) {
			//logger.error("error opening html report file " + url);
		}
	}
}