package edu.plas.testautoandci.ampc.helper;

import edu.plas.testautoandci.ampc.driver.Driver;
import org.openqa.selenium.WebElement;

public class FrameAndAlertHelper {
    // Switches focus to a frame that matches the passed WebElement
    public static void switchToFrame(WebElement element) {
        Driver.getWebDriver().switchTo().frame(element);
    }

    // Switches focus to a frame that has the specified name or ID
    public static void switchToFrame(String frameNameOrID) {
        Driver.getWebDriver().switchTo().frame(frameNameOrID);
    }

    // Switches focus back to the main content
    public static void switchToTopFrame() {
        Driver.getWebDriver().switchTo().defaultContent();
    }

    // Switches focus on the parent frame of the current frame on focus
    public static void switchToParentFrame() {
        Driver.getWebDriver().switchTo().parentFrame();
    }


    // Returns the text of an active alert
    public static String getAlertText() {
        return Driver.getWebDriver().switchTo().alert().getText();
    }

    // Accept the active alert
    public static void acceptAlert() {
        Driver.getWebDriver().switchTo().alert().accept();
    }

    // Dismiss the active alert
    public static void dismissAlert() {
        Driver.getWebDriver().switchTo().alert().dismiss();
    }
}