@contacts
Feature: Android contacts

  Scenario Outline: Launching Android Contact book
    When a contact with name <name>, mobile number <mobile_number>, home number <home_number>, email <email> is added
    Then the new contact has name <name>, mobile number <mobile_number>, home number <home_number>, email <email>
    When the contact name <name> is clicked
    Then the contact with name <name> is available in the list
    When the contact with name <name> in the list is clicked
    And the contact is edited to have name <modified_name> and mobile number <modified_mobile_number>
    Then the contact has name <modified_name>, mobile number <modified_mobile_number>, home number <home_number>, email <email>
  Examples:
    | name       | mobile_number | home_number  | email                       | modified_name | modified_mobile_number |
    | Anna Maria | +35679632015  | +35621632015 | annamaria.cassola@go.com.mt | AMC           | +35679632013           |