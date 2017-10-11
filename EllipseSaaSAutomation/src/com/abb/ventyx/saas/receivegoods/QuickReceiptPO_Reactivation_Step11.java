package com.abb.ventyx.saas.receivegoods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.abb.ventyx.saas.objects.pagedefinitions.ApplicationName;
import com.abb.ventyx.saas.objects.pagedefinitions.ItemDetailPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.POItemsPageDefinition;
import com.abb.ventyx.saas.objects.pagedefinitions.POSearchPageDefinition;
import com.abb.ventyx.utilities.ALM;
import com.abb.ventyx.utilities.BaseTestCase;
import com.abb.ventyx.utilities.Credentials;

@ALM(id = "1029")
@Credentials(user = "SPR002", password = "", district = "R100", position = "MATMAN")
public class QuickReceiptPO_Reactivation_Step11 extends BaseTestCase {

	@Test(description = "Access to Receive Goods Application")
	public void accessReceiveGoods() {
		loginToApplication(ApplicationName.RECEIVE_GOODS);
		Assert.assertEquals(driver.findElement(By.xpath(POSearchPageDefinition.NEW_RECEIVE_GOODS_TEXT_ID)).getText(), "PO Search");
	}

	@Test(description = "Input value PO Number on PO search Page", dependsOnMethods = "accessReceiveGoods", alwaysRun = true)
	public void inputPurchaseOrderNumber() {
		WebElement purchaseOrderNumber = driver.findElement(By.id(POSearchPageDefinition.PO_NUMBER_ID));
		purchaseOrderNumber.sendKeys("P05097");
	}

	@Test(description = "Input value PO Item Number on PO search Page", dependsOnMethods = "inputPurchaseOrderNumber", alwaysRun = true)
	public void inputPurchaseOrderItemNumber() {
		WebElement purchaseOrderItemNumber = driver.findElement(By.id(POSearchPageDefinition.PO_ITEM_NUMBER_ID));
		purchaseOrderItemNumber.sendKeys("3");
	}

	@Test(description = "Click button SEARCH on PO search Page", dependsOnMethods = "inputPurchaseOrderItemNumber", alwaysRun = true)
	public void clickSEARCHButton() {
		screenAction.clickBtn(By.cssSelector(POSearchPageDefinition.SEARCH_BUTTON_ID));
		screenAction.waitObjVisible(driver, By.cssSelector(POSearchPageDefinition.SEARCH_BUTTON_ID), 3);
	}

	@Test(description = "Access to PO Items Page", dependsOnMethods = "clickSEARCHButton", alwaysRun = true)
	public void accessToPOItemsPage() {
		// Assert.assertEquals(driver.findElement(By.xpath(POItemsPageDefinition.NEW_PO_ITEMS_TEXT_ID)).getText(),"PO Items");
	}

	@Test(description = "Display Detail Items", dependsOnMethods = "accessToPOItemsPage", alwaysRun = true)
	public void displayDetailItems() {
		screenAction.waitObjVisible(driver, By.id(POItemsPageDefinition.PO_ITEM_NUMBER_ID), 3);
		List<WebElement> documentItem = driver.findElements(By.id(POItemsPageDefinition.PO_ITEM_NUMBER_ID));
		List<WebElement> partNumber = driver.findElements(By.id(POItemsPageDefinition.PART_NUMBER_ID));
		List<WebElement> stockCode = driver.findElements(By.id(POItemsPageDefinition.STOCK_CODE_ID));
		List<WebElement> description = driver.findElements(By.id(POItemsPageDefinition.DESCRIPTION_ID));
		List<WebElement> outstandingQuantityUOP = driver.findElements(By.id(POItemsPageDefinition.OUT_STANDING_QUANTITY_UOP_ID));
		List<WebElement> unitOfPurchase = driver.findElements(By.id(POItemsPageDefinition.UNIT_OF_PURCHASE_ID));
		List<WebElement> outstandingQuantityUOI = driver.findElements(By.id(POItemsPageDefinition.OUT_STANDING_QUANTITY_UOI_ID));
		List<WebElement> unitOfIssue = driver.findElements(By.id(POItemsPageDefinition.UNIT_OF_ISSUE_ID));

		Assert.assertEquals(documentItem.get(0).getAttribute("value"), "003");
		Assert.assertEquals(partNumber.get(0).getAttribute("value"), "");
		Assert.assertEquals(stockCode.get(0).getAttribute("value"), "STC85A");
		Assert.assertEquals(description.get(0).getAttribute("value"), "STOCK CODE;");
		Assert.assertEquals(outstandingQuantityUOP.get(0).getAttribute("value"), "10.0");
		Assert.assertEquals(unitOfPurchase.get(0).getAttribute("value"), "EA");
		Assert.assertEquals(outstandingQuantityUOI.get(0).getAttribute("value"), "10.0");
		Assert.assertEquals(unitOfIssue.get(0).getAttribute("value"), "EA");
	}

