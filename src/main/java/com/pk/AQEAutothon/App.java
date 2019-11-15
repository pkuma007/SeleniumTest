<<<<<<< HEAD
package com.pk.AQEAutothon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;



/**
 * Main Class for Tests
 *
 */
public class App 
{
	
	//Launching Browser based on conditions
	public  WebDriver launchBrowser(String browserType, String url) {
		WebDriver driver=null;
		if(browserType.equals("CHROME")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserType.equals("FIREFOX"))  {
			System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
			Proxy proxy = new Proxy();
		    proxy.setProxyType(Proxy.ProxyType.AUTODETECT);
		    FirefoxOptions options = new FirefoxOptions();
		    options.setProxy(proxy);
			driver = new FirefoxDriver(options);
		}else if(browserType.equals("ANDROID_APP")) {
			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				// set the capability to execute test in chrome browser
				//capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
				// set the capability to execute our test in Android Platform
				// we need to define platform name
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
				// Set the device name as well (you can give any name)
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"MyPhone");
				//Automation MAe for appium
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1");
				//Device ID
				capabilities.setCapability(MobileCapabilityType.UDID,"0837fef4252c056e");
				//ANDROID Version
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0.1");
				
				capabilities.setCapability("newCommandTimeout",90000);
				
				capabilities.setCapability("autoGrantPermissions",true);
				capabilities.setCapability("noReset",false);

				capabilities.setCapability("appPackage","com.flipkart.android");
				capabilities.setCapability("appActivity","com.flipkart.android.activity.HomeFragmentHolderActivity");
				capabilities.setCapability("appWaitPackage","com.flipkart.android");
				capabilities.setCapability("appWaitActivity","com.flipkart.android.activity.HomeFragmentHolderActivity");
				 
				
				// Skip the installation of io.appium.settings app and the UIAutomator 2 server.
				capabilities.setCapability("skipDeviceInitialization", true);
				capabilities.setCapability("skipServerInstallation", true);
				
				// Create object of URL class and specify the appium server address
				URL appium_url = new URL("http://127.0.0.1:4723/wd/hub");
				// Create object of  AndroidDriver class and pass the url and capability that we created
				driver = new AppiumDriver(appium_url, capabilities);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
				return driver;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Please provide correct Driver browserType to launch");
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		return driver;
	}
    
	//Read data from Excel
	public String readExcel(String TestName) throws IOException{
		String output=null;
	    //Create an object of File class to open xlsx file
		String filePath = "TestData.xlsx";
	    File file =    new File(filePath);
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook workbook = null;
	    //Check condition if the file is xlsx file
	    if(filePath.contains(".xlsx")){
	    	workbook = new XSSFWorkbook(inputStream);
	    }
	    else if(filePath.equals(".xls")){
	    	workbook = new HSSFWorkbook(inputStream);
	    }
	    //Read sheet inside the workbook by its name
	    Sheet sheet = workbook.getSheet("Sheet1");
	    //Find number of rows in excel file
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    //Create a loop over all the rows of excel file to read it
	    for (int i = 0; i < rowCount+1; i++) {
	        Row row = sheet.getRow(i);
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	        	if (row.getCell(j).getStringCellValue().equalsIgnoreCase(TestName)) {
	        		output = row.getCell(j+1).getStringCellValue();
	        		break;
	        	}
	            //System.out.print(row.getCell(j).getStringCellValue()+"|| ");
	        }
	        
	    } 
	    return output;
	 }  
	
	//Take screenshot
	public static String takeScreenShot(WebDriver webdriver) throws IOException {
	
		File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("Report/Screenshots/" + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getPath();
		FileUtils.copyFile(scrFile, Dest);
		errflpath = errflpath.replace("Report\\", "");
		return errflpath;
	}
	
	   
}
=======
package com.pk.AQEAutothon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;



/**
 * Main Class for Tests
 *
 */
public class App 
{
	
	//Launching Browser based on conditions
	public  WebDriver launchBrowser(String browserType, String url) {
		WebDriver driver=null;
		if(browserType.equals("CHROME")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserType.equals("FIREFOX"))  {
			System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
			Proxy proxy = new Proxy();
		    proxy.setProxyType(Proxy.ProxyType.AUTODETECT);
		    FirefoxOptions options = new FirefoxOptions();
		    options.setProxy(proxy);
			driver = new FirefoxDriver(options);
		}else if(browserType.equals("ANDROID_APP")) {
			try {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				// set the capability to execute test in chrome browser
				//capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
				// set the capability to execute our test in Android Platform
				// we need to define platform name
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
				// Set the device name as well (you can give any name)
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"MyPhone");
				//Automation MAe for appium
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1");
				//Device ID
				capabilities.setCapability(MobileCapabilityType.UDID,"0837fef4252c056e");
				//ANDROID Version
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0.1");
				
				capabilities.setCapability("newCommandTimeout",90000);
				
				capabilities.setCapability("autoGrantPermissions",true);
				capabilities.setCapability("noReset",false);

				capabilities.setCapability("appPackage","com.flipkart.android");
				capabilities.setCapability("appActivity","com.flipkart.android.activity.HomeFragmentHolderActivity");
				capabilities.setCapability("appWaitPackage","com.flipkart.android");
				capabilities.setCapability("appWaitActivity","com.flipkart.android.activity.HomeFragmentHolderActivity");
				 
				
				// Skip the installation of io.appium.settings app and the UIAutomator 2 server.
				capabilities.setCapability("skipDeviceInitialization", true);
				capabilities.setCapability("skipServerInstallation", true);
				
				// Create object of URL class and specify the appium server address
				URL appium_url = new URL("http://127.0.0.1:4723/wd/hub");
				// Create object of  AndroidDriver class and pass the url and capability that we created
				driver = new AppiumDriver(appium_url, capabilities);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
				return driver;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Please provide correct Driver browserType to launch");
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		return driver;
	}
    
	//Read data from Excel
	public String readExcel(String TestName) throws IOException{
		String output=null;
	    //Create an object of File class to open xlsx file
		String filePath = "TestData.xlsx";
	    File file =    new File(filePath);
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook workbook = null;
	    //Check condition if the file is xlsx file
	    if(filePath.contains(".xlsx")){
	    	workbook = new XSSFWorkbook(inputStream);
	    }
	    else if(filePath.equals(".xls")){
	    	workbook = new HSSFWorkbook(inputStream);
	    }
	    //Read sheet inside the workbook by its name
	    Sheet sheet = workbook.getSheet("Sheet1");
	    //Find number of rows in excel file
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    //Create a loop over all the rows of excel file to read it
	    for (int i = 0; i < rowCount+1; i++) {
	        Row row = sheet.getRow(i);
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	        	if (row.getCell(j).getStringCellValue().equalsIgnoreCase(TestName)) {
	        		output = row.getCell(j+1).getStringCellValue();
	        		break;
	        	}
	            //System.out.print(row.getCell(j).getStringCellValue()+"|| ");
	        }
	        
	    } 
	    return output;
	 }  
	
	//Take screenshot
	public static String takeScreenShot(WebDriver webdriver) throws IOException {
	
		File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("Report/Screenshots/" + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getPath();
		FileUtils.copyFile(scrFile, Dest);
		errflpath = errflpath.replace("Report\\", "");
		return errflpath;
	}
	
	   
}
>>>>>>> 2cff7cd9a4f01b5a9748fa6f542471223da31e67
