package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.FrameAndAlertHelper;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
import java.util.Date;
import java.util.List;

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

    protected void clickDoneButton() {
        DriverHelper.findElement(By.xpath("//button[@id='gwt-debug-NoteAttributes-doneButton' and text()='Done']")).click();
    }

    private void inputTitle(String title) {
//        System.out.println("***** " + new Date() + " About to input title");
        DriverHelper.findElement(By.id("gwt-debug-NoteTitleView-textBox")).sendKeys(title);
//        System.out.println("***** " + new Date() + " Title has been input");
    }

    private void inputBody(String body) {
//        System.out.println("***** " + new Date() + " switching to IFrame");
//        System.out.println("***** " + new Date() + " switched to IFrame");
        switchToBodyFrame();
//        System.out.println("***** " + new Date() + " About to input body");
        WebElement bodyInput = getBodyInputElement();
        bodyInput.click();
        bodyInput.sendKeys(body);
//        System.out.println("***** " + new Date() + " Title has been input");

        FrameAndAlertHelper.switchToParentFrame();
    }

    private void switchToBodyFrame() {
        WaitHelper.disableImplicitWait();
        FrameAndAlertHelper.switchToFrame("entinymce_170_ifr");
        WaitHelper.enableImplicitWait();
    }

    private WebElement getBodyInputElement() {
        return DriverHelper.findElement(By.id("tinymce"));
    }

    protected void clickDeleteNoteButton() {
        DriverHelper.findElement(By.id("gwt-debug-NoteAttributes-trashButton")).click();
    }

    protected void addTag(String tag) {
        // When a note is loaded into the note section, the body seems to be the last item populated.
        // Focus also moves to the body which is inside an iframe. Once loaded, move back to top frame.
        waitForNonEmptyBody();
        FrameAndAlertHelper.switchToTopFrame();

        WebElement tagSection = DriverHelper.findElement(By.id("gwt-debug-NoteTagsView-tagInputBox"));
        tagSection.click();
        WebElement tagInput = DriverHelper.findElement(tagSection, By.cssSelector(".qa-ResizingSuggestLozenge-input"));
        tagInput.sendKeys(tag);
        tagInput.sendKeys(Keys.RETURN);
    }

    protected void waitForAdditionOfTag(String tag) {
        // make sure focus is in main frame
        FrameAndAlertHelper.switchToTopFrame();
        DriverHelper.findElement(By.xpath("//*[@id='gwt-debug-NoteTagsView-tagInputBox-lozengeInput']//span[text()='" + tag + "']"));
    }

//    private List<WebElement> getTags() {
//        return DriverHelper.findElements(By.cssSelector(".qa-TagLozenge-name"));
//    }
//
//    private boolean containsTag(String tag) {
//        List<WebElement> tags = getTags();
//        for (WebElement tagElement : tags) {
//            if (tagElement.getText().equals(tag)) {
//                return true;
//            }
//        }
//
//        return false;
//    }

    private void waitForNonEmptyBody() {
        switchToBodyFrame();
        WebElement bodyInput = getBodyInputElement();
        WaitHelper.disableImplicitWait();
        WaitHelper.untilTextMatches(bodyInput, ".+", 5);
        WaitHelper.enableImplicitWait();
    }

}
