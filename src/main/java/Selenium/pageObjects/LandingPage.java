package Selenium.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComp.AbstractComponents;

public class LandingPage extends AbstractComponents
{
	
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement useremailElement;
	
	@FindBy(id="userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement submitElement;
	
	@FindBy(css= "[class*= 'flyInOut'")
	WebElement errorElement;
	
	public ProductCatalog loginApplication(String email, String password) {
		
		useremailElement.sendKeys(email);
		passwordElement.sendKeys(password);
		submitElement.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public void goTo(){
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	
	public String errorMessage() {
		waitForWebElementsToAppear(errorElement);
		return errorElement.getText();
		
	}


}
