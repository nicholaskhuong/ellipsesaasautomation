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
import com.abb.ventyx.utilities.ScreenAction;

@ALM(id = "1025")
@Credentials(user = "SPR002", password = "", district = "R100", position = "MATMAN")
public class QuickReceiptPartialQuantity_GoodsPO extends BaseTestCase {

	@Test(description = "Access to Receive Goods Application")
	public void accessReceiveGoods() {
		loginToApplication(ApplicationName.RECEIVE_GOODS);
		Assert.assertEquals(
				driver.findElement(
						By.xpath(POSearchPageDefinition.NEW_RECEIVE_GOODS_TEXT_ID))
						.getText(), "PO Search");
	}

	@Test(description = "Input value PO Number on PO search Page", dependsOnMethods = "accessReceiveGoods", alwaysRun = true)
	public void inputPurchaseOrderNumber() {
		WebElement purchaseOrderNumber = driver.findElement(By
				.id(POSearchPageDefinition.PO_NUMBER_ID));
		purchaseOrderNumber.sendKeys("P05018");
	}

	@Test(description = "Input value PO Item Number on PO search Page", dependsOnMethods = "inputPurchaseOrderNumber", alwaysRun = true)
	public void inputPurchaseOrderItemNumber() {
		WebElement purchaseOrderItemNumber = driver.findElement(By
				.id(POSearchPageDefinition.PO_ITEM_NUMBER_ID));
		purchaseOrderItemNumber.sendKeys("");
	}

	@Test(description = "Click button SEARCH on PO search Page", dependsOnMethods = "inputPurchaseOrderItemNumber", alwaysRun = true)
	public void clickSEARCHButton() {
		ScreenAction actionBtn = new ScreenAction(driver);
		actionBtn.clickBtn(By
				.cssSelector(POSearchPageDefinition.SEARCH_BUTTON_ID));
	}

	@Test(description = "Access to PO Items Page", dependsOnMethods = "clickSEARCHButton", alwaysRun = true)
	public void accessToPOItemsPage() {
		// Assert.assertEquals(driver.findElement(By.xpath(POItemsPageDefinition.NEW_PO_ITEMS_TEXT_ID)).getText(),
		// "PO Items");
	}

	// int qty=0;
	@Test(description = "Display Detail Items", dependsOnMethods = "accessToPOItemsPage", alwaysRun = true)
	public void displayDetailItems() {
		List<WebElement> documentItem = driver.findElements(By
				.id(POItemsPageDefinition.PO_ITEM_NUMBER_ID));
		List<WebElement> partNumber = driver.findElements(By
				.id(POItemsPageDefinition.PART_NUMBER_ID));
		List<WebElement> stockCode = driver.findElements(By
				.id(POItemsPageDefinition.STOCK_CODE_ID));
		List<WebElement> description = driver.findElements(By
				.id(POItemsPageDefinition.DESCRIPTION_ID));
		List<WebElement> outstandingQuantityUOP = driver.findElements(By
				.id(POItemsPageDefinition.OUT_STANDING_QUANTITY_UOP_ID));
		List<WebElement> unitOfPurchase = driver.findElements(By
				.id(POItemsPageDefinition.UNIT_OF_PURCHASE_ID));
		List<WebElement> outstandingQuantityUOI = driver.findElements(By
				.id(POItemsPageDefinition.OUT_STANDING_QUANTITY_UOI_ID));
		List<WebElement> unitOfIssue = driver.findElements(By
				.id(POItemsPageDefinition.UNIT_OF_ISSUE_ID));

		Assert.assertEquals(documentItem.get(0).getAttribute("value"), "001");
		Assert.assertEquals(partNumber.get(0).getAttribute("value"), "");
		Assert.assertEquals(stockCode.get(0).getAttribute("value"), "PC0005001");
		Assert.assertEquals(description.get(0).getAttribute("value"), "POSP");

		// qty=
		// Integer.valueOf(outstandingQuantityUOP.get(0).getAttribute("value"));
		Assert.assertEquals(
				outstandingQuantityUOP.get(0).getAttribute("value"), "15.0");
		Assert.assertEquals(unitOfPurchase.get(0).getAttribute("value"), "AUD");
		Assert.assertEquals(
				outstandingQuantityUOI.get(0).getAttribute("value"), "15.0");
		Assert.assertEquals(unitOfIssue.get(0).getAttribute("value"), "AUD");
	}

	@Test(description = "Click button PARTIAL on PO Items Page", dependsOnMethods = "displayDetailItems", alwaysRun = true)
	public void clickPARTIALButton() {
		// if(qty<15){
		// click icon back to list page
		// open detail time 2;
		// }
		List<WebElement> btnPARTIAL = driver.findElements(By
				.cssSelector(POItemsPageDefinition.PARTIAL_BUTTON_ID));
		btnPARTIAL.get(0).click();
	}

	@Test(description = "Input Value On Item Detail Page", dependsOnMethods = "clickPARTIALButton", alwaysRun = true)
	public void inputValueOnItemDetailPage() {
		// Assert.assertEquals(driver.findElement(By.xpath(ItemDetailPageDefinition.NEW_ITEM_DETAIL_TEXT_ID)).getText(),
		// "Item Detail");
		WebElement taskQuantityUOM = driver.findElement(By
				.id(ItemDetailPageDefinition.RECEIPT_QTY_ID));
		taskQuantityUOM.sendKeys("5");
		WebElement receiptReference = driver.findElement(By
				.id(ItemDetailPageDefinition.RECEIPT_PEFERENCE_ID));
		receiptReference.clear();
		receiptReference.sendKeys("04may005");
	}

	@Test(description = "Click Receive Button On Item Detail Page", dependsOnMethods = "inputValueOnItemDetailPage", alwaysRun = true)
	public void clickRECEIVEButton() {
		ScreenAction actionBtn = new ScreenAction(driver);
		actionBtn.clickBtn(By
				.cssSelector(ItemDetailPageDefinition.RECEIPT_PEFERENCE_ID));
	}

	@Test(description = "Display Message On PO Item Page", dependsOnMethods = "clickRECEIVEButton", alwaysRun = true)
	public void displayMessageOnPOItemPage() {
		List<WebElement> lismg = driver.findElements(By
				.cssSelector(POItemsPageDefinition.MESSAGE_TEXT_ID));
		Assert.assertEquals(
				lismg.get(0).getText(),
				"(INFO) CORE.E06004: Action successfully completed.\n(INFO) 3140.I0687: Purchase Order P05018001 is linked to Purchase Requisition PC0005001. Print Receiving Report.\n(INFO) 3140.I0464: Successfully receipted purchase order. P05018001");
		lismg.get(0).click();
	}
}
