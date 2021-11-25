package sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSQL {

    public boolean insertIntoBook(String userID, String ShowID, int tickets, float purchasePrice, int purchasedFront, int purchasedMid, int purchasedRare) {
        String sql = "INSERT INTO booking (userid, showid, tickets, purchaseprice, purchasedfront, purchasedmid, purchasedrare)\n" +
                "VALUES ('"+ userID +"', '"+ ShowID +"', '"+ tickets +"', '"+ purchasePrice +"', '"+ purchasedFront +"', '"+ purchasedMid +"', '"+ purchasedRare +"');";
        System.out.println(sql);
        try {
            int r = SQLconn.executeUpdate(sql);
            return r != 0;
        } catch (Exception e) {
            System.out.println("An error was reported in the insertion process");
        }
        return false;
    }

    public Object[][] userhisselection(String userid, String name, String time) {
        String sql1 = "select book_id,show_id,film_name,screensize,location_name,film_classification,show_release,purchaseprice,tickets,front,middle,rear\n\n" +
                "from booking b INNER JOIN show on show.show_id = b.Showid INNER JOIN film f on show.film_id = f.film_id INNER JOIN location l on show.Location_id = l.location_id " +
                "where userId = " + userid;

        String sql2 = "and UPPER (film_name) LIKE UPPER('%" + name + "%')";
        String sql6 = " and show_release = '" + time + "'";

        if (time.equals("")) {
            sql6 = " and 1=1";
        }

        return gethislist(sql1 + sql2 + sql6);
    }

    public Object[][] gethislist(String mysql) {
        Object[][] films = new Object[50][13];

        try {
            ResultSet rs = SQLconn.executeQuery(mysql);
            int i = 0;
            while (rs.next()) {
                films[i][0] = rs.getString(1);
                films[i][1] = rs.getString(2);
                films[i][2] = rs.getString(3);
                films[i][3] = rs.getString(4);
                films[i][4] = rs.getString(5);
                films[i][5] = rs.getString(6);
                films[i][6] = rs.getString(7);
                films[i][7] = rs.getString(8);
                films[i][8] = rs.getString(9);
                films[i][9] = rs.getString(10);
                films[i][10] = rs.getString(11);
                films[i][11] = rs.getString(12);
                i++;
            }
            SQLconn.closeConnection();

        } catch (SQLException e) {
            System.out.println("An error raise in history table");
        }
        return films;
    }

    public void drophis(String bookid) {
        String sql = "DELETE FROM booking WHERE book_id = '" + bookid + "'";
        SQLconn.executeQuery(sql);
        SQLconn.closeConnection();
    }
}
