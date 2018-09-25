package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test5", null, null));
    }
    app.getGroupHelper().selectGroup(By.name("selected[]"));
    app.getGroupHelper().deleteSelectedGroup(By.xpath("(//input[@name='delete'])[2]"));
    app.getGroupHelper().returnGroupPage();
  }
}