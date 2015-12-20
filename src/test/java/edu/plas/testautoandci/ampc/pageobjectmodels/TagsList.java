package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

    private WebElement getTag(String tagName) {
        System.out.println("********* Getting tags with name '" + tagName + "'");
        List<WebElement> tags = getTags();
        System.out.println("********* Found " + tags.size() + " shortcuts");

        WebElement element;
        for (WebElement tag : tags) {
            System.out.println("************ checking tag " + tag);
            element = DriverHelper.findElement(tag, By.cssSelector(".qa-name"));
            System.out.println("************ found tag with name " + element.getText());
            System.out.println("************ found tag with class " + element.getAttribute("class"));
            if (element.getText().equals(tag)) {
                return element;
            }
        }

        throw new RuntimeException("No tag with name " + tagName + " is present in tag list");
    }

    protected List<WebElement> getTags() {
        return DriverHelper.findElements(DriverHelper.findElement(By.id("gwt-debug-DrawerView-root")), By.cssSelector(".qa-tagWidget"));
    }

    public void deleteAllTags() {
        System.out.println("********** Clearing tags.....");
        // find delete buttons
        List<WebElement> deleteButtons = DriverHelper.findElements(By.cssSelector(".focus-drawer-TagsDrawer-TagSelectable-delete-icon"));

        System.out.println("******** Found " + deleteButtons.size() + " delete buttons.....");

        WebElement button;
        WebElement clickableContainer;
        for (int i = deleteButtons.size(); i != 0; i--) {
            System.out.println("******** i=" + i + ", deleteButtons.size() = " + deleteButtons.size());
            button = deleteButtons.get(i - 1);
            clickableContainer = DriverHelper.findElement(DriverHelper.findElement(button, By.xpath("../../..")), By.cssSelector(".qa-clickContainer"));
            new Actions(Driver.getWebDriver()).moveToElement(clickableContainer).perform();
            button.click();

            confirmDelete();
        }
        System.out.println("********** Cleared all tags.....");
    }

    private void confirmDelete() {
        DriverHelper.findElement(By.id("gwt-debug-ConfirmationDialog-confirm")).click();
    }

}
