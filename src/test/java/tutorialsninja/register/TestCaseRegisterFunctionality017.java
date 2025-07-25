package tutorialsninja.register;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import utils.CommonUtils;

public class TestCaseRegisterFunctionality017 extends Base {

	WebDriver driver;
	Properties prop;

	@BeforeMethod
	public void setUp() {

		driver = openBrowserAndApplication();
		prop = CommonUtils.loadProperties();
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "passwordSupplier")
	public void verifyRegisteringAccountAndCheckingPasswordComplexityStandards(String passwordText) {

		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.id("input-password")).sendKeys(passwordText);
		driver.findElement(By.id("input-confirm")).sendKeys(passwordText);
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String warningMessage = "Password entered is not matching the Complexity standards";

		boolean status = false;

		try {
			String actualWarningMessage = driver
					.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
			Assert.assertEquals(actualWarningMessage, warningMessage);
			status = true;
		} catch (Exception e) {
			status = false;
		}

		Assert.assertTrue(status);

		Assert.assertFalse(
				driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());

	}

	@DataProvider(name = "passwordSupplier")
	public Object[][] supplyPasswords() {
		Object[][] data = { { "12345" }, { "abcdefghi" }, { "abcd1234" }, { "abcd123$" }, { "ABCD456#" } };
		return data;
	}

}