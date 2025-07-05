package tutorialsninja.register;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtils;

public class TestCaseRegisterFunctionality010 {
	@Test
	public void verifyRegisteringUsingInvalidEmailPlusOffline() throws Exception {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new"); // Use --headless=new instead of --headless in latest Chrome
		options.addArguments("--window-size=1920,1080");
		WebDriver driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='My Account']")));
		
		driver.findElement(By.linkText("Register")).click();

		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys("amotoori");
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//h1[text()='Register Account']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		Thread.sleep(3000);

		File srcScreenshot1 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcScreenshot1, new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png"));

		Thread.sleep(3000);

		Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png",System.getProperty("user.dir") + "\\Screenshots\\sc1Expected.png"));

		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("amotoori@");
		driver.findElement(By.xpath("//h1[text()='Register Account']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		Thread.sleep(2000);

		File srcScreenshot2 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcScreenshot2, new File(System.getProperty("user.dir") + "\\Screenshots\\sc2Actual.png"));

		Thread.sleep(2000);

		Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir") + "\\Screenshots\\sc2Actual.png",
				System.getProperty("user.dir") + "\\Screenshots\\sc2Expected.png"));

		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail");
		driver.findElement(By.xpath("//h1[text()='Register Account']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		Thread.sleep(2000);

		String expectedWarningMessage = "E-Mail Address does not appear to be valid!";
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(),
				expectedWarningMessage);

		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail.");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		Thread.sleep(3000);

		File srcScreenshot3 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcScreenshot3, new File(System.getProperty("user.dir") + "\\Screenshots\\sc3Actual.png"));

		Thread.sleep(3000);

		Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir") + "\\Screenshots\\sc3Actual.png",
				System.getProperty("user.dir") + "\\Screenshots\\sc3Expected.png"));

		driver.quit();

	}
}
