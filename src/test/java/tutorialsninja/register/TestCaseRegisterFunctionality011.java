package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import utils.CommonUtils;

public class TestCaseRegisterFunctionality011 extends Base {

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
	public void verifyRegisterAccountByProvidingInvalidTelephoneNumber() {

		driver.findElement(By.id("input-firstname")).sendKeys("Arun");
		driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
		driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("abcde");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String expectedWarningMessage = "Telephone number does not appear to be valid";

		// Writing below code avoids NoSuchElementException. Otherwise browser will not
		// close and the WebDriver session will not end.
		boolean state = false;
		try {
			String actualWarningMessage = driver
					.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
			if (actualWarningMessage.equals(expectedWarningMessage)) {
				state = true;
			}
		} catch (Exception e) {
			state = false;
		}

		Assert.assertTrue(state);

	}
}
