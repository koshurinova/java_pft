package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        ContactData contact = new ContactData().withFirstname("Иван").withLastname("Иванов").withMobile("123456").withEmail("1@test.ru").withGroup("test1");
        app.contact().contactPage();
        Contacts before=app.contact().all();
        app.contact().create(contact);
        Contacts after=app.contact().all();
        assertThat(after.size(),equalTo(before.size()+1));
        assertThat(after, equalTo (before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }

}



