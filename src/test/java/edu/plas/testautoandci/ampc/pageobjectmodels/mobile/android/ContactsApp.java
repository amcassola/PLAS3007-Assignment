package edu.plas.testautoandci.ampc.pageobjectmodels.mobile.android;

import edu.plas.testautoandci.ampc.driver.Driver;
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

    private WebElement getElementByText(String text) {
        return Driver.getAndroidDriver().findElementByXPath("//*[@text='" + text + "']");
    }

    public void clickContactNameToGoToContactList(String name) {
        getElementByText(name).click();
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

    public void click(String elementName) {
        try {
            Driver.getAndroidDriver().findElementByName(elementName).click();
        } catch (NoSuchElementException nsee) {
            Driver.getAndroidDriver().findElement(By.xpath("//*[@content-desc='" + elementName + "']")).click();
        }
    }

    public void addContact(String name, String mobileNumber, String homeNumber, String email) {
        try {
            click("All contacts");
            click("Add Contact");
        } catch (NoSuchElementException nsee) {
            click("Create a new contact");
        }

        List<WebElement> textFieldsList = Driver.getAndroidDriver().findElementsByClassName("android.widget.EditText");
        textFieldsList.get(0).sendKeys(name);
        textFieldsList.get(1).sendKeys(mobileNumber);

        Driver.getAndroidDriver().findElementByName("Add new").click();
        textFieldsList = Driver.getAndroidDriver().findElementsByClassName("android.widget.EditText");
        textFieldsList.get(2).sendKeys(homeNumber);
        textFieldsList.get(3).sendKeys(email);

        click("Done");
    }

    private WebElement getContactName(String name) {
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
        WebElement mobileNo = Driver.getAndroidDriver().findElement(By.xpath(getContactDetailXpathForView(detailCategory, detailType)));

        if (!cleanNumber(mobileNo.getText()).equals(mobileNumber)) {
            return false;
        }

        detailType = "HOME";
        WebElement homeNo = Driver.getAndroidDriver().findElement(By.xpath(getContactDetailXpathForView(detailCategory, detailType)));

        if (!cleanNumber(homeNo.getText()).equals(homeNumber)) {
            return false;
        }

        detailCategory = "EMAIL";
        WebElement emailAddr = Driver.getAndroidDriver().findElement(By.xpath(getContactDetailXpathForView(detailCategory, null)));

        return emailAddr.getText().equals(email);
    }

    private String cleanNumber(String number) {
        return number.replaceAll(" ", "").replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "");
    }

    private String getContactDetailXpathForView(String detailCategory, String detailType) {
        // retrieve only 1st matching text view
        return "//android.widget.TextView[@text='" + detailCategory + "']/../following-sibling::android.widget.FrameLayout//" + (detailType != null ? "android.widget.LinearLayout[./android.widget.LinearLayout[./android.widget.TextView[@text='" + detailType + "']]]/" : "") + "android.widget.TextView[1]";
    }

    private String getContactDetailXpathForEdit(String detailCategory, String detailType) {
        // retrieve only 1st matching text view
        return "//android.widget.TextView[@text='" + detailCategory + "']/../following-sibling::android.widget.LinearLayout//" + (detailType != null ? "android.widget.LinearLayout[./android.widget.Spinner[./android.widget.TextView[@text='" + detailType + "']]]//" : "") + "android.widget.EditText[1]";
    }

    public void editContact(String newName, String newMobileNumber) {
        click("More options");
        Driver.getAndroidDriver().findElementByName("Edit").click();

        WebElement name = Driver.getAndroidDriver().findElement(By.xpath("//android.widget.EditText[1]"));
        name.clear();
        name.sendKeys(newName);

        String detailCategory = "Phone";
        String detailType = "Mobile";
        WebElement mobileNumber = Driver.getAndroidDriver().findElement(By.xpath(getContactDetailXpathForEdit(detailCategory, detailType)));
        mobileNumber.clear();
        mobileNumber.sendKeys(newMobileNumber);

        click("Done");
    }

    public boolean isTextDisplayed(String text) {
        return getElementByText(text) != null;
    }

    public boolean isContactFavorite(String name) {
        return getElementByText(name) != null;
    }

    public void deleteAllContacts() {
        click("All contacts");
        List<WebElement> contacts = getContacts();
        while (contacts.size() > 0) {
            contacts.get(0).click();
            click("More options");
            click("Delete");
            click("OK");

            contacts = getContacts();
        }
    }

}
