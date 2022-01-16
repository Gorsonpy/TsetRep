package idi.Gorsonpy.JavaBean;

import lombok.Data;

@Data //lombok的Data可以自动添加getter、setter方法等等
public class Basic {
    private String name;
    private String city_ID;
    private String lat;//纬度
    private String lon;//精度
}
