package idi.Gorsonpy.userTests;

import idi.Gorsonpy.function.Func.Sign_LoginFunc;

/*
测试用户注册功能,程序运行观察数据库表users是否正确写入内容
 */
public class SignTest {
    public static void main(String[] args) {
        Sign_LoginFunc ui = new Sign_LoginFunc();
        ui.sign();
    }
}
