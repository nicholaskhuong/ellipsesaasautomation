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

@ALM(id = "1869")
@Credentials(user = "PAULINEH", password = "", district = "R100", position = "HRMAN")
public class CreateLeaveRequest_CONF_For_Approve_Step2_Part2 extends BaseTestCase {
	
	protected int row=-1;
	
	//Confirm leave transaction
	@Test(description = "Access to Leave Request Application")
	public void accessApproveLeave() {
		loginToApplication(ApplicationName.APPROVE_LEAVE);
		ScreenAction.waitObjVisible(driver, By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID));
		Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID)).getText(), "Leave Requests");
	}
	
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
	    
	    screenAction.waitObjVisible(driver, By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID),3);
	    Assert.assertTrue(ScreenAction.isElementPresent(driver, By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID),3));
	
	}
	
	@Test(description = "Input Work Group for Setting ", dependsOnMethods="selectSettingIcon")
	public void inputWorkGroup() {	
		
		screenAction.clickBtn(By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID));
		
		screenAction.waitObjVisible(driver, By.id(SettingPageDefinition.WORK_GROUP_FILTER_ID),3);
		
		screenAction.inputTextField(By.id(SettingPageDefinition.WORK_GROUP_FILTER_ID), "SWG");
		
		screenAction.waitObjVisible(driver, By.xpath(SettingPageDefinition.WORK_GROUP_LIST_ID),10);
		
		screenAction.clickBtn(By.xpath(SettingPageDefinition.WORK_GROUP_LIST_ID));
		
		Assert.assertTrue(ScreenAction.isElementPresent(driver,By.id(SettingPageDefinition.WORK_GROUP_BUTTON_ID),2));
		
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
	public void selectUNCOTransaction() {	
		
		List<WebElement> employee = driver.findElements(By.id(LeaveRequestsPageDefinition.EMPLOYEE_TEXTFIELD_ID));
		List<WebElement> statusDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.STATUS_DESC_TEXT_ID));
		List<WebElement> startDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.START_DATE_TEXT_ID));
		List<WebElement> endDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.END_DATE_TEXT_ID));
		List<WebElement> days = driver.findElements(By.id(LeaveRequestsPageDefinition.DAYS_TEXT_ID));
		
		for (int i = 0; i < startDate.size(); i++) {
			
			if(("2016-06-03".equals(startDate.get(i).getAttribute("value")))
					&& ("SPR003".equals(employee.get(i).getAttribute("value"))) ){
				row=i;	
				break;
			}
		}
		
		Assert.assertEquals(employee.get(row).getAttribute("value"),"SPR003");
		Assert.assertEquals(statusDesc.get(row).getAttribute("value"),"Unconfirmed Leave");
		Assert.assertEquals(startDate.get(row).getAttribute("value"),"2016-06-03");
		Assert.assertEquals(endDate.get(row).getAttribute("value"),"2016-06-03");
		Assert.assertEquals(days.get(row).getAttribute("value"),"1.0001");
	
	}
	
	@Test(description = "Assert row count ", dependsOnMethods="selectUNCOTransaction",alwaysRun=true)
	public void selectButtonConfirm() {	
		screenAction.assertButtonEnabled(By.id(LeaveRequestsPageDefinition.CONFIRM_BUTTON_ID), true);
		
		screenAction.clickBtnByIndex(By.id(LeaveRequestsPageDefinition.CONFIRM_BUTTON_ID), row);
	
	}
	
	@Test(description = "Assert row count ", dependsOnMethods="selectButtonConfirm",alwaysRun=true)
	public void assertNoErrorMessageAfterConfirmed(){
		List<WebElement> lismg=driver.findElements(By.cssSelector(LeaveBalancePageDefinition.MESSAGE_TEXT_ID));
		
		String errorMessage="Application has no error message";
		
		if(lismg.size()>0){
			errorMessage=lismg.get(0).getText();
		}

		Assert.assertTrue(lismg.size()==0, errorMessage);
	}
	
	
	@Test(description = "Assert row count ", dependsOnMethods="assertNoErrorMessageAfterConfirmed",alwaysRun=true)
	public void assertStatusChangedToCONF() {	
		List<WebElement> startDate = driver.findElements(By.cssSelector(LeaveRequestsPageDefinition.START_DATE_TEXT_ID));
		List<WebElement> statusDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.STATUS_DESC_TEXT_ID));
		
		
		
		int index=-1;
		for (int i = 0; i < startDate.size(); i++) {
			
			if(("2016-06-03".equals(startDate.get(i).getAttribute("value")))
				&&("Confirmed Leave".equals(statusDesc.get(i).getAttribute("value")))){
				index=i;	
				break;
			}
		}
		
		Assert.assertTrue(index!=-1, "Records not found");
		Assert.assertEquals(startDate.get(index).getAttribute("value"),"2016-06-03");
		Assert.assertEquals(statusDesc.get(index).getAttribute("value"),"Confirmed Leave");
	}
}
