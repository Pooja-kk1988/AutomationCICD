package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.CheckOut;
import rahulshettyacademy.pageobject.ConfirmationPage;
import rahulshettyacademy.pageobject.LandingPage;
import rahulshettyacademy.pageobject.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	
	
	public ConfirmationPage confirmation;
	public LandingPage landingPage;
	public ProductCatalogue prodcatalogue;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage=launchApplication() ;
		
	}
	 @Given("^Logged in with username (.+) and password (.+)$")
	 public void   Logged_in_with_username_and_password(String username,String password)
	 {
		 prodcatalogue = landingpage.LoginApp(username,password);
	 }

	 @When("^I add product (.+) to Cart$")
	 public void I_add_product_to_cart(String productName) throws InterruptedException
	 {

			List<WebElement> prod = prodcatalogue.getProductList();
			prodcatalogue.addProductToCart(productName);
	 }
	 
	 //(.+) it is used only when data is driven from the outline
	 @When("^Checkout (.+) and submit the order$")
	 public void Checkout_and_submit_the_order(String productName)
	 {
		 CartPage cartPage = prodcatalogue.goToCartPage();

			Boolean match = cartPage.VerifyProductDisplay(productName);
			Assert.assertTrue(match);
			CheckOut checkOut = cartPage.goToCheckOut();
			checkOut.selectCountry("India");
			 confirmation = checkOut.submitOrder();

	 }

	 
	 //here data is already present in the step so we cannot use (.+)
	 @Then("^\"([^\"]*)\" message is displayed on confirmation page$")
	 public void message_displayed_confirmation_page(String string)
	 {
		 
		 confirmation = new  ConfirmationPage(driver);
		    String confirm = confirmation.getConfirmationMessage();
			Assert.assertTrue(confirm.equalsIgnoreCase(string));
			//driver.close();
			}
	 
	 
	 
	
		 @Then("^\"([^\"]*)\" message is displayed$")
		 public void message_displayed(String strArg1)
		 {
			
			 Assert.assertEquals(strArg1, landingpage.getErrorMessage());
				//driver.close();
				}
	 
	 
	 
	 
	 
	 
}
