package com.dss.test.dss.subscription;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.dss.test.dss.subscription.*;
import com.dss.test.dataproviders.DSSDataProvider;
import com.dss.test.dss.pageobject.OSentinelCheckoutPageObject;
import com.dss.test.dss.pageobject.OSentinelHomepagePageObject;
import com.dss.test.dss.pageobject.OSentinelSubscriptionPageObject;
import com.dss.test.properties.DSSProperties;
import com.dss.test.utilities.DSSUtilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


/**
 * ------- SubscriptionTests ------- Author: QA-DART Created on: 17-May-2016
 * History of Changes: File created for holding TestNG tests
 * Test changes for POC II
 */

public class SubscriptionTest {

	private WebDriver driver;

	private ExtentTest logger;
	private OSentinelHomepagePageObject OSHomePage;
	private OSentinelSubscriptionPageObject OSSubscriptionPage;
	private OSentinelCheckoutPageObject OSCheckoutPage;
	private DSSUtilities util;
	
	

	 private ExtentReports report = new ExtentReports(DSSProperties.ExtentReportPath);

	/*  @BeforeMethod (alwaysRun = true)
		@Parameters("browser")
		public void beforeTest(String browser) throws InterruptedException, IOException
		{

		  if(browser.equalsIgnoreCase("firefox"))
		  {	  
		   driver = new FirefoxDriver();
		  }
		  
		  else if(browser.equalsIgnoreCase("chrome"))
		  {
			  System.setProperty("webdriver.chrome.driver",DSSProperties.CHROME_DRIVER);
			  driver = new ChromeDriver();
			  driver.get(DSSProperties.URL1);
		  }
		  
		  else if(browser.equalsIgnoreCase("internet explorer"))
		  {
			  System.setProperty("webdriver.ie.driver",DSSProperties.IE_DRIVER);
			  driver = new InternetExplorerDriver();
			  Thread.sleep(5000);
			  driver.get(DSSProperties.URL2);
			  DSSUtilities.Windows_Security_Handler(driver);
		  }
		  
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		  OSHomePage = new OSentinelHomepagePageObject(driver);
		  OSCheckoutPage = new OSentinelCheckoutPageObject(driver);
		  util = new DSSUtilities();
		  
		  
		}
	 
	*/	 
    //Test case execution starts here on one of the browsers - Firefox, Chrome and IE or on all 3 simultaneously. 
	 	@Parameters({ "browser", "version" })
		@BeforeMethod (alwaysRun = true)
		public void beforeTest(String browser, String version) throws MalformedURLException, InterruptedException {

			DesiredCapabilities caps = new DesiredCapabilities();

			if (browser.equalsIgnoreCase("firefox")) {

				caps.setBrowserName("firefox");
			}

			if (browser.equalsIgnoreCase("chrome")) {

				caps.setBrowserName("chrome");
			}

			if (browser.equalsIgnoreCase("internet explorer")) {

				caps.setBrowserName("internet explorer");
			}
			
			caps.setPlatform(Platform.WINDOWS);
			caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			caps.setCapability(CapabilityType.SUPPORTS_WEB_STORAGE, false);
			caps.setVersion(version);

			driver = new RemoteWebDriver(new URL(DSSProperties.hubUrl), caps);
			driver.manage().window().maximize();
			
			if(browser.equalsIgnoreCase("chrome"))
			{	
				driver.get(DSSProperties.URL1);
			
			}
			
			else if(browser.equalsIgnoreCase("internet explorer"))
			{
				driver.get(DSSProperties.URL2);
				Thread.sleep(3000);
				DSSUtilities.Windows_Security_Handler(driver);
			}
			
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			OSHomePage = new OSentinelHomepagePageObject(driver);
			OSCheckoutPage = new OSentinelCheckoutPageObject(driver);
			util = new DSSUtilities();

		} 
	
