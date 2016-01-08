@login
Feature: Evernote login testing

  Background:
    Given the Evernote Login page is loaded

  @requiresLogout
  Scenario: Successful login
    When logging in with valid credentials for an existing account
    Then the Home page is displayed
    And the account username matches the logged in username

  Scenario Outline: Missing or invalid login credentials
    When email address <email_address> and password <password> are entered as login credentials
    Then error message '<message>' is displayed for missing or invalid credentials
  Examples:
    | email_address | password | message                             |
    |               |          | This is a required field.           |
    | abc@xyz.com   |          | This is a required field.           |
    |               | asdufh   | Incorrect username and/or password. |
    | abc@xyz.com   | asdufh   | Incorrect username and/or password. |

  Scenario Outline: Incorrect login credentials
    When logging in with incorrect credentials for an existing account
    Then error message '<standard_message> <optional_message>' is displayed for incorrect credentials
  Examples:
    | standard_message                    | optional_message                                                            |
    | Incorrect username and/or password. | You modified your password DAYS_OR_HOURS_SINCE_PASSWORD_CHANGE_PLACEHOLDER. |

