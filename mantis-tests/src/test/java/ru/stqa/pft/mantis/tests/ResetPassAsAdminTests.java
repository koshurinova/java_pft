package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserDate;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPassAsAdminTests extends TestBase {
    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }
    @Test

    public void testResetPassAsAdmin() throws IOException {
        //берем из БД данные юзера и админа
        int administratorId = app.db().users().stream().filter((a) -> a.getUsername().equals("administrator")).iterator().next().getId();
        UserDate user = app.db().users().stream().filter((u) -> u.getId() != administratorId).iterator().next();

        //заходим под администратором
        app.resetPass().logAdmin();

       //переходим в список пользователей и выбираем пользователя для смены пароля
        app.resetPass().resert(user);

        // проверяем почту и переходим по ссылке-меняем пароль
        app.resetPass().changePass(user);

        //проверяем вход под юзером с новым паролем
        app.resetPass().logNewPass(user);

    }
    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
