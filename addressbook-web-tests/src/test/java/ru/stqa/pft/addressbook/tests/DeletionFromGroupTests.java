package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletionFromGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions (){
        //проверка наличия группы
        if (app.db().groups().size() ==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test4"));
        }
        Groups groups =app.db().groups();
        //проверка наличия контакта
        if (app.db().contacts().size()==0){
            app.contact().contactPage();
            app.contact().create(new ContactData().
                    withFirstname("Семен").withLastname("Вгрупов").withMobilePhone("123456").withEmail("1@test.ru")
                    .inGroup(groups.iterator().next()));
        }
    }
    @Test
    public void testContactToGroup(){
        GroupData group =app.db().groups().iterator().next();
        ContactData deletedFromGroup=app.db().contacts().iterator().next();
        if (!deletedFromGroup.getGroups().equals(group)){
            app.contact().contactPage();
            app.contact().contactAddToGroup(deletedFromGroup);
        }
        int groupBefore=deletedFromGroup.getGroups().size();
        app.contact().contactPage();
        app.contact().deleteContactFromGroup(deletedFromGroup,group);
        app.db().refresh(deletedFromGroup);
        int groupAfter=deletedFromGroup.getGroups().size();
        assertThat(groupAfter, equalTo(groupBefore-1));
    }

}
