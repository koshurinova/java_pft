package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions (){
        app.contact().contactPage();
        if (app.contact().all().size()==0){
            app.contact().create(new ContactData().withFirstname("Иван").withLastname("Иванов").withMobilePhone("123456").withEmail("1@test.ru").withGroup("test5"));
        }
    }

    @Test
    public void testContactDeletion(){
        Contacts before=app.contact().all();
        ContactData deletedContact=before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().Count(),equalTo(before.size()-1));
        Contacts after=app.contact().all();
        assertThat(after, equalTo(before.withOut(deletedContact))) ;


    }


}
