package framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworks.AbstractComponents.AbstractComponent;

public class OrderHistory extends AbstractComponent{

	WebDriver driver;

	public OrderHistory(WebDriver driver)

	{
		super(driver);
		// initialization of driver
		this.driver = driver;
		// Passing driver to all Findby method
		PageFactory.initElements(driver, this);

	}

	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> OrderderdProduct;

	By OrderedList = By.cssSelector(".mb-3");


	public Boolean verifyOrder(String productName)
	{
		//waitForElementToAppear1(OrderderdProduct);

		Boolean match =OrderderdProduct.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
		return match;


	}
}
