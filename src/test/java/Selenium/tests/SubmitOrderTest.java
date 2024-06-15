package Selenium.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.Element;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.internal.TestNamesMatcher;

import Selenium.TestComponents.BeseTest;
import Selenium.pageObjects.CartPage;
import Selenium.pageObjects.CheckOutPage;
import Selenium.pageObjects.ConfirmPage;
import Selenium.pageObjects.LandingPage;
import Selenium.pageObjects.OrderPage;
import Selenium.pageObjects.ProductCatalog;

public class SubmitOrderTest extends BeseTest {

	public String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchaseOrder" })
	public void SubmitOrderTest(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCart();

		Boolean matchBoolean = cartPage.verifyProductPresent(input.get("product"));
		Assert.assertTrue(matchBoolean);
		CheckOutPage checkOutPage = cartPage.checkOut();

		checkOutPage.selectContry("india");
		ConfirmPage confirmPage = checkOutPage.Chekout();
		String confirmMsg = confirmPage.orderConfirm();

		Assert.assertTrue(confirmMsg.equals("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "SubmitOrderTest" })
	public void OrderHistoryTest() {

		ProductCatalog productCatalog = landingPage.loginApplication("seleniumA@gmail.com", "SeleniumAutomation1");
		OrderPage orderPage = productCatalog.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderPresent(productName));

	}
	
	

	

	
	@DataProvider
	public Object[][] getData() throws IOException {
		// return new Object[][] {{"seleniumA@gmail.com", "SeleniumAutomation1","ZARA
		// COAT 3"},{"sellly@gmail.com","Selselsel1!","ADIDAS ORIGINAL"}};
//		

//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "anshika@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");

//		
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//Selenium//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
