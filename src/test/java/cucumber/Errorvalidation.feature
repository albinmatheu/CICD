@tag
Feature: Error Validation




@tag2
Scenario Outline: 
Given I landed on ecommerce page
When  Logged in with username <name> and password <password>
Then "Incorrect email or password." message is dispalyed

Examples:
|  name              |  password     |
|  albin_qa@test.com |  12345678aA   |