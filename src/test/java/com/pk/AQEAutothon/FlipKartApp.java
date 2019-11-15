package com.pk.AQEAutothon;

import com.pk.AQEAutothon.App;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlipKartApp {
	private WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	App a = new App();
	@BeforeClass
	public void beforeClass() {
		report = new ExtentReports("Report/FlipKartAppTestReport.html");
		test = report.startTest("FlipKartAppTest");
		//Launch Url: https://www.meripustak.com/
		driver = a.launchBrowser("ANDROID_APP","com.flipkart.android");
	}	
	
	@Test
	void flipkartAppTest() throws IOException, InterruptedException {
		String productToSearch="Apple iPhone XR (Black, 64GB)";
		Thread.sleep(5000);
		test.log(LogStatus.PASS,test.addScreenCapture(App.takeScreenShot(driver))+ "Flipkart App is launched");
		
		WebDriverWait wait=new WebDriverWait(driver, 50);
		try {
			WebElement backbutton = driver.findElement(By.xpath("//*[@resource-id='com.flipkart.android:id/custom_back_icon']"));
			if(backbutton != null) backbutton.click();
		}catch(Exception e) {
			test.log(LogStatus.FAIL,test.addScreenCapture(App.takeScreenShot(driver))+ "Flipkart Home Page not launched");
			return;
		}
		//Click on Search
		try {
			WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id='com.flipkart.android:id/search_widget_textbox']")));
			test.log(LogStatus.PASS,test.addScreenCapture(App.takeScreenShot(driver))+ "Flipkart Home Page");
			search.click();
		}catch(Exception e) {
			test.log(LogStatus.FAIL,test.addScreenCapture(App.takeScreenShot(driver))+ "Flipkart Home Page not launched");
			return;
		}
		//Search Product
		try {
			WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id='com.flipkart.android:id/search_autoCompleteTextView']")));
			searchBox.sendKeys(productToSearch);
			test.log(LogStatus.PASS,test.addScreenCapture(App.takeScreenShot(driver))+ "Enter Product Name : "+ productToSearch);
		}catch(Exception e) {
			test.log(LogStatus.FAIL,test.addScreenCapture(App.takeScreenShot(driver))+ "Failed to Enter Product Name : "+ productToSearch);
			return;
		}
		//Tap to search
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id='com.flipkart.android:id/txt_title']"))).click();
		}catch(Exception e) {
			test.log(LogStatus.FAIL,test.addScreenCapture(App.takeScreenShot(driver))+ "Failed to click Product Title");
			return;
		}
		//Click on Searched Product 
		try {
			WebElement searchedProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Apple iPhone XR (Black, 64 GB)']")));
			test.log(LogStatus.PASS,test.addScreenCapture(App.takeScreenShot(driver))+ productToSearch + " is searched");
			searchedProduct.click();
		}catch(Exception e) {
			test.log(LogStatus.FAIL,test.addScreenCapture(App.takeScreenShot(driver))+ "Failed to Search Product");
			return;
		}
		//Add to Cart
		try {
			WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='ADD TO CART']")));
			test.log(LogStatus.PASS,test.addScreenCapture(App.takeScreenShot(driver))+productToSearch + " details are here");
			addToCart.click();
			
			Thread.sleep(5000);
			//SKIP & GO TO CART
			WebElement SkipGoToCart = driver.findElement(By.xpath("*//android.widget.ImageButton[@resource-id='' and @text='']"));
			if(SkipGoToCart != null) SkipGoToCart.click();
			
			WebElement GoToCart = driver.findElement(By.xpath("//*[@text='GO TO CART']"));
			if(GoToCart != null) GoToCart.click();
		}catch(Exception e) {
			test.log(LogStatus.FAIL,test.addScreenCapture(App.takeScreenShot(driver))+ "Failed to Add into cart");
			return;
		}		
		//Place Order
		try {
			WebElement placeOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Place Order ']")));
			test.log(LogStatus.PASS,test.addScreenCapture(App.takeScreenShot(driver))+productToSearch + " is added into cart");
			//placeOrder.click();
		}catch(Exception e) {
			test.log(LogStatus.FAIL,test.addScreenCapture(App.takeScreenShot(driver))+ "Failed to Place Order");
			return;
		}
		//input Email Id Number
		/*
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.
		 * xpath("//*[@text='Use Email-ID']"))).click(); Thread.sleep(2000); WebElement
		 * emailId = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//*[@resource-id='com.flipkart.android:id/phone_input']")));
		 * emailId.sendKeys("kupravin@amdocs.com");
		 * //Reporter.log("Email ID entered to Continue");
		 */		
		test.log(LogStatus.PASS,test.addScreenCapture(App.takeScreenShot(driver))+ "FlipkartAppTest Completed");
		
	}
	
	
	
	@AfterClass
	public void afterClass() {
		report.endTest(test);
		report.flush();
		driver.quit();
	}
}
