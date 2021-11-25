package sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffSQL {
    public Object[][] managerStaffSelection(String name) {
        String sql1 = "SELECT userId, userAccount, userName, authority FROM users\n" +
                "WHERE authority= 1\n";

        String sql2 = "AND UPPER (userName) LIKE UPPER('%" + name + "%')";


        if (name == null) {
            sql2 = "where 1=1";
        }

        String mysql = sql1 + sql2;
        System.out.println(mysql);

        return getStafflist(sql1 + sql2);

    }

    public Object[][] getStafflist(String mysql) {
        Object[][] staff = new Object[50][8];

        try {
            ResultSet rs = SQLconn.executeQuery(mysql);
            int i = 0;
            while (rs.next()) {
                staff[i][0] = rs.getString(1);
                staff[i][1] = rs.getString(2);
                staff[i][2] = rs.getString(3);
                staff[i][3] = rs.getString(4);
                i++;
            }

            SQLconn.closeConnection();

        } catch (SQLException e) {
            System.out.println("An error");
        }
        return staff;
    }

    public boolean insertStaff(String userAccount, String pin, String userName) {
        String mysql = "INSERT INTO users\n VALUES('" + userAccount + "','" + pin + "','" + userName + "', 1, Default, Default)";
        System.out.println(mysql);
        int r = SQLconn.executeUpdate(mysql);
        return r != 0;
    }

    public boolean deleteStaff(String userId) {
        String mysql = "DELETE FROM users\n" +
                "WHERE userId= '" + userId + "';";
        System.out.println(mysql);
        int r = SQLconn.executeUpdate(mysql);
        return r != 0;
    }

}
