package edu.plas.testautoandci.ampc.pageobjectmodels.web.evernote;

import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class AccountMenu {

    protected String getAccountUsername() {
        WebElement accountName = DriverHelper.findElement(By.id("gwt-debug-AccountMenu-name"));
        return accountName.getText();
    }

    protected void waitForAvailability() {
        WaitHelper.waitUntilAttributeValueMatches(By.cssSelector(".GOSDSN-CJP"), "src", "^.*/shard/.*/user/.*$", WaitHelper.EXPLICIT_WAIT_TIMEOUT);
    }

    protected void clickLogOut() {
        WebElement logOutMenuItem = DriverHelper.findElement(By.id("gwt-debug-AccountMenu-logout"));
        logOutMenuItem.click();
    }

    protected boolean isDisplayed() {
        return DriverHelper.findElement(By.id("gwt-debug-AccountMenuPopup-root")).isDisplayed();
    }

}
