package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.Note;
import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.FrameAndAlertHelper;
import edu.plas.testautoandci.ampc.utils.SiteUrlUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    public MainMenu getMainMenu() {
        return mainMenu;
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

    public ShortcutsList getShortcutList() {
        return shortcutsList;
    }

    public TagsList getTagsList() {
        return tagsList;
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

        WebElement accountMenuItem = DriverHelper.findElement(By.id("gwt-debug-AccountMenu-avatar"));
        accountMenuItem.click();
    }

    public void createNote(Note note) {
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

    private void confirmDelete() {
        DriverHelper.findElement(By.id("gwt-debug-ConfirmationDialog-confirm")).click();
    }

    public void deleteAllNotes() {
        System.out.println("********** Clearing notes.....");
        List<WebElement> notes = notesList.getNotes();

        WebElement note;
        for (int i = notes.size(); i != 0; i--) {
            System.out.println("******** i=" + i + ", notes.size() = " + notes.size());
            note = notes.get(i - 1);
            note.click();
            noteSection.clickDeleteNoteButton();
            confirmDelete();
        }
        System.out.println("********** Cleared all notes.....");
    }


    public void addNoteToShortcuts(String title){
        List<WebElement> notes = notesList.getNotes();
        if (notes.size() > 0){
            notesList.addNoteToShortcuts(notes.get(0));
        }
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

    public List<String> getNotesWithTag(String tag){
        mainMenu.clickTagsButton();
        tagsList.clickTag(tag);

        return new ArrayList<>();
    }

}
