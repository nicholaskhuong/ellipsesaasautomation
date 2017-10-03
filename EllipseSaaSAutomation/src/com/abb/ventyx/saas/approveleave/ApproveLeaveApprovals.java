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
public class ApproveLeaveApprovals extends BaseTestCase {
	
	ScreenAction screenAction;
	
	@Test(description = "Access to Leave Request Application")
	public void accessApproveLeave() {
		loginToApplication(ApplicationName.APPROVE_LEAVE);
		ScreenAction.waitObjVisible(driver, By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID));
		Assert.assertEquals(driver.findElement(By.xpath(LeaveRequestsPageDefinition.APPROVE_LEAVE_REQUESTS_TITLE_ID)).getText(), "Leave Requests");
		
	}
}
	