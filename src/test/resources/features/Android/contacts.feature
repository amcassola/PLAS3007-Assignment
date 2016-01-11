@contacts
Feature: Android Contacts App

  Scenario Outline: Launching Android Contact book
    When a contact with name '<name>', mobile number '<mobile_number>', home number '<home_number>', email '<email>' is added
    Then the new contact has name '<name>', mobile number '<mobile_number>', home number '<home_number>', email '<email>'
    When the contact name '<name>' is clicked
    Then the contact with name '<name>' is available in the contacts list
    When the contact with name '<name>' in the list is clicked
    And the contact is edited to have name '<modified_name>' and mobile number '<modified_mobile_number>'
    Then the contact has name '<modified_name>', mobile number '<modified_mobile_number>', home number '<home_number>', email '<email>'
    When the contact name '<modified_name>' is clicked
    And the 'Favorites' button is clicked
    Then 'No favorites.' is displayed
    When the 'All contacts' button is clicked
    And the contact with name '<modified_name>' in the list is clicked
    And the 'Add to favorites' button is clicked
    When the contact name '<modified_name>' is clicked
    And the 'Favorites' button is clicked
    Then the contact with name '<modified_name>' is available in the favorites list
  Examples:
    | name       | mobile_number | home_number  | email                     | modified_name | modified_mobile_number |
    | Robin Hood | +35679632015  | +35621632015 | robin.hood@nottingham.com | Little John   | +35679632013           |