	       // Test case 1: Subscribe for Print + Digital with SSOR user within area
	       // Zipcode
	       @Test(dataProvider = "WithInAreaZIP SSORUser with CreditCard", dataProviderClass = DSSDataProvider.class, enabled = false, priority=1)
	       public void BuyPrintPlusDigitalSubscriptionWithinAreaZIPWithSSOR(String Scnerio, String WithinAreaZip,
	                     String CreditCardHolderName, String CreditCardNo, String CCExpDate, String FirstName, String LastName,
	                     String Address1, String Address2, String City, String State, String PhoneNo, String Password,
	                     String SSOREmailid) throws Exception {

	              logger = report.startTest("Subscribe Print Plus Digital Subscription Within Area ZIP With SSOR user");
	              System.out.println(Scnerio);
	              String thankYouMessage;
	              int Dateseperater = CCExpDate.indexOf(",");
	              String Month = CCExpDate.substring(0, Dateseperater);
	              String Year = CCExpDate.substring(Dateseperater + 1, CCExpDate.length());
	              boolean accountMenuIcon = false;

	              OSSubscriptionPage = OSHomePage.goToSubscriptionsFromHomepage();
	              logger.log(LogStatus.INFO, "Subscription Page is displayed");

	              OSSubscriptionPage.addPrintDigitalPlusAccessWithinArea(WithinAreaZip);
	              logger.log(LogStatus.INFO, "Entered Within Area ZIP");
	              OSCheckoutPage.enterDigitalAccessSSOR(SSOREmailid);
	              logger.log(LogStatus.INFO, "Entered SSOR email");
	              OSCheckoutPage.payWithCreditCard(CreditCardHolderName, CreditCardNo, Month, Year);
	              logger.log(LogStatus.INFO, "Entered Credit Card Details");

	              OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame(FirstName, LastName, Address1, Address2,
	                           WithinAreaZip, City, State, PhoneNo);
	              logger.log(LogStatus.INFO, "Entered Billing Address");

	              OSCheckoutPage.placeOrder();
	              logger.log(LogStatus.INFO, "Order Placed");
	              OSCheckoutPage.navigateToHomepageStorySSORUser(SSOREmailid, Password);

	              logger.log(LogStatus.INFO, "Navigating to Home Page by Continue to LogIn");

	              thankYouMessage = OSHomePage.getThankYouPanelMessage();
	              Assert.assertEquals(DSSProperties.ActualThankYouMessage, thankYouMessage);
	              logger.log(LogStatus.INFO, "Thank You Panel is displayed");
	              accountMenuIcon = OSHomePage.isUserLoggedIn(SSOREmailid);
	              Assert.assertTrue(accountMenuIcon);
	              OSHomePage.logOutUser(driver);
	              logger.log(LogStatus.INFO, "User logged In");
	              logger.log(LogStatus.PASS, "Test Completed Successfully!!");

	       }

	       // Test case 2: Subscribe for Print + Digital with Non- SSOR user within
	       // area Zipcode
	       @Test(dataProvider = "WithInAreaZIP NONSSORUser with BankAccount", dataProviderClass = DSSDataProvider.class, enabled = true, priority=0)
	       public void BuyPrintPlusDigitalSubscriptionWithinAreaZIPWithNonSSOR(String Scnerio, String WithinAreaZip,
	                     String BankName, String BankAccountNo, String BankRoutingNo, String FirstName, String LastName,
	                     String Address1, String Address2, String City, String State, String PhoneNo, String Password,
	                     String Emailid)
	                     throws InterruptedException {

	              logger = report.startTest("Subscribe Print Plus Digital Subscription Within Area ZIP With Non-SSOR user");
	              String thankYouMessage;
	              boolean accountMenuIcon = false;

	              String email = util.generateEmailid(DSSProperties.MarketName);

	              OSSubscriptionPage = OSHomePage.goToSubscriptionsFromHomepage();
	              logger.log(LogStatus.INFO, "SubsCription Page is displayed");
	              OSSubscriptionPage.addPrintDigitalPlusAccessWithinArea(WithinAreaZip);
	              logger.log(LogStatus.INFO, "Entered Within Area ZIP");
	              OSCheckoutPage.enterDigitalAccessNonSSOR(email, Password, Password);
	              logger.log(LogStatus.INFO, "Entered Non-SSOR email and set password");
	              OSCheckoutPage.payWithMyBankAccount(BankName, BankAccountNo, BankRoutingNo);
	              logger.log(LogStatus.INFO, "Entered Bank Account Details");

	              OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame(FirstName, LastName,
	                           Address1, Address2, WithinAreaZip, City,
	                           State, PhoneNo);
	              logger.log(LogStatus.INFO, "Entered Billing Address");

	              OSCheckoutPage.placeOrder();
	              logger.log(LogStatus.INFO, "Order Placed");
	              System.out.println("Email ID is" + email);
	              OSCheckoutPage.navigateToHomepageStoryNonSSORUser(email, Password);
	              logger.log(LogStatus.INFO, "Navigating to Home Page by Continue reading");

	              thankYouMessage = OSHomePage.getThankYouPanelMessage();
	              Assert.assertEquals(DSSProperties.ActualThankYouMessage, thankYouMessage);
	              logger.log(LogStatus.INFO, "Thank You Panel is displayed");
	              accountMenuIcon = OSHomePage.isUserLoggedIn(email);
	              Assert.assertTrue(accountMenuIcon);
	              OSHomePage.logOutUser(driver);
	              logger.log(LogStatus.INFO, "User logged In");
	              logger.log(LogStatus.PASS, "Test Completed Successfully!!");

	       }

