package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.Set;

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

        Set<GroupData> before=app.group().all();
        GroupData modifiedGroup=before.iterator().next(); //возвращаем любой элемент множества
//        int index = before.size()-1; //индекс выделяемой группы
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(group); //метод для модификации групп
        Set<GroupData> after=app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(new HashSet<>(before),new HashSet<>(after));
    }


}
