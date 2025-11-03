package framework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class cartPage {

	WebDriver driver;

	public cartPage(WebDriver driver) {

		// initialization of driver
		this.driver = driver;
		// Passing driver to all Findby method
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> itemList;

	@FindBy(css = ".totalRow button")
	WebElement checkOut;

	public Boolean cartList(String productName) {
		Boolean match = itemList.stream().anyMatch(CartProduct -> CartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public void checkout()
	{
		checkOut.click();
	}
}