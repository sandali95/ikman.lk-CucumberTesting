Feature: Houses
  Scenario: Navigate to ikman.lk to find houses in colombo
    Given I am on the "https://ikman.lk" page
    When I click on Property link
    And I click on Houses link
    And I click on Colombo link
    And I enter min_price as "5000000"and a max_Price as "7500000"
    And I set bed as "3"
    Then I am naviageted to the result page

