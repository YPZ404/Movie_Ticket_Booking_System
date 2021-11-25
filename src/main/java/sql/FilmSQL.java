package sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmSQL {
    public Object[][] staffMovieSelection(String name, String filmtype) {
        String sql1 = "SELECT film_id,film_name,film_classification,upcomingtimes,film_release,director,casts,film_synopsis\n" +
                "FROM film ";

        String sql2 = "where UPPER (film_name) LIKE UPPER('%" + name + "%')";
        String sql3 = " and film_classification = '" + filmtype + "'";

        if (name == null) {
            sql2 = "where 1=1";
        }
        if (filmtype.equals("all")) {
            sql3 = " and 1=1";
        }

        String mysql = sql1 + sql2 + sql3;
        System.out.println(mysql);

        return getMovielist(sql1 + sql2 + sql3);

    }

    public Object[][] getMovielist(String mysql) {
        Object[][] films = new Object[50][8];

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
                i++;
            }

            SQLconn.closeConnection();

        } catch (SQLException e) {
            System.out.println("An error");
        }
        return films;
    }

    public boolean updateFilm(String film_id, String film_name, String film_type, String upcomint_times, String release, String director, String cast, String synposis) {
        String mysql = "UPDATE film SET film_name= '"+ film_name +"', film_classification= '"+film_type +"', upcomingtimes= '"+ upcomint_times +"', film_release= '"+ release +"', director= '"+ director +"', casts= '"+ cast +"', film_synopsis= '"+ synposis +"'" + "where film_id = '"+film_id+ "'";
        System.out.println(mysql);
        int r = SQLconn.executeUpdate(mysql);
        return r != 0;
    }

    public boolean insertFilm(String film_name, String film_type, String upcoming_times, String release, String director, String cast, String synposis) {
        String mysql = "INSERT INTO film\n VALUES('" + film_name + "','" + synposis + "','" + film_type + "','" + release + "','" + director + "','" + cast + "','" + upcoming_times +"')";
        System.out.println(mysql);
        int r = SQLconn.executeUpdate(mysql);
        return r != 0;
    }

    public boolean deleteFilm(String film_id) {
        String mysql = "DELETE FROM film\n" +
                "WHERE film_id= '" + film_id + "';";
        System.out.println(mysql);
        int r = SQLconn.executeUpdate(mysql);
        return r != 0;
    }

}