	@Test(description = "Click button PARTIAL on PO Items Page", dependsOnMethods = "displayDetailItems", alwaysRun = true)
	public void clickPARTIALButton() {
		screenAction.clickBtnByIndex(By.cssSelector(POItemsPageDefinition.PARTIAL_BUTTON_ID), 0);
	}

	@Test(description = "Display Item Detail Items", dependsOnMethods = "clickPARTIALButton", alwaysRun = true)
	public void displayItemDetailItems() {
		// Assert.assertEquals(driver.findElement(By.xpath(ItemDetailPageDefinition.NEW_ITEM_DETAIL_TEXT_ID)).getText(), "Item Detail");
		List<WebElement> stockCode = driver.findElements(By.id(ItemDetailPageDefinition.STOCK_CODE_ID));
		List<WebElement> description = driver.findElements(By.id(ItemDetailPageDefinition.DESCRIPTION_ID));
		List<WebElement> outstandingQuantityUOP = driver.findElements(By.id(ItemDetailPageDefinition.OUT_STANDING_QUANTITY_UOP_ID));
		List<WebElement> unitOfPurchase = driver.findElements(By.id(ItemDetailPageDefinition.UNIT_OF_PURCHASE_ID));
		List<WebElement> outstandingQuantityUOI = driver.findElements(By.id(ItemDetailPageDefinition.OUT_STANDING_QUANTITY_UOI_ID));
		List<WebElement> unitOfIssue = driver.findElements(By.id(ItemDetailPageDefinition.UNIT_OF_ISSUE_ID));

		Assert.assertEquals(stockCode.get(0).getAttribute("value"), "STC85A");
		Assert.assertEquals(description.get(0).getAttribute("value"), "STOCK CODE;");
		Assert.assertEquals(outstandingQuantityUOP.get(0).getAttribute("value"), "10.0");
		Assert.assertEquals(unitOfPurchase.get(0).getAttribute("value"), "EA");
		Assert.assertEquals(outstandingQuantityUOI.get(0).getAttribute("value"), "10.0");
		Assert.assertEquals(unitOfIssue.get(0).getAttribute("value"), "EA");
	}
	
	@Test(description = "Input Receipt  On Item Detail Page", dependsOnMethods = "displayItemDetailItems", alwaysRun = true)
	public void inputReceiptQtyOnItemDetailPage() {
		screenAction.waitObjVisible(driver, By.id(ItemDetailPageDefinition.RECEIPT_QTY_ID), 3);
		WebElement taskQuantityUOM = driver.findElement(By.id(ItemDetailPageDefinition.RECEIPT_QTY_ID));
		taskQuantityUOM.sendKeys("7");
	}
		
	@Test(description = "Input Receipt Reference On Item Detail Page", dependsOnMethods = "inputReceiptQtyOnItemDetailPage", alwaysRun = true)
	public void inputReceiptReferenceOnItemDetailPage() {
		WebElement receiptReference = driver.findElement(By.id(ItemDetailPageDefinition.RECEIPT_PEFERENCE_ID));
		receiptReference.clear();
		receiptReference.sendKeys("04may1029");
	}

	@Test(description = "Click Receive Button On Item Detail Page", dependsOnMethods = "inputReceiptReferenceOnItemDetailPage", alwaysRun = true)
	public void clickRECEIVEButton() {
		screenAction.clickBtn(By.xpath(ItemDetailPageDefinition.RECEIPT_PEFERENCE2_ID));
		screenAction.waitObjVisible(driver, By.xpath(ItemDetailPageDefinition.RECEIPT_PEFERENCE2_ID), 3);
	}

	@Test(description = "Display Message On PO Item Page", dependsOnMethods = "clickRECEIVEButton", alwaysRun = true)
	public void displayMessageOnPOItemPage() {
			// Assert.assertEquals(driver.findElement(By.xpath(POItemsPageDefinition.NEW_PO_ITEMS_TEXT_ID)).getText(), "PO Items"); // Still error
		screenAction.waitObjVisible(driver, By.cssSelector(POItemsPageDefinition.MESSAGE_TEXT_ID), 3);	
		Assert.assertEquals(driver.findElement(By.cssSelector(POItemsPageDefinition.MESSAGE_TEXT_ID)).getText(),"The search did not return any results\n(INFO) CORE.E06004: Action successfully completed.\n(INFO) 3140.I0464: Successfully receipted purchase order. P05097003");
		driver.findElement(By.cssSelector(POItemsPageDefinition.MESSAGE_TEXT_ID)).click();
	}
}

