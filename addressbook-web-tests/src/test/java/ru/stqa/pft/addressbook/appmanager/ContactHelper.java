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
        wd.findElement(By.linkText(home)).click();
    }

    public void initContactCreation(String s) {
        wd.findElement(By.xpath(s)).click();
    }

    public void fillContactForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wd.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
        wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    protected void goToAddContact(String s) {
        viewPageContact(s);
    }
}