	       // Test case 3: Subscribe for Print + Digital with SSOR user with outside
	       // area Zipcode
	       @Test(dataProvider = "OutOfAreaZIP SSORUser with BankAccount", dataProviderClass = DSSDataProvider.class, enabled = false)
	       public void BuyPrintPlusDigitalSubscriptionOutSideAreaZIPWithSSOR(String Scnerio, String OutofAreaZip,
	                     String BankName, String BankAccountNo, String BankRoutingNo, String FirstName, String LastName,
	                     String Address1, String Address2, String City, String State, String PhoneNo, String Password,
	                     String SSOREmailid) throws InterruptedException {

	              logger = report.startTest("Subscribe Print Plus Digital Subscription Outside Area ZIP With SSOR user");
	              System.out.println(Scnerio);
	              String thankYouMessage;
	              boolean accountMenuIcon = false;

	              String OutsideAreaZipValidationMag;

	              OSSubscriptionPage = OSHomePage.goToSubscriptionsFromHomepage();
	              logger.log(LogStatus.INFO, "SubsCription Page is displayed");
	              OutsideAreaZipValidationMag = OSSubscriptionPage.availableOptionsForOutsideAreaZip(OutofAreaZip);
	              Assert.assertEquals(DSSProperties.OutsideAreaZipValidationActualMesssage, OutsideAreaZipValidationMag);
	              logger.log(LogStatus.INFO, "Verifying the out of are ZIP error message");
	              logger.log(LogStatus.PASS, "Out of area zip error message is displayed. Hence validation passed!!");

	              OSSubscriptionPage.proceedWithTryDigital();
	              logger.log(LogStatus.INFO, "Proceeding with Try Digital");

	              OSCheckoutPage.enterDigitalAccessSSOR(SSOREmailid);
	              logger.log(LogStatus.INFO, "Entered SSOR email");
	              OSCheckoutPage.payWithMyBankAccount(BankName, BankAccountNo, BankRoutingNo);
	              logger.log(LogStatus.INFO, "Entered Bank Account Details");

	              OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame(FirstName, LastName, Address1, Address2,
	                           OutofAreaZip, City, State, PhoneNo);
	              logger.log(LogStatus.INFO, "Entered Billing Address");

	              OSCheckoutPage.placeOrder();
	              logger.log(LogStatus.INFO, "Order Placed");
	              OSCheckoutPage.navigateToHomepageStorySSORUser(SSOREmailid, Password);
	              logger.log(LogStatus.INFO, "Navigating to Home Page by Continue reading");

	              thankYouMessage = OSHomePage.getThankYouPanelMessage();
	              Assert.assertEquals(DSSProperties.ActualThankYouMessage, thankYouMessage);
	              logger.log(LogStatus.INFO, "Thank You Panel is displayed");
	              accountMenuIcon = OSHomePage.isUserLoggedIn(SSOREmailid);
	              Assert.assertTrue(accountMenuIcon);
	              logger.log(LogStatus.INFO, "User logged In");
	              logger.log(LogStatus.PASS, "Test Completed Successfully!!");

	       }

