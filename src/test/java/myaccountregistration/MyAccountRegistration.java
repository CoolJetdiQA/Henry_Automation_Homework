package myaccountregistration;

import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import Utilities.Driver;
import Utilities.Utility;

public class MyAccountRegistration {
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeMethod
	public void beforeTest() {
		driver = Driver.getDriver();

		Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// This is deprecated in Selenium 4.
		// Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
		// This is deprecated in Selenium 4.
		// wait = new WebDriverWait(Driver.getDriver(), 30);
	}

	@Test(priority = 1)
	public void signIn() {

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// wait for email text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utility.getProperties("emailField"))));

		// Java Faker to generate random email address.
		FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());
		String email = fakeValuesService.bothify("????##@gmail.com");		
		
		// Enter valid new email address. Email never registered before.
		driver.findElement(By.id(Utility.getProperties("emailField"))).sendKeys(email);

		// Enter valid registered password.
		String password = Utility.getProperties("registeredPassword");

		for (int i = 0; i < password.length(); i++) {
			driver.findElement(By.id(Utility.getProperties("passwordField"))).sendKeys(password.charAt(i) + "");
			for (int j = 0; j < 1000; j++) {}
		}

		// Register button is enabled or disabled.
		boolean registerBtnClickable = driver.findElement(
				By.cssSelector(Utility.getProperties("registerBtn"))).isEnabled();

		// Password is not strong. Register button is not enabled.
		Assert.assertTrue(registerBtnClickable, Utility.getProperties("passwordErrorMsg"));

		// Click on register button.
		driver.findElement(By.cssSelector(Utility.getProperties("registerBtn"))).click();

		// Wait for registered complete page to load.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(Utility.getProperties("registerSuccessfulPage"))));

		// User will be registered successfully.
		String expectedRegisteredSuccessfullyErrorMsg = Utility.getProperties("expectedRegisteredSuccessfullyErrorMsg").toUpperCase();
		String actualRegisterCompleteMsg = driver.findElement(By.xpath(Utility.getProperties("registerSuccessfulPage"))).getText().toUpperCase();
		Assert.assertTrue(!actualRegisterCompleteMsg.contains(expectedRegisteredSuccessfullyErrorMsg));
	}

	@Test(priority = 2)
	public void invalidEmail() {

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// wait for email text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utility.getProperties("emailField"))));

		// Enter invalid email address.
		driver.findElement(By.id(Utility.getProperties("emailField"))).sendKeys(Utility.getProperties("invalidEmail"));

		// Enter valid password.
		String password = Utility.getProperties("registeredPassword");

		for (int i = 0; i < password.length(); i++) {
			driver.findElement(By.id(Utility.getProperties("passwordField"))).sendKeys(password.charAt(i) + "");
			for (int j = 0; j < 1000; j++) {}
		}

		// Register button is enabled or disabled.
		boolean registerBtnClickable = driver.findElement(
				By.cssSelector(Utility.getProperties("registerBtn"))).isEnabled();

		// Password is not strong. Register button is not enabled.
		Assert.assertTrue(registerBtnClickable, Utility.getProperties("passwordErrorMsg"));

		// Click on register button.
		driver.findElement(By.cssSelector(Utility.getProperties("registerBtn"))).click();

		String actualEmailErrorMsg = driver.findElement(
				By.xpath(Utility.getProperties("errorMsgLabel"))).getText().toUpperCase();
		
		String expectedEmailErrorMsg = Utility.getProperties("expectedEmailErrorMsg").toUpperCase();

		// Assert invalid email error message. Email error message must match for test to pass.
		Assert.assertTrue(actualEmailErrorMsg.contains(expectedEmailErrorMsg), Utility.getProperties("invalidEmailErroMsg"));
	}

	@Test(priority = 3)
	public void emptyEmail() {

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// wait for email text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utility.getProperties("emailField"))));

		// Enter an empty email.
		driver.findElement(By.id(Utility.getProperties("emailField"))).sendKeys("");

		// Enter valid registered password.
		String password = Utility.getProperties("registeredPassword");

		for (int i = 0; i < password.length(); i++) {
			driver.findElement(By.id(Utility.getProperties("passwordField"))).sendKeys(password.charAt(i) + "");
			for (int j = 0; j < 1000; j++) {}
		}

		// Register button is enabled or disabled.
		boolean registerBtnClickable = driver.findElement(
				By.cssSelector(Utility.getProperties("registerBtn"))).isEnabled();

		// Password is not strong. Register button is not enabled.
		Assert.assertTrue(registerBtnClickable, Utility.getProperties("passwordErrorMsg"));

		// Click on register button.
		driver.findElement(By.cssSelector(Utility.getProperties("registerBtn"))).click();

		String actualEmailErrorMsg = driver.findElement(
				By.xpath(Utility.getProperties("errorMsgLabel"))).getText().toUpperCase();
		
		String expectedEmailErrorMsg = Utility.getProperties("expectedEmailErrorMsg").toUpperCase();

		// Assert invalid email error message. Email error message must match for test to pass.
		Assert.assertTrue(actualEmailErrorMsg.contains(expectedEmailErrorMsg), Utility.getProperties("invalidEmailErroMsg"));
	}

	@Test(priority = 4)
	public void emptyPassword() {

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// wait for email text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utility.getProperties("emailField"))));

		// Java Faker to generate random email address.
		FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());
		String email = fakeValuesService.bothify("????##@gmail.com");	
		
		// Enter valid email. Email never registered before.
		driver.findElement(By.id(Utility.getProperties("emailField"))).sendKeys(email);

		// Enter an empty password.
		driver.findElement(By.id(Utility.getProperties("passwordField"))).sendKeys("");

		// Click on register button.
		driver.findElement(By.cssSelector(Utility.getProperties("registerBtn"))).click();

		String actualPasswordErrorMsg = driver.findElement(
				By.xpath(Utility.getProperties("errorMsgLabel"))).getText().toUpperCase();
		
		String expectedPasswordErrorMsg = Utility.getProperties("expectedPasswordErrorMsg").toUpperCase();

		// Assert empty password error message. Password error message must match for test to pass.
		Assert.assertTrue(actualPasswordErrorMsg.contains(expectedPasswordErrorMsg), Utility.getProperties("invalidEmailErroMsg"));
	}

	@Test(priority = 5)
	public void emptyEmailPassword() {

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// wait for email text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utility.getProperties("emailField"))));

		// Enter an empty email.
		driver.findElement(By.id(Utility.getProperties("emailField"))).sendKeys("");

		// Enter an empty password.
		driver.findElement(By.id(Utility.getProperties("passwordField"))).sendKeys("");

		// Click on register button.
		driver.findElement(By.cssSelector(Utility.getProperties("registerBtn"))).click();

		String actualErrorMsg = driver.findElement(
				By.xpath(Utility.getProperties("errorMsgLabel"))).getText().toUpperCase();
		
		String expectedErrorMsg = Utility.getProperties("expectedEmailErrorMsg").toUpperCase();

		// Assert email error message.
		// Email error message must match for test to pass.
		Assert.assertTrue(actualErrorMsg.contains(expectedErrorMsg), Utility.getProperties("invalidEmailErroMsg"));
	}

	@AfterMethod
	public void afterTest() {
		Driver.tearDown();
	}
}
