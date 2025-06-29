package tutorialsninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseRegisterFunctionality002 {
	
	@Test
	public void verifyRegisteringWithAllFields() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		driver.findElement(By.id("input-firstname")).sendKeys("Sam");
		driver.findElement(By.id("input-lastname")).sendKeys("Md");
		driver.findElement(By.id("input-email")).sendKeys(generateNewEmail());
		driver.findElement(By.id("input-telephone")).sendKeys("8121162728");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		
		driver.findElement(By.xpath("//label[text()='Yes']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		Assert.assertEquals(driver.findElement(By.linkText("Logout")).getText(), "Logout", "Asseted Logout option is found");
		
		String actualTextDetails = driver.findElement(By.xpath("//div[@id='content']")).getText();
		
		Assert.assertTrue(actualTextDetails.contains("Your Account Has Been Created!"));
		Assert.assertTrue(actualTextDetails.contains("Congratulations! Your new account has been successfully created!"));
		Assert.assertTrue(actualTextDetails.contains("You can now take advantage of member privileges to enhance your online shopping experience with us."));
		Assert.assertTrue(actualTextDetails.contains("If you have ANY questions about the operation of this online shop, please e-mail the store owner."));
		Assert.assertTrue(actualTextDetails.contains("contact us."));
		
		driver.findElement(By.linkText("Continue")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
		driver.quit();
	}
	public String generateNewEmail() {
		return (new Date().toString().replaceAll("\\s", "").replaceAll("\\:", "") + "@gmail.com");
	}
}
