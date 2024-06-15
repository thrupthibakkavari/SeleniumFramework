package Selenium.AbstractComp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Selenium.pageObjects.CartPage;
import Selenium.pageObjects.OrderPage;

public class AbstractComponents {
	WebDriver driver;
	
	@FindBy(css = "button[routerlink*='cart']")
	WebElement goToCartElement;
	
	@FindBy(css=".btn.btn-custom[routerlink='/dashboard/cart']")
	WebElement goToOrderElement;
	public AbstractComponents(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	public void waitForElementsToAppear(By findBy) throws InterruptedException {

		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}
	
public void waitForWebElementsToAppear(WebElement findBy)  {

		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	
	

	public void waitForElementsToDisappear(By findBy) throws InterruptedException {
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));

	}



	public CartPage goToCart() {
	
	
	goToCartElement.click();
	CartPage cartPage= new CartPage(driver);
	return cartPage;
	
	}	
	
	public OrderPage goToOrderPage () {
		goToOrderElement.click();
	   OrderPage orderpage= new OrderPage(driver);
	   return orderpage;
	}
	

}
