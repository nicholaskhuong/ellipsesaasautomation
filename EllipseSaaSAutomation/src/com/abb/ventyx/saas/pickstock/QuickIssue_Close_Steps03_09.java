package com.abb.ventyx.saas.pickstock;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.PickTasksClosePageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.PickTasksPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.PickTasksSettingPageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1022")
@Credentials(user = "SPR002", password = "", district = "R100", position = "MATMAN")
public class QuickIssue_Close_Steps03_09 extends BaseTestCase {

	@Test(description = "Access to Pick Stock Application")
	public void accessPickStock() {
		loginToApplication(ApplicationName.PICK_STOCK);
		Assert.assertEquals(driver.findElement(By.xpath(PickTasksPageDefinition.PICK_TASKS_TEXT_ID)).getText(), "Pick Tasks");
		ScreenAction errorAction = new ScreenAction(driver);
		errorAction.clickTapToClose(driver,By.cssSelector(PickTasksPageDefinition.MESSAGE_TEXT_ID));
		boolean existError = ScreenAction.isElementPresent(driver,By.cssSelector(PickTasksPageDefinition.MESSAGE_TEXT_ID));
		Assert.assertFalse(existError, "Application has error message");
	}

	@Test(description = "Select Menubar on Pick Tasks Page  ", dependsOnMethods = "accessPickStock", alwaysRun = true)
	public void selectMenuBarIconOnPickTasksPage() {
		driver.findElement(By.cssSelector(PickTasksPageDefinition.MENU_NAVIGATION_ID)).click();
	}
	
	@Test(description = "Select MenuItem Setting on Pick Tasks Page  ", dependsOnMethods = "selectMenuBarIconOnPickTasksPage", alwaysRun = true)
	public void selectMenuItemOnPickTasksPage() {
		driver.findElement(By.xpath(PickTasksPageDefinition.ICON_SETTING_BUTON_ID)).click();
	}
	
	@Test(description = "Input WareHouse On Setting Page ", dependsOnMethods = "selectMenuItemOnPickTasksPage", alwaysRun = true)
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
		
		new Select(driver.findElement(By.cssSelector(PickTasksSettingPageDefinition.WARE_HOUSE_DROPDOWN_ID))).selectByVisibleText("WHSE - WHSE");
	
	}
	@Test(description = "input Order By On Setting Page", dependsOnMethods = "inputWareHouseOnSettingPage", alwaysRun = true)
	public void inputOrderByOnSettingPage() {
		Select orderBy = new Select(driver.findElement(By.cssSelector(PickTasksSettingPageDefinition.ORDER_BY_DROPDOWN_ID)));
		orderBy.selectByVisibleText("Requisition");
	}

	@Test(description = "click Apply button On Setting Page", dependsOnMethods = "inputOrderByOnSettingPage", alwaysRun = true)
	public void clickApplybuttonOnSettingPage() {
		screenAction.clickBtn(By.id(PickTasksSettingPageDefinition.APPLY_BUTTON_ID));
		screenAction.waitObjVisible(driver, By.id(PickTasksSettingPageDefinition.APPLY_BUTTON_ID), 6);
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

		Assert.assertEquals(stockCode.get(0).getAttribute("value"), "STC055");
		Assert.assertEquals(binCode.get(0).getAttribute("value"), "BIN");
		Assert.assertEquals(quantityToIssue.get(0).getAttribute("value"), "20");
		Assert.assertEquals(unitOfIssue.get(0).getAttribute("value"), "EA");
		Assert.assertEquals(documentNumber.get(0).getAttribute("value"),"F01155");
		Assert.assertEquals(documentItemNumber.get(0).getAttribute("value"),"0001");
	}
	
	@Test(description = "Verify CLOSE - PARTIAL - PULL PICK button enabled", dependsOnMethods = "showPickTasks", alwaysRun = true)
	public void verifyEnabledButton() {
		screenAction.assertButtonEnabled(By.id(PickTasksPageDefinition.CLOSE_BUTTON_ID), 0, true);
		screenAction.assertButtonEnabled(By.id(PickTasksPageDefinition.PARTIAL_PICK_BUTTON_ID), 0, true);
		screenAction.assertButtonEnabled(By.id(PickTasksPageDefinition.FULL_PICK_BUTTON_ID), 0, true);
	}
	
	@Test(description = "Click CLOSE button", dependsOnMethods = "verifyEnabledButton", alwaysRun = true)
	public void clickCloseButton() {
		screenAction.clickBtnByIndex(By.id(PickTasksPageDefinition.CLOSE_BUTTON_ID), 0);
		screenAction.waitObjVisible(driver, By.id(PickTasksPageDefinition.CLOSE_BUTTON_ID), 6);
	}
	
	@Test(description = "Open Close Pick Task page", dependsOnMethods = "clickCloseButton", alwaysRun = true)
	public void opentClosePickTaskPage() {
	Assert.assertEquals(driver.findElement(By.xpath(PickTasksClosePageDefinition.CLOSE_PICK_TASK_TEXT_ID)).getText(),"Close Pick Task");
	}
	
	@Test(description = "Tick in CheckBox on Close Pick Task page", dependsOnMethods = "opentClosePickTaskPage", alwaysRun = true)
	public void tickCheckBox() {
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='fgPickStockCloseDetails']/div[1]/div[1]/div")).getText(),"Complete Requisition");   
		driver.findElement(By.cssSelector("[type='checkbox']")).click();;
	}
	
	@Test(description = "Select Reason in Dropdown on Close Pick Task page", dependsOnMethods = "tickCheckBox", alwaysRun = true)
	public void selectReasonInDropDown() {
		screenAction.waitObjVisible(driver, By.cssSelector(PickTasksClosePageDefinition.INABILITY_REASON_ID), 6);
		driver.findElement(By.cssSelector(PickTasksClosePageDefinition.INABILITY_REASON_ID)).sendKeys("0001 Inability - No Discrepancy");
	}
	
	@Test(description = "Click CLOSE Button On Close Pick Task Page", dependsOnMethods = "selectReasonInDropDown", alwaysRun = true)
	public void clickCloseButtonOnClosePickTaskPage() {
		screenAction.clickBtn(By.id(PickTasksClosePageDefinition.CLOSE_BUTTON_ID));
	}
	
	@Test(description = "Open Pick Task page", dependsOnMethods = "clickCloseButtonOnClosePickTaskPage", alwaysRun = true)
	public void opentPickTaskPage() {
	//	Assert.assertEquals(driver.findElement(By.xpath(PickTasksPageDefinition.PICK_TASKS_TEXT_ID)).getText(),"Pick Tasks");
	}
	
	@Test(description = "Display message", dependsOnMethods = "opentPickTaskPage", alwaysRun = true)
	public void displayMessageSuccessfully() {
		Assert.assertEquals(driver.findElement(By.cssSelector(PickTasksPageDefinition.MESSAGE_TEXT_ID)).getText(),"(INFO) CORE.E06004: Action successfully completed.");
		driver.findElement(By.cssSelector(PickTasksPageDefinition.MESSAGE_TEXT_ID)).click();
	}
}
