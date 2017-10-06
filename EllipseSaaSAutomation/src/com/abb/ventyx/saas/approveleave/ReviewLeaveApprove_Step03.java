package com.abb.ventyx.saas.approveleave;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveRequestsPageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1868")
@Credentials(user = "SPR002", password = "", district = "R100", position = "HR1")
public class ReviewLeaveApprove_Step03 extends BaseTestCase {

	@Test(description = "Access to Leave Request Application")
	public void accessApproveLeave() {
		loginToApplication(ApplicationName.APPROVE_LEAVE);
		ScreenAction.waitObjVisible(driver, By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID));
		Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID)).getText(), "Leave Requests");
	}
	
	//Step 3: Ensure Approve, Confirm, Reject will be greyed out when login as a ESS user
	@Test(description = "Assert row count ", dependsOnMethods="accessApproveLeave")
	public void assertLeaveRequestsDetailsDisplay() {	
		
		screenAction.waitObjVisible(driver, By.id(LeaveRequestsPageDefinition.EMPLOYEE_TEXTFIELD_ID),20);
		screenAction.waitObjVisible(driver, By.id(LeaveRequestsPageDefinition.STATUS_DESC_TEXT_ID),20);
		List<WebElement> employee = driver.findElements(By.id(LeaveRequestsPageDefinition.EMPLOYEE_TEXTFIELD_ID));
		List<WebElement> statusDesc = driver.findElements(By.id(LeaveRequestsPageDefinition.STATUS_DESC_TEXT_ID));
		
		Assert.assertEquals(employee.get(0).getAttribute("value"),"SPR002");
		Assert.assertEquals(statusDesc.get(0).getAttribute("value"),"Unconfirmed Leave");
	}
	
	@Test(description = "Assert row count ", dependsOnMethods="assertLeaveRequestsDetailsDisplay", alwaysRun=true)
	public void assertButtonsApprove_Confirm_RejectGreyed() {
		screenAction.assertButtonEnabled(By.id(LeaveRequestsPageDefinition.APPROVE_BUTTON_ID), false);
		screenAction.assertButtonEnabled(By.id(LeaveRequestsPageDefinition.CONFIRM_BUTTON_ID), false);
		screenAction.assertButtonEnabled(By.id(LeaveRequestsPageDefinition.REJECT_BUTTON_ID), false);
	}
}
