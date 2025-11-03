package framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworks.AbstractComponents.AbstractComponent;

public class deliveryaddressPage extends AbstractComponent {

	WebDriver driver;

	public deliveryaddressPage(WebDriver driver)

	{
		super(driver);
		// initialization of driver
		this.driver = driver;
		// Passing driver to all Findby method
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	By list = By.cssSelector(".ta-results");

	@FindBy(xpath ="(//button[@type='button'])[2]")
	WebElement selectCountry;

	@FindBy(css=".action__submit")
	WebElement placeOrder;

	public void selectCountry() {
		Actions a = new Actions(driver);
		a.sendKeys(country, "india").build().perform();
		waitForElementToAppear(list);
		selectCountry.click();

	}

	public void placeOrder()
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(placeOrder).perform();
		placeOrder.click();
	}



}
