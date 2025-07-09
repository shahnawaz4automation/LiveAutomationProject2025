package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCaseRegisterFunctionality010 {

	WebDriver driver;
	String browserName = "firefox";

	@BeforeMethod
	public void setUp() {

		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://tutorialsninja.com/demo");

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
	public void verifyRegisteringUsingInvalidEmailPlusOffline() {

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
		
		if(browserName.equals("chrome") || browserName.equals("edge")) {
			Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"),"Please include an '@' in the email address. 'amotoori' is missing an '@'.");
		} else if(browserName.equals("firefox")) {
			System.out.println(driver.findElement(By.id("input-email")).getAttribute("validationMessage"));
			Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"),"Please enter an email address.");
		}

		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("amotoori@");
		driver.findElement(By.xpath("//h1[text()='Register Account']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		if(browserName.equals("chrome") || browserName.equals("edge")) {
			Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"),"Please enter a part following '@'. 'amotoori@' is incomplete.");
		} else if(browserName.equals("firefox")) {
			System.out.println(driver.findElement(By.id("input-email")).getAttribute("validationMessage"));
			Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"),"Please enter an email address.");
		}
		
		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail");
		driver.findElement(By.xpath("//h1[text()='Register Account']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String expectedWarningMessage = "E-Mail Address does not appear to be valid!";
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(),
				expectedWarningMessage);

		driver.findElement(By.id("input-email")).clear();
		driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail.");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		if(browserName.equals("chrome") || browserName.equals("edge")) {
			Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"),"'.' is used at a wrong position in 'gmail.'.");
		} else if(browserName.equals("firefox")) {
			System.out.println(driver.findElement(By.id("input-email")).getAttribute("validationMessage"));
			Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("validationMessage"),"Please enter an email address.");
		}
	}
}
