package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
//    private boolean acceptNextAlert = true;
//    private StringBuffer verificationErrors = new StringBuffer();



    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().viewPageContact("home");
        app.getContactHelper().addContactPage();
        app.getContactHelper().fillContactForm(new ContactData("Иван", "Иванов", "123456", "1@test.ru", "test1"), true);
        app.getContactHelper().initContactCreation("(//input[@name='submit'])[2]");
        app.getContactHelper().viewPageContact("home");
    }


}
