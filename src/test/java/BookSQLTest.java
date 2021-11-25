import org.junit.jupiter.api.Test;
import sql.BookSQL;
import sql.SQLconn;
import sql.ShowSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookSQLTest {

    @Test
    public void insertIntoBooktest(){
        BookSQL booksql = new BookSQL();
        boolean a = booksql.insertIntoBook("40000","20002",1,60,1,1,1);
        assertTrue(a);
    }

    @Test
    public void userhisselectiontest(){
        BookSQL booksql = new BookSQL();
        Object[][] a = booksql.userhisselection("40003","","");
        assertEquals("90000",a[0][0]);
    }
}
