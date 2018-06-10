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

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.ErrorCode;

import hu.euronics.euronicsautomation.testdefinitions.TestConstants;

public class CustomAppender extends FileAppender {

	public CustomAppender() {
	}

	public CustomAppender(Layout layout, String filename, boolean append, boolean bufferedIO, int bufferSize)
			throws IOException {
		super(layout, filename, append, bufferedIO, bufferSize);
	}

	public CustomAppender(Layout layout, String filename, boolean append) throws IOException {
		super(layout, filename, append);
	}

	public CustomAppender(Layout layout, String filename) throws IOException {
		super(layout, filename);
	}
	
	 public void activateOptions() {
		    if (fileName != null) {
		    	try {
		    		String logFolder =System.getProperty(TestConstants.SystemProperty.LOGFOLDER);
		    		if(StringUtils.isNotBlank(logFolder)){
		    			fileName = logFolder+File.separator+getNewLogFileName();
		    		}else{
		    			fileName = getNewLogFileName();
		    		}
		    		setFile(fileName, fileAppend, bufferedIO, bufferSize);
		    	} catch (Exception e) {
		    		errorHandler.error("Error while activating log options", e,
		    				ErrorCode.FILE_OPEN_FAILURE);
		    	}
		    }
	  }

	private String getNewLogFileName() {
		if (fileName != null) {
			final String DOT = ".";
			final String UNDERSCORE = "_";
			final File logFile = new File(fileName);
			final String fileName = logFile.getName();
			String newFileName;
			
			final int dotIndex = fileName.indexOf(DOT);
			String timeStamp  = getTimeStamp();
			if (dotIndex != -1) {
				String prefix = fileName.substring(0, dotIndex);
				String suffix = fileName.substring(dotIndex+1);
				newFileName = prefix + UNDERSCORE + timeStamp + DOT +suffix;
			} else {
				newFileName = fileName + UNDERSCORE + timeStamp;
			}
			
			return newFileName;
		}
		return null;
	}
	
	
	private String getTimeStamp(){
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String timeStamp=sdf.format(date.getTime());
		return timeStamp;
	}
}
