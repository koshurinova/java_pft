package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions (){
        app.contact().contactPage();
        if (app.contact().all().size()==0){
            app.contact().create(new ContactData().withFirstname("Иван").withLastname("Иванов").withMobilePhone("123456")
                    .withEmail("1@test.ru").withAddress("Москва, Красная Площадь")
                    .withGroup("test1"));
        }
    }
    @Test
    public void testContactEmail () {
        ContactData contact=app.contact().all().iterator().next(); //загружаем множество контактов
        ContactData contactEditFormEmail = app.contact().editFormEmail(contact);
        assertThat(contact.getAllEmails(), equalTo (mergeEmails(contactEditFormEmail)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s)->!s.equals(""))
                .collect(Collectors.joining("\n")); //склеиваем в одну строку с указанием разделителя

    }
}
