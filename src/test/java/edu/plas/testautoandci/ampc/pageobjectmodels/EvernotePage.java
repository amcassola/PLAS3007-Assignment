package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.utils.SiteUrlUtils;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public abstract class EvernotePage implements WebPage {

    protected final String SITE_NAME;

    public EvernotePage(String siteName){
        SITE_NAME = siteName;
    }

    public void navigateToPage(){
        String url = SiteUrlUtils.getSiteUrl(SITE_NAME);
        Driver.getWebDriver().get(url);
    }

}
