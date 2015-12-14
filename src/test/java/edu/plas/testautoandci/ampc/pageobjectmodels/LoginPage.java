package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
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

    public void signIn(String username, String password){
        populateUserName(username);
        populatePassword(password);

        clickSignInButton();
    }

    private void populateUserName(String username){
        WebElement usernameInput = Driver.getWebDriver().findElement(By.id("username"));
        usernameInput.sendKeys(username);
    }

    private void populatePassword(String password){
        WebElement passwordInput = Driver.getWebDriver().findElement(By.id("password"));
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
