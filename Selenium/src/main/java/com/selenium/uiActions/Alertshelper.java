package com.selenium.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class Alertshelper {
	private WebDriver driver;
	private  static final Logger logger=Logger.getLogger(Alertshelper.class.getName());
	
	
	public void alerthelper(WebDriver driver){
		this.driver=driver;
		logger.debug("alerthelper:"+this.driver.hashCode());
}
	public Alert getalert(){
		logger.info(" ");
		return driver.switchTo().alert();
	}
	public void AcceptAlert(){
		logger.info(" ");
		getalert().accept();
}
	public void Dismissalert(){
		logger.info(" ");
		getalert().dismiss();
}
	public String getalerttext(){
		String text=getalert().getText();
		logger.info(text);
		return text;
	}
	
	public boolean isAlertPresent(){
		try{
			driver.switchTo().alert();
			logger.info(true);
			return true;
		}catch(NoAlertPresentException e){
			logger.info(false);
			return false;
		}
	}
		public void AcceptAlertIfpresent(){
			if(!isAlertPresent())
				return;
				AcceptAlert();
				logger.info("");
			}	
		
			public void DismissAlertIfpresent(){
				if(!isAlertPresent())
					return;
					Dismissalert();
					logger.info("");
				}	
			public void AcceptPrompt(String text){
				if(!isAlertPresent())
					return;
					Alert alert=getalert();
					alert.sendKeys(text);
					alert.accept();
					logger.info(text);
				}	
			
	}
	
	
	
	
	
	
	
	

