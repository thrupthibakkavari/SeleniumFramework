
@tag
Feature: Purchase the order in Ecommerce Website
  I want to use this template for my feature file

 Background:
 Given  I landed on Ecommerce Page 

  @Regression 
  Scenario Outline: Postive test of Submitting  the order
   
    Given Logged in with username  <name> and password <password>
    When I add the product <productName> to Cart
    And checkout <productName> and submit the order 
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmPage

    Examples: 
      | name                |        password         | productName  |
      | seleniumA@gmail.com |     SeleniumAutomation1 | ZARA COAT 3|
      
       
