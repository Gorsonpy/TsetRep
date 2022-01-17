package idi.Gorsonpy.function.Func;

import idi.Gorsonpy.JavaBean.User;
import idi.Gorsonpy.utils.ConnectMySQL;
import idi.Gorsonpy.utils.GetHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
用户注册、登录功能类
 */
public class Sign_LoginFunc {

    //用户注册账号方法
    public void sign() {
        Connection conn = null;
        PreparedStatement ps = null;//采用PreparedStatement以获取更好的性能和安全性
        /*
        询问管理员/用户名/密码
         */
        boolean admin = QueryAdmin();
        String username = QueryUsername();
        String password = QueryPassword();
        try {
            conn = ConnectMySQL.getConnection();
            String sql = "insert into users(username,password,is_admin) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setBoolean(3, admin);
            ps.executeUpdate();
            System.out.println("-----------恭喜，你的账号注册成功！-----------");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectMySQL.close(ps);
            ConnectMySQL.close(conn);
        }
    }

    //用户登录的方法
    public static User Login(String username, String password) {

        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;//采用PreparedStatement以获取更好的性能和安全性
        ResultSet rs = null;
        String sql = "select * from users where username=? and password=?";
        try {
            conn = ConnectMySQL.getConnection();//获取数据库的连接
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);//填充sql语句，下同，参数索引从1开始
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user = GetHelper.getUser(rs);
            }//如果表中有该用户的数据，那么rs不为null，rs的数据传入user返回
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            /*
        完成数据库的操作后要及时关闭资源
         */
            ConnectMySQL.close(rs);
            ConnectMySQL.close(ps);
            ConnectMySQL.close(conn);
        }
        return user;//如果表中没有该用户的数据，返回null
    }

    //检查用户用户名是否已经被使用过
    public boolean isUsed(String username) {

        Connection conn = null;
        PreparedStatement ps = null;//采用PreparedStatement以获取更好的性能和安全性
        ResultSet rs = null;
        int num = 0;
        try {
            conn = ConnectMySQL.getConnection();
            //仅仅只是查询这条数据是否存在，用count统计数字更好，limit 1增加效率
            String sql = "select count(*) from users where username=? limit 1";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            /*
            一个结果集最初将游标定位在第一行的前面
            count查出来的数字是储存在第一行,所以这里要先把游标后移一位，否则会异常
             */
            rs.next();
            num = rs.getInt(1);//这里使用列号查询，因为rs查询数量的本质也是形成一个一维表，查找第一列即可
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectMySQL.close(rs);
            ConnectMySQL.close(ps);
            ConnectMySQL.close(conn);
        }
        return num == 1;
    }

    //询问是否是管理员账号的方法
    public boolean QueryAdmin() {
        Scanner in = new Scanner(System.in);
        char c;
        String d;
        boolean admin = false;
        boolean flag = true;
        while (flag) {
            System.out.println("-----------你想创建一个什么类型的账号？-----------");
            System.out.println("-----------输入1：管理员账号(全部功能)-----------");
            System.out.println("-----------输入2：普通账号(仅查找功能)-----------");
            d = in.next();
            c = d.charAt(0);
            if (c == '1') {
                System.out.println("-----------管理员有更强力的功能，但是你需要通过考验-----------");
                System.out.println("-----------请回答下列问题：-----------");
                System.out.println("-----------我们的工作室名字是？-----------");
                String s;
                s = in.next();
                if (s.equals("西二在线") || s.equals("West2 Online")) {
                    flag = false;
                    admin = true;
                    System.out.println("-----------恭喜你通过考验，拥有了管理员资格！-----------");
                } else {
                    System.out.println("-----------你回答的一团糟，重开吧......-----------");
                }
            } else if (c == '2') {
                flag = false;
                System.out.println("-----------你是一个普通用户-----------");
            } else {
                System.out.println("-----------只能是1或者2噢！-----------");
            }
        }
        return admin;
    }

    //询问用户名的方法
    public String QueryUsername() {
        Scanner in = new Scanner(System.in);
        String s = null;
        boolean bool = true;//循环的控制变量
        while (bool) {
            System.out.println("请输入你注册要使用的用户名：");
            s = in.next();
            if (!isUsed(s))//如果该用户名还没被使用过
            {
                System.out.println("-----------恭喜，该用户名尚未被注册-----------");
                bool = false;
            } else {
                System.out.println("-----------抱歉，该用户名已经被使用了-----------");
            }
        }
        return s;
    }

    public String QueryPassword() {
        Scanner in = new Scanner(System.in);
        boolean tag = true;
        String s1 = null;
        while (tag) {
            System.out.println("-----------请设置密码-----------");
            System.out.println("-----------为了密码安全，必填字母数字及特殊字符，且以字母开头，8位以上-----------");
            //使用正则表达式验证密码强度是否合格
            String regex = "^(?![0-9]+$)(?![^0-9]+$)(?![a-zA-Z]+$)(?![^a-zA-Z]+$)(?![a-zA-Z0-9]+$)[a-zA-Z0-9\\S]{8,}$";
            s1 = in.next();
            if (s1.matches(regex)) {
                System.out.println("-----------请确认密码-----------");
                String s2;
                s2 = in.next();
                if (s1.equals(s2)) {
                    tag = false;
                } else //如果两次密码不一致要重新输入
                {
                    System.out.println("-----------两次输入密码不同！-----------");
                }
            } else {
                System.out.println("-----------密码强度过弱，请重新设置密码-----------");
            }
        }
        return s1;
    }
}
