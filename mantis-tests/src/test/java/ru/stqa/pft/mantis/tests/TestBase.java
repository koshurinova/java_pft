package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.openqa.selenium.remote.BrowserType;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.IssueBugify;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),
                "config_inc.php","config_inc.php.back");
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public void skipIfNotFixedBugify(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpenBugify(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        String status = app.soap().getIssue(issueId).getStatus(); //проверяем статус
        if (status.equals("closed") || status.equals("resolved")) { //если не открыт
            return false; //возвращаем false и работаем с ним
        }else {
            return true;
        }

    }
    private boolean isIssueOpenBugify(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        RestAssured.authentication=RestAssured.basic("c9739743a483bc10a0ed3679211b6f73","");
        String json= RestAssured.get("http://demo.bugify.com/api/issues/"+issueId+".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        IssueBugify issue = new Gson().<List<IssueBugify>>fromJson(issues, new TypeToken<List<IssueBugify>>() {}.getType()).get(0);

        if (issue.getState_name().equals("Closed") || issue.getState_name().equals("Resolved")){ //если не открыт
            return false; //возвращаем false и работаем с ним
        }else {
            return true;
        }

    }
    @AfterSuite (alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.back","config_inc.php");
        app.stop();
    }


}
