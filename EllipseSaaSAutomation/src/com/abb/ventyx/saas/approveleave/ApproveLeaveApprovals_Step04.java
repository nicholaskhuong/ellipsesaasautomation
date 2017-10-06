package com.abb.ventyx.saas.approveleave;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveBalancePageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveRequestPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveRequestsDetailsPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveRequestsPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.LoginPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.SettingPageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1869")
@Credentials(user = "PAULINEH", password = "", district = "R100", position = "HRMAN")
public class ApproveLeaveApprovals_Step04 extends BaseTestCase {
	
	protected int row=-1;
	
	@Test(description = "Access to Leave Request Application")
	public void accessApproveLeave() {
		loginToApplication(ApplicationName.APPROVE_LEAVE);
		ScreenAction.waitObjVisible(driver, By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID));
		Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID)).getText(), "Leave Requests");
	}
	
	//Step 4: Approve Leave with Status = CONF from Leave Request Detail page.
	@Test(description = "Select Setting icon on Leave Balance Page  ", dependsOnMethods="accessApproveLeave")
	public void selectSettingIcon() {
		screenAction.waitObjInvisible(By.id(LoginPageDefinition.LOGIN_BUTTON_ID));
		
		List<WebElement> lismg=driver.findElements(By.cssSelector(LeaveBalancePageDefinition.MESSAGE_TEXT_ID));
		
		if(lismg.size()>0){
			screenAction.clickTapToClose(driver, By.cssSelector(LeaveBalancePageDefinition.MESSAGE_TEXT_ID));
			lismg=driver.findElements(By.cssSelector(LeaveBalancePageDefinition.MESSAGE_TEXT_ID));
		}

		Assert.assertTrue(lismg.size()==0);
		
	    driver.findElement(By.cssSelector(SettingPageDefinition.MENU_NAVIGATE_ID)).click();
	    driver.findElement(By.xpath(SettingPageDefinition.SETTING_ID)).click();
	    
	    screenAction.waitObjVisible(driver, By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID),10);
	    Assert.assertTrue(ScreenAction.isElementPresent(driver, By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID),10));
	
	}
	
	@Test(description = "Input Work Group for Setting ", dependsOnMethods="selectSettingIcon")
	public void inputWorkGroup() {	
		
		screenAction.clickBtn(By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID));
		
		screenAction.waitObjVisible(driver, By.id(SettingPageDefinition.WORK_GROUP_FILTER_ID),5);
		
		screenAction.inputTextField(By.id(SettingPageDefinition.WORK_GROUP_FILTER_ID), "SWG");
		
		screenAction.waitObjVisible(driver, By.xpath(SettingPageDefinition.WORK_GROUP_LIST_ID),20);
		
		screenAction.clickBtn(By.xpath(SettingPageDefinition.WORK_GROUP_LIST_ID));
		
		Assert.assertTrue(ScreenAction.isElementPresent(driver,By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID),5));
		
		Assert.assertEquals(driver.findElement(By.id(SettingPageDefinition.CREW_BUTTON_ID)).getText(),"");
		Assert.assertEquals(driver.findElement(By.id(SettingPageDefinition.POSITION_BUTTON_ID)).getText(),"");
		Assert.assertEquals(driver.findElement(By.cssSelector(SettingPageDefinition.POSITION_LEVELS_TEXTFIELD_ID)).getAttribute("value"),"");
		
		Assert.assertTrue(driver.findElement(By.cssSelector(SettingPageDefinition.APPLY_BUTTON_ID)).isEnabled());		
		Assert.assertTrue(driver.findElement(By.id(SettingPageDefinition.DELETE_WORK_GROUP_BUTTON_ID)).isEnabled());
		Assert.assertEquals(driver.findElement(By.cssSelector(SettingPageDefinition.PERSONNEL_GROUP_DROPDOWN_ID)).getAttribute("value"),"1");
		
		Assert.assertEquals(driver.findElement(By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID)).getText(), "SWG Sarah test WG");
	}
	
	
	@Test(description = "Click Apply ", dependsOnMethods="inputWorkGroup")
	public void clickButtonApply() {	
		screenAction.assertButtonEnabled(By.cssSelector(SettingPageDefinition.APPLY_BUTTON_ID), true);
		driver.findElement(By.cssSelector(SettingPageDefinition.APPLY_BUTTON_ID)).click();
	  
	    screenAction.waitObjVisible(driver, By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID),2);
	    Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID)).getText(), "Leave Requests");
	}
	
	@Test(description = "Assert row count ", dependsOnMethods="clickButtonApply",alwaysRun=true)
	public void assertCONFTransaction() {	
		
		List<WebElement> employee = driver.findElements(By.id(LeaveRequestsPageDefinition.EMPLOYEE_TEXTFIELD_ID));
		List<WebElement> statusDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.STATUS_DESC_TEXT_ID));
		List<WebElement> startDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.START_DATE_TEXT_ID));
		List<WebElement> endDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.END_DATE_TEXT_ID));
		List<WebElement> days = driver.findElements(By.id(LeaveRequestsPageDefinition.DAYS_TEXT_ID));
		
		for (int i = 0; i < startDate.size(); i++) {
			
			if(("2016-06-04".equals(startDate.get(i).getAttribute("value")))
					&& ("SPR003".equals(employee.get(i).getAttribute("value"))) ){
				row=i;	
				break;
			}
		}
		
		Assert.assertEquals(employee.get(row).getAttribute("value"),"SPR003");
		Assert.assertEquals(statusDesc.get(row).getAttribute("value"),"Confirmed Leave");
		Assert.assertEquals(startDate.get(row).getAttribute("value"),"2016-06-04");
		Assert.assertEquals(endDate.get(row).getAttribute("value"),"2016-06-04");
		Assert.assertEquals(days.get(row).getAttribute("value"),"0.4000");
	}
	
	@Test(description = "Assert row count ", dependsOnMethods="assertCONFTransaction",alwaysRun=true)
	public void selectCONFAndAssertLeaveDetailsPageDisplay() {	
		
		screenAction.clickBtnByIndex(By.id(LeaveRequestsPageDefinition.ROW_SELECT_BUTTON_ID), row);
		
		screenAction.waitObjInvisible(By.id(LeaveRequestsPageDefinition.CONFIRM_BUTTON_ID));
		screenAction.assertButtonEnabled(By.id(LeaveRequestsPageDefinition.APPROVE_BUTTON_ID), true);
		screenAction.waitObjVisible(driver, By.xpath(LeaveRequestPageDefinition.LEAVE_REQUEST_TITLE_ID), 60);
		Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestPageDefinition.LEAVE_REQUEST_TITLE_ID)).getText(), "Leave Request Details");
	}
	
	
	@Test(description = "Assert row count ", dependsOnMethods="selectCONFAndAssertLeaveDetailsPageDisplay",alwaysRun=true)
	public void selectButtonApprove() {	
		screenAction.assertButtonEnabled(By.xpath(LeaveRequestsDetailsPageDefinition.APPROVE_BUTTON_ID), true);
		
		Assert.assertEquals(driver.findElements(By.xpath(LeaveRequestsDetailsPageDefinition.APPROVE_BUTTON_ID)).get(0).getText(), "APPROVE");
		
		screenAction.clickBtnByIndex(By.xpath(LeaveRequestsDetailsPageDefinition.APPROVE_BUTTON_ID),0);
		
		 screenAction.waitObjVisible(driver, By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID),5);
		 Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID)).getText(), "Leave Requests");
	}
	
	@Test(description = "Assert row count ", dependsOnMethods="selectButtonApprove",alwaysRun=true)
	public void assertNoErrorMessage(){
		List<WebElement> lismg=driver.findElements(By.cssSelector(LeaveBalancePageDefinition.MESSAGE_TEXT_ID));
		
		String errorMessage="Application has no error message";
		
		if(lismg.size()>0){
			errorMessage=lismg.get(0).getText();
		}

		Assert.assertTrue(lismg.size()==0, errorMessage);
	}
	
	
	@Test(description = "Assert row count ", dependsOnMethods="assertNoErrorMessage",alwaysRun=true)
	public void assertEntryApprovedNoExistInLeaveRequests() {	
		List<WebElement> startDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.START_DATE_TEXT_ID));
		List<WebElement> statusDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.STATUS_DESC_TEXT_ID));
		
		int index=-1;
		for (int i = 0; i < startDate.size(); i++) {
			
			if(("2016-06-04".equals(startDate.get(i).getAttribute("value"))) 
					&& ("Confirmed Leave".equals(statusDesc.get(i).getAttribute("value"))) ){
				index=i;	
				break;
			}
		}
		
		Assert.assertTrue(index==-1, "Record still exists at row: "+index);
	
	}
}
	