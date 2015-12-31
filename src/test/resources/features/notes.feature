@notes
Feature: Creation of notes

  Background:
    Given the Evernote Home page is loaded
    And an existing account is logged in to

  Scenario Outline: Create a note
    When a note is created with title '<title>' and body '<body>'
    Then the note with title '<title>' is available in the list of notes
    When the account is logged out from
    And the Evernote Login page is loaded
    And the existing account is logged in to
    Then the note with title '<title>' is still available in the list of notes
  Examples:
    | title        | body                   |
    | Sample title | This is some body text |

  @shortcuts
  Scenario Outline: Add note to shortcuts
    When a note is created with title '<title>' and body '<body>'
    And the note with title '<title>' is added to shortcuts
    Then the note with title '<title>' is visible under the shortcut list
  Examples:
    | title                     | body                                              |
    | Sample note with shortcut | This is some body text for a note with a shortcut |

  @table
  Scenario Outline: Add a table to note body
    When a note is created with title '<title>' and body containing a table with <rows> rows and <columns> columns
    Then the note with title '<title>' has a table with <rows> rows and <columns> columns
  Examples:
    | title                  | rows | columns |
    | Sample note with table | 3    | 3       |
#    | Another note with a table | 4    | 4       |

  @notes
  @sorting
  Scenario: Sorting
    When notes are created with title and body:
      | title                            | body                                                  |
      | Sample note for sorting          | This is some body text for note 1 that will be sorted |
      | Another sample note for sorting  | This is some body text for note 2 that will be sorted |
      | One more sample note for sorting | This is some body text for note 3 that will be sorted |
    And the notes are sorted by Date Created (oldest first)
    Then the notes in the notes list are in the following order:
      | Sample note for sorting          |
      | Another sample note for sorting  |
      | One more sample note for sorting |
    When the notes are sorted by Date Created (newest first)
    Then the notes in the notes list are in the following order:
      | One more sample note for sorting |
      | Another sample note for sorting  |
      | Sample note for sorting          |
    When the notes are sorted by Title (ascending)
    Then the notes in the notes list are in the following order:
      | Another sample note for sorting  |
      | One more sample note for sorting |
      | Sample note for sorting          |
    When the notes are sorted by Title (descending)
    Then the notes in the notes list are in the following order:
      | Sample note for sorting          |
      | One more sample note for sorting |
      | Another sample note for sorting  |

  @search
  Scenario: Search notes
    When notes are created with title and body:
      | title                    | body                                                       |
      | Sample note 1 for search | This is some body text for note 1 that may be searched for |
      | Sample note 2 for search | This is some body text for note 2 that may be searched for |
      | Sample note 3 for search | This is some body text for note 3 that may be searched for |
    And notes are searched for using the text 'Sample note 2 for search'
    Then 1 note is found
    And the note with title 'Sample note 2 for search' is available in the list of notes
    When notes are searched for using the text 'body text for note 1'
    Then 1 note is found
    And the note with title 'Sample note 1 for search' is available in the list of notes

  @notebooks
  Scenario Outline: Adding notes to notebooks
    When a notebook is created with title '<notebook_title>'
    And a note is created in notebook '<notebook_title>' with title '<note_title>' and body '<note_body>'
    Then the note with title '<note_title>' is available under notebook '<notebook_title>'
    And the note with title '<note_title>' is not available under notebook 'DEFAULT_NOTEBOOK_PLACEHOLDER'
  Examples:
    | notebook_title   | note_title                    | note_body                                       |
    | My test notebook | Sample note for test notebook | This is some body text for a note in a notebook |

  @trashcan
  Scenario: Trash can
    When notes are created with title and body:
      | title                       | body                                                        |
      | Sample note to be deleted 1 | This is some body text for a note that will be deleted soon |
      | Sample note to be deleted 2 | This is some body text for a note that will be deleted soon |
      | Sample note to be deleted 3 | This is some body text for a note that will be deleted soon |
    When all notes are deleted
    Then the trash can contains the notes with title:
      | Sample note to be deleted 1 |
      | Sample note to be deleted 2 |
      | Sample note to be deleted 3 |
    When the trash can is emptied
    Then there are no notes in the trash can

  @trashcan
  Scenario Outline: Restoring notes from trash can
    When a note is created with title '<title>' and body '<body>'
    When the note with title '<title>' is deleted
    And the note with title '<title>' is restored
    Then the note with title '<title>' is available in the list of notes
  Examples:
    | title                      | body                                                                 |
    | Sample note to be restored | This is some body text for a note that will be deleted then restored |

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
