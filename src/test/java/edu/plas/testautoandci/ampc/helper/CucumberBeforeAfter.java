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

public class CucumberBeforeAfter {
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

        Driver.setBrowser(System.getProperty("browser"));
        Driver.startWebDriver();
    }

    @After(value="@notes", order = 10)
    public void clearNotes(){
        if (Boolean.valueOf(PropertyUtils.getProperty("after.notes.deletenotes"))) {
            HomePage homePage = new HomePage();
            if (!Driver.getWebDriver().getCurrentUrl().startsWith(SiteUrlUtils.getSiteUrl("Evernote Home"))) {
                homePage.navigateToPage();
                if (Driver.getWebDriver().getCurrentUrl().startsWith(SiteUrlUtils.getSiteUrl("Evernote Login"))) {
                    LoginPage loginPage = new LoginPage();
                    loginPage.navigateToPage();
                    loginPage.signIn(PropertyUtils.getLoginUserName(), PropertyUtils.getLoginPassword());
                }
            }

            homePage.getNotesList().clear();
//        homePage.displayAccountMenu();
//        homePage.getAccountMenu().clickLogOut();
        }
    }

    // This ensures that this @After is always executed last
    @After(order = 0)
    public void tearDown(Scenario scenario) {
        // If Cucumber scenario fails, output time of failure and take screen shot
        if (scenario.isFailed()) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            scenario.write("Time of failure: " + dateFormat.format(Calendar.getInstance().getTime()));
            ScreenShotHelper.takeScreenShot(scenario);
        }

        if (Driver.getWebDriver() != null) {
            Driver.getWebDriver().quit();
            Driver.nullWebDriver();
        }
    }
}