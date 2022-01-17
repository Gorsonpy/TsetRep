package idi.Gorsonpy.userview;

import java.util.Scanner;

public class WeatherQuerySys {
    public void init(){
        Scanner in = new Scanner(System.in);
        //刚启动就先自动更新一次已有城市天气信息
        System.out.println("----------正在更新最新天气数据......----------");
        new RenewView().renewAll();
        System.out.println("----------更新完成,进入系统......----------");
        while(true) {
            System.out.println("----------**欢迎来到天气查询系统!***----------");
            System.out.println("----------1.查询功能----------");
            System.out.println("----------2.更新数据----------");
            System.out.println("----------0.退出----------");
            String s = in.next();
            switch(s){
                case "1":{
                    new QueryView().queryInit();
                    break;
                }
                case "2":{
                    new RenewView().renewInit();
                    break;
                }
                case "0":{
                    System.out.println("----------Bye~----------");
                    return;
                }
                default:{
                    System.out.println("----------非法输入！----------");
                    break;
                }
            }
        }
    }
}
