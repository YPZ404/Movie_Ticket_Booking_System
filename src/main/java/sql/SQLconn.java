package sql;

import java.sql.*;

/**
 * @Project: FIlmTicketingSystem
 * @Date: 18/10/2021
 * @author: zhefu wu
 * @Description: TODO User SQL server connection
 */

public class SQLconn {

    //Create database connection, preprocessing and result set objects
    private static Connection conn = null;
    private static PreparedStatement pre = null;
    private static ResultSet rs = null;

    //Load the driver and establish a connection
    private static void buildConnection() {
        //Database configuration information
        String connectionUrl =
                "jdbc:sqlserver://usyd-soft2412-2021s2-f14-c09-group03.database.windows.net:1433;"
                        + "database=film-booking;"
                        + "user=superman@usyd-soft2412-2021s2-f14-c09-group03.database.windows.net;"
                        + "password=Please1moreHD!;"
                        + "encrypt=true;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";

        try {
            conn = DriverManager.getConnection(connectionUrl);
            System.out.println("Database connection is successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Execute query SQL statement and return query result set
     *
     * @param sql SQL statement
     * @return result set object
     */

    public static ResultSet executeQuery(String sql) {
        buildConnection();
        try {
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Need to manually close the connection at the call site
        return rs;
    }

    /**
     * Execute update statement
     *
     * @param sql update statement
     * @return 0 is not updated, otherwise the update is successful
     */

    public static int executeUpdate(String sql) {
        buildConnection();
        int r = 0;
        try {
            pre = conn.prepareStatement(sql);
            r = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        closeConnection();
        return r;
    }

    //Execute a single statement and return the result object
    public static Object executeSingleQuery(String singleSql) {
        buildConnection();
        Object obj = null;
        try {
            pre = conn.prepareStatement(singleSql);
            rs = pre.executeQuery();
            if (rs.next()) {
                obj = rs.getObject(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        closeConnection();
        return obj;
    }


    /**
     * Close the connection, pay attention to the order of closing, the last used is the first to close
     */
    public static void closeConnection() {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (pre != null && !pre.isClosed()) {
                pre.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            System.out.println("Database connection is closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        buildConnection();
    }
}
