package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {
//  private boolean acceptNextAlert = true;
//  private StringBuffer verificationErrors = new StringBuffer();


  @Test
  public void testGroupDeletion() {
    goToGroupPage();
    selectGroup(By.name("selected[]"));
    deleteSelectedGroup(By.xpath("(//input[@name='delete'])[2]"));
    goToGroupPage();
  }
}