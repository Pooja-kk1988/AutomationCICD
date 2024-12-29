
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
  Given I landed on Ecommerce Page
  When  Logged in with username <username> and password <password>
  Then "Incorrect email or password." message is displayed on confirmation page

    Examples: 
      | username            | password        | 
      | Pooja@gmail.com     |Pooja@1988        | 
      