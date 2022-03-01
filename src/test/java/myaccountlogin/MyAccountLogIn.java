/*  	
 	Class: TechCircle Batch #9
 	Date: February 26, 2022
 	Student: Ming-Jen Leu (Henry Leu)
 	Programming language: Java 11
 	Filename: MyAccountLogIn.java
 	
  My Account - Log In automation testing.
  Test cases #1 to #8.
  
 */

package myaccountlogin;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Utilities.Driver;
import Utilities.Utility;

public class MyAccountLogIn {
	private WebDriver driver;

	@Before
	public void beforeTest() {
		driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void validUsernamePassword() {
		System.out.print("My Account: Log In - Valid Username & Password: Test case #1 starts.....");

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
		Assert.assertTrue(Utility.getProperties("pageLoadError"), actualPageTitle.contains(expectedPageTitle));

		System.out.println("PASS!");
	}

	@Test
	public void invalidUsernamePassword() {
		System.out.print("My Account: Log In - Invalid Username & Password: Test case #2 starts.....");

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

		System.out.println("PASS!");
	}

	@Test
	public void validUsernameEmptyPassword() {
		System.out.print("My Account: Log In - Valid Username & Empty Password: Test case #3 starts.....");

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

		System.out.println("PASS!");
	}
	
	@Test
	public void emptyUsernameValidPassword() {
		System.out.print("My Account: Log In - Empty Username & Valid Password: Test case #4 starts.....");

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

		System.out.println("PASS!");
	}
	
	@Test
	public void emptyUsernamePassword() {
		System.out.print("My Account: Log In - Empty Username & Password: Test case #5 starts.....");

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

		System.out.println("PASS!");
	}
	
	@Test
	public void passwordMasked() {
		System.out.print("My Account: Log In - Password Should Be Masked: Test case #6 starts.....");

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// Enter some character in the user password field.
		driver.findElement(By.id(Utility.getProperties("passwordLogIn"))).sendKeys(Utility.getProperties("password"));

		// Verify if password is masked.
		WebElement element = driver.findElement(By.id(Utility.getProperties("passwordLogIn")));
		Assert.assertTrue(element.getAttribute("type").equalsIgnoreCase(Utility.getProperties("passwordLogIn")));

		System.out.println("PASS!");
	}
	
	@Test
	public void handleCaseSensitive() {
		System.out.print("My Account: Log In - Handle Case Sensitive: Test case #7 starts.....");

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

		System.out.println("PASS!");
	}
	
	@Test
	public void authentication() {
		System.out.print("My Account: Log In - Authentication: Test case #8 starts.....");

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

		System.out.println("PASS!");
	}
	
	@After
	public void afterTest() {
		Driver.tearDown();
	}
}
