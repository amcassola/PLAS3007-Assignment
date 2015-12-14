package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class MainMenu {

//    public void waitForAvailability() {
//        WaitHelper.disableImplicitWait();
//       new WebDriverWait(Driver.getWebDriver(), WaitHelper.EXPLICIT_WAIT_TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(By.id("gwt-debug-Sidebar-newNoteButton")));
//        WaitHelper.enableImplicitWait();
//    }

    public void clickNewNoteButton() {
        WebElement newNoteButton = Driver.getWebDriver().findElement(By.id("gwt-debug-Sidebar-newNoteButton"));
        newNoteButton.click();
    }

}
