package Selenium.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Selenium.TestComponents.BeseTest;
import Selenium.TestComponents.Retry;
import Selenium.pageObjects.CartPage;
import Selenium.pageObjects.CheckOutPage;
import Selenium.pageObjects.ConfirmPage;
import Selenium.pageObjects.LandingPage;
import Selenium.pageObjects.ProductCatalog;

public class ErrorValidations extends BeseTest {

	@Test(groups = {"ErrorHandling"} , retryAnalyzer = Retry.class)
	public void LoginEroorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		
		landingPage.loginApplication("a@gma7il.com", "SeleniumAutomation1");
		
		Assert.assertEquals( "Incorrect email or password.",landingPage.errorMessage());
		
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		
		
		String productName = "ZARA COAT 3";


		ProductCatalog productCatalog = landingPage.loginApplication("seleniumA@gmail.com", "SeleniumAutomation1");

		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addToCart(productName);
		CartPage cartPage = productCatalog.goToCart();

		Boolean matchBoolean = cartPage.verifyProductPresent(productName);
		Assert.assertTrue(matchBoolean);
	}
	

}