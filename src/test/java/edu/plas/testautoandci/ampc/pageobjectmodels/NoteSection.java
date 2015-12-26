package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.FrameAndAlertHelper;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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
        createNote(title, body, null);
    }

    protected void createNote(String noteTitle, String noteBody, String notebookTitle){
        if (notebookTitle != null){
            selectNotebook(notebookTitle);
        }

        inputTitle(noteTitle);
        inputBody(noteBody);
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

    private void selectNotebook(String title){
        WebElement currentNotebook = DriverHelper.findElement(By.id("gwt-debug-NotebookSelectMenu-notebookName"));
        currentNotebook.click();

        List<WebElement> availableNotebooks = DriverHelper.findElements(DriverHelper.findElement(By.id("gwt-debug-notebookSelectMenu-slidingPanel")), By.cssSelector(".qa-name"));
        for (WebElement notebook : availableNotebooks){
            if (notebook.getText().equals(title)){
                notebook.click();
                waitForNotebookChangedMessage();
                return;
            }
        }

        throw new RuntimeException("No notebook with title " + title + " was found");
    }

    public void waitForNotebookChangedMessage(){
        By messageLocator = By.xpath("//div[@id='gwt-debug-toastContainer']//div[starts-with(text(), 'Note moved')]");
        WaitHelper.waitUntil(ExpectedConditions.visibilityOfElementLocated(messageLocator));
        WaitHelper.waitUntil(ExpectedConditions.invisibilityOfElementLocated(messageLocator));
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

    private void waitForNonEmptyBody() {
        switchToBodyFrame();
        WebElement bodyInput = getBodyInputElement();
        WaitHelper.disableImplicitWait();
        WaitHelper.waitUntilTextMatches(bodyInput, ".+", 5);
        WaitHelper.enableImplicitWait();
    }

}
