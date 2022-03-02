package shop;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Driver;
import Utilities.Utility;

public class Shop {
	private WebDriver driver;
	private WebDriverWait wait;

	
	@Before
	public void beforeTest() {
		driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
	}
	
	@Test
	public void shopFilterPrice() {
		System.out.print("Shop - Test case #1 starts.....");

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("filterBtn"))));
		
		WebElement slider = driver.findElement(By.xpath(Utility.getProperties("leftSlider")));
		
		Actions action = new Actions(driver);
		action.dragAndDropBy(slider, -28, 0).click().build().perform();
		
		System.out.println("PASS!");
	}
}
