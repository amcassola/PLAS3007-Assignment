package edu.plas.testautoandci.ampc.pageobjectmodels.web;

import edu.plas.testautoandci.ampc.helper.DriverHelper;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 12/01/2016
 */
public class WebPageNavigator {

    public void navigateToSite(String site){
        DriverHelper.navigateToSite(site);
    }
}
