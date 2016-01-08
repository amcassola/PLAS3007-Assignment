package edu.plas.testautoandci.ampc.stepdefinitions.mobile.android;

import cucumber.api.java.en.Given;
import edu.plas.testautoandci.ampc.driver.AndroidDriver;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 08/01/2016
 */
public class ContactSteps {

    @Given("the Android Contact App is launched")
    public void launchApp(){
        System.out.println(AndroidDriver.getDriver().getPageSource());
    }
}
