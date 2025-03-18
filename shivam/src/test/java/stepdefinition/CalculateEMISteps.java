package stepdefinition;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pages.EMICalculatorPage;

public class CalculateEMISteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private EMICalculatorPage emiPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Given("I open the EMI Calculator site")
    public void i_open_the_emi_calculator_site() {
        driver.get("https://emicalculator.net/");
        emiPage = new EMICalculatorPage(driver, wait);
        Assert.assertTrue(emiPage.isPageLoaded(), "EMI Calculator page did not load properly");
    }

    @When("I select {string} tab")
    public void i_select_tab(String loanType) {
        emiPage.selectTab(loanType);
    }

    @When("I set loan amount to {string}")
    public void i_set_loan_amount_to(String amount) {
        emiPage.setLoanAmount(amount);
        Assert.assertTrue(emiPage.verifyLoanAmount(amount), "Loan amount not set correctly");
    }

    @When("I set interest rate to {string}")
    public void i_set_interest_rate_to(String rate) {
        emiPage.setInterestRate(rate);
        Assert.assertTrue(emiPage.verifyInterestRate(rate), "Interest rate not set correctly");
    }

    @When("I set loan tenure to {string}")
    public void i_set_loan_tenure_to(String tenure) {
        emiPage.setLoanTenure(tenure);
        Assert.assertTrue(emiPage.verifyLoanTenure(tenure), "Loan tenure not set correctly");
    }

    @Then("I print the EMI results")
    public void i_print_the_emi_results() {
        String loanEMI = emiPage.getLoanEMI();
        String totalInterest = emiPage.getTotalInterest();
        String totalPayment = emiPage.getTotalPayment();

        System.out.println("\n===== EMI Calculation Results =====");
        System.out.println("Loan EMI: " + loanEMI);
        System.out.println("Total Interest Payable: " + totalInterest);
        System.out.println("Total Payment (Principal + Interest): " + totalPayment);
        System.out.println("===================================\n");

        Assert.assertFalse(loanEMI.isEmpty(), "Loan EMI is empty");
        Assert.assertFalse(totalInterest.isEmpty(), "Total Interest is empty");
        Assert.assertFalse(totalPayment.isEmpty(), "Total Payment is empty");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
