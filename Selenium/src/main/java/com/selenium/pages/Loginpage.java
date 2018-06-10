package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import com.selenium.uiActions.Waithelper;

public class Loginpage {
	private WebDriver driver;
	Waithelper waithelper;
	 private Logger logger=Logger.getLogger(Loginpage.class);
	public Loginpage(WebDriver driver) {
	this.driver=driver;
	}

	public void clickOnSignInlink(){
		logger.info("clcik on sign in link");
		signin.click();
	}
	public void enteremailaddress(String emailaddress){
		logger.info("entermail"+emailaddress);
		this.emailaddress.sendkeys(emailaddress);
	}
	
	public void enterpassword(String password){
		logger.info("entermail"+password);
		this.password.sendkeys(password);
	}
	public Homepage clicksubmitbutton(){
		logger.info("click submit button");
		submit.click();
		return new Homepage(driver);
	}

public boolean verifySuccessLoginMsg(){
	eturn new Generichelper().isDisplayed(successMsgobject);
}
public void enterRegistrationEmail(){
	String email=System.currentTimeMillis()+"@gmail.com";
logger.info("entering reg mail"+email);
registration.sendkeys(email);
}
	
	
	
}
