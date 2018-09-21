package ru.stqa.pft.addressbook.appmanager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.linkText("add new"));
    }

    public void viewPageContact(String home) {
        click(By.linkText("home"));
    }

    public void initContactCreation(String s) {
        click(By.xpath(s));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("mobile"),contactData.getMobile());
        type(By.name("email"),contactData.getEmail());
    }

    protected void goToAddContact(String s) {
        viewPageContact(s);
    }
}
