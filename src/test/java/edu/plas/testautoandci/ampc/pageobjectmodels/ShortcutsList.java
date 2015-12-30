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

    protected boolean containsNote(String title) {
        return getNotes(title).size() > 0;
    }

    private List<WebElement> getNotes(String title) {
        List<WebElement> notes = getNotes();

        List<WebElement> matchingNotes = new ArrayList<>();
        WebElement element;
        for (WebElement note : notes) {
            element = DriverHelper.findElement(note, By.cssSelector(".qa-name"));
            if (element.getText().equals(title)) {
                matchingNotes.add(note);
            }
        }

        return matchingNotes;
    }

    protected List<WebElement> getNotes() {
        return DriverHelper.findElements(DriverHelper.findElement(By.id("gwt-debug-ShortcutsDrawerView-root")), By.cssSelector(".qa-shortcutWidget"));
    }

}
