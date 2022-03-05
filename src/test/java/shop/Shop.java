package shop;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.processing.ProcessingEnvironment;

import org.jsoup.select.Selector;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Utilities.Driver;
import Utilities.Utility;

public class Shop {
	private WebDriverWait wait;

	@BeforeMethod
	public void beforeTest() {
		Driver.getDriver();
		Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(Driver.getDriver(), 10);
	}

	@Test
	public void shopFilterPrice() {
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("filterBtn"))));

		// User defined start price for filtering.
		int startFilterPrice = Integer.parseInt(Utility.getProperties("startFilterPrice"));

		// User defined end price for filtering.
		int endFilterPrice = Integer.parseInt(Utility.getProperties("endFilterPrice"));

		// Minimum slider value. The lowest price value that the slider can access.
		int minPrice = Integer.parseInt(
				Driver.getDriver().findElement(By.id(Utility.getProperties("minPrice"))).getAttribute("data-min"));

		// Maximum slider value. The highest price value that the slider can access.
		int maxPrice = Integer.parseInt(
				Driver.getDriver().findElement(By.id(Utility.getProperties("maxPrice"))).getAttribute("data-max"));

		// Assert user defined start price does not exceed end price.
		Assert.assertTrue(startFilterPrice <= endFilterPrice,
				"Start filter price must be less than or equal to end filter price.");

		// Assert user defined start price to be within the range of minimum & maximum
		// slider value.
		Assert.assertTrue((startFilterPrice >= minPrice) && (startFilterPrice <= maxPrice),
				"ERROR! Start filter price must be equal or greater than minimum slider value, and also smaller or equal to maximum slider value.");

		// Assert user defined end price does not exceed slider's maximum value.
		Assert.assertTrue(endFilterPrice <= maxPrice,
				"End filter price must be less than or equal to maximum slider value.");

		WebElement rightSlider = Driver.getDriver().findElement(By.xpath(Utility.getProperties("rightSlider")));

		// Right slider's price value when the page is loaded.
		double maxDisplayPrice = Double
				.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("maxDisplayPrice")))
						.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Move Right slider.
		while (true) {
			// Current right slider's price value.
			double currentPositionPrice = Double
					.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("maxDisplayPrice")))
							.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

			// No move. User defined end price is equal to the right slider's price.
			if (currentPositionPrice == endFilterPrice)
				break;
			// Move right slider to the left.
			// User defined end price is smaller than the right slider's price.
			else if (endFilterPrice < maxDisplayPrice)
				rightSlider.sendKeys(Keys.ARROW_LEFT);
			// Move right slider to the right.
			// User defined end price is greater than the right slider's price.
			else
				rightSlider.sendKeys(Keys.ARROW_RIGHT);
		}

		WebElement leftSlider = Driver.getDriver().findElement(By.xpath(Utility.getProperties("leftSlider")));

		// Left slider's price value when the page is loaded.
		double minDisplayPrice = Double
				.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("minDisplayPrice")))
						.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Move left slider.
		while (true) {
			// Current left slider's price value.
			double currentPositionPrice = Double
					.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("minDisplayPrice")))
							.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

			// No move. User defined start price is equal to the left slider's price.
			if (currentPositionPrice == startFilterPrice)
				break;
			// Move left slider to the right.
			// User defined start price is greater than the left slider's price.
			else if (startFilterPrice > minDisplayPrice)
				leftSlider.sendKeys(Keys.ARROW_RIGHT);
			// Move left slider to the left.
			// User defined start price is smaller than the left slider's price.
			else
				leftSlider.sendKeys(Keys.ARROW_RIGHT);
		}

		// Click filter button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("filterBtn"))).click();

		// Verify book(s) within the price range.
		List<WebElement> bookPrices = Driver.getDriver().findElements(By.xpath(Utility.getProperties("bookPrices")));
		for (WebElement book : bookPrices) {
			// Retrieve book price. Sell price only. Not the original price.
			String priceArray[] = book.getText().split("\n");
			double bookPrice = Double.parseDouble(
					priceArray[priceArray.length - 1].replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

			// Assert each book's price with the price filtered range.
			Assert.assertTrue(bookPrice >= startFilterPrice && bookPrice <= endFilterPrice,
					"ERROR! Book's price is not within the filtered price range.");
		}
	}

	@Test
	public void shopProductCategory() {
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("productCategoryJavaScript"))));
		
		// Select one product category to verify.
		// Number of books presents in one product category.
		int productCategoryQty =Integer.parseInt(Driver.getDriver().findElement(By.xpath(Utility.getProperties("productCategoryQty"))).getText().replaceAll("[()]", ""));
		
		// Click on one of the product categories.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("productCategoryJavaScript"))).click();
		
		// User can view only that particular product.
		// By asserting that the  displayed book quantity is equal to the quantity shown in 
		// the product category menu.
		int displayedBookQty = Driver.getDriver().findElements(By.xpath(Utility.getProperties("displayedProductQty"))).size();
		Assert.assertEquals(productCategoryQty, displayedBookQty);
	}
	
	@Test
	public void shopDefaultSorting() {
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("sortDropDown"))));
		
		// Sort drop down list.
		WebElement sortDropDown = Driver.getDriver().findElement(By.xpath(Utility.getProperties("sortDropDown")));
		
		// Select "Sort by Popularity" option in the drop down list.
		Select sortPopularity = new Select(sortDropDown);
		sortPopularity.selectByVisibleText(Utility.getProperties("sortByPopularity"));
	
		// Take the selected sort option from the drop down list.
		// Assert if it is "sort by popularity". 
		WebElement selectedSortPopularity = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("selectedItemInSortDropDown")));
		String actualSortOption = selectedSortPopularity.getText().toUpperCase();
		Assert.assertEquals(actualSortOption, Utility.getProperties("sortByPopularity").toUpperCase());
	}
	
	@AfterTest
	public void afterTest() {
		Driver.tearDown();
	}
}
