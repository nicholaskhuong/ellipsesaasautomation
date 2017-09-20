package com.abb.ventyx.saas;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pages.HomePage;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Constants;
import com.abb.ventyx.utilities.DriverCreator;

public class CreateLeaveRequest extends BaseTestCase {

	@Test(description = "User be able to sign in to SaaS system with a corrected email")
	public void CreateLeaveRequest() throws InterruptedException {
//		driver.get("/SupplierPortal/#!listSupplier");
	    driver.findElement(By.id("gwt-uid-7")).clear();
	    driver.findElement(By.id("gwt-uid-7")).sendKeys("1094");
	    driver.findElement(By.id("gwt-uid-9")).clear();
	    driver.findElement(By.id("gwt-uid-9")).sendKeys("Testuser1");
	   
	    driver.findElement(By.xpath("//span[@text='Login']")).click();
	    
	    
	    
//	    driver.findElement(By.id("gwt-uid-22")).clear();
//	    driver.findElement(By.id("gwt-uid-22")).sendKeys("Test 1");
//		WebDriver driver= super.driver;
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	    driver.findElement(By.id("email")).clear();
//	    driver.findElement(By.id("email")).sendKeys("u@example.com");
//	    driver.findElement(By.id("loginButton")).click();
//	    driver.findElement(By.id("vCode")).clear();
//	    driver.findElement(By.id("vCode")).sendKeys("u");
//	    driver.findElement(By.id("verifyButton")).click();
//	    driver.findElement(By.linkText("LEAVEREQUEST EL8ST (8.8)")).click();
////	    WebElement element = driver.findElement(By.id("LeaveRequest_EL8ST__8_8_"));
////	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
////	    Thread.sleep(500); 
////	    element.click();
//	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	    driver.findElement(By.id("username")).clear();
//	    driver.findElement(By.id("username")).sendKeys("ausaenc");
//	    Thread.sleep(500); 
//	    driver.findElement(By.id("scope")).clear();
//	    driver.findElement(By.id("scope")).sendKeys("r100");
//	    Thread.sleep(500); 
//	    driver.findElement(By.id("position")).clear();
//	    driver.findElement(By.id("position")).sendKeys("hrman");
//	    Thread.sleep(500); 
//	    driver.findElement(By.id("saveBtn")).click();
//	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
////	    driver.findElement(By.linkText("New Leave Request")).click();
//	    driver.findElement(By.cssSelector("#searchLeaveRequest > span.v-button-wrap > span.v-button-caption")).click();
//	    WebElement newLeaveRequest = driver.findElement(By.id("New Leave Request"));
//	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newLeaveRequest);
//	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
////	    newLeaveRequest.click();
//	    new Select(driver.findElement(By.cssSelector("select.v-select-select"))).selectByVisibleText("S Sick Leave");
//	    new Select(driver.findElement(By.xpath("//div[@id='bookedLeaveCode']/select"))).selectByVisibleText("S Sick Leave");
//	    driver.findElement(By.id("modifyAction")).click();
	}
}
