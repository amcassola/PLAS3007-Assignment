package edu.plas.testautoandci.ampc.pageobjectmodels.web.evernote;

import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import edu.plas.testautoandci.ampc.utils.PropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class MainMenu {

    protected void clickNewNoteButton() {
        clickMenuItem(DriverHelper.findElement(By.id("gwt-debug-Sidebar-newNoteButton")));
    }

    protected void clickSearchButton() {
        clickMenuItem(DriverHelper.findElement(By.id("gwt-debug-Sidebar-searchButton")));
    }

    protected void clickShortcutsButton() {
        clickMenuItem(DriverHelper.findElement(By.id("gwt-debug-Sidebar-shortcutsButton")));
    }

    protected void clickNotesButton() {
        clickMenuItem(DriverHelper.findElement(By.id("gwt-debug-Sidebar-notesButton")));
    }

    protected void clickNotebooksButton() {
        clickMenuItem(DriverHelper.findElement(By.id("gwt-debug-Sidebar-notebooksButton")));
    }

    protected void clickTagsButton() {
        clickMenuItem(DriverHelper.findElement(By.id("gwt-debug-Sidebar-tagsButton")));
    }

    private void clickMenuItem(WebElement menuButton){
        try {
            menuButton.click();
        } catch (WebDriverException wde) {
            System.out.println("******* ...trying to click menu item again...");
            // wait 1 second and retry
            WaitHelper.simplyWait(PropertyUtils.getPropertyAsInt("wait.retry"));
            menuButton.click();
        }
    }
}
