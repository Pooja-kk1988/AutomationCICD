package rahulshettyacademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.CheckOut;
import rahulshettyacademy.pageobject.ConfirmationPage;
import rahulshettyacademy.pageobject.LandingPage;
import rahulshettyacademy.pageobject.OrderPage;
import rahulshettyacademy.pageobject.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubmitorderTest extends BaseTest {

	 String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void Submitorder(HashMap<String,String> input)
			throws IOException, InterruptedException {

		ProductCatalogue prodcatalogue = landingpage.LoginApp(input.get("email"),input.get("password") );

		List<WebElement> prod = prodcatalogue.getProductList();
		prodcatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = prodcatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOut checkOut = cartPage.goToCheckOut();
		checkOut.selectCountry("India");
		ConfirmationPage confirmation = checkOut.submitOrder();

		String confirm = confirmation.getConfirmationMessage();
		Assert.assertTrue(confirm.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = {"Submitorder"})
	public void OrderHistory() {
		ProductCatalogue prodcatalogue = landingpage.LoginApp("Pooja@gmail.com", "Pooja@1988");
		OrderPage orderPage = prodcatalogue.goToOrdersPage();

		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}

	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		
		return new Object[][] { {data.get(0)},{data.get(1)}};
	}
}



//HashMap<String,String> map=new HashMap<String,String> ();
		//map.put("email", "Pooja@gmail.com");
		//map.put("password", "Pooja@1988");
		//map.put("product",  "ZARA COAT 3" );
		
		
		//HashMap<String,String> map1=new HashMap<String,String> ();
		//map1.put("email", "Ria@gmail.com");
		//map1.put("password", "Riadas12345");
		//map1.put("product",  "ADIDAS ORIGINAL" );


//@DataProvider
	//public Object[][] getData() {
	//	return new Object[][] { {"Pooja@gmail.com","Pooja@1988","ZARA COAT 3" },{"Smita@gmail.com","Smita@12345","ADIDAS ORIGINAL"}};
	//}
	
		