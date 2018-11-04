package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserDate;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordHelper extends HelperBase {

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void logAdmin() throws IOException {
        app.session().login("administrator","root");
    }

    public void resert(UserDate user) {
        click(By.linkText("Manage Users"));
        selectUserById(user.getId());
        click(By.cssSelector("input[value='Reset Password']"));

    }

    private void selectUserById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']",id))).click();
    }

    public void changePass(UserDate user) throws IOException {
        String newpassword = "newpassword";
        String email = user.getEmail();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        finishChange(confirmationLink, newpassword);
    }

    private void finishChange(String confirmationLink, String newpassword) {
        wd.get(confirmationLink); //проходим по ссылке
        type(By.name("password"),newpassword);
        type(By.name("password_confirm"),newpassword);
        click(By.cssSelector("input[value='Update User']"));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex= VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    public void logNewPass(UserDate user) throws IOException {
           assertTrue(app.newSession().login(String.format(user.getUsername()),"newpassword"));
    }
}
