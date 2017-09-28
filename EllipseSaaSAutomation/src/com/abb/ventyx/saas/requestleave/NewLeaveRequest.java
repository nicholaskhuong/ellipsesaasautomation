package com.abb.ventyx.saas.requestleave;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class NewLeaveRequest extends BaseTestCase {
	
	@Test(description = "Access to Leave Request Application")
	public void accessLeaveRequest() {
		loginToApplication(ApplicationName.LEAVE_REQUEST);
		ScreenAction.waitObjVisible(driver, By.id("leaveBalanceDays"));
		//Assert.assertEquals(driver.findElement(By.cssSelector("body > div:nth-child(1) > div > div.v-touchkit-navpanel.v-widget.v-has-width.v-has-height > div > div > div > div.v-touchkit-navbar.v-widget > div.v-touchkit-navbar-caption > span")).getText(), "Leave Balance");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(), "Leave Balance");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='fgLeaveBalanceList']/div/div/div")).getText(), "Forecast Date");
	}
	
	@Test(groups="LeaveBalanceSummary",description = "Assert Days and Hours for Leave Balance Summay screen", dependsOnMethods="accessLeaveRequest")
	public void assertAnnualVacationLeaveOnLeaveBalancePage() {
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='gwt-uid-3']/div")).getText(), "Days");
		Assert.assertEquals(leaveBalanceDays.get(0).getAttribute("value"),"0.0148");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='gwt-uid-5']/div")).getText(), "Hours");
		Assert.assertEquals(leaveBalanceHours.get(0).getAttribute("value"),"0.1180");
	}
	
	@Test(groups="LeaveBalanceSummary",description = "Assert Days and Hours for Long Service Leave", dependsOnMethods="accessLeaveRequest")
	public void assertLongServiceLeaveOnLeaveBalancePage() {
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='gwt-uid-7']/div")).getText(), "Days");
		Assert.assertEquals(leaveBalanceDays.get(1).getAttribute("value"),"0.2813");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='gwt-uid-9']/div")).getText(), "Hours");
		Assert.assertEquals(leaveBalanceHours.get(1).getAttribute("value"),"2.2500");
	}
	
	@Test(groups="LeaveBalanceSummary", description = "Assert Days and Hours for Sick Leave", dependsOnMethods="accessLeaveRequest")
	public void assertSickLeaveOnLeaveBalancePage() {
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='gwt-uid-11']/div")).getText(), "Days");
		Assert.assertEquals(leaveBalanceDays.get(2).getAttribute("value"),"0.0147");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='gwt-uid-13']/div")).getText(), "Hours");
		Assert.assertEquals( leaveBalanceHours.get(2).getAttribute("value"),"0.1175");
	}
	
	@Test(dependsOnMethods="assertSickLeaveOnLeaveBalancePage")
	public void addNewLeaveRequest() {
		driver.findElement(By.cssSelector("#newLeaveRequest3 > span:nth-child(1) > span:nth-child(1)")).click();
		
		Select leaveType = new Select(driver.findElement(By.cssSelector("#leaveTypeCode > select")));
		leaveType.selectByVisibleText("S Sick Leave");
		Select leaveCode = new Select(driver.findElement(By.cssSelector("#bookedLeaveCode > select")));
		leaveCode.selectByVisibleText("S1SK Sick Leave Request Code");

	//    JavascriptExecutor jse = (JavascriptExecutor) driver;
	 //   WebElement startDay = driver.findElement(By.cssSelector("#leaveStartDate > input[type='date']"));
	   
	//    startDay.sendKeys("09232017");
	    
//	    while ( ScreenAction.isElementPresent(driver, By.cssSelector("#saas-3522304-overlays > div:nth-child(4) > div > div")))
//	    	{
//	    		 driver.findElement(By.cssSelector("#saas-3522304-overlays > div:nth-child(4) > div > div")).click() ;
//	    	}
//	    jse.executeScript("arguments[0].setAttribute('value', '9/12/2017')",startDay);
//	    WebElement endDay = driver.findElement(By.cssSelector("#leaveEndDate > input[type='date']"));
//	    jse.executeScript("arguments[0].setAttribute('value', '9/12/2017')",endDay);
//	    endDay.sendKeys("09232017");
//	    while ( ScreenAction.isElementPresent(driver, By.cssSelector("#saas-3522304-overlays > div:nth-child(4) > div > div")))
//    	{
//    		 driver.findElement(By.cssSelector("#saas-3522304-overlays > div:nth-child(4) > div > div")).click() ;
//    	}

	    WebElement TimeleaveStartDate = driver.findElement(By.cssSelector("#leaveStartDate > input[type='date']"));
	    TimeleaveStartDate.sendKeys("23092017");
	   
	    int maxLoop = 0;
	    while (ScreenAction.isElementPresent(driver, 
	    		By.cssSelector("#saas-3522304-overlays > div.v-Notification.error.v-Notification-error > div > div > h1"))) {
	     maxLoop += 1;
	     if (maxLoop < 10) {
	      Actions action = new Actions(driver);
	      action.sendKeys(Keys.ESCAPE).build().perform();
	     } else {
	      break;
	     }
	    }
	    WebElement TimeleaveEndDate = driver.findElement(By.cssSelector("#leaveEndDate > input[type='date']"));
	    TimeleaveEndDate.sendKeys("23092017");
	    int maxLoop1 = 0;
	    while (ScreenAction.isElementPresent(driver, 
	    		By.cssSelector("#saas-3522304-overlays > div.v-Notification.error.v-Notification-error > div > div > h1"))) {
	     maxLoop1 += 1;
	     if (maxLoop1 < 10) {
	      Actions action = new Actions(driver);
	      action.sendKeys(Keys.ESCAPE).build().perform();
	     } else {
	      break;
	     }
	    }
//	    List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
//	    Assert.assertEquals("-0.4932", leaveBalanceDays.get(0).getAttribute("value"));
//	    Assert.assertEquals("9.6630", leaveBalanceDays.get(1).getAttribute("value"));
//	    Assert.assertEquals("2.9322", leaveBalanceDays.get(2).getAttribute("value"));
//	    
	    
	    driver.findElement(By.cssSelector("#createLeaveRequest3 > span:nth-child(1) > span:nth-child(1)")).click();
	
	}
	
}
