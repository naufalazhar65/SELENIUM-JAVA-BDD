package StepDefinitions.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/Features/", 
		glue = { "StepDefinitions" }, 
		dryRun = false,
		plugin = { "pretty", "json:target/cucumber.json" },
		monochrome = false,
		tags = ""
//		publish = true

	)

public class CucumberTestRunner extends AbstractTestNGCucumberTests {

}
