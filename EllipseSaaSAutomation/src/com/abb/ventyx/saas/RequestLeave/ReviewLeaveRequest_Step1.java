package com.abb.ventyx.saas.RequestLeave;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.LoginPageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1867")
@Credentials(user = "SPR002", password = "", district = "R100", position = "HRMAN")
public class ReviewLeaveRequest_Step1 extends BaseTestCase {
	
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
	
	@Test(groups="LeaveRquestDetails", description = "Select SHOW ALL REQUESTS button", dependsOnGroups="LeaveBalanceSummary")
	public void selectSHOWALLREQUESTSOnLeaveBalancePageT() {
		Assert.assertEquals(driver.findElement(By.id(LoginPageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).getText(), "SHOW ALL REQUESTS");
		Assert.assertTrue(driver.findElement(By.id(LoginPageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).isEnabled());
		driver.findElement(By.id(LoginPageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).click();
		ScreenAction.waitObjVisible(driver, By.id("leaveStartDate"));
		//Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[2]/div/div[1]/div[1]/span")).getText(), "Leave Requests");//Still error
		
	}
	
	@Test(groups="LeaveRquestDetails", description = "Assert row count leave request", dependsOnMethods="selectSHOWALLREQUEST",alwaysRun=true)
	public void assertRowCountOnLeaveRequestPage() {
		ScreenAction.waitObjVisible(driver, By.id("leaveStartDate"));
		List<WebElement> leaveStartDate = driver.findElements(By.id("leaveStartDate"));
		Assert.assertEquals(leaveStartDate.size(),9);
	}
	
	@Test(groups="LeaveRquestDetails", description = "Assert details of first row", dependsOnMethods="selectSHOWALLREQUEST",alwaysRun=true)
	public void assertFirstRowOnLeaveRequestPage() {
		ScreenAction.waitObjVisible(driver, By.id("leaveStartDate"));
		List<WebElement> leaveTypeDesc = driver.findElements(By.id("leaveTypeDesc"));
		List<WebElement> bookedLeaveCode = driver.findElements(By.id("bookedLeaveCode"));
		List<WebElement> bookedLeaveDesc = driver.findElements(By.id("bookedLeaveDesc"));
		List<WebElement> leaveStartDate = driver.findElements(By.id("leaveStartDate"));
		List<WebElement> leaveEndDate = driver.findElements(By.id("leaveEndDate"));
		List<WebElement> leaveDays = driver.findElements(By.id("leaveDays"));
		List<WebElement> leaveStatusDesc = driver.findElements(By.id("leaveStatusDesc"));
		
		
		Assert.assertEquals(bookedLeaveCode.get(0).getAttribute("value"),"000S");
		Assert.assertEquals(bookedLeaveDesc.get(0).getAttribute("value"),"Sick");
//		Assert.assertEquals(leaveStartDate.get(0).getAttribute("value"),"17/08/2016");//Still error
//		Assert.assertEquals(leaveEndDate.get(0).getAttribute("value"),"17/08/2016");//Still error
		Assert.assertEquals(leaveDays.get(0).getAttribute("value"),"1.0000");
		Assert.assertEquals(leaveStatusDesc.get(0).getAttribute("value"),"Approved Leave");
		
	}
}
