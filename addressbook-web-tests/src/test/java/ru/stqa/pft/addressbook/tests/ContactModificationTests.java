package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions (){
        app.contact().contactPage();
        if (app.contact().all().size()==0){
            app.contact().create(new ContactData().
                    withFirstname("Иван").withLastname("Иванов").withMobile("123456").withEmail("1@test.ru")
                    .withGroup("test1"));
        }
    }


    @Test
    public void testContactModification(){

       Contacts before=app.contact().all();
        ContactData modifiedContact=before.iterator().next();
        ContactData contact =new ContactData().withId(modifiedContact.getId()).withFirstname("Иван").withLastname("Иванов").withMobile("123456").withEmail("1@test.ru");
        app.contact().modifyContact(contact);
        Contacts after=app.contact().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));


    }


}
