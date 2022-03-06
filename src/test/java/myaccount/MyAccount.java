package myaccount;

import java.time.Duration;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Utilities.Driver;
import Utilities.Utility;

public class MyAccount {
	private WebDriverWait wait;

	@BeforeMethod
	public void beforeTest() {
		Driver.getDriver();

		Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// This is deprecated in Selenium 4.
		// Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
		
		// This is deprecated in Selenium 4.
		// wait = new WebDriverWait(Driver.getDriver(), 30);
	}

	@Test(priority = 1)
	public void Dashboard() {
		
		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter User email address.
		Driver.getDriver().findElement(By.id(Utility.getProperties("usernameLogIn")))
				.sendKeys(Utility.getProperties("emailForAccountLogIn"));

		// Enter User password.
		Driver.getDriver().findElement(By.id(Utility.getProperties("passwordLogIn")))
				.sendKeys(Utility.getProperties("password"));

		// Click Log In button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("dashboard"))));

		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Assert login in to the dash board page.
		WebElement dashboardPage = Driver.getDriver().findElement(By.xpath(Utility.getProperties("dashboard")));
		Assert.assertTrue(dashboardPage.isDisplayed());
	}

	@Test(priority = 2)
	public void Orders() {
		
		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();
		
		// Enter User email address.
		Driver.getDriver().findElement(By.id(Utility.getProperties("usernameLogIn")))
				.sendKeys(Utility.getProperties("emailForAccountLogIn"));

		// Enter User password.
		Driver.getDriver().findElement(By.id(Utility.getProperties("passwordLogIn")))
				.sendKeys(Utility.getProperties("password"));

		// Click Log In button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("dashboard"))));

		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Assert login in to the dash board page.
		WebElement dashboardPage = Driver.getDriver().findElement(By.xpath(Utility.getProperties("dashboard")));
		Assert.assertTrue(dashboardPage.isDisplayed());

		// Click Orders button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("ordersBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("ordersPageTable"))));

		// User must view their orders on clicking orders link.
		WebElement ordersPageTable = Driver.getDriver().findElement(By.xpath(Utility.getProperties("ordersPageTable")));
		Assert.assertTrue(ordersPageTable.isDisplayed());
	}

