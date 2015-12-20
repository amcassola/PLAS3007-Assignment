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
public class ShortcutsList {

    public boolean containsNote(String title) {
        return getNotes(title).size() > 0;
    }

    private List<WebElement> getNotes(String title) {
        // check that there is at least 1 note with a non-blank title
//        List<WebElement> titleElements = Driver.getWebDriver().findElements(By.xpath("//div[@id='gwt-debug-notesListView']//div[contains(@class,'qa-title') and not(text()='')]"));
//
//        if (titleElements.size() == 0) {
//            return new ArrayList<>();
//        }

        System.out.println("********* Getting shortcuts with title '" + title + "'");
        List<WebElement> notes = getNotes();
        System.out.println("********* Found " + notes.size() + " shortcuts");

        List<WebElement> matchingNotes = new ArrayList<>();
        WebElement element;
        for (WebElement note : notes) {
            System.out.println("************ checking note title " + title);
//            System.out.println("************ number of title elements for note: " + note.findElements(By.cssSelector(".qa-title")).size());
//            Driver.getWebDriver().getPageSource();
            element = DriverHelper.findElement(note, By.cssSelector(".qa-name"));
            System.out.println("************ found note with title text " + element.getText());
            System.out.println("************ found note with title class " + element.getAttribute("class"));
//            new WebDriverWait(Driver.getWebDriver(), 5).until(ExpectedConditions.textToBePresentInElement(note, title));
            if (element.getText().equals(title)) {
                matchingNotes.add(note);
            }
        }

        return matchingNotes;
    }

    protected List<WebElement> getNotes() {
        return DriverHelper.findElements(DriverHelper.findElement(By.id("gwt-debug-DrawerView-root")), By.cssSelector(".qa-shortcutWidget"));
    }

}
