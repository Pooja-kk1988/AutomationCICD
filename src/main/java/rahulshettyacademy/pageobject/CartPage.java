package rahulshettyacademy.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	
	
	WebDriver driver;
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartprods;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match = cartprods.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
	return match;
	}
	
	public CheckOut goToCheckOut()
	{
		checkoutEle.click();
		return new CheckOut(driver);
	}
	
	
	
	
	
	
	
	
}
