package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class AccountMenu{

    public String getAccountUsername() {
        WebElement accountName = Driver.getWebDriver().findElement(By.id("gwt-debug-AccountMenu-name"));
        return accountName.getText();
    }

    public void waitForAvailability() {
        WaitHelper.disableImplicitWait();
        WaitHelper.untilAttributeValueMatches(By.cssSelector(".GNTMVRYJP"), "src", "^.*/shard/.*/user/.*$", WaitHelper.EXPLICIT_WAIT_TIMEOUT);
        WaitHelper.enableImplicitWait();
    }

    public void clickLogOut() {
        if (this.isDisplayed()){
            WebElement logOutMenuItem = Driver.getWebDriver().findElement(By.id("gwt-debug-AccountMenu-logout"));
            logOutMenuItem.click();
        } else {
            throw new RuntimeException("Account Menu is not visible. Cannot log out. Account Menu must be made visible in order to log out.");
        }
    }

    public boolean isDisplayed() {
        List<WebElement> accountMenu = Driver.getWebDriver().findElements(By.id("gwt-debug-AccountMenuPopup-root"));
        return accountMenu.size() != 0 && accountMenu.get(0).isDisplayed();
    }

}
