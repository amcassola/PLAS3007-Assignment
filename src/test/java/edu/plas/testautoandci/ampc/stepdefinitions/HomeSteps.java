package edu.plas.testautoandci.ampc.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.ampc.Note;
import edu.plas.testautoandci.ampc.pageobjectmodels.HomePage;
import edu.plas.testautoandci.ampc.utils.PropertyUtils;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.*;

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
        assertEquals("Account name must match logged in user", page.getLoggedInUsername().toLowerCase(), username.toLowerCase());
    }

    @When("a note is created with title '(.*)' and body '(.*)'")
    public void createNote(String title, String body) {
        page.createNote(new Note(title, body));
    }

    @When("^(?:a )?note(?:s)? (?:is|are) created with title and body(?::)?$")
    public void createNote(List<Note> notes) {
        page.createNotes(notes);
    }

    @When("^the account is logged out from$")
    public void logOut() {
        page.logOut();
    }

    @Then("the note with title '(.*)' and date '(.*)' is (?:still )?available in the list of notes")
    public void isNoteInList(String title, String date) {
        String dateRegex = "(?i)" + date.replace("NOTE_DATE_PLACEHOLDER", "\\d+ (day(s)?|hour(s)?|minute(s)?|second(s)?) ago");
        assertTrue("Note is expected to be found in note list", page.isNoteInNoteList(title, dateRegex));
    }

    @When("the note with title '(.*)' is added to shortcuts")
    public void addNoteToShortcuts(String title) {
        page.addNoteToShortcuts(title);
    }

    @Then("the note with title '(.*)' is visible under the shortcut list")
    public void isShortcutAvailable(String title){
        assertTrue("Note is expected to be found in shortcut list", page.isNoteUnderShortcuts(title));
    }

    @When("a tag '(.*)' is assigned to the notes with title:")
    public void assignTag(String tag, List<String> noteTitles){
        page.addTagToNotes(tag, noteTitles);
    }

    @Then("the notes under tag '(.*)' are:")
    public void checkNotesWithTag(String tag, List<String> noteTitles){
        assertTrue("All note titles are expected to be found under tag " + tag, page.notesHaveTag(tag, noteTitles));
    }

}
