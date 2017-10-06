package com.abb.ventyx.saas.approveleave;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveBalancePageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveRequestsPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.LoginPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.SettingPageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1868")
@Credentials(user = "PAULINEH", password = "", district = "R100", position = "HRMAN")
public class ReviewLeaveApprove_Step0102 extends BaseTestCase {
	
	
	@Test(description = "Access to Leave Request Application")
	public void accessApproveLeave() {
		loginToApplication(ApplicationName.APPROVE_LEAVE);
		ScreenAction.waitObjVisible(driver, By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID));
		Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID)).getText(), "Leave Requests");
		
	}
	
	//Step 1: Filter Criteria - Ensure Leave Requests are displays based up the filter criteria.

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
	    
	    screenAction.waitObjVisible(driver, By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID),60);
	    Assert.assertTrue(ScreenAction.isElementPresent(driver, By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID),10));
	
	}
	
	@Test(description = "Input Work Group for Setting ", dependsOnMethods="selectSettingIcon")
	public void inputWorkGroup() {	
		
		screenAction.clickBtn(By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID));
		
		screenAction.waitObjVisible(driver, By.id(SettingPageDefinition.WORK_GROUP_FILTER_ID),5);
		
		screenAction.inputTextField(By.id(SettingPageDefinition.WORK_GROUP_FILTER_ID), "SWG");
		
		screenAction.waitObjVisible(driver, By.xpath(SettingPageDefinition.WORK_GROUP_LIST_ID),60);
		
		screenAction.clickBtn(By.xpath(SettingPageDefinition.WORK_GROUP_LIST_ID));
		
		Assert.assertTrue(ScreenAction.isElementPresent(driver,By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID),10));
		
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
	
	@Test(description = "Assert row count ", dependsOnMethods="clickButtonApply")
	public void assertLeaveRequestsDetailsDisplay() {	
		
		List<WebElement> employee = driver.findElements(By.id(LeaveRequestsPageDefinition.EMPLOYEE_TEXTFIELD_ID));
		List<WebElement> statusDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.STATUS_DESC_TEXT_ID));
		
		Assert.assertEquals(employee.get(0).getAttribute("value"),"SPR002");
		Assert.assertEquals(statusDesc.get(0).getAttribute("value"),"Unconfirmed Leave");
	}
	
	//Step 2: Filter Criteria - Ensure data is removed
	
	@Test(description = "Select Setting icon on Leave Balance Page  ", dependsOnMethods="clickButtonApply")
	public void selectSettingIconAgain() {
	
	    driver.findElement(By.cssSelector(SettingPageDefinition.MENU_NAVIGATE_ID)).click();
	    driver.findElement(By.xpath(SettingPageDefinition.SETTING_ID)).click();
	    
	    screenAction.waitObjVisible(driver, By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID),3);
	    Assert.assertTrue(ScreenAction.isElementPresent(driver, By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID),3));
	}
	
	@Test(description = "Input Work Group for Setting ", dependsOnMethods="selectSettingIconAgain",alwaysRun = true)
	public void assertSettingDetails() {	
		
		screenAction.waitObjVisible(driver, By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID),3);
		
		Assert.assertEquals(driver.findElement(By.id(SettingPageDefinition.CREW_BUTTON_ID)).getText(),"");
		Assert.assertEquals(driver.findElement(By.id(SettingPageDefinition.POSITION_BUTTON_ID)).getText(),"");
		Assert.assertEquals(driver.findElement(By.cssSelector(SettingPageDefinition.POSITION_LEVELS_TEXTFIELD_ID)).getAttribute("value"),"");
		Assert.assertEquals(screenAction.getSelectedText(driver, SettingPageDefinition.PERSONNEL_GROUP_DROPDOWN_ID).trim(),"");
		Assert.assertTrue(driver.findElement(By.cssSelector(SettingPageDefinition.APPLY_BUTTON_ID)).isEnabled());		
		Assert.assertEquals(driver.findElement(By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID)).getText(), "SWG Sarah test WG");
	}
	
	@Test(description = "Input Work Group for Setting ", dependsOnMethods="assertSettingDetails",alwaysRun = true)
	public void removeWorkGroup() {	
		
		Assert.assertTrue(driver.findElement(By.id(SettingPageDefinition.DELETE_WORK_GROUP_BUTTON_ID)).isEnabled());	
		screenAction.clickBtn(By.id(SettingPageDefinition.DELETE_WORK_GROUP_BUTTON_ID));
		
		Assert.assertTrue(driver.findElement(By.cssSelector(SettingPageDefinition.APPLY_BUTTON_ID)).isEnabled());	
		Assert.assertEquals(driver.findElement(By.id(SettingPageDefinition.CREW_BUTTON_ID)).getText(),"");
		Assert.assertEquals(driver.findElement(By.id(SettingPageDefinition.POSITION_BUTTON_ID)).getText(),"");
		Assert.assertEquals(driver.findElement(By.cssSelector(SettingPageDefinition.POSITION_LEVELS_TEXTFIELD_ID)).getAttribute("value"),"");
		Assert.assertEquals(screenAction.getSelectedText(driver, SettingPageDefinition.PERSONNEL_GROUP_DROPDOWN_ID).trim(),"");
		Assert.assertTrue(driver.findElement(By.cssSelector(SettingPageDefinition.APPLY_BUTTON_ID)).isEnabled());		
	}
	
	@Test(description = "Click Apply ", dependsOnMethods="removeWorkGroup",alwaysRun = true)
	public void clickButtonApplyAgain() {	
		driver.findElement(By.cssSelector(SettingPageDefinition.APPLY_BUTTON_ID)).click();
	  
		 screenAction.waitObjInvisible(By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID));
	    screenAction.waitObjVisible(driver, By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID),30);
	    Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID)).getText(), "Leave Requests");
	}
	
	@Test(description = "Assert row count ", dependsOnMethods="clickButtonApplyAgain")
	public void assertLeaveRequestsDetailsDisplayAgain() {	
		Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID)).getText(), "Leave Requests");
		
		List<WebElement> employee = driver.findElements(By.id(LeaveRequestsPageDefinition.EMPLOYEE_TEXTFIELD_ID));
		
		Assert.assertEquals(employee.size(), 0);
	}
	
	@Test(description = "Assert error message displays ", dependsOnMethods="assertLeaveRequestsDetailsDisplayAgain")
	public void assertErrorMessage() {	
		List<WebElement> lismg=driver.findElements(By.cssSelector(LeaveBalancePageDefinition.MESSAGE_TEXT_ID));
		
		Assert.assertTrue(lismg.size()>0);
		Assert.assertTrue(lismg.get(0).getText().contains("The search did not return any results"));
	}
}
