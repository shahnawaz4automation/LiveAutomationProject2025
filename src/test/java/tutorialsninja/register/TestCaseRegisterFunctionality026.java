package tutorialsninja.register;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import utils.CommonUtils;

public class TestCaseRegisterFunctionality026 extends Base {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {

		driver = openBrowserAndApplication();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='My Account']")));
		driver.findElement(By.linkText("Register")).click();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void verifyUIOfRegisterAccountPage() throws IOException, InterruptedException {

		// https://drive.google.com/file/d/1X6EPJW-Ojl3Xpv99qrnOV4wU8FuekmtO/view

		/*
		 * ChromeOptions options = new ChromeOptions();
		 * options.addArguments("--headless=new"); // Use --headless=new instead of
		 * --headless in latest Chrome options.addArguments("--window-size=2500,1300");
		 * WebDriver driver = new ChromeDriver(options);
		 */

		driver.findElement(By.xpath("//h1[text()='Register Account']")).click();

		// Thread.sleep(1000);

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(srcScreenshot,
					new File(System.getProperty("user.dir") + "\\Screenshots\\actualRegisterPageUI.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (browserName.equals("firefox") || browserName.equals("edge")) {
			Assert.assertTrue(CommonUtils.compareTwoScreenshots1(
					System.getProperty("user.dir") + "\\Screenshots\\actualRegisterPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedRegisterPageUI.png"));
		} else if (browserName.equals("chrome")) {
			int diffSize = CommonUtils.compareTwoScreenshots(
					System.getProperty("user.dir") + "\\Screenshots\\actualRegisterPageUI.png",
					System.getProperty("user.dir") + "\\Screenshots\\expectedChromeRegisterPageUI.png");
			Assert.assertTrue(diffSize < 50);
		}

	}

}