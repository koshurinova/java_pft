package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;



import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before=app.group().all();
    GroupData group = new GroupData().withName("test3");
    app.group().create(group);
    Groups after=app.group().all();
    assertThat(after.size(),equalTo(before.size()+1));

//    group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());  //присваиваем идентификатор добавленной группе/
    // идент: берем коллекцию с уже известными идент// , превращаем ее в поток идент-в
    // / В кач-ве параметра группа, в рез-те id группы/берем макс/преобразуем в целое число
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

}
