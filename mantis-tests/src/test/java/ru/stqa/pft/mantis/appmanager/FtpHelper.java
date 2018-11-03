package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {


    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app) { //создается ftp клиент
        this.app = app;
        ftp = new FTPClient();
    }

    public void upload(File file, String target, String backup) throws IOException { //загружаем новый файл и перееименовываем старый
        ftp.connect(app.getProperty("ftp.host")); //соединение с сервером
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password")); //логинимся
        ftp.deleteFile(backup); //удаляем предыдущую резервную копию
        ftp.rename(target, backup); //перееименовываем удаленный файл, делаем резервную копию
        ftp.enterLocalPassiveMode(); //пассивный режим передачи данных (технич.)
        ftp.storeFile(target, new FileInputStream(file)); //передача файла
        ftp.disconnect(); //разрыв соединения
    }

    public void restore(String backup, String target) throws IOException {//восстанавливает старый файл
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup, target);
        ftp.disconnect();
    }
}
