package edu.plas.testautoandci.ampc.pageobjectmodels.mobile.android;

import edu.plas.testautoandci.ampc.driver.Driver;
import edu.plas.testautoandci.ampc.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Write something about this class here
 *
 * @author AnnaMaria.Cassola
 * @since 09/01/2016
 */
public class ContactsApp {

    public void clickContactNameToGoToContactList(String name) {
        Driver.getAndroidDriver().findElementByXPath("//*[@text='" + name + "']").click();
    }

    public void clickContactNameInContactList(String name) {
        WebElement contactName = getContactName(name);
        if (contactName != null) contactName.click();
    }

    private List<WebElement> getContacts() {
        return Driver.getAndroidDriver().findElementsByClassName("android.view.View");
    }

    private List<WebElement> getContactNames() {
        return Driver.getAndroidDriver().findElements(By.xpath("//android.view.View/android.widget.TextView"));
    }

    public void addContact(String name, String mobileNumber, String homeNumber, String email) {
        try {
            Driver.getAndroidDriver().findElementByName("Add Contact").click();
        } catch (NoSuchElementException nsee) {
            Driver.getAndroidDriver().findElementByName("Create a new contact").click();
        }

        List<WebElement> textFieldsList = Driver.getAndroidDriver().findElementsByClassName("android.widget.EditText");
        textFieldsList.get(0).sendKeys(name);
        textFieldsList.get(1).sendKeys(mobileNumber);

        Driver.getAndroidDriver().findElementByName("Add new").click();
        textFieldsList = Driver.getAndroidDriver().findElementsByClassName("android.widget.EditText");
        textFieldsList.get(2).sendKeys(homeNumber);
        textFieldsList.get(3).sendKeys(email);

        Driver.getAndroidDriver().findElementByName("Done").click();
    }

    private WebElement getContactName(String name){
        List<WebElement> contactNames = getContactNames();
        for (WebElement contactName : contactNames) {
            if (contactName.getText().equals(name)) {
                return contactName;
            }
        }
        return null;
    }

    public boolean contactExists(String name) {
        return getContactName(name) != null;
    }

    public boolean contactHasDetails(String name, String mobileNumber, String homeNumber, String email) {
        try {
            // look for contact name. If found, name is correct.
            Driver.getAndroidDriver().findElementByName(name);
        } catch (NoSuchElementException nsee) {
            return false;
        }

        String detailCategory = "PHONE";
        String detailType = "MOBILE";
        WebElement mobileNo = Driver.getAndroidDriver().findElement(By.xpath(getContactDetailXpath(detailCategory, detailType)));

        if (!cleanNumber(mobileNo.getText()).equals(mobileNumber)) {
            return false;
        }

        detailType = "HOME";
        WebElement homeNo = Driver.getAndroidDriver().findElement(By.xpath(getContactDetailXpath(detailCategory, detailType)));

        if (!cleanNumber(homeNo.getText()).equals(homeNumber)) {
            return false;
        }

        detailCategory = "EMAIL";
        WebElement emailAddr = Driver.getAndroidDriver().findElement(By.xpath(getContactDetailXpath(detailCategory, null)));

        return emailAddr.getText().equals(email);
    }

    private String cleanNumber(String number) {
        return number.replaceAll(" ", "").replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "");
    }

    private String getContactDetailXpath(String detailCategory, String detailType) {
        // retrieve only 1st matching text view
        return "//android.widget.TextView[@text='" + detailCategory + "']/../following-sibling::android.widget.FrameLayout//" + (detailType != null ? "android.widget.LinearLayout[./android.widget.LinearLayout[./android.widget.TextView[@text='" + detailType + "']]]/" : "") + "android.widget.TextView[1]";
    }

    public void deleteAllContacts() {
        List<WebElement> contacts = getContacts();
        while (contacts.size() > 0) {
            contacts.get(0).click();
            Driver.getAndroidDriver().findElementByName("More options").click();
            Driver.getAndroidDriver().findElementByName("Delete").click();
            Driver.getAndroidDriver().findElementByName("OK").click();

            contacts = getContacts();
        }
    }

}
