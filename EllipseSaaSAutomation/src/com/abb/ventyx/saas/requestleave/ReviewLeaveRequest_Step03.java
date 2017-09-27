package com.abb.ventyx.saas.requestleave;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveBalancePageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1867")
@Credentials(user = "SPR002", password = "", district = "R100", position = "HRMAN")
public class ReviewLeaveRequest_Step03 extends BaseTestCase {
	
	@Test(description = "Access to Leave Request Application")
	public void accessRequestLeave() {
		loginToApplication(ApplicationName.LEAVE_REQUEST);
		ScreenAction.waitObjVisible(driver, By.id("leaveBalanceDays"));
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(), "Leave Balance");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='fgLeaveBalanceList']/div/div/div")).getText(), "Forecast Date");
	}
	@Test(description = "Assert Days and Hours for Annual Leave", dependsOnMethods="accessRequestLeave")
	public void assertAnnualVacationLeaveOnLeaveBalancePage() {
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='gwt-uid-3']/div")).getText(), "Days");
		Assert.assertEquals(leaveBalanceDays.get(0).getAttribute("value"),"0.0148");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='gwt-uid-5']/div")).getText(), "Hours");
		Assert.assertEquals(leaveBalanceHours.get(0).getAttribute("value"),"0.1180");
	}
	
	@Test(description = "Assert Days and Hours for Long Service Leave", dependsOnMethods="assertAnnualVacationLeaveOnLeaveBalancePage", alwaysRun=true)
	public void assertLongServiceLeaveOnLeaveBalancePage() {
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='gwt-uid-7']/div")).getText(), "Days");
		Assert.assertEquals(leaveBalanceDays.get(1).getAttribute("value"),"0.2813");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='gwt-uid-9']/div")).getText(), "Hours");
		Assert.assertEquals(leaveBalanceHours.get(1).getAttribute("value"),"2.2500");
	}
	
	@Test( description = "Assert Days and Hours for Sick Leave", dependsOnMethods="assertLongServiceLeaveOnLeaveBalancePage",alwaysRun=true)
	public void assertSickLeaveOnLeaveBalancePage() {
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='gwt-uid-11']/div")).getText(), "Days");
		Assert.assertEquals(leaveBalanceDays.get(2).getAttribute("value"),"0.0147");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='gwt-uid-13']/div")).getText(), "Hours");
		Assert.assertEquals( leaveBalanceHours.get(2).getAttribute("value"),"0.1175");
	}
	
	@Test(description = "input Forecast Date on next pay period ", dependsOnMethods="assertSickLeaveOnLeaveBalancePage",alwaysRun=true)
	public void inputForecastDate() {
		//Current Pay End Date: 26/06/2016
		
		WebElement forecastDate = driver.findElement(By.cssSelector(LeaveBalancePageDefinition.FORECAST_DATE_TEXT_ID));
		forecastDate.sendKeys("06262016");
		
		int maxLoop = 0;
		  
		boolean existError=ScreenAction.isElementPresent(driver,By.cssSelector("#saas-3522304-overlays > div.v-Notification.error.v-Notification-error > div > div > h1"));
		while (existError) {
		  
			  maxLoop += 1;
		 
			  if (maxLoop < 10) {
			    Actions action = new Actions(driver);
			    action.sendKeys(Keys.ESCAPE).build().perform();
			   } 
			  else {
			    break;
			   }
		  }
		  existError=ScreenAction.isElementPresent(driver,By.cssSelector("#saas-3522304-overlays > div.v-Notification.error.v-Notification-error > div > div > h1"));
		  Assert.assertTrue(existError==false, " No error message returned");
		  Assert.assertEquals(forecastDate.getAttribute("value"), "2016-06-26");
	}
	
	@Test(description = "Assert Days and Hours for Annual Leave after input forecast date", dependsOnMethods="inputForecastDate")
	public void assertAnnualVacationLeaveAfterInputForecastDate() {
		
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		
		Assert.assertEquals(leaveBalanceDays.get(0).getAttribute("value"),"-0.9705");
		Assert.assertEquals(leaveBalanceHours.get(0).getAttribute("value"),"-7.7640");
	}
	
	@Test(description = "Assert Days and Hours for Annual Leave after input forecast date", dependsOnMethods="assertAnnualVacationLeaveAfterInputForecastDate", alwaysRun=true)
	public void assertLongServiceLeavefterInputForecastDate() {
		
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		Assert.assertEquals(leaveBalanceDays.get(1).getAttribute("value"),"0.5625");
		Assert.assertEquals(leaveBalanceHours.get(1).getAttribute("value"),"4.5000");
	}
	
	@Test(description = "Assert Days and Hours for Annual Leave after input forecast date", dependsOnMethods="assertLongServiceLeavefterInputForecastDate",alwaysRun=true)
	public void assertSickLeaveAfterInputForecastDate() {
		
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		
		Assert.assertEquals(leaveBalanceDays.get(2).getAttribute("value"),"0.3526");
		Assert.assertEquals(leaveBalanceHours.get(2).getAttribute("value"),"2.8208");
	}
}
