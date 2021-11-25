package sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentSQL {

    public String getCard(String userAccount) throws SQLException {
        String mysql = "select * from users where userAccount='" + userAccount + "'";
        System.out.println(mysql);
        ResultSet rs = SQLconn.executeQuery(mysql);
        rs.next();
        if (rs.wasNull()) {
            System.out.println("");
            return "";
        } else {
            System.out.println(rs.getString(6));
            return rs.getString(6);
        }
    }

    public String getName(String userAccount) throws SQLException {
        String mysql = "select * from users where userAccount='" + userAccount + "'";
        ResultSet rs = SQLconn.executeQuery(mysql);
        rs.next();
        if (rs.wasNull()) {
            System.out.println("");
            return "";
        } else {
            System.out.println(rs.getString(7));
            return rs.getString(7);
        }
    }

    public boolean setCard(String userAccount, String cardName, String cardNumber) {
        String mysql = "UPDATE users\n" +
                "SET cardnumber = '"+ cardNumber +"', cardname = '"+ cardName +"'\n" +
                "WHERE userAccount = '"+ userAccount +"';";
        try {
            int r = SQLconn.executeUpdate(mysql);
            return r != 0;
        } catch (Exception e) {
            System.out.print("");
        }
        return false;
    }
}
