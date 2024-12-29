package rahulshettyacademy.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Page object should only contain the elements and actions not the data
	// WebElement userMail=driver.findElement(By.id("userEmail"));
	// PageFactory

	// List<WebElement> prod = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> prod;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	

	

	By prodby = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {

		waitForElementToAppear(prodby);
		return prod;
	}

	public WebElement getProductByName(String productName) {
		WebElement product = getProductList().stream()
				.filter(Prods -> Prods.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return product;

	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);

	}

}