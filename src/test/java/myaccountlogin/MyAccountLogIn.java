package myaccountlogin;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Utilities.Driver;
import Utilities.Utility;

public class MyAccountLogIn {
	private WebDriver driver;

	@BeforeMethod
	public void beforeTest() {
		driver = Driver.getDriver();
		
		Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		// This is deprecated in Selenium 4.
		// Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test (priority = 1)
	public void validUsernamePassword() {
		
		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter User email address.
		driver.findElement(By.id(Utility.getProperties("usernameLogIn"))).sendKeys(Utility.getProperties("emailForAccountLogIn"));

		// Enter User password.
		driver.findElement(By.id(Utility.getProperties("passwordLogIn"))).sendKeys(Utility.getProperties("password"));

		// Click Log In button.
		driver.findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Assert login in to the next page.
		String actualPageTitle = driver.getTitle().toLowerCase();
		String expectedPageTitle = Utility.getProperties("expectedPageTitle").toLowerCase();
		Assert.assertTrue(actualPageTitle.contains(expectedPageTitle), Utility.getProperties("pageLoadError"));
	}

	@Test(priority = 2)
	public void invalidUsernamePassword() {

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter incorrect user email address.
		driver.findElement(By.id(Utility.getProperties("usernameLogIn")))
				.sendKeys(Utility.getProperties("incorrectEmail"));

		// Enter incorrect user password.
		driver.findElement(By.id(Utility.getProperties("passwordLogIn")))
				.sendKeys(Utility.getProperties("incorrectPassword"));

		// Click Log In button.
		driver.findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Assert invalid email/password error message is displayed.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("errorLabel"))).isDisplayed());
	}

	@Test(priority = 3)
	public void validUsernameEmptyPassword() {

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter valid user email address.
		driver.findElement(By.id(Utility.getProperties("usernameLogIn"))).sendKeys(Utility.getProperties("emailForAccountLogIn"));

		// Enter empty user password.
		driver.findElement(By.id(Utility.getProperties("passwordLogIn"))).sendKeys("");

		// Click Log In button.
		driver.findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Assert invalid email/password error message is displayed.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("errorLabel"))).isDisplayed());
	}
	
	@Test(priority = 4)
	public void emptyUsernameValidPassword() {

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter empty user email address.
		driver.findElement(By.id(Utility.getProperties("usernameLogIn"))).sendKeys("");

		// Enter valid user password.
		driver.findElement(By.id(Utility.getProperties("passwordLogIn"))).sendKeys(Utility.getProperties("password"));

		// Click Log In button.
		driver.findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Assert invalid email/password error message is displayed.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("errorLabel"))).isDisplayed());
	}
	
	@Test(priority = 5)
	public void emptyUsernamePassword() {

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter empty user email address.
		driver.findElement(By.id(Utility.getProperties("usernameLogIn"))).sendKeys("");

		// Enter valid user password.
		driver.findElement(By.id(Utility.getProperties("passwordLogIn"))).sendKeys("password");

		// Click Log In button.
		driver.findElement(By.xpath(Utility.getProperties("logInBtn"))).click();

		// Assert invalid email/password error message is displayed.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("errorLabel"))).isDisplayed());
	}
	
	@Test(priority = 6)
	public void passwordMasked() {

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter some character in the user password field.
		driver.findElement(By.id(Utility.getProperties("passwordLogIn"))).sendKeys(Utility.getProperties("password"));

		// Verify if password is masked.
		WebElement element = driver.findElement(By.id(Utility.getProperties("passwordLogIn")));
		Assert.assertTrue(element.getAttribute("type").equalsIgnoreCase(Utility.getProperties("passwordLogIn")));
	}
	
	@Test(priority = 7)
	public void handleCaseSensitive() {

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter changed case user email address.
		driver.findElement(By.id(Utility.getProperties("usernameLogIn")))
		      .sendKeys(Utility.getProperties("emailForAccountLogIn").toUpperCase());

		// Enter changed case user password field.
		driver.findElement(By.id(Utility.getProperties("passwordLogIn")))
				.sendKeys(Utility.getProperties("password").toUpperCase());

		// Click Log In button.
		driver.findElement(By.xpath(Utility.getProperties("logInBtn"))).click();
		
		// Assert invalid email/password error message is displayed.
		Assert.assertTrue(driver.findElement(By.xpath(Utility.getProperties("errorLabel"))).isDisplayed());
	}
	
	@Test(priority = 8)
	public void authentication() {

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter valid user email address.
		driver.findElement(By.id(Utility.getProperties("usernameLogIn")))
		      .sendKeys(Utility.getProperties("emailForAccountLogIn"));

		// Enter valid user password field.
		driver.findElement(By.id(Utility.getProperties("passwordLogIn")))
				.sendKeys(Utility.getProperties("password"));

		// Click Log In button.
		driver.findElement(By.xpath(Utility.getProperties("logInBtn"))).click();
		
		// Click Logout button.
		driver.findElement(By.xpath(Utility.getProperties("signOutBtn"))).click();
		
		// Navigate back to the page.
		driver.navigate().back();
		
		// User shouldn¡¯t be signed in to his account rather a general web page must be visible.
		String expectedTitle = "My Account ¨C Automation Practice Site"; 
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@AfterMethod
	public void afterTest() {
		Driver.tearDown();
	}
}
