package idi.Gorsonpy.userView;

import idi.Gorsonpy.function.Func.AdminUserFunc;

import java.util.Scanner;

public class AdminUserView {
    public void AdminUserInit() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("----------***管理员用户界面***----------");
            System.out.println("----------(管理员用户，拥有全部功能)----------");
            System.out.println("----------1.查询功能----------");
            System.out.println("----------2.更新功能----------");
            System.out.println("----------3.删除功能----------");
            System.out.println("----------0.退出(返回上级界面）----------");
            String d = in.next();
            switch (d) {
                case "1": {
                    AdminUserFunc auf = new AdminUserFunc();
                    auf.queryOrders();
                    System.out.println("----------输入1.返回登录界面----------");
                    System.out.println("----------其他任意内容：返回上级界面----------");
                    String s = in.next();
                    if (s.equals("1"))
                        return;
                    break;
                }
                case "2": {
                    AdminUserFunc auf = new AdminUserFunc();
                    auf.renewInf();
                    System.out.println("----------输入1.返回登录界面----------");
                    System.out.println("----------其他任意内容：返回上级界面----------");
                    String s = in.next();
                    if (s.equals("1"))
                        return;
                    break;
                }
                case "3": {
                    AdminUserFunc auf = new AdminUserFunc();
                    auf.deleteInf();
                    System.out.println("----------输入1.返回登录界面----------");
                    System.out.println("----------其他任意内容：返回上级界面----------");
                    String s = in.next();
                    if (s.equals("1"))
                        return;
                    break;
                }
                case "0": {
                    return;
                }
                default: {
                    System.out.println("----------非法输入！----------");
                    break;
                }
            }
        }
    }
}
