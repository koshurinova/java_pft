package ru.stqa.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {

    private CloseableHttpClient httpClient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app) {
        this.app = app;
        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build(); //создается новая сессия с перенаправлением
    }

    public boolean login(String username, String password) throws IOException { //метод логин
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php"); //создание запроса типа POST
        List<NameValuePair> params = new ArrayList<>(); //тут и ниже параметры формируются
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params)); //параметры упаковываются и помещаются в заранее созданный запрос
        CloseableHttpResponse response = httpClient.execute(post); //отправка запроса и получение в результате ответа
        String body = getTextFrom(response); //получение текста ответа
        return body.contains(String.format("<span class=\"italic\">%s</span>", username)); //проверка успешности входа пользователя
    }

    private String getTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    public boolean isLoggedInAs(String username) throws IOException { //метод определяющий текущего пользователя в системе
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
        CloseableHttpResponse response = httpClient.execute(get);
        String body = getTextFrom(response);
        return body.contains(String.format("<span class=\"italic\">%s</span>", username));
    }
}
