package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import utils.CommonUtils;

public class TestCaseRegisterFunctionality019 extends Base {

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
	public void verifyLeadingAndTrailingSpacesWhileRegisteringAccount() {

		String enteredFirstName = "        Arun     ";
		driver.findElement(By.id("input-firstname")).sendKeys(enteredFirstName);
		String enteredLastName = "       Motoori    ";
		driver.findElement(By.id("input-lastname")).sendKeys(enteredLastName);
		String enteredEmail = "      " + CommonUtils.generateBrandNewEmail() + "       ";
		driver.findElement(By.id("input-email")).sendKeys(enteredEmail);
		String enteredTelphone = "   1234567890   ";
		driver.findElement(By.id("input-telephone")).sendKeys(enteredTelphone);
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.findElement(By.xpath("//a[@class='btn btn-primary'][text()='Continue']")).click();
		driver.findElement(By.linkText("Edit your account information")).click();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("value"),
				enteredFirstName.trim()); // Failue is expected here as spaces are not trimmed in the application
		softAssert.assertEquals(driver.findElement(By.id("input-lastname")).getAttribute("value"), enteredLastName.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("value"), enteredEmail.trim());
		softAssert.assertEquals(driver.findElement(By.id("input-telephone")).getAttribute("value"), enteredTelphone.trim());
		softAssert.assertAll();
		
		// has some issue in firefox. dont run in firefox

	}

}