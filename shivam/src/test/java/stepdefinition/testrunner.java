package stepdefinition;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/CalculateEMI.feature",
    glue = {"stepdefinition"},
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true
)
public class testrunner extends AbstractTestNGCucumberTests {
}

