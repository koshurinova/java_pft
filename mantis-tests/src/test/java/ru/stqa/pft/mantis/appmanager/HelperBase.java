package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;

import java.io.File;

public class HelperBase {
    protected ApplicationManager app;
    private boolean acceptNextAlert = true;
    protected WebDriver wd;

    public HelperBase(ApplicationManager app) {
        this.app=app;
        this.wd=app.getDriver();

    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if (text != null){
            String existingText=wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach (By locator, File file) {
        if (file != null){
                            wd.findElement(locator).sendKeys(file.getAbsolutePath()); //c указанием абсолютного пути
            }

    }

    public boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public String closeAlertAndGetItsText() {
        try {
            Alert alert = wd.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
