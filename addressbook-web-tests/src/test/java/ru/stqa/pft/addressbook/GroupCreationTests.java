package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    initGroupCreation("new");
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    initGroupCreation("submit");
    goToGroupPage();

  }

}
