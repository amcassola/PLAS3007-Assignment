$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("noteCreation.feature");
formatter.feature({
  "line": 2,
  "name": "Creation of notes",
  "description": "",
  "id": "creation-of-notes",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@notes"
    }
  ]
});
formatter.before({
  "duration": 18518076195,
  "status": "passed"
});
formatter.background({
  "line": 4,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 5,
  "name": "the Evernote Home page is loaded",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "an existing account is logged in to",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "Evernote Home",
      "offset": 4
    }
  ],
  "location": "NavigationSteps.siteIsLoaded(String)"
});
formatter.result({
  "duration": 5655297889,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.logInWithValidCredentialsForExistingAccount()"
});
formatter.result({
  "duration": 16699072090,
  "status": "passed"
});
formatter.scenario({
  "line": 8,
  "name": "Create a note",
  "description": "",
  "id": "creation-of-notes;create-a-note",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 9,
  "name": "a note with title \u0027Sample title\u0027 and body \u0027This is some body text\u0027 is created",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "the note with title \u0027Sample title\u0027 and date \u0027moments ago\u0027 is available in the list of notes",
  "keyword": "Then "
});
formatter.step({
  "line": 11,
  "name": "the account is logged out from",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "the Evernote Login page is loaded",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "the existing account is logged in to",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "the note with title \u0027Sample title\u0027 and date \u0027NOTE_DATE_PLACEHOLDER\u0027 is still available in the list of notes",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Sample title",
      "offset": 19
    },
    {
      "val": "This is some body text",
      "offset": 43
    }
  ],
  "location": "HomeSteps.createNote(String,String)"
});
formatter.result({
  "duration": 8493108678,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Sample title",
      "offset": 21
    },
    {
      "val": "moments ago",
      "offset": 45
    }
  ],
  "location": "HomeSteps.isNoteInList(String,String)"
});
formatter.result({
  "duration": 511113449,
  "status": "passed"
});
formatter.match({
  "location": "HomeSteps.logOut()"
});
formatter.result({
  "duration": 8753641969,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Evernote Login",
      "offset": 4
    }
  ],
  "location": "NavigationSteps.siteIsLoaded(String)"
});
formatter.result({
  "duration": 1748832629,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.logInWithValidCredentialsForExistingAccount()"
});
formatter.result({
  "duration": 6808451354,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Sample title",
      "offset": 21
    },
    {
      "val": "NOTE_DATE_PLACEHOLDER",
      "offset": 45
    }
  ],
  "location": "HomeSteps.isNoteInList(String,String)"
});
formatter.result({
  "duration": 3810157825,
  "status": "passed"
});
formatter.after({
  "duration": 4493003895,
  "status": "passed"
});
formatter.after({
  "duration": 2406446870,
  "status": "passed"
});
});