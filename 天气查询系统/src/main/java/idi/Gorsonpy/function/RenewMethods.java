package idi.Gorsonpy.function;

import idi.Gorsonpy.JavaBean.Basic;
import idi.Gorsonpy.JavaBean.Weather;
import idi.Gorsonpy.utils.ConnectMysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RenewMethods {
    //更新城市基本信息方法
    public void renewBasicInf(Basic basic) {
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


    //更新未来三天天气信息的方法
    public void renewWeatherInf(Weather weather, String city_id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            //天气表更改
            String sql = "insert into weather(fxDate,tempMax,tempMin,textDay,city_second_id) " +
                    "values(?, ?, ?, ?, ?) " +
                    "on duplicate key update fxDate = ?,tempMax = ?,tempMin = ?,textDay = ?";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(weather.getFxDate()));
            ps.setString(2, weather.getTempMax());
            ps.setString(3, weather.getTempMin());
            ps.setString(4, weather.getTextDay());
            ps.setString(5, city_id);
            ps.setDate(6, Date.valueOf(weather.getFxDate()));
            ps.setString(7, weather.getTempMax());
            ps.setString(8, weather.getTempMin());
            ps.setString(9, weather.getTextDay());
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
