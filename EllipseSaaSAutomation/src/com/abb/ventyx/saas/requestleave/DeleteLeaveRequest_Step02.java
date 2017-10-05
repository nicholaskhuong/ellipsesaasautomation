package com.abb.ventyx.saas.requestleave;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveBalancePageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveRequestPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveRequestsPageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1866")
@Credentials(user = "SPR002", password = "", district = "R100", position = "SAASPOS_01")
public class DeleteLeaveRequest_Step02 extends BaseTestCase {
	
	protected int rowCount;
	
	@Test(description = "Access to Leave Request Application")
	public void accessRequestLeave() {
		loginToApplication(ApplicationName.LEAVE_REQUEST);
		
		screenAction.waitObjVisible(driver, By.id("leaveBalanceDays"),2);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_TITLE_ID)).getText(), "Leave Balance");
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.FORECAST_DATE_LABEL_ID)).getText(), "Forecast Date");
	}

	//Step2: Delete Leave Request with Status = UNCO 
	@Test(description = "Assert Days and Hours for Leave Balance Summay screen", dependsOnMethods="accessRequestLeave")
	public void assertAnnualVacationLeaveOnLeaveBalancePage() {
		List<WebElement> leaveBalanceDays = driver.findElements(By.id(LeaveBalancePageDefinition.LEAVE_BALANCE_DAYS_TEXT_ID));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id(LeaveBalancePageDefinition.LEAVE_BALANCE_HOURS_TEXT_ID));
		
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_DAYS_LABEL_ID)).getText(), "Days");
		Assert.assertEquals(leaveBalanceDays.get(0).getAttribute("value"),"0.0148");
		
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_HOURS_LABEL_ID)).getText(), "Hours");
		Assert.assertEquals(leaveBalanceHours.get(0).getAttribute("value"),"0.1180");
	}
	
	@Test(description = "Assert Days and Hours for Long Service Leave", dependsOnMethods="assertAnnualVacationLeaveOnLeaveBalancePage", alwaysRun=true)
	public void assertLongServiceLeaveOnLeaveBalancePage() {
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));
		
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_DAYS_LABEL_ID)).getText(), "Days");
		Assert.assertEquals(leaveBalanceDays.get(1).getAttribute("value"),"0.2813");
		
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_HOURS_LABEL_ID)).getText(), "Hours");
		Assert.assertEquals(leaveBalanceHours.get(1).getAttribute("value"),"2.2500");
	}
	
	@Test( description = "Assert Days and Hours for Sick Leave", dependsOnMethods="assertLongServiceLeaveOnLeaveBalancePage",alwaysRun=true)
	public void assertSickLeaveOnLeaveBalancePage() {
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id("leaveBalanceHours"));

		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_DAYS_LABEL_ID)).getText(), "Days");
		Assert.assertEquals(leaveBalanceDays.get(2).getAttribute("value"),"0.0147");

		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_HOURS_LABEL_ID)).getText(), "Hours");
		Assert.assertEquals( leaveBalanceHours.get(2).getAttribute("value"),"0.1175");
	}
	
	//Before delete
	@Test( description = "Assert no error message returned", dependsOnMethods="assertSickLeaveOnLeaveBalancePage", alwaysRun=true)
	public void selectShowAllRequests(){
		Assert.assertEquals(driver.findElement(By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).getText(), "SHOW ALL REQUESTS");
		Assert.assertTrue(driver.findElement(By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).isEnabled());
		driver.findElement(By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).click();
		
		screenAction.waitObjVisible(driver, By.xpath(LeaveRequestsPageDefinition.LEAVE_REQUEST_TITLE_ID), 3);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestsPageDefinition.LEAVE_REQUEST_TITLE_ID)).getText(), "Leave Requests");
	}
	
	@Test(description = "Assert row count leave request", dependsOnMethods="selectShowAllRequests",alwaysRun=true)
	public void getRowCountBeforeDeleting() {
		ScreenAction.waitObjVisible(driver, By.id("leaveStartDate"));
		List<WebElement> leaveStartDate = driver.findElements(By.id("leaveStartDate"));
		rowCount=leaveStartDate.size();
		Assert.assertEquals(leaveStartDate.size(),rowCount);
	}
	
	@Test(description = "Select PROC leave transaction", dependsOnMethods="getRowCountBeforeDeleting",alwaysRun=true)
	public void selectPROCLeaveTransaction() {
		List<WebElement> leaveStartDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.START_DATE_TEXT_ID));
		
		int index=-1;
		int totalRows=leaveStartDate.size();
		for (int i = 0; i <= totalRows; i++) {
			
			if("2016-06-30".equals(leaveStartDate.get(i).getAttribute("value"))){
				index=i;	
				break;
			}
		}
		Assert.assertTrue(index>-1, "Records not found");
		screenAction.clickBtn(By.xpath("//*[@id='leaveRequestList']/div/div/div/div/div/div["+(index+1)+"]/div/div/div/div/div[2]"));
		
		screenAction.waitObjVisible(driver, By.id(LeaveRequestPageDefinition.DELETE_BUTTON_ID), 10);
		screenAction.assertButtonEnabled(By.id(LeaveRequestPageDefinition.DELETE_BUTTON_ID), true);
		screenAction.waitObjVisible(driver, By.xpath(LeaveRequestPageDefinition.LEAVE_REQUEST_TITLE_ID), 60);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestPageDefinition.LEAVE_REQUEST_TITLE_ID)).getText(), "Leave Request");
		
	}
	
	@Test(description = "Click Delete", dependsOnMethods="selectPROCLeaveTransaction",alwaysRun=true)
	public void clickButtonDelete(){
		Assert.assertEquals(driver.findElement(By.id(LeaveRequestPageDefinition.DELETE_BUTTON_ID)).getText(), "DELETE");
		screenAction.assertButtonEnabled(By.id(LeaveRequestPageDefinition.DELETE_BUTTON_ID), true);
		
		screenAction.clickBtn(By.id(LeaveRequestPageDefinition.DELETE_BUTTON_ID));
	}
	
	@Test( description = "Assert no error message returned", dependsOnMethods="clickButtonDelete", alwaysRun=true)
	public void assertDeletedWithoutErrorMessage(){
		List<WebElement> lismg=driver.findElements(By.cssSelector(LeaveBalancePageDefinition.MESSAGE_TEXT_ID));
		
		String errorMessage="Application has no error message";
		
		if(lismg.size()>0){
			errorMessage=lismg.get(0).getText();
		}

		Assert.assertTrue(lismg.size()==0, errorMessage);
	}
	
	@Test(description = "Assert details of first row", dependsOnMethods="assertDeletedWithoutErrorMessage",alwaysRun=true)
	public void assertEntryDeleted() {
		List<WebElement> leaveStartDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.START_DATE_TEXT_ID));
		List<WebElement> leaveStatusDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.STATUS_DESC_TEXT_ID));
		
		boolean found=false;
		int totalRow=leaveStartDate.size();
		for (int i = 0; i <totalRow ; i++) {
			
			if(("2016-06-30".equals(leaveStartDate.get(i).getAttribute("value"))) 
			   &&("UNCO".equals(leaveStatusDesc.get(i).getAttribute("value"))) ){
				found=true;
				break;
			}
		}
		
		Assert.assertFalse(found);
	}
}
