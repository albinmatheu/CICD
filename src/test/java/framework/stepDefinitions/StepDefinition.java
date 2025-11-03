package framework.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import framework.TestComponents.BaseTest;
import framework.pageobjects.ConfirmationPage;
import framework.pageobjects.LandingPage;
import framework.pageobjects.Productcataloge;
import framework.pageobjects.cartPage;
import framework.pageobjects.deliveryaddressPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {

	public LandingPage landingPage;
	String ActualMessage = "THANKYOU FOR THE ORDER.";

	@Given("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in(String username, String password) {
		Login.loginAction(username, password);
	}

	@When("^I add the product (.+) to cart$")
	public void addproduct(String productName) {
		Productcataloge ProductListing = new Productcataloge(driver);
		List<WebElement> products = ProductListing.getProductList();

		ProductListing.addtoCart(productName);

		ProductListing.clickCart();
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout(String productName)
	{
		Productcataloge ProductListing = new Productcataloge(driver);
		ProductListing.clickCart();

		cartPage list = new cartPage(driver);
		Boolean match = list.cartList("ZARA COAT 3");
		Assert.assertTrue(match);
		list.checkout();
		deliveryaddressPage addres = new deliveryaddressPage(driver);
		addres.selectCountry();
		addres.placeOrder();

	}
	
	@Then("{string} message is displayed on the Confirmation Page")
	public void confirmationmessage(String ActualMessage)
	{
		
		ConfirmationPage conf = new ConfirmationPage(driver);
		conf.orderConfirmation(ActualMessage);
	}
	
	@Then("{string} message is dispalyed")
	public void login_error(String errormsg)
	{
		
		Assert.assertEquals(errormsg, Login.getErrorMessage());
		Login.getErrorMessage();
		driver.close();
	}
	
}
