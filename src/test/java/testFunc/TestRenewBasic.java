package testFunc;

import idi.Gorsonpy.JavaBean.Basic;
import idi.Gorsonpy.function.RenewMethods;
import idi.Gorsonpy.utils.ConnectAPI;

//测试更新城市基本信息
public class TestRenewBasic {
    public static void main(String[] args){
        //测试更新或添加福州的城市基本信息
        Basic testBasic= ConnectAPI.getBasicInf("福州");
        RenewMethods.renewBasicInf(testBasic);
    }
}
