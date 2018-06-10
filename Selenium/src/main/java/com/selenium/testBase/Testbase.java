package com.selenium.testBase;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.excellread.Excel_reader;

public class Testbase {
	public static final Logger logger=Logger.getLogger(Testbase.class.getName());
	public WebDriver driver;
	public Properties OR;
	public File f1;
	public FileInputStream fi;
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	public Excel_reader excelreader;

	
	public void getbrowser(String browser){
		if(System.getProperty("os.name").equalsIgnoreCase("Windows")){
			if(browser.equalsIgnoreCase("firefox")){
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/drivers/geckodriver.exe");
				driver=new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver.exe");
				driver=new ChromeDriver();	
			}
		}
		else if(System.getProperty("os.name").equalsIgnoreCase("Mac")){
			if(browser.equalsIgnoreCase("firefox")){
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.firefox.marionette",System.getProperty("user.dir")+"/drivers/geckodriver");
				driver=new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/chromedriver");
				driver=new ChromeDriver();		
			}
		}	
	}	
public void loadpropertiesfile() throws IOException{
	String log4jconfpath="log4j.properties";
	PropertyConfigurator.configure(log4jconfpath);
	 OR=new Properties();
	 f1=new File(System.getProperty("user.dir")+"/src/main/java/com/selenium/config/config.properties");
	 fi = new FileInputStream(f1);
		OR.load(fi);
		logger.info("loading config file");
		f1=new File(System.getProperty(""
				+ ""
				+ "user.dir")+"/src/main/java/com/selenium/config/or.properties");
		 fi = new FileInputStream(f1);
		
			OR.load(fi);
	}
	public String getscreenshot(String imagename) throws IOException{
		if(imagename.equals(" ")){
			imagename="blank";
		}
File image = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
String imagelocation=System.getProperty("user.dir")+"\\src\\main\\java\\com\\selenium\\screenshots\\";
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat farmater=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	
	String actualimageName=imagelocation+imagename+"_"+farmater.format(calendar.getTime()+".png");
	File destfile=new File(actualimageName);
	FileUtils.copyFile(image,destfile );
	return actualimageName;
	}
	public WebElement waitforelement(WebDriver driver,long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public WebElement waitforelementwithpollingtimeinterval(WebDriver driver,long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void implicitwait(long time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		
	}
	
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat farmater=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent=new ExtentReports(System.getProperty("user,dir")+"\\src\\main\\java\\com\\selenium\\reports\\test"+farmater.format(calendar.getTime())+".html",false);
	}
	
	public void getresults(ITestResult result) throws IOException{
		if(result.getStatus()==ITestResult.SUCCESS){
			test.log(LogStatus.PASS,result.getName()+"test is pass");
		}else if(result.getStatus()==ITestResult.SKIP){
			test.log(LogStatus.SKIP,result.getName()+"test is skipped and skip reason is"+result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.FAILURE){
			test.log(LogStatus.FAIL, result.getName()+"test is failed"+result.getThrowable());
			String screen=getscreenshot("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
			
		}
		else if(result.getStatus()==ITestResult.STARTED){
			test.log(LogStatus.INFO, result.getName()+"test is started");
	}
}
	@AfterMethod()
	public void aftermethod(ITestResult result) throws IOException{
		getresults(result);
	}
	
	
	@BeforeMethod()
	public void beforemethod(Method result){
		test=extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName()+"teststarted");
		
	}
	
	@AfterClass(alwaysRun=true)
public void endtest(){
		driver.quit();
		extent.endTest(test);
		extent.flush();
	}
	public void getpropertiesdata(){
		
	}
	
	public WebElement getlocator(String locator) throws Exception{
		String[] split=locator.split(":");
		String locatorType=split[0];
		String locatorValue=split[1];
		
		if(locatorType.toLowerCase().equals("id"))
			return driver.findElement(By.id(locatorValue));
		else if(locatorType.toLowerCase().equals("name"))
			return driver.findElement(By.name(locatorValue));
		else if((locatorType.toLowerCase().equals("linktext"))||(locatorType.toLowerCase().equals("link")))
		return driver.findElement(By.linkText(locatorValue));
		else if((locatorType.toLowerCase().equals("cssselector"))||(locatorType.toLowerCase().equals("css")))
			return driver.findElement(By.cssSelector(locatorValue));
		
		else if((locatorType.toLowerCase().equals("tagname"))||(locatorType.toLowerCase().equals("tag")))
			return driver.findElement(By.tagName(locatorValue));
		else if(locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElement(By.partialLinkText(locatorValue));
		
		else if((locatorType.toLowerCase().equals("classname"))||(locatorType.toLowerCase().equals("class")))
			return driver.findElement(By.className(locatorValue));
		else if(locatorType.toLowerCase().equals("xpath"))
			return driver.findElement(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type'"+locatorType+"'");
		
	}
	
	public List<WebElement> getlocators(String locator) throws Exception{
		String[] split=locator.split(":");
		String locatorType=split[0];
		String locatorValue=split[1];
		
		if(locatorType.toLowerCase().equals("id"))
			return driver.findElements(By.id(locatorValue));
		else if(locatorType.toLowerCase().equals("name"))
			return driver.findElements(By.name(locatorValue));
		else if((locatorType.toLowerCase().equals("linktext"))||(locatorType.toLowerCase().equals("link")))
		return driver.findElements(By.linkText(locatorValue));
		else if((locatorType.toLowerCase().equals("cssselector"))||(locatorType.toLowerCase().equals("css")))
			return driver.findElements(By.cssSelector(locatorValue));
		
		else if((locatorType.toLowerCase().equals("tagname"))||(locatorType.toLowerCase().equals("tag")))
			return driver.findElements(By.tagName(locatorValue));
		else if(locatorType.toLowerCase().equals("partiallinktext"))
			return driver.findElements(By.partialLinkText(locatorValue));
		
		else if((locatorType.toLowerCase().equals("classname"))||(locatorType.toLowerCase().equals("class")))
			return driver.findElements(By.className(locatorValue));
		else if(locatorType.toLowerCase().equals("xpath"))
			return driver.findElements(By.xpath(locatorValue));
		else
			throw new Exception("Unknown locator type'"+locatorType+"'");
		
	}
	
	
	public WebElement getwebelement(String locator) throws Exception{
		return getlocator(OR.getProperty(locator));
		
	}
	
	public List<WebElement> getwebelements(String locators) throws Exception{
		return getlocators(OR.getProperty(locators));
		
	}
	
	public String[][] getdata(String excelName,String sheetname) throws IOException{
		System.out.println(System.getProperty("user.dir"));
		String excellocation=System.getProperty("user.dir")+"/src/main/java/data"+excelName;
		System.out.println(excellocation);
		excelreader = new Excel_reader();
	return excelreader.getexcelldata(excellocation, sheetname);
		
	}
	public static void main(String[]args) throws IOException {
			
			Testbase test=new Testbase();
			test.loadpropertiesfile();
			System.out.println(test.OR.getProperty("test"));
			System.out.println(test.OR.getProperty("url"));
			
			
		}
	}


