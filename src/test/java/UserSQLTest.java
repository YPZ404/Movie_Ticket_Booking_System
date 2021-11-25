import sql.SQLconn;

import sql.*;
import entity.*;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import sql.UserSQL;

import java.sql.*;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class UserSQLTest {

    @Test
    public void userValidatetest(){
        UserSQL usersql = new UserSQL();
        boolean existed_1 = usersql.userValidate("Langjiefu","password123",0);
        assertTrue(existed_1);
        boolean existed_2 = usersql.userValidate("Langjiefu","password123",1);
        assertFalse(existed_2);
    }

    @Test
    public void userQueryByAccounttest(){
        UserSQL usersql = new UserSQL();
        User user = usersql.userQueryByAccount("Langjiefu");
        assertEquals("Jeffkinsman",user.getUsername());
    }

    @Test
    public void userExistedtest(){
        UserSQL usersql = new UserSQL();
        boolean a = usersql.userExisted("Langjiefu");
        boolean b = usersql.userExisted("Langjiefu1234");
        assertTrue(a);
        assertFalse(b);
    }

    @Test
    public void registertest(){
        UserSQL usersql = new UserSQL();
        usersql.register("Jeffwoolen","12345jhfd","Woolen jeff");
        boolean a = usersql.userExisted("Jeffwoolen");
        assertTrue(a);
    }

    @Test
    public void userhisselectiontest(){
        UserSQL usersql = new UserSQL();
        Object[][] a = usersql.userhisselection("40003","","");
        assertEquals("20005",a[0][0]);
    }

    @Test
    public void updateUsertest(){
        UserSQL usersql = new UserSQL();
        boolean a = usersql.updateUser("Jeffwoolen","123456anc");
        assertTrue(a);
    }

}