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
    if (app.db().groups().size() ==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test4"));
    }
  }

  @Test
  public void testGroupDeletion() {

    Groups before=app.db().groups();
    GroupData deletedGroup=before.iterator().next(); //возвращаем любой элемент множества
    app.group().delete(deletedGroup);
    assertThat(app.group().count(),equalTo(before.size()-1));
    Groups after=app.db().groups();
    assertThat(after, equalTo(before.withOut(deletedGroup))) ;

  }


}