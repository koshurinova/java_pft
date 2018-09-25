package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{



    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {

    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))){
        return;
    }
        click(By.linkText("groups"));
    }

    public void returnGroupPage() {

        click(By.linkText("group page"));
    }
    public void viewPageContact(String home) {

        if (isElementPresent(By.id("nametable"))){
            return;
        }
        click(By.linkText("home"));
    }
}
