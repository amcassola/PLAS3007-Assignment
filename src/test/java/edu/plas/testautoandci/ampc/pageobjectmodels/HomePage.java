package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.Note;
import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.FrameAndAlertHelper;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import edu.plas.testautoandci.ampc.utils.SiteUrlUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

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
    private ShortcutsList shortcutsList = new ShortcutsList();
    private TagsList tagsList = new TagsList();

    public HomePage() {
        super("Evernote home");
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
        DriverHelper.findElement(By.id("gwt-debug-AccountMenu-avatar")).click();
    }

    public void createNote(Note note) {
        noteSection.waitForAvailability();
        mainMenu.clickNewNoteButton();
        noteSection.createNote(note.getTitle(), note.getBody());
        noteSection.clickDoneButton();
        notesList.waitForAdditionOfNote(note.getTitle());
    }

    public void createNotes(List<Note> notes){
        for(Note note : notes){
            createNote(note);
        }
    }

    public boolean isNoteInNoteList(String title, String noteDateRegex){
        accountMenu.waitForAvailability();
        return notesList.containsNote(title, noteDateRegex);
    }

    private void confirmDelete() {
        DriverHelper.findElement(By.id("gwt-debug-ConfirmationDialog-confirm")).click();
    }

    public void deleteAllNotes() {
        System.out.println("********** Clearing notes.....");
        mainMenu.clickNotesButton();

        List<WebElement> notes = notesList.getNotes();

        WebElement note;
        for (int i = notes.size(); i != 0; i--) {
//            System.out.println("******** i=" + i + ", notes.size() = " + notes.size());
            note = notes.get(i - 1);
            note.click();
            noteSection.clickDeleteNoteButton();
            confirmDelete();
            waitForTrashMessage();
        }
        System.out.println("********** Cleared all notes.....");
    }

    public void waitForTrashMessage(){
        By trashMessageLocator = By.xpath("//div[@id='gwt-debug-toastContainer']//span[text()='Trash']");
        WaitHelper.disableImplicitWait();
        new WebDriverWait(Driver.getWebDriver(), WaitHelper.EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(trashMessageLocator));
        new WebDriverWait(Driver.getWebDriver(), WaitHelper.EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.invisibilityOfElementLocated(trashMessageLocator));
        WaitHelper.enableImplicitWait();
    }

    public void addNoteToShortcuts(String title){
        List<WebElement> notes = notesList.getNotes();
        if (notes.size() > 0){
            notesList.addNoteToShortcuts(notes.get(0));
        }
    }

    public void deleteAllShortcuts(){
        mainMenu.clickShortcutsButton();
        shortcutsList.deleteAllShortcuts();
    }

    public boolean isNoteUnderShortcuts(String title){
        mainMenu.clickShortcutsButton();
        return shortcutsList.containsNote(title);
    }

    public void addTagToNotes(String tag, List<String> titles){
        mainMenu.clickNotesButton();

        for (String title : titles) {
            notesList.clickNote(title);
            noteSection.addTag(tag);
            noteSection.waitForAdditionOfTag(tag);
        }
    }

    public boolean notesHaveTag(String tag, List<String> titles){
        mainMenu.clickTagsButton();
        tagsList.clickTag(tag);


        // When tag list is clicked, the notes list is displayed with only those notes having the matching tag
        // However, at times, the notes list is displayed with the full list of notes, and is then 'refreshed'
        // with only the tagged notes. Hence the requirement for a pause.
        WaitHelper.pause(2);

        // check that notes under a particular tag contain the list of note titles provided
        boolean allFound = true;
        for (String title : titles){
            if (notesList.getNotes(title, null).size() == 0){
                return false;
            }
        }

        return true;
    }

    public void deleteAllTags(){
        mainMenu.clickTagsButton();
        tagsList.deleteAllTags();
    }

    public void logOut(){
        boolean loggedOut = false;
        do {
            System.out.println("**** Attempting logout...");
            displayAccountMenu();
            if (accountMenu.isDisplayed()) {
                accountMenu.clickLogOut();
                try {
                    Driver.getWebDriver().switchTo().alert().dismiss();
                    WaitHelper.pause(2);
                    // try to log out again
                } catch (NoAlertPresentException Ex) {
                    loggedOut = true;
                }
            } else {
                throw new RuntimeException("Account Menu is not visible. Cannot log out. Account Menu must be made visible in order to log out.");
            }
        } while (! loggedOut);
        System.out.println("**** Logged out!");
    }
}
