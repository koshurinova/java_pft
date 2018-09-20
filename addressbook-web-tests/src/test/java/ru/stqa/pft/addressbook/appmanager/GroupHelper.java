package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper {
    private WebDriver wd;

    public GroupHelper(WebDriver wd) {
        this.wd=wd;
    }

    public void goToGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }

    public void fillGroupForm(GroupData groupData) {
      wd.findElement(By.name("group_name")).clear();
      wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
      wd.findElement(By.name("group_header")).clear();
      wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      wd.findElement(By.name("group_footer")).clear();
      wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void initGroupCreation(String s) {
      wd.findElement(By.name(s)).click();
    }

    public void deleteSelectedGroup(By xpath) {
      wd.findElement(xpath).click();
    }

    public void selectGroup(By name) {
      wd.findElement(name).click();
    }

    }
