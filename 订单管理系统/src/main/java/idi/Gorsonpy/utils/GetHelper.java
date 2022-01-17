package idi.Gorsonpy.utils;

import idi.Gorsonpy.JavaBean.Good;
import idi.Gorsonpy.JavaBean.J_Order;
import idi.Gorsonpy.JavaBean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class GetHelper {
    //封装从resultSet获取各类对象信息的方法
    public static J_Order getJ_Order(ResultSet rs) {
        J_Order aOrder = null;
        try {
            aOrder = new J_Order();
            long aId = rs.getLong("id");
            Timestamp aDate = rs.getTimestamp("time");//存放查询出的订单时间信息
            aOrder.setId(aId);
            aOrder.setTime(aDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aOrder;
    }

    public static Good getGood(ResultSet rs) {
        Good good = null;
        try {
            long aGood_id = rs.getLong("good_id");
            String aName = rs.getString("good_name");
            double aPrice = rs.getDouble("good_price");
            long aAccount = rs.getLong("account");
            good = new Good(aGood_id, aPrice, aName, aAccount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return good;
    }

    public static User getUser(ResultSet rs) {
        User user = null;
        try {
            user = new User();
            int id = rs.getInt("id");
            user.setId(id);
            String uname = rs.getString("username");
            user.setUsername(uname);
            String pw = rs.getString("password");
            user.setPassword(pw);
            boolean isAdmin = rs.getBoolean("is_admin");
            user.setAdmin(isAdmin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
