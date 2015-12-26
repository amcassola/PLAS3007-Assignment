package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.Note;
import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import edu.plas.testautoandci.ampc.utils.PropertyUtils;
import edu.plas.testautoandci.ampc.utils.SiteUrlUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
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
    private NotebooksList notebooksList = new NotebooksList();
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

    public void createNote(Note note, String notebookTitle) {
        noteSection.waitForAvailability();
        mainMenu.clickNewNoteButton();
        noteSection.createNote(note.getTitle(), note.getBody(), notebookTitle);
        noteSection.clickDoneButton();
        notesList.waitForAdditionOfNote(note.getTitle());
    }

    public void createNotes(List<Note> notes){
        for(Note note : notes){
            createNote(note, null);
        }
    }

    public boolean isNoteInNoteList(String title){
        System.out.println("***** " + new Date() + " " + title);
        accountMenu.waitForAvailability();
        System.out.println("***** " + new Date() + " account menu is available");
        mainMenu.clickNotesButton();
        System.out.println("***** " + new Date() + " notes button has been clicked");
        notesList.waitForAvailability();
        System.out.println("***** " + new Date() + " notes list is available");
        return notesList.containsNote(title);
    }

    private void confirm() {
        DriverHelper.findElement(By.id("gwt-debug-ConfirmationDialog-confirm")).click();
    }

    private void deleteNote(WebElement note){
        try {
            note.click();
        } catch (WebDriverException wde){
            System.out.println("******* ...trying to click note again...");
            // wait 2 seconds and retry
            WaitHelper.pause(2);
            note.click();
        }
        noteSection.clickDeleteNoteButton();
        confirm();
        waitForTrashMessage();
    }

    public void deleteNote(String title){
        mainMenu.clickNotesButton();
        List<WebElement> notes = notesList.getNotes(title);
        if (!notes.isEmpty()){
            deleteNote(notes.get(0));
        } else {
            throw new RuntimeException("No note with title '" + title + "' was found");
        }
    }

    public void deleteAllNotes() {
        System.out.println("********** Clearing notes.....");
        mainMenu.clickNotesButton();

        List<WebElement> notes = notesList.getNotes();

        WebElement note;
        for (int i = notes.size(); i != 0; i--) {
//            System.out.println("******** i=" + i + ", notes.size() = " + notes.size());
            note = notes.get(i - 1);
            deleteNote(note);
        }
        System.out.println("********** Cleared all notes.....");
    }

    public void waitForTrashMessage(){
        By messageLocator = By.xpath("//div[@id='gwt-debug-toastContainer']//span[text()='Trash']");
        WaitHelper.waitUntil(ExpectedConditions.visibilityOfElementLocated(messageLocator));
        WaitHelper.waitUntil(ExpectedConditions.invisibilityOfElementLocated(messageLocator));
    }

    public void addNoteToShortcuts(String title){
        List<WebElement> notes = notesList.getNotes();
        if (!notes.isEmpty()){
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

    public void createNewNotebook(String notebookTitle){
        mainMenu.clickNotebooksButton();
        notebooksList.waitForAvailability();
        notebooksList.clickCreateNotebookButton();
        WebElement notebookTitleInput = DriverHelper.findElement(By.id("gwt-debug-CreateNotebookDialog-centeredTextBox-textBox"));
        notebookTitleInput.click();
        notebookTitleInput.sendKeys(notebookTitle);
        DriverHelper.findElement(By.id("gwt-debug-CreateNotebookDialog-confirm")).click();
        waitForNotebookCreationMessage();
    }

    private void waitForNotebookCreationMessage(){
        By messageLocator = By.cssSelector(".gwt-Label");
//        WaitHelper.waitUntil(ExpectedConditions.visibilityOfElementLocated(messageLocator));
        WaitHelper.waitUntil(ExpectedConditions.invisibilityOfElementLocated(messageLocator));
    }

    public boolean isNoteInNotebook(String noteTitle, String notebookTitle){
        mainMenu.clickNotebooksButton();
        notebooksList.waitForAvailability();
        notebooksList.selectNotebook(notebookTitle);

        // When trash can is displayed, the notes list is displayed with only those notes in the trash
        // However, at times, the notes list is displayed with the full list of notes, and is then 'refreshed'
        // with only the tagged notes. Hence the requirement for a pause.
        WaitHelper.pause(2);

        return notesList.containsNote(noteTitle);

    }

    public void deleteNotebooks(){
        // delete all notebooks except the default notebook
        System.out.println("********** Clearing notebooks.....");
        mainMenu.clickNotebooksButton();

        List<WebElement> notebooks = notebooksList.getNotebooks();

        WebElement notebook;
        for (int i = notebooks.size(); i != 0; i--) {
//            System.out.println("******** i=" + i + ", notes.size() = " + notes.size());
            notebook = notebooks.get(i - 1);

            String title = notebooksList.getNotebookTitle(notebook);
//            System.out.println("**** Found notebook with title " + title);
            if (! title.equals(PropertyUtils.getProperty("evernote.notebook.default"))) {
                deleteNotebook(notebook);
            }
        }
        System.out.println("********** Cleared notebooks.....");
    }

    private void deleteNotebook(WebElement notebook){
        notebooksList.clickNotebookDeleteButton(notebook);
        confirm();
        waitForNotebookDeletedMessage();
    }

    private void waitForNotebookDeletedMessage(){
        By messageLocator = By.xpath("//div[@id='gwt-debug-toastContainer']//span[text()='Notebook deleted.']");
        WaitHelper.waitUntil(ExpectedConditions.visibilityOfElementLocated(messageLocator));
        WaitHelper.waitUntil(ExpectedConditions.invisibilityOfElementLocated(messageLocator));
    }

    private void goToTrashCan(){
        mainMenu.clickNotebooksButton();
        notebooksList.goToTrashCan();
    }

    public void restoreNoteFromTrashCan(String title){
        goToTrashCan();
        List<WebElement> notes = notesList.getNotes(title);
        if (!notes.isEmpty()){
            notesList.restoreNoteFromTrashCan(notes.get(0));
        }
//        WaitHelper.pause(5);
//        notesList.waitForRemovalOfNote(title);
    }

    public boolean trashCanContainsNotes(List<String> titles){
        goToTrashCan();

        // When trash can is displayed, the notes list is displayed with only those notes in the trash
        // However, at times, the notes list is displayed with the full list of notes, and is then 'refreshed'
        // with only the tagged notes. Hence the requirement for a pause.
        WaitHelper.pause(2);

        return notesList.containsAllNotes(titles);
    }

    public void emptyTrashCan(){
        if (! notesList.isTrashCanDisplayed()){
            goToTrashCan();
        }
        notesList.clickEmptyTrashButton();
        confirm();

        notesList.waitForNoteCountToChange(0);
    }

    public boolean isTrashCanEmpty(){
        if (! notesList.isTrashCanDisplayed()){
            goToTrashCan();
        }
        return notesList.getNotes().isEmpty();
    }

    public void addTagToNotes(String tag, List<String> titles){
        mainMenu.clickNotesButton();

        for (String title : titles) {
            notesList.clickNote(title);
            noteSection.addTag(tag);
            tagsList.waitForAdditionOfTag(tag);
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
        return notesList.containsAllNotes(titles);
    }

    public void deleteAllTags(){
        mainMenu.clickTagsButton();
        tagsList.deleteAllTags();
    }

    public void logOut(){
        boolean loggedOut = false;
        do {
//            System.out.println("**** Attempting logout...");
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
//        System.out.println("**** Logged out!");
    }
}
