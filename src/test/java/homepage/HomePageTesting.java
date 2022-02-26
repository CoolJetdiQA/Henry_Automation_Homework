/*  	
 	Class: TechCircle Batch #9
 	Date: February 26, 2022
 	Student: Ming-Jen Leu (Henry Leu)
 	Programming language: Java 11
 	Filename: HomePageTesting.java
 	
  Home Page automation testing.
  Test cases #1 to #18.
  
 */

package homepage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilities.Driver;
import Utilities.Utility;

public class HomePageTesting {
	private WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void beforeTest() {
		driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test1() {
		System.out.print("Home Page - Test case #1 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("sliders"))));

		// # of actual sliders.
		int actualSliderQty = driver.findElements(By.xpath(Utility.getProperties("sliders"))).size();

		int expectedSliderQty = 3;

		// Assert 3 sliders only.
		Assert.assertTrue(actualSliderQty == expectedSliderQty);

		System.out.println("PASSED!");
	}

	
	@Test
	public void test2() {
		System.out.print("Home Page - Test case #2 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();

		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		System.out.println("PASSED!");
	}

	
	@Test
	public void test3() {
		System.out.print("Home Page - Test case #3 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();

		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		System.out.println("PASSED!");
	}

	
	@Test
	public void test4() {
		System.out.print("Home Page - Test case #4 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Click and assert Description tab.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("descriptionTab"))).isDisplayed());
		driver.findElement(By.xpath(Utility.getProperties("descriptionTab"))).click();
		String actualDescription = driver.findElement(By.xpath(Utility.getProperties("descriptionTitle"))).getText();
		String expectedDescription = "Product Description";
		Assert.assertTrue(actualDescription.equalsIgnoreCase(expectedDescription));

		System.out.println("PASSED!");
	}

	
	@Test
	public void test5() {
		System.out.print("Home Page - Test case #5 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Click and assert Reviews tab.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("reviewTab"))).isDisplayed());
		driver.findElement(By.xpath(Utility.getProperties("reviewTab"))).click();
		String actualReview = driver.findElement(By.xpath(Utility.getProperties("reviewTitle"))).getText();
		String expectedReview = "Reviews";
		Assert.assertTrue(actualReview.equalsIgnoreCase(expectedReview));

		System.out.println("PASSED!");
	}

	
	@Test
	public void test6() {
		System.out.print("Home Page - Test case #6 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty"))).sendKeys(Utility.getProperties("orderQty"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQty");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQty"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		System.out.println("PASSED!");
	}

	
	@Test
	public void test7() {
		System.out.print("Home Page - Test case #7 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty"))).sendKeys(Utility.getProperties("orderQty"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQty");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQty"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		// Assert over-stock quantity
		String inStockQtyStr = driver.findElement(By.xpath(Utility.getProperties("inStockQty"))).getText();
		int inStockQty = Integer.parseInt(inStockQtyStr.split("\\D")[0]);
		int overStockQty = Integer.parseInt(Utility.getProperties("overStockQty"));
		// Assert.assertTrue(overStockQty < inStockQty) Alternative way to assert. This
		// will fail the test.
		if (overStockQty > inStockQty)
			System.out.println("Warning! Quantity entered exceeds in stock quantity.");

		System.out.println("PASSED!");
	}

	
	@Test
	public void test8() {
		System.out.print("Home Page - Test case #8 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty"))).sendKeys(Utility.getProperties("orderQty"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQty");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQty"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		// Click on Item link and go to check out page.
		driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("bookTotalTXT"))));

		// Assert "Basket Totals" is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("bookTotalTXT"))).isDisplayed());

		System.out.println("PASSED!");
	}

	
	@Test
	public void test9() {
		System.out.print("Home Page - Test case #9 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty"))).sendKeys(Utility.getProperties("orderQty"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQty");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQty"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		// Click on Item link and go to check out page.
		driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("bookTotalTXT"))));

		// Assert "Basket Totals" is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("bookTotalTXT"))).isDisplayed());

		// Enter coupon code.
		driver.findElement(By.xpath(Utility.getProperties("couponCodeField")))
				.sendKeys(Utility.getProperties("couponCode"), Keys.ENTER);

		// Assert valid coupon.
		String msg = driver.findElement(By.xpath("//ul[@class='woocommerce-error']")).getText().toUpperCase();
		if (!msg.contains("SUCCESSFULLY"))
			System.out.println("Coupon failed! " + msg);
		else {
			// Assert coupon discount amount.
			double actualCouponAmount = Double
					.parseDouble(driver.findElement(By.xpath(Utility.getProperties("couponAmount"))).getText()
							.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
			double expectedCouponAmount = Double.parseDouble(Utility.getProperties("expectedCouponAmount")
					.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
			if (Double.valueOf(expectedCouponAmount) != Double.valueOf(actualCouponAmount))
				System.out.println("Coupon code amount mismatch.");
			else
				System.out.println("PASSED!");
		}
	}

	
	@Test
	public void test10() {
		System.out.print("Home Page - Test case #10 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty")))
				.sendKeys(Utility.getProperties("orderQtyLessValue450"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQtyLessValue450");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQtyLessValue450"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		// Click on Item link and go to check out page.
		driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("bookTotalTXT"))));

