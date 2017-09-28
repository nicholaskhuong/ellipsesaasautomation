package com.abb.ventyx.saas.issuestock;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveBalancePageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1019")
@Credentials(user = "SPR002", password = "", district = "R100", position = "MATMAN")
public class IssueStockSearchbyRequisitionNumbers_Step07 extends BaseTestCase {
	
	@Test(description = "Access to Pick Stock Application")
	public void accessPickStock() {
		loginToApplication(ApplicationName.PICK_STOCK);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(), "Pick Tasks");
		ScreenAction errorAction = new ScreenAction(driver);
		errorAction.clickTapToClose(driver,By.cssSelector("#saas-3522304-overlays > div.v-Notification.error.v-Notification-error > div > div > h1"));
		boolean existError=ScreenAction.isElementPresent(driver,By.cssSelector("#saas-3522304-overlays > div.v-Notification.error.v-Notification-error > div > div > h1"));
		Assert.assertFalse(existError, "Application has error message");
	}

	@Test(description = "Select Setting icon on Pick Tasks Page  ", dependsOnMethods="accessPickStock",alwaysRun = true)
	public void selectSettingIconOnPickTasksPage() {
		    driver.findElement(By.cssSelector("#menuNavigation > span > span.v-menubar-menuitem-caption > span")).click();
		    driver.findElement(By.xpath("//div[@id='saas-3522304-overlays']/div[2]/div/div/span/span/span")).click();
		 // Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(), "Pick Tasks"); Still error
	}
	
	@Test(description = "Input value for Pick Stock Setting ", dependsOnMethods="selectSettingIconOnPickTasksPage",alwaysRun = true)
	public void inputValueOnPickTasksSettingPage() throws Throwable {	
		//TODO: for sleep
		Thread.sleep(2000);
	    int maxLoop = 0;
		boolean dropDown= true;
		while (maxLoop < 3) {
			  maxLoop += 1;
			  dropDown = ScreenAction.isElementPresent(driver,By.cssSelector("select.v-select-select"));
			  if (dropDown) {
				Thread.sleep(2000);
			    new Select(driver.findElement(By.cssSelector("select.v-select-select"))).selectByVisibleText("<More Data>");
			  } 
			  else {
			    break;
			   }
		  }
		Thread.sleep(3000);
	    new Select(driver.findElement(By.cssSelector("select.v-select-select"))).selectByVisibleText("W001 - WAREHOUSE 1 FOR R100 DST");
		
	    driver.findElement(By.id("assignedToEmployee")).click();
	    // Assert.assertEquals(driver.findElement(By.xpath("//*[@id="login-popup"]/div/div/div[3]/div/div/div[1]/div[1]/span")).getText(), "Search and select"); Still error
	    driver.findElement(By.id("assignedToEmployee.popover.filter")).clear();
	    driver.findElement(By.id("assignedToEmployee.popover.filter")).sendKeys("GP6732");
	    
	    driver.findElement(By.xpath("//*[@id='assignedToEmployee.popover.list']/div[2]/div[1]/table/tbody/tr/td/div")).click();
	    
	    Select orderBy = new Select(driver.findElement(By.cssSelector("#orderBy > select")));
		orderBy.selectByVisibleText("Bin");
		driver.findElement(By.cssSelector("#saveBtn > span > span")).click();
	}
	
	@Test(description = "Show Pick Tasks type ", dependsOnMethods="inputValueOnPickTasksSettingPage",alwaysRun = true)
	public void showPickTasksFollowAssignedTo() {
		// Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(), "Pick Tasks"); Still error
		List<WebElement> stockCode = driver.findElements(By.id("stockCode"));
		List<WebElement> binCode = driver.findElements(By.id("binCode"));
		List<WebElement> quantityToIssue = driver.findElements(By.id("quantityToIssue"));
		List<WebElement> unitOfIssue = driver.findElements(By.id("unitOfIssue"));
		List<WebElement> priorityCode = driver.findElements(By.id("priorityCode"));
		List<WebElement> documentNumber = driver.findElements(By.id("documentNumber"));
		List<WebElement> documentItemNumber = driver.findElements(By.id("documentItemNumber"));
		List<WebElement> isStockItemComplexManaged = driver.findElements(By.id("isStockItemComplexManaged"));
		
		Assert.assertEquals(stockCode.get(0).getAttribute("value"),"JSTEST3");
		Assert.assertEquals(binCode.get(0).getAttribute("value"),"");
		Assert.assertEquals(quantityToIssue.get(0).getAttribute("value"),"1");
		Assert.assertEquals(unitOfIssue.get(0).getAttribute("value"),"EA");
		Assert.assertEquals(priorityCode.get(0).getAttribute("value"),"HIGH");
		Assert.assertEquals(documentNumber.get(0).getAttribute("value"),"E00650");
		Assert.assertEquals(documentItemNumber.get(0).getAttribute("value"),"0002");
		Assert.assertEquals(isStockItemComplexManaged.get(0).getAttribute("value"),"Yes");
		
		Assert.assertEquals(stockCode.get(1).getAttribute("value"),"DS0038");
		Assert.assertEquals(binCode.get(1).getAttribute("value"),"BIN1");
		Assert.assertEquals(quantityToIssue.get(1).getAttribute("value"),"2");
		Assert.assertEquals(unitOfIssue.get(1).getAttribute("value"),"EA");
		Assert.assertEquals(priorityCode.get(1).getAttribute("value"),"HIGH");
		Assert.assertEquals(documentNumber.get(1).getAttribute("value"),"A00244");
		Assert.assertEquals(documentItemNumber.get(1).getAttribute("value"),"0001");
		Assert.assertEquals(isStockItemComplexManaged.get(1).getAttribute("value"),"Yes");
		
	

	}
	
}
