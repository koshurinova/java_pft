package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
    @Test
    public void testContactModification(){
        app.getContactHelper().viewPageContact("home");
        if (! app.getContactHelper().isThereAGroup()){
            app.getContactHelper().createContact(new ContactData("Иван", "Иванов", "123456", "1@test.ru", "test5"));
        }
        app.getContactHelper().selectContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Иван", "Иванов", "123456", "1@test.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().viewPageContact("home");
    }
}
