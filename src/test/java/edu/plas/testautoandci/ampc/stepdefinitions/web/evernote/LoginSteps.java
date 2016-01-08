package edu.plas.testautoandci.ampc.stepdefinitions.web.evernote;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;
import edu.plas.testautoandci.ampc.pageobjectmodels.LoginPage;
import edu.plas.testautoandci.ampc.utils.PropertyUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 25/11/2015
 */
public class LoginSteps implements En {

    private static final String USERNAME = PropertyUtils.getLoginUserName();
    private static final String PASSWORD_VALID = PropertyUtils.getLoginPassword();
    private static final String PASSWORD_INVALID = PropertyUtils.getProperty("evernote.password.invalid");

    LoginPage page = new LoginPage();

    @When("^(?:logging in with valid credentials for an existing account)|(?:(?:an|the) existing account is logged in to)$")
    public void logInWithValidCredentialsForExistingAccount() {
        if (page.isLoginPageDisplayed()){
            logIn(USERNAME, PASSWORD_VALID);
        }
    }

    @When("^logging in with incorrect credentials for an existing account$")
    public void logInWithIncorrectCredentialsForExistingAccount() {
        logIn(USERNAME, PASSWORD_INVALID);
    }

    @When("^email address (.*) and password (.*) are entered as login credentials$")
    public void logIn(String username, String password) {
        page.signIn(username, password);
    }

    @Then("^error message '(.*)' is displayed for missing or invalid credentials$")
    public void incorrectCredentialsErrorMessageIsDisplayed(String message) {
        assertEquals("Error message expected", message, page.getLoginErrorMessage());
    }

    @Then("^error message '(.*) (.*)' is displayed for incorrect credentials$")
    public void incorrectCredentialsErrorMessageIsDisplayed(String standardMessage, String optionalMessage) {
        String optionalMessageRegex = optionalMessage.replace("DAYS_OR_HOURS_SINCE_PASSWORD_CHANGE_PLACEHOLDER", "(in the past )?\\d+ (day(s)?|hour(s)?)( ago)?");
        String errorMessageRegex = "^" + standardMessage + "( " + optionalMessageRegex + ")?$";
        String errorMessage = page.getLoginErrorMessage();
        assertTrue("Error message expected to match [" + errorMessageRegex + "]. Error message is: " + errorMessage, errorMessage.matches(errorMessageRegex));
    }
}
