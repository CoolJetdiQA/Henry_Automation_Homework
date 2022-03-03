package shop;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
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
		
	     
		int minPrice = 150;  // find xpath later
		int maxPrice = 500;  // find xpath later
		int fromFilterPrice=400; // min filter price
		int toFilterPrice=605;   // max filter price
		
		Assert.assertTrue("Min filter price must be less than or equal to max filter price.", fromFilterPrice <= toFilterPrice);
		Assert.assertTrue("ERROR! Min filter price must be equal or greater than min price, and also smaller or equal to max value", 
				         (fromFilterPrice >= minPrice) && (fromFilterPrice <= maxPrice));
		Assert.assertTrue("Max filter price must be less than or equal to max price.", toFilterPrice <= maxPrice);
		
		double maxDisplayPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("maxDisplayPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
		double minDisplayPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("minDisplayPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
		
		System.out.println("Max display price: " + maxDisplayPrice);
		System.out.println("Min display price: " + minDisplayPrice);

		System.out.println("PASS!");
	}
}
