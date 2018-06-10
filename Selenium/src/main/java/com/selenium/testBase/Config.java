package com.selenium.testBase;

public class Config extends Testbase {
	public String getUsername(){
		return OR.getProperty("Username");	
	}
	public String getPassword(){
		return OR.getProperty("Password");	
	}
	public String getURL(){
		return OR.getProperty("URL");	
	}
	public int getPageloadtimeOut(){
		return Integer.parseInt(OR.getProperty("PageLoadTimeOut"));	
	}
	public int getImplicitwait(){
		return Integer.parseInt(OR.getProperty("Implicitwait"));	
	}
	public int getExplicitwait(){
		return Integer.parseInt(OR.getProperty("Explicitwait"));	
	}
	public String getDbType(){
		return OR.getProperty("DataBase.Type");
		}
	public String getDbconnstr(){
		return OR.getProperty("DataBase.ConnectionStr");
	}
	public String getBrowser(){
		return OR.getProperty("Browser");
	}
}
