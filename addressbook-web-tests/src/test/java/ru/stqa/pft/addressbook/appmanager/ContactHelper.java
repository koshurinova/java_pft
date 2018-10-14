package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void goToContactPage() {
        click(By.linkText("add new"));
    }

    public void contactPage() {
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
//        contactPage(s);
//    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" +id+ "']" )).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptDeletionContact() {
        wd.switchTo().alert().accept();
    }

    public void selectContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void addContactPage() {
        click(By.linkText("add new"));
    }

    public void create(ContactData contact) {
        addContactPage();
        fillContactForm(contact, true);
        initContactCreation("(//input[@name='submit'])[2]");;
        contactPage();
    }

    public void modifyContact(ContactData contact) {
        selectContactById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactPage();
    }
    public void delete() {
       selectContact();
       deleteSelectedContact();
       acceptDeletionContact();
       contactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptDeletionContact();
        contactPage();

    }


    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts= new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            List<WebElement> td = element.findElements(By.tagName("td"));
            List<String> stlist = new ArrayList<String>();
            for (WebElement element1 : td) {
                stlist.add(element1.getText());
            }
            String firstname = stlist.get(2);
            String lastname = stlist.get(1);
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add( new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }




}
