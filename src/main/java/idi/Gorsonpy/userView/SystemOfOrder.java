package idi.Gorsonpy.userView;

import idi.Gorsonpy.JavaBean.User;
import idi.Gorsonpy.function.Func.Sign_LoginFunc;

import java.util.Scanner;

public class SystemOfOrder {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Sign_LoginFunc uf = new Sign_LoginFunc();
        User aUser;
        boolean flag = true;
        System.out.println("-----------这里是西二在线订单管理系统-----------");
        while (flag) {
            System.out.println("-----------***登录&&注册界面***-----------");
            System.out.println("-----------1.现在登录-----------");
            System.out.println("-----------2.先去注册-----------");
            System.out.println("-----------0.退出-----------");
            String d = in.next();
            char c = d.charAt(0);
            switch (c) {
                case '1': {
                    LoginView lv = new LoginView();
                    aUser = lv.loginView();
                    if (aUser == null) {
                        System.out.println("----------登录失败----------");
                    } else if (aUser.isAdmin()) {
                        AdminUserView auv = new AdminUserView();
                        auv.AdminUserInit();
                    } else {
                        GeneralUserView guv = new GeneralUserView();
                        guv.GeneralUserInit();
                    }
                    break;
                }
                case '2': {
                    uf.sign();
                    break;
                }
                case '0': {
                    System.out.println("-----------Bye~-----------");
                    flag = false;
                    break;
                }
                default: {
                    System.out.println("-----------非法输入！-----------");
                    break;
                }
            }
        }
    }
}
