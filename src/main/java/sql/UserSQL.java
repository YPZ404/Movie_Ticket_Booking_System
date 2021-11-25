package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.User;


/**
 * @Project: FIlmTicketingSystem
 * @Date: 21/10/2021
 * @author: zhefu wu
 * @Description: TODO User SQL command and link
 */

public class UserSQL {
    //Determine whether the user exists and whether the password is entered correctly
    public boolean userValidate(String Account, String pwd, int type) {
        boolean existed = false;
        String mysql = "select userAccount,Pin from users ";
        mysql += "where userAccount='" + Account + "'" + " and Pin='" + pwd + "'";
        mysql += " and Authority='" + type + "'";
        System.out.println(mysql);
        try {
            ResultSet rs = SQLconn.executeQuery(mysql);
            //If the user exists
            if (rs.next()) {
                existed = true;
            }
            SQLconn.closeConnection();
        } catch (SQLException e) {
            System.out.println("An error is reported in the method of verifying the user");
        }
        return existed;
    }

    //Obtain user information by user account
    public User userQueryByAccount(String userAccount) {
        String mysql = "select * from users where userAccount='" + userAccount + "'";
        System.out.println(mysql);
        return getUsers(mysql).get(0);
    }

    //Update user information by User ID(for administrators)
    public boolean updateUser(String userAccount, String newpin) {
        String mysql = "update users set Pin= '"+ newpin +"'" + "where userAccount = '"+userAccount+ "'";
        System.out.println(mysql);
        int r = SQLconn.executeUpdate(mysql);
        return r != 0;
    }


    //Query whether the user exists, if it does not exist, return false
    public boolean userExisted(String userAccount) {
        String mysql = "select * from users where userAccount='" + userAccount + "'";
        System.out.println(mysql);
        Object obj = SQLconn.executeSingleQuery(mysql);
        return obj != null;
    }

    //Register user information, return whether the registration is successful
    public boolean register(String UserAccount, String password, String name) {
        String mysql = "insert into users values('" + UserAccount + "','" + password + "','" + name + "',Default,Default,Default);";
        System.out.println(mysql);
        //0 means insertion failed
        int rs = 0;
        try {
            //If the update is successful, rs=1
            rs = SQLconn.executeUpdate(mysql);
        } catch (Exception e) {
            System.out.println("An error was reported in the registered user information error");
        }
        return rs != 0;
    }
    //Get user information array list
    private ArrayList<User> getUsers(String mysql) {
        ArrayList<User> users = new ArrayList<>();
        try {
            ResultSet rs = SQLconn.executeQuery(mysql);
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString(1));
                user.setAccount(rs.getString(2));
                user.setPin(rs.getString(3));
                user.setUsername(rs.getString(4));
                user.setAuthority(rs.getInt(5));
                user.setCardnumber(rs.getString(6));
                user.setCardname(rs.getString(7));
                users.add(user);
            }
            SQLconn.closeConnection();
        } catch (SQLException e) {
            System.out.println("An error was reported in the method of obtaining the user array list");
        }
        return users;
    }
    public Object[][] userhisselection(String userid,String name,String time){
        String sql1 = "select show_id,film_name,screensize,location_name,film_classification,show_release,purchaseprice,tickets\n" +
                "from booking b INNER JOIN show on show.show_id = b.Showid INNER JOIN film f on show.film_id = f.film_id INNER JOIN location l on show.Location_id = l.location_id " +
                "where userId = " + userid ;

        String sql2 = "and UPPER (film_name) LIKE UPPER('%" + name +"%')";
        String sql6 = " and show_release = '" + time + "'";

        if (time.equals("")){sql6 = " and 1=1";}

        return gethislist(sql1+sql2+sql6);
    }

    public Object[][] gethislist(String mysql){
        Object[][] films =  new Object[50][9];

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
                i ++;
            }
            SQLconn.closeConnection();

        }catch (SQLException e) {
            System.out.println("An error raise in history table");
        }
        return films;
    }

}
