package tutorialsninja.register;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.Base;
import pages.LandingPage;
import utils.CommonUtils;

public class TestCaseRegisterFunctionality001 extends Base {

	WebDriver driver;
	Properties prop;

	@BeforeMethod
	public void setUp() {
		driver = openBrowserAndApplication();
		prop = CommonUtils.loadProperties();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.clickOnMyAccount();
		landingPage.selectRegisterOption();
<<<<<<< HEAD
		//System.out.println("-");
=======
>>>>>>> d59f34b (Framework update)
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
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

		Assert.assertEquals(driver.findElement(By.linkText("Logout")).getText(), "Logout");

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
