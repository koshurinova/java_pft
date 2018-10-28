package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions (){

        if (app.db().contacts().size()==0){
            Groups groups =app.db().groups();
            app.contact().contactPage();
            app.contact().create(new ContactData().
                    withFirstname("Иван").withLastname("Иванов").withMobilePhone("123456").withEmail("1@test.ru")
                    .inGroup(groups.iterator().next()));
        }
    }


    @Test
    public void testContactModification(){

       Contacts before=app.db().contacts();
        ContactData modifiedContact=before.iterator().next();
        ContactData contact =new ContactData().withId(modifiedContact.getId()).withFirstname("Иван").withLastname("Иванов").withMobilePhone("123456").withEmail("1@test.ru");
        app.contact().contactPage();
        app.contact().modifyContact(contact);
        assertThat(app.contact().Count(),equalTo(before.size()));
        Contacts after=app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));


    }


}
