package shop;

import java.util.concurrent.TimeUnit;

import org.graalvm.compiler.nodes.memory.address.OffsetAddressNode;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Utilities.Driver;
import Utilities.Utility;

public class Shop {
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeMethod
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

		int minPrice = Integer
				.parseInt(driver.findElement(By.id(Utility.getProperties("minPrice"))).getAttribute("data-min"));
		int maxPrice = Integer
				.parseInt(driver.findElement(By.id(Utility.getProperties("maxPrice"))).getAttribute("data-max"));
		int fromFilterPrice = Integer.parseInt(Utility.getProperties("fromFilterPrice")); // min filter price
		int toFilterPrice = Integer.parseInt(Utility.getProperties("toFilterPrice")); // max filter price

		Assert.assertTrue(fromFilterPrice <= toFilterPrice,
				"Min filter price must be less than or equal to max filter price.");
		Assert.assertTrue((fromFilterPrice >= minPrice) && (fromFilterPrice <= maxPrice),
				"ERROR! Min filter price must be equal or greater than min price, and also smaller or equal to max value");
		Assert.assertTrue(toFilterPrice <= maxPrice, "Max filter price must be less than or equal to max price.");

		double maxDisplayPrice = Double
				.parseDouble(driver.findElement(By.xpath(Utility.getProperties("maxDisplayPrice"))).getText()
						.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
		double minDisplayPrice = Double
				.parseDouble(driver.findElement(By.xpath(Utility.getProperties("minDisplayPrice"))).getText()
						.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		WebElement leftSlider = driver.findElement(By.xpath(Utility.getProperties("leftSlider")));
		WebElement rightSlider = driver.findElement(By.xpath(Utility.getProperties("rightSlider")));

		Point position = leftSlider.getLocation();
		// int xAxis = position.getX();
		int yAxis = position.getY();
		// System.out.println("x: " + xAxis);
		System.out.println("y: " + yAxis);

		Actions action = new Actions(driver);
		//action.moveToElement(leftSlider).click().build().perform();
		// action.dragAndDropBy(slider, p.getX(), p.getY()).perform();

		int xOffSet = 0;
		// move left slider.
		if (toFilterPrice < maxDisplayPrice) {
			while (true) {
				action.dragAndDropBy(leftSlider, --xOffSet, 0).build().perform();
				for(int i=0; i<1000; i++) {}
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Utility.getProperties("leftSlider"))));
				double d = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("maxDisplayPrice")))
						.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
				System.out.println("left slider value is: " + d);
				System.out.println("x off set : " + xOffSet);

				if(d <= toFilterPrice) {
					break;
				}
			}

		}

		System.out.println("PASS!");
	}
}
