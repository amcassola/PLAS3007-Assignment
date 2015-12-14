package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
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

//    private void waitForAvailability() {
//        new WebDriverWait(Driver.getWebDriver(), 30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".focus-NotesView-Subheader-NotesOverview")));
//    }

    public void clear() {
        System.out.println("********** Clearing notes.....");
        List<WebElement> notes = getNotes();
        for (WebElement note : notes) {
//            WaitHelper.disableImplicitWait();
//            if (note.isDisplayed()) {
//            Boolean notStale = new WebDriverWait(Driver.getWebDriver(), WaitHelper.EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.not(ExpectedConditions.stalenessOf(note)));
//            WebElement visibleElement = new WebDriverWait(Driver.getWebDriver(), WaitHelper.EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.visibilityOf(note));
//            WaitHelper.enableImplicitWait();
//            if (visibleElement != null){
            deleteNote(note);
            waitForRemovalOfNote(note);
//            }
        }
//        WebElement note;
//        while (notes.size() > 0){
//            note = notes.get(0);
//            deleteNote(note);
//            notes = getNotes();
//        }
    }

    protected void waitForRemovalOfNote(WebElement note) {
        WaitHelper.disableImplicitWait();
        new WebDriverWait(Driver.getWebDriver(), WaitHelper.EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.stalenessOf(note));
        WaitHelper.enableImplicitWait();
    }

    public void waitForAdditionOfNote() {
        WebElement noteList = Driver.getWebDriver().findElement(By.cssSelector(".NotesView-ScrollWindow"));
        WaitHelper.disableImplicitWait();
        new WebDriverWait(Driver.getWebDriver(), WaitHelper.EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.textToBePresentInElement(noteList, "MOMENTS AGO"));
        WaitHelper.enableImplicitWait();
    }

    public boolean containsNote(String title) {
        return getNotes(title, null).size() > 0;
    }

    public boolean containsNote(String title, String noteDateRegex) {
        return getNotes(title, noteDateRegex).size() > 0;
    }

    private List<WebElement> getNotes(String title, String noteDateRegex) {
        System.out.println("********* Getting notes with title " + title + " and matching date regex " + noteDateRegex);
        List<WebElement> notes = getNotes();

        List<WebElement> matchingNotes = new ArrayList<>();
        WebElement element;
        for (WebElement note : notes) {
            System.out.println("************ checking note title " + title);
            element = note.findElement(By.cssSelector(".focus-NotesView-Note-noteTitle"));
            System.out.println("************ found note with title " + element.getText());
            if (element.getText().equals(title)) {
                if (noteDateRegex != null) {
                    System.out.println("************ checking note date " + noteDateRegex);
                    element = note.findElement((By.cssSelector(".focus-NotesView-Note-date")));
                    System.out.println("************ found note with date " + element.getText());
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

    private void deleteNote(WebElement note) {
        WebElement deleteButton;

        deleteButton = note.findElement(By.cssSelector(".focus-NotesView-Note-delete"));
        new Actions(Driver.getWebDriver()).moveToElement(deleteButton).click().perform();

        confirmDelete();
    }

    private void confirmDelete() {
        WebElement deleteButton = Driver.getWebDriver().findElement(By.id("gwt-debug-ConfirmationDialog-confirm"));
        deleteButton.click();
    }

    protected List<WebElement> getNotes() {
//        waitForAvailability();
        return Driver.getWebDriver().findElements(By.cssSelector(".focus-NotesView-Note"));
    }

}
