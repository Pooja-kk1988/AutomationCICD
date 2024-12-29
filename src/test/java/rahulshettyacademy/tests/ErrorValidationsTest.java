package rahulshettyacademy.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.CheckOut;
import rahulshettyacademy.pageobject.ConfirmationPage;
import rahulshettyacademy.pageobject.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		//String productName = "ZARA COAT 3";
		landingpage.LoginApp("Pooja@gmail.com", "Pooja@1988");
		
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}

	
}











/*@Test
public void ProductErrorValidation() throws IOException, InterruptedException {

	String productName = "ZARA COAT 3";
	ProductCatalogue prodcatalogue = landingpage.LoginApp("Pooja@gmail.com", "Pooja@1988");

	List<WebElement> prod = prodcatalogue.getProductList();
	prodcatalogue.addProductToCart(productName);
	CartPage cartPage = prodcatalogue.goToCartPage();

	Boolean match = cartPage.VerifyProductDisplay(productName);
	Assert.assertTrue(match);

}
*/