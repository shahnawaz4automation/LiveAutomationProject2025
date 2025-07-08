package tutorialsninja.register;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.CommonUtils;

public class TestCaseRegisterFunctionality026 {
	@Test
	public void verifyUIOfRegisterAccountPage() throws IOException, InterruptedException {

		// https://drive.google.com/file/d/1X6EPJW-Ojl3Xpv99qrnOV4wU8FuekmtO/view

		/*ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new"); // Use --headless=new instead of --headless in latest Chrome
		options.addArguments("--window-size=2500,1300");
		WebDriver driver = new ChromeDriver(options);*/
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo/");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='My Account']")));
		
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.xpath("//h1[text()='Register Account']")).click();
		
		//Thread.sleep(1000);
		
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);

		try {
			FileHandler.copy(srcScreenshot,
					new File(System.getProperty("user.dir") + "\\Screenshots\\actualRegisterPageUI.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Assert.assertTrue(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir") + "\\Screenshots\\actualRegisterPageUI.png", System.getProperty("user.dir") + "\\Screenshots\\expectedRegisterPageUI.png"));
		
		int diffSize = CommonUtils.compareTwoScreenshots(System.getProperty("user.dir") + "\\Screenshots\\actualRegisterPageUI.png", System.getProperty("user.dir") + "\\Screenshots\\expectedRegisterPageUI.png");
		Assert.assertTrue(diffSize < 50); 
		
		driver.quit();

	}

}