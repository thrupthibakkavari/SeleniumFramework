package Selenium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComp.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	 @FindBy(css=".form-group input")
	 WebElement countryElement;
	 
	 @FindBy(xpath ="(//button[@type='button'])[2]")
	 WebElement selectCountryElement;
	 
	 @FindBy(css=".actions a")
	 WebElement checkOutElement;
	
	public void selectContry(String country) {
		// TODO Auto-generated method stub
		Actions action = new Actions(driver);
		action.sendKeys(countryElement, country).build().perform();
		selectCountryElement.click();
		
	}
public ConfirmPage Chekout() {
	
	checkOutElement.click();
	ConfirmPage confirmMsg = new ConfirmPage(driver);
	return confirmMsg;
}


}
