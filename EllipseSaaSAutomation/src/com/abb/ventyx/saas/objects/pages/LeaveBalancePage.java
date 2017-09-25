package com.abb.ventyx.saas.objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abb.ventyx.saas.objects.pagedefinitions.LeaveBalancePageDefinition;

public class LeaveBalancePage {
	public static WebDriver driver;

	public LeaveBalancePage(WebDriver driver) {
		LeaveBalancePage.driver = driver;
	}

	// Get ELELEMENTS

	public static WebElement getForecastDateTextField() {
		WebElement forecastDate = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By
				.cssSelector(LeaveBalancePageDefinition.FORECAST_DATE_TEXT_ID)));
		return forecastDate;
	}
}
