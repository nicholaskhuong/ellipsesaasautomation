package com.abb.ventyx.saas.objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abb.ventyx.saas.objects.pagedefinitions.HomePageDefinition;
import com.abb.ventyx.utilities.InputController;
import com.abb.ventyx.utilities.ScreenAction;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void emailVerification(String email, String code) {
		inputEmailAddress(email);
		clickToSignInButton();
		// codeVerification(code);
	}

	public void codeVerification(String code) {
		if (ScreenAction.isElementPresent(driver, By.id(HomePageDefinition.VCODE_TEXTFIELD_ID))) {
			WebElement vCode = driver.findElement(By.id(HomePageDefinition.VCODE_TEXTFIELD_ID));
			InputController.inputToTextFiled(vCode, code);
			clickVerifyButton();
		}
	}

	public void inputEmailAddress(String email) {
		WebElement emailField = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By
				.id(HomePageDefinition.EMAIL_TEXTFIELD_ID)));
		InputController.inputToTextFiled(emailField, email);
	}

	public void clickToSignInButton() {
		driver.findElement(By.id(HomePageDefinition.LOGIN_BUTTON_ID)).click();
	}

	public boolean isErrorAppear() {
		try {
			WebElement errorPopup = driver.findElement(By.className("v-Notification-caption"));
			return errorPopup.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public void clickVerifyButton() {
		driver.findElement(By.id(HomePageDefinition.VERIFY_BUTTON_ID)).click();
	}

	public void chooseEllipseVersion(String ellipseVersion) {
		new Select(driver.findElement(By.cssSelector("select.v-select-select"))).selectByVisibleText(ellipseVersion);
	}
}
