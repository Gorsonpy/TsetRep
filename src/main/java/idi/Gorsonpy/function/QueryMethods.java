package idi.Gorsonpy.function;

import idi.Gorsonpy.JavaBean.Basic;
import idi.Gorsonpy.JavaBean.Weather;
import idi.Gorsonpy.utils.ConnectMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryMethods {

    //根据给出的城市名称模糊查询出可能的城市信息
    public static ArrayList<Basic> queryCityInf(String cityName){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<Basic> basicArrayList = null;
        try{
            conn= ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            //聚合查询
            String sql = "select * from city where name like ?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,cityName);
            rs=ps.executeQuery();
            conn.commit();
            basicArrayList=new ArrayList<>();
            while(rs.next()){
                Basic basic =new Basic();
                basic.setCity_ID(rs.getString("city_id"));
                basic.setName(rs.getString("name"));
                basic.setLat(rs.getString("lat"));
                basic.setLon(rs.getString("lon"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            try {
                conn.rollback();
            }catch(SQLException e2){
                e2.printStackTrace();
            }
        }
        return basicArrayList;
    }
    //根据城市名称查询天气信息
    public static ArrayList<Weather> queryWeatherInfByName(String cityName){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<Weather> weatherArrayList = null;
        try{
            conn= ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            //聚合查询
            String sql = "select y.fxDate,y.tempMax,y.tempMin,y.textDay" +
                    "from city x"+
                    "inner join weather y"+
                    "on x.id=y.city_first_id"+
                    "where name=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,cityName);
            rs=ps.executeQuery();
            conn.commit();
            weatherArrayList=new ArrayList<>();
            while(rs.next()){
                Weather weather = new Weather();
                weather.setFxDate(rs.getString("fxDate"));
                weather.setTempMax(rs.getString("tempMax"));
                weather.setTempMin(rs.getString("tempMin"));
                weather.setTextDay(rs.getString("textDay"));
                weatherArrayList.add(weather);
            }
        }catch (SQLException e){
            e.printStackTrace();
            try {
                conn.rollback();
            }catch(SQLException e2){
                e2.printStackTrace();
            }
        }
        return weatherArrayList;
    }
}
