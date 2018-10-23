package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter (names="-f", description ="Target file" )
    public String file;

    @Parameter (names="-c", description ="Group count" )
    public int count;

    @Parameter (names="-d", description ="Date Format" )
    public String format;

    public static void main (String [] args) throws IOException {
        GroupDataGenerator generator=new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
        jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }


        generator.run();
//        int count=Integer.parseInt(args[0]);
//        File file= new File(args[1]);


    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        if (format.equals("csv")){ //проверка формата загружаемого файла
        saveAsCsv(groups, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(groups, new File(file));
        } else if (format.equals("json")){
            saveAsJson(groups, new File(file));
        } else {
            System.out.println("Не поддерживаемый формат для импорта данных" +format);
        }
    }

    private void saveAsJson(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try (Writer writer=new FileWriter(file); ){ //открываем файл на запись
             writer.write(json); //записываем и автоматически закрываем
        }

    }

    private void saveAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream(); //создаем объект типа xstream
        xstream.processAnnotations(GroupData.class);
//        xstream.alias("group", GroupData.class); //аннотация напрямую
        String xml = xstream.toXML(groups); //конвертируем в xml
        Writer writer=new FileWriter(file); //открываем файл на запись
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv(List<GroupData> groups, File file) throws IOException {
        Writer writer=new FileWriter(file); //открываем файл на запись
        for(GroupData group:groups){ //проходим по всем группам и каждую из них записываем
           writer.write(String.format("%s;%s;%s\n", group.getName(),group.getHeader(),group.getFooter()));
        }
        writer.close(); //закрываем и сохраняем файл

    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i=0; i< count; i++){
            groups.add(new GroupData().withName(String.format("test%s", i))
                    .withHeader(String.format("header%s", i))
                            .withFooter(String.format("footer%s", i)));
        }
        return groups;
    }
}
