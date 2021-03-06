package com.abb.ventyx.saas.pickstock;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.PickTasksPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.PickTasksSettingPageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1021")
@Credentials(user = "SPR002", password = "", district = "R100", position = "MATMAN")
public class QuickIssue_PartialQty_Step04 extends BaseTestCase {

	@Test(description = "Access to Pick Stock Application")
	public void accessPickStock() {
		loginToApplication(ApplicationName.PICK_STOCK);
		Assert.assertEquals(driver.findElement(By.xpath(PickTasksPageDefinition.PICK_TASKS_TEXT_ID)).getText(), "Pick Tasks");
		ScreenAction errorAction = new ScreenAction(driver);
		errorAction.clickTapToClose(driver,By.cssSelector(PickTasksPageDefinition.MESSAGE_TEXT_ID));
		boolean existError = ScreenAction.isElementPresent(driver,By.cssSelector(PickTasksPageDefinition.MESSAGE_TEXT_ID));
		Assert.assertFalse(existError, "Application has error message");
	}

	@Test(description = "Select Setting icon on Pick Tasks Page  ", dependsOnMethods = "accessPickStock", alwaysRun = true)
	public void selectSettingIconOnPickTasksPage() {
		driver.findElement(By.cssSelector(PickTasksPageDefinition.MENU_NAVIGATION_ID)).click();
		driver.findElement(By.xpath(PickTasksPageDefinition.ICON_SETTING_BUTON_ID)).click();
		// Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(),"Pick Tasks"); Still error
	}

	@Test(description = "Input WareHouse On Setting Page ", dependsOnMethods = "selectSettingIconOnPickTasksPage", alwaysRun = true)
	public void inputWareHouseOnSettingPage() throws Throwable {
		// TODO: for sleep
		int maxLoop = 0;
		boolean dropDown = true;
		while (maxLoop < 3) {
			maxLoop += 1;
				dropDown = ScreenAction.isElementPresent(driver,By.cssSelector(PickTasksSettingPageDefinition.WARE_HOUSE_DROPDOWN_ID));
				if (dropDown) {
					Thread.sleep(3000);
					new Select(driver.findElement(By.cssSelector(PickTasksSettingPageDefinition.WARE_HOUSE_DROPDOWN_ID))).selectByVisibleText("<More Data>");
				} else {
					break;
				}
			}
		Thread.sleep(3000);
		new Select(driver.findElement(By.cssSelector(PickTasksSettingPageDefinition.WARE_HOUSE_DROPDOWN_ID))).selectByVisibleText("HB01 - Heidy's Warehouse");
	
	}
	@Test(description = "input Order By On Setting Page", dependsOnMethods = "inputWareHouseOnSettingPage", alwaysRun = true)
	public void inputOrderByOnSettingPage() {
		Select orderBy = new Select(driver.findElement(By.cssSelector(PickTasksSettingPageDefinition.ORDER_BY_DROPDOWN_ID)));
		orderBy.selectByVisibleText("Requisition");
	}

	@Test(description = "click Apply button On Setting Page", dependsOnMethods = "inputOrderByOnSettingPage", alwaysRun = true)
	public void clickApplybuttonOnSettingPage() {
		screenAction.clickBtn(By.id(PickTasksSettingPageDefinition.APPLY_BUTTON_ID));
	}

	@Test(description = "Show Pick Tasks type ", dependsOnMethods = "clickApplybuttonOnSettingPage", alwaysRun = true)
	public void showPickTasks() {
		// Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(),"Pick Tasks"); Still error
		List<WebElement> stockCode = driver.findElements(By.id(PickTasksPageDefinition.STOCK_CODE_ID));
		List<WebElement> binCode = driver.findElements(By.id(PickTasksPageDefinition.BIN_CODE_ID));
		List<WebElement> quantityToIssue = driver.findElements(By.id(PickTasksPageDefinition.QUANTITY_TO_PICK_ID));
		List<WebElement> unitOfIssue = driver.findElements(By.id(PickTasksPageDefinition.UNIT_OF_ISSUE_ID));
		List<WebElement> documentNumber = driver.findElements(By.id(PickTasksPageDefinition.DOCUMENT_NUMBER_ID));
		List<WebElement> documentItemNumber = driver.findElements(By.id(PickTasksPageDefinition.DOCUMENT_ITEM_NUMBER_ID));

		// #1
		Assert.assertEquals(stockCode.get(0).getAttribute("value"), "TC1020Q");
		Assert.assertEquals(binCode.get(0).getAttribute("value"), "BIN");
		Assert.assertEquals(quantityToIssue.get(0).getAttribute("value"), "30");
		Assert.assertEquals(unitOfIssue.get(0).getAttribute("value"), "EA");
		Assert.assertEquals(documentNumber.get(0).getAttribute("value"),"C01186");
		Assert.assertEquals(documentItemNumber.get(0).getAttribute("value"),"0001");

		Assert.assertEquals(stockCode.get(1).getAttribute("value"), "A00001109");
		Assert.assertEquals(binCode.get(1).getAttribute("value"), "BIN");
		Assert.assertEquals(quantityToIssue.get(1).getAttribute("value"), "30");
		Assert.assertEquals(unitOfIssue.get(1).getAttribute("value"), "EA");
		Assert.assertEquals(documentNumber.get(1).getAttribute("value"),"D01089");
		Assert.assertEquals(documentItemNumber.get(1).getAttribute("value"),"0001");
		
		// #2
		Assert.assertEquals(stockCode.get(2).getAttribute("value"), "TC1020B");
		Assert.assertEquals(binCode.get(2).getAttribute("value"), "BIN1");
		Assert.assertEquals(quantityToIssue.get(2).getAttribute("value"), "30");
		Assert.assertEquals(unitOfIssue.get(2).getAttribute("value"), "EA");
		Assert.assertEquals(documentNumber.get(2).getAttribute("value"),"H01138");
		Assert.assertEquals(documentItemNumber.get(2).getAttribute("value"),"0001");
		
		// #3
		Assert.assertEquals(stockCode.get(3).getAttribute("value"), "STC041");
		Assert.assertEquals(binCode.get(3).getAttribute("value"), "");
		Assert.assertEquals(quantityToIssue.get(3).getAttribute("value"), "1,050");
		Assert.assertEquals(unitOfIssue.get(3).getAttribute("value"), "EA");
		Assert.assertEquals(documentNumber.get(3).getAttribute("value"),"J01178");
		Assert.assertEquals(documentItemNumber.get(3).getAttribute("value"),"0001");
	}
}
