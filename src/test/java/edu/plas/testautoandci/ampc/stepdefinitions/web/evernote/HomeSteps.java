package edu.plas.testautoandci.ampc.stepdefinitions.web.evernote;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.ampc.Note;
import edu.plas.testautoandci.ampc.pageobjectmodels.web.evernote.HomePage;
import edu.plas.testautoandci.ampc.utils.PropertyUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class HomeSteps {

    private static final String username = PropertyUtils.getLoginUserName();

    HomePage page = new HomePage();

    @Then("^the [hH]ome page is displayed$")
    public void homePageIsDisplayed() {
        assertTrue("Home Page is expected to be displayed", page.isHomePageDisplayed());
    }

    @Then("^the account username matches the logged in username$")
    public void confirmLoggedInUser() {
        assertEquals("Account name must match logged in user",
                page.getLoggedInUsername().toLowerCase(), username.toLowerCase());
    }

    @When("^a note is created with title '(.*)' and body '(.*)'$")
    public void createNote(String title, String body) {
        page.createNote(new Note(title, body), null);
    }

    @When("^a note is created in notebook '(.*)' with title '(.*)' and body '(.*)'$")
    public void createNote(String notebookTitle, String title, String body) {
        page.createNote(new Note(title, body), notebookTitle);
    }

    @When("^(?:a )?note(?:s)? (?:is|are) created with title and body(?::)?$")
    public void createNote(List<Note> notes) {
        page.createNotes(notes);
    }

    @When("^the account is logged out from$")
    public void logOut() {
        page.logOut();
    }

    @Then("^the note with title '(.*)' is (?:still )?available in the list of notes$")
    public void isNoteInList(String title) {
        assertTrue("Note is expected to be found in note list", page.isNoteInNoteList(title));
    }

    @When("^the note with title '(.*)' is added to shortcuts$")
    public void addNoteToShortcuts(String title) {
        page.addNoteToShortcuts(title);
    }

    @Then("^the note with title '(.*)' is visible under the shortcut list$")
    public void isShortcutAvailable(String title) {
        assertTrue("Note is expected to be found in shortcut list", page.isNoteUnderShortcuts(title));
    }

    @When("^a note is created with title '(.*)' and body containing a table with ([0-6]) rows and ([0-6]) columns$")
    public void addTableToNoteBody(String title, int rows, int columns) {
        page.createNoteWithTable(title, rows, columns);
    }

    @Then("^the note with title '(.*)' has a table with ([0-6]) rows and ([0-6]) columns$")
    public void noteContainsTable(String title, int rows, int columns) {
        assertTrue("Note with title '" + title + "' expected to contain table with " + rows + " row/s and " + columns + " column/s", page.noteContainsTable(title, rows, columns));
    }

    @Then("^the notes are sorted by (.*)$")
    public void sortNotes(String order) {
        page.sortNotesList(order);
    }

    @Then("^the notes in the notes list are in the following order:$")
    public void checkNoteOrdering(List<String> titlesInExpectedOrder) {
        assertTrue("Notes are expected to be ordered as selected", page.checkNotesOrdering(titlesInExpectedOrder));
    }

    @When("^all notes are deleted$")
    public void deleteAllNotes() {
        page.deleteAllNotes();
    }

    @When("the note with title '(.*)' is deleted")
    public void deleteNote(String title) {
        page.deleteNote(title);
    }

    @When("^notes are searched for using the text '(.*)'$")
    public void searchNotes(String text) {
        page.searchNotes(text);
    }

    @Then("^(\\d+) note(?:s)? (?:is|are) found$")
    public void checkNumberOfNotesFound(int expectedCount) {
        assertEquals("Number of notes expected to match", expectedCount, page.getNoteCount());
    }

    @When("^a notebook is created with title '(.*)'$")
    public void createNotebook(String title) {
        page.createNewNotebook(title);
    }

    @Then("the note with title '(.*)' is( not)? available under notebook '(.*)'")
    public void isNoteInNotebook(String title, String not, String notebookTitle) {
        // replace placeholder if present in notebook title
        notebookTitle = notebookTitle.replace("DEFAULT_NOTEBOOK_PLACEHOLDER", PropertyUtils.getProperty("evernote.notebook.default"));

        boolean negate = not != null;
        if (negate) {
            assertTrue("Note is not expected to be found in notebook", !page.isNoteInNotebook(title, notebookTitle));
        } else {
            assertTrue("Note is expected to be found in notebook", page.isNoteInNotebook(title, notebookTitle));
        }
    }

    @Then("^the trash can contains the notes with title:$")
    public void trashCanContainsNotes(List<String> titles) {
        assertTrue("Trash can is expected to contain notes", page.trashCanContainsNotes(titles));
    }

    @When("^the trash can is emptied$")
    public void emptyTrashCan() {
        page.emptyTrashCan();
    }

    @Then("^there are no notes in the trash can$")
    public void isTrashCanEmpty() {
        assertTrue("Trash can is expected to be empty", page.isTrashCanEmpty());
    }

    @When("^the note with title '(.*)' is restored$")
    public void restoreNote(String title) {
        page.restoreNoteFromTrashCan(title);
    }

    @When("^a tag '(.*)' is assigned to the notes with title:$")
    public void assignTag(String tag, List<String> noteTitles) {
        page.addTagToNotes(tag, noteTitles);
    }

    @Then("^the notes under tag '(.*)' are:$")
    public void checkNotesWithTag(String tag, List<String> noteTitles) {
        assertTrue("All note titles are expected to be found under tag " + tag, page.notesHaveTag(tag, noteTitles));
    }

}
