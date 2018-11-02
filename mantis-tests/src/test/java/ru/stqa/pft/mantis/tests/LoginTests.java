package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase{

    @Test
    public void testLogin () throws IOException {
        HttpSession session = app.newSession(); //создаем новую сессию
        assertTrue(session.login("administrator","root")); //проверка логина пользователем
        assertTrue(session.isLoggedInAs("administrator"));

    }
}
