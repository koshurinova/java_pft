package ru.stqa.pft.mantis.model;

public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to, String text){
        this.text=text;
        this.to=to;
    }
}
