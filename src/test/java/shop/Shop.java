package shop;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
	public void shopFilterPrice() throws InterruptedException {
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

		WebElement leftSlider = driver.findElement(By.xpath(Utility.getProperties("leftSlider")));
		double maxDisplayPrice = Double
				.parseDouble(driver.findElement(By.xpath(Utility.getProperties("maxDisplayPrice"))).getText()
						.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Move left slider.
		while (true) {
			double currentPositionPrice = Double
					.parseDouble(driver.findElement(By.xpath(Utility.getProperties("maxDisplayPrice"))).getText()
							.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

			if (currentPositionPrice == toFilterPrice)
				break;
			else if (toFilterPrice < maxDisplayPrice)
				leftSlider.sendKeys(Keys.ARROW_LEFT);
			else
				leftSlider.sendKeys(Keys.ARROW_RIGHT);

	
		}

		WebElement rightSlider = driver.findElement(By.xpath(Utility.getProperties("rightSlider")));

		double minDisplayPrice = Double
				.parseDouble(driver.findElement(By.xpath(Utility.getProperties("minDisplayPrice"))).getText()
						.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Move right slider.
		while (true) {
			double currentPositionPrice = Double
					.parseDouble(driver.findElement(By.xpath(Utility.getProperties("minDisplayPrice"))).getText()
							.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

			if (currentPositionPrice == fromFilterPrice)
				break;
			else if (fromFilterPrice > minDisplayPrice)
				rightSlider.sendKeys(Keys.ARROW_RIGHT);
			else
				rightSlider.sendKeys(Keys.ARROW_RIGHT);
		}
		
		driver.findElement(By.xpath(Utility.getProperties("filterBtn"))).click();
		
		//  Verify book(s) are within the price range.
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for(WebElement book: books) {
			double bookPrice = Double
					.parseDouble(book.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
			Assert.assertTrue(bookPrice >= fromFilterPrice && bookPrice <= toFilterPrice, "ERROR! Book's price is not within the price range.");
		}

		System.out.println("PASS!");
	}
}
