package myaccountregistration;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utilities.Driver;
import Utilities.Utility;

public class MyAccountRegistration {
	private WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void beforeTest() {
		driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
	}
	
	@Test
	public void signIn(){

		System.out.print("My Account Registration - Sign-In: Test case #1 starts.....");

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// wait for email text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utility.getProperties("emailField"))));

		// Enter valid new email address. Email never registered before.
		driver.findElement(By.id(Utility.getProperties("emailField")))
				.sendKeys(Utility.getProperties("registeredEmail"));

		// Enter valid registered password.
		String password = Utility.getProperties("registeredPassword");

		for (int i = 0; i < password.length(); i++) {
			driver.findElement(By.id(Utility.getProperties("passwordField"))).sendKeys(password.charAt(i) + "");
			for(int j = 0; j < 1000; j++) {}
		}

		// Register button is enabled or disabled.
		boolean registerBtnClickable = driver.findElement(By.cssSelector(Utility.getProperties("registerBtn")))
				.isEnabled();
		
		// Password is not strong. Register button is not enabled.
	  	Assert.assertTrue("Password is not strong enough!", registerBtnClickable);
		
		// Click on register button.
		driver.findElement(By.cssSelector(Utility.getProperties("registerBtn"))).click();
		
		// Wait for registered complete page to load.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Utility.getProperties("registerSuccessfulPage"))));		
		
		// User will be registered successfully.
		String expectedRegisteredSuccessfullyErrorMsg = Utility.getProperties("expectedRegisteredSuccessfullyErrorMsg").toUpperCase();
		String actualRegisterCompleteMsg = driver.findElement(By.xpath(Utility.getProperties("registerSuccessfulPage"))).getText().toUpperCase();
		Assert.assertTrue(!actualRegisterCompleteMsg.contains(expectedRegisteredSuccessfullyErrorMsg));

		System.out.println("PASSED!");
	}
	
	@Test
	public void invalidEmail(){

		System.out.print("My Account Registration - Invalid Email: Test case #2 starts.....");

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// wait for email text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utility.getProperties("emailField"))));

		// Enter invalid email address.
		driver.findElement(By.id(Utility.getProperties("emailField")))
				.sendKeys(Utility.getProperties("invalidEmail"));

		// Enter valid password.
		String password = Utility.getProperties("registeredPassword");

		for (int i = 0; i < password.length(); i++) {
			driver.findElement(By.id(Utility.getProperties("passwordField"))).sendKeys(password.charAt(i) + "");
			for(int j = 0; j < 1000; j++) {}
		}

		// Register button is enabled or disabled.
		boolean registerBtnClickable = driver.findElement(By.cssSelector(Utility.getProperties("registerBtn")))
				.isEnabled();
		
		// Password is not strong. Register button is not enabled.
	  	Assert.assertTrue("Password is not strong enough!", registerBtnClickable);
		
		// Click on register button.
		driver.findElement(By.cssSelector(Utility.getProperties("registerBtn"))).click();
		
		String actualEmailErrorMsg = driver.findElement(By.xpath(Utility.getProperties("errorMsgLabel"))).getText().toUpperCase();
		String expectedEmailErrorMsg = Utility.getProperties("expectedEmailErrorMsg").toUpperCase();
		
		// Assert invalid email error message. Email error message must match for test to pass.
		Assert.assertTrue("TEST FAILED! Actual and expected email error messages do not match.",
				actualEmailErrorMsg.contains(expectedEmailErrorMsg));

		System.out.println("PASSED!");
	}
	
	@Test
	public void emptyEmail(){

		System.out.print("My Account Registration - Empty Email: Test case #3 starts.....");

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// wait for email text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utility.getProperties("emailField"))));

		// Enter an empty email.
		driver.findElement(By.id(Utility.getProperties("emailField")))
				.sendKeys("");

		// Enter valid registered password.
		String password = Utility.getProperties("registeredPassword");

		for (int i = 0; i < password.length(); i++) {
			driver.findElement(By.id(Utility.getProperties("passwordField"))).sendKeys(password.charAt(i) + "");
			for(int j = 0; j < 1000; j++) {}
		}

		// Register button is enabled or disabled.
		boolean registerBtnClickable = driver.findElement(By.cssSelector(Utility.getProperties("registerBtn")))
				.isEnabled();
		
		// Password is not strong. Register button is not enabled.
	  	Assert.assertTrue("Password is not strong enough!", registerBtnClickable);
		
		// Click on register button.
		driver.findElement(By.cssSelector(Utility.getProperties("registerBtn"))).click();
		
		String actualEmailErrorMsg = driver.findElement(By.xpath(Utility.getProperties("errorMsgLabel"))).getText().toUpperCase();
		String expectedEmailErrorMsg = Utility.getProperties("expectedEmailErrorMsg").toUpperCase();
		
		// Assert invalid email error message. Email error message must match for test to pass.
		Assert.assertTrue("TEST FAILED! Actual and expected email error messages do not match.",
				actualEmailErrorMsg.contains(expectedEmailErrorMsg));

		System.out.println("PASSED!");
	}
	
	@Test
	public void emptyPassword(){

		System.out.print("My Account Registration - Empty Password: Test case #4 starts.....");

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// wait for email text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utility.getProperties("emailField"))));

		// Enter valid email. Email never registered before.
		driver.findElement(By.id(Utility.getProperties("emailField")))
				.sendKeys(Utility.getProperties("registeredEmail"));

		// Enter an empty password.
		driver.findElement(By.id(Utility.getProperties("passwordField"))).sendKeys("");
		
		// Click on register button.
		driver.findElement(By.cssSelector(Utility.getProperties("registerBtn"))).click();
		
		String actualPasswordErrorMsg = driver.findElement(By.xpath(Utility.getProperties("errorMsgLabel"))).getText().toUpperCase();
		String expectedPasswordErrorMsg = Utility.getProperties("expectedPasswordErrorMsg").toUpperCase();
		
		// Assert empty password error message. Password error message must match for test to pass.
		Assert.assertTrue("TEST FAILED! Actual and expected password error messages do not match.",
				actualPasswordErrorMsg.contains(expectedPasswordErrorMsg));

		System.out.println("PASSED!");
	}
	
	@Test
	public void emptyEmailPassword(){

		System.out.print("My Account Registration - Empty Email & Password: Test case #5 starts.....");

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
		
		String actualErrorMsg = driver.findElement(By.xpath(Utility.getProperties("errorMsgLabel"))).getText().toUpperCase();
		String expectedErrorMsg = Utility.getProperties("expectedEmailErrorMsg").toUpperCase();
		
		// Assert email error message.
		// Email error message must match for test to pass.
		Assert.assertTrue("TEST FAILED! Actual and expected email error messages do not match.",
				actualErrorMsg.contains(expectedErrorMsg));

		System.out.println("PASSED!");
	}

	@After
	public void afterTest() {
		Driver.tearDown();
	}
}
