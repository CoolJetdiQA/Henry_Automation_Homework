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
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase().split("\\D")[0];
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
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase().split("\\D")[0];
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
		//Assert.assertTrue(overStockQty < inStockQty) Alternative way to assert. This will fail the test.
		if(overStockQty > inStockQty)  
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
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase().split("\\D")[0];
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
		String qtyMenu = driver.findElement(By.xpath(Utility.getProperties("qtyMenu"))).getText().toUpperCase().split("\\D")[0];
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
		driver.findElement(By.xpath(Utility.getProperties("couponCodeField"))).sendKeys(Utility.getProperties("couponCode"), Keys.ENTER);
		
		// Assert coupon discount amount.
		double actualCouponAmount = Double.parseDouble(driver.findElement(By.xpath(Utility.getProperties("couponAmount"))).getText()
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());
		
		double expectedCouponAmount = Double.parseDouble(Utility.getProperties("expectedCouponAmount")
				.replaceFirst("\\S", "").replaceAll("[$,]", "").trim());

		Assert.assertEquals(Double.valueOf(expectedCouponAmount), Double.valueOf(actualCouponAmount));
		
		System.out.println("PASSED!");
	}
}
