package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @BeforeMethod
    //Проверка наличия группы, в которую можно добавить контакт
    public void ensurePreconditions () {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test_null"));
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader=new BufferedReader(new FileReader(new File("src\\test\\resources\\contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test (dataProvider = "validContactsFromJson", enabled = false)
    public void testContactCreationJson(ContactData contact) throws Exception {
        Groups groups =app.db().groups();
        app.contact().contactPage();
        Contacts before=app.db().contacts();
        app.contact().create(contact);
        assertThat(app.contact().Count(),equalTo(before.size()+1));
        Contacts after=app.db().contacts();
        assertThat(after, equalTo (before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }
    @Test
    public void testContactCreation() throws Exception {


      Groups groups =app.db().groups();
        File photo=new File("src/test/resources/photo.jpg");
        ContactData contact = new ContactData().withFirstname("Петров").withLastname("Иванов")
                .withMobilePhone("123456").withEmail("1@test.ru").withPhoto(photo).inGroup(groups.iterator().next());

        app.contact().contactPage();
        Contacts before=app.db().contacts();
        app.contact().create(contact);
        assertThat(app.contact().Count(),equalTo(before.size()+1));
        Contacts after=app.db().contacts();
        assertThat(after, equalTo (before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
    }
}



