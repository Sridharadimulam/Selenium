package com.selenium.uiActions;

import java.awt.Window;
import java.util.LinkedList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

public class Browserhelper {
	private WebDriver driver;
	private Logger logger=Logger.getLogger(Browserhelper.class);
public  Browserhelper(WebDriver driver){
	this.driver=driver;
	logger.debug("BrowserHelper:"+this.driver.hashCode());
}
public void goback(){
	driver.navigate().back();
	logger.info("");
}
	public void gofarward(){
		driver.navigate().forward();
		logger.info("");
	}
public void refresh(){
	driver.navigate().refresh();
	logger.info("");
}
public  Set<String>getWindowhandles(){
	logger.info("");
	return driver.getWindowHandles();
}
public void SwitchToWindow(int index){
	LinkedList<String> windowsID=new LinkedList<String>(getWindowhandles());
	if(index<0|| index>windowsID.size())
		throw new IllegalArgumentException("Invalid Index:"+index);
	driver.switchTo().window(windowsID.get(index));
	logger.info(index);
	}
public void SwitchToParentwindow(){
	LinkedList<String>windowsId=new LinkedList<String>(getWindowhandles());
	driver.switchTo().window(windowsId.get(0));
	logger.info("");
}
public void SwitchToParentwithchildclose(){
	switchToParentWindow();
	LinkedList<String>windowsId=new LinkedList<String>(getWindowhandles());
	for(int i=1;i<windowsId.size();i++){
		logger.info(windowsId.get(i));
		driver.switchTo().window(windowsId.get(i));
		driver.close();
		
	}switchToParentWindow();
	
}
public void switchToFrame(String nameOrId){
	driver.switchTo().frame(nameOrId);
	logger.info(nameOrId);
		
	}
	
public void switchToParentWindow(){
	LinkedList<String> windowsID=new LinkedList<String>(getWindowhandles());
	driver.switchTo().window(windowsID.get(0));
	logger.info("");
}


}