	@Test(priority = 3)
	public void OrdersViewButton() {
		
		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter User email address.
		Driver.getDriver().findElement(By.id(Utility.getProperties("usernameLogIn")))
				.sendKeys(Utility.getProperties("emailForAccountLogIn"));

		// Enter User password.
		Driver.getDriver().findElement(By.id(Utility.getProperties("passwordLogIn")))
				.sendKeys(Utility.getProperties("password"));

		// Click Log In button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("dashboard"))));

		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Assert login in to the dash board page.
		WebElement dashboardPage = Driver.getDriver().findElement(By.xpath(Utility.getProperties("dashboard")));
		Assert.assertTrue(dashboardPage.isDisplayed());

		// Click Orders button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("ordersBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("ordersPageTable"))));

		// User must view their orders on clicking orders link.
		WebElement ordersPageTable = Driver.getDriver().findElement(By.xpath(Utility.getProperties("ordersPageTable")));
		Assert.assertTrue(ordersPageTable.isDisplayed());

		// Click on View button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("viewBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("orderDetail"))));

		// User must view his Order details,customer details and billing details.
		WebElement orderDetail = Driver.getDriver().findElement(By.xpath(Utility.getProperties("orderDetail")));
		Assert.assertTrue(orderDetail.isDisplayed());
		WebElement customerDetail = Driver.getDriver().findElement(By.xpath(Utility.getProperties("customerDetail")));
		Assert.assertTrue(customerDetail.isDisplayed());
		WebElement billingAddressDetail = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("billingAddressDetail")));
		Assert.assertTrue(billingAddressDetail.isDisplayed());
	}

	@Test(priority = 4)
	public void OrderNumberDateStatus() {
		
		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter User email address.
		Driver.getDriver().findElement(By.id(Utility.getProperties("usernameLogIn")))
				.sendKeys(Utility.getProperties("emailForAccountLogIn"));

		// Enter User password.
		Driver.getDriver().findElement(By.id(Utility.getProperties("passwordLogIn")))
				.sendKeys(Utility.getProperties("password"));

		// Click Log In button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("dashboard"))));

		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Assert login in to the dash board page.
		WebElement dashboardPage = Driver.getDriver().findElement(By.xpath(Utility.getProperties("dashboard")));
		Assert.assertTrue(dashboardPage.isDisplayed());

		// Click Orders button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("ordersBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("ordersPageTable"))));

		// User must view their orders on clicking orders link.
		WebElement ordersPageTable = Driver.getDriver().findElement(By.xpath(Utility.getProperties("ordersPageTable")));
		Assert.assertTrue(ordersPageTable.isDisplayed());

		// Click on View button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("viewBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("orderDetail"))));

		// User must view Order Number, Ordered date, and Status of the order.
		WebElement orderNumberDetail = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("orderNumberDetail")));
		Assert.assertTrue(orderNumberDetail.isDisplayed());
		WebElement orderDateDetail = Driver.getDriver().findElement(By.xpath(Utility.getProperties("orderDateDetail")));
		Assert.assertTrue(orderDateDetail.isDisplayed());
		WebElement orderStatusDetail = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("orderStatusDetail")));
		Assert.assertTrue(orderStatusDetail.isDisplayed());
	}

	@Test(priority = 5)
	public void Address() {
		
		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter User email address.
		Driver.getDriver().findElement(By.id(Utility.getProperties("usernameLogIn")))
				.sendKeys(Utility.getProperties("emailForAccountLogIn"));

		// Enter User password.
		Driver.getDriver().findElement(By.id(Utility.getProperties("passwordLogIn")))
				.sendKeys(Utility.getProperties("password"));

		// Click Log In button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("dashboard"))));

		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Assert login in to the dash board page.
		WebElement dashboardPage = Driver.getDriver().findElement(By.xpath(Utility.getProperties("dashboard")));
		Assert.assertTrue(dashboardPage.isDisplayed());

		// Click on Addresses menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("addressBtn"))).click();

		// Wait for next page to load.
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("billingAddressDetail"))));

		// User must view billing address and ship address.
		WebElement billingAddressDetail = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("billingAddressDetail")));
		Assert.assertTrue(billingAddressDetail.isDisplayed());
		WebElement shippingAddressDetail = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("shippingAddressDetail")));
		Assert.assertTrue(shippingAddressDetail.isDisplayed());
	}

	@Test(priority = 6)
	public void EditShippingAddress() {
		
		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter User email address.
		Driver.getDriver().findElement(By.id(Utility.getProperties("usernameLogIn")))
				.sendKeys(Utility.getProperties("emailForAccountLogIn"));

		// Enter User password.
		Driver.getDriver().findElement(By.id(Utility.getProperties("passwordLogIn")))
				.sendKeys(Utility.getProperties("password"));

		// Click Log In button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("dashboard"))));

		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Assert login in to the dash board page.
		WebElement dashboardPage = Driver.getDriver().findElement(By.xpath(Utility.getProperties("dashboard")));
		Assert.assertTrue(dashboardPage.isDisplayed());

		// Click on Address menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("addressBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(Utility.getProperties("shippingAddressDetail"))));

		// User can Edit Shipping address.
		WebElement editShippingAddressBtn = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("editShippingAddressBtn")));
		Assert.assertTrue(editShippingAddressBtn.isDisplayed());
	}

	@Test(priority = 7)
	public void AccountDetails() {
		
		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter User email address.
		Driver.getDriver().findElement(By.id(Utility.getProperties("usernameLogIn")))
				.sendKeys(Utility.getProperties("emailForAccountLogIn"));

		// Enter User password.
		Driver.getDriver().findElement(By.id(Utility.getProperties("passwordLogIn")))
				.sendKeys(Utility.getProperties("password"));

		// Click Log In button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("dashboard"))));

		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Assert login in to the dash board page.
		WebElement dashboardPage = Driver.getDriver().findElement(By.xpath(Utility.getProperties("dashboard")));
		Assert.assertTrue(dashboardPage.isDisplayed());

		// Click on Account Details menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("accountDetailsBtn"))).click();

		// Wait for next page to load.
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("currentPasswordField"))));

		// User can view account details where he can change his password also.
		WebElement currentPasswordField = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("currentPasswordField")));
		Assert.assertTrue(currentPasswordField.isEnabled());
	}

	@Test(priority = 8)
	public void LogOut() {
		
		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter User email address.
		Driver.getDriver().findElement(By.id(Utility.getProperties("usernameLogIn")))
				.sendKeys(Utility.getProperties("emailForAccountLogIn"));

		// Enter User password.
		Driver.getDriver().findElement(By.id(Utility.getProperties("passwordLogIn")))
				.sendKeys(Utility.getProperties("password"));

		// Click Log In button.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("dashboard"))));

		// Click on My Account menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Assert login in to the dash board page.
		WebElement dashboardPage = Driver.getDriver().findElement(By.xpath(Utility.getProperties("dashboard")));
		Assert.assertTrue(dashboardPage.isDisplayed());

		// Click on Logout menu.
		Driver.getDriver().findElement(By.xpath(Utility.getProperties("logoutBtn"))).click();

		// Wait for next page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("loginDetailsField"))));

		// On clicking logout,User successfully comes out from the site.
		WebElement loginDetailsField = Driver.getDriver()
				.findElement(By.xpath(Utility.getProperties("loginDetailsField")));
		Assert.assertTrue(loginDetailsField.isDisplayed());
	}

	@AfterMethod
	public void afterTest() {
		Driver.tearDown();
	}
}
