package framework.Test;

import java.io.IOException;
import java.util.HashMap;
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

public class hashMap extends BaseTest {

	String productName;

	@Test(dataProvider="DataSet",groups={"hashmap"})
	public void submitOrder(HashMap<String, String>input) throws IOException {

		String ActualMessage = "THANKYOU FOR THE ORDER.";
		Login.loginAction(input.get("email"),input.get("password"));

		Productcataloge ProductListing = new Productcataloge(driver);
		List<WebElement> products = ProductListing.getProductList();

		ProductListing.addtoCart(input.get("product"));

		ProductListing.clickCart();

		cartPage list = new cartPage(driver);
		Boolean match = list.cartList(input.get("product"));
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
		HashMap<String, String> map = new HashMap<>();
		map.put("email","albin_qa@test.com");
		map.put("password", "12345678aA!");
		map.put("product", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<>();
		map1.put("email","albin_qa@test.com");
		map1.put("password", "12345678aA!");
		map1.put("product", "ADIDAS ORIGINAL");

		return new Object [][] {{map},{map1}};
	}

}
