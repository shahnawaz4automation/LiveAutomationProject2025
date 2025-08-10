package tutorialninja.practice;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import utils.CommonUtils;

public class TestCaseRegisterFunctionality101 extends Base{
	
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
		if(driver!=null) {
			//driver.quit();
		}
	}
	
	@Test
	public void verifyRegisteringWithMandatoyFields() {
		driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateBrandNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String content = driver.findElement(By.xpath("//div[@id='content']")).getText();
		
		Assert.assertTrue(content.contains("Your Account Has Been Created!"));
		
		driver.findElement(By.linkText("Continue")).click();
		
		driver.findElement(By.linkText("Logout")).click();
	}
}
