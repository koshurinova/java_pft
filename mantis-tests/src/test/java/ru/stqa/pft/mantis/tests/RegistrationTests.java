package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException, ServiceException {

        //проверяем не заблокирован ли тест багом с ID 0000002
        skipIfNotFixed (0000002);
        skipIfNotFixedBugify(2700);
        long now=System.currentTimeMillis();
        String user = "user"+now;
        String password = "password";
        String email = user+"@localhost.localdomain";

//        app.james().createUser(user,password); //создаем юзера на внешнем сервере
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 20000); //письмо во встроенный почтовый сервер
//        List<MailMessage> mailMessages = app.james().waitForMail(user,password,200000); //письмо во внешний сервер
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user,password));


    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex= VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
