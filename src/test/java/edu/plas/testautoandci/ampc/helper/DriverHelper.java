package edu.plas.testautoandci.ampc.helper;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.utils.SiteUrlUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 07/12/2015
 */
public class DriverHelper {

    public static void navigateToSite(String site) {
        // retrieve site URL from properties file
        String url = SiteUrlUtils.getSiteUrl(site);

        if (!Driver.getWebDriver().getCurrentUrl().startsWith(url)) {
            try {
                Driver.getWebDriver().get(url);
            } catch (Exception e) {
                throw new RuntimeException("Failed to navigate to " + url, e);
            }
        }
    }

    public static WebElement findElement(By locator) {
        return findElement(null, locator);
    }

    public static WebElement findElement(WebElement element, By locator) {
        List<WebElement> elements = findElements(element, locator);

        if (elements.size() == 1) {
            return elements.get(0);
        }

        if (elements.isEmpty()) {
            throw new RuntimeException("No matching elements found using locator " + locator.toString());
        }

        throw new RuntimeException("Multiple matching elements found using locator " + locator.toString());
    }

    public static List<WebElement> findElements(By locator) {
        return findElements(null, locator);
    }

    public static List<WebElement> findElements(WebElement element, By locator) {
        if (element == null) {
            return Driver.getWebDriver().findElements(locator);
        } else {
            return element.findElements(locator);
        }
    }
}
