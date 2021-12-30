package idi.Gorsonpy.function.Methods;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import idi.Gorsonpy.JavaBean.Good;
import idi.Gorsonpy.JavaBean.J_Order;
import idi.Gorsonpy.utils.ConnectMySQL;
import idi.Gorsonpy.utils.GetHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class QueryMethods {
    //模糊查询版本
    public static ArrayList<J_Order> findOrderInfById(long id) {
        ArrayList<J_Order> orderArrayList = null;
        Connection conn = null;
        PreparedStatement ps = null;//采用PreparedStatement以获取更好的性能和安全性
        ResultSet rs = null;
        try {
            conn = ConnectMySQL.getConnection();
            //订单编号可以不一定准确，支持模糊查询，cast转id long类型为字符类型
            String sql = "select * from orders where cast(id as char) like ?";//查询订单表
            ps = conn.prepareStatement(sql);
            ps = conn.prepareStatement(sql);
            //long类型id字段+字符串类型"%"可以自动转为字符串类型
            ps.setString(1, "%" + id + "%");
            conn.setAutoCommit(false);//关闭自动提交
            rs = ps.executeQuery();
            conn.commit();//手动提交事务
            orderArrayList = new ArrayList<>();
            while (rs.next()) {
                J_Order aOrder;
                aOrder = GetHelper.getJ_Order(rs);
                orderArrayList.add(aOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();//如果遇到异常就回滚
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectMySQL.close(rs, ps, conn);//关闭所有的资源
        }
        return orderArrayList;
    }

    //精确查询版本
    public static J_Order exactFindOrderInfById(long id) {
        J_Order aOrder = null;
        Connection conn = null;
        PreparedStatement ps = null;//采用PreparedStatement以获取更好的性能和安全性
        ResultSet rs = null;
        try {
            conn = ConnectMySQL.getConnection();
            //订单编号可以不一定准确，支持模糊查询，cast转id long类型为字符类型
            String sql = "select * from orders where id=?";//查询订单表
            ps = conn.prepareStatement(sql);
            ps = conn.prepareStatement(sql);
            //long类型id字段+字符串类型"%"可以自动转为字符串类型
            ps.setLong(1, id);
            conn.setAutoCommit(false);//关闭自动提交
            rs = ps.executeQuery();
            conn.commit();//手动提交事务
            if (rs.next()) {
                aOrder = new J_Order();
                aOrder = GetHelper.getJ_Order(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();//如果遇到异常就回滚
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectMySQL.close(rs, ps, conn);//关闭所有的资源
        }
        return aOrder;
    }

    //按订单编号查找订单内含商品的信息
    public static ArrayList<Good> findGoodInfById(long id) {
        ArrayList<Good> goodArrayList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectMySQL.getConnection();
            //使用内连接方式的多表连接查询
            String sql = "select x.good_id,g.name good_name,g.price good_price,x.account" +
                    " from good_order x" +
                    " inner join goods g" +
                    " on x.good_id=g.id" +
                    " where order_id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            //rs1储存的是商品编号和购买数量
            conn.setAutoCommit(false);
            rs = ps.executeQuery();
            conn.commit();
            goodArrayList = new ArrayList<>();
            while (rs.next()) {
                Good aGood = GetHelper.getGood(rs);
                goodArrayList.add(aGood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectMySQL.close(rs, ps, conn);
        }
        return goodArrayList;
    }

    public static ArrayList<J_Order> findOrderByTime(String time) {
        ArrayList<J_Order> orderArrayList = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectMySQL.getConnection();
            //把timestamp类型用cast改成字符串类型来实现模糊查询
            String sql = "select * from orders where cast(time as char) like ?";
            ps = conn.prepareStatement(sql);
            //"%"表示后面有任意字符
            ps.setString(1, time + "%");
            conn.setAutoCommit(false);
            rs = ps.executeQuery();
            conn.commit();
            orderArrayList = new ArrayList<>();
            while (rs.next()) {
                J_Order aOrder;
                aOrder = GetHelper.getJ_Order(rs);
                orderArrayList.add(aOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectMySQL.close(rs, ps, conn);
        }
        return orderArrayList;
    }

    public static ArrayList<Long> findOrderIdByGood(String s) {
        ArrayList<Long> orderIdArrayList = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectMySQL.getConnection();
            String sql = "select g.id,x.order_id" +
                    " from goods g" +
                    " inner join good_order x" +
                    " on g.id=x.good_id" +
                    " where name like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + s + "%");
            conn.setAutoCommit(false);
            rs = ps.executeQuery();
            conn.commit();
            orderIdArrayList = new ArrayList<>();
            while (rs.next()) {
                orderIdArrayList.add(rs.getLong("order_id"));
            }
            return orderIdArrayList;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectMySQL.close(rs, ps, conn);
        }
        return orderIdArrayList;
    }

    //显示对应页数据库的商品信息方法
    public static ArrayList<Good> showGoods(long pageSize, long pageIndex) {
        ArrayList<Good> goodArrayList = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectMySQL.getConnection();
            String sql = "select * from goods limit ? offset ?";
            /*
            LIMIT总是设定为pageSize；
O           OFFSET计算公式为pageSize * (pageIndex - 1)
             */
            ps = conn.prepareStatement(sql);
            ps.setLong(1, pageSize);
            ps.setLong(2, pageSize * (pageIndex - 1));
            conn.setAutoCommit(false);
            rs = ps.executeQuery();
            conn.commit();
            goodArrayList = new ArrayList<>();
            while (rs.next()) {
                Good aGood = new Good();
                aGood.setId(rs.getLong("id"));
                aGood.setName(rs.getString("name"));
                aGood.setPrice(rs.getDouble("price"));
                goodArrayList.add(aGood);
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectMySQL.close(rs, ps, conn);
        }
        return goodArrayList;
    }

    public static ArrayList<Good> findGoodsByName(String name) {
        ArrayList<Good> goodArrayList = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ConnectMySQL.getConnection();
            String sql = "select * from goods where name like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            conn.setAutoCommit(false);
            rs = ps.executeQuery();
            conn.commit();
            goodArrayList = new ArrayList<>();
            while (rs.next()) {
                String aName = rs.getString("name");
                long aId = rs.getLong("id");
                double aPrice = rs.getDouble("price");
                Good aGood = new Good(aId, aPrice, aName, 0);
                goodArrayList.add(aGood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectMySQL.close(rs, ps, conn);
        }
        return goodArrayList;
    }

    //分页显示订单编号和下单时间，一并显示订单内含商品信息
    public static void showOrders(long pageSize, long pageIndex) {
        Connection conn = null;
        PreparedStatement ps = null;
        //rs1存放查询订单表信息，rs2用于存放查询对应关系表获取商品购买数量
        ResultSet rs = null;
        try {
            conn = ConnectMySQL.getConnection();
            String sql = "select * from orders " +
                    "limit ? offset ? ";
            /*
            LIMIT总是设定为pageSize；
O           OFFSET计算公式为pageSize * (pageIndex - 1)
             */
            ps = conn.prepareStatement(sql);
            ps.setLong(1, pageSize);
            ps.setLong(2, pageSize * (pageIndex - 1));
            conn.setAutoCommit(false);
            rs = ps.executeQuery();
            conn.commit();
            while (rs.next()) {
                J_Order aOrder = new J_Order();
                aOrder.setId(rs.getLong("id"));
                aOrder.setTime(rs.getTimestamp("time"));
                System.out.println(JSON.toJSONString(aOrder));
                //复用按照订单编号查找商品信息的方法
                ArrayList<Good> goodArrayList = findGoodInfById(aOrder.getId());
                System.out.println("----------该订单内含有商品的信息如下：----------");
                for (Good good : goodArrayList) {
                    //补充购买数量信息
                    System.out.println(JSON.toJSONString(good));
                }
                System.out.println("------------------------------------------\n");
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectMySQL.close(rs, ps, conn);
        }
    }

    //询问用户商品信息分页并显示，便于多处复用
    public static void queryGoodDiv() {
        Scanner in = new Scanner(System.in);
        long num = CountMethods.countGood();
        boolean tag = true;//控制多次分页查看
        System.out.println("----------当前数据库已经有" + num + "条商品记录----------");
        System.out.println("Tip:仅实际显示商品编号、名称和价格,'购买数量'一律显示为0");
        System.out.println("----------支持分页查看，请输入每页显示多少条信息？----------");
        long pageSize = in.nextLong();
        long total = (int) Math.ceil((double) num / pageSize);
        System.out.println("----------共有" + total + "页商品信息----------");
        while (tag) {
            System.out.println("----------请输入你要查看第几页(从1开始)?----------");
            long pageIndex = in.nextLong();
            ArrayList<Good> goodArrayList = QueryMethods.showGoods(pageSize, pageIndex);
            System.out.println("----------当前第" + pageIndex + "/" + total + "页----------");
            System.out.println("----------------------------------------");
            for (Good good : goodArrayList) {
                System.out.println(JSON.toJSONString(good));
            }
            System.out.println("----------------------------------------\n");
            System.out.println("----------还要查看其他页吗(y or n)？----------");
            String x = in.next();
            if (!x.equals("y"))
                tag = false;
        }
    }

    public static void queryOrderDiv() {
        Scanner in = new Scanner(System.in);
        long num = CountMethods.countOrders();
        boolean tag = true;//控制多次分页查看
        System.out.println("----------当前数据库已经有" + num + "条订单记录----------");
        System.out.println("----------支持分页查看，请输入每页显示多少条信息？----------");
        long pageSize = in.nextLong();
        long total = (int) Math.ceil((double) num / pageSize);
        System.out.println("----------共有" + total + "页订单信息----------");
        while (tag) {
            System.out.println("----------请输入你要查看第几页(从1开始)?----------");
            long pageIndex = in.nextLong();
            System.out.println("----------当前第" + pageIndex + "/" + total + "页----------");
            System.out.println("----------------------------------------");
            QueryMethods.showOrders(pageSize, pageIndex);
            System.out.println("----------------------------------------------\n");
            System.out.println("----------还要查看其他页吗(y or n)？----------");
            String x = in.next();
            if (!x.equals("y"))
                tag = false;
        }
    }
}
