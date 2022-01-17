package testAPI;

import idi.Gorsonpy.JavaBean.Weather;
import idi.Gorsonpy.utils.ConnectAPI;

import java.util.ArrayList;

//测试获取三天城市天气信息
public class TestGetWeatherInf {
    public static void main(String[] args) {
        //福州id为101230101
        ArrayList<Weather> weatherArrayList = ConnectAPI.getWeatherInf("101230101");
        for (Weather weather : weatherArrayList) {
            System.out.println(weather);
        }
    }
}
