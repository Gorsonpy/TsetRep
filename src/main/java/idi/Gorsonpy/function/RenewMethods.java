package idi.Gorsonpy.function;

import idi.Gorsonpy.JavaBean.Basic;
import idi.Gorsonpy.utils.ConnectMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RenewMethods {
    //更新城市基本信息方法
    public  void renewBasicInf(Basic basic) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            //城市表更改
            String sql = "insert into city(city_id,name,lat,lon) values(?,?,?,?) " +
                    "on duplicate key update city_id = ?,name = ?,lat = ?,lon = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, basic.getCity_ID());
            ps.setString(2, basic.getName());
            ps.setString(3, basic.getLat());
            ps.setString(4, basic.getLon());
            ps.setString(5, basic.getCity_ID());
            ps.setString(6, basic.getName());
            ps.setString(7, basic.getLat());
            ps.setString(8, basic.getLon());
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            ConnectMysql.close(ps);
            ConnectMysql.close(conn);
        }
    }
}
