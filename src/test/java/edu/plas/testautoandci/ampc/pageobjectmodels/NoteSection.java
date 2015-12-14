package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.FrameAndAlertHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class NoteSection {

//    private void waitForAvailability() {
//        new WebDriverWait(Driver.getWebDriver(), WaitHelper.EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.id("gwt-debug-NotebookSelectMenu-notebookName")));
//    }

    protected void createNote(String title, String body) {
        inputTitle(title);
        inputBody(body);
    }

    protected void clickDoneButton(){
        WebElement doneButton = Driver.getWebDriver().findElement(By.xpath("//button[@id='gwt-debug-NoteAttributes-doneButton' and text()='Done']"));
        doneButton.click();
    }

    private void inputTitle(String title) {
        WebElement titleContainer = Driver.getWebDriver().findElement(By.id("gwt-debug-NoteTitleView-textBox"));
        titleContainer.sendKeys(title);
    }

    private void inputBody(String body) {
        FrameAndAlertHelper.switchToFrame("entinymce_170_ifr");

        WebElement bodyInput = Driver.getWebDriver().findElement(By.id("tinymce"));
        Actions actions = new Actions(Driver.getWebDriver());
        actions.click(bodyInput).sendKeys(body).perform();

        FrameAndAlertHelper.switchToParentFrame();
    }

}
