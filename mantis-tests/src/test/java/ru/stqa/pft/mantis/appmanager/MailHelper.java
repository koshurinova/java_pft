package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {


    private ApplicationManager app;
    private final Wiser wiser;

    public MailHelper(ApplicationManager app) {
        this.app = app;
        wiser = new Wiser(); //объект типа wiser (почтовый сервер), для его запуска вызывается метод start
    }

    public List<MailMessage> waitForMail(int count, long timeout) { //метод для ожидания почты (кол-во писем, время ожидания)
        long start = System.currentTimeMillis(); //запоминаем текущее время
        while (System.currentTimeMillis() < start + timeout) {
            if (wiser.getMessages().size() >= count) {
                return wiser.getMessages().stream().map((m) -> toModelMail(m)).collect(Collectors.toList()); //берем список
                // -превращаем в поток
                // -ко всем элементам потока применяем функцию toModelMail
                // -получившиеся объекты собираем в новый список
            }
            try {
                Thread.sleep(1000); //подождать 1000 мс
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No mail :(");
    }

    public static MailMessage toModelMail(WiserMessage m) {
        try {
            MimeMessage mm = m.getMimeMessage(); //реальный объект
            return new MailMessage(mm.getAllRecipients()[0].toString(), (String) mm.getContent()); //берем первого получателя и контент
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void start() {
        wiser.start();
    }

    public void stop() {
        wiser.stop();
    }
}