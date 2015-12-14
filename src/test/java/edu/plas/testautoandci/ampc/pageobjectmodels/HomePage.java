package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.utils.SiteUrlUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class HomePage extends EvernotePage {

    private MainMenu mainMenu = new MainMenu();
    private AccountMenu accountMenu = new AccountMenu();
    private NotesList notesList = new NotesList();
    private NoteSection noteSection = new NoteSection();

    public HomePage() {
        super("Evernote home");
    }

    public AccountMenu getAccountMenu() {
        return accountMenu;
    }

    public NotesList getNotesList() {
        return notesList;
    }

    public NoteSection getNoteSection() {
        return noteSection;
    }

    public boolean isHomePageDisplayed() {
        return Driver.getWebDriver().getCurrentUrl().startsWith(SiteUrlUtils.getSiteUrl("Evernote Home"));
    }

    public String getLoggedInUsername() {
        displayAccountMenu();
        return accountMenu.getAccountUsername();
    }

    public void displayAccountMenu() {
        accountMenu.waitForAvailability();

        WebElement accountMenuItem = Driver.getWebDriver().findElement(By.id("gwt-debug-AccountMenu-avatar"));
        accountMenuItem.click();
    }

    public void createNote(String title, String body){
        boolean firstNote = notesList.getNotes().isEmpty();
        if (! firstNote){
            mainMenu.clickNewNoteButton();
        }

        noteSection.createNote(title, body);

        if (! firstNote){
            noteSection.clickDoneButton();
        }

        notesList.waitForAdditionOfNote();
    }
}
