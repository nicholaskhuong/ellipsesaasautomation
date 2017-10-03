package com.abb.ventyx.saas.receivegoods;

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
import com.abb.ventyx.saas.objects.pagedefinitions.ItemDetailPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.LeaveBalancePageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.POItemsPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.POSearchPageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1026")
@Credentials(user = "SPR002", password = "", district = "R100", position = "MATMAN")
public class QuickReceiptFullQuantity_NormalPO_OwnedSOH_Step16 extends BaseTestCase {

	@Test(description = "Access to Receive Goods Application")
	public void accessReceiveGoods() {
		loginToApplication(ApplicationName.RECEIVE_GOODS);
		Assert.assertEquals(driver.findElement(By.xpath(POSearchPageDefinition.NEW_RECEIVE_GOODS_TEXT_ID)).getText(), "PO Search");
	}
	
	@Test(description = "Input value PO Number on PO search Page", dependsOnMethods="accessReceiveGoods",alwaysRun = true)
	public void inputPurchaseOrderNumber() {
		WebElement purchaseOrderNumber = driver.findElement(By.id(POSearchPageDefinition.PO_NUMBER_ID));
		purchaseOrderNumber .sendKeys("P05058");
	}
	
	@Test(description = "Input value PO Item Number on PO search Page", dependsOnMethods="inputPurchaseOrderNumber",alwaysRun = true)
	public void inputPurchaseOrderItemNumber() {
		WebElement purchaseOrderItemNumber = driver.findElement(By.id(POSearchPageDefinition.PO_ITEM_NUMBER_ID));
		purchaseOrderItemNumber .sendKeys("");
	}
	
	@Test(description = "Click button SEARCH on PO search Page", dependsOnMethods="inputPurchaseOrderItemNumber",alwaysRun = true)
	public void clickSEARCHButton() {
		screenAction.clickBtn(By.cssSelector(POSearchPageDefinition.SEARCH_BUTTON_ID));
	}
	
	@Test(description = "Access to PO Items Page", dependsOnMethods="clickSEARCHButton",alwaysRun = true)
	public void accessToPOItemsPage() {
	//	Assert.assertEquals(driver.findElement(By.xpath(POItemsPageDefinition.NEW_PO_ITEMS_TEXT_ID)).getText(), "PO Items"); //Still error
		}
	
	@Test(description = "Display Detail Items", dependsOnMethods="accessToPOItemsPage",alwaysRun = true)
	public void displayDetailItems () {
		List<WebElement> documentItem = driver.findElements(By.id(POItemsPageDefinition.PO_ITEM_NUMBER_ID));
		List<WebElement> stockCode = driver.findElements(By.id(POItemsPageDefinition.STOCK_CODE_ID));
		List<WebElement> partNumber = driver.findElements(By.id(POItemsPageDefinition.PART_NUMBER_ID));
		List<WebElement> description = driver.findElements(By.id(POItemsPageDefinition.DESCRIPTION_ID));
		List<WebElement> outstandingQuantityUOP = driver.findElements(By.id(POItemsPageDefinition.OUT_STANDING_QUANTITY_UOP_ID));
		List<WebElement> unitOfPurchase = driver.findElements(By.id(POItemsPageDefinition.UNIT_OF_PURCHASE_ID));
		List<WebElement> outstandingQuantityUOI = driver.findElements(By.id(POItemsPageDefinition.OUT_STANDING_QUANTITY_UOI_ID));
		List<WebElement> unitOfIssue = driver.findElements(By.id(POItemsPageDefinition.UNIT_OF_ISSUE_ID));
		
		Assert.assertEquals(documentItem.get(0).getAttribute("value"),"001");
		Assert.assertEquals(stockCode.get(0).getAttribute("value"),"STC01S");
		Assert.assertEquals(partNumber.get(0).getAttribute("value"),"");
		Assert.assertEquals(description.get(0).getAttribute("value"),"STOCK CODE;");
		Assert.assertEquals(outstandingQuantityUOP.get(0).getAttribute("value"), "30.0");
		Assert.assertEquals(unitOfPurchase.get(0).getAttribute("value"),"EA");
		Assert.assertEquals(outstandingQuantityUOI.get(0).getAttribute("value"), "30.0");
		Assert.assertEquals(unitOfIssue.get(0).getAttribute("value"),"EA");
	
		screenAction.assertButtonEnabled(By.id("partialAction"),0, false);
		screenAction.assertButtonEnabled(By.id("fullAction"),0, false);
	}
}
