package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		/*After clicking on the register option, we know that we will be taken to register page. 
		So we create a object for register page. 
		and return type should be register page
		- new keyword will create object for AccountSuccessPage*/
		return new RegisterPage(driver);
	}
}
