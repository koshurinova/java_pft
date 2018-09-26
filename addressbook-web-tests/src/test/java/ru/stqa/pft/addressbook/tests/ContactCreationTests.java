package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().viewPageContact("home");
        app.getContactHelper().createContact(new ContactData("Иван", "Иванов", "123456", "1@test.ru", "test5"));

    }


}
