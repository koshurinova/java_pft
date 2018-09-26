package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void goToContactPage() {
        click(By.linkText("add new"));
    }

    public void viewPageContact() {
        click(By.linkText("home"));
    }

    public void initContactCreation(String s) {
        click(By.xpath(s));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }


    }

//    protected void goToAddContact(String s) {
//        viewPageContact(s);
//    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptDeletionContact() {
        wd.switchTo().alert().accept();
    }

    public void selectContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void addContactPage() {
        click(By.linkText("add new"));
    }

    public void createContact(ContactData contact) {
        addContactPage();
        fillContactForm(contact, true);
        initContactCreation("(//input[@name='submit'])[2]");;
        viewPageContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}
