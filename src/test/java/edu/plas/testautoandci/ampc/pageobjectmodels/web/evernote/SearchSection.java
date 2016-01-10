package edu.plas.testautoandci.ampc.pageobjectmodels.web.evernote;

import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import edu.plas.testautoandci.ampc.utils.PropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class SearchSection {

    protected void waitForAvailability() {
        isDisplayed();
    }

    protected boolean isDisplayed() {
        return getSearchBox().isDisplayed();
    }

    private WebElement getSearchBox(){
        return DriverHelper.findElement(By.id("gwt-debug-searchViewSearchBox"));
    }

    protected void search(String text){
        WebElement searchBox = getSearchBox();

        try {
            searchBox.click();
        } catch (WebDriverException wde) {
            System.out.println("******* ...trying to click search box again...");
            // wait 1 second and retry
            WaitHelper.simplyWait(PropertyUtils.getPropertyAsInt("wait.retry"));
            searchBox.click();
        }

        searchBox.clear();
        searchBox.sendKeys(text);
        searchBox.sendKeys(Keys.RETURN);
    }
}
