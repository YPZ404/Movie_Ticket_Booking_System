package sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowSQL {

    public Object[][] usershowselection(String name,String filmtype, String screensize, String location, String time){
        String sql1 = "select show_id,film_name,screensize,location_name,film_classification,show_release,price,front,middle,rear,director,casts,film_synopsis\n" +
                "from show INNER JOIN film f on show.film_id = f.film_id INNER JOIN location l on show.Location_id = l.location_id " ;

        String sql2 = "where UPPER (film_name) LIKE UPPER('%" + name +"%')";
        String sql3 = " and film_classification = '" + filmtype + "'";
        String sql4 = " and screensize = '" + screensize + "'";
        String sql5 = " and location_name = '" + location + "'";
        String sql6 = " and show_release = '" + time + "'";

        if (name == null){sql2 = "where 1=1";}
        if (filmtype.equals("all")){sql3 = " and 1=1";}
        if (screensize.equals("all")){sql4 = " and 1=1";}
        if (location.equals("all")){sql5 = " and 1=1";}
        if (time.equals("")){sql6 = " and 1=1";}

        String mysql = sql1+sql2+sql3+sql4+sql5+sql6;
        System.out.println(mysql);

        return getshowlist(sql1+sql2+sql3+sql4+sql5+sql6);
    }

    public Object[][] getshowlist(String mysql){
        Object[][] films =  new Object[50][13];

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
                films[i][12] = rs.getString(13);
                i ++;
            }
            SQLconn.closeConnection();

        }catch (SQLException e) {
            System.out.println("An error");
        }
        return films;
    }

    public boolean insertShow(String film_id, String location_id, String screenSize, String release, String front, String middle, String rear, String price) {
        String mysql = "INSERT INTO show\n VALUES('" + film_id + "','" + location_id + "','" + screenSize + "','" + release + "','" + front + "','" + middle + "','" + rear + "','" + price + "')";
        System.out.println(mysql);
        int r = SQLconn.executeUpdate(mysql);
        return r != 0;
    }

    public boolean seatupdate(String showID, int front, int mid, int rear){
        String sql = "UPDATE show set front=" +front+ ", middle ="+mid+", rear=" + rear +
                "where show_id=" +  showID;
        int r  = SQLconn.executeUpdate(sql);
        SQLconn.closeConnection();
        return r != 0;
    }

}