	       // Test case 4: Subscribe for Print + user with Non-SSOR with outside area
	       // Zipcode
	       @Test(dataProvider = "OutOfAreaZIP NONSSORUser with CreditCard", dataProviderClass = DSSDataProvider.class, enabled = false)
	       public void BuyPrintPlusDigitalSubscriptionOutSideAreaZIPWithNonSSOR(String Scnerio, String OutofAreaZip,
	                     String CreditCardHolderName, String CreditCardNo, String CCExpDate, String FirstName, String LastName,
	                     String Address1, String Address2, String City, String State, String PhoneNo, String Password,
	                     String Emailid)
	                     throws InterruptedException {

	              logger = report.startTest("Subscribe Print Plus Digital Subscription Outside Area ZIP With Non-SSOR user");

	              String thankYouMessage;
	              boolean accountMenuIcon = false;
	              String OutsideAreaZipValidationMag;
	              
	              int Dateseperater = CCExpDate.indexOf(",");
	              String Month = CCExpDate.substring(0, Dateseperater);
	              String Year = CCExpDate.substring(Dateseperater + 1, CCExpDate.length());

	              String email = util.generateEmailid(DSSProperties.MarketName);

	              OSSubscriptionPage = OSHomePage.goToSubscriptionsFromHomepage();
	              logger.log(LogStatus.INFO, "SubsCription Page is displayed");
	              OutsideAreaZipValidationMag = OSSubscriptionPage.availableOptionsForOutsideAreaZip(OutofAreaZip);
	              Assert.assertEquals(DSSProperties.OutsideAreaZipValidationActualMesssage, OutsideAreaZipValidationMag);
	              logger.log(LogStatus.INFO, "Verfying the out of are ZIP error message");
	              logger.log(LogStatus.PASS, "Out of area zip error message is displayed. Hence validation passed!!");

	              OSSubscriptionPage.proceedWithTryDigital();
	              logger.log(LogStatus.INFO, "Proceeding with Try Digital");
	              OSCheckoutPage.enterDigitalAccessNonSSOR(email, Password, Password);
	              OSCheckoutPage.payWithCreditCard(CreditCardHolderName, CreditCardNo,
	                           Month, Year);
	              logger.log(LogStatus.INFO, "Entered Credit Card Details");

	              OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame(FirstName, LastName,
	                           Address1, Address2, OutofAreaZip, City,
	                           State, PhoneNo);
	              logger.log(LogStatus.INFO, "Entered Billing Address");

	              OSCheckoutPage.placeOrder();
	              logger.log(LogStatus.INFO, "Order Placed");
	              OSCheckoutPage.navigateToHomepageStoryNonSSORUser(email, Password);

	              logger.log(LogStatus.INFO, "Navigating to Home Page by Continue to LogIn");

	              thankYouMessage = OSHomePage.getThankYouPanelMessage();
	              Assert.assertEquals(DSSProperties.ActualThankYouMessage, thankYouMessage);
	              logger.log(LogStatus.INFO, "Thank You Panel is displayed");
	              accountMenuIcon = OSHomePage.isUserLoggedIn(email);
	              Assert.assertTrue(accountMenuIcon);
	              logger.log(LogStatus.INFO, "User logged In");
	              logger.log(LogStatus.PASS, "Test Completed Successfully!!");

	       }

	       // Test case 5: Subscribe for Digital only with SSOR user
	       @Test(dataProvider = "WithInAreaZIP SSORUser with CreditCard", dataProviderClass = DSSDataProvider.class, enabled = false)
	       public void BuydigitalPlusSubscriptionWithSSOR(String Scnerio, String WithinAreaZip, String CreditCardHolderName,
	                     String CreditCardNo, String CCExpDate, String FirstName, String LastName, String Address1, String Address2,
	                     String City, String State, String PhoneNo, String Password, String SSOREmailid)
	                     throws InterruptedException {

	              logger = report.startTest("Subscribe DigitalPlus Subscription With SSOR user");

	              String thankYouMessage;
	              boolean accountMenuIcon = false;
	              int Dateseperater = CCExpDate.indexOf(",");
	              String Month = CCExpDate.substring(0, Dateseperater);
	              String Year = CCExpDate.substring(Dateseperater + 1, CCExpDate.length());

	              OSSubscriptionPage = OSHomePage.goToSubscriptionsFromHomepage();
	              logger.log(LogStatus.INFO, "SubsCription Page is displayed");
	              OSSubscriptionPage.addDigitalPlusSubscription();
	              logger.log(LogStatus.INFO, "Added DigitalPlus subscription");
	              OSCheckoutPage.selectPackage(driver, DSSProperties.DIGITAL_SAVER);
	              logger.log(LogStatus.INFO, "Digital package selected");
	              OSCheckoutPage.enterDigitalAccessSSOR(SSOREmailid);
	              logger.log(LogStatus.INFO, "Entered SSOR email");
	              OSCheckoutPage.payWithCreditCard(CreditCardHolderName, CreditCardNo,
	                           Month, Year);
	              logger.log(LogStatus.INFO, "Entered Credit Card Details");

	              OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame(FirstName, LastName,
	                           Address1, Address2, WithinAreaZip, City,
	                           State, PhoneNo);
	              logger.log(LogStatus.INFO, "Entered Billing Address");

	              OSCheckoutPage.placeOrder();
	              logger.log(LogStatus.INFO, "Order Placed");
	              OSCheckoutPage.navigateToHomepageStorySSORUser(SSOREmailid, Password);

	              logger.log(LogStatus.INFO, "Navigating to Home Page by Continue to LogIn");

	              thankYouMessage = OSHomePage.getThankYouPanelMessage();
	              Assert.assertEquals(DSSProperties.ActualThankYouMessage, thankYouMessage);

	              logger.log(LogStatus.INFO, "Thank You Panel is displayed");
	              accountMenuIcon = OSHomePage.isUserLoggedIn(SSOREmailid);
	              Assert.assertTrue(accountMenuIcon);
	              logger.log(LogStatus.INFO, "User logged In");
	              logger.log(LogStatus.PASS, "Test Completed Successfully!!");

	       }

