package myaccountregistration;

import java.util.concurrent.TimeUnit;

import javax.swing.KeyStroke;
import javax.swing.JTree.DynamicUtilTreeNode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

public class MyAccountRegistration {
	private WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void beforeTest() {
		driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void test1() throws InterruptedException {

		System.out.print("My Account Registration - Test case #1 starts.....");

		wait = new WebDriverWait(driver, 10);

		// Click on My Account menu.
		driver.findElement(By.xpath(Utility.getProperties("MyAccountBtn"))).click();

		// wait for email text box
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utility.getProperties("emailField"))));

		// Enter valid registered email address.
		driver.findElement(By.id(Utility.getProperties("emailField")))
				.sendKeys(Utility.getProperties("registeredEmail"));

		// Enter valid registered password.
		String password = Utility.getProperties("registeredPassword");

		for (int i = 0; i < password.length(); i++) 
			driver.findElement(By.id(Utility.getProperties("passwordField"))).sendKeys(password.charAt(i) + "");

		// Assert register button is enabled.
		boolean registerBtnClickable = driver.findElement(By.cssSelector(Utility.getProperties("registerBtn")))
				.isEnabled();
		
		// Password is not strong. Register button is not enabled.
	  	Assert.assertTrue("Password is not strong enough!", registerBtnClickable);
		
		// Click on register button.
		driver.findElement(By.cssSelector(Utility.getProperties("registerBtn"))).click();
		
		// User will be registered successfully.
		String expectedRegisterCompleteMsg = Utility.getProperties("RegisterCompleteMsg").toUpperCase();
		String actualRegisterCompleteMsg = driver.findElement(By.xpath(Utility.getProperties("RegisterSuccessfulPage"))).getText().toUpperCase();
		Assert.assertTrue(!actualRegisterCompleteMsg.contains(expectedRegisterCompleteMsg));

		System.out.println("PASSED!");
	}

//	@After
//	public void afterTest() {
//		Driver.tearDown();
//	}
}
