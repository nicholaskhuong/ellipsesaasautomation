package com.abb.ventyx.saas.requestleave;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveBalanceDetailsPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveBalancePageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveRequestsPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.NewLeaveBalancePageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1864")
@Credentials(user = "SPR002", password = "", district = "R100", position = "HRMAN")
public class CreateLeaveRequest_Step02 extends BaseTestCase {
	
	ScreenAction screenAction=null;
	
	
	@Test(description = "Access to Leave Request Application")
	public void accessRequestLeave() {
		loginToApplication(ApplicationName.LEAVE_REQUEST);
		
		screenAction = new ScreenAction(driver);
		
		screenAction.waitObjVisible(driver, By.id("leaveBalanceDays"),2);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_TITLE_ID)).getText(), "Leave Balance");
	}

	//Step 2: Create Leave Request Part Day  from Detail Balance Page
	@Test(description = "Assert Days and Hours for Leave Balance Summay screen", dependsOnMethods="accessRequestLeave")
	public void assertAnnualVacationLeaveOnLeaveBalancePage() {
		List<WebElement> leaveBalanceDays = driver.findElements(By.id(LeaveBalancePageDefinition.LEAVE_BALANCE_DAYS_TEXT_ID));
		List<WebElement> leaveBalanceHours = driver.findElements(By.id(LeaveBalancePageDefinition.LEAVE_BALANCE_HOURS_TEXT_ID));
		
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_DAYS_LABEL_ID)).getText(), "Days");
		Assert.assertEquals(leaveBalanceDays.get(0).getAttribute("value"),"0.0148");
		
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_HOURS_LABEL_ID)).getText(), "Hours");
		Assert.assertEquals(leaveBalanceHours.get(0).getAttribute("value"),"0.1180");
	}
	
	@Test(description = "Select Annual Leave Icon", dependsOnMethods="assertAnnualVacationLeaveOnLeaveBalancePage",alwaysRun=true)
	public void selectAnnualVacationLeaveDetailIcon(){
		driver.findElement(By.cssSelector(LeaveBalancePageDefinition.ANNUAL_VACATION_LVE_NEXT_ICON_ID)).click();
		
		screenAction.waitObjVisible(driver, By.xpath(LeaveBalanceDetailsPageDefinition.LEAVE_BALANCE_DETAILS_TITILE_ID),5);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalanceDetailsPageDefinition.LEAVE_BALANCE_DETAILS_TITILE_ID)).getText(), "Leave Balance Details");
		
	}
	
	@Test(description = "Assert Days and Hours for Leave Balance Summay screen", dependsOnMethods="selectAnnualVacationLeaveDetailIcon")
	public void assertAnnualVacationLveOnLeaveBalanceDetailsPage() {
		Assert.assertEquals(driver.findElement(By.cssSelector(LeaveBalanceDetailsPageDefinition.TYPE_CODE_TEXT_ID)).getAttribute("value"), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector(LeaveBalanceDetailsPageDefinition.TYPE_DESC_TEXT_ID)).getAttribute("value"), "ANNUAL/VACATION LVE");
		Assert.assertEquals(driver.findElement(By.cssSelector(LeaveBalanceDetailsPageDefinition.ANNIVERSARY_DATE_TEXT_ID)).getAttribute("value"), "2016-05-30");
		Assert.assertEquals(driver.findElement(By.cssSelector(LeaveBalanceDetailsPageDefinition.STEP_DATE_TEXT_ID)).getAttribute("value"), "2016-05-30");
		Assert.assertEquals(driver.findElement(By.cssSelector(LeaveBalanceDetailsPageDefinition.DAYS_TEXT_ID)).getAttribute("value"),"0.0148");
		Assert.assertEquals(driver.findElement(By.cssSelector(LeaveBalanceDetailsPageDefinition.HOURS_TEXT_ID)).getAttribute("value"),"0.1180");
	}

	@Test(description = "Select New Leave Request button", dependsOnMethods="assertAnnualVacationLveOnLeaveBalanceDetailsPage",alwaysRun=true)
	public void selectButtonNewLeaveRequest() {
		Assert.assertEquals(driver.findElement(By.cssSelector(LeaveBalanceDetailsPageDefinition.NEW_LEAVE_REQUEST_BUTTON_ID)).getText(), "NEW LEAVE REQUEST");
		Assert.assertTrue(driver.findElement(By.cssSelector(LeaveBalanceDetailsPageDefinition.NEW_LEAVE_REQUEST_BUTTON_ID)).isEnabled());
		driver.findElement(By.cssSelector(LeaveBalanceDetailsPageDefinition.NEW_LEAVE_REQUEST_BUTTON_ID)).click();
		
		screenAction.waitObjVisible(driver, By.xpath(NewLeaveBalancePageDefinition.NEW_LEAVE_REQUEST_LABEL_ID),3 );
		Assert.assertTrue(ScreenAction.isElementPresent(driver, By.xpath(NewLeaveBalancePageDefinition.NEW_LEAVE_REQUEST_LABEL_ID)));
	}
	
	@Test( description = "Select Type", dependsOnMethods="selectButtonNewLeaveRequest",alwaysRun=true)
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
		screenAction.inputDate(driver,By.cssSelector(NewLeaveBalancePageDefinition.START_DATE_DATEPICKER_ID), "06292016");
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.START_DATE_DATEPICKER_ID)).getAttribute("value"), "2016-06-29");
	}
	
	@Test( description = "Input Notes", dependsOnMethods="inputStartDate", alwaysRun=true)
	public void inputNotes(){
		driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.NOTES_TEXTAREA_ID)).sendKeys("Create Part Day leave");
		
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.NOTES_TEXTAREA_ID)).getAttribute("value"), "Create Part Day leave");
	}
	
	@Test( description = "Assert Day, Hours Forecast Days, Forecast Hours", dependsOnMethods="inputNotes", alwaysRun=true)
	public void assertDays_Hours_ForecastDays_ForecastHours(){
		screenAction.waitObjVisible(driver, By.cssSelector(NewLeaveBalancePageDefinition.DAYS_TEXT_ID), 10);
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.DAYS_TEXT_ID)).getAttribute("value"), "0.4000");
		
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.HOURS_TEXT_ID)).getAttribute("value"), "5.0005");
		
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.FORECAST_DAYS_TEXT_ID)).getAttribute("value"), "-1.5924");
		
		Assert.assertEquals(driver.findElement(By.cssSelector(NewLeaveBalancePageDefinition.FORECAST_HOURS_TEXT_ID)).getAttribute("value"), "-12.7389");
	}
	
	@Test( description = "Click Apply", dependsOnMethods="assertDays_Hours_ForecastDays_ForecastHours", alwaysRun=true)
	public void clickApply(){
		screenAction = new ScreenAction(driver);
		screenAction.clickBtn(By.cssSelector(NewLeaveBalancePageDefinition.APPLY_FOR_UPDATE_BUTTON_ID));
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
	public void assertLeaveBalanceDetailsDisplays(){
		screenAction.waitObjVisible(driver, By.xpath(LeaveBalanceDetailsPageDefinition.LEAVE_BALANCE_DETAILS_2_TITILE_ID),10);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalanceDetailsPageDefinition.LEAVE_BALANCE_DETAILS_2_TITILE_ID)).getText(), "Leave Balance Details");
	}
	
	@Test( description = "Assert no error message returned", dependsOnMethods="assertLeaveBalanceDetailsDisplays", alwaysRun=true)
	public void clickBackIconToReturnBalancePage(){
		screenAction.clickBtn(By.cssSelector(LeaveBalanceDetailsPageDefinition.BACK_ICON_ID));
		
		screenAction.waitObjVisible(driver, By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_TITLE_2_ID),10);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveBalancePageDefinition.LEAVE_BALANCE_TITLE_2_ID)).getText(), "Leave Balance");
	}
	
	@Test( description = "Assert no error message returned", dependsOnMethods="clickBackIconToReturnBalancePage", alwaysRun=true)
	public void selectButtonShowAllRequests(){
		screenAction.waitObjVisible(driver, By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID), 2);
		
		Assert.assertEquals(driver.findElement(By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).getText(), "SHOW ALL REQUESTS");
		Assert.assertTrue(driver.findElement(By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).isEnabled());
		driver.findElement(By.id(LeaveBalancePageDefinition.SHOW_ALL_REQUESTS_BUTTON_ID)).click();
		
		screenAction.waitObjVisible(driver, By.xpath(LeaveRequestsPageDefinition.LEAVE_REQUEST_TITLE_ID), 10);
	}
	
	@Test(description = "Assert details of first row", dependsOnMethods="selectButtonShowAllRequests",alwaysRun=true)
	public void assertNewEntryCreated() {
		List<WebElement> bookedLeaveCode = driver.findElements(By.id(LeaveRequestsPageDefinition.REQUEST_CODE_TEXT_ID));
		List<WebElement> bookedLeaveDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.REQUEST_CODE_DESCRIPTION_TEXT_ID));
		List<WebElement> leaveStartDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.START_DATE_TEXT_ID));
		List<WebElement> leaveEndDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.END_DATE_TEXT_ID));
		List<WebElement> leaveDays = driver.findElements(By.id(LeaveRequestsPageDefinition.DAYS_TEXT_ID));
		List<WebElement> leaveStatusDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.STATUS_TEXT_ID));
		
		int index=-1;
		for (int i = 0; i < leaveStartDate.size(); i++) {
			
			if("2016-06-29".equals(leaveStartDate.get(i).getAttribute("value"))){
				index=i;	
				break;
			}
		}
		
		Assert.assertTrue(index>0, "No records found");
		Assert.assertEquals(bookedLeaveCode.get(index).getAttribute("value"),"A");
		Assert.assertEquals(bookedLeaveDesc.get(index).getAttribute("value"),"ANNUAL LEAVE");
		Assert.assertEquals(leaveStartDate.get(index).getAttribute("value"),"2016-06-29");
		Assert.assertEquals(leaveEndDate.get(index).getAttribute("value"),"2016-06-29");
		Assert.assertEquals(leaveDays.get(index).getAttribute("value"),"0.4000");
		Assert.assertEquals(leaveStatusDesc.get(index).getAttribute("value"),"Approved Leave");
	}
	
}
