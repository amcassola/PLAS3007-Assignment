package edu.plas.testautoandci.ampc.pageobjectmodels;

import edu.plas.testautoandci.ampc.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 06/12/2015
 */
public class IndexPage extends EvernotePage {

    public IndexPage(){
        super("Evernote index");
    }

    public void signUp(String username, String password){
        populateUserName(username);
        populatePassword(password);
        clickSignUpButton();
    }


    private void populateUserName(String username){
        WebElement usernameInput = Driver.getWebDriver().findElement(By.id("email"));
        usernameInput.sendKeys(username);
    }

    private void populatePassword(String password){
        WebElement passwordInput = Driver.getWebDriver().findElement(By.id("password-login"));
        passwordInput.sendKeys(password);
    }

    private void clickSignUpButton(){
        WebElement signInButton = Driver.getWebDriver().findElement(By.name("register"));
        signInButton.click();
    }
}
