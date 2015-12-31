package edu.plas.testautoandci.ampc.helper;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.pageobjectmodels.HomePage;
import edu.plas.testautoandci.ampc.pageobjectmodels.LoginPage;
import edu.plas.testautoandci.ampc.utils.PropertyUtils;
import edu.plas.testautoandci.ampc.utils.SiteUrlUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HouseKeeper {
    private static boolean imagesCleaned = false;

    // This ensures that this @Before is always executed first
    @Before(order = 0)
    public void setup() {
        // Delete all screen shots from previous execution
        // THIS SHOULD BE EXECUTED ONLY ONCE
        if (!imagesCleaned) {
            File reportsDirectory = new File("reports/html-reports");
            final File[] files = reportsDirectory.listFiles((dir, name) -> {
                return name.matches(".*.jpeg");
            });
            for (final File file : files) {
                file.delete();
            }
            imagesCleaned = true;
        }
    }

    @After(value = "@requiresLogout", order = 20)
    public void logout() {
        HomePage homePage = new HomePage();
        if (homePage.isHomePageDisplayed()) {
            homePage.logOut();
        }
    }

    @After(value = "@notes", order = 20)
    public void logIn() {
        HomePage homePage = new HomePage();
        if (!homePage.isHomePageDisplayed()) {
            homePage.navigateToPage();
            LoginPage loginPage = new LoginPage();
            if (loginPage.isLoginPageDisplayed()) {
                loginPage.signIn(PropertyUtils.getLoginUserName(), PropertyUtils.getLoginPassword());
            }
        }
    }

    @After(value = "@sorting", order = 10)
    public void resetSortingOption() {
        new HomePage().sortNotesList("Date Updated (newest first)");
    }

    @After(value = "@notebooks", order = 10)
    public void clearNotebooks() {
        new HomePage().deleteNotebooks();
    }

    @After(value = "@notes", order = 10)
    public void clearNotes() {
        new HomePage().deleteAllNotes();
    }

    // This ensures that this @After is always executed last
    @After(order = 100)
    public void tearDown(Scenario scenario) {
        // If Cucumber scenario fails, output time of failure and take screen shot
        if (scenario.isFailed()) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            scenario.write("Time of failure: " + dateFormat.format(Calendar.getInstance().getTime()));
            ScreenShotHelper.takeScreenShot(scenario);
        }
    }
}