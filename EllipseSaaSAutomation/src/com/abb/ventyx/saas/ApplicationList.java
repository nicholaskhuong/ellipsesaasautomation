package com.abb.ventyx.saas;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1")
@Credentials(user = "SPR002", password = "", district = "R100", position = "HRMAN")
public class ApplicationList extends BaseTestCase {
	@Test(description = "Access to Leave Request Application")
	public void checkApplicationInList() {
		loginToApplication(ApplicationName.LEAVE_REQUEST);
		ScreenAction.waitObjVisible(driver, By.id("leaveBalanceDays"));
		List<WebElement> leaveBalanceDays = driver.findElements(By.id("leaveBalanceDays"));
		Assert.assertEquals("0.0148", leaveBalanceDays.get(0).getAttribute("value"));
		Assert.assertEquals("0.2813", leaveBalanceDays.get(1).getAttribute("value"));
		Assert.assertEquals("0.0147", leaveBalanceDays.get(2).getAttribute("value"));
	}
}
