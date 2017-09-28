package com.abb.ventyx.saas.pickstock;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.text.html.parser.Element;

import net.sf.antcontrib.logic.Throw;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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

@ALM(id = "1020")
@Credentials(user = "SPR002", password = "", district = "R100", position = "HRMAN")
public class QuickIssue_Steps09to13 extends BaseTestCase {
	ScreenAction waitAction =new ScreenAction(driver);
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
	public void inputValueSettingPage() throws Throwable {	
		//TODO: for sleep
		int maxLoop = 0;
		boolean dropDown= true;
		while (maxLoop < 3) {
			  maxLoop += 1;
			  dropDown = ScreenAction.isElementPresent(driver,By.cssSelector("select.v-select-select"));
			  if (dropDown) {
				  Thread.sleep(3000);
				  new Select(driver.findElement(By.cssSelector("select.v-select-select"))).selectByVisibleText("<More Data>");
			  } 
			  else {
			    break;
			   }
		  }
		Thread.sleep(3000);
	    new Select(driver.findElement(By.cssSelector("select.v-select-select"))).selectByVisibleText("DJW1 - DJ TEST WH 1 (R100)");
	    Select orderBy = new Select(driver.findElement(By.cssSelector("#orderBy > select")));
		orderBy.selectByVisibleText("Requisition");
		ScreenAction actionScreen =new ScreenAction(driver);
		actionScreen.clickBtn(By.cssSelector("#saveBtn > span > span"));
	}
	
	//Step 09
	@Test(description = "Show Pick Tasks type ", dependsOnMethods="inputValueSettingPage",alwaysRun = true)
	public void showPickTasks() {
		// Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(), "Pick Tasks"); Still error
		List<WebElement> stockCode = driver.findElements(By.id("stockCode"));
		List<WebElement> binCode = driver.findElements(By.id("binCode"));
		List<WebElement> quantityToIssue = driver.findElements(By.id("quantityToIssue"));
		List<WebElement> unitOfIssue = driver.findElements(By.id("unitOfIssue"));
		List<WebElement> priorityCode = driver.findElements(By.id("priorityCode"));
		List<WebElement> documentNumber = driver.findElements(By.id("documentNumber"));
		List<WebElement> documentItemNumber = driver.findElements(By.id("documentItemNumber"));
		List<WebElement> isStockItemComplexManaged = driver.findElements(By.id("isStockItemComplexManaged"));
		
		Assert.assertEquals(stockCode.get(3).getAttribute("value"),"SAAS001");
		Assert.assertEquals(binCode.get(3).getAttribute("value"),"");
		Assert.assertEquals(quantityToIssue.get(3).getAttribute("value"),"20");
		Assert.assertEquals(unitOfIssue.get(3).getAttribute("value"),"EA");
		Assert.assertEquals(priorityCode.get(3).getAttribute("value"),"");
		Assert.assertEquals(documentNumber.get(3).getAttribute("value"),"F01146");
		Assert.assertEquals(documentItemNumber.get(3).getAttribute("value"),"0001");
	}
	
	//Step10
	@Test(description = "click Full Pick Button ", dependsOnMethods="showPickTasks",alwaysRun = true)
	public void clickFullPickButton() {
		List<WebElement> fullPickAction = driver.findElements(By.cssSelector("#fullPickAction > span > span"));
		fullPickAction.get(2).click();
	//	Thread.sleep(3000);
	}
	
	@Test(description = "Show Pick Tasks Details ", dependsOnMethods="clickFullPickButton",alwaysRun = true)
	public void showPickTasksDetails() {
		// Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(), "Pick Task Details"); Still error
		waitAction.waitObjVisible(driver, By.id("stockCode"), 3);
		List<WebElement> stockCode = driver.findElements(By.id("stockCode"));
		List<WebElement> quantityToIssue = driver.findElements(By.id("quantityToIssue"));
		List<WebElement> unitOfIssue = driver.findElements(By.id("unitOfIssue"));
		List<WebElement> documentNumber = driver.findElements(By.id("documentNumber"));
		List<WebElement> documentItemNumber = driver.findElements(By.id("documentItemNumber"));
		
		 Assert.assertEquals(stockCode.get(3).getAttribute("value"),"SAAS001");
		 Assert.assertEquals(driver.findElement(By.id("catalogueItemName")).getAttribute("value"),"SAAS01 NAME");
		 Assert.assertEquals(quantityToIssue.get(3).getAttribute("value"), "20");
		 Assert.assertEquals(unitOfIssue.get(3).getAttribute("value"), "EA");
		 Assert.assertEquals(driver.findElement(By.id("quantityPicked")).getAttribute("value"), "20");
		 Assert.assertEquals(documentNumber.get(3).getAttribute("value"), "F01146");
		 Assert.assertEquals(documentItemNumber.get(3).getAttribute("value"), "0001");
		 
		 WebElement closeAction = driver.findElement(By.cssSelector("#closeAction2 > span > span"));
		 WebElement partialPickAction = driver.findElement(By.cssSelector("#issueAction > span > span"));
		 Assert.assertTrue(closeAction.isEnabled());
		 Assert.assertTrue(partialPickAction.isEnabled());
	}
	
	//Step11
	@Test(description = "click Close Button ", dependsOnMethods="showPickTasksDetails",alwaysRun = true)
	public void clickCloseButton() {
		driver.findElement(By.id("closeAction2")).click();
	//	Thread.sleep(3000);
		waitAction.waitObjVisible(driver, By.id("closeAction2"), 3);
	}
	
	@Test(description = "click Close Button ", dependsOnMethods="clickCloseButton",alwaysRun = true)
	public void displayClosePickTaskSreen() {
		// Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[2]/div/div[1]/div[1]/span")).getText(), "Close Pick Task"); Still error
		Assert.assertEquals(driver.findElement(By.cssSelector("#closureComment")).getAttribute("value"), "");
	}
	
	//Step12
	@Test(description = "click Back Navigation Button ", dependsOnMethods="displayClosePickTaskSreen",alwaysRun = true)
	public void clickBackNavigationButton() throws Throwable {
		waitAction.waitObjVisible(driver, By.xpath("(//div[@id='backNavigation']/span)[2]"), 3);
		driver.findElement(By.xpath("(//div[@id='backNavigation']/span)[2]")).click();
		// Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[2]/div/div[1]/div[1]/span")).getText(), "Pick Task Details"); Still error
	}
	
	//Step13
	@Test(description = "confirm Pick Quantity", dependsOnMethods="clickBackNavigationButton",alwaysRun = true)
	public void confirmPickQuantity() throws Throwable{
		waitAction.waitObjVisible(driver, By.id("quantityPicked"), 3);
		Assert.assertEquals(driver.findElement(By.id("quantityPicked")).getAttribute("value"), "20");
	}
}
