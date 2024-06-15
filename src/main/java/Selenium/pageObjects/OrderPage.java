package Selenium.pageObjects;

import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComp.AbstractComponents;


public class OrderPage extends AbstractComponents{
	
	WebDriver driver;
	

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orders;
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> orderElements;
	
	
	public Boolean verifyOrderPresent(String productName) {
		orders.click();
		Boolean matchBoolean = orderElements.stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return matchBoolean;
		
	
	}
	
	

}
