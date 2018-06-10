/**
 * This code is part of WS Publishing.
 * This code is developed by Techwave India Pvt Limited, Hyderabad.
 * Do not make any changes.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * Copyright (C) Techwave India Pvt Limited, Hyderabad.
 * All Rights Reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++ 
 */


package hu.euronics.euronicsautomation.testdefinitions;

public interface TestConstants {
	
	public static final String DELIMITER =";";
	public static final String CAPABILITIES_FIELD_DELIMITER =";";
	public static final String CAPABILITIES_VALUES_DELIMITER ="=";
	public static final String ROWNUMBER_DELIMITER = ".";
	public static final String ARGUMENT = "ARGUMENT";
	public static final String TESTSCENARIOS_SHEET = "TESTSCENARIOS";
	public static final String TESTCASES_SHEET = "TESTCASES";
	public static final String TESTDATA = "TESTDATA";
	public static final String DRIVERSPATH = "DRIVERSPATH";
	public static final String CAPABILITIES = "CAPABILITIES";	
	public static final String REPORTFOLDER = "REPORTFOLDER";
	public static final String CSSFOLDER ="CSSFOLDER";
	public static final String IMAGESFOLDER ="IMAGESFOLDER";
	public static final String FIREFOXEXE = "FIREFOXEXE";
	public static final String CHROMEEXE = "CHROMEEXE";
	public static final String IEEXE = "IEEXE";
	public static final String ENV = "ENV";
	public static final String URL = "URL";
	public static final String BROWSER = "BROWSER";
	public static final String LOGFOLDER = "LOGFOLDER";
	public static final String OBJECTREPOSITORY = "OBJECTREPOSITORY";
	
	public static final String YES = "Y";
	
	
	public static final String FIELD_SEPARATOR = ";";
	public static final String VALUE_SEPARATOR = "=";
	
	
	public interface SystemProperty{
		String BROWSER = "test.browser";
		String CAPABILITIES= "test.capabilities";
		String DRIVERSPATH= "test.driversPath";
		String URL= "test.url";
		String IMAGES= "test.images";
		String OS_NAME= "os.name";
		String LOGFOLDER= "log.folder";
		String ENV = "app.env";
		String OBJECTREPOSITORY = "test.repository";
	}
	
	public interface TestScenarioSheet{
		int SCENARIOID_COL=0;
		int EXECUTE_COL =1;
		int SCENARIONAME_COL =2;
		int LISTOFTESTCASES_COL =3;
	}
	public interface TestCaseSheet{
		int TESTCASEID_COL =0;
		int TESTCASENAME_COL =1;
		int DESCRIPTION_COL =2;
		int SHEET_COL =3;
		int ROWNUMBER_COL =4;
		
	}
	public interface TestStepsSheet{
		int ROWNUMBER_COL =0;
		int ACTION_COL =1;
		int DESCRIPTION_COL=2;
		int ARGUMENTS_COL =3; 
	}
	
}
