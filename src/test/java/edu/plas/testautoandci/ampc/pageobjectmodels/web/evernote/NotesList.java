package edu.plas.testautoandci.ampc.pageobjectmodels.web.evernote;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class NotesList {

    protected void waitForAvailability() {
        // wait until notes count is filled in
        WaitHelper.waitUntilTextMatches(getNotesCountElement(), "\\d+ note(s)?", 3);
    }

    protected boolean isDisplayed() {
        return getNoteListViewElement().isDisplayed();
    }

    private WebElement getNoteListViewElement() {
        return DriverHelper.findElement(By.id("gwt-debug-notesListView"));
    }

    private WebElement getNotesCountElement() {
        return DriverHelper.findElement(getNoteListViewElement(), By.cssSelector(".qa-notesCount"));
    }

    protected void waitForNoteCountToChange(int expectedCount) {
        WebElement noteCountElement = DriverHelper.findElement(getNoteListViewElement(), By.cssSelector(".qa-notesCount"));
        WaitHelper.waitUntilTextMatches(noteCountElement, expectedCount + " note(s)?", WaitHelper.EXPLICIT_WAIT_TIMEOUT);
    }

    protected void waitForAdditionOfNote(String title) {
//        DriverHelper.findElement(By.xpath("//*[@id='gwt-debug-notesListView']//div[text()='" + title + "' and ../div/text()='Moments ago']"));
        DriverHelper.findElements(By.xpath("//*[@id='gwt-debug-notesListView']//div[text()='" + title + "']"));
    }

    /**
     * Check that notes list contains notes with all of the given titles
     *
     * @param titles
     * @return true or false
     */
    protected boolean containsAllNotes(List<String> titles) {
        boolean allFound = true;
        for (String title : titles) {
            if (getNotes(title).isEmpty()) {
                allFound = false;
            }
        }

        return allFound;
    }

    protected boolean containsNote(String title) {
        return !getNotes(title).isEmpty();
    }

    protected List<WebElement> getNotes(String title) {
        return getNotes(title, null);
    }

    protected List<WebElement> getNotes(String title, String noteDateRegex) {
        List<WebElement> matchingNotes = new ArrayList<>();

        // check that there is at least 1 note with a non-blank title and neither with a title 'Loading...'
        List<WebElement> titleElements = DriverHelper.findElements(By.xpath("//div[@id='gwt-debug-notesListView']//div[contains(@class,'qa-title') and not(text()='' or text='Loading...')]"));

        if (titleElements.isEmpty()) {
            return matchingNotes;
        }

        List<WebElement> notes = getNotes();
        for (WebElement note : notes) {
            if (getNoteTitle(note).equals(title)) {
                if (noteDateRegex != null) {
                    if (getNoteDate(note).matches(noteDateRegex)) {
                        matchingNotes.add(note);
                    }
                } else {
                    matchingNotes.add(note);
                }
            }
        }

        return matchingNotes;
    }

    protected String getTitleOfNoteAtPosition(int position){
        return DriverHelper.findElement(By.xpath("(//div[@id='gwt-debug-notesListView']//div[contains(@class,'qa-title') and not(text()='' or text='Loading...')])[" + position + "]")).getText();
    }

    protected String getNoteTitle(WebElement note) {
        return DriverHelper.findElement(note, By.cssSelector(".qa-title")).getText();
    }

    protected String getNoteDate(WebElement note) {
        return DriverHelper.findElement(note, By.cssSelector(".qa-date")).getText();
    }

    protected void addNoteToShortcuts(WebElement note) {
        WebElement shortcutButton = DriverHelper.findElement(note, By.cssSelector(".qa-shortcutButton"));
        new Actions(Driver.getWebDriver()).moveToElement(shortcutButton).perform();
        shortcutButton.click();
    }

    protected List<WebElement> getNotes() {
        return DriverHelper.findElements(getNoteListViewElement(), By.cssSelector(".focus-NotesView-Note"));
    }

    protected void clickNote(String title) {
        List<WebElement> notes = getNotes(title);
        if (!notes.isEmpty()) {
            notes.get(0).click();
        }
    }

    private WebElement getNotesOptions() {
        return DriverHelper.findElement(By.cssSelector(".qa-notesOptions"));
    }

    protected void selectListOrdering(String order) {
        WebElement notesOptions = getNotesOptions();
        notesOptions.click();

        DriverHelper.findElement(DriverHelper.findElement(notesOptions, By.cssSelector(".Selector")), By.xpath("./div[text()='" + order + "']")).click();
    }

    protected String getSelectedOrderOption() {
        return DriverHelper.findElement(getNotesOptions(), By.cssSelector(".SelectorOption-selected")).getText();
    }


    protected void clickEmptyTrashButton() {
        DriverHelper.findElement(getTrashHeaderElement(), By.tagName("button")).click();
    }

    protected void restoreNoteFromTrashCan(WebElement note) {
        WebElement restoreButton = DriverHelper.findElement(note, By.xpath(".//button[text()='Restore']"));
        new Actions(Driver.getWebDriver()).moveToElement(note).perform();
        restoreButton.click();
    }

    protected boolean isTrashCanDisplayed() {
        try {
            WebElement trashCanElement = getTrashHeaderElement();
            return trashCanElement.isDisplayed();
        } catch (RuntimeException e) {
            return false;
        }
    }

    private WebElement getTrashHeaderElement() {
        return DriverHelper.findElement(By.id("gwt-debug-trashHeaderContainer"));
    }
}
