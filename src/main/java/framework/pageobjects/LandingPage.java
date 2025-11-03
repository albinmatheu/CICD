package framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworks.AbstractComponents.AbstractComponent;


public class LandingPage extends AbstractComponent{

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver);
		// initialization of driver
		this.driver = driver;
		// Passing driver to all Findby method
		PageFactory.initElements(driver, this);

	}

	// WebElement username = driver.findElement(By.id("userEmail"));

	@FindBy(id = "userEmail")
	WebElement username;


	// driver.findElement(By.id("userPassword"))
	@FindBy(id = "userPassword")
	WebElement password;


	@FindBy(id = "login")
	WebElement loginBtn;

	@FindBy(css="[class*='toast-container']")
	WebElement errorToast;


	public void loginAction(String email, String passwordCred)
	{
		username.sendKeys(email);
		password.sendKeys(passwordCred);
		loginBtn.click();
	}

	public String getErrorMessage()
	{
		waitForElementToAppear1(errorToast);
		return errorToast.getText();

	}

	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
}
