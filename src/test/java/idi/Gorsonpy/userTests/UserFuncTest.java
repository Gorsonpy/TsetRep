package idi.Gorsonpy.userTests;

import idi.Gorsonpy.function.Func.GeneralUserFunc;
import idi.Gorsonpy.function.Func.UserFunc;

//测试公共功能(查询功能）
public class UserFuncTest {
    public static void main(String[] args) {
        UserFunc um = new UserFunc();
        um.queryOrders();
        GeneralUserFunc gum = new GeneralUserFunc();
        gum.queryOrders();
    }
}
