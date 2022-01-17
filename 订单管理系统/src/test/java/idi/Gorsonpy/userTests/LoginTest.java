package idi.Gorsonpy.userTests;

import idi.Gorsonpy.JavaBean.User;
import idi.Gorsonpy.function.Func.Sign_LoginFunc;

//测试用户登录功能(先用sign测试类注册部分对应账号密码，再用本测试类测试能否给出正确的登录反应
public class LoginTest {
    public static void main(String[] args) {
        User aUser = Sign_LoginFunc.Login("Admin1", "Admin1!!!");
        User cUser = Sign_LoginFunc.Login("General1", "General1!!!");
        //以下是一个不可能被注册的账号，作为特判
        User bUser = Sign_LoginFunc.Login("h", "www");
    }
}
