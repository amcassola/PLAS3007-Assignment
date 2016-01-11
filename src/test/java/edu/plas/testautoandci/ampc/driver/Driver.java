package edu.plas.testautoandci.ampc.driver;

import edu.plas.testautoandci.ampc.utils.PropertyUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver webDriver = null;
    private static AndroidDriver androidDriver = null;
    private static String browser;
    private static final String SELENIUM_GRID_HUB_URL = PropertyUtils.getProperty("selenium.hub.url");
    private static final String CHROME_DRIVER_MAC_PATH = PropertyUtils.getProperty("browser.driver.path.mac.chrome");
    private static final String CHROME_DRIVER_WINDOWS_PATH = PropertyUtils.getProperty("browser.driver.path.windows.chrome");
    private static final String IE_DRIVER_WINDOWS_PATH = PropertyUtils.getProperty("browser.driver.path.windows.ie");
    private static final String APPIUM_HUB_URL = PropertyUtils.getProperty("appium.hub.url");

    private Driver() {
    }

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            browser = System.getProperty("browser");
            if (browser == null) {
                throw new NullPointerException("Browser option was not set. System property browser is expected to be found.");
            }
            startWebDriver();
//            throw new IllegalStateException("Selenium WebDriver is not initialised!");
        }
        return webDriver;
    }

    public static AndroidDriver getAndroidDriver() {
        if (androidDriver == null) {
            startAndroidDriver();
//            throw new IllegalStateException("Selenium WebDriver is not initialised!");
        }
        return androidDriver;
    }

    private static void startAndroidDriver() {
        // Check whether webDriver has already been initialised
        if (androidDriver != null) {
            throw new IllegalStateException("Android Driver has already been initialised!");
        }
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.android();
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
//            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "30");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
//            capabilities.setCapability(MobileCapabilityType.VERSION, "4.2.2");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
//            capabilities.setCapability(MobileCapabilityType.DEVICE_READY_TIMEOUT, "30");
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
            capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.android.contacts");
            capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, ".DialtactsContactsEntryActivity");
            androidDriver = new AndroidDriver(new URL(APPIUM_HUB_URL), capabilities);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Android Driver!", e);
        }

        // prepare web webDriver shutdown commands so that web webDriver will be terminated at the end of the test run
        prepareAndroidDriverShutDown();
    }

    private static void startWebDriver() {
        // Check whether webDriver has already been initialised
        if (webDriver != null) {
            throw new IllegalStateException("Selenium WebDriver has already been initialised!");
        }
        try {
            switch (browser) {
                case "localFirefox":
                    FirefoxProfile firefoxProfile = new FirefoxProfile();
                    firefoxProfile.setEnableNativeEvents(false);
                    webDriver = new FirefoxDriver(firefoxProfile);
                    break;

                case "localChrome":
                    if (System.getProperty("os.name").contains("Mac")) {
                        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_MAC_PATH);
                    } else if (System.getProperty("os.name").contains("Windows")) {
                        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_WINDOWS_PATH);
                    }
                    webDriver = new ChromeDriver();
                    break;

                case "localIE":
                    System.setProperty("webdriver.ie.webDriver", IE_DRIVER_WINDOWS_PATH);
                    webDriver = new InternetExplorerDriver();
                    break;

                case "gridFirefox":
                    FirefoxProfile firefoxProfileGrid = new FirefoxProfile();
                    firefoxProfileGrid.setEnableNativeEvents(false);
                    DesiredCapabilities firefoxCapability = DesiredCapabilities.firefox();
                    firefoxCapability.setBrowserName("firefox");
//                    firefoxCapability.setVersion("41.0.1");
                    firefoxCapability.setPlatform(Platform.WINDOWS);
                    firefoxCapability.setCapability(FirefoxDriver.PROFILE, firefoxProfileGrid);
                    webDriver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB_URL), firefoxCapability);
                    break;

                case "gridChrome":
                    DesiredCapabilities chromeCapability = DesiredCapabilities.chrome();
                    chromeCapability.setBrowserName("chrome");
                    chromeCapability.setPlatform(Platform.WINDOWS);
                    webDriver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB_URL), chromeCapability);
                    break;

                case "gridIE":
                    DesiredCapabilities ieCapability = DesiredCapabilities.internetExplorer();
                    ieCapability.setBrowserName("internet explorer");
                    ieCapability.setVersion("11");
                    ieCapability.setPlatform(Platform.WINDOWS);
                    webDriver = new RemoteWebDriver(new URL(SELENIUM_GRID_HUB_URL), ieCapability);
                    break;

                default:
                    throw new IllegalArgumentException("Browser system property is wrong! Cannot be " + browser);
            }

            webDriver.manage().timeouts().pageLoadTimeout(PropertyUtils.getPropertyAsInt("wait.pageload"), TimeUnit.SECONDS);
            webDriver.manage().timeouts().implicitlyWait(PropertyUtils.getPropertyAsInt("wait.implicit"), TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Selenium WebDriver!", e);
        }

        // prepare web webDriver shutdown commands so that web webDriver will be terminated at the end of the test run
        prepareWebDriverShutDown();
    }

    private static void prepareWebDriverShutDown() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutDownWebDriver();
            }
        });
    }

    private static void prepareAndroidDriverShutDown() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutDownAndroidDriver();
            }
        });
    }

    public static void shutDownWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    public static void shutDownAndroidDriver() {
        if (androidDriver != null) {
            androidDriver.quit();
            androidDriver = null;
        }
    }

    public static String getBrowser() {
        return browser;
    }

    public static void setBrowser(String browser) {
        Driver.browser = browser;
    }

}