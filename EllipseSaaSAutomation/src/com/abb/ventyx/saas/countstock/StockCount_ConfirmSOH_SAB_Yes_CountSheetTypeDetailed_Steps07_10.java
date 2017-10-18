package com.abb.ventyx.saas.countstock;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.CountActivitiesDetaiksPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.CountActivitiesPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.PickTasksPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.SettingCountActivitiesPageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1035")
@Credentials(user = "LD3638", password = "", district = "R100", position = "SYSAD")
public class StockCount_ConfirmSOH_SAB_Yes_CountSheetTypeDetailed_Steps07_10 extends BaseTestCase {

	@Test(description = "Access to Count Stock Application")
	public void accessCountStock() {
		loginToApplication(ApplicationName.COUNT_STOCK);
		Assert.assertEquals(driver.findElement(By.xpath(CountActivitiesPageDefinition.COUNT_ACTIVITIES_TEXT_ID)).getText(), "Count Activities");
	}

	@Test(description = "Display Message on Count Stock Page  ", dependsOnMethods = "accessCountStock", alwaysRun = true)
	public void displayMessageOnCountStockPage() {
		screenAction.waitObjVisible(driver, By.cssSelector(CountActivitiesPageDefinition.MESSAGE_TEXT_ID), 6);
		Assert.assertEquals(driver.findElement(By.cssSelector(CountActivitiesPageDefinition.MESSAGE_TEXT_ID)).getText(), "The search did not return any results");
		driver.findElement(By.cssSelector(PickTasksPageDefinition.MESSAGE_TEXT_ID)).click();
	}
	
	@Test(description = "Select Menubar on Count Stock Page  ", dependsOnMethods = "displayMessageOnCountStockPage", alwaysRun = true)
	public void selectMenuBarIconOnCountStockPage() {
		driver.findElement(By.cssSelector(CountActivitiesPageDefinition.MENU_NAVIGATION_ID)).click();
	}
	
	@Test(description = "Select MenuItem Setting on Count Stock Page  ", dependsOnMethods = "selectMenuBarIconOnCountStockPage", alwaysRun = true)
	public void selectMenuItemOnCountStockPage() {
		driver.findElement(By.xpath(CountActivitiesPageDefinition.ICON_SETTING_BUTON_ID)).click();
	}
	
	@Test(description = "Input WareHouse On Setting Page ", dependsOnMethods = "selectMenuItemOnCountStockPage", alwaysRun = true)
	public void inputWareHouseOnSettingPage() throws Throwable {
		// TODO: for sleep
		int maxLoop = 0;
		boolean dropDown = true;
		while (maxLoop < 3) {
			maxLoop += 1;
				dropDown = ScreenAction.isElementPresent(driver,By.cssSelector(SettingCountActivitiesPageDefinition.WARE_HOUSE_DROPDOWN_ID));
				if (dropDown) {
					Thread.sleep(3000);
					new Select(driver.findElement(By.cssSelector(SettingCountActivitiesPageDefinition.WARE_HOUSE_DROPDOWN_ID))).selectByVisibleText("<More Data>");
				} else {
					break;
				}
			}
		Thread.sleep(3000);
		
		new Select(driver.findElement(By.cssSelector(SettingCountActivitiesPageDefinition.WARE_HOUSE_DROPDOWN_ID))).selectByVisibleText("ROVE - Rock Vegas");
	
	}

	@Test(description = "click Apply button On Setting Page", dependsOnMethods = "inputWareHouseOnSettingPage", alwaysRun = true)
	public void clickApplybuttonOnSettingPage() {
		screenAction.clickBtn(By.id(SettingCountActivitiesPageDefinition.APPLY_BUTTON_ID));
		screenAction.waitObjVisible(driver, By.id(SettingCountActivitiesPageDefinition.APPLY_BUTTON_ID), 5);
	}

	@Test(description = "Show Count Activities type ", dependsOnMethods = "clickApplybuttonOnSettingPage", alwaysRun = true)
	public void showCountActivites() {
		// Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(),"Count Activities"); Still error
		screenAction.waitObjVisible(driver, By.id(CountActivitiesPageDefinition.BIN_ID), 3);
		List<WebElement> bin = driver.findElements(By.id(CountActivitiesPageDefinition.BIN_ID));
		List<WebElement> stockCode = driver.findElements(By.id(CountActivitiesPageDefinition.STOCK_CODE_ID));
		List<WebElement> description = driver.findElements(By.id(CountActivitiesPageDefinition.DESCRIPTION_ID));
		List<WebElement> stockOnHand = driver.findElements(By.id(CountActivitiesPageDefinition.STOCK_ON_HAND_ID));
		
    	Assert.assertEquals(bin.get(0).getAttribute("value"), "BIN1");
		Assert.assertEquals(stockCode.get(0).getAttribute("value"), "STC41");
		Assert.assertEquals(description.get(0).getAttribute("value"), "STOCK CODE;");
		Assert.assertEquals(stockOnHand.get(0).getAttribute("value"), "250");

	}
	

