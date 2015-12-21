package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.FrameAndAlertHelper;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class NoteSection {

    protected void waitForAvailability() {
        DriverHelper.findElement(By.xpath("//div[@id='gwt-debug-NotebookSelectMenu-notebookName' and not(text()='')]"));
    }

    protected void createNote(String title, String body) {
        inputTitle(title);
        inputBody(body);
    }

    protected void clickDoneButton() {
        DriverHelper.findElement(By.xpath("//button[@id='gwt-debug-NoteAttributes-doneButton' and text()='Done']")).click();
    }

    private void inputTitle(String title) {
        DriverHelper.findElement(By.id("gwt-debug-NoteTitleView-textBox")).sendKeys(title);
    }

    private void inputBody(String body) {
        switchToBodyFrame();

        WebElement bodyInput = getBodyInputElement();
        bodyInput.click();
        bodyInput.sendKeys(body);

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

    private void waitForNonEmptyBody() {
        switchToBodyFrame();
        WebElement bodyInput = getBodyInputElement();
        WaitHelper.waitUntilTextMatches(bodyInput, ".+", 5);
    }

}
