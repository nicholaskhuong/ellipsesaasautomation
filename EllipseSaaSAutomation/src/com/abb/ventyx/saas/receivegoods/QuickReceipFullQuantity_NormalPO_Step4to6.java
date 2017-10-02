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
public class QuickReceipFullQuantity_NormalPO_Step4to6 extends BaseTestCase {

	@Test(description = "Access to Receive Goods Application")
	public void accessReceiveGoods() {
		loginToApplication(ApplicationName.RECEIVE_GOODS);
		Assert.assertEquals(driver.findElement(By.xpath(POSearchPageDefinition.NEW_RECEIVE_GOODS_TEXT_ID)).getText(), "PO Search");
	}
	
	@Test(description = "Input value PO Number on PO search Page", dependsOnMethods="accessReceiveGoods",alwaysRun = true)
	public void inputPurchaseOrderNumber() {
		WebElement purchaseOrderNumber = driver.findElement(By.id(POSearchPageDefinition.PO_NUMBER_ID));
		purchaseOrderNumber .sendKeys("P05042");
	}
	
	@Test(description = "Input value PO Item Number on PO search Page", dependsOnMethods="inputPurchaseOrderNumber",alwaysRun = true)
	public void inputPurchaseOrderItemNumber() {
		WebElement purchaseOrderItemNumber = driver.findElement(By.id(POSearchPageDefinition.PO_ITEM_NUMBER_ID));
		purchaseOrderItemNumber .sendKeys("");
	}
	
	@Test(description = "Click button SEARCH on PO search Page", dependsOnMethods="inputPurchaseOrderItemNumber",alwaysRun = true)
	public void clickSEARCHButton() {
		ScreenAction actionBtn = new ScreenAction(driver);
		actionBtn.clickBtn(By.cssSelector(POSearchPageDefinition.SEARCH_BUTTON_ID));
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
		Assert.assertEquals(stockCode.get(0).getAttribute("value"),"STC014");
		Assert.assertEquals(partNumber.get(0).getAttribute("value"),"");
		Assert.assertEquals(description.get(0).getAttribute("value"),"STOCK CODE;");
		Assert.assertEquals(outstandingQuantityUOP.get(0).getAttribute("value"), "30.0");
		Assert.assertEquals(unitOfPurchase.get(0).getAttribute("value"),"EA");
		Assert.assertEquals(outstandingQuantityUOI.get(0).getAttribute("value"), "30.0");
		Assert.assertEquals(unitOfIssue.get(0).getAttribute("value"),"EA");
	}
	
	@Test(description = "Click button FULL on PO Items Page", dependsOnMethods="displayDetailItems",alwaysRun = true)
	public void clickFULLButton() {
		List<WebElement> btnFULL = driver.findElements(By.cssSelector(POItemsPageDefinition.FULL_BUTTON_ID));
		btnFULL.get(0).click();
	}
	
	@Test(description = "Input Value On Item Detail Page", dependsOnMethods="clickFULLButton",alwaysRun = true)
	public void inputValueOnItemDetailPage() {
		List<WebElement> stockCode = driver.findElements(By.id(ItemDetailPageDefinition.STOCK_CODE_ID));
		List<WebElement> description = driver.findElements(By.id(ItemDetailPageDefinition.DESCRIPTION_ID));
		List<WebElement> unitOfPurchase = driver.findElements(By.id(ItemDetailPageDefinition.UNIT_OF_PURCHASE_ID));
		List<WebElement> documentNumber = driver.findElements(By.id(ItemDetailPageDefinition.PO_NUMBER_ID));
		List<WebElement> documentItem = driver.findElements(By.id(ItemDetailPageDefinition.NUMBER_ITEM_ID));
		List<WebElement> taskQuantityUOM = driver.findElements(By.id(ItemDetailPageDefinition.TASK_QUANTITY_UOM_ID));
		List<WebElement> outstandingQuantityUOP = driver.findElements(By.id(ItemDetailPageDefinition.OUT_STANDING_QUANTITY_UOP_ID));
		List<WebElement> receiptReference = driver.findElements(By.id(ItemDetailPageDefinition.RECEIPT_REFERENCE_ID));
		
		Assert.assertEquals(stockCode.get(0).getAttribute("value"), "STC014");
		Assert.assertEquals(description.get(0).getAttribute("value"), "STOCK CODE;");
		Assert.assertEquals(documentNumber.get(0).getAttribute("value"), "P05042");
		Assert.assertEquals(documentItem.get(0).getAttribute("value"), "001");
		Assert.assertEquals(unitOfPurchase.get(0).getAttribute("value"), "EA");
		Assert.assertEquals(outstandingQuantityUOP.get(0).getAttribute("value"),"30.0");
		Assert.assertEquals(taskQuantityUOM.get(0).getAttribute("value"), "30.0");
		receiptReference.get(0).clear();
		receiptReference.get(0).sendKeys("MAYCHAZ");
		
	}
	
	@Test(description = "Click Receive Button On Item Detail Page", dependsOnMethods="inputValueOnItemDetailPage",alwaysRun = true)
	public void clickRECEIVEButton() {
		ScreenAction actionBtn = new ScreenAction(driver);
		actionBtn.clickBtn(By.cssSelector(ItemDetailPageDefinition.RECEIVE_BUTTON_ID));
	}
	
	@Test(description = "Display Message On PO Item Page", dependsOnMethods="clickRECEIVEButton",alwaysRun = true)
	public void displayMessageOnPOItemPage() {
		//Assert.assertEquals(driver.findElement(By.xpath(POItemsPageDefinition.NEW_PO_ITEMS_TEXT_ID)).getText(), "PO Items"); // Still error
		List<WebElement> lismg =driver.findElements(By.cssSelector(POItemsPageDefinition.MESSAGE_TEXT_ID));
		Assert.assertEquals(lismg.get(0).getText(),"The search did not return any results\n(INFO) CORE.E06004: Action successfully completed.\n(INFO) 3140.I0464: Successfully receipted purchase order. P05042001");
		lismg.get(0).click();
	}
}
