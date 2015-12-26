package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class MainMenu {

    protected void clickNewNoteButton() {
        DriverHelper.findElement(By.id("gwt-debug-Sidebar-newNoteButton")).click();
    }

    protected void clickSearchButton() {
        DriverHelper.findElement(By.id("gwt-debug-Sidebar-searchButton")).click();
    }

    protected void clickShortcutsButton() {
        DriverHelper.findElement(By.id("gwt-debug-Sidebar-shortcutsButton")).click();
    }

    protected void clickNotesButton() {
        DriverHelper.findElement(By.id("gwt-debug-Sidebar-notesButton")).click();
    }

    protected void clickNotebooksButton() {
        DriverHelper.findElement(By.id("gwt-debug-Sidebar-notebooksButton")).click();
    }

    protected void clickTagsButton() {
        DriverHelper.findElement(By.id("gwt-debug-Sidebar-tagsButton")).click();
    }

}
