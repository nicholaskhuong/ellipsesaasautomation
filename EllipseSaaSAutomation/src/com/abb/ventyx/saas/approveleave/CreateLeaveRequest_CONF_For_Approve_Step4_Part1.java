package com.abb.ventyx.saas.approveleave;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveBalancePageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveRequestsPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.NewLeaveBalancePageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1869")
@Credentials(user = "SPR003", password = "", district = "R100", position = "SAASPOS_01")
public class CreateLeaveRequest_CONF_For_Approve_Step4_Part1 extends BaseTestCase {
	
	protected int row=-1;
	
	@Test(description = "Access to Leave Request Application")
	public void accessRequestLeave() {
		loginToApplication(ApplicationName.LEAVE_REQUEST);
		
		screenAction.waitObjVisible(driver, By.id("leaveBalanceDays"),3);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_TITLE_ID)).getText(), "Leave Balance");
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.FORECAST_DATE_LABEL_ID)).getText(), "Forecast Date");
	}

	//Prepare data: Create two leave request transactions for approval test case
	@Test(description = "Select New Leave Request button", dependsOnMethods="accessRequestLeave")
	public void selectNewLeaveRequestOnLeaveBalancePage() {
		Assert.assertEquals(driver.findElement(By.id(LeaveBalancePageDefinition.NEW_LEAVE_REQUEST_BUTTON_ID)).getText(), "NEW LEAVE REQUEST");
		Assert.assertTrue(driver.findElement(By.id(LeaveBalancePageDefinition.NEW_LEAVE_REQUEST_BUTTON_ID)).isEnabled());
		driver.findElement(By.id(LeaveBalancePageDefinition.NEW_LEAVE_REQUEST_BUTTON_ID)).click();
		
		screenAction.waitObjVisible(driver, By.cssSelector(NewLeaveBalancePageDefinition.APPLY_BUTTON_ID),2 );
		screenAction.waitObjVisible(driver, By.xpath(NewLeaveBalancePageDefinition.NEW_LEAVE_REQUEST_LABEL_ID),2 );
		Assert.assertTrue(ScreenAction.isElementPresent(driver, By.xpath(NewLeaveBalancePageDefinition.NEW_LEAVE_REQUEST_LABEL_ID)));
		Assert.assertEquals(driver.findElement(By.xpath(NewLeaveBalancePageDefinition.NEW_LEAVE_REQUEST_LABEL_ID)).getText(), "New Leave Request");
		Assert.assertTrue(ScreenAction.isElementPresent(driver, By.cssSelector(NewLeaveBalancePageDefinition.REQUEST_CODE_DROPDOWN_ID)));
	}
	
	@Test( description = "Select Type", dependsOnMethods="selectNewLeaveRequestOnLeaveBalancePage")
	public void selectType(){
		screenAction.selectByText(driver,  By.cssSelector(NewLeaveBalancePageDefinition.TYPE_DROPDOWN_ID), "A Rec Leave (OLVF)" );
		Assert.assertEquals(screenAction.getSelectedText(driver, NewLeaveBalancePageDefinition.TYPE_DROPDOWN_ID), "A Rec Leave (OLVF)");
	}
	
	@Test( description = "Select Request Code", dependsOnMethods="selectType", alwaysRun=true)
	public void selectRequestCode(){
		screenAction.waitObjVisible(driver, By.cssSelector(NewLeaveBalancePageDefinition.REQUEST_CODE_DROPDOWN_ID), 2);
		screenAction.selectByText(driver, By.cssSelector(NewLeaveBalancePageDefinition.REQUEST_CODE_DROPDOWN_ID), "A ANNUAL LEAVE" );
		Assert.assertEquals(screenAction.getSelectedText(driver, NewLeaveBalancePageDefinition.REQUEST_CODE_DROPDOWN_ID), "A ANNUAL LEAVE");
	}
	
	@Test( description = "Input Start Date", dependsOnMethods="selectRequestCode", alwaysRun=true)
	public void selectPartDayLeave(){
		
		screenAction.checkObjSelected(0);
		screenAction.clickCheckBoxN(0);
		Assert.assertTrue(screenAction.checkObjSelected(0));
		
		screenAction.waitObjVisible(driver, By.cssSelector(NewLeaveBalancePageDefinition.START_TIME_HOURS_TEXTFIELD_ID),2);
		screenAction.waitObjVisible(driver, By.cssSelector(NewLeaveBalancePageDefinition.STOP_TIME_HOURS_TEXTFIELD_ID),2);
		screenAction.waitObjVisible(driver, By.cssSelector(NewLeaveBalancePageDefinition.STOP_TIME_HOURS_TEXTFIELD_ID),2);
	}
	
	@Test( description = "Input Start Date", dependsOnMethods="selectPartDayLeave", alwaysRun=true)
	public void inputStartTime(){
		screenAction.inputTextField(By.cssSelector(NewLeaveBalancePageDefinition.START_TIME_HOURS_TEXTFIELD_ID), "06");
		screenAction.inputTextField(By.cssSelector(NewLeaveBalancePageDefinition.START_TIME_MINUTES_TEXTFIELD_ID), "00");
		screenAction.inputTextField(By.cssSelector(NewLeaveBalancePageDefinition.STOP_TIME_HOURS_TEXTFIELD_ID), "11");
		screenAction.inputTextField(By.cssSelector(NewLeaveBalancePageDefinition.STOP_TIME_MINUTES_TEXTFIELD_ID), "00");
	}
	
	@Test( description = "Input Start Date", dependsOnMethods="inputStartTime", alwaysRun=true)
	public void inputStartDate(){
		screenAction.inputDate(driver,By.cssSelector(NewLeaveBalancePageDefinition.START_DATE_DATEPICKER_ID), "06042016");
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.START_DATE_DATEPICKER_ID)).getAttribute("value"), "2016-06-04");
	}
	
	@Test( description = "Input Notes", dependsOnMethods="inputStartDate", alwaysRun=true)
	public void inputNotes(){
		driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.NOTES_TEXTAREA_ID)).sendKeys("Create Part Day leave");
		
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.NOTES_TEXTAREA_ID)).getAttribute("value"), "Create Part Day leave");
	}
	
	@Test( description = "Click Apply", dependsOnMethods="inputNotes", alwaysRun=true)
	public void clickApply(){
		screenAction.clickBtn(By.cssSelector(NewLeaveBalancePageDefinition.APPLY_BUTTON_ID));
	}
	
	
	@Test( description = "Assert no error message returned", dependsOnMethods="clickApply", alwaysRun=true)
	public void assertNoErrorMessage(){
		List<WebElement> lismg=driver.findElements(By.cssSelector(LeaveBalancePageDefinition.MESSAGE_TEXT_ID));
		
		String errorMessage="Application has no error message";
		
		if(lismg.size()>0){
			errorMessage=lismg.get(0).getText();
		}

		Assert.assertTrue(lismg.size()==0, errorMessage);
	}
	
	@Test( description = "Assert no error message returned", dependsOnMethods="assertNoErrorMessage", alwaysRun=true)
	public void assertLeaveBalanceDisplays(){
		screenAction.waitObjVisible(driver, By.id("leaveBalanceDays"),2);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_TITLE_ID)).getText(), "Leave Balance");
	}
	
	@Test( description = "Assert no error message returned", dependsOnMethods="assertLeaveBalanceDisplays", alwaysRun=true)
	public void selectShowAllRequest(){
		Assert.assertEquals(driver.findElement(By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).getText(), "SHOW ALL REQUESTS");
		Assert.assertTrue(driver.findElement(By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).isEnabled());
		driver.findElement(By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).click();
		
		screenAction.waitObjVisible(driver, By.xpath(LeaveRequestsPageDefinition.LEAVE_REQUEST_TITLE_ID), 3);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestsPageDefinition.LEAVE_REQUEST_TITLE_ID)).getText(), "Leave Requests");
	}
	
	@Test(description = "Assert details of first row", dependsOnMethods="selectShowAllRequest",alwaysRun=true)
	public void assertNewEntryCreated() {
		List<WebElement> bookedLeaveCode = driver.findElements(By.id(LeaveRequestsPageDefinition.REQUEST_CODE_TEXT_ID));
		List<WebElement> bookedLeaveDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.REQUEST_CODE_DESCRIPTION_TEXT_ID));
		List<WebElement> leaveStartDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.START_DATE_TEXT_ID));
		List<WebElement> leaveEndDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.END_DATE_TEXT_ID));
		List<WebElement> leaveDays = driver.findElements(By.id(LeaveRequestsPageDefinition.DAYS_TEXT_ID));
		List<WebElement> leaveStatusDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.STATUS_DESC_TEXT_ID));
		
		for (int i = 0; i < leaveStartDate.size(); i++) {
			
			if("2016-06-04".equals(leaveStartDate.get(i).getAttribute("value"))){
				row=i;	
				break;
			}
		}
		
		Assert.assertTrue(row!=-1, "Records not found");
		Assert.assertEquals(bookedLeaveCode.get(row).getAttribute("value"),"A");
		Assert.assertEquals(bookedLeaveDesc.get(row).getAttribute("value"),"ANNUAL LEAVE");
		Assert.assertEquals(leaveStartDate.get(row).getAttribute("value"),"2016-06-04");
		Assert.assertEquals(leaveEndDate.get(row).getAttribute("value"),"2016-06-04");
		Assert.assertEquals(leaveDays.get(row).getAttribute("value"),"0.4000");
		Assert.assertEquals(leaveStatusDesc.get(row).getAttribute("value"),"Unconfirmed Leave");
	}
	
}
