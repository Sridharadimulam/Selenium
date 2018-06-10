package hu.euronics.euronicsautomation.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hu.euronics.euronicsautomation.common.utils.HTMLReport;
import hu.euronics.euronicsautomation.common.utils.PropertyLoader;
import hu.euronics.euronicsautomation.testdefinitions.TestConstants;
import hu.euronics.euronicsautomation.testdefinitions.TestSuite;
import hu.euronics.euronicsautomation.webdriver.utils.WebDriverConstants;

public class TestDriver {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args)  {

		final PropertyLoader propertyloader = new PropertyLoader(args[0]);

		// url under test
		String url = propertyloader.getTestProperty(TestConstants.URL);

		// testdata excel xlsx file with defined test cases
		String testData = propertyloader.getTestProperty(TestConstants.TESTDATA);

		// browser on which the tests have to be run
		String browser = propertyloader.getTestProperty(TestConstants.BROWSER);

		// specific browser capabilities
		String capabilities = propertyloader.getTestProperty(TestConstants.CAPABILITIES);

		// Path to the webdriver for the selected browser
		String driversPath = propertyloader.getTestProperty(TestConstants.DRIVERSPATH);

		// the folder where test reports and error images are captured
		String reportFolder = propertyloader.getTestProperty(TestConstants.REPORTFOLDER);

		// the environment related to which test are run.
		String env = propertyloader.getTestProperty(TestConstants.ENV);
		
		//read the object repository file
		String objectRepository = propertyloader.getTestProperty(TestConstants.OBJECTREPOSITORY);
		File f= new File(objectRepository);
		if(f.exists()){
			objectRepository = f.getAbsolutePath();
		}
		
		
		// the folder where the log files have to be generated
		String logFolder = propertyloader.getTestProperty(TestConstants.LOGFOLDER);

		System.setProperty(TestConstants.SystemProperty.BROWSER, browser);
		System.setProperty(TestConstants.SystemProperty.CAPABILITIES, capabilities);
		System.setProperty(TestConstants.SystemProperty.DRIVERSPATH, driversPath);
		System.setProperty(TestConstants.SystemProperty.URL, url);
		System.setProperty(TestConstants.SystemProperty.IMAGES, reportFolder);
		System.setProperty(TestConstants.SystemProperty.ENV, env);
		System.setProperty(TestConstants.SystemProperty.OBJECTREPOSITORY, objectRepository);
		System.setProperty(TestConstants.SystemProperty.LOGFOLDER, logFolder);

		TestSuite testSuite = new TestSuite();
		testSuite.buildTestScenarios(url, testData);
		testSuite.runTestScenarios();
		String resultFilePath = createTestReport(reportFolder, testSuite);
		String browserExecutable = getBrowserBySystemProperty(propertyloader);
		
		HTMLReport.openTestResults(browserExecutable, resultFilePath);
	}
	
	/**
	 * this method is used to generate the final test report post execution.
	 * @param reportFolder
	 * @param testSuite
	 * @return
	 */
	private static String createTestReport(String reportFolder, TestSuite testSuite) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date date = new Date();
		String timeStamp = sdf.format(date);
		String testReport = reportFolder + File.separator + "TestReport_" + timeStamp + ".html";
		HTMLReport.buildHTMLReport(testReport, testSuite);
		File f= new File(testReport);
		return f.getAbsolutePath();
		
	}
	
	
	/**
	 * this method is used to browser executable, which is used to oepn the test report 
	 * @param propertyloader
	 * @return
	 */
	private static String getBrowserBySystemProperty(PropertyLoader propertyloader) {
		String browserExecutable;
		String browser = System.getProperty(TestConstants.SystemProperty.BROWSER);
		if ((WebDriverConstants.Browsers.FIREFOX).equalsIgnoreCase(browser)) {
			browserExecutable = propertyloader.getTestProperty(TestConstants.FIREFOXEXE);
		} else if ((WebDriverConstants.Browsers.INTERNETEXPLORER).equalsIgnoreCase(browser)) {
			browserExecutable = propertyloader.getTestProperty(TestConstants.IEEXE);
		} else {
			// set default browser as chrome
			browserExecutable = propertyloader.getTestProperty(TestConstants.CHROMEEXE);
		}
		return browserExecutable;
	}

}
