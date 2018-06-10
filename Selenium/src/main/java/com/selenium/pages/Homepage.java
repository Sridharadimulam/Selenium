package com.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import com.selenium.uiActions.Waithelper;

public class Homepage {
	Waithelper waithelper;
	 WebDriver driver;
	 private Logger logger=Logger.getLogger(Homepage.class);
	 
	public Homepage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
		waithelper=new Waithelper(driver);
		waithelper.
		
	}
	
	public void mouseOver(String data){
		logger.info("doing mouse over on:"+data);
		Actions action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(""))).build().perform();
	}
	public productCatalogPage clickOnItem(String data){
		logger.info("click on :"+data);
		driver.findElement(By.xpath("")).click();
		return new productCatalogPage(driver);
		
	}
	public productCatalogPage clickOnmenu(WebElement element){
		logger.info("click on :"+element.getText());;
		element.click();
		return new productCatalogPage(driver);
	}
}
