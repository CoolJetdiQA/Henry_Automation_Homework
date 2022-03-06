package shop;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Utilities.Driver;
import Utilities.Utility;

public class Shop {
	private WebDriverWait wait;

	@BeforeMethod
	public void beforeTest() {
		Driver.getDriver();
		
		Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// This is deprecated in Selenium 4.
		// Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		wait = new WebDriverWait(Driver.getDriver(),Duration.ofSeconds(10));
		// This is deprecated in Selenium 4.
		// wait = new WebDriverWait(Driver.getDriver(), 30);
	}

	@Test (priority = 1)
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

	@Test(priority = 2)
	public void shopProductCategory() {
		
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(Utility.getProperties("productCategoryJavaScript"))));

		// Click on one of the product categories.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("productCategoryJavaScript"))).click();

		String actualCategory = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("displayedCategoryTitleOnBodyPage"))).getText()
				.toUpperCase();

		String expectedCategory = Utility.getProperties("expectedCategoryTitleOnBodyPage").toUpperCase();

		// Assert product category.
		Assert.assertTrue(actualCategory.contains(expectedCategory), Utility.getProperties("CategoryErrorMsg"));
	}

	@Test(priority = 3)
	public void shopSortByPopularity() {
		
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

	@Test(priority = 4)
	public void shopSortByAverage() {
		
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("sortDropDown"))));

		// Sort drop down list.
		WebElement sortDropDown = Driver.getDriver().findElement(By.xpath(Utility.getProperties("sortDropDown")));

		// Select "Sort by Average" option in the drop down list.
		Select sortByAverage = new Select(sortDropDown);
		sortByAverage.selectByVisibleText(Utility.getProperties("sortByAverage"));

		// Take the selected sort option from the drop down list.
		// Assert if it is "sort by average".
		WebElement selectedSortAverage = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("selectedItemInSortDropDown")));
		String actualSortOption = selectedSortAverage.getText().toUpperCase();
		Assert.assertEquals(actualSortOption, Utility.getProperties("sortByAverage").toUpperCase());
	}

	@Test(priority = 5)
	public void shopSortByNewness() {
		
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("sortDropDown"))));

		// Sort drop down list.
		WebElement sortDropDown = Driver.getDriver().findElement(By.xpath(Utility.getProperties("sortDropDown")));

		// Select "Sort by Newness" option in the drop down list.
		Select sortByNewness = new Select(sortDropDown);
		sortByNewness.selectByVisibleText(Utility.getProperties("sortByNewness"));

		// Take the selected sort option from the drop down list.
		// Assert if it is "sort by newness".
		WebElement selectedSortNewness = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("selectedItemInSortDropDown")));
		String actualSortOption = selectedSortNewness.getText().toUpperCase();
		Assert.assertEquals(actualSortOption, Utility.getProperties("sortByNewness").toUpperCase());
	}

	@Test(priority = 6)
	public void shopSortByPriceLowToHigh() {
		
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("sortDropDown"))));

		// Sort drop down list.
		WebElement sortDropDown = Driver.getDriver().findElement(By.xpath(Utility.getProperties("sortDropDown")));

		// Select "Sort by Price: low to high" option in the drop down list.
		Select sortByPriceLowToHigh = new Select(sortDropDown);
		sortByPriceLowToHigh.selectByVisibleText(Utility.getProperties("sortByPriceLowToHigh"));

		// Take the selected sort option from the drop down list.
		// Assert if it is "sort by price: low to high".
		WebElement selectedSortByPriceLowToHigh = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("selectedItemInSortDropDown")));
		String actualSortOption = selectedSortByPriceLowToHigh.getText().toUpperCase();
		Assert.assertEquals(actualSortOption, Utility.getProperties("sortByPriceLowToHigh").toUpperCase());
	}

	@Test(priority = 7)
	public void shopSortByPriceHighToLow() {
		
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("sortDropDown"))));

		// Sort drop down list.
		WebElement sortDropDown = Driver.getDriver().findElement(By.xpath(Utility.getProperties("sortDropDown")));

		// Select "Sort by Price: high to low" option in the drop down list.
		Select sortByPriceHighToLow = new Select(sortDropDown);
		sortByPriceHighToLow.selectByVisibleText(Utility.getProperties("sortByPriceHighToLow"));

		// Take the selected sort option from the drop down list.
		// Assert if it is "sort by price: high to low".
		WebElement selectedSortByPriceHighToLow = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("selectedItemInSortDropDown")));
		String actualSortOption = selectedSortByPriceHighToLow.getText().toUpperCase();
		Assert.assertEquals(actualSortOption, Utility.getProperties("sortByPriceHighToLow").toUpperCase());
	}

	@Test(priority = 8)
	public void shopReadMore() {
		
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("shopMenu"))));

		// # of books.
		int size = Driver.getDriver().findElements(By.xpath(Utility.getProperties("shopBooks"))).size();

		WebElement book;

		// For each book that has "Read More" option, click on that book and go to its
		// book page.
		// Verify out of stock.
		for (int i = 0; i < size; i++) {
			book = Driver.getDriver().findElement(By.xpath(Utility.getProperties("shopBooks") + "[" + (i + 1) + "]"));

			// Book with "Read More" option.
			if (book.getText().toUpperCase().contains(Utility.getProperties("readMore"))) {

				// Click on the book.
				book.click();

				// Wait for the next page to load.
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(Utility.getProperties("outOfStockMenu"))));

				String actualOutOfStockMenu = Driver.getDriver()
						.findElement(By.xpath(Utility.getProperties("outOfStockMenu"))).getText().toUpperCase();

				String expectedOutOfStockMenu = Utility.getProperties("expectedOutOfStockMenu").toUpperCase();

				// Assert out of stock. User cannot add the product.
				Assert.assertEquals(actualOutOfStockMenu, expectedOutOfStockMenu);

				// Go back to the shop page.
				Driver.getDriver().navigate().back();

				// Wait for the next page to load.
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("shopMenu"))));
			}
		}
	}

	@Test(priority = 9)
	public void shopSale() {
		
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("shopMenu"))));

		// # of books.
		int size = Driver.getDriver().findElements(By.xpath(Utility.getProperties("shopBooks"))).size();
		WebElement book;

		// For each on-sale book, click on that book and go to its book page.
		// User can clearly view the actual price with old price stricken for the sale
		// written products.
		for (int i = 0; i < size; i++) {
			book = Driver.getDriver().findElement(By.xpath(Utility.getProperties("shopBooks") + "[" + (i + 1) + "]"));

			// On sale book.
			if (book.getText().toUpperCase().contains(Utility.getProperties("sale").toUpperCase())) {

				// Click on the book.
				book.click();

				// Wait for the next page to load.
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("bookMenu"))));

				double originalPrice = Double
						.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("originalPrice")))
								.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

				double salePrice = Double
						.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("discountPrice")))
								.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

				// Assert discount price less than original price.
				Assert.assertTrue(originalPrice > salePrice);

				// Go back to the shop page.
				Driver.getDriver().navigate().back();

				// Wait for the next page to load.
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("shopMenu"))));
			}
		}
	}

	@Test(priority = 10)
	public void shopAddToBasketViewBasket() {
		
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("shopMenu"))));

		// Click on an "Add To Basket" button which adds that book to your basket.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("addToBasketBtn"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("viewBasketBtn"))));

		// Click on View Basket button and go to check out page.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("viewBasketBtn"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(Utility.getProperties("textBoxValueQty"))));

		// Get sub total.
		double subTotal = Double
				.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("subTotal@checkOut")))
						.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Get total.
		double total = Double
				.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("totalPrice@checkOut")))
						.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Assert sub total < total
		Assert.assertTrue(subTotal < total);

		// Click "Proceed to Checkout" button to proceeds to payment gateway page.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("checkOutBtn"))).click();

		// Wait for payment gateway page is opened.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("firstName"))));

		// First name
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("firstName"))).sendKeys("Henry");

		// Last name
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("lastName"))).sendKeys("Leu");

		// Company name
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("companyName"))).sendKeys("TechCircle");

		// Email
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("email")))
				.sendKeys(Utility.getProperties("emailAddress"));

		// Phone
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("phone"))).sendKeys("123456789");

		// Country
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("countryDropDown"))).click();
		List<WebElement> countries = Driver.getDriver().findElements(By.xpath(Utility.getProperties("countries")));
		for (int i = 0; i < countries.size(); i++) {
			WebElement country = Driver.getDriver()
					.findElement(By.xpath(Utility.getProperties("countries") + "[" + (i + 1) + "]"));

			if (country.getText().equalsIgnoreCase(Utility.getProperties("countryName"))) {
				country.click();
				break;
			}
		}

		// Address
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("address"))).sendKeys("1111 Ave U");

		// Address 2
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("address2"))).sendKeys("Apartment #2C");

		// City
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("city"))).sendKeys("Flushing");

		// County
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("county"))).sendKeys("Taipei");

		// Zip code
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("zipCode"))).sendKeys("11354");

		// Click payment method.
		WebElement element = Driver.getDriver().findElement(By.id(Utility.getProperties("cod")));
		Actions action = new Actions(Driver.getDriver());
		action.moveToElement(element).click().build().perform();

		// Order notes
		Driver.getDriver().findElement(By.tagName(Utility.getProperties("orderNotes")))
				.sendKeys(Utility.getProperties("orderNotesMsg"));		

		// Place order
		Driver.getDriver().findElement(By.id(Utility.getProperties("placeOrder"))).click();

		// Wait for order confirmation page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("orderNumber"))));

		// Assert Order confirmation page is displayed by checking order number is
		// displayed.
		Assert.assertTrue(Driver.getDriver().findElement(By.xpath(Utility.getProperties("orderNumber"))).isDisplayed());
	}

	@Test(priority = 11)
	public void shopAddToBasketViewBasketThroughItemLink() {
		
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("shopMenu"))));

		// Click on an "Add To Basket" button which adds that book to your basket.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("addToBasketBtn"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("viewBasketBtn"))));


		// Click on Item Link button and go to check out page.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(Utility.getProperties("textBoxValueQty"))));

		// Get sub total.
		double subTotal = Double
				.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("subTotal@checkOut")))
						.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Get total.
		double total = Double
				.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("totalPrice@checkOut")))
						.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Assert sub total < total
		Assert.assertTrue(subTotal < total);

		// Click "Proceed to Checkout" button to proceeds to payment gateway page.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("checkOutBtn"))).click();

		// Wait for payment gateway page is opened.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("firstName"))));

		// First name
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("firstName"))).sendKeys("Henry");

		// Last name
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("lastName"))).sendKeys("Leu");

		// Company name
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("companyName"))).sendKeys("TechCircle");

		// Email
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("email")))
				.sendKeys(Utility.getProperties("emailAddress"));

		// Phone
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("phone"))).sendKeys("123456789");

		// Country
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("countryDropDown"))).click();
		List<WebElement> countries = Driver.getDriver().findElements(By.xpath(Utility.getProperties("countries")));
		for (int i = 0; i < countries.size(); i++) {
			WebElement country = Driver.getDriver()
					.findElement(By.xpath(Utility.getProperties("countries") + "[" + (i + 1) + "]"));

			if (country.getText().equalsIgnoreCase(Utility.getProperties("countryName"))) {
				country.click();
				break;
			}
		}

		// Address
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("address"))).sendKeys("1111 Ave U");

		// Address 2
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("address2"))).sendKeys("Apartment #2C");

		// City
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("city"))).sendKeys("Flushing");

		// County
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("county"))).sendKeys("Taipei");

		// Zip code
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("zipCode"))).sendKeys("11354");

		// Click payment method.
		WebElement element = Driver.getDriver().findElement(By.id(Utility.getProperties("cod")));
		Actions action = new Actions(Driver.getDriver());
		action.moveToElement(element).click().build().perform();

		// Order notes
		Driver.getDriver().findElement(By.tagName(Utility.getProperties("orderNotes")))
				.sendKeys(Utility.getProperties("orderNotesMsg"));		

		// Place order
		Driver.getDriver().findElement(By.id(Utility.getProperties("placeOrder"))).click();

		// Wait for order confirmation page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("orderNumber"))));

		// Assert Order confirmation page is displayed by checking order number is
		// displayed.
		Assert.assertTrue(Driver.getDriver().findElement(By.xpath(Utility.getProperties("orderNumber"))).isDisplayed());
	}
	
	@Test(priority = 12)
	public void shopAddtoBasketViewBasketTax() {
		
		// Click on Shop menu.
		Driver.getDriver().findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("shopMenu"))));

		// Click on an "Add To Basket" button which adds that book to your basket.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("addToBasketBtn"))).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("viewBasketBtn"))));

		// Click on Item Link button and go to check out page.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(Utility.getProperties("textBoxValueQty"))));

		// Get sub total.
		double subTotal = Double
				.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("subTotal@checkOut")))
						.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Get total.
		double total = Double
				.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("totalPrice@checkOut")))
						.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Assert sub total < total
		Assert.assertTrue(subTotal < total);
		
		// Get roaming tax.
		double roamingTax =  Double
				.parseDouble(Driver.getDriver().findElement(By.xpath(Utility.getProperties("roamingTax")))
						.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Assert tax rate for abroad other country. Tax rate is 5%.
		double otherCountryTaxRate = Double.parseDouble(Utility.getProperties("otherCountryTaxRate"));
		Assert.assertTrue(subTotal*otherCountryTaxRate == roamingTax);
		
		// Assert tax rate for Indian country. Tax rate is 2%.
		double indianTaxRate = Double.parseDouble(Utility.getProperties("indianTaxRate"));
		double expectedIndianTax = Double.parseDouble(Utility.getProperties("expectedIndianTax"));
		Assert.assertTrue(subTotal*indianTaxRate == expectedIndianTax);
	}
	
	@AfterMethod
	public void afterTest() {
		Driver.tearDown();
	}
}
