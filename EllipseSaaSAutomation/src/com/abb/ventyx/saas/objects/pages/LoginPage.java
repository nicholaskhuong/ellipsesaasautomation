package com.abb.ventyx.saas.objects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.abb.ventyx.saas.objects.pagedefinitions.LoginPageDefinition;
import com.abb.ventyx.utilities.InputController;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public LoginPage() {
		super(BasePage.driver);
	}

	public void login(String username, String password, String district, String position) {

		InputController.inputToTextFiled(getUsernameTextField(), username);
		InputController.inputToTextFiled(getPasswordTextField(), password);
		InputController.inputToTextFiled(getDistrictTextField(), district);
		InputController.inputToTextFiled(getPositionTextField(), position);
		getLoginButton().click();

	}

	// Get ELELEMENTS

	public static WebElement getUsernameTextField() {
		WebElement userName = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By
				.id(LoginPageDefinition.USERNAME_TEXT_FIELD_ID)));
		return userName;
	}

	public static WebElement getPasswordTextField() {
		return driver.findElement(By.id(LoginPageDefinition.PASSWORD_TEXT_FIELD_ID));
	}

	public static WebElement getDistrictTextField() {
		return driver.findElement(By.id(LoginPageDefinition.DISTRICT_TEXT_FIELD_ID));
	}

	public static WebElement getPositionTextField() {
		return driver.findElement(By.id(LoginPageDefinition.POSITION_TEXT_FIELD_ID));
	}

	public static WebElement getLoginButton() {
		return driver.findElement(By.id(LoginPageDefinition.LOGIN_BUTTON_ID));
	}
}
