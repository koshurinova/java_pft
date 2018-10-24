package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTests {
    @Test

    public void testDbConnnection() {
        Connection conn = null;

        try {
            conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC&user=root&password=");
            // Do something with the Connection
            Statement st = conn.createStatement(); //создаем объект типа Statement
            ResultSet rs = st.executeQuery("select group_id,  group_name, group_header, group_footer from group_list");
            Groups groups = new Groups();
            while (rs.next()){
                           groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getNString("group_name")).withHeader(rs.getNString("group_header"))
                       .withFooter(rs.getNString("group_footer")));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(groups);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
