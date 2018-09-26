package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }


    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation(String s) {
        click(By.name(s));
    }

    public void submitGroupCreation(String s) {
        click(By.name(s));
    }

    public void deleteSelectedGroup(By xpath) {
        click(xpath);
    }

    public void returnGroupPage() {
        click(By.linkText("group page"));
    }

    public void selectGroup(By name) {
        click(name);
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void createGroup(GroupData group) {

        initGroupCreation("new");
        fillGroupForm(group);
        submitGroupCreation("submit");
        returnGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}
