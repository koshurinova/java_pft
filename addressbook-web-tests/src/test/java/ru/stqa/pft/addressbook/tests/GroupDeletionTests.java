package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod //предусловия к выполнению тестов
  public void ensurePreconditions () { //проверка наличия хотя бы одной группы
    app.goTo().groupPage();
    if (app.group().all().size() ==0){
      app.group().create(new GroupData().withName("test5"));
    }
  }

  @Test
  public void testGroupDeletion() {

    Groups before=app.group().all();
    GroupData deletedGroup=before.iterator().next(); //возвращаем любой элемент множества
    app.group().delete(deletedGroup);
   Groups after=app.group().all();
    assertThat(after.size(), equalTo(before.size()-1));
//    before.remove(deletedGroup); //удаляем элемент для сравнения
    assertThat(after, equalTo(before.withOut(deletedGroup))) ;

  }


}