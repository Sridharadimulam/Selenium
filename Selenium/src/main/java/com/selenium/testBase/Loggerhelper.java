package com.selenium.testBase;

import javax.annotation.Resource;

import org.apache.log4j.PropertyConfigurator;
import org.testng.log4testng.Logger;

@SuppressWarnings("rawtypes")
public class Loggerhelper {
	private static boolean root=false;
	public static Logger getLogger(Class clas){
		if(root){
			return Logger.getLogger(clas);
		}
		PropertyConfigurator.configure();
	}

}
