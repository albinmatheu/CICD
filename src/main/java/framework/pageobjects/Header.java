package framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import frameworks.AbstractComponents.AbstractComponent;

public class Header extends AbstractComponent{

	WebDriver driver;

	public Header(WebDriver driver)

	{
		super(driver);
		// initialization of driver
		this.driver = driver;
		// Passing driver to all Findby method
		PageFactory.initElements(driver, this);

	}

	@FindBy (xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement Orders;



	public OrderHistory clickOrderBtn()
	{
		Orders.click();
		OrderHistory History = new OrderHistory(driver);
		return History;

	}
}
