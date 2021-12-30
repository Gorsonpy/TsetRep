package idi.Gorsonpy.generalTests;

import com.alibaba.fastjson.JSON;
import idi.Gorsonpy.JavaBean.Good;
import idi.Gorsonpy.function.Methods.QueryMethods;

import java.util.ArrayList;

//测试分页显示商品的方法
public class ShowGoodsTest {
    public static void main(String[] args) {
        //一页两条，查询的是第一页面
        ArrayList<Good> goodArrayList = QueryMethods.showGoods(2, 1);
        for (Good good : goodArrayList) {
            System.out.println(JSON.toJSONString(good));
        }
    }
}
