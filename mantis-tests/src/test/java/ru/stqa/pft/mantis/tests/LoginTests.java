package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase{

    @Test
    public void testLogin () throws IOException, ServiceException {
        //проверяем не заблокирован ли тест багом с ID 0000002
        skipIfNotFixed (0000002);
        HttpSession session = app.newSession(); //создаем новую сессию
        assertTrue(session.login("administrator","root")); //проверка логина пользователем
        assertTrue(session.isLoggedInAs("administrator"));

    }
}
