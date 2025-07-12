package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;

public class TestCaseRegisterFunctionality016 extends Base {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {

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
	public void verifyRegisteringAccountWithOnlySpaces() {

		driver.findElement(By.id("input-firstname")).sendKeys(" ");
		driver.findElement(By.id("input-lastname")).sendKeys(" ");
		driver.findElement(By.id("input-lastname")).sendKeys(" ");
		driver.findElement(By.id("input-telephone")).sendKeys(" ");
		driver.findElement(By.id("input-password")).sendKeys(" ");
		driver.findElement(By.id("input-confirm")).sendKeys(" ");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String firstNameWarning = "First Name must be between 1 and 32 characters!";
		String lastNameWarning = "Last Name must be between 1 and 32 characters!";
		String emailWarning = "E-Mail Address does not appear to be valid!";
		String telephoneWarning = "Telephone must be between 3 and 32 characters!";
		String passwordWarning = "Password must be between 4 and 20 characters!";

		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(),
				firstNameWarning);
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(),
				lastNameWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(),
				emailWarning);
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(),
				telephoneWarning);
		Assert.assertEquals(
				driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(),
				passwordWarning);

	}
}
