
@tag
Feature: Purchase the order from Ecommerce Site
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on confirmation page

    Examples: 
      | username           | password        | productName
      | Pooja@gmail.com    |Pooja@1988       | ZARA COAT 3
      
