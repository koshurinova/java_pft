package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names="-f", description ="Target file" )
    public String file;

    @Parameter (names="-c", description ="Contact count" )
    public int count;

    @Parameter (names="-d", description ="Date Format" )
    public String format;

    public static void main (String [] args) throws IOException {
        ContactDataGenerator generator=new  ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Не поддерживаемый формат для импорта данных" +format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer=new FileWriter(file); ){
            writer.write(json);
        }

    }


    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i=0; i< count; i++){
            contacts.add(new ContactData().withFirstname(String.format("FirstName%s", i))
                    .withLastname(String.format("LastName%s", i))
                    .withMobilePhone(String.format("12345%s", i))
                    .withGroup("test1")
                    .withAddress(String.format("Adress%s", i)));
        }
        return contacts;
    }
}

