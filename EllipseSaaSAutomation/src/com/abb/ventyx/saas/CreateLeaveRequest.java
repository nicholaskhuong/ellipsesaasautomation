package com.abb.ventyx.saas;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.abb.ventyx.utilities.BaseTestCase;

public class CreateLeaveRequest extends BaseTestCase {

	@Test(description = "User be able to sign in to SaaS system with a corrected email")
	public void CreateLeaveRequest() throws InterruptedException {

		driver.findElement(By.cssSelector("#searchLeaveRequest > span.v-button-wrap > span.v-button-caption")).click();
		WebElement newLeaveRequest = driver.findElement(By.id("New Leave Request"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newLeaveRequest);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// newLeaveRequest.click();
		new Select(driver.findElement(By.cssSelector("select.v-select-select"))).selectByVisibleText("S Sick Leave");
		new Select(driver.findElement(By.xpath("//div[@id='bookedLeaveCode']/select"))).selectByVisibleText("S Sick Leave");
		driver.findElement(By.id("modifyAction")).click();
	}
}
