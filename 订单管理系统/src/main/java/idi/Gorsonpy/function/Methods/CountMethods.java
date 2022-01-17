package idi.Gorsonpy.function.Methods;

import idi.Gorsonpy.utils.ConnectMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountMethods {
    //统计当前已有的商品数量
    public static long countGood() {
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        long num = 0;
        try {
            conn = ConnectMySQL.getConnection();
            String sql = "select count(*) num from goods";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                num = rs.getLong("num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    //计算当前已有的订单数量
    public static long countOrders() {
        Connection conn;
        PreparedStatement ps;
        ResultSet rs;
        long num = 0;
        try {
            conn = ConnectMySQL.getConnection();
            String sql = "select count(*) num from orders";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                num = rs.getLong("num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }
}
