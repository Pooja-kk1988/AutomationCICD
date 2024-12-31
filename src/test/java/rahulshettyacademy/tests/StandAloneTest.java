package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		// Test
        String prodname="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("Pooja@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Pooja@1988");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> prod = driver.findElements(By.cssSelector(".mb-3"));

//prod.stream()--means it will iterate  it will contain the list of prod info. and it will give 1 prod info.so we are searching for Soil,it is hidden inside
//for that we are using prod.findelement,it will continue from prod to that b tag
//here Prods-->it contains first prod info
		WebElement product = prod.stream()
				.filter(Prods -> Prods.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);

		product.findElement(By.cssSelector(".w-10")).click();
//product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//*[@class='cartSection']/h3
		List<WebElement> cartprods=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartprods.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(prodname));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		//driver.findElement(By.cssSelector(".form-group input")).sendKeys("Ind");
		//List<WebElement> countries=driver.findElements(By.cssSelector(".ng-star-inserted"));
		//countries.stream().filter(count->count.getText().equalsIgnoreCase("India"));
		Actions a= new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		/*//button[contains(@class,'ta-item ')][2]*/
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')]) [2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirm=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirm.equalsIgnoreCase("Thankyou for the order."));
		
		
		
		
		
		
		
		
		
		
	}

}
