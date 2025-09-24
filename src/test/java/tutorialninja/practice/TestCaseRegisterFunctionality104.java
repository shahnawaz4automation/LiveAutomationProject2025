package tutorialninja.practice;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.LandingPage;
import utils.CommonUtils;


public class TestCaseRegisterFunctionality104 extends Base{
	
	WebDriver driver;
	Properties prop;
	
	@BeforeMethod
	public void setUp() {
		driver = openBrowserAndApplication();
	    prop = CommonUtils.loadProperties();
		
	    driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[text()='Register']")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null) {
			//driver.quit();
		}
	}
	@Test
	public void verifyRegisteringAccountWithoutFillFields() {
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']//following-sibling::div")).getText(), expectedFirstNameWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']//following-sibling::div")).getText(), expectedLastNameWarning);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']//following-sibling::div")).getText() , expectedEmailWarning);
	}
}
