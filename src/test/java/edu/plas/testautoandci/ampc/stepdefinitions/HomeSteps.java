package edu.plas.testautoandci.ampc.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.ampc.pageobjectmodels.HomePage;
import edu.plas.testautoandci.ampc.utils.PropertyUtils;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    public void homePageIsDisplayed(){
        assertTrue("Home Page is expected to be displayed", page.isHomePageDisplayed());
    }

    @Then("^the account username matches the logged in username$")
    public void confirmLoggedInUser() {
        assertEquals("Account name must match logged in user", page.getLoggedInUsername().toLowerCase(), username.toLowerCase());
    }

    @When("a note with title '(.*)' and body '(.*)' is created")
    public void createNote(String title, String body){
        page.createNote(title, body);
    }

    @When("^the account is logged out from$")
    public void logOut(){
        page.displayAccountMenu();
        page.getAccountMenu().clickLogOut();
    }

    @Then("the note with title '(.*)' and date '(.*)' is (?:still )?available in the list of notes")
    public void isNoteInList(String title, String date){
        System.out.println("*********** date: " + date);
        String dateRegex = "(?i)" + date.replace("NOTE_DATE_PLACEHOLDER", "\\d+ (day(s)?|hour(s)?|minute(s)?|second(s)?) ago");
        assertTrue("Note is expected to be found in note list", page.getNotesList().containsNote(title, dateRegex));
    }
}
