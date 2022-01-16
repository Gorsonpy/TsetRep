package idi.Gorsonpy.userview;


import com.alibaba.fastjson.JSON;
import idi.Gorsonpy.JavaBean.Basic;
import idi.Gorsonpy.function.CountMethods;
import idi.Gorsonpy.function.QueryMethods;

import java.util.ArrayList;
import java.util.Scanner;

//查询功能界面
public class QueryView {
    public void QueryInit() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("----------***欢迎来到查询功能界面***----------");
            System.out.println("----------1.查询城市基本信息(名字、编号、经纬度)----------");
            System.out.println("----------2.查询天气情况----------");
            System.out.println("----------0.退出查询功能----------");
            String d;
            d = in.next();
            switch (d) {
                case "1": {
                    System.out.println("----------请输入要查询城市的名称----------");
                    in.nextLine();//提前吃掉回车
                    String cityName = in.nextLine();
                    long num = new CountMethods().countCityByName(cityName);
                    System.out.println("----------你可能要查找的城市信息有" + num + "条----------");
                    System.out.println("----------请输入一页要查看的信息数量----------");
                    long pageSize = in.nextLong();
                    long total = (int) Math.ceil((double) num / pageSize);//总页数
                    System.out.println("----------共有" + total + "页可能的城市信息----------");
                    boolean tag = true;
                    while (tag) {
                        System.out.println("----------请输入你要查看第几页(从1开始)?----------");
                        long pageIndex = in.nextLong();
                        ArrayList<Basic> basicArrayList = new QueryMethods().queryCityInf(pageSize, pageIndex, cityName);
                        System.out.println("----------当前第" + pageIndex + "/" + total + "页----------");
                        System.out.println("----------------------------------------");
                        for (Basic basic : basicArrayList) {
                            System.out.println(JSON.toJSONString(basic));
                        }
                        System.out.println("----------------------------------------\n");
                        System.out.println("----------还要查看其他页吗(y or n)？----------");
                        String x = in.next();
                        if (!x.equals("y"))
                            tag = false;
                    }
                    break;
                }
                case "2": {
                    
                    break;
                }
                case "0": {
                    return;
                }
                default: {
                    System.out.println("----------非法输入！----------");
                }
            }
        }
    }
}
