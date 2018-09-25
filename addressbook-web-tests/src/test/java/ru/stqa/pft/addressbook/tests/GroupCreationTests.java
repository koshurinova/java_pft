package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.getGroupHelper().goToGroupPage();
    app.getGroupHelper().initGroupCreation("new");
    app.getGroupHelper().fillGroupForm(new GroupData("test5", null, null));
    app.getGroupHelper().submitGroupCreation("submit");
    app.getNavigationHelper().returnGroupPage();

  }

}
