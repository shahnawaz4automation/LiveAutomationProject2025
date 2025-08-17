package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	
	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	
	@FindBy(id = "input-email")
	private WebElement emailField;

	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	public void enterTelephoneNumber(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	
	@FindBy(id = "input-password")
	private WebElement passwordField;

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	
	@FindBy(id = "input-confirm")
	private WebElement passwordConfirmField;

	public void enterConfirmField(String passwordText) {
		passwordConfirmField.sendKeys(passwordText);
	}
	
	@FindBy(name = "agree")
	private WebElement privacyPolicyField;

	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	@FindBy(xpath = "//input[@name='newsletter' and @value='1']")
	private WebElement yesNewsLetterOption;
	
	public void selectYesNewsLetterOPtion() {
		yesNewsLetterOption.click();
	}
}
