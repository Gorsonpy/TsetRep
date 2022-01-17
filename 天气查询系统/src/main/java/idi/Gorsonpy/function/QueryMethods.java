package idi.Gorsonpy.function;

import idi.Gorsonpy.JavaBean.Basic;
import idi.Gorsonpy.JavaBean.Weather;
import idi.Gorsonpy.JavaBean.Weather_With_Name;
import idi.Gorsonpy.utils.ConnectMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryMethods {

    //根据给出的城市名称模糊查询出可能的城市信息(分页)
    public ArrayList<Basic> queryCityInf(long pageSize, long index, String cityName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Basic> basicArrayList = null;
        try {
            conn = ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            //聚合查询
            String sql = "select * from city where name like ? limit ? offset ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + cityName + "%");
            ps.setLong(2, pageSize);
            ps.setLong(3, pageSize * (index - 1));
            rs = ps.executeQuery();
            conn.commit();
            basicArrayList = new ArrayList<>();
            while (rs.next()) {
                Basic basic = new Basic();
                basic.setCity_ID(rs.getString("city_id"));
                basic.setName(rs.getString("name"));
                basic.setLat(rs.getString("lat"));
                basic.setLon(rs.getString("lon"));
                basicArrayList.add(basic);
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
        return basicArrayList;
    }

    //根据城市编号查询天气信息(id为非主键id)
    public ArrayList<Weather> queryWeatherInfById(long pageSize, long index, String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Weather> weatherArrayList = null;
        try {
            conn = ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            //根据时间倒叙查询结果，可以显示离查询时间最近的日期天气情况
            String sql = "select fxDate,tempMax,tempMin,textDay " +
                    "from weather where city_second_id = ? order by fxDate desc limit ? offset ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setLong(2, pageSize);
            ps.setLong(3, pageSize * (index - 1));
            rs = ps.executeQuery();
            conn.commit();
            weatherArrayList = new ArrayList<>();
            while (rs.next()) {
                Weather weather = new Weather();
                weather.setFxDate(String.valueOf(rs.getDate("fxDate")));
                weather.setTempMax(rs.getString("tempMax"));
                weather.setTempMin(rs.getString("tempMin"));
                weather.setTextDay(rs.getString("textDay"));
                weatherArrayList.add(weather);
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
        return weatherArrayList;
    }

    //根据日期查询天气信息
    public ArrayList<Weather_With_Name> queryWeatherByDate(long pageSize, long index, String date) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Weather_With_Name> weatherArrayList = null;
        try {
            System.out.println(date);
            conn = ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            //连接查询使得表格同时具有城市的名字信息
            String sql = "select c.name name,w.fxDate,w.tempMax,w.tempMin,w.textDay " +
                    "from weather w " +
                    "inner join city c " +
                    "on w.city_second_id = c.city_id " +
                    "where cast(fxDate as char) = ? " +
                    "limit ? offset ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setLong(2, pageSize);
            ps.setLong(3, pageSize * (index - 1));
            rs = ps.executeQuery();
            conn.commit();
            weatherArrayList = new ArrayList<>();
            while (rs.next()) {
                Weather_With_Name weather = new Weather_With_Name();
                weather.setName(rs.getString("name"));
                weather.setFxDate(String.valueOf(rs.getDate("fxDate")));
                weather.setTempMax(rs.getString("tempMax"));
                weather.setTempMin(rs.getString("tempMin"));
                weather.setTextDay(rs.getString("textDay"));
                weatherArrayList.add(weather);
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
        return weatherArrayList;
    }

    //查询所有已经收录的城市id列表
    public ArrayList<String> queryAllCityId() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> idArrayList = null;
        try {
            conn = ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            String sql = "select city_id from city";
            ps = (PreparedStatement) conn.prepareStatement(sql);
            rs = ps.executeQuery();
            conn.commit();
            idArrayList = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("city_id");
                idArrayList.add(id);
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
        return idArrayList;
    }

    //根据城市id查询城市名称
    public String queryNameByCityId(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String cityName = null;
        try {
            conn = ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            String sql = "select name from city where city_id = ?";
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            conn.commit();
            if (rs.next()) {
                cityName = rs.getString("name");
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
        return cityName;
    }
}
