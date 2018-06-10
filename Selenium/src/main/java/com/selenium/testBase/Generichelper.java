package com.selenium.testBase;

import javax.swing.text.Element;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class Generichelper {
	private static final Logger log=Logger.getLogger(Generichelper.class);
	public String readvalueFromElement(WebElement element){
		if(null==element){
			log.info("webelemt is null");
			return null;
		}
		boolean displayed=false;
		try{
			displayed=isDisplayed(element);
		}catch(Exception e){
			log.error(e);
return null;		}
	}
if(!displayed){
	return null;
	
}
String text=element.getText();
log.info("webelement values is.."+text);
return text;
}




}
