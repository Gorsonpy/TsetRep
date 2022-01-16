package testAPI;

import idi.Gorsonpy.JavaBean.Basic;
import idi.Gorsonpy.utils.ConnectAPI;

//测试从API获取指定名称城市基本信息
public class TestGetBasicInf {
    public static void main(String[] args) {
        Basic basic1=ConnectAPI.getBasicInf("福州");
        Basic basic2=ConnectAPI.getBasicInf("北京");
        Basic basic3=ConnectAPI.getBasicInf("上海");
        System.out.println(basic1);
        System.out.println(basic2);
        System.out.println(basic3);
    }
}
