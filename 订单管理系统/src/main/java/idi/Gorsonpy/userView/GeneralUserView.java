package idi.Gorsonpy.userView;

import idi.Gorsonpy.function.Func.GeneralUserFunc;

import java.util.Scanner;

public class GeneralUserView {
    public void GeneralUserInit() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("----------***普通用户界面***----------");
            System.out.println("----------(普通用户，你仅有查询功能)----------");
            System.out.println("----------1.开始查询----------");
            System.out.println("----------0.退出(返回上级界面）----------");
            String d = in.next();
            switch (d) {
                case "1": {
                    GeneralUserFunc gum = new GeneralUserFunc();
                    gum.queryOrders();
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
                    System.out.println("-----------非法输入！-----------");
                    break;
                }
            }
        }
    }
}
