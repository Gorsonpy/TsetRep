package testFunc;

import idi.Gorsonpy.function.CountMethods;

//测试各计数方法
public class TestCount {
    public static void main(String[] args){
        CountMethods cm=new CountMethods();
        long num1=cm.countCityByName("州");
        long num2=cm.countWeatherInfById("101230101");
        System.out.println(num1);
        System.out.println(num2);
    }
}
