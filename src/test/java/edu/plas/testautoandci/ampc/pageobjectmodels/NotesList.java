package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        DriverHelper.findElement(By.id("gwt-debug-notesListView")).isDisplayed();
    }

    protected void waitForNoteCountToChange(int expectedCount) {
        WebElement noteCountElement = DriverHelper.findElement(DriverHelper.findElement(By.id("gwt-debug-notesListView")), By.cssSelector(".qa-notesCount"));
        WaitHelper.waitUntilTextMatches(noteCountElement, expectedCount + " note(s)?", WaitHelper.EXPLICIT_WAIT_TIMEOUT);
    }

    protected void waitForAdditionOfNote(String title) {
        DriverHelper.findElement(By.xpath("//*[@id='gwt-debug-notesListView']//div[text()='" + title + "' and ../div/text()='Moments ago']"));
    }

    protected void waitForRemovalOfNote(String title) {
        WaitHelper.waitUntil(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='gwt-debug-notesListView']//div[text()='" + title + "']"))));
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
        return ! getNotes(title).isEmpty();
    }

    protected List<WebElement> getNotes(String title) {
        return getNotes(title, null);
    }

    protected List<WebElement> getNotes(String title, String noteDateRegex) {
        List<WebElement> matchingNotes = new ArrayList<>();

        // check that there is at least 1 note with a non-blank title and neither with a title 'Loading...'
        List<WebElement> titleElements = DriverHelper.findElements(By.xpath("//div[@id='gwt-debug-notesListView']//div[contains(@class,'qa-title') and not(text()='' or text='Loading...')]"));

        if (titleElements.isEmpty()) {
//            System.out.print("******** Note title elements is empty - no notes found!");
            return matchingNotes;
        }

        List<WebElement> notes = getNotes();
//        System.out.println("********* Found " + notes.size() + " notes");
//        System.out.println("********* Getting notes with title " + title + " and matching date regex " + noteDateRegex);

        WebElement element;
        for (WebElement note : notes) {
//            System.out.println("***** note inner html: \n" + note.getAttribute("innerHTML"));
            element = DriverHelper.findElement(note, By.cssSelector(".qa-title"));
//            System.out.println("************ found note with title text '" + element.getText() + "'");
            if (element.getText().equals(title)) {
                if (noteDateRegex != null) {
                    element = DriverHelper.findElement(note, By.cssSelector(".qa-date"));
//                    System.out.println("************ found note with date " + element.getText());
                    if (element.getText().matches(noteDateRegex)) {
                        matchingNotes.add(note);
                    }
                } else {
                    matchingNotes.add(note);
                }
            }
        }

        return matchingNotes;
    }

    protected void addNoteToShortcuts(WebElement note) {
        WebElement shortcutButton = DriverHelper.findElement(note, By.cssSelector(".qa-shortcutButton"));
        new Actions(Driver.getWebDriver()).moveToElement(shortcutButton).perform();
        shortcutButton.click();
    }

    protected List<WebElement> getNotes() {
        return DriverHelper.findElements(DriverHelper.findElement(By.id("gwt-debug-notesListView")), By.cssSelector(".focus-NotesView-Note"));
    }

    protected void clickNote(String title) {
        List<WebElement> notes = getNotes(title);
        if (!notes.isEmpty()) {
            notes.get(0).click();
        }
    }

    protected void clickEmptyTrashButton() {
        DriverHelper.findElement(DriverHelper.findElement(By.id("gwt-debug-trashHeaderContainer")), By.tagName("button")).click();
    }

    protected void restoreNoteFromTrashCan(WebElement note) {
        WebElement restoreButton = DriverHelper.findElement(note, By.xpath(".//button[text()='Restore']"));
        new Actions(Driver.getWebDriver()).moveToElement(note).perform();
        restoreButton.click();
    }

    protected boolean isTrashCanDisplayed() {
        List<WebElement> trashCanElement = DriverHelper.findElements(By.id("gwt-debug-trashHeaderContainer"));
        if (!trashCanElement.isEmpty()) {
            // assumption that there will always be 1
            return trashCanElement.get(0).isDisplayed();
        }
        return false;
    }

}
