package Selenium.TestComponents;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Selenium.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BeseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Selenium\\resources\\GlobalData.properties");
		properties.load(fileInputStream);

		
		String browserString=	System.getProperty("browser") !=null ? System.getProperty("browser"):properties.getProperty("browser") ;
		//String browserString = properties.getProperty("browser");

		if (browserString.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserString.contains("headless")) {
				options.addArguments("headless");
			}

			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900) );
		} else if (browserString.equals("firefox")) {
			// for firefox code

		}

		else if (browserString.equals("edge")) {

			System.setProperty("webdriver.edge.driver", "C:\\Users\\pc\\Downloads\\edgedriver_win64\\msedgedriver.exe");
			driver= new EdgeDriver();
			// for edge code

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}

	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	//{map, map}

	}
	
	public String getScreenShot(String testCaseName , WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName +".png";
	}
	

	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

	   	initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		driver.quit();
	}

}
