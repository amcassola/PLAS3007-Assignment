package edu.plas.testautoandci.ampc.pageobjectmodels;

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

    public String getAccountUsername() {
        WebElement accountName = DriverHelper.findElement(By.id("gwt-debug-AccountMenu-name"));
        return accountName.getText();
    }

    public void waitForAvailability() {
        WaitHelper.waitUntilAttributeValueMatches(By.cssSelector(".GOSDSN-CJP"), "src", "^.*/shard/.*/user/.*$", WaitHelper.EXPLICIT_WAIT_TIMEOUT);
    }

    public void clickLogOut() {
        WebElement logOutMenuItem = DriverHelper.findElement(By.id("gwt-debug-AccountMenu-logout"));
        logOutMenuItem.click();
    }

    public boolean isDisplayed() {
        return DriverHelper.findElement(By.id("gwt-debug-AccountMenuPopup-root")).isDisplayed();
    }

}
