Feature: Creation of notes

  Background:
    Given the Evernote Home page is loaded
    And an existing account is logged in to

  @notes
  Scenario: Create a note
    When a note is created with title 'Sample title' and body 'This is some body text'
    Then the note with title 'Sample title' and date 'moments ago' is available in the list of notes
    When the account is logged out from
    And the Evernote Login page is loaded
    And the existing account is logged in to
    Then the note with title 'Sample title' and date 'NOTE_DATE_PLACEHOLDER' is still available in the list of notes

  @notes
  Scenario: Add note to shortcuts
    When a note is created with title 'Sample note with shortcut' and body 'This is some body text for a note with a shortcut'
    And the note with title 'Sample note with shortcut' is added to shortcuts
    Then the note with title 'Sample note with shortcut' is visible under the shortcut list


  @tags
  Scenario: Using tags
    When notes are created with title and body:
      | title                  | body                   |
      | Sample note with tag 1 | This is some body text |
      | Sample note with tag 2 | This is some body text |
      | Sample note with tag 3 | This is some body text |
    When a tag 'test' is assigned to the notes with title:
      | Sample note with tag 2 |
      | Sample note with tag 1 |
    Then the notes under tag 'test' are:
      | Sample note with tag 1 |
      | Sample note with tag 2 |
