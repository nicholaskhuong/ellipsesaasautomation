package com.abb.ventyx.saas.approveleave;

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

@ALM(id = "1866")
@Credentials(user = "SPR003", password = "", district = "R100", position = "HRMAN")
public class DeleteLeaveRequest_DeleteApproveData_Step03 extends BaseTestCase {
	
	@Test(description = "Access to Leave Request Application")
	public void accessRequestLeave() {
		loginToApplication(ApplicationName.LEAVE_REQUEST);
		
		screenAction.waitObjVisible(driver, By.id("leaveBalanceDays"),2);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_TITLE_ID)).getText(), "Leave Balance");
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.FORECAST_DATE_LABEL_ID)).getText(), "Forecast Date");
	}

	//Delete Leave Request with Status = PROC (Current Pay Period). 
	
	@Test( description = "Select SHOW ALL REQUESTS", dependsOnMethods="accessRequestLeave", alwaysRun=true)
	public void selectButtonShowAllRequests(){
		Assert.assertEquals(driver.findElement(By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).getText(), "SHOW ALL REQUESTS");
		Assert.assertTrue(driver.findElement(By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).isEnabled());
		screenAction.clickBtn(By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID));
		
		screenAction.waitObjVisible(driver, By.xpath(LeaveRequestsPageDefinition.LEAVE_REQUEST_TITLE_ID), 3);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestsPageDefinition.LEAVE_REQUEST_TITLE_ID)).getText(), "Leave Requests");
	}
	
	@Test(description = "Select PROC leave transaction", dependsOnMethods="selectButtonShowAllRequests",alwaysRun=true)
	public void selectPROCLeaveTransaction() {
		List<WebElement> bookedLeaveCode = driver.findElements(By.id(LeaveRequestsPageDefinition.REQUEST_CODE_TEXT_ID));
		List<WebElement> bookedLeaveDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.REQUEST_CODE_DESCRIPTION_TEXT_ID));
		List<WebElement> leaveStartDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.START_DATE_TEXT_ID));
		List<WebElement> leaveDays = driver.findElements(By.id(LeaveRequestsPageDefinition.DAYS_TEXT_ID));
		List<WebElement> leaveStatusDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.STATUS_DESC_TEXT_ID));
		
		int index=-1;
		int totalRows=leaveStartDate.size();
		for (int i = 0; i <= totalRows; i++) {
			
			if("2016-06-02".equals(leaveStartDate.get(i).getAttribute("value"))){
				index=i;	
				break;
			}
		}
		
		Assert.assertTrue(index>-1, "Records not found");
		Assert.assertEquals(bookedLeaveCode.get(index).getAttribute("value"),"A");
		Assert.assertEquals(bookedLeaveDesc.get(index).getAttribute("value"),"ANNUAL LEAVE");
		Assert.assertEquals(leaveStartDate.get(index).getAttribute("value"),"2016-06-02");
		Assert.assertEquals(leaveDays.get(index).getAttribute("value"),"0.4000");
		Assert.assertEquals(leaveStatusDesc.get(index).getAttribute("value"),"Approved/Processed");
		
		screenAction.clickBtn(By.xpath("//*[@id='leaveRequestList']/div/div/div/div/div/div["+(index+1)+"]/div/div/div/div/div[2]"));
		
		screenAction.waitObjVisible(driver, By.id(LeaveRequestPageDefinition.DELETE_BUTTON_ID), 10);
		screenAction.assertButtonEnabled(By.id(LeaveRequestPageDefinition.DELETE_BUTTON_ID), true);
		screenAction.waitObjVisible(driver, By.xpath(LeaveRequestPageDefinition.LEAVE_REQUEST_TITLE_ID), 60);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestPageDefinition.LEAVE_REQUEST_TITLE_ID)).getText(), "Leave Request");
		
	}
	
	@Test(description = "Click Delete", dependsOnMethods="selectPROCLeaveTransaction",alwaysRun=true)
	public void clickButtonDelete(){
		Assert.assertEquals(driver.findElement(By.id(LeaveRequestPageDefinition.DELETE_BUTTON_ID)).getText(), "DELETE");
		Assert.assertTrue(driver.findElement(By.id(LeaveRequestPageDefinition.DELETE_BUTTON_ID)).isEnabled());
		
		screenAction.clickBtn(By.id(LeaveRequestPageDefinition.DELETE_BUTTON_ID));
	}
	
	@Test( description = "Assert no error message returned", dependsOnMethods="clickButtonDelete", alwaysRun=true)
	public void assertNoErrorMessage(){
		List<WebElement> lismg=driver.findElements(By.cssSelector(LeaveBalancePageDefinition.MESSAGE_TEXT_ID));
		
		String errorMessage="Application has no error message";
		
		if(lismg.size()>0){
			errorMessage=lismg.get(0).getText();
		}

		Assert.assertTrue(lismg.size()==0, errorMessage);
	}
	
	
	@Test(description = "Assert details of first row", dependsOnMethods="assertNoErrorMessage",alwaysRun=true)
	public void assertEntryDeleted() {
		List<WebElement> leaveStartDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.START_DATE_TEXT_ID));
		List<WebElement> leaveStatusDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.STATUS_DESC_TEXT_ID));
		
		boolean found=false;
		for (int i = 0; i < leaveStartDate.size(); i++) {
			
			if(("2016-06-02".equals(leaveStartDate.get(i).getAttribute("value"))) 
			   &&("PROC".equals(leaveStatusDesc.get(i).getAttribute("value"))) ){
				found=true;
				break;
			}
		}
		
		Assert.assertFalse(found);
	}
}
