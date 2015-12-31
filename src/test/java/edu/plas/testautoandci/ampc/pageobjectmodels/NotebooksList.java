package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import edu.plas.testautoandci.ampc.utils.PropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class NotebooksList {

    protected void waitForAvailability() {
        DriverHelper.findElement(By.id("gwt-debug-NotebooksDrawer-title")).isDisplayed();
//        DriverHelper.findElement(By.id("gwt-debug-NotebooksDrawerView-root")).isDisplayed();
    }

    protected boolean isDisplayed() {
        return DriverHelper.findElement(By.id("gwt-debug-NotebooksDrawerView-root")).isDisplayed();
    }

    protected void clickCreateNotebookButton(){
        WebElement createNotebookButton = DriverHelper.findElement(By.id("gwt-debug-NotebooksDrawer-createNotebookButton"));
        try {
            createNotebookButton.click();
        } catch (WebDriverException wde) {
            System.out.println("******** ...trying to click notebook again...");
            // wait 1 seconds and retry
            WaitHelper.simplyWait(PropertyUtils.getPropertyAsInt("wait.retry"));
            createNotebookButton.click();
        }
    }

    protected List<WebElement> getNotebooks() {
        // also filter on dragdrop-draggable class since trash can also has the qa-notebookWidget class
        return DriverHelper.findElements(DriverHelper.findElement(By.id("gwt-debug-NotebooksDrawerView-root")), By.cssSelector(".qa-notebookWidget.dragdrop-draggable"));
    }

    protected String getNotebookTitle(WebElement notebook){
        return DriverHelper.findElement(notebook, By.cssSelector(".qa-name")).getText();
    }

    protected void selectNotebook(String title){
        List<WebElement> notebooks = getNotebooks();
        for (WebElement notebook : notebooks){
            if (getNotebookTitle(notebook).equals(title)){
                notebook.click();
                return;
            }
        }

        throw new RuntimeException("No notebook with title " + title + " was found");
    }

    protected void clickNotebookDeleteButton(WebElement notebook){
        WebElement deleteButton = DriverHelper.findElement(notebook, By.cssSelector(".qa-deleteButton"));
        new Actions(Driver.getWebDriver()).moveToElement(notebook).perform();
        try{
            deleteButton.click();
        } catch (WebDriverException wde) {
            System.out.println("******* ...trying to click delete again...");
            // wait 1 seconds and retry
            WaitHelper.simplyWait(PropertyUtils.getPropertyAsInt("wait.retry"));
            deleteButton.click();
        }
    }

    protected void goToTrashCan(){
        System.out.println("******* gototrash 1");
        waitForAvailability();
        System.out.println("******* gototrash 2");
        WebElement trashCanElement = DriverHelper.findElement(By.cssSelector(".qa-trash"));
        System.out.println("******* gototrash 3");
        try{
            System.out.println("******* gototrash 4");
            trashCanElement.click();
            System.out.println("******* gototrash 5");
        } catch (WebDriverException wde) {
            System.out.println("******* ...trying to click delete again...");
            // wait 1 seconds and retry
            WaitHelper.simplyWait(PropertyUtils.getPropertyAsInt("wait.retry"));
            System.out.println("******* gototrash 6");
            trashCanElement.click();
            System.out.println("******* gototrash 7");
        }
    }

}
