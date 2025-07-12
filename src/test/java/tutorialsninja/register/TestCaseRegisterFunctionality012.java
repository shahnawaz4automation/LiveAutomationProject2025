package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import utils.CommonUtils;

public class TestCaseRegisterFunctionality012 extends Base {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {

		String browserName = "chrome";

		driver = openBrowserAndApplication();

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void verifyRegisteringAccountUsingKeyboardKeys() {

		Actions actions = new Actions(driver);

		for (int i = 0; i <= 22; i++) {
			actions.sendKeys(Keys.TAB).perform();
		}

		actions.sendKeys("Arun").pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
				.sendKeys("Motoori").sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
				.sendKeys(CommonUtils.generateBrandNewEmail()).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys("1234567890").pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys("12345").pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys("12345").pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys(Keys.LEFT).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
				.pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.SPACE)
				.pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(3)).sendKeys(Keys.ENTER)
				.build().perform();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='column-right']//a[text()='Logout']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());

	}
}
