@tag
Feature: purchase the ordrer from wecommwece site


Background:
Given I landed on ecommerce page

@tag2
Scenario Outline: Positive test of submitting the order
Given Logged in with username <name> and password <password>
When I add the product <productName> to cart
And  Checkout "<productName>" and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on the Confirmation Page



Examples:
|  name              |  password     |  productName |
|  albin_qa@test.com |  12345678aA!  |  ZARA COAT 3 |
