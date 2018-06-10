package com.selenium.uiActions;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Dropdownhelper {
	private WebDriver driver;
	private Logger logger =Logger.getLogger(Dropdownhelper.class);
	
public Dropdownhelper(WebDriver driver){
	
	this.driver=driver;
	logger.debug("Dropdownhelper:"+this.driver.hashCode());
}
public void SelectUsingVisiblevalue(WebElement element,String visiblevalue){
	Select select=new Select(element);
	select.selectByVisibleText(visiblevalue);
	logger.info("Locator:"+element+"value:"+visiblevalue);
}

public String getSelectedvalue(WebElement element){
	String value=new Select(element).getFirstSelectedOption().getText();
	logger.info("Webelement:"+element+"value:"+value);
	return value;
	}


public void selectusingIndex(WebElement element,int index){
	Select select=new Select(element);
	select.selectByIndex(index);
	logger.info("Locator:"+element+"value:"+index);
	}


public List<String>getAllDropDownValues(WebElement locator){
	Select select=new Select(locator);
	List<WebElement>elementlist=select.getOptions();
	List<String>valueList=new LinkedList<String>();
	for(WebElement element:elementlist){
		logger.info(element.getText());
		valueList.add(element.getText());
		
	}return valueList;
	
}
}
