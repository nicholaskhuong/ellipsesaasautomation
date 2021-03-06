package com.abb.ventyx.utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ScreenAction {
	WebDriver driver;

	public ScreenAction(WebDriver driver) {
		this.driver = driver;
	}

	public void waitObjInvisible(By obj) {
		(new WebDriverWait(driver, 90)).until(ExpectedConditions.invisibilityOfElementLocated(obj));
	}

	public static void waitObjVisible(WebDriver driver, By obj) {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(obj));
	}

	public void waitObjVisible(WebDriver driver, By obj, long timeoutInSecond) {
		(new WebDriverWait(driver, timeoutInSecond)).until(ExpectedConditions.visibilityOfElementLocated(obj));

	}

	public void waitObjVisibleAndClick(By obj) {
		WebElement element = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(obj));
		element.click();

	}

	public void clickBtn(By obj) {
		WebElement btn = driver.findElement(obj);
		btn.click();
	}

	public void clickBtnByIndex(By obj, int index) {
		List<WebElement> listButtons = driver.findElements(obj);
		listButtons.get(index).click();
	}


	public void inputTextField(String obj, String value) {
		WebElement txtField = driver.findElement(By.id(obj));
		txtField.clear();
		txtField.sendKeys(value);
	}

	public void inputTextField(By obj, String value) {
		WebElement txtField = driver.findElement(obj);
		txtField.clear();
		txtField.sendKeys(value);
	}

	public void clickCheckBoxN(int n) {
		List<WebElement> listCheckbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
		listCheckbox.get(n).click();
	}

	public void checkObjSelected(int start, int end) {
		List<WebElement> listCheckbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (int i = start; i < end; i++) {
			listCheckbox.get(i).isSelected();
		}
	}

	public boolean checkObjSelected(int start) {
		boolean isSelected = false;
		List<WebElement> listCheckbox = driver.findElements(By.xpath("//input[@type='checkbox']"));
		isSelected = listCheckbox.get(start).isSelected();
		return isSelected;

	}

	public void assertMessgeError(String msgCSS, String msg) {
		WebElement error = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(msgCSS)));
		Assert.assertEquals(error.getText(), msg);
	}

	public void assertMessage(String msgCSS, String msg) {
		WebElement message = (new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(msgCSS)));
		Assert.assertEquals(message.getText(), msg);
	}

	public static boolean isElementPresent(WebDriver driver, By by, long timeout) {
		try {
			WebElement error = (new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(by));
			return error.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isElementPresent(WebDriver driver, By by) {
		return isElementPresent(driver, by, 10);
	}

	public void inputDate(WebDriver driver, By obj, String mmddyyyy_date) {
		WebElement forecastDate = driver.findElement(obj);
		forecastDate.sendKeys(mmddyyyy_date);
		int maxLoop = 0;
		boolean existError = false;
		while (maxLoop < 4) {
			maxLoop += 1;
			existError = ScreenAction.isElementPresent(driver,
					By.cssSelector("#saas-3522304-overlays > div.v-Notification.error.v-Notification-error > div > div > h1"), 1);
			if (existError) {
				Actions action = new Actions(driver);
				action.sendKeys(Keys.ESCAPE).build().perform();
			} else {
				break;
			}
		}
	}
	
	public void clickTapToClose(WebDriver driver, By obj) {
		  int maxLoop = 0;
		  boolean existError = false;
		 
		  while (maxLoop < 4) {
			   maxLoop += 1;
			   existError = ScreenAction.isElementPresent(driver,
			     By.cssSelector("#saas-3522304-overlays > div.v-Notification.error.v-Notification-error > div > div > h1"), 1);
			   if (existError) {
			    Actions action = new Actions(driver);
			    action.sendKeys(Keys.ESCAPE).build().perform();
			   } else {
			    break;
			   }
		  }
	}	
	
	public void selectByText(WebDriver driver, By obj, String input) {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(obj));
		
		Select select = new Select(driver.findElement(obj));
		select.selectByVisibleText(input);

	}

	public String getSelectedText(WebDriver driver, String Id) {
		Select select = new Select(driver.findElement(By.cssSelector(Id)));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isClickable(By arg0, int index) {
		List<WebElement> listButtons = driver.findElements(arg0);
		
		if (listButtons.get(index) == null) {
		    return true;
		} else {
		    String atr = listButtons.get(index).getAttribute("aria-disabled");
		    if (null == atr || atr.isEmpty()) {
		       return true;
		    } else {
		       return false;
		 
		  }
		}
		
	}
	
	public void assertButtonEnabled(By arg0, int index, boolean arg1) {
		Assert.assertEquals(isClickable(arg0, index), arg1);
	}
	
	public void assertButtonEnabled(By arg0, boolean arg1) {
		Assert.assertEquals(isClickable(arg0, 0), arg1);
	}
	
	
	
	
}
