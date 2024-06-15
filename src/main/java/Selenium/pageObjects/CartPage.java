package Selenium.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComp.AbstractComponents;

public class CartPage extends AbstractComponents{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartElements;
	
	@FindBy(css=".totalRow button")
	WebElement checkouElement;
	public Boolean verifyProductPresent(String productName) {
		
		Boolean matchBoolean = cartElements.stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return matchBoolean;
		
	}
	
	
	public CheckOutPage checkOut() {
		
		checkouElement.click();
		CheckOutPage checkOutPage= new CheckOutPage(driver);
		return checkOutPage;
	}
	
	
	

	
	
	

}
