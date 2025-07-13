package base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.CommonUtils;

public class Base {

	WebDriver driver;
	String browserName;
	
	public WebDriver openBrowserAndApplication() {

		Properties prop = CommonUtils.loadProperties();
		browserName = prop.getProperty("browserName");

		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(prop.getProperty("appURL"));

		return driver;
	}

	public String getBrowser() {
		return browserName;
	}
}
