Feature: Checkout
  As a user, I want to be able to smoothly complete the checkout process, ensuring a convenient shopping experience.

  Background: 
    Given User has logged in with valid credentials
    When User adds items to cart and goes to checkout

  Scenario: User is able to checkout successfully
    And User enters checkout information and completes checkout
    Then User should see checkout complete message

  Scenario: User is unable to checkout with missing information
    And User enters incomplete checkout information and tries to complete checkout
    Then User should see error message indicating missing information
