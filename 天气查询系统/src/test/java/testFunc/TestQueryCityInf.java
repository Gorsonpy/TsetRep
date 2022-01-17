package testFunc;

import com.alibaba.fastjson.JSONObject;
import idi.Gorsonpy.JavaBean.Basic;
import idi.Gorsonpy.function.QueryMethods;

import java.util.ArrayList;

public class TestQueryCityInf {
    public static void main(String[] args) {
        ArrayList<Basic> basicArrayList = new QueryMethods().queryCityInf(2, 1, "州");
        for (Basic basic : basicArrayList)
            System.out.println(JSONObject.toJSONString(basic));
    }
}
