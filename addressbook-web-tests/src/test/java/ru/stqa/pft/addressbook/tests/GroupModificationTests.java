package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod //предусловия к выполнению тестов
    public void ensurePreconditions () { //проверка наличия хотя бы одной группы
        app.goTo().groupPage();
        if (app.group().all().size() ==0){
            app.group().create(new GroupData().withName("test4"));
        }
    }

    @Test
    public void testGroupModification(){

        Groups before=app.group().all();
        GroupData modifiedGroup=before.iterator().next(); //возвращаем любой элемент множества
//        int index = before.size()-1; //индекс выделяемой группы
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(group); //метод для модификации групп
        Groups after=app.group().all();
        assertThat(after.size(), equalTo(before.size()));
       assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    }


}
