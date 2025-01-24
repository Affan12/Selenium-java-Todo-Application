package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = "stepdefinitions",                 // Package with step definitions
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json",
               // "tech.grasshopper.extentreports.cucumber.adapter.ExtentCucumber7Adapter:"
        }, // Plugins for reporting
        monochrome = true                         // Pretty console output
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
