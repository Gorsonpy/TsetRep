package idi.Gorsonpy.userview;

import idi.Gorsonpy.JavaBean.Weather;
import idi.Gorsonpy.function.CountMethods;
import idi.Gorsonpy.function.QueryMethods;
import idi.Gorsonpy.function.RenewMethods;
import idi.Gorsonpy.utils.ConnectAPI;

import java.util.ArrayList;
import java.util.Scanner;

public class RenewView {

    //更新城市基本信息的方法
    public void renewCityBasic() {
        Scanner in = new Scanner(System.in);
        System.out.println("----------请输入你要更新信息的城市的名称----------");
        String cityName = in.next();
        new RenewMethods().renewBasicInf(ConnectAPI.getBasicInf(cityName));
    }

    //更新未来三天天气信息的方法(无参数版本获取用户输入版本，用于更新功能界面使用)
    public void renewWeather() {
        Scanner in = new Scanner(System.in);
        System.out.println("----------下面转至查询界面,请寻找要更新天气信息的城市编号----------");
        new QueryView().findBasic();//借助查询界面让用户看到城市编号
        System.out.println("----------请输入要更新天气信息的城市id编号----------");
        String cityId = in.next();
        String cityName = new QueryMethods().queryNameByCityId(cityId);
        System.out.println("----------正在更新" + cityName + "未来三天的天气信息(可能会造成覆盖)----------");
        ArrayList<Weather> weatherArrayList = new ArrayList<>();
        weatherArrayList = ConnectAPI.getWeatherInf(cityId);
        if (weatherArrayList == null || weatherArrayList.isEmpty()) {
            System.out.println("----------Error!请检查城市编号是否正确!----------");
        } else {
            for (Weather weather : weatherArrayList) {
                new RenewMethods().renewWeatherInf(weather, cityId);
            }
        }
    }

    public void renewAll() {
        long num = new CountMethods().countAllCityNum();
        if (num == 0) {
            System.out.println("----------Error!当前未收录任何城市！----------");
        } else {
            System.out.println("----------当前已经收录了" + num + "个城市----------");
            System.out.println("----------正在为你更新他们未来三天的天气信息----------");
            ArrayList<String> idArrayList = new QueryMethods().queryAllCityId();
            for (String cityId : idArrayList) {
                String name = new QueryMethods().queryNameByCityId(cityId);
                System.out.println("----------正在为你更新" + name + "未来三天的天气信息----------");
                ArrayList<Weather> weatherArrayList = new ArrayList<>();
                weatherArrayList = ConnectAPI.getWeatherInf(cityId);
                for (Weather weather : weatherArrayList) {
                    new RenewMethods().renewWeatherInf(weather, cityId);
                }
            }
        }
    }

    public void renewInit() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("----------**欢迎来到数据更新功能!***----------");
            System.out.println("----------1.更新城市信息(仅更新城市基本信息，不更新天气)----------");
            System.out.println("----------2.更新指定城市未来三天天气数据----------");
            System.out.println("----------3.更新所有已收录城市未来三天天气数据----------");
            System.out.println("----------0.退出----------");
            String s = in.next();
            switch (s) {
                case "1": {
                    renewCityBasic();
                    System.out.println("----------更新成功！----------");
                    break;
                }
                case "2": {
                    renewWeather();
                    System.out.println("----------更新成功！----------");
                    break;
                }
                case "3": {
                    renewAll();
                    System.out.println("----------更新成功！----------");
                    break;
                }
                case "0": {
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
