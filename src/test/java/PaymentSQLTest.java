import org.junit.jupiter.api.Test;
import sql.PaymentSQL;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentSQLTest {

    @Test
    public void testGetCard() throws SQLException {
        PaymentSQL paymentSQL = new PaymentSQL();
        String s = paymentSQL.getCard("a");
        assertEquals("40691", s);
    }

    @Test
    public void testGetName() throws SQLException {
        PaymentSQL paymentSQL = new PaymentSQL();
        String s = paymentSQL.getName("a");
        assertEquals("Charles", s);
    }

    @Test
    public void setCardTest() {
        PaymentSQL paymentSQL = new PaymentSQL();
        boolean b = paymentSQL.setCard("a", "Charles", "40691");
        assertTrue(b);
    }
}
