package Selenium.pageObjects;

import java.security.PublicKey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComp.AbstractComponents;

public class ConfirmPage extends AbstractComponents{

	
	WebDriver driver;
	
	public ConfirmPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmMsgElement;
	
	
	public String orderConfirm() {
		CheckOutPage cp = new CheckOutPage(driver);	
		return confirmMsgElement.getText();
	}
	

}
