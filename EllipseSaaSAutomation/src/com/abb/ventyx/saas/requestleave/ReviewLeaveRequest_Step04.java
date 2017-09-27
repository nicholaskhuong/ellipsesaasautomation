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
public class ReviewLeaveRequest_Step04 extends BaseTestCase {
	
	@Test(description = "Access to Leave Request Application")
	public void accessRequestLeave() {
		loginToApplication(ApplicationName.LEAVE_REQUEST);
		ScreenAction.waitObjVisible(driver, By.id("leaveBalanceDays"));
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(), "Leave Balance");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='fgLeaveBalanceList']/div/div/div")).getText(), "Forecast Date");
	}
	
	@Test(description = "input Forecast Date on next pay period ", dependsOnMethods="accessRequestLeave")
	public void inputForecastDate() {
		//Review forecast date on next 4 years 26/06/2020
		
		WebElement forecastDate = driver.findElement(By.cssSelector(LeaveBalancePageDefinition.FORECAST_DATE_TEXT_ID));
		forecastDate.sendKeys("06262020");
		
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
		  Assert.assertEquals(forecastDate.getAttribute("value"), "2020-06-26");
	}
	
	@Test(description = "Assert Days and Hours for Annual Leave after input forecast date", dependsOnMethods="inputForecastDate")
	public void assertAnnualVacationLeaveAfterInputForecastDate() {
		
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		
		Assert.assertEquals(leaveBalanceDays.get(0).getAttribute("value"),"0.5690");
		Assert.assertEquals(leaveBalanceHours.get(0).getAttribute("value"),"4.5520");
	}
	
	@Test(description = "Assert Days and Hours for Annual Leave after input forecast date", dependsOnMethods="assertAnnualVacationLeaveAfterInputForecastDate", alwaysRun=true)
	public void assertLongServiceLeavefterInputForecastDate() {
		
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		Assert.assertEquals(leaveBalanceDays.get(1).getAttribute("value"),"29.9130");
		Assert.assertEquals(leaveBalanceHours.get(1).getAttribute("value"),"239.3036");
	}
	
	@Test(description = "Assert Days and Hours for Annual Leave after input forecast date", dependsOnMethods="assertLongServiceLeavefterInputForecastDate",alwaysRun=true)
	public void assertSickLeaveAfterInputForecastDate() {
		
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		
		Assert.assertEquals(leaveBalanceDays.get(2).getAttribute("value"),"18.7510");
		Assert.assertEquals(leaveBalanceHours.get(2).getAttribute("value"),"150.0080");
	}
}
