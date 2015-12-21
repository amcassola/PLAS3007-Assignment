$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("notes.feature");
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
  "duration": 164540634,
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
  "duration": 22585802585,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.logInWithValidCredentialsForExistingAccount()"
});
formatter.result({
  "duration": 21527257354,
  "status": "passed"
});
formatter.scenario({
  "line": 9,
  "name": "Log out",
  "description": "",
  "id": "creation-of-notes;log-out",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 8,
      "name": "@logout"
    }
  ]
});
formatter.step({
  "line": 10,
  "name": "a note is created with title \u0027Sample title\u0027 and body \u0027This is some body text\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "the note with title \u0027Sample title\u0027 and date \u0027moments ago\u0027 is available in the list of notes",
  "keyword": "Then "
});
formatter.step({
  "line": 12,
  "name": "the account is logged out from",
  "keyword": "When "
});
formatter.match({
  "arguments": [
    {
      "val": "Sample title",
      "offset": 30
    },
    {
      "val": "This is some body text",
      "offset": 54
    }
  ],
  "location": "HomeSteps.createNote(String,String)"
});
formatter.result({
  "duration": 6956410132,
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
  "duration": 291626999,
  "status": "passed"
});
formatter.match({
  "location": "HomeSteps.logOut()"
});
formatter.result({
  "duration": 12536491546,
  "status": "passed"
});
formatter.after({
  "duration": 11000794215,
  "status": "passed"
});
formatter.after({
  "duration": 12460138708,
  "status": "passed"
});
formatter.after({
  "duration": 246776,
  "status": "passed"
});
formatter.before({
  "duration": 95374,
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
  "duration": 18159167,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.logInWithValidCredentialsForExistingAccount()"
});
formatter.result({
  "duration": 93802899,
  "status": "passed"
});
formatter.scenario({
  "line": 15,
  "name": "Log out",
  "description": "",
  "id": "creation-of-notes;log-out",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 14,
      "name": "@logout"
    }
  ]
});
formatter.step({
  "line": 16,
  "name": "a note is created with title \u0027Sample title\u0027 and body \u0027This is some body text\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "the note with title \u0027Sample title\u0027 and date \u0027moments ago\u0027 is available in the list of notes",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "the account is logged out from",
  "keyword": "When "
});
formatter.match({
  "arguments": [
    {
      "val": "Sample title",
      "offset": 30
    },
    {
      "val": "This is some body text",
      "offset": 54
    }
  ],
  "location": "HomeSteps.createNote(String,String)"
});
formatter.result({
  "duration": 3961201937,
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
  "duration": 303785287,
  "status": "passed"
});
formatter.match({
  "location": "HomeSteps.logOut()"
});
formatter.result({
  "duration": 7788933854,
  "status": "passed"
});
formatter.after({
  "duration": 10665215952,
  "status": "passed"
});
formatter.after({
  "duration": 10499071919,
  "status": "passed"
});
formatter.after({
  "duration": 103072,
  "status": "passed"
});
formatter.before({
  "duration": 141565,
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
  "duration": 21527630,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.logInWithValidCredentialsForExistingAccount()"
});
formatter.result({
  "duration": 20873269,
  "status": "passed"
});
formatter.scenario({
  "line": 21,
  "name": "Log out",
  "description": "",
  "id": "creation-of-notes;log-out",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 20,
      "name": "@logout"
    }
  ]
});
formatter.step({
  "line": 22,
  "name": "a note is created with title \u0027Sample title\u0027 and body \u0027This is some body text\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 23,
  "name": "the note with title \u0027Sample title\u0027 and date \u0027moments ago\u0027 is available in the list of notes",
  "keyword": "Then "
});
formatter.step({
  "line": 24,
  "name": "the account is logged out from",
  "keyword": "When "
});
formatter.match({
  "arguments": [
    {
      "val": "Sample title",
      "offset": 30
    },
    {
      "val": "This is some body text",
      "offset": 54
    }
  ],
  "location": "HomeSteps.createNote(String,String)"
});
formatter.result({
  "duration": 4911556447,
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
  "duration": 385271654,
  "status": "passed"
});
formatter.match({
  "location": "HomeSteps.logOut()"
});
formatter.result({
  "duration": 4270323904,
  "status": "passed"
});
formatter.after({
  "duration": 10324610655,
  "status": "passed"
});
formatter.after({
  "duration": 9846557418,
  "status": "passed"
});
formatter.after({
  "duration": 106922,
  "status": "passed"
});
formatter.before({
  "duration": 127023,
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
  "duration": 23592076,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.logInWithValidCredentialsForExistingAccount()"
});
formatter.result({
  "duration": 18620213,
  "status": "passed"
});
formatter.scenario({
  "line": 27,
  "name": "Log out",
  "description": "",
  "id": "creation-of-notes;log-out",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 26,
      "name": "@logout"
    }
  ]
});
formatter.step({
  "line": 28,
  "name": "a note is created with title \u0027Sample title\u0027 and body \u0027This is some body text\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 29,
  "name": "the note with title \u0027Sample title\u0027 and date \u0027moments ago\u0027 is available in the list of notes",
  "keyword": "Then "
});
formatter.step({
  "line": 30,
  "name": "the account is logged out from",
  "keyword": "When "
});
formatter.match({
  "arguments": [
    {
      "val": "Sample title",
      "offset": 30
    },
    {
      "val": "This is some body text",
      "offset": 54
    }
  ],
  "location": "HomeSteps.createNote(String,String)"
});
formatter.result({
  "duration": 4529963758,
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
  "duration": 455134341,
  "status": "passed"
});
formatter.match({
  "location": "HomeSteps.logOut()"
});
formatter.result({
  "duration": 4370731576,
  "status": "passed"
});
formatter.after({
  "duration": 9990899246,
  "status": "passed"
});
formatter.after({
  "duration": 10525656948,
  "status": "passed"
});
formatter.after({
  "duration": 104783,
  "status": "passed"
});
formatter.before({
  "duration": 117186,
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
  "duration": 22775621,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.logInWithValidCredentialsForExistingAccount()"
});
formatter.result({
  "duration": 23041643,
  "status": "passed"
});
formatter.scenario({
  "line": 33,
  "name": "Log out",
  "description": "",
  "id": "creation-of-notes;log-out",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 32,
      "name": "@logout"
    }
  ]
});
formatter.step({
  "line": 34,
  "name": "a note is created with title \u0027Sample title\u0027 and body \u0027This is some body text\u0027",
  "keyword": "When "
});
formatter.step({
  "line": 35,
  "name": "the note with title \u0027Sample title\u0027 and date \u0027moments ago\u0027 is available in the list of notes",
  "keyword": "Then "
});
formatter.step({
  "line": 36,
  "name": "the account is logged out from",
  "keyword": "When "
});
formatter.match({
  "arguments": [
    {
      "val": "Sample title",
      "offset": 30
    },
    {
      "val": "This is some body text",
      "offset": 54
    }
  ],
  "location": "HomeSteps.createNote(String,String)"
});
formatter.result({
  "duration": 4148640083,
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
  "duration": 426287702,
  "status": "passed"
});
formatter.match({
  "location": "HomeSteps.logOut()"
});
formatter.result({
  "duration": 4429307601,
  "status": "passed"
});
formatter.after({
  "duration": 10659215930,
  "status": "passed"
});
formatter.after({
  "duration": 9695248140,
  "status": "passed"
});
formatter.after({
  "duration": 82971,
  "status": "passed"
});
});