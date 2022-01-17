package idi.Gorsonpy.utils;

import java.sql.*;

public class ConnectMysql {
    //一个工具类
    public static Connection getConnection() {
        Connection conn = null;//初始化Connection类
        try {
            //初始化驱动类com.mysql.jdbc.Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "root";
            String password = "aaa24331277";//不要拨打哦
            String DatabaseName = "cityweather";
            //最后一项表示采用utf8编码（支持中文）
            String url = "jdbc:mysql://127.0.0.1:3306/" + DatabaseName + "?characterEncoding=UTF-8";
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;//返回一个连接好的Connection类
    }

    /*
    下面封装了Connection，ResultSet等关闭方法,关闭的时候要注意遵守从内向外的原则
     */
    public static void close(PreparedStatement ps) {
        if (ps != null) {                        //避免出现空指针异常
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //把所有的资源都关闭
    public static void close(ResultSet rs, PreparedStatement ps, Connection co) {
        close(rs);
        close(ps);
        close(co);
    }
}
