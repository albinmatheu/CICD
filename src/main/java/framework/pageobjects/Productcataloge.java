package framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworks.AbstractComponents.AbstractComponent;

public class Productcataloge extends AbstractComponent {

	WebDriver driver;

	public Productcataloge(WebDriver driver) {

		super(driver); // Sending driver to the parent class (To AbstractComponent)
		// initialization of driver
		this.driver = driver;
		// Passing driver to all Findby method
		PageFactory.initElements(driver, this);

	}
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> item;

	@FindBy (css ="#toast-container")
	WebElement spinner;

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartIcon;

	By produtsBy = By.cssSelector(".mb-3");
	By addCartbtn = By.cssSelector(".rounded:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");


	public List<WebElement> getProductList() {
		waitForElementToAppear(produtsBy);
		return item;
	}

	public WebElement getProductName(String productName)
	{
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addtoCart(String productName)
	{
		WebElement prod = getProductName(productName);
		prod.findElement(addCartbtn).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisAppear(spinner);
	}

	public void clickCart()
	{
		cartIcon.click();
	}
}
