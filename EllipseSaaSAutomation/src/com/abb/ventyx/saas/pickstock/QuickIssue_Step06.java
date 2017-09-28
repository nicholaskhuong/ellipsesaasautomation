package com.abb.ventyx.saas.pickstock;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
@Credentials(user = "SPR002", password = "", district = "R100", position = "MATMAN")
public class QuickIssue_Step06 extends BaseTestCase {

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
	
	@Test(description = "Input WareHouse On Setting Page ", dependsOnMethods="selectSettingIconOnPickTasksPage",alwaysRun = true)
	public void inputWareHouseOnSettingPage() throws Throwable {	
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
	}
	
	@Test(description = "input Order By On Setting Page", dependsOnMethods="inputWareHouseOnSettingPage",alwaysRun = true)
	public void inputOrderByOnSettingPage()  {   
	    Select orderBy = new Select(driver.findElement(By.cssSelector("#orderBy > select")));
		orderBy.selectByVisibleText("Requisition");
	}
	
	@Test(description = "click Apply button On Setting Page", dependsOnMethods="inputOrderByOnSettingPage",alwaysRun = true)
	public void clickApplybuttonOnSettingPage()  {   
		ScreenAction actionScreen =new ScreenAction(driver);
		actionScreen.clickBtn(By.cssSelector("#saveBtn > span > span"));
	}
	
	@Test(description = "Show Pick Tasks type ", dependsOnMethods="clickApplybuttonOnSettingPage",alwaysRun = true)
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
		
		//#3
		Assert.assertEquals(stockCode.get(4).getAttribute("value"),"SAAS003");
		Assert.assertEquals(binCode.get(4).getAttribute("value"),"BIN1");
		Assert.assertEquals(quantityToIssue.get(4).getAttribute("value"),"30");
		Assert.assertEquals(unitOfIssue.get(4).getAttribute("value"),"EA");
		Assert.assertEquals(priorityCode.get(4).getAttribute("value"),"");
		Assert.assertEquals(documentNumber.get(4).getAttribute("value"),"K01159");
		Assert.assertEquals(documentItemNumber.get(4).getAttribute("value"),"0001");
		Assert.assertEquals(isStockItemComplexManaged.get(4).getAttribute("value"),"Yes");
		
		//#1
		Assert.assertEquals(stockCode.get(5).getAttribute("value"),"TYZ1");
		Assert.assertEquals(binCode.get(5).getAttribute("value"),"");
		Assert.assertEquals(quantityToIssue.get(5).getAttribute("value"),"20");
		Assert.assertEquals(unitOfIssue.get(5).getAttribute("value"),"EA");
		Assert.assertEquals(priorityCode.get(5).getAttribute("value"),"");
		Assert.assertEquals(documentNumber.get(5).getAttribute("value"),"K01162");
		Assert.assertEquals(documentItemNumber.get(5).getAttribute("value"),"0001");
		Assert.assertEquals(isStockItemComplexManaged.get(5).getAttribute("value"),"Yes");
		
	}
}
