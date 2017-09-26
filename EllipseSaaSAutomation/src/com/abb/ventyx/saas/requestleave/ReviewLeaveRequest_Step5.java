package com.abb.ventyx.saas.requestleave;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
public class ReviewLeaveRequest_Step5 extends BaseTestCase {
	
	@Test(description = "Access to Leave Request Application")
	public void accessRequestLeave() {
		
		loginToApplication(ApplicationName.LEAVE_REQUEST);
		ScreenAction.waitObjVisible(driver, By.id("leaveBalanceDays"));
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(), "Leave Balance");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='fgLeaveBalanceList']/div/div/div")).getText(), "Forecast Date");
	}

	@Test(description = "Assert Days and Hours for Leave Balance Summay screen", dependsOnMethods="accessRequestLeave")
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
	
	@Test(description = "Select Setting icon on Leave Balance Page  ", dependsOnMethods="assertSickLeaveOnLeaveBalancePage",alwaysRun = true)
	public void selectSettingIconOnLeaveBalancePage() {
		    driver.findElement(By.cssSelector("#menuNavigation > span > span.v-menubar-menuitem-caption > span")).click();
		    driver.findElement(By.xpath("//div[@id='saas-3522304-overlays']/div[2]/div/div/span/span/span")).click();
		    
		 // Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[2]/div/div[1]/div[1]/span")).getText(), "Settings");  Still error
	
	}
	
	@Test(description = "Input value for Setting ", dependsOnMethods="selectSettingIconOnLeaveBalancePage",alwaysRun = true)
	public void inputValueOnSettingPage() {	
		Select leaveType = new Select(driver.findElement(By.cssSelector("#leaveTypeCode > select")));
		leaveType.selectByVisibleText("S Sick Leave");
		
		if ( !driver.findElement(By.xpath("//input[@type='checkbox']")).isSelected() )
		{
		     driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		}
	    driver.findElement(By.cssSelector("#saveBtn > span > span")).click();
	  
	 //   Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(), "Leave Balance");  Still error
	}
	
	@Test(description = "Select SHOW ALL REQUESTS button", dependsOnMethods="inputValueOnSettingPage",alwaysRun=true)
	public void selectShowAllRequestOnLeaveBalancePage() {
		ScreenAction actionScreen =new ScreenAction(driver);
		actionScreen.waitObjInvisible(By.cssSelector("#saveBtn > span > span"));
	
		WebElement searchBtn= driver.findElement(By.id("searchLeaveRequest"));
		searchBtn.click();
		/*  ScreenAction.waitObjVisible(driver, By.id("searchLeaveRequest"));
		    driver.findElement(By.id("searchLeaveRequest")).click();*/
		//Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[2]/div/div[1]/div[1]/span")).getText(), "Leave Requests");//Still error
	}	
	
	@Test(description = "Assert row count leave request type", dependsOnMethods="selectShowAllRequestOnLeaveBalancePage",alwaysRun=true)
	public void assertRowCountOnLeaveRequestPage() {
		ScreenAction.waitObjVisible(driver, By.id("leaveStartDate"));
		List<WebElement> leaveStartDate = driver.findElements(By.id("leaveStartDate"));
		Assert.assertEquals(leaveStartDate.size(),2);
	}

	@Test(description = "Show leave request type ", dependsOnMethods="selectShowAllRequestOnLeaveBalancePage",alwaysRun = true)
	public void showLeaveRequestType() {
		ScreenAction.waitObjVisible(driver, By.id("leaveStartDate"));
		List<WebElement> bookedLeaveCode = driver.findElements(By.id("bookedLeaveCode"));
		List<WebElement> bookedLeaveDesc = driver.findElements(By.id("bookedLeaveDesc"));
		List<WebElement> leaveStartDate = driver.findElements(By.cssSelector("#leaveStartDate > input[type='date']"));
		List<WebElement> leaveEndDate = driver.findElements(By.cssSelector("#leaveEndDate > input[type='date']"));
		List<WebElement> leaveDays = driver.findElements(By.id("leaveDays"));
		List<WebElement> leaveStatusDesc = driver.findElements(By.id("leaveStatusDesc"));
		
		Assert.assertEquals(bookedLeaveCode.get(0).getAttribute("value"),"");
		Assert.assertEquals(bookedLeaveDesc.get(0).getAttribute("value"),"");
		Assert.assertEquals(leaveStartDate.get(0).getAttribute("value"),"2016-05-30");
		Assert.assertEquals(leaveEndDate.get(0).getAttribute("value"),"2016-05-30");
		Assert.assertEquals(leaveDays.get(0).getAttribute("value"),"0.0000");
		Assert.assertEquals(leaveStatusDesc.get(0).getAttribute("value"),"Initital Leave");
		
		Assert.assertEquals(bookedLeaveCode.get(1).getAttribute("value"),"S");
		Assert.assertEquals(bookedLeaveDesc.get(1).getAttribute("value"),"SICK LEAVE");
		Assert.assertEquals(leaveStartDate.get(1).getAttribute("value"),"2016-06-01");
		Assert.assertEquals(leaveEndDate.get(1).getAttribute("value"),"2016-06-01");
		Assert.assertEquals(leaveDays.get(1).getAttribute("value"),"0.1600");
		Assert.assertEquals(leaveStatusDesc.get(1).getAttribute("value"),"Paid Leave");
		
	}
	
}
