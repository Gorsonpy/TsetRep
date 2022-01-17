package idi.Gorsonpy.function.Func;

import com.alibaba.fastjson.JSON;
import idi.Gorsonpy.JavaBean.Good;
import idi.Gorsonpy.JavaBean.J_Order;
import idi.Gorsonpy.function.Methods.QueryMethods;

import java.util.ArrayList;
import java.util.Scanner;

/*
公共功能
 */
public class UserFunc {


    public void queryOrders() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("-----------***查询功能界面***-----------");
            System.out.println("-----------你要做什么呢？-----------");
            System.out.println("-----------1.按订单编号查询-----------");
            System.out.println("-----------2.按下单时间查询-----------");
            System.out.println("-----------3.按订单内含商品名称查询-----------");
            System.out.println("-----------4.查看已有的商品信息-----------");
            System.out.println("-----------5.查看已有的订单信息-----------");
            System.out.println("-----------0.退出查询功能-----------");
            String d;
            d = in.next();
            switch (d) {
                case "1": {
                    System.out.println("----------请输入查询订单编号----------");
                    long id = in.nextLong();
                    ArrayList<J_Order> orderArrayList = QueryMethods.findOrderInfById(id);
                    if (orderArrayList.isEmpty()) {
                        System.out.println("----------没有找到可能符合的订单......----------");
                        System.out.println("----------请检查订单编号----------");
                    } else {
                        System.out.println("----------可能符合的订单如下----------");
                        //使用JSON把JAVA对象转化为JSON字符串
                        for (J_Order o : orderArrayList) {
                            System.out.println(JSON.toJSONString(o));
                            ArrayList<Good> goodArrayList;
                            System.out.println("----------该订单内含有的商品有：----------");
                            //复用订单编号查询的第二个步骤
                            goodArrayList = QueryMethods.findGoodInfById(o.getId());
                            for (Good good : goodArrayList) {
                                System.out.println(JSON.toJSONString(good));
                            }
                            System.out.println("----------------------------------------\n");
                        }
                    }
                    break;
                }
                case "2": {
                    //用户往往不记得订单确切的时间，所以不强求用户输入准确时间
                    System.out.println("----------请输入要查询订单的时间：----------");
                    System.out.println("Tip:你可以输入各种范围的时间，示例如下：");
                    System.out.println("如果你想查询2021年12月26日 20点47分30秒的订单：");
                    System.out.println("1.只记得年份，输入2021");
                    System.out.println("2.记得年份和月份，输入2021-12");
                    System.out.println("3.记得年月日，输入2021-12-26");
                    System.out.println("以此类推，格式为yyyy-mm-dd hh:mm:ss");
                    System.out.println("务必按照格式输入，否则可能会查找失败噢！");
                    String s = in.next();
                    ArrayList<J_Order> orderArrayList = QueryMethods.findOrderByTime(s);
                    if (orderArrayList.isEmpty()) {
                        System.out.println("----------无可能符合的订单，请检查时间----------");
                    } else {
                        System.out.println("----------可能符合的订单如下----------");
                        for (J_Order o : orderArrayList) {
                            System.out.println(JSON.toJSONString(o));
                            System.out.println("----------该订单内含有的商品有：----------");
                            //复用订单编号查询的第二个步骤
                            ArrayList<Good> goodArrayList = QueryMethods.findGoodInfById(o.getId());
                            for (Good good : goodArrayList) {
                                System.out.println(JSON.toJSONString(good));
                            }
                            System.out.println("----------------------------------------\n");
                        }
                    }
                    break;
                }
                case "3": {
                    System.out.println("请输入商品名称");
                    String s = in.next();
                    ArrayList<Long> orderIdArrayList = QueryMethods.findOrderIdByGood(s);
                    if (orderIdArrayList.isEmpty()) {
                        System.out.println("----------无可能符合的订单，请检查商品名称----------");
                    } else {
                        System.out.println("----------可能符合的订单如下----------");
                        for (long l : orderIdArrayList) {
                            J_Order aOrder = QueryMethods.exactFindOrderInfById(l);
                            System.out.println(JSON.toJSONString(aOrder));
                            System.out.println("----------该订单内含有的商品有：----------");
                            ArrayList<Good> goodArrayList = QueryMethods.findGoodInfById(l);
                            for (Good good : goodArrayList) {
                                System.out.println(JSON.toJSONString(good));
                            }
                            System.out.println("----------------------------------------\n");
                        }
                    }
                    break;
                }
                case "4": {
                    QueryMethods.queryGoodDiv();
                }
                break;
                case "5": {
                    QueryMethods.queryOrderDiv();
                    break;
                }
                case "0": {
                    System.out.println("----------查询完毕，谢谢使用!----------");
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