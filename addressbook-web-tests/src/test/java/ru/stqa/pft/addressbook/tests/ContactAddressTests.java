package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

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
        ContactData contactInfoFromEditForm = app.contact().editFormAddress(contact);
        assertThat(contact.getAddress(), equalTo (cleaned(contactInfoFromEditForm.getAddress())));
    }



    public static String cleaned(String address){
        return address.replaceAll("^\\s+","").replaceAll("(\\s)+"," ");
    }
}