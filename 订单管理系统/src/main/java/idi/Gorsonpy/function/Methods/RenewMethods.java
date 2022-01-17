package idi.Gorsonpy.function.Methods;

import idi.Gorsonpy.utils.ConnectMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class RenewMethods {
    //更新订单：(订单编号，下单时间，订单内含有商品的编号,购买的数量）
    public static void renewOrder(Long order_Id, Timestamp ts, long good_Id, long account) {
        Connection conn = null;
        PreparedStatement ps1 = null, ps2 = null;
        try {
            conn = ConnectMySQL.getConnection();
            conn.setAutoCommit(false);
            //更新订单表中的时间语句
            String sql1 = "insert into orders(id,time) values(?,?) " +
                    "on duplicate key update time=?";
            //更新对应关系表语句(order_id和good_id建立唯一索引)
            String sql2 = "insert into good_order(order_id,good_id,account) " +
                    "values(?,?,?) " +
                    "on duplicate key update good_id=?,account=?";
            ps1 = conn.prepareStatement(sql1);
            ps2 = conn.prepareStatement(sql2);
            ps1.setLong(1, order_Id);
            ps1.setTimestamp(2, ts);
            ps1.setTimestamp(3, ts);
            ps2.setLong(1, order_Id);
            ps2.setLong(2, good_Id);
            ps2.setLong(3, account);
            ps2.setLong(4, good_Id);
            ps2.setLong(5, account);
            ps1.executeUpdate();
            ps2.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectMySQL.close(ps1);
            ConnectMySQL.close(ps2);
            ConnectMySQL.close(conn);
        }
    }

    public static void renewGoods(String name, double price) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectMySQL.getConnection();
            conn.setAutoCommit(false);
            //SQL语句，有数据更新，无数据插入
            String sql = "insert into goods(name,price) values(?,?) " +
                    "on duplicate key update price=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setDouble(3, price);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectMySQL.close(ps);
            ConnectMySQL.close(conn);
        }
    }
}
