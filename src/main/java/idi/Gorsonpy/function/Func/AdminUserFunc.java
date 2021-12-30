package idi.Gorsonpy.function.Func;

import com.alibaba.fastjson.JSON;
import idi.Gorsonpy.JavaBean.Good;
import idi.Gorsonpy.function.Methods.CountMethods;
import idi.Gorsonpy.function.Methods.DeleteMethods;
import idi.Gorsonpy.function.Methods.QueryMethods;
import idi.Gorsonpy.function.Methods.RenewMethods;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/*
管理员的功能,继承了UserFunc中的查询功能
 */
public class AdminUserFunc extends UserFunc {
    //更新订单方法
    public void renewInf() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("----------***更新功能界面***----------");
            System.out.println("----------1.更新订单信息----------");
            System.out.println("----------2.更新商品信息----------");
            System.out.println("----------0.退出----------");
            System.out.println("Tip:如果信息已经存在，那么会更新原来的信息;");
            System.out.println("如果信息不存在，会插入新的信息");
            String d = in.next();
            switch (d) {
                case "1": {
                    System.out.println("----------正在更新订单----------");
                    //用户事先不知道编号，先输出信息
                    System.out.println("----------将先为你连接查询功能显示已有的订单信息，请寻找你要更新订单的编号----------");
                    QueryMethods.queryOrderDiv();
                    System.out.println("----------请输入要更新订单的编号(如果要插入新订单则自行输入新订单编号)----------");
                    long order_Id = in.nextLong();
                    System.out.println("----------输入该订单时间(yyyy-MM-dd HH:mm:ss)----------");
                    String order_Time;
                    in.nextLine();//nextLine会把读编号敲得回车吃进去，提前吃掉回车确保读到时间
                    order_Time = in.nextLine();
                    //指定时间格式
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    //字符串转为LocalDateTime
                    LocalDateTime localDateTime = LocalDateTime.from(dtf.parse(order_Time));
                    //再转化为Timestamp
                    Timestamp ts = Timestamp.valueOf(localDateTime);
                    while (true) {
                        while (true) {
                            System.out.println("----------请输入订单内含有的商品名称----------");
                            String name = in.next();
                            //需要用户输入商品名称先查询商品编号
                            ArrayList<Good> goodArrayList = QueryMethods.findGoodsByName(name);
                            System.out.println("----------你可能想选择的商品如下：----------");
                            for (Good good : goodArrayList) {
                                System.out.println(JSON.toJSONString(good));
                            }
                            System.out.println("----------是否找到你想要的商品编号(y or n)？----------");
                            String s = in.next();
                            if (s.equals("y")) {
                                break;
                            } else {
                                System.out.println("----------请重新输入名称查找----------");
                            }
                        }
                        System.out.println("----------请找到订单内含商品的编号，并输入(单个）----------");
                        long good_Id = in.nextLong();
                        System.out.println("----------请输入该商品购买的数量----------");
                        long account = in.nextLong();
                        System.out.println("----------正在更新记录----------");
                        RenewMethods.renewOrder(order_Id, ts, good_Id, account);
                        System.out.println("----------更新成功！----------");
                        System.out.println("----------还要往该订单添加其他商品吗(y or n)？----------");
                        String o = in.next();
                        if (!o.equals("y"))
                            break;
                    }
                    break;
                }
                case "2": {
                    long num1 = CountMethods.countGood();
                    //提示已有的商品记录数量，以便知道是新插入一个记录还是更新一个记录
                    System.out.println("----------当前已经存在" + num1 + "条商品记录----------");
                    System.out.println("----------请输入商品名称----------");
                    String name = in.next();
                    System.out.println("----------请输入商品价格----------");
                    double price = in.nextDouble();
                    //规范用户错误输入
                    while (price - 0 < 0) {
                        System.out.println("----------商品价格不能为负数！请重新输入----------");
                        price = in.nextDouble();
                    }
                    System.out.println("----------正在更新数据......----------");
                    RenewMethods.renewGoods(name, price);
                    long num2 = CountMethods.countGood();
                    //根据前后数量的不同给予不同的提示
                    if (num1 == num2) {
                        System.out.println("----------修改了一条已有记录！当前存在" + num2 + "条商品记录----------");
                    } else {
                        System.out.println("----------新插入了一条记录！当前存在" + num2 + "条商品记录----------");
                    }
                }
                case "0": {
                    System.out.println("----------更新完毕,谢谢使用！----------");
                    return;
                }
                default: {
                    System.out.println("----------非法输入！----------");
                    break;
                }
            }
        }
    }

    //删除订单功能
    public void deleteInf() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("----------***删除功能界面***----------");
            System.out.println("----------1.删除整个订单----------");
            System.out.println("----------2.删除订单部分内容----------");
            System.out.println("----------0.退出删除功能----------");
            String d = in.next();
            switch (d) {
                case "1": {
                    System.out.println("----------正在为你连接查看已有的订单----------");
                    QueryMethods.queryOrderDiv();
                    System.out.println("----------请输入你要删除的订单的编号----------");
                    long id = in.nextLong();
                    DeleteMethods.deleteOrders(id);
                    System.out.println("----------删除成功！----------");
                    long num = CountMethods.countOrders();
                    System.out.println("----------现在有" + num + "条订单----------");
                    break;
                }
                case "2": {
                    System.out.println("----------正在为你连接查看已有的订单----------");
                    QueryMethods.queryOrderDiv();
                    System.out.println("----------请输入你要删除的订单的编号----------");
                    long order_Id = in.nextLong();
                    while (true) {
                        System.out.println("----------请输入你要删除该订单内商品的编号(单个)----------");
                        long good_Id = in.nextLong();
                        DeleteMethods.deletePart(order_Id, good_Id);
                        System.out.println("----------删除成功！----------");
                        System.out.println("----------该订单还有要删除的商品吗(y or n)？----------");
                        String x = in.next();
                        if (!x.equals("y")) {
                            break;
                        }
                    }
                    break;
                }
                case "0": {
                    System.out.println("----------删除完毕，谢谢使用！----------");
                    return;
                }
                default: {
                    System.out.println("----------非法输入！----------");
                    break;
                }
            }
        }
    }
}
