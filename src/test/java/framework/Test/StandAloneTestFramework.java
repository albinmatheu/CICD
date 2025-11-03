package framework.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import framework.TestComponents.BaseTest;
import framework.pageobjects.ConfirmationPage;
import framework.pageobjects.Header;
import framework.pageobjects.OrderHistory;
import framework.pageobjects.Productcataloge;
import framework.pageobjects.cartPage;
import framework.pageobjects.deliveryaddressPage;

public class StandAloneTestFramework extends BaseTest {

	String productName;

	@Test(dataProvider = "DataSet", groups = { "purchase" })
	public void submitOrder(String email, String password, String productName) throws IOException {

		String ActualMessage = "THANKYOU FOR THE ORDER.";
		Login.loginAction("albin_qa@test.com", "12345678aA!");

		Productcataloge ProductListing = new Productcataloge(driver);
		List<WebElement> products = ProductListing.getProductList();

		ProductListing.addtoCart(productName);

		ProductListing.clickCart();

		cartPage list = new cartPage(driver);
		Boolean match = list.cartList(productName);
		Assert.assertTrue(match);
		list.checkout();

		//

		deliveryaddressPage addres = new deliveryaddressPage(driver);
		addres.selectCountry();
		addres.placeOrder();
		ConfirmationPage conf = new ConfirmationPage(driver);
		conf.orderConfirmation(ActualMessage);
	}

	@Test(dependsOnMethods = { "submitOrder" }) // this test will execute only after executing submitOrder because we
												// are using this "dependsOnMethods"
	public void Orderhistory() {
		Login.loginAction("albin_qa@test.com", "12345678aA!");
		Header header = new Header(driver);
		OrderHistory History = header.clickOrderBtn();
		Assert.assertTrue(History.verifyOrder(productName));
	}

	@DataProvider
	public Object[][] DataSet()

	{

		return new Object[][] { { "albin_qa@test.com", "12345678aA!", "ZARA COAT 3" },
				{ "albin_qa@test.com", "12345678aA!", "ADIDAS ORIGINAL" } };
	}

}
