package edu.plas.testautoandci.ampc.pageobjectmodels.web.evernote;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.utils.SiteUrlUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 26/11/2015
 */
public class LoginPage extends EvernotePage {

    public LoginPage(){
        super("Evernote login");
    }

    public boolean isLoginPageDisplayed() {
        return Driver.getWebDriver().getCurrentUrl().startsWith(SiteUrlUtils.getSiteUrl("Evernote Login"));
    }

    public void signIn(String username, String password){
        populateUserName(username);
        populatePassword(password);

        clickSignInButton();
    }

    private void populateUserName(String username){
        WebElement usernameInput = Driver.getWebDriver().findElement(By.id("username"));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    private void populatePassword(String password){
        WebElement passwordInput = Driver.getWebDriver().findElement(By.id("password"));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    private void clickSignInButton(){
        WebElement signInButton = Driver.getWebDriver().findElement(By.id("login"));
        signInButton.click();
    }

    public String getLoginErrorMessage(){
        WebElement errorMsg = Driver.getWebDriver().findElement(By.cssSelector("#password-wrapper > div"));
        return errorMsg.getText();
    }
}
