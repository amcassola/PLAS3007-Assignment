package edu.plas.testautoandci.ampc.stepdefinitions.web.evernote;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.DriverHelper;
import edu.plas.testautoandci.ampc.helper.WindowHelper;
import edu.plas.testautoandci.ampc.utils.SiteUrlUtils;

/**
 * Write something about this class here
 *
 * @author annamaria.cassola
 * @since 29/10/2015
 */

public class NavigationSteps {

    @Given("^the (.*) (?:site|page) is loaded$")
    public void siteIsLoaded(String site) {
        DriverHelper.navigateToSite(site);
    }

}
