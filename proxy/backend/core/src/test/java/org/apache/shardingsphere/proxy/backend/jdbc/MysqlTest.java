package org.apache.shardingsphere.proxy.backend.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MysqlTest {

    static final String DB_URL = "jdbc:mysql://127.0.0.1:3307/sharding_db";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement statement = conn.createStatement();
            String sql = "";

            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
        }


    }

    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connecting to mysql ...");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
