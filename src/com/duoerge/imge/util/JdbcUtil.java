package com.duoerge.imge.util;

import java.sql.*;

/**
 * @ClassName : JdbcUtil  //类名
 * @Description : 数据库工具类  //描述
 * @Author : Program_Dog  //作者
 * @Date: 2020-09-16 09:39  //时间
 */
public class JdbcUtil {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/db16";
    private static final String USER = "root";
    private static final String PWD = "z123456";
    private static Connection connection = null;

//    连接数据库
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USER,PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    关闭数据库连接
    public static void close(ResultSet rs, PreparedStatement ps,Connection conn){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (ps != null){
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
