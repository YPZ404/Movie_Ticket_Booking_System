import sql.SQLconn;

import entity.*;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class SQLconnTest{

    @Test
    public void executeQuerytest(){
        String mysql = "select * from users where userId = 40000";
        try{
            ResultSet rs = SQLconn.executeQuery(mysql);
            String result = rs.getString(1);
            assertEquals("40000", result);
        }catch (SQLException e) {
            System.out.println("An error was reported in the method of obtaining the user array list");
        }
    }

    @Test
    public void executeUpdatetest(){
        String mysql_1 = "update users set userAccount= 'Langjiefu', Pin='password123' , userName='Jeffkinsman',Authority=0\n" +
                "where userId=40000";
        assertEquals(1, SQLconn.executeUpdate(mysql_1));
    }

    @Test
    public void executeSingleQuerytest(){
        String mysql = "select userAccount from users where userId = 40000";
        Object obj = SQLconn.executeSingleQuery(mysql);
        assertEquals("Langjiefu", obj);

    }

}