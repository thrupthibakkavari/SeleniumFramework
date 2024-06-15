package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "C:\\Users\\pc\\eclipse-workspace\\SeleniumFrameWork\\src\\test\\java\\cucumber", glue = "Selenium.stepDefinitions", monochrome = true,
 tags ="@Regression" ,   plugin = {"html:target/cucumber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