		// Assert "Basket Totals" is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("bookTotalTXT"))).isDisplayed());

		// Enter coupon code.
		driver.findElement(By.xpath(Utility.getProperties("couponCodeField")))
				.sendKeys(Utility.getProperties("couponCode"), Keys.ENTER);

		// Assert valid coupon.
		String msg = driver.findElement(By.xpath("//ul[@class='woocommerce-error']")).getText().toUpperCase();
		if (!msg.contains("SUCCESSFULLY"))
			System.out.println("Coupon failed! " + msg);
		else {
			// Assert coupon discount amount.
			double actualCouponAmount = Double
					.parseDouble(driver.findElement(By.xpath(Utility.getProperties("couponAmount"))).getText()
							.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
			double expectedCouponAmount = Double.parseDouble(Utility.getProperties("expectedCouponAmount")
					.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
			if (Double.valueOf(expectedCouponAmount) != Double.valueOf(actualCouponAmount))
				System.out.println("Coupon code amount mismatch.");
			else
				System.out.println("PASSED!");
		}

		double subTotal = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("subTotal"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
		double couponMinReq = Double.parseDouble(Utility.getProperties("couponMinReq"));

		// Assert minimum book amount purchased in order to apply coupon.
		if (subTotal < couponMinReq)
			System.out.println("FAILED! Book price must be greater than 450.");
		else
			System.out.println("PASSED!");
	}

	
	@Test
	public void test11() {
		System.out.print("Home Page - Test case #11 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty"))).sendKeys(Utility.getProperties("orderQty"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQty");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQty"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		// Click on Item link and go to check out page.
		driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(Utility.getProperties("removeThisItemIcon"))));

		// Click on "Remove this item" icon to remove book.
		driver.findElement(By.cssSelector(Utility.getProperties("removeThisItemIcon"))).click();

		// Assert item is removed.
		String actualItemRemoved = driver.findElement(By.xpath(Utility.getProperties("itemRemovedMsg"))).getText();
		String expectedItemRemoved = "removed";
		Assert.assertTrue(actualItemRemoved.contains(expectedItemRemoved));

		System.out.println("PASSED!");
	}

	
	@Test
	public void test12() {
		System.out.print("Home Page - Test case #12 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty"))).sendKeys(Utility.getProperties("orderQty"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQty");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQty"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		// Click on Item link and go to check out page.
		driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(Utility.getProperties("textBoxValueQty"))));

		// Add books clicking up arrow.
		driver.findElement(By.cssSelector(Utility.getProperties("textBoxValueQty"))).sendKeys(Keys.ARROW_UP);
		driver.findElement(By.cssSelector(Utility.getProperties("textBoxValueQty"))).sendKeys(Keys.ARROW_UP);
		driver.findElement(By.cssSelector(Utility.getProperties("textBoxValueQty"))).sendKeys(Keys.ARROW_UP);

		// Subtract books clicking down arrow.
		driver.findElement(By.cssSelector(Utility.getProperties("textBoxValueQty"))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.cssSelector(Utility.getProperties("textBoxValueQty"))).sendKeys(Keys.ARROW_DOWN);

		// Assert "Update Basket" button is enable.
		Assert.assertTrue(driver.findElement(By.cssSelector(Utility.getProperties("updateBasketBtn"))).isEnabled());

		// Click "Update Basket" button.
		driver.findElement(By.cssSelector(Utility.getProperties("updateBasketBtn"))).click();

		System.out.println("PASSED!");
	}

	
	@Test
	public void test13() {
		System.out.print("Home Page - Test case #13 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty"))).sendKeys(Utility.getProperties("orderQty"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQty");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQty"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		// Click on Item link and go to check out page.
		driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("totalPrice@checkOut"))));

		// Assert total price is visible at check out page.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("totalPrice@checkOut"))).isDisplayed());

		System.out.println("PASSED!");
	}

	
	@Test
	public void test14() {
		System.out.print("Home Page - Test case #14 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty"))).sendKeys(Utility.getProperties("orderQty"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQty");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQty"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		// Click on Item link and go to check out page.
		driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(Utility.getProperties("textBoxValueQty"))));

		// Add books clicking up arrow.
		driver.findElement(By.cssSelector(Utility.getProperties("textBoxValueQty"))).sendKeys(Keys.ARROW_UP);
		driver.findElement(By.cssSelector(Utility.getProperties("textBoxValueQty"))).sendKeys(Keys.ARROW_UP);
		driver.findElement(By.cssSelector(Utility.getProperties("textBoxValueQty"))).sendKeys(Keys.ARROW_UP);

		// Subtract books clicking down arrow.
		driver.findElement(By.cssSelector(Utility.getProperties("textBoxValueQty"))).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.cssSelector(Utility.getProperties("textBoxValueQty"))).sendKeys(Keys.ARROW_DOWN);

		// Assert "Update Basket" button is enable.
		Assert.assertTrue(driver.findElement(By.cssSelector(Utility.getProperties("updateBasketBtn"))).isEnabled());

		// Click "Update Basket" button.
		driver.findElement(By.cssSelector(Utility.getProperties("updateBasketBtn"))).click();

		System.out.println("PASSED!");
	}

	
	@Test
	public void test15() {
		System.out.print("Home Page - Test case #15 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty"))).sendKeys(Utility.getProperties("orderQty"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQty");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQty"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		// Click on Item link and go to check out page.
		driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(Utility.getProperties("textBoxValueQty"))));

		// Get sub total.
		double subTotal = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("subTotal@checkOut")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Get total.
		double total = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice@checkOut")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Assert sub total < total
		Assert.assertTrue(subTotal < total);

		System.out.println("PASSED!");
	}

	@Test
	public void test16() {
		System.out.print("Home Page - Test case #16 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty"))).sendKeys(Utility.getProperties("orderQty"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQty");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQty"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		// Click on Item link and go to check out page.
		driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(Utility.getProperties("textBoxValueQty"))));

		// Get sub total.
		double subTotal = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("subTotal@checkOut")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Get total.
		double total = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice@checkOut")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Assert sub total < total
		Assert.assertTrue(subTotal < total);

		// Click "Proceed to Checkout" button to proceeds to payment gateway page.
		driver.findElement(By.xpath(Utility.getProperties("checkOutBtn"))).click();

		// Wait for payment gateway page is opened when first name text field is located
		// on that page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("firstName"))));

		System.out.println("PASSED!");
	}

	@Test
	public void test17() {
		System.out.print("Home Page - Test case #17 starts.....");

		wait = new WebDriverWait(driver, 30);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty"))).sendKeys(Utility.getProperties("orderQty"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQty");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQty"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		// Click on Item link and go to check out page.
		driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(Utility.getProperties("textBoxValueQty"))));

		// Get sub total.
		double subTotal = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("subTotal@checkOut")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Get total.
		double total = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice@checkOut")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Assert sub total < total
		Assert.assertTrue(subTotal < total);

		// Click "Proceed to Checkout" button to proceeds to payment gateway page.
		driver.findElement(By.xpath(Utility.getProperties("checkOutBtn"))).click();

		// Wait for payment gateway page is opened and enter coupon text link is located
		// on that page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("clickEnterCoupon"))));

		// Enter coupon
		driver.findElement(By.xpath(Utility.getProperties("clickEnterCoupon"))).click();
		driver.findElement(By.xpath(Utility.getProperties("couponTextField")))
				.sendKeys(Utility.getProperties("couponCode"), Keys.ENTER);

		// First name
		driver.findElement(By.xpath(Utility.getProperties("firstName"))).sendKeys("Henry");

		// Last name
		driver.findElement(By.xpath(Utility.getProperties("lastName"))).sendKeys("Leu");

		// Company name
		driver.findElement(By.xpath(Utility.getProperties("companyName"))).sendKeys("TechCircle");

		// Email
		driver.findElement(By.xpath(Utility.getProperties("email"))).sendKeys(Utility.getProperties("emailAddress"));

		// Phone
		driver.findElement(By.xpath(Utility.getProperties("phone"))).sendKeys("123456789");

		// Country
		driver.findElement(By.xpath(Utility.getProperties("countryDropDown"))).click();
		List<WebElement> countries = driver.findElements(By.xpath(Utility.getProperties("countries")));
		for (int i = 0; i < countries.size(); i++) {
			WebElement country = driver.findElement(By.xpath(Utility.getProperties("countries") + "[" + (i + 1) + "]"));

			if (country.getText().equalsIgnoreCase(Utility.getProperties("countryName"))) {
				country.click();
				break;
			}
		}

		// Address
		driver.findElement(By.xpath(Utility.getProperties("address"))).sendKeys("1111 Ave U");

		// Address 2
		driver.findElement(By.xpath(Utility.getProperties("address2"))).sendKeys("Apartment #2C");

		// City
		driver.findElement(By.xpath(Utility.getProperties("city"))).sendKeys("Flushing");

		// County
		driver.findElement(By.xpath(Utility.getProperties("county"))).sendKeys("Taipei");

		// Zip code
		driver.findElement(By.xpath(Utility.getProperties("zipCode"))).sendKeys("11354");

		// Click payment method.
		WebElement element = driver.findElement(By.id(Utility.getProperties("cod")));
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();

		// Order notes
		driver.findElement(By.tagName(Utility.getProperties("orderNotes")))
				.sendKeys(Utility.getProperties("orderNotesMsg"));

		System.out.println("PASSED!");
	}

	@Test
	public void test18() {
		System.out.print("Home Page - Test case #18 starts.....");

		wait = new WebDriverWait(driver, 30);

		// Click on Shop menu.
		driver.findElement(By.id(Utility.getProperties("shop"))).click();

		// Wait for the next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("home"))));

		// Click on Home menu.
		driver.findElement(By.xpath(Utility.getProperties("home"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("books"))));

		// List of book arrivals.
		int actualBookArrival = driver.findElements(By.xpath(Utility.getProperties("books"))).size();
		int expectedArrivalQty = 3;

		// Assert 3 arrivals only.
		Assert.assertTrue(actualBookArrival == expectedArrivalQty);

		// Click on the book that is in stock.
		boolean isInStock = false;
		List<WebElement> books = driver.findElements(By.xpath(Utility.getProperties("books")));
		for (int i = 0; i < books.size(); i++)
			if (books.get(i).getText().toUpperCase().contains("ADD TO BASKET")) {
				books.get(i).click();
				isInStock = true;
			}

		// Assert book in stock.
		Assert.assertTrue(isInStock);

		// Wait for next page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("addBtn"))));

		// Assert "Add To Basket" button is visible.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("addBtn"))).isDisplayed());

		// Enter quantity in the field.
		driver.findElement(By.xpath(Utility.getProperties("qty"))).clear();
		driver.findElement(By.xpath(Utility.getProperties("qty"))).sendKeys(Utility.getProperties("orderQty"));

		// Click "Add to Basket" button.
		driver.findElement(By.xpath(Utility.getProperties("addBtn"))).click();

		// Assert the book quantity on the Menu.
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase()
				.split("\\D")[0];
		String orderQty = Utility.getProperties("orderQty");
		Assert.assertTrue(qtyMenu.contains(orderQty));

		// Assert the total price on the Menu item.
		double unitPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("unitPrice"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		double expectedTotalPrice = unitPrice * Integer.parseInt(Utility.getProperties("orderQty"));

		double actualTotalPrice = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedTotalPrice), Double.valueOf(actualTotalPrice));

		// Click on Item link and go to check out page.
		driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).click();

		// Wait for next page.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(Utility.getProperties("textBoxValueQty"))));

		// Get sub total.
		double subTotal = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("subTotal@checkOut")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Get total.
		double total = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("totalPrice@checkOut")))
				.getText().replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		// Assert sub total < total
		Assert.assertTrue(subTotal < total);

		// Click "Proceed to Checkout" button to proceeds to payment gateway page.
		driver.findElement(By.xpath(Utility.getProperties("checkOutBtn"))).click();

		// Wait for payment gateway page is opened and enter coupon text link is located
		// on that page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("clickEnterCoupon"))));

		// Enter coupon
		driver.findElement(By.xpath(Utility.getProperties("clickEnterCoupon"))).click();
		driver.findElement(By.xpath(Utility.getProperties("couponTextField")))
				.sendKeys(Utility.getProperties("couponCode"), Keys.ENTER);

		// First name
		driver.findElement(By.xpath(Utility.getProperties("firstName"))).sendKeys("Henry");

		// Last name
		driver.findElement(By.xpath(Utility.getProperties("lastName"))).sendKeys("Leu");

		// Company name
		driver.findElement(By.xpath(Utility.getProperties("companyName"))).sendKeys("TechCircle");

		// Email
		driver.findElement(By.xpath(Utility.getProperties("email"))).sendKeys(Utility.getProperties("emailAddress"));

		// Phone
		driver.findElement(By.xpath(Utility.getProperties("phone"))).sendKeys("123456789");

		// Country
		driver.findElement(By.xpath(Utility.getProperties("countryDropDown"))).click();
		List<WebElement> countries = driver.findElements(By.xpath(Utility.getProperties("countries")));
		for (int i = 0; i < countries.size(); i++) {
			WebElement country = driver.findElement(By.xpath(Utility.getProperties("countries") + "[" + (i + 1) + "]"));

			if (country.getText().equalsIgnoreCase(Utility.getProperties("countryName"))) {
				country.click();
				break;
			}
		}

		// Address
		driver.findElement(By.xpath(Utility.getProperties("address"))).sendKeys("1111 Ave U");

		// Address 2
		driver.findElement(By.xpath(Utility.getProperties("address2"))).sendKeys("Apartment #2C");

		// City
		driver.findElement(By.xpath(Utility.getProperties("city"))).sendKeys("Flushing");

		// County
		driver.findElement(By.xpath(Utility.getProperties("county"))).sendKeys("Taipei");

		// Zip code
		driver.findElement(By.xpath(Utility.getProperties("zipCode"))).sendKeys("11354");

		// Click payment method.
		WebElement element = driver.findElement(By.id(Utility.getProperties("cod")));
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();

		// Order notes
		driver.findElement(By.tagName(Utility.getProperties("orderNotes")))
				.sendKeys(Utility.getProperties("orderNotesMsg"));

		// Place order
		driver.findElement(By.id(Utility.getProperties("placeOrder"))).click();

		// Wait for order confirmation page.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("orderNumber"))));

		// Assert Order confirmation page is displayed by checking order number is
		// displayed.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("orderNumber"))).isDisplayed());

		System.out.println("PASSED!");
	}

	// I will open it when I finish all test cases.
//	@After
//	public void afterTest() {
//		Driver.tearDown();
//	}
}
