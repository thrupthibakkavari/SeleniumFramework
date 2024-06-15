package Selenium.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Selenium.AbstractComp.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	

	By productBy = By.cssSelector(".mb-3");
	By addCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	By animator = By.cssSelector(".ng-animating");

	public List<WebElement> getProductList() throws InterruptedException {

		waitForElementsToAppear(productBy);
		return products;

	}

	public WebElement getProductName(String productName) throws InterruptedException {

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		return prod;

	}

	public void addToCart(String productName) throws InterruptedException {

		WebElement prod = getProductName(productName);
		prod.findElement(addCart).click();
		waitForElementsToAppear(toastMessage);
		waitForElementsToDisappear(animator);
		
	}
	
	
	

}

