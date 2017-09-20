package com.abb.ventyx.utilities;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TableFunction {
	WebDriver driver;

	public TableFunction(WebDriver driver) {
		this.driver = driver;
	}

	public int findRowByString(String tableCSS, String value, int columnindex) {
		int row = 0;

		WebElement baseTable = driver.findElement(By.cssSelector(tableCSS));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		int sumRow = tableRows.size();
		for (int i = 1; i < sumRow; i++) {
			WebElement columnValue = driver.findElement(By.cssSelector(tableCSS + "> table > tbody > tr:nth-child(" + i + ") > td:nth-child("
					+ columnindex + ")"));
			if (columnValue.getText().equals(value)) {
				row = i;
				break;
			}

		}
		return row;

	}

	public void clickDocType(String tableCSS, String value) {
		// int row = 0;
		WebElement baseTable = driver.findElement(By.cssSelector(tableCSS));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		int sumRow = tableRows.size();
		for (int i = 0; i < sumRow; i++) {
			String DocTypevalue = driver.findElement(By.id("docTypeBtn" + i)).getText();
			if (DocTypevalue.equals(value)) {
				driver.findElement(By.id("docTypeBtn" + i)).click();
				// row=i;
				break;
			}
		}

	}

	public int countRow(String tableCSS) {
		WebElement baseTable = driver.findElement(By.cssSelector(tableCSS));
		List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
		int sumRow = tableRows.size();
		return sumRow;
	}

	public void assertRowEqual(String obj, String value, int row) {
		WebElement rowFilter = driver.findElement(By.id(obj + row));
		assertEquals(rowFilter.getText(), value, "Title is wrong");
	}

	public void assertRowNotEqual(String obj, String value, int row) {
		WebElement rowFilter = driver.findElement(By.id(obj + row));
		if (rowFilter != null) {
			assertNotEquals(rowFilter.getText(), value, "Title is wrong");
		}
	}
}
