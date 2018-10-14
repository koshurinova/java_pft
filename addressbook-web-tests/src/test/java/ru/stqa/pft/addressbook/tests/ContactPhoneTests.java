package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

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
    public void testContactPhone () {
       ContactData contact=app.contact().all().iterator().next(); //загружаем множество контактов
       ContactData contactInfoFromEditForm = app.contact().editFormPhone(contact);
       assertThat(contact.getAllPhones(), equalTo (mergePhones(contactInfoFromEditForm)));
   }

    private String mergePhones(ContactData contact) {
       return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s)->!s.equals(""))
               .map(ContactPhoneTests::cleaned) //применяем функцию очищения
               .collect(Collectors.joining("\n")); //склеиваем в одну строку с указанием разделителя

    }

    public static String cleaned(String phone){ //заменяем лишние символы
       return phone.replaceAll("[-\\s()]","");
   }
}
