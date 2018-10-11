package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void deleteSelectedGroup() {
        click(By.xpath("(//input[@name='delete'])[2]"));
    }

    public void returnGroupPage() {
        click(By.linkText("group page"));
    }

    public void selectGroupById (int id) {
        wd.findElement(By.cssSelector("input[value='" +id+ "']" )).click(); //выбор по id элемента
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {  //создание группы
        initGroupCreation("new");
        fillGroupForm(group);
        submitGroupCreation("submit");
        returnGroupPage();
    }

    public void modify(GroupData group) { //модификация группы
        selectGroupById(group.getId());
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnGroupPage();
    }


      public void delete(GroupData group) {
        selectGroupById(group.getId()); //выбирает по идентификатору
        deleteSelectedGroup();
        returnGroupPage();

    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public Set<GroupData> all() { //возвращает готовое множество
        Set<GroupData> groups= new HashSet<GroupData>(); //создаем множество элементов типа GroupData
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group")); //смотрим все элементы с указанным локатором
        for (WebElement element: elements){ //для каждого вытаскиваем
            String name = element.getText(); //имя

            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value")); //идентификатор группы
            groups.add(new GroupData().withId(id).withName(name)); //помещаем в множество
        }
        return groups;
    }


}
