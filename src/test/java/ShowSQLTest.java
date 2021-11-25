import org.junit.jupiter.api.Test;
import sql.SQLconn;
import sql.ShowSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowSQLTest {


    @Test
    public void usershowselectiontest(){
        ShowSQL showsql = new ShowSQL();
        Object[][] a = showsql.usershowselection("d","all","all","all","");
        String b = (String) a[0][1];
        assertEquals("Dune",b);
    }

    @Test
    public void insertShowtest(){
        ShowSQL showsql = new ShowSQL();
        boolean a = showsql.insertShow("10001","30001","silver","2021-12-04 13:30:00.000",
                "10","10","10","25.00");
        assertTrue(a);
    }

    @Test
    public void seatupdatetest(){
        ShowSQL showsql = new ShowSQL();
        boolean a = showsql.seatupdate("20001",9,9,9);
        assertTrue(a);
    }
}
