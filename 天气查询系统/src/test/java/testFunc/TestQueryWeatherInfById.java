package testFunc;

import com.alibaba.fastjson.JSONObject;
import idi.Gorsonpy.JavaBean.Weather;
import idi.Gorsonpy.function.QueryMethods;

import java.util.ArrayList;

public class TestQueryWeatherInfById {
    public static void main(String[] args){
        ArrayList<Weather> weatherArrayList=new QueryMethods().queryWeatherInfById(2,1,"101230101");
        for(Weather weather:weatherArrayList){
            System.out.println(JSONObject.toJSONString(weather));
        }
    }
}
