package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EMICalculatorPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private String currentTab = "Home Loan"; // default tab

    public EMICalculatorPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.titleContains("EMI Calculator")) != null;
    }

    // Select the desired tab using the correct locators
    public void selectTab(String loanType) {
        String xpath = "";
        if (loanType.equalsIgnoreCase("Home Loan")) {
            xpath = "//*[@id='home-loan']/a";
            currentTab = "Home Loan";
        } else if (loanType.equalsIgnoreCase("Personal Loan")) {
            xpath = "//*[@id='personal-loan']/a";
            currentTab = "Personal Loan";
        } else if (loanType.equalsIgnoreCase("Car Loan")) {
            xpath = "//*[@id='car-loan']/a";
            currentTab = "Car Loan";
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    // Use the provided correct locators for loan amount, interest rate, and loan term.
    private By getAmountInputLocator() {
        return By.xpath("//*[@id='loanamount']");
    }

    private By getInterestInputLocator() {
        return By.xpath("//*[@id='loaninterest']");
    }

    private By getTenureInputLocator() {
        return By.xpath("//*[@id='loanterm']");
    }

    public void setLoanAmount(String amount) {
        WebElement amountInput = wait.until(ExpectedConditions.visibilityOfElementLocated(getAmountInputLocator()));
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public boolean verifyLoanAmount(String expectedAmount) {
        String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(getAmountInputLocator()))
                            .getAttribute("value");
        // Remove commas and trim any extra spaces before comparing
        String actualNoCommas = actual.replaceAll(",", "").trim();
        try {
            double actualVal = Double.parseDouble(actualNoCommas);
            double expectedVal = Double.parseDouble(expectedAmount.trim());
            return actualVal == expectedVal;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setInterestRate(String rate) {
        WebElement interestInput = wait.until(ExpectedConditions.visibilityOfElementLocated(getInterestInputLocator()));
        interestInput.clear();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", interestInput, rate);
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change'));", interestInput);
    }

    public boolean verifyInterestRate(String expectedRate) {
        String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(getInterestInputLocator()))
                            .getAttribute("value");
        try {
            double actualVal = Double.parseDouble(actual.trim());
            double expectedVal = Double.parseDouble(expectedRate.trim());
            return actualVal == expectedVal;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setLoanTenure(String tenure) {
        WebElement tenureInput = wait.until(ExpectedConditions.visibilityOfElementLocated(getTenureInputLocator()));
        // Clear the field completely using JavaScript
        ((JavascriptExecutor)driver).executeScript("arguments[0].value = '';", tenureInput);
        // Set the desired value
        ((JavascriptExecutor)driver).executeScript("arguments[0].value = arguments[1];", tenureInput, tenure);
        // Dispatch change event if needed
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change'));", tenureInput);
    }

    // Updated verifyLoanTenure with log statements and numerical comparison
    public boolean verifyLoanTenure(String expectedTenure) {
        String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(getTenureInputLocator()))
                            .getAttribute("value").trim();
        System.out.println("DEBUG: Loan Tenure - Expected: " + expectedTenure + ", Actual: " + actual);
        try {
            // Remove any non-numeric characters (if needed) and compare as doubles
            String actualNumeric = actual.replaceAll("[^0-9.]", "");
            String expectedNumeric = expectedTenure.replaceAll("[^0-9.]", "");
            double actualVal = Double.parseDouble(actualNumeric);
            double expectedVal = Double.parseDouble(expectedNumeric);
            return actualVal == expectedVal;
        } catch (NumberFormatException e) {
            return actual.equals(expectedTenure);
        }
    }



    public String getLoanEMI() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"emiamount\"]/p/span"))).getText();
    }

    public String getTotalInterest() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"emitotalinterest\"]/p/span"))).getText();
    }

    public String getTotalPayment() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"emitotalamount\"]/p/span"))).getText();
    }
}
