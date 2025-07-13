package tutorialsninja.register;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import utils.CommonUtils;

public class TestCaseRegisterFunctionality019 extends Base {

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

	@Test
	public void verifyLeadingAndTrailingSpacesWhileRegisteringAccount() {

		String enteredFirstName = "        "+prop.getProperty("firstName")+"     ";
		driver.findElement(By.id("input-firstname")).sendKeys(enteredFirstName);
		String enteredLastName = "        "+prop.getProperty("lastName")+"     ";
		driver.findElement(By.id("input-lastname")).sendKeys(enteredLastName);
		String enteredEmail = "      " + CommonUtils.generateBrandNewEmail() + "       ";
		driver.findElement(By.id("input-email")).sendKeys(enteredEmail);
		String enteredTelphone = "        "+prop.getProperty("telephoneNumber")+"     ";
		driver.findElement(By.id("input-telephone")).sendKeys(enteredTelphone);
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.findElement(By.xpath("//a[@class='btn btn-primary'][text()='Continue']")).click();
		driver.findElement(By.linkText("Edit your account information")).click();

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("value"),
				enteredFirstName.trim()); // Failue is expected here as spaces are not trimmed in the application
		softAssert.assertEquals(driver.findElement(By.id("input-lastname")).getAttribute("value"),
				enteredLastName.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("value"), enteredEmail.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-telephone")).getAttribute("value"),
				enteredTelphone.trim());
		softAssert.assertAll();

		// has some issue in firefox. dont run in firefox

	}

}