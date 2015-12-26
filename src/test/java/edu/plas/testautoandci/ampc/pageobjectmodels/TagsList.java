package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.FrameAndAlertHelper;
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
public class TagsList {

    protected void clickTag(String tagName){
        getTag(tagName).click();
    }

    protected void waitForAdditionOfTag(String tag) {
        // make sure focus is in main frame
        FrameAndAlertHelper.switchToTopFrame();
        DriverHelper.findElement(By.xpath("//*[@id='gwt-debug-NoteTagsView-tagInputBox-lozengeInput']//span[text()='" + tag + "']"));
    }

    private WebElement getTag(String tagName) {
//        System.out.println("********* Getting tags with name '" + tagName + "'");
        List<WebElement> tags = getTags();
//        System.out.println("********* Found " + tags.size() + " tags");

        WebElement element;
        for (WebElement tag : tags) {
            element = DriverHelper.findElement(tag, By.cssSelector(".qa-name"));
//            System.out.println("************ found tag with name " + element.getText());
            if (element.getText().equals(tagName)) {
//                System.out.println("************ Tag name matches! " + element.getText());
                return element;
            }
        }

        throw new RuntimeException("No tag with name " + tagName + " is present in tag list");
    }

    protected List<WebElement> getTags() {
        return DriverHelper.findElements(DriverHelper.findElement(By.id("gwt-debug-DrawerView-root")), By.cssSelector(".qa-tagWidget"));
    }

    protected void deleteAllTags() {
        System.out.println("********** Clearing tags.....");
        // find delete buttons
        List<WebElement> deleteButtons = DriverHelper.findElements(By.cssSelector(".focus-drawer-TagsDrawer-TagSelectable-delete-icon"));

//        System.out.println("******** Found " + deleteButtons.size() + " delete buttons.....");

        WebElement button;
        WebElement clickableContainer;
        for (int i = deleteButtons.size(); i != 0; i--) {
//            System.out.println("******** i=" + i + ", deleteButtons.size() = " + deleteButtons.size());
            button = deleteButtons.get(i - 1);
            clickableContainer = DriverHelper.findElement(DriverHelper.findElement(button, By.xpath("../../..")), By.cssSelector(".qa-clickContainer"));
            new Actions(Driver.getWebDriver()).moveToElement(clickableContainer).perform();

            WaitHelper.disableImplicitWait();
            new WebDriverWait(Driver.getWebDriver(), WaitHelper.EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.visibilityOf(button));
            WaitHelper.enableImplicitWait();
            button.click();

//            System.out.println("******** Clicked delete (" + i + ")");
            confirmDelete();
//            System.out.println("******** Confirmed delete (" + i + ")");
        }
        System.out.println("********** Cleared all tags.....");
    }

    private void confirmDelete() {
        DriverHelper.findElement(By.id("gwt-debug-ConfirmationDialog-confirm")).click();
    }

    protected boolean isDisplayed() {
        return DriverHelper.findElement(By.cssSelector("#gwt-debug-DrawerView-root .qa-tags-drawer")).isDisplayed();
    }

}
