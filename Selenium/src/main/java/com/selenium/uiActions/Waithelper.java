package com.selenium.uiActions;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waithelper {
	private WebDriver driver;
	private Logger log=Logger.getLogger(Waithelper.class);
	public Waithelper(WebDriver driver){
		this.driver=driver;
		log.debug("waitHelper:"+this.driver.hashCode());
	}
	public void setImplicitwait(long timeout,TimeUnit unit){
		log.info(timeout);
		driver.manage().timeouts().implicitlyWait(timeunit, unit==null ? TimeUnit.SECONDS:);
	}
	
	private WebDriverWait getwait(int timeOutInseconds,int pollingEveryInMilesec){
		log.debug("");
		WebDriverWait wait=new WebDriverWait(driver,timeOutInseconds);
	wait.pollingEvery(pollingEveryInMilesec,TimeUnit.MILLISECONDS);
	wait.ignoring(NoSuchElementException.class);
	wait.ignoring(ElementNotVisibleException.class);
	wait.ignoring(StaleElementReferenceException.class);
	wait.ignoring(NoSuchFrameException.class);
	return wait;
	}

}
