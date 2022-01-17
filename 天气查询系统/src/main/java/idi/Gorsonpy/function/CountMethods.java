package idi.Gorsonpy.function;

import idi.Gorsonpy.utils.ConnectMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountMethods {
    //查看可能的城市数量
    public long countCityByName(String cityName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long num = 0;
        try {
            conn = ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            String sql = "select count(*) num from city where name like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + cityName + "%");
            rs = ps.executeQuery();
            conn.commit();
            if (rs.next()) {
                num = rs.getLong("num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            ConnectMysql.close(rs, ps, conn);
        }
        return num;
    }

    //获取数据库对应编号城市的天气信息天数
    public long countWeatherInfById(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long num = 0;
        try {
            conn = ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            String sql = "select count(*) num " +
                    "from weather where city_second_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            conn.commit();
            if (rs.next()) {
                num = rs.getLong("num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            ConnectMysql.close(rs, ps, conn);
        }
        return num;
    }

    //获取给定日期天气信息数量
    public long countWeatherInfByDate(String date) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long num = 0;
        try {
            conn = ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            String sql = "select count(*) num " +
                    "from weather where cast(fxDate as char) = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            rs = ps.executeQuery();
            conn.commit();
            if (rs.next()) {
                num = rs.getLong("num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            ConnectMysql.close(rs, ps, conn);
        }
        return num;
    }

    //计算已经收录的城市数量
    public long countAllCityNum() {
        long num = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            String sql = "select count(*) num " +
                    "from city";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            conn.commit();
            if (rs.next()) {
                num = rs.getLong("num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            ConnectMysql.close(rs, ps, conn);
        }
        return num;
    }
}
