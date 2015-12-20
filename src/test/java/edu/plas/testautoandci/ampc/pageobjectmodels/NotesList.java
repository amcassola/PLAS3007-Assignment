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

    public void waitForAdditionOfNote(String title) {
        DriverHelper.findElement(By.xpath("//*[@id='gwt-debug-notesListView']//div[text()='" + title + "' and ../div/text()='Moments ago']"));
    }

    public boolean containsNote(String title) {
        return getNotes(title, null).size() > 0;
    }

    public boolean containsNote(String title, String noteDateRegex) {
        return getNotes(title, noteDateRegex).size() > 0;
    }

    protected List<WebElement> getNotes(String title, String noteDateRegex) {
        List<WebElement> matchingNotes = new ArrayList<>();

        // check that there is at least 1 note with a non-blank title
        List<WebElement> titleElements = DriverHelper.findElements(By.xpath("//div[@id='gwt-debug-notesListView']//div[contains(@class,'qa-title') and not(text()='')]"));

        if (titleElements.size() == 0) {
            return matchingNotes;
        }

        System.out.println("********* Getting notes with title " + title + " and matching date regex " + noteDateRegex);
        List<WebElement> notes = getNotes();

        WebElement element;
        for (WebElement note : notes) {
            System.out.println("************ checking note title '" + title + "'");
//            System.out.println("===========================================================");
//            System.out.println(Driver.getWebDriver().getPageSource());
//            System.out.println("===========================================================");
            element = DriverHelper.findElement(note, By.cssSelector(".qa-title"));
            System.out.println("************ found note with title text '" + element.getText() + "'");
            System.out.println("************ found note with title class " + element.getAttribute("class"));
//            new WebDriverWait(Driver.getWebDriver(), 5).until(ExpectedConditions.textToBePresentInElement(note, title));
            if (element.getText().equals(title)) {
                if (noteDateRegex != null) {
                    System.out.println("************ checking note date " + noteDateRegex);
                    element = DriverHelper.findElement(note, By.cssSelector(".qa-date"));
                    System.out.println("************ found note with date " + element.getText());
                    System.out.println("************ found note with date class " + element.getAttribute("class"));
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

    protected void clickNote(String title){
        List<WebElement> notes = getNotes(title, null);
        if (notes.size() > 0){
            notes.get(0).click();
        }
    }

}
