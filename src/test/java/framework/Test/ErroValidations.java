package framework.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.TestComponents.BaseTest;
import framework.pageobjects.Productcataloge;
import framework.pageobjects.cartPage;

public class ErroValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void inValidCred()
	{

	Login.loginAction("albin_qa@test.com", "12348aA!");
	Assert.assertEquals("Incorrect email or password.", Login.getErrorMessage());
	Login.getErrorMessage();
}
	@Test
	public void productValidations()
	{
		Login.loginAction("albin_qa@test.com", "12345678aA!");
		String productName = "ZARA COAT 3";
		Productcataloge ProductListing = new Productcataloge(driver);

		ProductListing.addtoCart(productName);
		ProductListing.clickCart();
		cartPage list = new cartPage(driver);
		Assert.assertTrue(list.cartList(productName));
	}
}

//Comments added
//Commented