	       // Test case 6: Subscribe for Digital only with Non-SSOR user
	              @Test(dataProvider = "WithInAreaZIP NONSSORUser with CreditCard", dataProviderClass = DSSDataProvider.class, enabled = false)
	              public void BuydigitalPlusSubscriptionWithNonSSOR(String Scnerio, String WithinAreaZip,
	                           String CreditCardHolderName, String CreditCardNo, String CCExpDate, String FirstName, String LastName,
	                           String Address1, String Address2, String City, String State, String PhoneNo, String Password,
	                           String Emailid)
	                           throws InterruptedException {

	              logger = report.startTest("Subscribe DigitalPlus Subscription With Non-SSOR user");

	              String thankYouMessage;
	              boolean accountMenuIcon = false;
	              
	              int Dateseperater = CCExpDate.indexOf(",");
	              String Month = CCExpDate.substring(0, Dateseperater);
	              String Year = CCExpDate.substring(Dateseperater + 1, CCExpDate.length());

	              String email = util.generateEmailid(DSSProperties.MarketName);

	              OSSubscriptionPage = OSHomePage.goToSubscriptionsFromHomepage();
	              logger.log(LogStatus.INFO, "SubsCription Page is displayed");
	              OSSubscriptionPage.addDigitalPlusSubscription();
	              logger.log(LogStatus.INFO, "Added DigitalPlus subscription");
	              OSCheckoutPage.selectPackage(driver, DSSProperties.DIGITAL);
	              logger.log(LogStatus.INFO, "Digital package selected");
	              OSCheckoutPage.enterDigitalAccessNonSSOR(email, Password, Password);
	              logger.log(LogStatus.INFO, "Entered Non-SSOR email and set password");
	              OSCheckoutPage.payWithCreditCard(CreditCardHolderName, CreditCardNo,
	                           Month, Year);
	              logger.log(LogStatus.INFO, "Entered Credit Card Details");

	              OSCheckoutPage.enterAddressWhenBillingAndDeliveryInformationSame(FirstName, LastName,
	                           Address1, Address2, WithinAreaZip, City,
	                           State, PhoneNo);
	              logger.log(LogStatus.INFO, "Entered Billing Address");

	              OSCheckoutPage.placeOrder();
	              logger.log(LogStatus.INFO, "Order Placed");
	              OSCheckoutPage.navigateToHomepageStoryNonSSORUser(email, Password);

	              logger.log(LogStatus.INFO, "Navigating to Home Page by Continue to LogIn");

	              thankYouMessage = OSHomePage.getThankYouPanelMessage();
	              Assert.assertEquals(DSSProperties.ActualThankYouMessage, thankYouMessage);
	              logger.log(LogStatus.INFO, "Thank You Panel is displayed");
	              accountMenuIcon = OSHomePage.isUserLoggedIn(email);
	              Assert.assertTrue(accountMenuIcon);
	              logger.log(LogStatus.INFO, "User logged In");
	              logger.log(LogStatus.PASS, "Test Completed Successfully!!");

	       }


	@AfterMethod
	public void afterTest(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			String screenshot_path = util.captureScrenshot(driver, result.getName());
			String image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.FAIL, "Test Verification", image);

		}

		report.endTest(logger);
		report.flush();
		driver.quit();
        
	}

}
