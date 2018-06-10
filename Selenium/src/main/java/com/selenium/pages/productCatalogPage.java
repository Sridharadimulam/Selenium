package com.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import com.selenium.uiActions.Dropdownhelper;
import com.selenium.uiActions.Waithelper;

public class productCatalogPage {
	 private Logger logger=Logger.getLogger(productCatalogPage.class);
	private WebDriver driver;
	
Waithelper waithelper;
	public productCatalogPage(WebDriver driver) {
	this.driver=driver;
	}

	public void selectSortByFilter(String dataToselect){
		Dropdownhelper dropdown=new Dropdownhelper(driver);
		dropdown.SelectUsingVisiblevalue(sortBy, dataToselect);
	}
	
}
