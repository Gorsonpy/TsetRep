package idi.Gorsonpy.function.Methods;

import idi.Gorsonpy.utils.ConnectMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteMethods {
    //根据商品的编号删除商品信息
    public static void deleteOrders(long id) {
        Connection conn = null;
        PreparedStatement ps1, ps2;
        try {
            conn = ConnectMySQL.getConnection();
            conn.setAutoCommit(false);
            String sql1 = "delete from orders where id=?";
            ps1 = conn.prepareStatement(sql1);
            ps1.setLong(1, id);
            String sql2 = "delete from good_order where order_id=?";
            ps2 = conn.prepareStatement(sql2);
            ps2.setLong(1, id);
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
        }
    }

    //传入订单编号和商品编号，去掉该订单里的该商品
    public static void deletePart(long order_Id, long good_Id) {
        Connection conn = null;
        PreparedStatement ps;
        try {
            conn = ConnectMySQL.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from good_order where order_id=? and good_id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, order_Id);
            ps.setLong(2, good_Id);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
