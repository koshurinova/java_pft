package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
    protected WebDriver wd;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;

    private StringBuffer verificationErrors = new StringBuffer();
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }


    public void init() {
       if (browser==BrowserType.FIREFOX){
           wd = new FirefoxDriver();
       } else if(browser==BrowserType.CHROME){
           wd = new ChromeDriver();
       } else if(browser==BrowserType.IE) {
           wd = new InternetExplorerDriver();
       }
       wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       wd.get("http://localhost/addressbook/group.php");
       groupHelper = new GroupHelper(wd);
       navigationHelper = new NavigationHelper(wd);
       sessionHelper=new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
       sessionHelper.login("admin", "secret");
    }


    public void stop() {
        wd.findElement(By.linkText("Logout")).click();
        wd.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
          fail(verificationErrorString);
        }
    }




    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
