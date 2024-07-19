package org.apache.shardingsphere.proxy.backend.jdbc;

import java.sql.*;

/**
 * 抓包过滤
 * ip.dst==10.19.32.89
 */
public class PostgresTest {

    //bjirm - postgres
//    static final String DB_URL = "jdbc:postgresql://10.19.87.13:3307/app_bj_db";
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:3307/sc_pg_db";
    static final String USER = "user_app_sc1";
    static final String PASS = "password_app1";

    static final String bj_sql = "SELECT  * FROM shj_work.cu_stockright_bd_info";
    static final String sc_sql = "SELECT  *  FROM test.xyz_facility a left join sm.aas_login_log b on a.name = b.name order by a.name , b.name  limit 100 ";
    static final String sc_sql2 = "SELECT  *  FROM test.xyz_facility a  limit 100 ";
    static final String sc_sql3 = "CREATE TABLE test.xyz_facility_000 (LIKE test.xyz_facility INCLUDING ALL)";
    static final String sc_sql4 = "insert  into test.xyz_facility_000 select * from test.xyz_facility";

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();

            System.out.println(" >>>  conn  ok");

            Statement statement = conn.createStatement();
            String sql = sc_sql;
            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();
            // 遍历结果集并处理数据
            int line = 0;
            while (resultSet.next()) {
                line++;
                ResultSetMetaData metaData = resultSet.getMetaData();

                int cols = metaData.getColumnCount();
                if (line == 1) {
                    for (int i = 1; i <= cols; i++) {
                        System.out.print(metaData.getColumnName(i));
                        System.out.print(" ");
                    }
                    System.out.println("-----");
                }


                for (int i = 1; i <= cols; i++) {
                    System.out.print(resultSet.getObject(i));
                    System.out.print(" ");
                }
                System.out.println("-----");
            }



           /* while (resultSet.next()) {
                String name = (String) resultSet.getObject(1);
                int age = resultSet.getInt("x");

                System.out.println("Name: " + name + ", Age: " + age);
            }*/

            System.out.println(" <<<<<< execute  ok --  sql =" + sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }


    }

    private static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        System.out.println("Connecting to postgresql ...");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
