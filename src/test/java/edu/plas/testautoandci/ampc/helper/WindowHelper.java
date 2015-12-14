package edu.plas.testautoandci.ampc.helper;

import edu.plas.testautoandci.ampc.driver.Driver;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;

public class WindowHelper {
    private static String mainWindowHandle;

    // Switches focus to the next available window that is not the main window
    public static void switchToWindow() {
        WebDriver driver = Driver.getWebDriver();
        mainWindowHandle = driver.getWindowHandle();

        Set<String> allWindowHandles = driver.getWindowHandles();

        Iterator<String> windowHandleIterator = allWindowHandles.iterator();

        while (windowHandleIterator.hasNext()) {
            String popupHandle = windowHandleIterator.next();
            if (!popupHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(popupHandle);
            }
        }
    }

    // Switches focus to the all available windows that are not the main window until
    // the window title is the same as the one provided. If correct window is not found
    // then focus is set once again on the main window.
    public static void swithToWindow(String title) {
        WebDriver driver = Driver.getWebDriver();
        mainWindowHandle = driver.getWindowHandle();

        Set<String> allWindowHandles = driver.getWindowHandles();

        Iterator<String> windowHandleIterator = allWindowHandles.iterator();

        while (windowHandleIterator.hasNext()) {
            String popupHandle = windowHandleIterator.next();
            if (!popupHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(popupHandle);
                if (driver.getTitle().equals(title)) {
                    return;
                }
            }
        }

        driver.switchTo().window(mainWindowHandle);
    }

    // Closes the browser window that is currently focused on. Does not stop the driver session.
    public static void closeCurrentWindow() {
        Driver.getWebDriver().close();
    }

    // Switches focus back to the main browser window
    public static void switchToMainWindow() {
        Driver.getWebDriver().switchTo().window(mainWindowHandle);
    }
}