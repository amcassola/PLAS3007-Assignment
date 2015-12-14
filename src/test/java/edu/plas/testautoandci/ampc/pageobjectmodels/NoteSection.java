package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.FrameAndAlertHelper;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Date;

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
        System.out.println("***** " + new Date() + " About to input title");
        WebElement titleContainer = Driver.getWebDriver().findElement(By.id("gwt-debug-NoteTitleView-textBox"));
        titleContainer.sendKeys(title);
        System.out.println("***** " + new Date() + " Title has been input");
    }

    private void inputBody(String body) {
        System.out.println("***** " + new Date() + " switching to IFrame");
        WaitHelper.disableImplicitWait();
        FrameAndAlertHelper.switchToFrame("entinymce_170_ifr");
        WaitHelper.enableImplicitWait();
        System.out.println("***** " + new Date() + " switched to IFrame");

        System.out.println("***** " + new Date() + " About to input body");
        WebElement bodyInput = Driver.getWebDriver().findElement(By.id("tinymce"));
        Actions actions = new Actions(Driver.getWebDriver());
        actions.click(bodyInput).sendKeys(body).perform();
        System.out.println("***** " + new Date() + " Title has been input");

        FrameAndAlertHelper.switchToParentFrame();
    }

}
