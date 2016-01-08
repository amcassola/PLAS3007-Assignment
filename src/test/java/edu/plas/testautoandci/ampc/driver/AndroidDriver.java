package edu.plas.testautoandci.ampc.driver;

import edu.plas.testautoandci.ampc.utils.PropertyUtils;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 08/01/2016
 */
public class AndroidDriver {
    private static io.appium.java_client.android.AndroidDriver driver = null;

    private AndroidDriver() {
    }

    public static io.appium.java_client.android.AndroidDriver getDriver() {
        if (driver == null) {
            startDriver();
//            throw new IllegalStateException("Selenium WebDriver is not initialised!");
        }
        return driver;
    }

    private static void startDriver() {
        // Check whether driver has already been initialised
        if (driver != null) {
            throw new IllegalStateException("Android Driver has already been initialised!");
        }
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.android();
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "30");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.VERSION, "4.2.2");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung Galaxy S4");
            capabilities.setCapability(MobileCapabilityType.DEVICE_READY_TIMEOUT, "30");
            capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,"com.android.contacts");
            capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,".DialtactsContactsEntryActivity");            driver = new io.appium.java_client.android.AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Selenium WebDriver!", e);
        }

        // prepare web driver shutdown commands so that web driver will be terminated at the end of the test run
        prepareWebDriverShutDown();
    }

    private static void prepareWebDriverShutDown(){
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                shutDownWebDriver();
            }
        });
    }

    public static void shutDownWebDriver(){
        if (driver != null) {
            driver.quit();
            nullWebDriver();
        }
    }

    private static void nullWebDriver() {
        driver = null;
    }

}
