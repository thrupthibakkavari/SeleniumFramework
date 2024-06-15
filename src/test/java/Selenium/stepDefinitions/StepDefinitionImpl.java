package Selenium.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Selenium.TestComponents.BeseTest;
import Selenium.pageObjects.CartPage;
import Selenium.pageObjects.CheckOutPage;
import Selenium.pageObjects.ConfirmPage;
import Selenium.pageObjects.LandingPage;
import Selenium.pageObjects.ProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BeseTest {

	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	 public ConfirmPage confirmPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {

		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {

		productCatalog = landingPage.loginApplication(username, password);

	}

	@When("^I add the product (.+) to Cart$")
	public void I_add_the_product_to_Cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addToCart(productName);

	}

	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_order(String productName) {

		CartPage cartPage = productCatalog.goToCart();

		Boolean matchBoolean = cartPage.verifyProductPresent(productName);
		
		Assert.assertTrue(matchBoolean);
		CheckOutPage checkOutPage = cartPage.checkOut();
		checkOutPage.selectContry("india");
		 confirmPage = checkOutPage.Chekout();
		
//		CartPage cartPage = productCatalogue.goToCartPage();
//
//		Boolean match = cartPage.VerifyProductDisplay(productName);
//		Assert.assertTrue(match);
//		CheckoutPage checkoutPage = cartPage.goToCheckout();
//		checkoutPage.selectCountry("india");
//		 confirmationPage = checkoutPage.submitOrder();
//		 
		 
	}

	@Then("{string} message is displayed on ConfirmPage")
	public void message_displayed_ConfirmPage(String string) {

		String confirmMsg = confirmPage.orderConfirm();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));
		driver.close();

	}
    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
   
    	Assert.assertEquals(strArg1, landingPage.errorMessage());
    	driver.close();
    }


}
