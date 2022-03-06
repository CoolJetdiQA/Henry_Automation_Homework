package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
	public static WebDriver driver;

	public static WebDriver getDriver() {
		if(driver == null) {
			switch(Utility.getProperties("browser")) {
			case "firefox": WebDriverManager.firefoxdriver().setup();
							driver = new FirefoxDriver();
							driver.get(Utility.getProperties("url"));
							driver.manage().window().maximize();
							driver.manage().deleteAllCookies();
							//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							break;
			case "chrome":  WebDriverManager.chromedriver().setup();
							//ChromeOptions c = new ChromeOptions();
							//c.addArguments("--no-sandbox");
							//c.addArguments("--disable-setuid-sandbox");
							driver = new ChromeDriver();
							driver.get(Utility.getProperties("url"));
							driver.manage().window().maximize();
							driver.manage().deleteAllCookies();
							//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							break;	
			}
		}
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
