package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

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
//        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
//        type(By.name("work"), contactData.getWorkPhone());
//        type(By.name("email"), contactData.getEmail());
//        type(By.name("email2"), contactData.getEmail2());
//        type(By.name("email3"), contactData.getEmail3());
        type(By.name("address"), contactData.getAddress());
//        attach(By.name("photo"), contactData.getPhoto());



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
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
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
        contactCache = null;
        contactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptDeletionContact();
        contactCache = null;
        contactPage();

    }

    private Contacts contactCache = null; //кеш списка групп


    public Contacts all() {

        if (contactCache != null){ //проверка наличия кешированного списка
            return new Contacts(contactCache); //создаем копию кеша
        }
        Contacts contactCache= new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));

        for (WebElement element : elements) {
            List<WebElement> td = element.findElements(By.tagName("td"));
            List<String> cells = new ArrayList<String>();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            for (WebElement element1 : td) {
                cells.add(element1.getText());
            }
            String firstname = cells.get(2);
            String lastname = cells.get(1);
            String allPhones = cells.get(5);
            String allEmails = cells.get(4);
            String address = cells.get(3);
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address)
                    .withAllEmails(allEmails).wihtAllPhones(allPhones));

//                    .withMobilePhone(phones[1]).withWorkPhone(phones[2]));

//            String[] phones = cells.get(5).split("\n"); //формируем массив из номеров телефона считанных с главной
//             страницы(разбиваем строку на фрагменты)
//            contactCache.add( new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withHomePhone(phones[0])
//                    .withMobilePhone(phones[1]).withWorkPhone(phones[2]));
        }
        return new Contacts(contactCache);
    }


    public int Count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData editFormPhone(ContactData contact) {
       selectContactById(contact.getId());
       String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
       String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
       String home = wd.findElement(By.name("home")).getAttribute("value");
       String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
       String work = wd.findElement(By.name("work")).getAttribute("value");
       wd.navigate().back();
       return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHomePhone(home)
               .withMobilePhone(mobile).withWorkPhone(work);


    }

    public ContactData editFormEmail(ContactData contact) {
        selectContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withEmail(email).withEmail2(email2).withEmail3(email3);


    }

    public ContactData editFormAddress(ContactData contact) {
        selectContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withAddress(address);
    }
}
