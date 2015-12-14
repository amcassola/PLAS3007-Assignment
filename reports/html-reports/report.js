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
  "duration": 18553950161,
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
  "duration": 9692719654,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.logInWithValidCredentialsForExistingAccount()"
});
formatter.result({
  "duration": 18399175761,
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
  "duration": 70697212935,
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
  "duration": 392291282,
  "status": "passed"
});
formatter.match({
  "location": "HomeSteps.logOut()"
});
formatter.result({
  "duration": 7246501734,
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
  "duration": 1997716578,
  "status": "passed"
});
formatter.match({
  "location": "LoginSteps.logInWithValidCredentialsForExistingAccount()"
});
formatter.result({
  "duration": 7631508221,
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
  "duration": 2706413339,
  "error_message": "java.lang.AssertionError: Note is expected to be found in note list\r\n\tat org.junit.Assert.fail(Assert.java:88)\r\n\tat org.junit.Assert.assertTrue(Assert.java:41)\r\n\tat edu.plas.testautoandci.ampc.stepdefinitions.HomeSteps.isNoteInList(HomeSteps.java:48)\r\n\tat ✽.Then the note with title \u0027Sample title\u0027 and date \u0027NOTE_DATE_PLACEHOLDER\u0027 is still available in the list of notes(noteCreation.feature:14)\r\n",
  "status": "failed"
});
formatter.after({
  "duration": 33627496516,
  "error_message": "org.openqa.selenium.TimeoutException: Timed out after 30 seconds waiting for element ([[FirefoxDriver: firefox on WINDOWS (171c04bb-7b05-44ac-a912-0b9ea218e6c6)] -\u003e css selector: .focus-NotesView-Note]) to become stale\nBuild info: version: \u00272.47.1\u0027, revision: \u0027unknown\u0027, time: \u00272015-07-30 11:02:44\u0027\nSystem info: host: \u0027itdplap006247\u0027, ip: \u002710.60.65.248\u0027, os.name: \u0027Windows 8.1\u0027, os.arch: \u0027amd64\u0027, os.version: \u00276.3\u0027, java.version: \u00271.8.0_40\u0027\nDriver info: org.openqa.selenium.firefox.FirefoxDriver\nCapabilities [{applicationCacheEnabled\u003dtrue, rotatable\u003dfalse, handlesAlerts\u003dtrue, databaseEnabled\u003dtrue, version\u003d42.0, platform\u003dWINDOWS, nativeEvents\u003dfalse, acceptSslCerts\u003dtrue, webStorageEnabled\u003dtrue, locationContextEnabled\u003dtrue, browserName\u003dfirefox, takesScreenshot\u003dtrue, javascriptEnabled\u003dtrue, cssSelectorsEnabled\u003dtrue}]\nSession ID: 171c04bb-7b05-44ac-a912-0b9ea218e6c6\r\n\tat org.openqa.selenium.support.ui.WebDriverWait.timeoutException(WebDriverWait.java:80)\r\n\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:229)\r\n\tat edu.plas.testautoandci.ampc.pageobjectmodels.NotesList.waitForRemovalOfNote(NotesList.java:50)\r\n\tat edu.plas.testautoandci.ampc.pageobjectmodels.NotesList.clear(NotesList.java:37)\r\n\tat edu.plas.testautoandci.ampc.helper.CucumberBeforeAfter.clearNotes(CucumberBeforeAfter.java:52)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:497)\r\n\tat cucumber.runtime.Utils$1.call(Utils.java:37)\r\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:13)\r\n\tat cucumber.runtime.Utils.invoke(Utils.java:31)\r\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\r\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:223)\r\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:211)\r\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:205)\r\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\r\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:91)\r\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\r\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\r\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\r\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:93)\r\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:37)\r\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\r\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\r\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\r\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\r\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\r\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\r\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:98)\r\n\tat org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:252)\r\n\tat org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:141)\r\n\tat org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:112)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:497)\r\n\tat org.apache.maven.surefire.util.ReflectionUtils.invokeMethodWithArray(ReflectionUtils.java:189)\r\n\tat org.apache.maven.surefire.booter.ProviderFactory$ProviderProxy.invoke(ProviderFactory.java:165)\r\n\tat org.apache.maven.surefire.booter.ProviderFactory.invokeProvider(ProviderFactory.java:85)\r\n\tat org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:115)\r\n\tat org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:75)\r\n",
  "status": "failed"
});
formatter.write("Time of failure: 14/12/2015 12:34:51");
formatter.write("\u003ca href\u003d\"1450092892670.jpeg\" target\u003d\"_blank\"\u003eClick to reveal screenshot at time of failure\u003c/a\u003e");
formatter.after({
  "duration": 4190279698,
  "status": "passed"
});
});