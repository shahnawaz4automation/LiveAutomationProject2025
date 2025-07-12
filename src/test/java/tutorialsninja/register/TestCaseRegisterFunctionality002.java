package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import utils.CommonUtils;

public class TestCaseRegisterFunctionality002 extends Base {
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
	public void verifyRegisteringWithAllFields() {

		driver.findElement(By.id("input-firstname")).sendKeys("Sam");
		driver.findElement(By.id("input-lastname")).sendKeys("Md");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("8121162728");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();

		driver.findElement(By.xpath("//label[text()='Yes']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		Assert.assertEquals(driver.findElement(By.linkText("Logout")).getText(), "Logout",
				"Asseted Logout option is found");

		String actualTextDetails = driver.findElement(By.xpath("//div[@id='content']")).getText();

		Assert.assertTrue(actualTextDetails.contains("Your Account Has Been Created!"));
		Assert.assertTrue(
				actualTextDetails.contains("Congratulations! Your new account has been successfully created!"));
		Assert.assertTrue(actualTextDetails.contains(
				"You can now take advantage of member privileges to enhance your online shopping experience with us."));
		Assert.assertTrue(actualTextDetails.contains(
				"If you have ANY questions about the operation of this online shop, please e-mail the store owner."));
		Assert.assertTrue(actualTextDetails.contains("contact us."));

		driver.findElement(By.linkText("Continue")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());

	}
}