	@Test(description = "Click ENTER COUNT QTY button for BIN1", dependsOnMethods = "showCountActivites", alwaysRun = true)
	public void clickENTERCOUNTQTYButton() {
		screenAction.clickBtnByIndex(By.cssSelector(CountActivitiesPageDefinition.ENTER_COUNT_QTY_ID), 0);
		screenAction.waitObjVisible(driver,By.cssSelector(CountActivitiesPageDefinition.ENTER_COUNT_QTY_ID), 3);
	}
	
	@Test(description = "Show Count Activity Details type ", dependsOnMethods = "clickENTERCOUNTQTYButton", alwaysRun = true)
	public void showCountActivityDetails() {
		// Assert.assertEquals(driver.findElement(By.xpath("//*[@id='saas-3522304']/div/div[2]/div/div[1]/div/div[1]/div[1]/span")).getText(),"Count Activity Details"); Still error
		
		List<WebElement> stockCode = driver.findElements(By.id(CountActivitiesDetaiksPageDefinition.STOCK_CODE_ID));
		List<WebElement> description = driver.findElements(By.id(CountActivitiesDetaiksPageDefinition.DESCRIPTION_ID));
		List<WebElement> bin = driver.findElements(By.id(CountActivitiesDetaiksPageDefinition.BIN_ID));
		
    	Assert.assertEquals(bin.get(0).getAttribute("value"), "BIN1");
		Assert.assertEquals(stockCode.get(0).getAttribute("value"), "STC41");
		Assert.assertEquals(description.get(0).getAttribute("value"), "STOCK CODE;");
		Assert.assertEquals(driver.findElement(By.id(CountActivitiesDetaiksPageDefinition.COUNT_QTY_ID)).getAttribute("value"), "");
		Assert.assertEquals(driver.findElement(By.id(CountActivitiesDetaiksPageDefinition.UNIT_OF_MEASURE_ID)).getAttribute("value"), "EA");
		Assert.assertEquals(driver.findElement(By.id(CountActivitiesDetaiksPageDefinition.BIN_SOH_ID)).getAttribute("value"), "250");
		Assert.assertEquals(driver.findElement(By.id(CountActivitiesDetaiksPageDefinition.STOCKTAKE_NUMBER_ID)).getAttribute("value"), "001789");
	}
	
	@Test(description = "Click Back navigation Button On Count Activity Details Page", dependsOnMethods = "showCountActivityDetails", alwaysRun = true)
	public void clickBackNavigationButton() {
		screenAction.waitObjVisible(driver, By.cssSelector(CountActivitiesDetaiksPageDefinition.BACK_NAVIGATION_ID), 3);
		screenAction.clickBtn(By.cssSelector(CountActivitiesDetaiksPageDefinition.BACK_NAVIGATION_ID));
	}
	
	@Test(description = "Click CONFIRM SOH Button On Count Activity Page for BIN1", dependsOnMethods = "clickBackNavigationButton", alwaysRun = true)
	public void clickCONFIRMButton() {
		screenAction.waitObjVisible(driver, By.cssSelector(CountActivitiesPageDefinition.CONFIRM_SOH_ID), 3);
		screenAction.clickBtnByIndex(By.cssSelector(CountActivitiesPageDefinition.CONFIRM_SOH_ID), 0);
		screenAction.waitObjVisible(driver, By.cssSelector(CountActivitiesPageDefinition.CONFIRM_SOH_ID), 3);
	}
	
	@Test(description = "Display message Success for BIN1", dependsOnMethods = "clickCONFIRMButton", alwaysRun = true)
	public void displayMessageSuccessfully() {
		screenAction.waitObjVisible(driver, By.cssSelector("#saas-3522304-overlays > div.v-Notification.humanized.v-Notification-humanized > div"), 6);
		Assert.assertEquals(driver.findElement(By.cssSelector("#saas-3522304-overlays > div.v-Notification.humanized.v-Notification-humanized > div")).getText(),"(INFO) CORE.E06004: Action successfully completed.");
		driver.findElement(By.cssSelector("#saas-3522304-overlays > div.v-Notification.humanized.v-Notification-humanized > div")).click();
	}
	}
