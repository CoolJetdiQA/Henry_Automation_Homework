package Utilities;

import javax.swing.text.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import okhttp3.internal.Util;

public class Driver {
	public static WebDriver driver;

	private Driver() {

	}

	public static WebDriver getDriver() {
		if(driver == null) {
			switch(Utility.getProperties("browser")) {
			case "firefox": WebDriverManager.firefoxdriver().setup();
							driver = new FirefoxDriver();
							break;
			case "chrome":  WebDriverManager.chromedriver().setup();
							driver = new ChromeDriver();
							break;
			case "safari":  WebDriverManager.safaridriver().setup();
			                driver = new SafariDriver();
			                break;		
			}
		}
		driver.get(Utility.getProperties("url"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		return driver; 
	}
	
	// CLose quit browser
	public static void tearDown() {
		if(driver != null) {
			driver.close();
			driver = null;
		}
	}

}
