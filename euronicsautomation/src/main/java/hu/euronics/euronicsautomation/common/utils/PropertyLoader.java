/**
 * This code is part of WS Publishing.
 * This code is developed by Techwave India Pvt Limited, Hyderabad.
 * Do not make any changes.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * Copyright (C) Techwave India Pvt Limited, Hyderabad.
 * All Rights Reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
 */

package hu.euronics.euronicsautomation.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertyLoader {
	Properties testProperties;
	
	/**
	 * Constructor
	 */
	public PropertyLoader(String strFile) {
		InputStream in = null;
		testProperties = new Properties();
		try {
			System.out.println("loading properties from file "+strFile);
			in = new FileInputStream(strFile);
			testProperties.load(in);			
		} catch (IOException e) {
			System.out.println("loading property file "+strFile +" failed with error "+e.getMessage());
		}
		finally {
			try{
				if(in != null){
					in.close();
				}
			}
			catch(Exception e){
				//ignore
			}
		}
	}
	
	/**
	 * 
	 * @param pstrKey
	 * @return
	 */
	public String  getTestProperty(String pstrKey){	
		String strValue = testProperties.getProperty(pstrKey);
		if(StringUtils.isBlank(strValue)){
				strValue = "";
				System.out.println("invalid or blank property "+pstrKey);
		}
		return strValue;
	}

}
