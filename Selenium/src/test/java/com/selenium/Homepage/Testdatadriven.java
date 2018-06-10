package com.selenium.Homepage;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.selenium.testBase.Testbase;

public class Testdatadriven extends Testbase {

	@DataProvider(name="testData")
	public Object[][] datasource() throws IOException{
		return getdata("", "");
	}
}
