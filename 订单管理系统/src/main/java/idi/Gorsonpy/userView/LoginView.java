package idi.Gorsonpy.userView;

import idi.Gorsonpy.JavaBean.User;
import idi.Gorsonpy.function.Func.Sign_LoginFunc;

import java.util.Scanner;

public class LoginView {
    //登录界面
    public User loginView() {
        User aUser;
        Scanner in = new Scanner(System.in);
        System.out.println("-----------请输入用户名-----------");
        String username = in.next();
        System.out.println("-----------请输入密码-----------");
        String password = in.next();
        //登录流程
        aUser = Sign_LoginFunc.Login(username, password);
        if (aUser == null) {
            System.out.println("-----------未找到该用户信息，请检查用户名或密码是否正确-----------");
        } else {
            System.out.println("-----------你好！ " + aUser.getUsername() + "-----------");
            if (aUser.isAdmin()) System.out.println("-----------你的身份是：管理员-----------");
            else System.out.println("-----------你的身份是：普通用户-----------");
        }
        return aUser;
    }
}
