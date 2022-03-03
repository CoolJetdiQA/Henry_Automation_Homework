package shop;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
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
		
		Point p = slider.getLocation();
		System.out.println("x: " + p.getX());
		System.out.println("y: " + p.getY());
		
		Actions action = new Actions(driver);
		action.dragAndDropBy(slider, p.getX(), p.getY()).perform();
		
	     
		
		
		double maxPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("maxSliderPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
		double minPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("minSliderPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
		
		System.out.println("Max price: " + maxPrice);
		System.out.println("Min price: " + minPrice);

		System.out.println("PASS!");
	}
}
