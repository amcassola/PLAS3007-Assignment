package edu.plas.testautoandci.ampc.stepdefinitions.mobile.android;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.ampc.pageobjectmodels.mobile.android.ContactsApp;
import static org.junit.Assert.assertTrue;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 08/01/2016
 */
public class ContactSteps {
    ContactsApp app = new ContactsApp();

    @When("^a contact with name '(.*)', mobile number '(\\+?\\d+)', home number '(\\+?\\d+)', email '(.*)' is added$")
    public void addContact(String name, String mobileNo, String homeNo, String email){
        app.addContact(name, mobileNo, homeNo, email);
    }

    @Then("^the (?:new )?contact has name '(.*)', mobile number '(\\+?\\d+)', home number '(\\+?\\d+)', email '(.*)'$")
    public void contactHasDetails(String name, String mobileNo, String homeNo, String email){
        assertTrue(app.contactHasDetails(name, mobileNo, homeNo, email));
    }

    @When("^the contact name '(.*)' is clicked$")
    public void clickContactName(String name){
        app.clickContactNameToGoToContactList(name);
    }

    @Then("^the contact with name '(.*)' is available in the contacts list$")
    public void contactExists(String name){
        assertTrue(app.contactExists(name));
    }

    @When("^the contact with name '(.*)' in the list is clicked$")
    public void selectContactFromList(String name){
        app.clickContactNameInContactList(name);
    }

    @When("^the contact is edited to have name '(.*)' and mobile number '(.*)'$")
    public void editContact(String newName, String newMobileNumber){
        app.editContact(newName, newMobileNumber);
    }

    @When("^the '(.*)' button is clicked$")
    public void clickButton(String buttonName){
        app.click(buttonName);
    }

    @Then("^'(.*)' is displayed$")
    public void isTextDisplayed(String text){
        assertTrue("'" + text + "' is expected to be displayed", app.isTextDisplayed(text));
    }

    @Then("^the contact with name '(.*)' is available in the favorites list$")
    public void contactIsFavorite(String name){
        app.isContactFavorite(name);
    }

}
