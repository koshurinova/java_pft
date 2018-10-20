package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase{


  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    BufferedReader reader=new BufferedReader(new FileReader(new File("src\\test\\resources\\groups.json")));
    String json="";
    String line=reader.readLine();
    while (line!=null){
      json+=line;
      line=reader.readLine();
    }
    Gson gson = new Gson();
    List<GroupData> groups = gson.fromJson(json,new TypeToken<List<GroupData>>(){}.getType());
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
//    List<Object[]> list = new ArrayList<Object[]>();
    //данные из файла
    BufferedReader reader=new BufferedReader(new FileReader(new File("src\\test\\resources\\groups.xml")));
    String xml=""; //переменная,  в которую читаем содержимое файла
    String line=reader.readLine();
    while (line!=null){
      xml+=line;
//      String[] split = line.split(";");
//      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line=reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>)xstream.fromXML(xml);
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();  //к каждому объекту
    // применяем функцию и все "формируем" в массив

    //данные вручную
//    list.add(new Object[]{new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
//    list.add(new Object[]{new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
//    list.add(new Object[]{new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
//    return list.iterator();

  }


  @Test(dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().groupPage();
    Groups before=app.group().all();
//    GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
    app.group().create(group);
    assertThat(app.group().count(),equalTo(before.size()+1));//проверка кол-ва групп до и после
    Groups after=app.group().all();
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadGroupCreation() throws Exception { //проверка с именем групп сожержащей '
    app.goTo().groupPage();
    Groups before=app.group().all();
    GroupData group = new GroupData().withName("test3'");
    app.group().create(group);
    assertThat(app.group().count(),equalTo(before.size()));
    Groups after=app.group().all();
    assertThat(after, equalTo(before));
  }
}
