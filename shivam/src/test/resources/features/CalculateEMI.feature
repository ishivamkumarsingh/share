Feature: EMI Calculator

  # Scenario 1: Home Loan
  Scenario: Home Loan EMI
    Given I open the EMI Calculator site
    When I select "Home Loan" tab
    And I set loan amount to "5000000"
    And I set interest rate to "10"
    And I set loan tenure to "20"
    Then I print the EMI results

  # Scenario 2: Personal Loan
  Scenario: Personal Loan EMI
    Given I open the EMI Calculator site
    When I select "Personal Loan" tab
    And I set loan amount to "750000"
    And I set interest rate to "15"
    And I set loan tenure to "5"
    Then I print the EMI results

  # Scenario 3: Car Loan
  Scenario: Car Loan EMI
    Given I open the EMI Calculator site
    When I select "Car Loan" tab
    And I set loan amount to "2000000"
    And I set interest rate to "9"
    And I set loan tenure to "6"
    Then I print the EMI results
