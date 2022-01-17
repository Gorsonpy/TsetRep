package idi.Gorsonpy.userview;


import com.alibaba.fastjson.JSON;
import idi.Gorsonpy.JavaBean.Basic;
import idi.Gorsonpy.JavaBean.Weather;
import idi.Gorsonpy.JavaBean.Weather_With_Name;
import idi.Gorsonpy.function.CountMethods;
import idi.Gorsonpy.function.QueryMethods;
import idi.Gorsonpy.function.RenewMethods;
import idi.Gorsonpy.utils.ConnectAPI;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Scanner;

//查询功能界面
public class QueryView {
    public void findBasic(){
        Scanner in = new Scanner(System.in);
        System.out.println("----------请输入要查询城市的名称----------");
        String cityName = in.nextLine();
        long num = new CountMethods().countCityByName(cityName);
        //这里先做一次更新信息,避免用户要查询的那个城市实际存在但是还未收录
        if(num == 0) {
            System.out.println("----------该城市信息尚未收录，正在尝试为你获取该城市信息......----------");
            new RenewMethods().renewBasicInf(ConnectAPI.getBasicInf(cityName));
            num = new CountMethods().countCityByName(cityName);
            if(num>0)
                System.out.println("----------成功找到该城市信息，已经收录----------");
            else
                System.out.println("----------抱歉，获取失败----------");
        }
        System.out.println("----------可能是你要查找的城市共有" + num + "个----------");
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
    }
    public void findWeather(){
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("----------***查询天气***----------");
            System.out.println("----------1.按照城市名称查询----------");
            System.out.println("----------2.按照日期查询----------");
            System.out.println("----------0.退出查询天气----------");
            String s;
            s = in.next();
            switch(s){
                case "1":{
                    System.out.println("----------将为你导入查询城市编号功能......----------");
                    System.out.println("----------请找到你要查询城市的编号----------");
                    findBasic();
                    System.out.println("----------请输入你要查询城市的编号----------");
                    String city_Id = in.next();
                    String cityName = new QueryMethods().queryNameByCityId(city_Id);
                    long num = new CountMethods().countWeatherInfById(city_Id);

                    //如果当前还没有该城市的天气信息，就先尝试获取
                    if(num == 0){
                        System.out.println("----------抱歉，当前暂且还未收录任何关于该城市天气" +
                                "的信息----------");
                        System.out.println("----------正在尝试为你获取......----------");
                        ArrayList<Weather> weatherArrayList = ConnectAPI.getWeatherInf(city_Id);
                        for(Weather weather:weatherArrayList) {
                            new RenewMethods().renewWeatherInf(weather, city_Id);
                        }
                        num = new CountMethods().countWeatherInfById(city_Id);
                        if(num>0){
                            System.out.println("----------成功获取了该城市未来三天天气情况，" +
                                    "已收录----------");
                        }else{
                            System.out.println("----------抱歉，获取失败----------");
                        }
                    }
                    System.out.println("----------"+cityName+"已经储存有的天气信息有" + num + "天----------");
                    System.out.println("----------请输入一页要查看的信息数量----------");
                    long pageSize = in.nextLong();
                    long total = (int) Math.ceil((double) num / pageSize);//总页数
                    System.out.println("----------"+cityName+"共有" + total + "页天气信息----------");
                    boolean tag = true;
                    while (tag) {
                        System.out.println("----------请输入你要查看第几页(从1开始,优先显示最近日期)?----------");
                        long pageIndex = in.nextLong();
                        ArrayList<Weather> weatherArrayList = new QueryMethods().
                                queryWeatherInfById(pageSize, pageIndex, city_Id);
                        System.out.println("----------当前第" + pageIndex + "/" + total + "页----------");
                        System.out.println("----------------------------------------");
                        for (Weather weather : weatherArrayList) {
                            System.out.println(JSON.toJSONString(weather));
                        }
                        System.out.println("----------------------------------------\n");
                        System.out.println("----------还要查看其他页吗(y or n)？----------");
                        String x = in.next();
                        if (!x.equals("y"))
                            tag = false;
                    }
                    break;
                }
                case "2":{
                    System.out.println("----------你要查询哪天的天气(yyyy-MM-dd)?----------");
                    String strDate=in.next();
                    long num = new CountMethods().countWeatherInfByDate(strDate);
                    System.out.println("----------该天已经储存有" + num + "个城市的天气信息----------");
                    System.out.println("----------请输入一页要查看的信息数量----------");
                    long pageSize = in.nextLong();
                    long total = (int) Math.ceil((double) num / pageSize);//总页数
                    System.out.println("----------共有" + total + "页可能的天气信息----------");
                    boolean tag = true;
                    while (tag) {
                        System.out.println("----------请输入你要查看第几页(从1开始,优先显示最近日期)?----------");
                        long pageIndex = in.nextLong();
                        ArrayList<Weather_With_Name> weatherArrayList = new QueryMethods().
                                queryWeatherByDate(pageSize, pageIndex,strDate);
                        System.out.println("----------当前第" + pageIndex + "/" + total + "页----------");
                        System.out.println("----------------------------------------");
                        for (Weather_With_Name weather : weatherArrayList) {
                            System.out.println(JSON.toJSONString(weather));
                        }
                        System.out.println("----------------------------------------\n");
                        System.out.println("----------还要查看其他页吗(y or n)？----------");
                        String x = in.next();
                        if (!x.equals("y"))
                            tag = false;
                    }
                    break;
                }
                case "0":{
                    return;
                }
                default:{
                    System.out.println("----------非法输入!----------");
                }
            }
        }
    }

    public void queryInit() {
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
                    findBasic();
                    break;
                }
                case "2": {
                    findWeather();
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
