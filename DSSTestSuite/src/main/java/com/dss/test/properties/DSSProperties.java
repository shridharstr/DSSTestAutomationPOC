package com.dss.test.properties;

import java.io.File;

import org.apache.poi.util.SystemOutLogger;

/**
 * ------- DSSProperties ------- 
 * Author: QA-DART 
 * Created on: 17-May-2016
 * History of Changes: Class to hold property values used in test cases
 */
public class DSSProperties {

	public static final String URL1 = "http://nguxbeta:nguxtr!b@ngux.orlandosentinel.stage.tribdev.com/";
	public static final String URL2 = "http://ngux.orlandosentinel.stage.tribdev.com/";
	public static final String userName="nguxbeta";
	public static final String userPassword="nguxtr!b";
	public static final String MarketName = "OS";
	public static final String hubUrl = "http://10.20.121.74:4444/wd/hub";
	
	
	//Web driver paths
	public static final String CHROME_DRIVER = System.getProperty("user.dir") + File.separator + "src/main/java" + File.separator + "com/dss/test/properties" + File.separator + "chromedriver.exe";
	public static final String IE_DRIVER = System.getProperty("user.dir") + File.separator + "src/main/java" + File.separator + "com/dss/test/properties" + File.separator + "IEDriverServer32bit.exe";
	public static final String EDGE_DRIVER = System.getProperty("user.dir") + File.separator + "src/main/java" + File.separator + "com/dss/test/properties" + File.separator + "MicrosoftWebDriver.exe";
	public static final String filePath = System.getProperty("user.dir") + File.separator + "src/main/java" + File.separator + "com/dss/test/dataproviders" + File.separator + "OSTestData.xlsx";
	
	// Subscriptions: Digital, Digital Super Saver, Digital Saver
	public static final String DIGITAL = "digital";
	public static final String DIGITAL_SUPER_SAVER = "digital_super_saver";
	public static final String DIGITAL_SAVER = "digital_saver";
	public static final String MOST_POPULAR = "most_popular";
	
	public static final String CONTINUE_READING = "Continue reading";
	public static final String LOGIN_TO_CONTINUE = "Log In To Continue";
	
	public static final String SOURCE_PATH = "C:\\Users\\gauravs\\git\\DSSTestSuite\\DSSTestSuite\\test-output";
	public static final String SOURCE_FOLDER = "C:\\Users\\gauravs\\git\\DSSTestSuite\\DSSTestSuite\\test-output\\html";
	public static final String DESTINATION_FOLDER_NAME = "C:\\Users\\gauravs\\git\\DSSTestSuite\\DSSTestSuite\\test-output\\html.zip";
		
	 public static final String FromAddress = "QADART<gaurav.sharma@bitwiseglobal.com>";
	 public static final String ToAddress = "gaurav.sharma@bitwiseglobal.com";
	 public static final String SubjectLine = "DSS Regression Report";
	 public static final String BodyMessage = "Please find the attached DSS regression test result report.";
	 
	 public static final String OutsideAreaZipValidationActualMesssage = "You are outside our delivery area. But you can still get Unlimited Digital Access.";
	
	 public static final String ActualThankYouMessage ="Welcome to orlandosentinel.com. You are now subscribed.";
	 
	 public static final String ExtentReportPath = "C:\\DSSTestReport\\DSSTestAutomationReport.html";
	 
	 
}
