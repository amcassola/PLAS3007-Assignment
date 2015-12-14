@notes
Feature: Creation of notes

  Background:
    Given the Evernote Home page is loaded
    And an existing account is logged in to

  Scenario: Create a note
    When a note with title 'Sample title' and body 'This is some body text' is created
    Then the note with title 'Sample title' and date 'moments ago' is available in the list of notes
    When the account is logged out from
    And the Evernote Login page is loaded
    And the existing account is logged in to
    Then the note with title 'Sample title' and date 'NOTE_DATE_PLACEHOLDER' is still available in the list of notes
