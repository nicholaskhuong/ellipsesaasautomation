package com.abb.ventyx.saas.requestleave;

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

@ALM(id = "1866")
@Credentials(user = "SPR002", password = "", district = "R100", position = "SAASPOS_01")
public class CreateLeaveRequest_UNCO_For_Delete extends BaseTestCase {
	
	ScreenAction screenAction=null;
	
	
	@Test(description = "Access to Leave Request Application")
	public void accessRequestLeave() {
		loginToApplication(ApplicationName.LEAVE_REQUEST);
		
		screenAction = new ScreenAction(driver);
		
		screenAction.waitObjVisible(driver, By.id("leaveBalanceDays"),2);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_TITLE_ID)).getText(), "Leave Balance");
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.FORECAST_DATE_LABEL_ID)).getText(), "Forecast Date");
	}

	//Step1: Create Leave Request Single Day  from Balance Page
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

	@Test(description = "Select New Leave Request button", dependsOnMethods="assertSickLeaveOnLeaveBalancePage",alwaysRun=true)
	public void selectNewLeaveRequestOnLeaveBalancePage() {
		Assert.assertEquals(driver.findElement(By.id(LeaveBalancePageDefinition.NEW_LEAVE_REQUEST_BUTTON_ID)).getText(), "NEW LEAVE REQUEST");
		Assert.assertTrue(driver.findElement(By.id(LeaveBalancePageDefinition.NEW_LEAVE_REQUEST_BUTTON_ID)).isEnabled());
		driver.findElement(By.id(LeaveBalancePageDefinition.NEW_LEAVE_REQUEST_BUTTON_ID)).click();
		
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
		screenAction.selectByText(driver, By.cssSelector(NewLeaveBalancePageDefinition.REQUEST_CODE_DROPDOWN_ID), "A ANNUAL LEAVE" );
		Assert.assertEquals(screenAction.getSelectedText(driver, NewLeaveBalancePageDefinition.REQUEST_CODE_DROPDOWN_ID), "A ANNUAL LEAVE");
	}
	
	@Test( description = "Input Start Date", dependsOnMethods="selectRequestCode", alwaysRun=true)
	public void inputStartDate(){
		screenAction.inputDate(driver,By.cssSelector(NewLeaveBalancePageDefinition.START_DATE_DATEPICKER_ID), "06302016");
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.START_DATE_DATEPICKER_ID)).getAttribute("value"), "2016-06-30");
	}
	
	@Test( description = "Input End Date", dependsOnMethods="inputStartDate", alwaysRun=true)
	public void inputEndDate(){
		screenAction.inputDate(driver, By.cssSelector(NewLeaveBalancePageDefinition.END_DATE_DATEPICKER_ID), "06302016");
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.END_DATE_DATEPICKER_ID)).getAttribute("value"), "2016-06-30");
	}
	
	@Test( description = "Input Notes", dependsOnMethods="inputEndDate", alwaysRun=true)
	public void inputNotes(){
		driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.NOTES_TEXTAREA_ID)).sendKeys("SaaS.\nCreate leave request testing");
		
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.NOTES_TEXTAREA_ID)).getAttribute("value"), "SaaS.\nCreate leave request testing");
	}
	
//	@Test( description = "Assert Day, Hours Forecast Days, Forecast Hours", dependsOnMethods="inputNotes", alwaysRun=true)
	public void assertDays_Hours_ForecastDays_ForecastHours(){
		screenAction.waitObjVisible(driver, By.cssSelector(NewLeaveBalancePageDefinition.DAYS_TEXT_ID), 3);
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.DAYS_TEXT_ID)).getAttribute("value"), "1.0001");
		
		screenAction.waitObjVisible(driver, By.cssSelector(NewLeaveBalancePageDefinition.HOURS_TEXT_ID), 3);
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.HOURS_TEXT_ID)).getAttribute("value"), "12.5013");
		
		screenAction.waitObjVisible(driver, By.cssSelector(NewLeaveBalancePageDefinition.FORECAST_DAYS_TEXT_ID), 3);
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.FORECAST_DAYS_TEXT_ID)).getAttribute("value"), "-2.5332");
		
		screenAction.waitObjVisible(driver, By.cssSelector(NewLeaveBalancePageDefinition.FORECAST_HOURS_TEXT_ID), 3);
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.FORECAST_HOURS_TEXT_ID)).getAttribute("value"), "-20.2653");
	}
		
	@Test( description = "Click Apply", dependsOnMethods="inputNotes", alwaysRun=true)
	public void clickApply(){
		screenAction = new ScreenAction(driver);
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
		
		ScreenAction screenAction= new ScreenAction(driver);
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
		List<WebElement> leaveStatusDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.STATUS_TEXT_ID));
		
		int index=-1;
		for (int i = 0; i < leaveStartDate.size(); i++) {
			
			if("2016-06-30".equals(leaveStartDate.get(i).getAttribute("value"))){
				index=i;	
				break;
			}
		}
		
		Assert.assertTrue(index>0, "No records found");
		Assert.assertEquals(bookedLeaveCode.get(index).getAttribute("value"),"A");
		Assert.assertEquals(bookedLeaveDesc.get(index).getAttribute("value"),"ANNUAL LEAVE");
		Assert.assertEquals(leaveStartDate.get(index).getAttribute("value"),"2016-06-30");
		Assert.assertEquals(leaveEndDate.get(index).getAttribute("value"),"2016-06-30");
		Assert.assertEquals(leaveDays.get(index).getAttribute("value"),"1.0001");
		Assert.assertEquals(leaveStatusDesc.get(index).getAttribute("value"),"Unconfirmed Leave");
	}
	
}
