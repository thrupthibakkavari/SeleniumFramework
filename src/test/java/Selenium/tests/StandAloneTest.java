package Selenium.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("seleniumA@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("SeleniumAutomation1");
		driver.findElement(By.id("login")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		List<WebElement> cartElements = driver.findElements(By.cssSelector(".cart h3"));
		Boolean matchBoolean = cartElements.stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(matchBoolean);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector(".form-group input")), "india").build().perform();
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		driver.findElement(By.cssSelector(".actions a")).click();

		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equals("THANKYOU FOR THE ORDER."));

		driver.close();

		// TODO Auto-generated method stub

	}

}
