package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

	WebDriver driver;
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Logout")
	private WebElement logoutOption;
	
	public boolean isUserLoggedIn() {
		return logoutOption.isDisplayed();
	}
	
	@FindBy(xpath = "//div[@id='common-success']//h1")
	private WebElement pageHeading;
	
	public String getPageHeading() {
		return pageHeading.getText();
	}
	
	@FindBy(xpath = "//div[@id='content']")
	private WebElement pageContent;
	
	public String getPageContent() {
		return pageContent.getText();
	}
	
	@FindBy(xpath = "//a[text()='Continue']")
	private WebElement continueButton;
	
	public AccountPage clickOnContinueButton() {
		continueButton.click();
		return new AccountPage(driver);
	}
}
