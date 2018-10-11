package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Set<GroupData> before=app.group().all();
    GroupData group = new GroupData().withName("test3");
    app.group().create(group);
    Set<GroupData> after=app.group().all();
    Assert.assertEquals(after.size(), before.size()+1);

    group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());  //присваиваем идентификатор добавленной группе/
    // идент: берем коллекцию с уже известными идент// , превращаем ее в поток идент-в
    // / В кач-ве параметра группа, в рез-те id группы/берем макс/преобразуем в целое число

    before.add(group);
    Assert.assertEquals(before,after);
  }

}
