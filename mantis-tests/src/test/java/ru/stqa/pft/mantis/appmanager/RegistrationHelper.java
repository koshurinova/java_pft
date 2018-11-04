package ru.stqa.pft.mantis.appmanager;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.mantis.tests.TestBase;

public class RegistrationHelper extends HelperBase {


    public RegistrationHelper(ApplicationManager app) {
       super(app);
//       wd=app.getDriver(); //инициализация драйвера в момент первого обращения
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"),username);
        type(By.name("email"),email);
        click(By.cssSelector("input[value='Signup']"));

    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink); //проходим по ссылке
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click(By.cssSelector("input[value='Update User']"));
    }


}
