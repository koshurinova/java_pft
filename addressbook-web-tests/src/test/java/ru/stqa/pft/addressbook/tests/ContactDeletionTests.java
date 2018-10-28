package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions (){
        Groups groups =app.db().groups();
        if (app.db().contacts().size()==0){
            app.contact().contactPage();
            app.contact().create(new ContactData().
                    withFirstname("Иван").withLastname("Иванов").withMobilePhone("123456").withEmail("1@test.ru")
                    .inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactDeletion(){
        Contacts before=app.db().contacts();
        ContactData deletedContact=before.iterator().next();
        app.contact().contactPage();
        app.contact().delete(deletedContact);
        assertThat(app.contact().Count(),equalTo(before.size()-1));
        Contacts after=app.db().contacts();
        assertThat(after, equalTo(before.withOut(deletedContact))) ;


    }


}
