package idi.Gorsonpy.function;

import idi.Gorsonpy.JavaBean.Basic;
import idi.Gorsonpy.JavaBean.Weather;
import idi.Gorsonpy.utils.ConnectMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountMethods {
    //查看可能的城市数量
    public long  countCityByName(String cityName){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        long num = 0;
        try{
            conn= ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            //聚合查询
            String sql = "select count(*) num from city where name like ?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,"%"+cityName+"%");
            rs=ps.executeQuery();
            conn.commit();
            if(rs.next()){
                num = rs.getLong("num");
            }
        }catch (SQLException e){
            e.printStackTrace();
            try {
                conn.rollback();
            }catch(SQLException e2){
                e2.printStackTrace();
            }
        }
        return num;
    }

    //查询数据库已有对应编号城市的天气信息天数
    public long countWeatherInfById(String id){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        long num = 0;
        try{
            conn= ConnectMysql.getConnection();
            conn.setAutoCommit(false);
            //根据时间倒叙查询结果，可以显示离查询时间最近的日期天气情况
            String sql = "select count(*) num " +
                    "from weather where city_second_id = ?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,id);
            rs=ps.executeQuery();
            conn.commit();
            if(rs.next()){
                num = rs.getLong("num");
            }
        }catch (SQLException e){
            e.printStackTrace();
            try {
                conn.rollback();
            }catch(SQLException e2){
                e2.printStackTrace();
            }
        }
        return num;
    }
}
