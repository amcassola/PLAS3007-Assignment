package edu.plas.testautoandci.ampc.helper;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.utils.SiteUrlUtils;

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

        try {
            Driver.getWebDriver().get(url);
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to " + url, e);
        }
    }
}
