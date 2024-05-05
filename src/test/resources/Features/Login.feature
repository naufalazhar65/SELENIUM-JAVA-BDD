Feature: Login Functionality Test
  As a user, I want to ensure that the login functionality of the application works correctly, so that I can access my account securely.

  Background: 
    Given I am on the SauceDemo login page

  Scenario: User opens the login page successfully
    Then The login page should be displayed

  Scenario Outline: login with valid account
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should be on the inventory page

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |

  Scenario Outline: Login with invalid account
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see an error message

    Examples: 
      | username | password |
      | xx       | xx       |

  Scenario Outline: User logs out successfully
    When I enter username "<username>" and password "<password>"
    And I click the login button
    And User clicks the logout button
    Then The user should be logged out successfully

    Examples: 
      | username      | password     |
      | standard_user | secret_sauce |
