package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{
    @Test
    public void testContactDeletion(){
        app.getContactHelper().viewPageContact();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Иван", "Иванов", "123456", "1@test.ru", "test5"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptDeletionContact();
        app.getContactHelper().viewPageContact();

    }
}
