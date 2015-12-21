package edu.plas.testautoandci.ampc.helper;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.utils.PropertyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 07/12/2015
 */
public class WaitHelper {

    public static final int IMPLICIT_WAIT_TIMEOUT = PropertyUtils.getPropertyAsInt("wait.implicit");
    public static final int EXPLICIT_WAIT_TIMEOUT = PropertyUtils.getPropertyAsInt("wait.explicit");

    public static void disableImplicitWait(){
        Driver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public static void enableImplicitWait(){
        Driver.getWebDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    public static void waitUntilAttributeValueMatches(By locator, String attributeName, String expectedValueRegex, long timeOutInSeconds)
    {
        disableImplicitWait();
        // adapted from http://stackoverflow.com/questions/15237129/webdriverwait-for-an-element-attribute-to-change
        new WebDriverWait(Driver.getWebDriver(), timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
            private By locator;
            private String attr;
            private String expectedValueRegex;

            private ExpectedCondition<Boolean> init(By locator, String attr, String expectedValueRegex) {
                this.locator = locator;
                this.attr = attr;
                this.expectedValueRegex = expectedValueRegex;
                return this;
            }

            public Boolean apply(WebDriver driver) {
                WebElement element = driver.findElement(this.locator);
                return element.getAttribute(this.attr).matches(this.expectedValueRegex);
            }
        }.init(locator, attributeName, expectedValueRegex));

        enableImplicitWait();
    }

    public static void waitUntilTextMatches(WebElement element, String expectedTextRegex, long timeOutInSeconds)
    {
        disableImplicitWait();

        // adapted from http://stackoverflow.com/questions/15237129/webdriverwait-for-an-element-attribute-to-change
        new WebDriverWait(Driver.getWebDriver(), timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
            private WebElement element;
            private String expectedTextRegex;

            private ExpectedCondition<Boolean> init(WebElement element, String expectedTextRegex) {
                this.element = element;
                this.expectedTextRegex = expectedTextRegex;
                return this;
            }

            public Boolean apply(WebDriver driver) {
                return element.getText().matches(this.expectedTextRegex);
            }
        }.init(element, expectedTextRegex));

        enableImplicitWait();
    }

    public static void pause(long timeInSeconds){
        try{Thread.sleep(timeInSeconds*1000);} catch (InterruptedException ie){}
    }
}

