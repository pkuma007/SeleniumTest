package com.pk.AQEAutothon;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchEngineTest {
	private WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	WebDriverWait wait;
	App a = new App();

	@BeforeClass
	public void beforeClass() throws IOException {
		report = new ExtentReports("Report/TestReport.html");
		FileUtils.cleanDirectory(new File("Report/")); 
		test = report.startTest("SearchEngineTest");
		//Launch Url: https://www.google.com/
		driver = a.launchBrowser("CHROME","https://www.google.com/");
		wait = new WebDriverWait(driver, Duration.ofMinutes(50));
		
	}	
	
	@Test
	void googleSearchEngineTest() throws IOException {
		try {
			
		String searchStr="Virat Kohli Wiki";
		
		WebElement searchGoogle= wait.until(ExpectedConditions.visibilityOfElementLocated(GoogleHome.searchGoogle));
		test.log(LogStatus.PASS,test.addScreenCapture(App.takeScreenShot(driver))+ "Google Home is launched");
		searchGoogle.sendKeys(searchStr);
		Thread.sleep(5000);
		searchGoogle.sendKeys(Keys.ESCAPE);
		
		WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(GoogleHome.searcBtn));
		search.click();
		//check Search or not
		WebElement viartWikiLink = wait.until(ExpectedConditions.visibilityOfElementLocated(GoogleHome.viartWikiLink));
		test.log(LogStatus.PASS,test.addScreenCapture(App.takeScreenShot(driver))+ searchStr +" searched successfully");
		viartWikiLink.click();
		
		//Wiki Page
		wait.until(ExpectedConditions.visibilityOfElementLocated(VKWikiPage.Competion));
		test.log(LogStatus.PASS,test.addScreenCapture(App.takeScreenShot(driver))+ " Virat Kohli Wiki Page opened successfully");
		
		//Fetch Statics	
		//List<WebElement> irows = driver.findElements(By.xpath("//th[text()='Career statistics']/../following::table[1]/tbody/tr"));     
		//List<WebElement> icols = driver.findElements(By.xpath("//th[text()='Career statistics']/../following::table[1]/tbody/tr/th"));     
		
		for (int i=1;i<=4;i++)      
		{   
			String tr = "";
			for (int j=1; j<=4;j++)                    
			{  
				WebElement val;
				if(i==1 || j ==1) {
					val= driver.findElement(By.xpath("//th[text()='Career statistics']/../following::table[1]/tbody/tr["+i+"]/th["+j+"]"));
				}else {
					val= driver.findElement(By.xpath("//th[text()='Career statistics']/../following::table[1]/tbody/tr["+i+"]/td["+j+"]"));
				}
				
				System.out.println(val.getText());
				tr = tr + " | " + val.getText();
			}
			test.log(LogStatus.PASS, tr);
		}
		
		//Image
		
		test.log(LogStatus.PASS,test.addScreenCapture(App.takeScreenShot(driver))+ "Search Engine Test Completed");
		}catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL,test.addScreenCapture(App.takeScreenShot(driver))+ "Search Engine Test Failed");
			
		}
	}
	
	
	
	@AfterClass
	public void afterClass() {
		report.endTest(test);
		report.flush();
		driver.quit();
	}
}
