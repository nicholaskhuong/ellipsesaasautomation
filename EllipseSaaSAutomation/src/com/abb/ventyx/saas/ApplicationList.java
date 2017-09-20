package com.abb.ventyx.saas;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;

@ALM(id = "1")
@Credentials(user = "HREMP", password = "test", district = "R100", position = "HRMAN")
public class ApplicationList extends BaseTestCase {
	@Test(description = "Access to Leave Request Application")
	public void checkApplicationInList() {
		super.expectedResult = "The application appears in the list";
		driver.findElement(By.cssSelector("#LeaveRequest_EL89SUP__8_9_ > a > span")).click();
		login();

	}
}
