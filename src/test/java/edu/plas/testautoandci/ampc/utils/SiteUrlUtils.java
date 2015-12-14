package edu.plas.testautoandci.ampc.utils;

import static org.junit.Assert.assertFalse;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class SiteUrlUtils {

    /**
     * Given a site name, the site URL will be retrieved from the property configuration
     * @param site Name of site for which URL must be retrieved
     * @return URL of site
     */
    public static String getSiteUrl(String site) {
        String urlPropertyName = "url." + site.replaceAll("\\s+", ".").toLowerCase();
        String url = PropertyUtils.getProperty(urlPropertyName);

        if (url == null) {
            throw new RuntimeException("Site URL not configured for " + site + ". Expected property named " + urlPropertyName);
        }

        return url;
    }
}
