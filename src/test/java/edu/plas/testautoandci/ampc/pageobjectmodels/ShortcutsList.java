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
//        System.out.println("********* Getting shortcuts with title '" + title + "'");
        List<WebElement> notes = getNotes();
//        System.out.println("********* Found " + notes.size() + " shortcuts");

        List<WebElement> matchingNotes = new ArrayList<>();
        WebElement element;
        for (WebElement note : notes) {
//            System.out.println("************ checking note title " + title);
            element = DriverHelper.findElement(note, By.cssSelector(".qa-name"));
//            System.out.println("************ found note with title text " + element.getText());
//            System.out.println("************ found note with title class " + element.getAttribute("class"));
            if (element.getText().equals(title)) {
                matchingNotes.add(note);
            }
        }

        return matchingNotes;
    }

    protected List<WebElement> getNotes() {
        return DriverHelper.findElements(DriverHelper.findElement(By.id("gwt-debug-ShortcutsDrawerView-root")), By.cssSelector(".qa-shortcutWidget"));
    }

    protected void deleteAllShortcuts() {
        System.out.println("********** Clearing shortcuts.....");
        // find delete buttons
        List<WebElement> deleteButtons = DriverHelper.findElements(DriverHelper.findElement(By.id("gwt-debug-ShortcutsDrawerView-root")), By.cssSelector(".GOSDSN-CGWB"));

//        System.out.println("******** Found " + deleteButtons.size() + " delete buttons.....");
//        System.out.println("******** shortcuts bar is displayed: " + DriverHelper.findElement(By.id("gwt-debug-ShortcutsDrawerView-root")).isDisplayed());

//        WebElement button;
        WebElement clickableContainer;
        for (WebElement button : deleteButtons){
            clickableContainer = DriverHelper.findElement(button, By.xpath("../.."));
//            System.out.println("******** shortcuts is displayed: " + clickableContainer.isDisplayed());
            new Actions(Driver.getWebDriver()).moveToElement(clickableContainer).perform();

//            boolean displayed = button.isDisplayed();
//            System.out.println("*** delete shortcut button is displayed: " + displayed);

            if (button.isDisplayed()){
//                System.out.println("*** About to click delete shortcut button");
                button.click();
//                System.out.println("*** About to click delete shortcut button");
            }
        }

//        for (int i = deleteButtons.size(); i != 0; i--) {
//            System.out.println("******** i=" + i + ", deleteButtons.size() = " + deleteButtons.size());
//            button = deleteButtons.get(i - 1);
//            clickableContainer = DriverHelper.findElement(DriverHelper.findElement(button, By.xpath("../../..")), By.cssSelector(".qa-name"));
//            System.out.println("********** shortcut class: " + clickableContainer.getAttribute("class"));
//            new Actions(Driver.getWebDriver()).moveToElement(clickableContainer).perform();
//
//            System.out.println("...... WAITING ...........");
//            try{Thread.sleep(5000);} catch (Exception e) {}
//            System.out.println("...... No longer waiting ...........");
//
//            WaitHelper.disableImplicitWait();
//            new WebDriverWait(Driver.getWebDriver(), WaitHelper.EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.visibilityOf(button));
//            WaitHelper.enableImplicitWait();
//            button.click();
//        }
        System.out.println("********** Cleared all shortcuts.....");
    }

    protected boolean isDisplayed() {
        return DriverHelper.findElement(By.id("gwt-debug-ShortcutsDrawerView-root")).isDisplayed();
    }
}
