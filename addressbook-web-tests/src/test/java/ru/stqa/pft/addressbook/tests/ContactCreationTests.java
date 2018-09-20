package ru.stqa.pft.addressbook.tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

//    @BeforeMethod(alwaysRun = true)
//    public void setUp() throws Exception {
//        wd = new FirefoxDriver();
//        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        wd.get("http://localhost/addressbook/");
////    wd.findElement(By.name("user")).clear();
//        login("admin", "secret");
//    }
//
//    private void login(String username, String password) {
//        wd.findElement(By.name("user")).sendKeys(username);
//        wd.findElement(By.name("pass")).sendKeys(password);
//        initContactCreation("//input[@value='Login']");
//    }

    @Test
    public void testContactCreation() throws Exception {
        app.getContactHelper().goToGroupPage();
//        goToAddContact("add new");
        app.getContactHelper().fillContactForm(new ContactData("Иван", "Иванов", "123456", "1@test.ru"));
//        fillContactForm(new ContactData("Иван", "Иванов", "123456", "1@test.ru"));
       app.getContactHelper().initContactCreation("(//input[@name='submit'])[2]");
//        initContactCreation("(//input[@name='submit'])[2]");
        app.getContactHelper().viewPageContact("home");
//        viewPageContact("home");


    }

//    @AfterMethod(alwaysRun = true)
//    public void tearDown() throws Exception {
//        app.getContactHelper()viewPageContact("Logout");
//        wd.quit();
//        String verificationErrorString = verificationErrors.toString();
//        if (!"".equals(verificationErrorString)) {
//            fail(verificationErrorString);
//        }
//    }

//    private boolean isElementPresent(By by) {
//        try {
//            wd.findElement(by);
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }
//
//    private boolean isAlertPresent() {
//        try {
//            wd.switchTo().alert();
//            return true;
//        } catch (NoAlertPresentException e) {
//            return false;
//        }
//    }
//
//    private String closeAlertAndGetItsText() {
//        try {
//            Alert alert = wd.switchTo().alert();
//            String alertText = alert.getText();
//            if (acceptNextAlert) {
//                alert.accept();
//            } else {
//                alert.dismiss();
//            }
//            return alertText;
//        } finally {
//            acceptNextAlert = true;
//        }
//    }
}
